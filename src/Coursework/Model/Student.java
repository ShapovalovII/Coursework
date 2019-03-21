package Coursework.Model;

public class Student {
    private int id;
    private String loginStudent;
    private String passwordStudent;
    private String nameStudent;
    private String surnameStudent;
    private String lastnameStudent;
    private String group;
    private int choice2;


    public Student() {
    }

    public Student(String loginStudent, String passwordStudent, String nameStudent, String surnameStudent, String lastnameStudent, String group, int choice2) {
        this.loginStudent = loginStudent;
        this.passwordStudent = passwordStudent;
        this.nameStudent = nameStudent;
        this.surnameStudent = surnameStudent;
        this.lastnameStudent = lastnameStudent;
        this.group = group;
        this.choice2 = choice2;
    }

    public Student(int id, String loginStudent, String passwordStudent, String nameStudent, String surnameStudent, String lastnameStudent, String group, int choice2) {
        this.id = id;
        this.loginStudent = loginStudent;
        this.passwordStudent = passwordStudent;
        this.nameStudent = nameStudent;
        this.surnameStudent = surnameStudent;
        this.lastnameStudent = lastnameStudent;
        this.group = group;
        this.choice2 = choice2;
    }


    public void addToDatabaseStudent() {
        String sql = String.format("INSERT INTO student(loginStudent, passwordStudent, nameStudent, surnameStudent, lastnameStudent, party, choice2)" +
                        " VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%d')",       //Использовать метод format из класса String, для сохранения Student
                this.loginStudent, this.passwordStudent, this.nameStudent, this.surnameStudent, this.lastnameStudent, this.group, this.choice2);
        System.out.println(sql);
        Database.executeSQL(sql);      //Вызов метода "executeSQL"  для добавления студентов в Базу Данных
    }


    public String getLoginStudent() {
        return this.loginStudent;
    }

    public String getPasswordTeacher() {
        return this.passwordStudent;
    }


    public int getId() {
        return id;
    }

    public String getPasswordStudent() {
        return passwordStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public String getSurnameStudent() {
        return surnameStudent;
    }

    public String getLastnameStudent() {
        return lastnameStudent;
    }

    public String getGroup() {
        return group;
    }

    public int getChoice2() {
        return choice2;
    }
}


