package javabean;

/**
 * Created by ray on 2017/5/25.
 */
public class Question {
    //数据库级别属性

    private  long id;
    private long class_id;
    private long teacher_id;
    private long student_id;
    private String question;
    private String question_date;
    private String question_time;
    private String answer;
    private String answer_date;
    private String answer_time;

    public short is_answered;


    //额外属性
    private String class_name;
    private String teacher_name;
    private String student_name;

    private String teacher_image;

    public Question() {
    }

    public Question(long class_id, long teacher_id, long student_id, String class_name, String teacher_name, String student_name) {
        this.class_id = class_id;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
        this.class_name = class_name;
        this.teacher_name = teacher_name;
        this.student_name = student_name;
    }

    public Question(long id, String answer, String answer_date, String answer_time) {
        this.id = id;
        this.answer = answer;
        this.answer_date = answer_date;
        this.answer_time = answer_time;
    }

    public String getTeacher_image() {
        return teacher_image;
    }

    public void setTeacher_image(String teacher_image) {
        this.teacher_image = teacher_image;
    }

    public short getIs_answered() {
        return is_answered;
    }

    public void setIs_answered(short is_answered) {
        this.is_answered = is_answered;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question(long class_id, long teacher_id, long student_id, String question, String question_date, String question_time, String answer, String answer_date, String answer_time) {
        this.class_id = class_id;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
        this.question = question;
        this.question_date = question_date;
        this.question_time = question_time;
        this.answer = answer;
        this.answer_date = answer_date;
        this.answer_time = answer_time;
    }

    public long getClass_id() {
        return class_id;
    }

    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }

    public long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion_date() {
        return question_date;
    }

    public void setQuestion_date(String question_date) {
        this.question_date = question_date;
    }

    public String getQuestion_time() {
        return question_time;
    }

    public void setQuestion_time(String question_time) {
        this.question_time = question_time;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer_date() {
        return answer_date;
    }

    public void setAnswer_date(String answer_date) {
        this.answer_date = answer_date;
    }

    public String getAnswer_time() {
        return answer_time;
    }

    public void setAnswer_time(String answer_time) {
        this.answer_time = answer_time;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
}
