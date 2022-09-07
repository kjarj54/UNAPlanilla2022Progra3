package cr.ac.una.unaplanilla;

import cr.ac.una.unaplanilla.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage,null);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/cr/ac/una/unaplanilla/resources/Agregar-48.png")));
        stage.setTitle("UNA PLANILLA");
        FlowController.getInstance().goViewInWindow("LogIng2");
    }

    public static void main(String[] args) {
        launch();
    }

}