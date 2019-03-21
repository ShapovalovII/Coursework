package Coursework.Model;

import java.io.*;
import java.util.Scanner;

public class Lecture {
    private int id;
    private String nameLecture;
    private String lecturefilePath ;
    private String content;

    public Lecture() {

    }

    public Lecture(String nameLecture, String content) {
        this.nameLecture = nameLecture;
        this.lecturefilePath = "lecture\\" + nameLecture;       //Прописан путь вручную
        this.content = content;
    }

    public Lecture(int id, String nameLecture, String lecturefilePath) {
        this.id = id;
        this.nameLecture = nameLecture;
        this.lecturefilePath = lecturefilePath;
        readLecture();
    }


    public void addToDatabaseLecture() {
        String sql = String.format("INSERT INTO lecture(nameLecture, lecturefilePath)" + " VALUES('%s', '%s')", this.nameLecture, this.lecturefilePath);
        System.out.println(sql);
        Database.executeSQL(sql);      //Вызов метода "executeSQL"  для добавления лекций в Базу Данных
    }

    private void deleteFromDatabase() {
        String sql = String.format("DELETE FROM lecture WHERE id = %d", this.id);
        System.out.println(sql);
        Database.executeSQL(sql);
    }

    public void deleteLecture() {
        File file = new File(lecturefilePath);
        System.out.println(lecturefilePath);
        file.delete();
        deleteFromDatabase();
    }


    public void createNewLectureFile() {
        new File("lecture").mkdir();

        File file = new File(lecturefilePath);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());          //   "getAbsoluteFile"   Возвращает строкой абсолютный путь

            try {
                out.print(content);

                addToDatabaseLecture();
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readLecture() {
        File file = new File(lecturefilePath);
        StringBuilder fileContents = new StringBuilder((int) file.length());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + System.lineSeparator());
            }
            content = fileContents.toString();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }




    @Override
    public String toString() {
        return this.nameLecture;
    }    //Возвращает строковый путь этого абстрактного пути


    public String getContent() {
        return content;
    }

    public int getId(){ return this.id; }

    public String getName() {
        return nameLecture;
    }
}
