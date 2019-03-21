package Coursework.Model;

import java.util.ArrayList;

public class Teachers {


    ArrayList<Teacher> teachers;

    public Teachers() {
        teachers = Database.loudTeacher();
    }

    public boolean checkCredentialsTeacher(String login, String password) {

        for (Teacher teacher : teachers) {
            if (teacher.getLoginTeacher().equals(login) && teacher.getPasswordTeacher().equals(password)) {
                return true;
            }
        }
        return false;
    }
}


