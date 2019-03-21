package Coursework.Controller;


import java.net.URL;
import java.util.ResourceBundle;


import Coursework.Model.Config;
import Coursework.Model.Database;
import Coursework.Model.Lecture;
import Coursework.Model.Lectures;
import Coursework.View.InformationWindow;
import Coursework.View.LectureNewWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class LectureRead {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea textLecture;

    @FXML
    private Button newLectureButton;

    @FXML
    private ListView<Lecture> lecturesList;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteLectureButton;

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
    void newLecture(ActionEvent event) {
        try {
            LectureNewWindow lectureNewWindow = new LectureNewWindow();
            lectureNewWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {

        Database.getDatabase();

        Lectures lectures = new Lectures();

        lecturesList.setItems(lectures.getAll());

        if (!Config.isTeacher) {
            newLectureButton.setVisible(false);
            deleteLectureButton.setVisible(false);
        }
    }

    @FXML
    void openLecture(MouseEvent event) {
        try {
            String lectureContent = lecturesList.getSelectionModel().getSelectedItem().getContent();
            textLecture.setText(lectureContent);
        }catch (Exception e){
            System.out.println("Lecture list is empty.");
        }
    }


    @FXML
    void deleteLecture(ActionEvent event) {
        try {
            System.out.println("Delete element: " + lecturesList.getSelectionModel().getSelectedItem());

            Lecture lecture = lecturesList.getSelectionModel().getSelectedItem();
            lecture.deleteLecture();

            int lectureIndexInList = lecturesList.getSelectionModel().getSelectedIndex();
            lecturesList.getItems().remove(lectureIndexInList);
        }catch (Exception e){
            System.out.println("Lecture does not selected.");
        }

        }
    }



