package Coursework.Controller;



import Coursework.Model.Config;
import Coursework.Model.Database;
import Coursework.Model.Student;
import Coursework.Model.Teacher;

import Coursework.View.StartWindow;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;



public class Registration {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Stage stage;

    @FXML
    private RadioButton choiceStydent;

    @FXML
    private TextField password;

    @FXML
    private ToggleGroup reg;

    @FXML
    private RadioButton choiceTeacher;

    @FXML
    private TextField surname;

    @FXML
    private TextField name;

    @FXML
    private Button doRegistButton;

    @FXML
    private TextField login;

    @FXML
    private TextField lastname;

    @FXML
    private TextField group;

    private Alert alertError;

    private Alert alertInformation;



    @FXML
    void doRegist(ActionEvent event) throws Exception {

        Student student;
        Teacher teacher;

        boolean selectedTeacher = choiceTeacher.isSelected();
        boolean selectedStudent = choiceStydent.isSelected();

        String tempLogin = login.getText();
        String tempPassword = password.getText();
        String tempName = name.getText();
        String tempSurname = surname.getText();
        String tempLastName = lastname.getText();

        if (selectedStudent) {
            String tempGroup = group.getText();
            int tempStudent = (selectedStudent) ? 1 : 0;

            if (login.getLength() == 0 || password.getLength() == 0 || name.getLength() == 0 || surname.getLength() == 0 || lastname.getLength() == 0 || group.getLength() == 0) {
                onError("Data entry error", "Заполните пустые поля", "");
            } else if (login.getText().equals("0") != true && password.getText().equals("0") != true  && name.getText().equals("0") != true && surname.getText().equals("0") != true && lastname.getText().equals("0") != true && group.getText().equals("0") != true) {
                student = new Student(tempLogin, tempPassword, tempName, tempSurname, tempLastName, tempGroup, tempStudent);
                student.addToDatabaseStudent();

                Stage stage = (Stage) doRegistButton.getScene().getWindow();
                stage.close();
                StartWindow startWindow = new StartWindow();
                startWindow.start(new Stage());
                onInformation("Registration", "Регистрация завершина.", "Пользователь: " + login.getText() + " зарегестророван.");

            }
        } else if (selectedTeacher) {
            int tempTeacher = (selectedTeacher) ? 1 : 0;

            if (login.getLength() == 0 || password.getLength() == 0 || name.getLength() == 0 || surname.getLength() == 0 || lastname.getLength() == 0) {
                onError("Data entry error", "Заполните пустые поля", "");
            } else if (login.getText().equals("0") != true && password.getText().equals("0") != true  && name.getText().equals("0") != true && surname.getText().equals("0") != true && lastname.getText().equals("0") != true) {
                teacher = new Teacher(tempLogin, tempPassword, tempName, tempSurname, tempLastName, tempTeacher);
                teacher.addToDatabaseTeacher();

                Stage stage = (Stage) doRegistButton.getScene().getWindow();
                stage.close();
                StartWindow startWindow = new StartWindow();
                startWindow.start(new Stage());
                onInformation("Registration", "Регистрация завершина.", "Пользователь: " + login.getText() + " зарегестророван.");
            }
        }
    }

    @FXML
    void onTeacher(ActionEvent event) {
        group.setDisable(true);
    }

    @FXML
    void onStudent(ActionEvent event) {
        group.setDisable(false);
    }

    @FXML
    void initialize() {
        alertInformation = new Alert(Alert.AlertType.INFORMATION);
        alertError = new Alert(Alert.AlertType.ERROR);
        Database.getDatabase();
    }


    private void onInformation(String title, String headerText, String ContextText) {
        alertInformation.setTitle(title);
        alertInformation.setHeaderText(headerText);
        alertInformation.setContentText(ContextText);
        alertInformation.show();
    }

    private void onError(String title, String headerText, String ContextText) {
        alertError.setTitle(title);
        alertError.setHeaderText(headerText);
        alertError.setContentText(ContextText);
        alertError.show();
    }
}