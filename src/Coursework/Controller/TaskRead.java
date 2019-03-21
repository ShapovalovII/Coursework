package Coursework.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Coursework.Model.Config;
import Coursework.Model.Database;
import Coursework.Model.Lecture;
import Coursework.Model.Lectures;
import Coursework.View.InformationWindow;
import Coursework.View.TaskNewWindow;
import Coursework.View.TaskQuestionWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TaskRead {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup responseGroup;

    @FXML
    private TextArea question;


    @FXML
    private ListView<Lecture> lectureList;

    @FXML
    private Button newTaskButton;

    @FXML
    private Button completeTaskButton;

    @FXML
    private Button backButton;

    @FXML
    private TextArea option3;

    @FXML
    private TextArea option1;

    @FXML
    private TextArea option2;

    public static int LECTURE_ID;

    private Alert alertInformation;

    @FXML
    void back(ActionEvent event) {

        try {
            InformationWindow informationWindow = new InformationWindow();
            informationWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void newTask(ActionEvent event) {
        try {
            TaskNewWindow taskNewWindow = new TaskNewWindow();
            taskNewWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();

    }


    @FXML
    void completeTask(ActionEvent event) {
        try {
            LECTURE_ID = lectureList.getSelectionModel().getSelectedItem().getId();


            try {
                TaskQuestionWindow taskQuestionWindow = new TaskQuestionWindow();
                taskQuestionWindow.start(new Stage());
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();


            }
        } catch (Exception e) {
            onInformation("Task", "Лекция не выбрана", "");
        }

    }

    @FXML
    void initialize() {
        Database.getDatabase();

        alertInformation = new Alert(Alert.AlertType.INFORMATION);

        if (!Config.isTeacher) {
            newTaskButton.setVisible(false);
        }

        if (Config.isTeacher) {
            completeTaskButton.setVisible(false);
        }

        Lectures lectures = new Lectures();
        lectureList.setItems(lectures.getAll());

    }


    private void onInformation(String title, String headerText, String ContextText) {
        alertInformation.setTitle(title);
        alertInformation.setHeaderText(headerText);
        alertInformation.setContentText(ContextText);
        alertInformation.show();
    }

}