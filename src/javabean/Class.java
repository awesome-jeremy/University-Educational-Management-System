package javabean;

/**
 * Created by ray on 2017/5/19.
 */
public class Class {


    public static String[][] times = {
            {"1", "2"},
            {"3", "4"},
            {"3", "4", "5"},
            {"6", "7"},
            {"8", "9"},
            {"10", "11", "12"}
    };
    private long id;
    private String name;
    private long classroom_id;
    private long teacher_id;
    private String day;//周几的课,例如周一
    private String day_code;//星期几代号,1-5
    private String time;//一天中什么时候的课,例如1、2节课

    private String building;
    private String room_number;
    private String teacher_name;

    private int question_number;
    private int haveAnswer_number;

    private int score;//学生成绩
    private int teacher_score;//学生给老师成绩

    public Class() {
    }

    public Class(String name, long classroom_id, long teacher_id, String day, String day_code, String time, String building, String room_number, String time_code, String info) {

        this.name = name;
        this.classroom_id = classroom_id;
        this.teacher_id = teacher_id;
        this.day = day;
        this.day_code = day_code;
        this.time = time;
        this.building = building;
        this.room_number = room_number;
        this.time_code = time_code;
        this.info = info;

    }

    public int getTeacher_score() {
        return teacher_score;
    }

    public void setTeacher_score(int teacher_score) {
        this.teacher_score = teacher_score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(int question_number) {
        this.question_number = question_number;
    }

    public int getHaveAnswer_number() {
        return haveAnswer_number;
    }

    public void setHaveAnswer_number(int haveAnswer_number) {
        this.haveAnswer_number = haveAnswer_number;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }


    public String getTime() {
        return time;
    }

    public String getDay_code() {
        return day_code;
    }

    public void setDay_code(String day_code) {
        this.day_code = day_code;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime_code() {
        return time_code;
    }

    public void setTime_code(String time_code) {
        this.time_code = time_code;
    }

    private String time_code;//上课时间代号,0--1、2节课 1--3、4节课 2--3、4、5节课 3--6、7节课 4--8、9节课 5--10、11、12节课
    private String info;
    private int number;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(long classroom_id) {
        this.classroom_id = classroom_id;
    }

    public long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(long teacher_id) {
        this.teacher_id = teacher_id;
    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
