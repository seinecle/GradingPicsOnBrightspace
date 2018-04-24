package net.clementlevallois.gradingpics.controller;

import net.clementlevallois.gradingpics.io.PictureLoader;
import net.clementlevallois.gradingpics.model.Assignment;
import net.clementlevallois.gradingpics.propertiesmanager.PropertiesManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.stage.Screen;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        Double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        int sceneMaxHeight = screenHeight.intValue();
        int sceneMaxWidth = screenWidth.intValue();

        //get the path of the assignments
        PropertiesManager pm = new PropertiesManager();
        String path = pm.readingProperty("pathAssignments");

        //loads the assignments
        PictureLoader pictureLoader = new PictureLoader();
        List<Assignment> assignments = pictureLoader.loadAssignmentsInMemory(path);

        //launches the assignment gradr
        ControllerScene1 controllerScene1 = new ControllerScene1();
        Scene scene1 = controllerScene1.buildScene1(assignments, sceneMaxHeight, sceneMaxWidth, stage);
        if (scene1 != null) {
            stage.setTitle("Visual Assignment Grader");
            stage.setScene(scene1);
            stage.setMaximized(true);
            stage.show();
        }
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

    @Override
    public void stop() {
        System.out.println("Stage is closing");
        // Save file
    }

}
