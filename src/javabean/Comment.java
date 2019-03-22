package javabean;

/**
 * Created by ray on 2017/5/27.
 */
public class Comment {
    private long class_id;
    private long student_id;
    private String student_name;
    private int teacher_score;
    private String comment;

    public Comment() {
    }

    public long getClass_id() {
        return class_id;
    }

    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public int getTeacher_score() {
        return teacher_score;
    }

    public void setTeacher_score(int teacher_score) {
        this.teacher_score = teacher_score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
