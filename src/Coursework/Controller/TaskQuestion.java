package Coursework.Controller;


import Coursework.Model.*;
import Coursework.View.TaskQuestionWindow;
import Coursework.View.TaskReadWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Coursework.Controller.TaskRead.LECTURE_ID;

public class TaskQuestion {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Label qCounter;

    @FXML
    private URL location;

    @FXML
    private RadioButton chouse1;

    @FXML
    private ToggleGroup responseGroup;

    @FXML
    private Button finishTestButton;

    @FXML
    private RadioButton chouse2;

    @FXML
    private RadioButton chouse3;

    @FXML
    private Label answer3;

    @FXML
    private Label answer2;

    @FXML
    private Label answer1;

    @FXML
    private Label questionText;

    private Tasks tasks;

    private Mark mark;

    private Marks marks;

    private Task currentTask;

    private int taskNumber;

    private Alert alertInformation;
    @FXML
    private AnchorPane close;


    private int countTrueAnswers = 0;

    private int passStudent = 0;

    @FXML
    void finishTest(ActionEvent event) {

    }

    @FXML
    void initialize() {

        Database.getDatabase();

        tasks = new Tasks();

        taskNumber = 1;
        currentTask = tasks.getTaskByIndex(taskNumber);


        System.out.println(LECTURE_ID);
        qCounter.setText(taskNumber + " / " + String.valueOf(tasks.countTasksByLection(LECTURE_ID)));

        setTaskData();


        responseGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

                if (responseGroup.getSelectedToggle() != null) {

                    int tempChoice1 = (chouse1.isSelected()) ? 1 : 0;
                    int tempChoice2 = (chouse2.isSelected()) ? 1 : 0;
                    int tempChoice3 = (chouse3.isSelected()) ? 1 : 0;

                    if (currentTask.getChoice1() == tempChoice1
                            && currentTask.getChoice2() == tempChoice2
                            && currentTask.getChoice3() == tempChoice3) {
                        countTrueAnswers++;
                    }


                    if (taskNumber == tasks.countTasksByLection(LECTURE_ID)) {
                        // if (marks.checkPassStudent(passStudent)) {
                        //passStudent++;
                        //System.out.println((marks.checkPassStudent(passStudent)) + "fdfdff");
                        mark = new Mark(LECTURE_ID, Students.getStudentInSystem().getSurnameStudent(), tasks.countTasksByLection(LECTURE_ID), countTrueAnswers, 1);
                        mark.addToDatabaseMark();
                        // System.out.println((marks.checkPassStudent(passStudent)));
                        // } else if (marks.checkPassStudent(passStudent) == false) {
                        //     System.out.println("fffqwdw");
                        //}


                        try {
                            TaskReadWindow taskReadWindow = new TaskReadWindow();
                            taskReadWindow.start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        alertInformation = new Alert(Alert.AlertType.INFORMATION);
                        onInformation("Task", "Задание пройдено.", "Результат " + countTrueAnswers + "/" + tasks.countTasksByLection(LECTURE_ID));


                        Stage stage = (Stage) close.getScene().getWindow();
                        stage.close();
                    }


                    taskNumber++;

                    try {
                        currentTask = tasks.getTaskByIndex(taskNumber);
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("tasks out");
                    }

                    set();

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    qCounter.setText(taskNumber + " / " + String.valueOf(tasks.countTasksByLection(LECTURE_ID)));

                    setTaskData();

                    anSet();


                    responseGroup.getSelectedToggle().setSelected(false);
                }
            }


        });
    }

    public void setTaskData() {
        questionText.setText(currentTask.getQuestion());

        answer1.setText(currentTask.getAnswer1());
        answer2.setText(currentTask.getAnswer2());
        answer3.setText(currentTask.getAnswer3());
    }

    private void set() {
        chouse1.setDisable(true);
        chouse2.setDisable(true);
        chouse3.setDisable(true);
        answer1.setDisable(true);
        answer2.setDisable(true);
        answer3.setDisable(true);

    }

    private void anSet() {
        chouse1.setDisable(false);
        chouse2.setDisable(false);
        chouse3.setDisable(false);
        answer1.setDisable(false);
        answer2.setDisable(false);
        answer3.setDisable(false);

    }

    private void onInformation(String title, String headerText, String ContextText) {
        alertInformation.setTitle(title);
        alertInformation.setHeaderText(headerText);
        alertInformation.setContentText(ContextText);
        alertInformation.show();
    }


}

