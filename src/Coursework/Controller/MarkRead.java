package Coursework.Controller;


import java.net.URL;
import java.util.ResourceBundle;

import Coursework.Model.*;
import Coursework.View.InformationWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MarkRead {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button lookMarkButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField lookStudent;

    @FXML
    private TableView<Mark> tableView;

    @FXML
    private TableColumn<Mark, String> nameLectureColonum;

    @FXML
    private TableColumn<Mark, Integer> markInQuestionColonum;


    private Alert alertError;

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
    void lookMark(ActionEvent event) {
        Marks marks = new Marks();

        String tempSurNameStudent = lookStudent.getText();



        if(marks.checkMarksStudent(tempSurNameStudent)){
            System.out.println("fsddsfsdfsd");
            tableView.setItems(marks.getAll(tempSurNameStudent));

        }else if(marks.checkMarksStudent(tempSurNameStudent) == false){
            onError("Wrong data", "Неверный ввод Студента(Фамилия).", "");
        }

    }

    @FXML
    void initialize() {
        String tempSurname;

        Marks marks = new Marks();
        Student student = new Student();
        Students students = new Students();

        nameLectureColonum.setCellValueFactory(new PropertyValueFactory<Mark, String>("lName"));
        markInQuestionColonum.setCellValueFactory(new PropertyValueFactory<Mark, Integer>("quantityMarksStudent"));

        alertError = new Alert(Alert.AlertType.ERROR);

        if (!Config.isTeacher) {
            lookMarkButton.setVisible(false);
            lookStudent.setVisible(false);
            if(Students.getStudentInSystem().getSurnameStudent() != null) {
                tempSurname = Students.getStudentInSystem().getSurnameStudent();
                tableView.setItems(marks.getAll(tempSurname));
            }

        }

        Database.getDatabase();



    }

    private void onError(String title, String headerText, String ContextText) {
        alertError.setTitle(title);
        alertError.setHeaderText(headerText);
        alertError.setContentText(ContextText);
        alertError.show();
    }

}

