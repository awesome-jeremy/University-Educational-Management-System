package DAO;

import javabean.*;
import javabean.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ray on 2017/5/25.
 */
public class QuestionDAO extends BaseDAO {
    Connection conn = null;

    public int insertQuestion(Question question) {


        int result = -1;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT Class_Question(class_id,teacher_id,student_id,question,question_date,question_time) values(?,?,?,?,?,?)");
            ps.setLong(1, question.getClass_id());
            ps.setLong(2, question.getTeacher_id());
            ps.setLong(3, question.getStudent_id());
            ps.setString(4, question.getQuestion());
            ps.setString(5, question.getQuestion_date());
            ps.setString(6, question.getQuestion_time());

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    //返回某门课所有还没回答的问题
    public ArrayList<Question> getOneClassUnansweredQuestions(long class_id){

        ArrayList<Question> oneClassUnansweredQuestions = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * from Class_Question where class_id=? and is_answered=0");
            ps.setLong(1, class_id);
            ResultSet rs = ps.executeQuery();
            if (rs != null)
                oneClassUnansweredQuestions = new ArrayList<>();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getLong("id"));
                question.setClass_id(rs.getLong("class_id"));
                question.setTeacher_id(rs.getLong("teacher_id"));
                question.setStudent_id(rs.getLong("student_id"));
                question.setQuestion(rs.getString("question"));
                question.setQuestion_date(rs.getString("question_date"));
                question.setQuestion_time(rs.getString("question_time"));


                ClassDAO classDAO = new ClassDAO();
                Class lesson = classDAO.getClassById(question.getClass_id());
                question.setClass_name(lesson.getName());

                TeacherDAO teacherDAO = new TeacherDAO();
                Teacher teacher = teacherDAO.getTeacherById(question.getTeacher_id());
                question.setTeacher_name(teacher.getName());

                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.findById(question.getStudent_id());
                question.setStudent_name(student.getName());

                oneClassUnansweredQuestions.add(question);
            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return oneClassUnansweredQuestions;
    }

    //返回这名学生的这门课下的所有和这个老师的问题。
    public ArrayList<Question> getOneClass_StudentQuestions(long class_id, long student_id)  {

        ArrayList<Question> OneClass_StudentQuestions = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * from Class_Question where class_id=? and student_id=? ORDER BY question_date desc,question_time desc");
            ps.setLong(1, class_id);
            ps.setLong(2, student_id);
            ResultSet rs = ps.executeQuery();
            if (rs != null)
                OneClass_StudentQuestions = new ArrayList<>();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getLong("id"));
                question.setClass_id(rs.getLong("class_id"));
                question.setTeacher_id(rs.getLong("teacher_id"));
                question.setStudent_id(rs.getLong("student_id"));
                question.setQuestion(rs.getString("question"));
                question.setQuestion_date(rs.getString("question_date"));
                question.setQuestion_time(rs.getString("question_time"));
                question.setAnswer(rs.getString("answer"));
                question.setAnswer_date(rs.getString("answer_date"));
                question.setAnswer_time(rs.getString("answer_time"));
                question.setIs_answered(rs.getShort("is_answered"));

                ClassDAO classDAO = new ClassDAO();
                Class lesson = classDAO.getClassById(question.getClass_id());
                question.setClass_name(lesson.getName());

                TeacherDAO teacherDAO = new TeacherDAO();
                Teacher teacher = teacherDAO.getTeacherById(question.getTeacher_id());
                question.setTeacher_name(teacher.getName());
                question.setTeacher_image(teacher.getImage());

                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.findById(question.getStudent_id());
                question.setStudent_name(student.getName());

                OneClass_StudentQuestions.add(question);
            }
            ps.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return OneClass_StudentQuestions;
    }


    public int insertAnswerToQuestion(Question question)  {
        int result = -1;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Class_Question set answer=?,answer_date=?,answer_time=?,is_answered=? where id=?");
            ps.setString(1, question.getAnswer());
            ps.setString(2, question.getAnswer_date());
            ps.setString(3, question.getAnswer_time());
            ps.setShort(4, (short) 1);
            ps.setLong(5, question.getId());
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //获取学生已经被回答但学生还没看的问题数量
    public int getAnsweredNotReadQuestionNum(long student_id){
        int result=-1;
        PreparedStatement ps=null;
        try {
            conn=getConnection();
            ps = conn.prepareStatement("SELECT count(*) question_num from (SELECT * from Class_Question where student_id=? and is_answered=1) ww");
            ps.setLong(1, student_id);
           ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result=rs.getInt("question_num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    //获取某节课某位学生已经被回答还未查看的数量
    public int getOneClassAnsweredNotReadQuestionNum(long student_id,long class_id){
        int result=-1;
        PreparedStatement ps=null;
        try {
            conn=getConnection();
            ps = conn.prepareStatement("SELECT count(*) question_num from (SELECT * from Class_Question where student_id=? and class_id=? and is_answered=1) ww");
            ps.setLong(1, student_id);
            ps.setLong(2,class_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result=rs.getInt("question_num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    //查看了老师的回复就设置成已查看，把1置为2
    public int setHaveReadAnswers(long class_id,long student_id){
        int result=-1;
        PreparedStatement ps=null;
        try {
            conn=getConnection();
            ps = conn.prepareStatement("UPDATE Class_Question SET is_answered=2 WHERE class_id=? and student_id=? and is_answered=1");
            ps.setLong(1, class_id);
            ps.setLong(2,student_id);
            result=ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return  result;
    }

    //获取老师所有还没回答问题数量
    public int getUnansweredQuestionNum(long teacher_id){
        int result=-1;
        PreparedStatement ps=null;
        try {
            conn=getConnection();
            ps = conn.prepareStatement("SELECT count(*) question_num from (SELECT * from Class_Question where teacher_id=? and is_answered=0) ww");
            ps.setLong(1, teacher_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result=rs.getInt("question_num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }
}
