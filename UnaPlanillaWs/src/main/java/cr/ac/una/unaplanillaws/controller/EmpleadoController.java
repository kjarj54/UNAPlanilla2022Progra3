/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanillaws.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import cr.ac.una.unaplanillaws.model.EmpleadoDto;
import cr.ac.una.unaplanillaws.service.EmpleadoService;
import cr.ac.una.unaplanillaws.util.CodigoRespuesta;
import cr.ac.una.unaplanillaws.util.JwTokenHelper;
import cr.ac.una.unaplanillaws.util.Respuesta;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import cr.ac.una.unaplanillaws.util.Secure;

/**
 *
 * @author Carlos
 */

//TODO
@Path("/EmpleadoController")
public class EmpleadoController {

    //TODO
    @EJB
    EmpleadoService empleadoService;
    
    //TODO
    @GET
    @Path("/ping")
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
    
    //TODO
    @GET
    @Path("/usuario/{usuario}/{clave}")
    public Response getUsuario(@PathParam("usuario") String usuario,@PathParam("clave") String clave) {
        try {
            Respuesta res = empleadoService.validarUsuario(usuario, clave);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            EmpleadoDto empleadoDto = (EmpleadoDto) res.getResultado("Empleado");
            //empleadoDto.setToken(null);//TODO
            return Response.ok(empleadoDto).build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }

    //TODO
    @GET
    @Path("/id/{id}")
    public Response getEmpleado(Long id) {
        try {
            Respuesta res = empleadoService.getEmpleado(id);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            EmpleadoDto empleadoDto = (EmpleadoDto) res.getResultado("Empleado");
            return Response.ok(empleadoDto).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el id").build();//TODO
        }
    }
/*
    //TODO
    public Response getEmpleados(String cedula, String nombre, String pApellido) {
        try {
            Respuesta res = empleadoService.getEmpleados(cedula, nombre, pApellido);
            if (!res.getEstado()) {
                return null;//TODO
            }
            return null;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return null;//TODO
        }
    }

    //TODO
    public Response guardarEmpleado(EmpleadoDto empleado) {
        try {
            Respuesta respuesta = empleadoService.guardarEmpleado(empleado);
            if (!respuesta.getEstado()) {
                return null;//TODO
            }
            return null;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return null;//TODO
        }
    }
    
    //TODO
    public Response eliminarEmpleado(Long id) {
        try {
            Respuesta respuesta = empleadoService.eliminarEmpleado(id);
            if (!respuesta.getEstado()) {
                return null;//TODO
            }
            return null;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return null;//TODO
        }
    }
*/
}
