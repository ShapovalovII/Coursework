package Coursework.Model;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static final String NAME = "database.db";
    private static final String URL = "jdbc:sqlite:" + NAME;


    private static Statement statement = null;
    private static Connection connection = null;
    private static ResultSet result = null;

    public static Statement getStatement() {     //Возвращаем statement(для использования в следующих действиях)'обнуляем'
        return statement;
    }

    public static ResultSet getResultSet() {     //Возвращаем result(для использования в следующих действиях)'обнуляем'
        return result;
    }



    public static void getDatabase() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL);
                statement = connection.createStatement();           //Метод createStatement, через connection создаёт statement\
                System.out.println("Connection with database created.");
                createDatabase();                                //Создание таблиц будет происходит параллельно запуску базы данных
            } catch (SQLException e) {
                System.out.println("Cant connect to database. \nError: " + e.getMessage());
            }
        }
    }

    public static void createDatabase() {
        String createStudentSQL = "CREATE TABLE IF NOT EXISTS student (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	loginStudent text NOT NULL,\n"
                + "	passwordStudent text NOT NULL,\n"
                + "	nameStudent text NOT NULL,\n"
                + "	surnameStudent text NOT NULL,\n"
                + "	lastnameStudent text NOT NULL,\n"
                + "	party text NOT NULL,\n"                                 //Слово "group" зарезервировано поэтому использованно "party"
                + " choice2 int NOT NULL\n"
                + ");";

        String createTeacherSQL = "CREATE TABLE IF NOT EXISTS teacher (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	loginTeacher text NOT NULL,\n"
                + "	passwordTeacher text NOT NULL,\n"
                + "	nameTeacher text NOT NULL,\n"
                + "	surnameTeacher text NOT NULL,\n"
                + "	lastnameTeacher text NOT NULL,\n"
                + " choice1 int NOT NULL\n"
                + ");";


        String createLectureSQL = "CREATE TABLE IF NOT EXISTS lecture (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " nameLecture text NOT NULL,\n"
                + " lecturefilePath text NOT NULL\n"
                + ");";

        String createTaskSQL = "CREATE TABLE IF NOT EXISTS task (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " idLecture int NOT NULL,\n"
                + " question text NOT NULL,\n"
                + " choice1 int NOT NULL,\n"
                + " choice2 int NOT NULL,\n"
                + " choice3 int NOT NULL,\n"
                + " answer1 text NOT NULL,\n"
                + " answer2 text NOT NULL,\n"
                + " answer3 text NOT NULL,\n"
                + " questionCounter int NOT NULL,\n"
                + " markQuestion int NOT NULL,\n"
                + " passQuestion int NOT NULL\n"
                + ");";

        String createMarkSQL = "CREATE TABLE IF NOT EXISTS mark (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " idLecture int NOT NULL,\n"
                + " surnameStudent text NOT NULL,\n"
                + " quantityMarksInQuestion int NOT NULL,\n"
                + " quantityMarksStudent int NOT NULL,\n"
                + " passQuestionStudent int NOT NULL\n"
                + ");";

        try {
            statement.execute(createStudentSQL);     //Метод execute используется, когда операторы SQL возвращают более одного набора данных,
            statement.execute(createTeacherSQL);     // более одного счетчика обновлений или и то, и другое.
            statement.execute(createLectureSQL);
            statement.execute(createTaskSQL);
            statement.execute(createMarkSQL);

        } catch (SQLException e) {
            System.out.println("Cant create database.\nError: " + e.getMessage());
        }
    }

    public static void executeSQL(String sql) {   //Создание строки sql(sql-скрипт) для многоразового использования.Для передачи данных в Базу
        getDatabase();
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Cant execute sql script.\nScript: " + sql + "\nError: " + e.getMessage());
        }
    }

    public static ArrayList<Student> loudStudents() {
        String sql = "SELECT * FROM student;";                //Загрузить ВСЁ касаемо студента

        getDatabase();
        ArrayList<Student> students = new ArrayList<>();

        try {
            result = statement.executeQuery(sql);           //Метод executeQuery через statement создаёт result
            /* Метод executeQuery необходим для запросов, результатом которых является один единственный набор значений, таких как запросов SELECT. */

            while (result.next()) {
                int id = result.getInt("id");                        // Через result проходим по всем элементам
                String loginStudent = result.getString("loginStudent");
                String passwordStudent = result.getString("passwordStudent");
                String nameStudent = result.getString("nameStudent");
                String surnameStudent = result.getString("surnameStudent");
                String lastnameStudent = result.getString("lastnameStudent");
                String group = result.getString("party");
                int choice2 = result.getInt("choice2");


                Student student = new Student(id, loginStudent, passwordStudent, nameStudent, surnameStudent, lastnameStudent, group, choice2);

                students.add(student);                //В список клиентов ДОБАВИТЬ Студента
            }
        } catch (Exception e) {
            System.out.println("Cant loud data from database.\nError: " + e.getMessage());
        }

        return students;
    }

    public static ArrayList<Teacher> loudTeacher() {
        String sql = "SELECT * FROM teacher;";              //Загрузить ВСЁ касаемо преподавателя

        getDatabase();
        ArrayList<Teacher> teachers = new ArrayList<>();

        try {
            result = statement.executeQuery(sql);       //Метод executeQuery через statement создаёт result
            /* Метод executeQuery необходим для запросов, результатом которых является один единственный набор значений, таких как запросов SELECT. */

            while (result.next()) {
                int id = result.getInt("id");                   // Через result проходим по всем элементам
                String loginTeacher = result.getString("loginTeacher");
                String passwordTeacher = result.getString("passwordTeacher");
                String nameTeacher = result.getString("nameTeacher");
                String surnameTeacher = result.getString("surnameTeacher");
                String lastnameTeacher = result.getString("lastnameTeacher");
                int choice1 = result.getInt("choice1");


                Teacher teacher = new Teacher(id, loginTeacher, passwordTeacher, nameTeacher, surnameTeacher, lastnameTeacher, choice1);

                teachers.add(teacher);                           //В список тарифов ДОБАВИТЬ преподавателя
            }
        } catch (Exception e) {
            System.out.println("Cant loud data from database.\nError: " + e.getMessage());
        }

        return teachers;
    }

    public static ArrayList<Lecture> loudLectures() {
        String sql = "SELECT * FROM lecture;";

        getDatabase();
        ArrayList<Lecture> lectures = new ArrayList<>();

        try {
            result = statement.executeQuery(sql);           //Метод executeQuery через statement создаёт result
            /* Метод executeQuery необходим для запросов, результатом которых является один единственный набор значений, таких как запросов SELECT. */

            while (result.next()) {
                int id = result.getInt("id");                        // Через result проходим по всем элементам
                String nameLecture = result.getString("nameLecture");
                String lecturefilePath = result.getString("lecturefilePath");


                Lecture lecture = new Lecture(id, nameLecture, lecturefilePath);
                lectures.add(lecture);
            }
        } catch (Exception e) {
            System.out.println("Cant loud data from database.\nError: " + e.getMessage());
        }

        return lectures;
    }

    public static ArrayList<Task> loudTasks() {
        String sql = "SELECT * FROM task;";

        getDatabase();
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            result = statement.executeQuery(sql);           //Метод executeQuery через statement создаёт result
            /* Метод executeQuery необходим для запросов, результатом которых является один единственный набор значений, таких как запросов SELECT. */

            while (result.next()) {
                int id = result.getInt("id");                        // Через result проходим по всем элементам
                int idLecture = result.getInt("idLecture");
                String question = result.getString("question");
                int choice1 = result.getInt("choice1");
                int choice2 = result.getInt("choice2");
                int choice3 = result.getInt("choice3");
                String answer1 = result.getString("answer1");
                String answer2 = result.getString("answer2");
                String answer3 = result.getString("answer3");
                int questionCounter = result.getInt("questionCounter");
                int markQuestion = result.getInt("markQuestion");
                int passQuestion = result.getInt("passQuestion");


                Task task = new Task(id, idLecture, question, choice1, choice2, choice3, answer1, answer2, answer3, questionCounter, markQuestion, passQuestion);
                tasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("Cant loud data from database.\nError: " + e.getMessage());
        }

        return tasks;
    }


    public static ArrayList<Mark> loudMarks() {
        String sql = "SELECT * FROM mark;";

        getDatabase();
        Lectures lectures = new Lectures();


        ArrayList<Mark> marks = new ArrayList<>();

        try {
            result = statement.executeQuery(sql);           //Метод executeQuery через statement создаёт result
            /* Метод executeQuery необходим для запросов, результатом которых является один единственный набор значений, таких как запросов SELECT. */

            while (result.next()) {
                int id = result.getInt("id");                        // Через result проходим по всем элементам
                int idLecture = result.getInt("idLecture");
                String surnameStudent = result.getString("surnameStudent");
                int quantityMarksInQuestion = result.getInt("quantityMarksInQuestion");
                int quantityMarksStudent = result.getInt("quantityMarksStudent");
                int passQuestionStudent = result.getInt("passQuestionStudent");

                Mark mark = new Mark(id, idLecture, surnameStudent, quantityMarksInQuestion, quantityMarksStudent, passQuestionStudent, lectures);

                marks.add(mark);
            }

        } catch (Exception e) {
            System.out.println("Cant loud data from database.\nError: " + e.getMessage());
        }

        return marks;
    }
}
