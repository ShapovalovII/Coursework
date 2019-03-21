package Coursework.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Marks {


    ArrayList<Mark> marks;

    public Marks() {
        marks = Database.loudMarks();
    }


    public boolean checkMarksStudent(String surname) {
        for (Mark mark : marks) {
            if (mark.getSurnameStudent().equals(surname)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPassStudent(int pass) {
        for (Mark mark : marks) {
            if (mark.getPassQuestion() == pass) {
                return true;
            }
        }
        return false;
    }

    public ObservableList<Mark> getAll(String surname) {
        ArrayList<Mark> tempMarks = new ArrayList<>();
        for (Mark mark : marks) {
            if (mark.getSurnameStudent().equals(surname)) {
                tempMarks.add(mark);
            }
        }
        return FXCollections.observableArrayList(tempMarks);
    }


}
