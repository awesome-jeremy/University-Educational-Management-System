package DAO;

import javabean.Class;
import javabean.Classroom;
import javabean.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by ray on 2017/5/20.
 */
public class ClassDAO extends BaseDAO {
    public String[][] times = {
            {"1", "2"},
            {"3", "4"},
            {"3", "4", "5"},
            {"6", "7"},
            {"8", "9"},
            {"10", "11", "12"}
    };
    Connection conn = null;

    public int insertClass(Class lesson) throws SQLException {

        if (location_time_conflict(lesson.getClassroom_id(), lesson.getDay_code(), lesson.getTime_code())) {
            return -1;
        }
        int result=-1;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT Class(name,classroom_id,teacher_id,day_code,day,time_code,time,info) values(?,?,?,?,?,?,?,?)");
            ps.setString(1, lesson.getName());
            ps.setLong(2, lesson.getClassroom_id());
            ps.setLong(3, lesson.getTeacher_id());
            ps.setString(4, lesson.getDay_code());
            ps.setString(5, lesson.getDay());
            ps.setString(6, lesson.getTime_code());
            ps.setString(7, lesson.getTime());
            ps.setString(8, lesson.getInfo());

            setClassroom_Classes(lesson.getClassroom_id(), lesson.getDay_code(), lesson.getTime_code());

            result=ps.executeUpdate();

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

    //设置教室的上课时间，例如课程是星期一的3、4节 则该对应的教室的class13和class14应设置为1
    public void setClassroom_Classes(long classroom_id, String day_code, String time_code) {
        ArrayList<String> time = getTimes(day_code, time_code);
        try {
            conn = getConnection();
            PreparedStatement ps = null;
            if (time.size() == 2) {
                ps = conn.prepareStatement("UPDATE Classroom SET " + time.get(0) + "=?," + time.get(1) + "=? WHERE id=?");

                ps.setShort(1, (short) 1);
                ps.setShort(2, (short) 1);
                ps.setLong(3, classroom_id);
            } else if (time.size() == 3) {
                ps = conn.prepareStatement("UPDATE Classroom SET " + time.get(0) + "=?," + time.get(1) + "=?," + time.get(2) + "=? WHERE id=?");

                ps.setShort(1, (short) 1);
                ps.setShort(2, (short) 1);
                ps.setShort(3, (short) 1);
                ps.setLong(4, classroom_id);
            }
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //冲突返回true
    public boolean location_time_conflict(long classroom_id, String day_code, String time_code)  {

        ArrayList<String> time = getTimes(day_code, time_code);
        try {
            conn = getConnection();
            PreparedStatement ps = null;
            if (time.size() == 2) {
                ps = conn.prepareStatement("SELECT id FROM Classroom WHERE id=? AND " + time.get(0) + "=? AND " + time.get(1) + "=?");
                ps.setLong(1, classroom_id);
                ps.setShort(2, (short) 0);
                ps.setShort(3, (short) 0);
            } else if (time.size() == 3) {
                ps = conn.prepareStatement("SELECT id FROM Classroom WHERE id=? AND " + time.get(0) + "=? AND " + time.get(1) + "=? AND " + time.get(2) + "=? ");
                ps.setLong(1, classroom_id);
                ps.setShort(2, (short) 0);
                ps.setShort(3, (short) 0);
                ps.setShort(4, (short) 0);
            }
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public ArrayList<String> getTimes(String day_code, String time_code) {
        int time_code_int = Integer.parseInt(time_code);
        ArrayList<String> time = new ArrayList<>();
        for (String s : times[time_code_int]) {
            String temp = "class" + day_code + s;
            time.add(temp);
        }
        return time;
    }

    public ArrayList<Class> getAllClass()  {
        ArrayList<Class> classes = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * from Class ORDER BY ?");
            ps.setString(1, "id");
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


                ClassroomDAO classroomDAO=new ClassroomDAO();
                Classroom classroom=classroomDAO.getClassroomById(lesson.getClassroom_id());
                lesson.setBuilding(classroom.getBuilding());
                lesson.setRoom_number(classroom.getRoom_number());

                TeacherDAO teacherDAO=new TeacherDAO();
                Teacher teacher=teacherDAO.getTeacherById(lesson.getTeacher_id());
                lesson.setTeacher_name(teacher.getName());

                classes.add(lesson);
            }

            //课程按照时间顺序排列
            Comparator<Class> comparator=new Comparator<Class>() {
                @Override
                public int compare(Class c1, Class c2) {

                    if(c1.getDay_code()!=c2.getDay_code()){
                        return Integer.parseInt(c1.getDay_code())-Integer.parseInt(c2.getDay_code());
                    }else{
                        return Integer.parseInt(c1.getTime_code())-Integer.parseInt(c2.getTime_code());
                    }
                }
            };
            Collections.sort(classes,comparator);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return classes;
    }

    public Class getClassById(long id) {
        Class lesson = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * from Class WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lesson = new Class();
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


                ClassroomDAO classroomDAO=new ClassroomDAO();
                Classroom classroom=classroomDAO.getClassroomById(lesson.getClassroom_id());
                lesson.setBuilding(classroom.getBuilding());
                lesson.setRoom_number(classroom.getRoom_number());

                TeacherDAO teacherDAO=new TeacherDAO();
                Teacher teacher=teacherDAO.getTeacherById(lesson.getTeacher_id());
                lesson.setTeacher_name(teacher.getName());

            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lesson;
    }

    //返回-1代表被限制不能删除-即这门课没被选，若>=1则代表删除成功
    public int deleteById(long id){
        int result=-1;
        try {
            conn=getConnection();
            PreparedStatement ps=conn.prepareStatement("DELETE FROM Class WHERE id=?");
            ps.setLong(1,id);
            result=ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int modifyClass(Class lesson)  {
        if (location_time_conflict(lesson.getClassroom_id(), lesson.getDay_code(), lesson.getTime_code())) {
            return -1;
        }
        int result=-1;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Class set name=?,classroom_id=?,teacher_id=?,day_code=?,day=?,time_code=?,time=?,info=? WHERE id=?");
            ps.setString(1, lesson.getName());
            ps.setLong(2, lesson.getClassroom_id());
            ps.setLong(3, lesson.getTeacher_id());
            ps.setString(4,lesson.getDay_code());
            ps.setString(5,lesson.getDay());
            ps.setString(6,lesson.getTime_code());
            ps.setString(7,lesson.getTime());
            ps.setString(8,lesson.getInfo());
            ps.setLong(9,lesson.getId());


            result= ps.executeUpdate();

            setClassroom_Classes(lesson.getClassroom_id(), lesson.getDay_code(), lesson.getTime_code());


            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
