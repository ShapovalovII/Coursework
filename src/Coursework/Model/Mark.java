package Coursework.Model;

public class Mark {
    private int id;
    private int idLecture;
    private String surnameStudent;
    private int quantityMarksInQuestion;
    private int quantityMarksStudent;
    private int passQuestionStudent;
    private String lName;


    public Mark() {

    }

    public Mark(int idLecture, String surnameStudent, int quantityMarksInQuestion, int quantityMarksStudent, int passQuestionStudent) {
        this.idLecture = idLecture;
        this.surnameStudent = surnameStudent;
        this.quantityMarksInQuestion = quantityMarksInQuestion;
        this.quantityMarksStudent = quantityMarksStudent;
        this.passQuestionStudent = passQuestionStudent;
    }

    public Mark(int idLecture, int quantityMarksStudent) {
        this.idLecture = idLecture;
        this.quantityMarksStudent = quantityMarksStudent;
    }

    public Mark(int id, int idLecture, String surnameStudent, int quantityMarksInQuestion, int quantityMarksStudent, int passQuestionStudent, Lectures lectures) {
        this.id = id;
        this.idLecture = idLecture;
        this.surnameStudent = surnameStudent;
        this.quantityMarksInQuestion = quantityMarksInQuestion;
        this.quantityMarksStudent = quantityMarksStudent;
        this.passQuestionStudent = passQuestionStudent;
        this.lName = this.getLectureName(lectures);
    }

    public void addToDatabaseMark() {
        String sql = String.format("INSERT INTO mark(idLecture, surnameStudent, quantityMarksInQuestion, quantityMarksStudent, passQuestionStudent)" +
                        " VALUES('%d', '%s', '%d', '%d', '%d')",       //Использовать метод format из класса String, для сохранения Mark
                this.idLecture, this.surnameStudent, this.quantityMarksInQuestion, this.quantityMarksStudent, this.passQuestionStudent);
        System.out.println(sql);
        Database.executeSQL(sql);      //Вызов метода "executeSQL"  для добавления студентов в Базу Данных
    }




    public String getSurnameStudent() {
        return surnameStudent;
    }


    public int getPassQuestion() {
        return passQuestionStudent;
    }

    public int getIdLecture() {
        return idLecture;
    }

    public int getQuantityMarksStudent() {
        return quantityMarksStudent;
    }

    public void setIdLecture(int idLecture) {
        this.idLecture = idLecture;
    }

    public void setQuantityMarksStudent(int quantityMarksStudent) {
        this.quantityMarksStudent = quantityMarksStudent;
    }

    private String getLectureName(Lectures lectures){
        Lecture lecture = lectures.getById(this.idLecture);

       return lecture.getName();
    }

    public String getLName() {
        return lName;
    }
}
