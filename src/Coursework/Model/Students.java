package Coursework.Model;

import java.util.ArrayList;


public class Students {

    ArrayList<Student> students;

    private static Student studentInSystem;

    public Students() {
        students = Database.loudStudents();
    }

    public boolean checkCredentialsStudent(String login, String password) {
        for (Student student : students) {
            if (student.getLoginStudent().equals(login) && student.getPasswordTeacher().equals(password)) {
                studentInSystem = student;
                return true;
            }
        }
        return false;
    }

    public static Student getStudentInSystem() {
        return studentInSystem;
    }
}
