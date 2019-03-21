package Coursework.Model;


public class Teacher {
    private int id;
    private String loginTeacher;
    private String passwordTeacher;
    private String nameTeacher;
    private String surnameTeacher;
    private String lastnameTeacher;
    private int choice1;


    public Teacher() {
    }

    public Teacher(String loginTeacher, String passwordTeacher, String nameTeacher, String surnameTeacher, String lastnameTeacher, int choice1) {
        this.loginTeacher = loginTeacher;
        this.passwordTeacher = passwordTeacher;
        this.nameTeacher = nameTeacher;
        this.surnameTeacher = surnameTeacher;
        this.lastnameTeacher = lastnameTeacher;
        this.choice1 = choice1;
    }

    public Teacher(int id, String loginTeacher, String passwordTeacher, String nameTeacher, String surnameTeacher, String lastnameTeacher, int choice1) {
        this.id = id;
        this.loginTeacher = loginTeacher;
        this.passwordTeacher = passwordTeacher;
        this.nameTeacher = nameTeacher;
        this.surnameTeacher = surnameTeacher;
        this.lastnameTeacher = lastnameTeacher;
        this.choice1 = choice1;
    }


    public void addToDatabaseTeacher() {
        String sql = String.format("INSERT INTO teacher(loginTeacher, passwordTeacher, nameTeacher, surnameTeacher, lastnameTeacher, choice1)" +
                        " VALUES('%s','%s','%s','%s','%s','%d')",       //Использовать метод format из класса String, для сохранения Teacher
                this.loginTeacher, this.passwordTeacher, this.nameTeacher, this.surnameTeacher, this.lastnameTeacher, this.choice1);
        System.out.println(sql);
        Database.executeSQL(sql);      //Вызов метода "executeSQL"  для добавления преподавателей в Базу Данных
    }

    private void updateTeacherInDatabase() {
        String sql = String.format("UPDATE teacher SET loginTeacher = '%s', password = '%s', nameTeacher = '%s', surnameTeacher = '%s', lastnameTeacher = '%s', choice1 = '%d' where id = %d",
                this.loginTeacher, this.passwordTeacher, this.nameTeacher, this.surnameTeacher, this.lastnameTeacher, this.choice1, id);
        System.out.println(sql);
        Database.executeSQL(sql);
    }

    private void deleteFromDatabase() {
        String sql = String.format("DELETE FROM teacher WHERE id = %d", id);
        System.out.println(sql);
        Database.executeSQL(sql);
    }


    public void deleteTeacher() {
        deleteFromDatabase();
    }

    public String getLoginTeacher() {
        return this.loginTeacher;
    }

    public String getPasswordTeacher() {
        return this.passwordTeacher;
    }


}

