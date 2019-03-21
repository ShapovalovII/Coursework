package Coursework.Controller;

        import java.net.URL;
        import java.util.ResourceBundle;

        import Coursework.Model.Database;
        import Coursework.Model.Lecture;
        import Coursework.View.LectureReadWindow;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;

public class LectureNew {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button saveLectureButton;

    @FXML
    private TextField newNameLecture;

    @FXML
    private Button backButton;

    @FXML
    private TextArea newTextLecture;

    private Alert alertInformation;
    private Alert alertError;

    @FXML
    void back(ActionEvent event) {
        try {
            LectureReadWindow lectureReadWindow = new LectureReadWindow();
            lectureReadWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void saveLecture(ActionEvent event) throws  Exception {
        try {
            String lectureName = newNameLecture.getText();
            String contentLecture = newTextLecture.getText();

            Lecture lecture = new Lecture(lectureName, contentLecture);
            lecture.createNewLectureFile();

            Stage stage = (Stage) saveLectureButton.getScene().getWindow();
            stage.close();
            LectureReadWindow lectureReadWindow = new LectureReadWindow();
            lectureReadWindow.start(new Stage());
            onInformation("Lecture", "Новая лекция сохранена.", "Тема лекции '" + newNameLecture.getText() + "'");
        }catch (Exception e){
            onError("Wrong data", "Введите недостоющие данные", "");
        }

    }

    @FXML
    void initialize() {
        alertError = new Alert(Alert.AlertType.ERROR);
        alertInformation = new Alert(Alert.AlertType.INFORMATION);
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
