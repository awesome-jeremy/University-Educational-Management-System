package DAO;

import com.sun.org.apache.regexp.internal.RE;
import javabean.Class;
import javabean.Classroom;
import javabean.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ray on 2017/5/20.
 */
public class ClassroomDAO extends BaseDAO {
    Connection conn=null;
    public int insertClassroom(String building,String room_number){
        int result=-1;
        if(isAlreadyExist(building,room_number))return -1;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT Classroom(building,room_number) values(?,?)");
            ps.setString(1,building);
            ps.setString(2,room_number);
            result= ps.executeUpdate();

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
    public ArrayList<Classroom> getAllClassrooms()  {
        ArrayList<Classroom> classrooms=new ArrayList<>();
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * from Classroom ORDER BY ?");
          ps.setString(1,"building");
            ResultSet rs=ps.executeQuery();
            if(rs!=null)
                while(rs.next()){
                    Classroom classroom=new Classroom();
                    classroom.setId(rs.getLong("id"));
                    classroom.setBuilding(rs.getString("building"));
                    classroom.setRoom_number(rs.getString("room_number"));


                    classrooms.add(classroom);
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
        return classrooms;
    }

    public ArrayList<String> getRoom_numbersByBuilding(String building)  {
        ArrayList<String> room_numbers=null;
        try {
            conn=getConnection();
            room_numbers=new ArrayList<>();
            PreparedStatement ps=conn.prepareStatement("SELECT room_number from Classroom WHERE building=?");
            ps.setString(1,building);
            ResultSet rs=ps.executeQuery();
            if(rs!=null){
                String s;
                while(rs.next()){
                    s=rs.getString("room_number");
                    room_numbers.add(s);
                }
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
        return room_numbers;
    }

    public long getClassroomId(String building,String room_number)  {
        long id=-1;
        try {
            conn=getConnection();
            PreparedStatement ps=conn.prepareStatement("SELECT id from Classroom WHERE building=? AND room_number=?");
            ps.setString(1,building);
            ps.setString(2,room_number);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                id=rs.getLong("id");
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
        return id;
    }

    public Classroom getClassroomById(long id) {
        Classroom classroom = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * from Classroom WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                classroom=new Classroom();
                classroom.setId(rs.getLong("id"));
                classroom.setBuilding(rs.getString("building"));
                classroom.setRoom_number(rs.getString("room_number"));

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
        return classroom;
    }

    //返回-1代表被限制不能删除-即没有课程在这个教室开课，若>=1则代表删除成功
    public int deleteById(long id){
        int result=-1;
        try {
            conn=getConnection();
            PreparedStatement ps=conn.prepareStatement("DELETE FROM Classroom WHERE id=?");
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

    public int modifyClassroom(Classroom classroom)  {
        int result=-1;
        if(isAlreadyExist(classroom.getBuilding(),classroom.getRoom_number()))return -1;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Classroom set building=?,room_number=? WHERE id=?");
            ps.setLong(3, classroom.getId());
            ps.setString(1, classroom.getBuilding());
            ps.setString(2, classroom.getRoom_number());
            result= ps.executeUpdate();
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

    public boolean isAlreadyExist(String building,String room_number)  {
        try {
            conn=getConnection();
            PreparedStatement ps=conn.prepareStatement("SELECT * from Classroom where building=? and room_number=?");
            ps.setString(1,building);
            ps.setString(2,room_number);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){


                return true;
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
        return false;
    }

}
