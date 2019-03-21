package Coursework.Controller;


import java.net.URL;
import java.util.ResourceBundle;

import Coursework.Model.*;
import Coursework.View.LectureReadWindow;
import Coursework.View.MarkReadWindow;
import Coursework.View.TaskReadWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button lectureButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button taskButton;

    @FXML
    private Button markButton;



    @FXML
    void lecture(ActionEvent event) {
        try {
            LectureReadWindow lectureReadWindow = new LectureReadWindow();
            lectureReadWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) lectureButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void task(ActionEvent event) {
        try {
            TaskReadWindow taskReadWindow = new TaskReadWindow();
            taskReadWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) taskButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void mark(ActionEvent event) {
        try {
            MarkReadWindow markReadWindow = new MarkReadWindow();
            markReadWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) markButton.getScene().getWindow();
        stage.close();

    }


    @FXML
    void exitInHomePage(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        Database.getDatabase();


    }
}
