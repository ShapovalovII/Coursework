package Coursework.Controller;



import java.net.URL;
import java.util.ResourceBundle;

import Coursework.Model.Database;
import Coursework.Model.Lecture;
import Coursework.Model.Lectures;
import Coursework.Model.Task;
import Coursework.View.TaskReadWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TaskNew {

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
    private Button saveTaskButton;

    @FXML
    private Button backButton;

    @FXML
    private TextArea option3;

    @FXML
    private TextArea option1;

    @FXML
    private RadioButton choiceV;

    @FXML
    private TextArea option2;

    @FXML
    private RadioButton choiceA;

    @FXML
    private RadioButton choiceB;

    @FXML
    private Label qCounter;

    @FXML
    private TextField counter;

    private Alert alertError;

    private Alert alertInformation;

    @FXML
    void back(ActionEvent event) {
        try {
            TaskReadWindow taskReadWindow = new TaskReadWindow();
            taskReadWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();

    }


    @FXML
    void saveTask(ActionEvent event) {

        Task task;



        System.out.println("Selected radio: " +  responseGroup.getSelectedToggle().toggleGroupProperty().getName());
        try {
            int tempIdLecture = lectureList.getSelectionModel().getSelectedItem().getId();


            String tempQuestion = question.getText();

            String tempAnswer1 = option1.getText();
            String tempAnswer2 = option2.getText();
            String tempAnswer3 = option3.getText();

            boolean selectedChoice1 = choiceA.isSelected();
            boolean selectedChoice2 = choiceB.isSelected();
            boolean selectedChoice3 = choiceV.isSelected();

            if (selectedChoice1 || selectedChoice2 || selectedChoice3) {
                int tempChoice1 = (selectedChoice1) ? 1 : 0;
                int tempChoice2 = (selectedChoice2) ? 1 : 0;
                int tempChoice3 = (selectedChoice3) ? 1 : 0;

                if (question.getText().length() == 0 || option1.getText().length() == 0 || option2.getText().length() == 0 || option3.getText().length() == 0 ) {
                    onError("Data entry error", "Заполните пустые поля", "");
                } else if (question.getText().equals("0") != true && option1.getText().equals("0") != true && option2.getText().equals("0") != true && option3.getText().equals("0") != true) {

                   int count = this.iterateNumberQuestion();

                    task = new Task(tempIdLecture, tempQuestion, tempChoice1, tempChoice2, tempChoice3, tempAnswer1, tempAnswer2, tempAnswer3,count, 1, 1);
                    task.addToDatabaseTask();

                    onInformation("Task", "Задание создано.", "Задание №: " +count + " успешно сохранено.");


                }
            }
        }catch (Exception e){
            onInformation("Task", "Лекция не выбрана.", "");
        }


    }

    private int iterateNumberQuestion() {
        int tempCount = 1;
        try {
           tempCount = Integer.parseInt(qCounter.getText());
            tempCount++;
            System.out.println(tempCount);
            qCounter.setText(String.valueOf(tempCount));
        } catch (Exception e) {
            qCounter.setText("1");
            System.out.println("Oups...");
        }
        return tempCount;
    }

    @FXML
    void initialize() {


        Lectures lectures = new Lectures();
        lectureList.setItems(lectures.getAll());

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
