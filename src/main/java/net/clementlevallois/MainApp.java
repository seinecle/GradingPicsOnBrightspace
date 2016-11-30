package net.clementlevallois;

import IO.PictureLoader;
import Model.Assignment;
import PropertiesManager.PropertiesManager;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        int sceneMaxHeight = 1200;
        int sceneMaxWidth = 1900;
        

        PropertiesManager pm = new PropertiesManager();
        String path = pm.readingProperty("pathAssignments");

        PictureLoader pictureLoader = new PictureLoader();
        List<Assignment> assignments = pictureLoader.loadAssignmentsInMemory(path);
        
                
        ControllerScene1 controllerScene1 = new ControllerScene1();
        Scene scene1 = controllerScene1.buildScene1(assignments,sceneMaxHeight,sceneMaxWidth,stage);
        

        stage.setTitle("Visual Assignment Grader");
        stage.setScene(scene1);
        stage.show();

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
