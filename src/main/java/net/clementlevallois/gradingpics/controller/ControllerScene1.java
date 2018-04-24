/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.gradingpics.controller;

import net.clementlevallois.gradingpics.io.Excel;
import net.clementlevallois.gradingpics.model.Assignment;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 *
 * @author LEVALLOIS
 */
public class ControllerScene1 {

    Assignment assignment;
    double totalImageWidth = 0;
    TilePane tileImages;
    TilePane tileButtons;
    TilePane buttonsPlusStudentName;
    Label label;
    Button button14;
    Button button16;
    Button failButton;
    Button button18;

    public Scene buildScene1(List<Assignment> assignments, int sceneMaxHeight, int sceneWidthMax, Stage stage) {

        assignment = getNextAssignment(assignments, stage);
        if (assignment == null) {
            new MainApp().stop();
        }

        //stackpane: the thing that manages layers on the screen.
        StackPane stackPane = new StackPane();

        label = setupLabel();

        //TilePanes guarantees the alignment of objects on screen
        tileImages = setupTilePics(sceneMaxHeight, sceneWidthMax);

        //we add 3 buttons to mark the assignment.
        button14 = new Button();
        button14.setMinHeight(30);
        button14.setMaxHeight(30);
        button14.setMinWidth(80);
        button14.setMaxWidth(80);
        button14.setText("14");
        button14.setOnAction(event -> {
            try {
                Excel excel = new Excel();
                excel.writeFGradeForOneStudent(assignment.getStudent().getFullName(), "14");
                Scene scene = new ControllerScene1().buildScene1(assignments, sceneMaxHeight, sceneWidthMax, stage);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ControllerScene1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        button16 = new Button();
        button16.setMinHeight(30);
        button16.setMaxHeight(30);
        button16.setMinWidth(80);
        button16.setMaxWidth(80);
        button16.setText("16");
        button16.setOnAction(event -> {
            try {
                Excel excel = new Excel();
                excel.writeFGradeForOneStudent(assignment.getStudent().getFullName(), "16");
                Scene scene = new ControllerScene1().buildScene1(assignments, sceneMaxHeight, sceneWidthMax, stage);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ControllerScene1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
        failButton = new Button();
        failButton.setMinHeight(30);
        failButton.setMaxHeight(30);
        failButton.setMinWidth(80);
        failButton.setMaxWidth(80);
        failButton.setText("FAIL");
        failButton.setOnAction(event -> {
            try {
                Excel excel = new Excel();
                excel.writeFGradeForOneStudent(assignment.getStudent().getFullName(), "08");
                Scene scene = new ControllerScene1().buildScene1(assignments, sceneMaxHeight, sceneWidthMax, stage);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ControllerScene1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
        button18 = new Button();
        button18.setMinHeight(30);
        button18.setMaxHeight(30);
        button18.setMinWidth(80);
        button18.setMaxWidth(80);
        button18.setText("18");
        button18.setOnAction(event -> {
            try {
                Excel excel = new Excel();
                excel.writeFGradeForOneStudent(assignment.getStudent().getFullName(), "18");
                Scene scene = new ControllerScene1().buildScene1(assignments, sceneMaxHeight, sceneWidthMax, stage);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ControllerScene1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        tileButtons = setupTileButtons();
        buttonsPlusStudentName = setupTilesButtonsAndName();

        stackPane.getChildren().add(tileImages);
        stackPane.getChildren().add(buttonsPlusStudentName);

        //we build the scene.
        Scene scene = new Scene(stackPane, sceneWidthMax, sceneMaxHeight);

        scene.getStylesheets().add("/styles/Styles.css");

        //students might have submitted several pics for a given assignment. We display all pics on a single screen, from left to right.
        //now we add the pics, and the button (in this order to make sure the buttons are visible on top of the pictures)
        return scene;
    }

    private Assignment getNextAssignment(List<Assignment> assignments, Stage stage) {
        if (assignments.size() > 0) {
            return assignments.remove(0);
        } else {
            stage.close();
            new MainApp().stop();
            return null;
        }
    }

    private TilePane setupTilePics(int sceneMaxHeight, int sceneWidthMax) {
        TilePane tileImages = new TilePane(Orientation.HORIZONTAL);
        totalImageWidth = 0;
        int nbImages = assignment.getImages().size();
        int maxWidthPerPic = Math.round((float) sceneWidthMax / (float) nbImages);
        for (Image image : assignment.getImages()) {

            double picHeight = image.getHeight();
            double picWidth = image.getWidth();

            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitHeight(Math.min(picHeight, sceneMaxHeight));
            imageView.setFitWidth(Math.min(picWidth, maxWidthPerPic));
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);

            tileImages.getChildren().add(imageView);

            //here we keep track of the total width of the pictures, to adjust the width of the Scene accordingly
            totalImageWidth = totalImageWidth + imageView.getFitWidth();

        }
        if (tileImages.getChildren().isEmpty()) {
            Label label = new Label("no image in this submission");
            label.setStyle("-fx-background-color: red;");
            tileImages.getChildren().add(label);
        }
        return tileImages;

    }

    private Label setupLabel() {
        Label label = new Label(assignment.getStudent().getFullName());
        label.setStyle("-fx-background-color: white;-fx-font-size: 20px;");
        label.setAlignment(Pos.BOTTOM_CENTER);
        return label;
    }

    private TilePane setupTileButtons() {
        //we put the buttons in a TilePane, to make sure they are aligned horizontally. The TilePane itself will be visible at the bottom center of the app.
        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);

        tileButtons.setPadding(new Insets(5, 10, 5, 0));
        tileButtons.setHgap(10.0);
        tileButtons.setVgap(0.0);
        tileButtons.getChildren().addAll(button14, failButton, button16, button18);
        tileButtons.setAlignment(Pos.BOTTOM_CENTER);

        return tileButtons;

    }

    private TilePane setupTilesButtonsAndName() {
        TilePane buttonsPlusStudentName = new TilePane(Orientation.VERTICAL);
        buttonsPlusStudentName.setPadding(new Insets(20, 10, 20, 0));
        buttonsPlusStudentName.setHgap(10.0);
        buttonsPlusStudentName.setVgap(8.0);
        buttonsPlusStudentName.getChildren().addAll(tileButtons, label);
        buttonsPlusStudentName.setAlignment(Pos.BOTTOM_CENTER);

        return buttonsPlusStudentName;

    }

}
