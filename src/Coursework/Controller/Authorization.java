package Coursework.Controller;

import Coursework.Model.*;

import Coursework.View.InformationWindow;
import Coursework.View.RegistrationWindow;


import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.stage.Stage;

public class Authorization {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password;

    @FXML
    private Button authSigInButton;

    @FXML
    private TextField login;

    @FXML
    private Button regisSigInButton;

    private Alert alertError;

public static Student student;
    @FXML





    void authorization(ActionEvent event) throws Exception {
        Students students = new Students();
        Teachers teachers = new Teachers();


        String tempLogin = login.getText();
        String tempPassword = password.getText();

        if (students.checkCredentialsStudent(tempLogin, tempPassword)) {
            Config.isTeacher = false;                                         //Проверка на использование программы

            InformationWindow informationWindow = new InformationWindow();
            informationWindow.start(new Stage());

            Stage stage = (Stage) authSigInButton.getScene().getWindow();
            stage.close();
        } else if (teachers.checkCredentialsTeacher(tempLogin, tempPassword)) {
            Config.isTeacher = true;

            InformationWindow informationWindow = new InformationWindow();
            informationWindow.start(new Stage());

            Stage stage = (Stage) authSigInButton.getScene().getWindow();
            stage.close();
        } else if (students.checkCredentialsStudent(tempLogin, tempPassword) == false &&
                teachers.checkCredentialsTeacher(tempLogin, tempPassword) == false) {
            onError("Wrong data", "Неверный логин или пароль", "");



        }

    }


    @FXML
    void registration(ActionEvent event) {
        try {
            RegistrationWindow registrationWindow = new RegistrationWindow();
            registrationWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) regisSigInButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {

        alertError = new Alert(Alert.AlertType.ERROR);
        Database.getDatabase();
    }

    private void onError(String title, String headerText, String ContextText) {
        alertError.setTitle(title);
        alertError.setHeaderText(headerText);
        alertError.setContentText(ContextText);
        alertError.show();
    }

}