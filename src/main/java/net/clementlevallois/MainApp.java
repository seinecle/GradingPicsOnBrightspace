package net.clementlevallois;

import IO.PictureLoader;
import Model.Assignment;
import PropertiesManager.PropertiesManager;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        int sceneHeight = 500;
        int sceneWidth = 0;
        int sceneWidthMax = 1200;
        int imageWidthFit = 300;

        PropertiesManager pm = new PropertiesManager();
        String path = pm.readingProperty("pathAssignments");

        PictureLoader pictureLoader = new PictureLoader();
        List<Assignment> assignments = pictureLoader.loadAssignmentsInMemory(path);

        Assignment assignment = assignments.iterator().next();

        
        //stackpane
        StackPane root = new StackPane();
        
        
        TilePane tileImages = new TilePane(Orientation.HORIZONTAL);
        
        double totalImageWidth = 0;

        for (Image image : assignment.getImages()) {

            double picHeight = image.getHeight();
            double picWidth = image.getWidth();

            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitHeight(sceneHeight);
            imageView.setFitWidth(imageWidthFit);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            
            tileImages.getChildren().add(imageView);
            
            totalImageWidth = totalImageWidth + imageView.getFitWidth();

        }

        Button passButton = new Button();
        passButton.setMinHeight(30);
        passButton.setMaxHeight(30);
        passButton.setMinWidth(80);
        passButton.setMaxWidth(80);
        passButton.setText("Pass");


        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        tileButtons.setHgap(10.0);
        tileButtons.setVgap(8.0);
        tileButtons.getChildren().addAll(passButton);
        tileButtons.setAlignment(Pos.BOTTOM_CENTER);

        root.getChildren().add(tileImages);
        root.getChildren().add(tileButtons);

        Scene scene = new Scene(root, totalImageWidth, sceneHeight);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Image Read Test");
        stage.setScene(scene);
        stage.show();

//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
//        Scene scene = new Scene(root);
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
