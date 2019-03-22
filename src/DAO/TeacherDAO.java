package DAO;

import com.sun.org.apache.regexp.internal.RE;
import javabean.*;
import javabean.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by ray on 2017/5/19.
 */
public class TeacherDAO extends BaseDAO {
    Connection conn = null;

    public int insertTeacher(String name, String sex, String nation, String birth, String id_number, String education, String professional_title, String image) {
        int result = -1;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT Teacher(name,sex,nation,birth,id_number,education,professional_title,image) values(?,?,?,?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, sex);
            ps.setString(3, nation);
            ps.setString(4, birth);
            ps.setString(5, id_number);
            ps.setString(6, education);
            ps.setString(7, professional_title);
            ps.setString(8, image);
            result = ps.executeUpdate();

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

    public Teacher getTeacherById(long id) {
        Teacher teacher = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * from Teacher WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                teacher = new Teacher();
                teacher.setId(rs.getLong("id"));
                teacher.setPassword(rs.getString("password"));
                teacher.setSex(rs.getString("sex"));
                teacher.setEducation(rs.getString("education"));
                teacher.setBirth(rs.getString("birth"));
                teacher.setId_number(rs.getString("id_number"));
                teacher.setProfessional_title(rs.getString("professional_title"));
                teacher.setName(rs.getString("name"));
                teacher.setNation(rs.getString("nation"));
                teacher.setImage(rs.getString("image"));
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
        return teacher;
    }

    public ArrayList<Teacher> getAllTeachers() {
        ArrayList<Teacher> teachers = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * from Teacher ORDER BY ?");
            ps.setString(1, "id");
            ResultSet rs = ps.executeQuery();
            if (rs != null)
                teachers = new ArrayList<>();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getLong("id"));
                teacher.setPassword(rs.getString("password"));
                teacher.setSex(rs.getString("sex"));
                teacher.setEducation(rs.getString("education"));
                teacher.setBirth(rs.getString("birth"));
                teacher.setId_number(rs.getString("id_number"));
                teacher.setProfessional_title(rs.getString("professional_title"));
                teacher.setName(rs.getString("name"));
                teacher.setNation(rs.getString("nation"));
                teacher.setImage(rs.getString("image"));

                teachers.add(teacher);
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
        return teachers;
    }

    //返回-1代表被限制不能删除-即没有这名老师的课程，若>=1则代表删除成功
    public int deleteById(long id) {
        int result = -1;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Teacher WHERE id=?");
            ps.setLong(1, id);
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {//删不了会出现异常
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

    public Teacher findById(long id) {
        Teacher teacher = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Teacher WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                teacher = new Teacher();
                teacher.setId(rs.getLong("id"));
                teacher.setPassword(rs.getString("password"));
                teacher.setSex(rs.getString("sex"));
                teacher.setEducation(rs.getString("education"));
                teacher.setBirth(rs.getString("birth"));
                teacher.setId_number(rs.getString("id_number"));
                teacher.setProfessional_title(rs.getString("professional_title"));
                teacher.setName(rs.getString("name"));
                teacher.setNation(rs.getString("nation"));
                teacher.setImage(rs.getString("image"));
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
        return teacher;
    }

    public int modifyTeacher(long id, String name, String sex, String nation, String professional_title, String birth, String id_number, String education, String password) {
        int result = -1;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Teacher set name=?,sex=?,nation=?,professional_title=?,birth=?,id_number=?,education=?,password=? WHERE id=?");
            ps.setString(1, name);
            ps.setString(2, sex);
            ps.setString(3, nation);
            ps.setString(4, professional_title);
            ps.setString(5, birth);
            ps.setString(6, id_number);
            ps.setString(7, education);
            ps.setString(8, password);
            ps.setLong(9, id);
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


    //教师自己登录后，系统将所有信息从数据库调出来，存进session中
    public Teacher login(long id, String password) {
        Teacher teacher = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Teacher WHERE id=? AND password=?");
            ps.setLong(1, id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                teacher = new Teacher();
                teacher.setId(rs.getLong("id"));
                teacher.setPassword(rs.getString("password"));
                teacher.setSex(rs.getString("sex"));
                teacher.setBirth(rs.getString("birth"));
                teacher.setId_number(rs.getString("id_number"));
                teacher.setEducation(rs.getString("education"));
                teacher.setProfessional_title(rs.getString("professional_title"));
                teacher.setName(rs.getString("name"));
                teacher.setNation(rs.getString("nation"));
                teacher.setImage(rs.getString("image"));
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
        return teacher;
    }

    //获取该名老师所有的任课课程
    public ArrayList<Class> getAllMyTeachClasses(long teacher_id) {
        ArrayList<Class> classes = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * from Class where teacher_id=?");
            ps.setLong(1, teacher_id);
            ResultSet rs = ps.executeQuery();
            if (rs != null)
                classes = new ArrayList<>();
            while (rs.next()) {
                Class lesson = new Class();
                lesson.setId(rs.getLong("id"));
                lesson.setName(rs.getString("name"));
                lesson.setClassroom_id(rs.getLong("classroom_id"));
                lesson.setTeacher_id(rs.getLong("teacher_id"));
                lesson.setDay_code(rs.getString("day_code"));
                lesson.setDay(rs.getString("day"));
                lesson.setTime_code(rs.getString("time_code"));
                lesson.setTime(rs.getString("time"));
                lesson.setInfo(rs.getString("info"));
                lesson.setNumber(rs.getInt("number"));

                ClassroomDAO classroomDAO = new ClassroomDAO();
                Classroom classroom = classroomDAO.getClassroomById(lesson.getClassroom_id());
                lesson.setBuilding(classroom.getBuilding());
                lesson.setRoom_number(classroom.getRoom_number());

                TeacherDAO teacherDAO = new TeacherDAO();
                Teacher teacher = teacherDAO.getTeacherById(lesson.getTeacher_id());
                lesson.setTeacher_name(teacher.getName());

                classes.add(lesson);
            }

            for (Class lesson : classes) {
                ps = conn.prepareStatement("SELECT count(*) question_num from (SELECT * from Class_Question where class_id=? and teacher_id=? and is_answered=0) ww");
                ps.setLong(1, lesson.getId());
                ps.setLong(2, lesson.getTeacher_id());
                rs = ps.executeQuery();
                if (rs.next()) {
                    lesson.setQuestion_number(rs.getInt("question_num"));
                }
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
        return classes;
    }

    //获取某节课程的所有评价
    public ArrayList<Comment> getAllCommentForOneClass(long class_id) {
        ArrayList<Comment> allCommentForOneClass = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT * FROM Student_Class WHERE class_id=? and teacher_score!=-1");
            ps.setLong(1, class_id);
            ResultSet rs = ps.executeQuery();
            StudentDAO studentDAO = new StudentDAO();
            if (rs != null)
                allCommentForOneClass = new ArrayList<>();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setClass_id(rs.getLong("class_id"));
                comment.setStudent_id(rs.getLong("student_id"));
                comment.setTeacher_score(rs.getInt("teacher_score"));
                comment.setComment(rs.getString("comment"));
                Student stu = studentDAO.findById(rs.getLong("student_id"));
                comment.setStudent_name(stu.getName());
                allCommentForOneClass.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return allCommentForOneClass;
    }

}
