package Coursework.Model;

public class Task {
    private int id;
    private int idLecture;
    private String question;
    private int choice1;
    private int choice2;
    private int choice3;
    private String answer1;
    private String answer2;
    private String answer3;
    private int questionCounter;
    private int markQuestion;
    private int passQuestion;


    public Task() {

    }

    public Task(int id, int idLecture, String question, int choice1, int choice2, int choice3, String answer1, String answer2, String answer3, int questionCounter, int markQuestion, int passQuestion) {
        this.id = id;
        this.idLecture = idLecture;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.questionCounter = questionCounter;
        this.markQuestion = markQuestion;
        this.passQuestion = passQuestion;
    }
    public Task(int idLecture, String question, int choice1, int choice2, int choice3, String answer1, String answer2, String answer3, int questionCounter, int markQuestion, int passQuestion) {
        this.idLecture = idLecture;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.questionCounter = questionCounter;
        this.markQuestion = markQuestion;
        this.passQuestion = passQuestion;
    }




    public void addToDatabaseTask() {
        String sql = String.format("INSERT INTO task(idLecture, question, choice1, choice2, choice3, answer1, answer2, answer3, questionCounter, markQuestion , passQuestion)" +
                        " VALUES('%d', '%s', '%d', '%d', '%d', '%s', '%s', '%s', '%d', '%d', '%d')",       //Использовать метод format из класса String, для сохранения Task
                this.idLecture, this.question, this.choice1, this.choice2, this.choice3, this.answer1, this.answer2, this.answer3, this.questionCounter, this.markQuestion, this.passQuestion);
        System.out.println(sql);
        Database.executeSQL(sql);      //Вызов метода "executeSQL"  для добавления студентов в Базу Данных
    }


    public int getIdLecture() {
        return idLecture;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public int getChoice1() {
        return choice1;
    }

    public int getChoice2() {
        return choice2;
    }

    public int getChoice3() {
        return choice3;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public int getQuestionCounter() {
        return questionCounter;
    }

    public int getMarkQuestion() {
        return markQuestion;
    }

    public int getPassQuestion(){
        return passQuestion;
    }
}
