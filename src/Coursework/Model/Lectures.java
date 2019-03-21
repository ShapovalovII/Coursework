package Coursework.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Lectures {

    ArrayList<Lecture> lectures;

    public Lectures() {
        lectures = Database.loudLectures();
    }

    public ObservableList<Lecture> getAll() {
        return FXCollections.observableArrayList(lectures);
    }

    public Lecture getById(int idLecture) {
        for (Lecture lecture: lectures){
            if(lecture.getId() == idLecture){
                return lecture;
            }
        }
        return null;
    }
}
