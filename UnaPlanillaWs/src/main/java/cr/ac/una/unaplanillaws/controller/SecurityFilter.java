/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanillaws.controller;

import cr.ac.una.unaplanillaws.util.JwTokenHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import java.io.IOException;
import java.security.Principal;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import cr.ac.una.unaplanillaws.util.Secure;
import java.lang.reflect.Method;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;


@Provider
@Secure
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_SERVICE_PATH = "getUsuario";//TODO
    private final JwTokenHelper jwTokenHelper = JwTokenHelper.getInstance();
    private static final String AUTHENTICATION_SCHEME = "Bearer ";
    
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        if (method.getName().equals(AUTHORIZATION_SERVICE_PATH)) {
            return;
        }

        // Get the Authorization header from the request
        String authorizationHeader = request.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            abortWithUnauthorized(request, "Authorization is missing in header");
            return;

        } else if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(request, "Invalid authorization");
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {

            // Validate the token
            try {
                Claims claims = jwTokenHelper.claimKey(token);
                final SecurityContext currentSecurityContext = request.getSecurityContext();
                /*request.setSecurityContext(new SecurityContext() {

                    @Override
                    public Principal getUserPrincipal() {
                        return () -> claims.getSubject();
                    }

                    @Override
                    public boolean isUserInRole(String role) {
                        return true;
                    }

                    @Override
                    public boolean isSecure() {
                        return currentSecurityContext.isSecure();
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return AUTHENTICATION_SCHEME;
                    }
                });*/
            } catch (ExpiredJwtException | MalformedJwtException e) {
                if (e instanceof ExpiredJwtException) {
                    abortWithUnauthorized(request, "Authorization is expired");
                } else if (e instanceof MalformedJwtException) {
                    abortWithUnauthorized(request, "Authorization is not correct");
                }
            }

        } catch (Exception e) {
            abortWithUnauthorized(request, "Invalid authorization");
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase());
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext, String message) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED.getStatusCode(),message)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                message)
                        .build());
    }


}
