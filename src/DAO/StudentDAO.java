package DAO;

import javabean.*;
import javabean.Class;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by ray on 2017/5/18.
 */
public class StudentDAO extends BaseDAO {
    public  String[][] times={
            {"1","2"},
            {"3","4"},
            {"3","4","5"},
            {"6","7"},
            {"8","9"},
            {"10","11","12"}
    };
    Connection conn;
    public int insertStudent(String name, String sex, String nation, String major, String birth, String id_number, String address, String image) {
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT Student(name,sex,nation,major,birth,id_number,address,image) values(?,?,?,?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, sex);
            ps.setString(3, nation);
            ps.setString(4, major);
            ps.setString(5, birth);
            ps.setString(6, id_number);
            ps.setString(7, address);
            ps.setString(8, image);
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    public int modifyStudent(long id,String name, String sex, String nation, String major, String birth, String id_number, String address, String password)  {
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Student set name=?,sex=?,nation=?,major=?,birth=?,id_number=?,address=?,password=? WHERE id=?");
            ps.setString(1, name);
            ps.setString(2, sex);
            ps.setString(3, nation);
            ps.setString(4, major);
            ps.setString(5, birth);
            ps.setString(6, id_number);
            ps.setString(7, address);
            ps.setString(8, password);
            ps.setLong(9,id);
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> students=new ArrayList<>();
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * from Student");
            ResultSet rs=ps.executeQuery();
            if(rs!=null)
            while(rs.next()){
                Student stu=new Student();
                stu.setId(rs.getLong("id"));
                stu.setPassword(rs.getString("password"));
                stu.setSex(rs.getString("sex"));
                stu.setAddress(rs.getString("address"));
                stu.setBirth(rs.getString("birth"));
                stu.setId_number(rs.getString("id_number"));
                stu.setMajor(rs.getString("major"));
                stu.setName(rs.getString("name"));
                stu.setNation(rs.getString("nation"));
                stu.setImage(rs.getString("image"));
                students.add(stu);
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
        return students;
    }

    //返回-1代表被限制不能删除-即这名学生没选课，若>=1则代表删除成功
    public int deleteById(long id){
        int result=-1;
        try {
             conn=getConnection();
            PreparedStatement ps=conn.prepareStatement("DELETE FROM Student WHERE id=?");
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

    //manager修改用户信息用，没有学生的上课信息
    public Student findById(long id) {
        Student stu = null;
        try {
             conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Student WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                stu = new Student();
                stu.setId(rs.getLong("id"));
                stu.setPassword(rs.getString("password"));
                stu.setSex(rs.getString("sex"));
                stu.setAddress(rs.getString("address"));
                stu.setBirth(rs.getString("birth"));
                stu.setId_number(rs.getString("id_number"));
                stu.setMajor(rs.getString("major"));
                stu.setName(rs.getString("name"));
                stu.setNation(rs.getString("nation"));
                stu.setImage(rs.getString("image"));
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
        return stu;
    }

    //学生自己登录后，系统将所有信息从数据库调出来（包括学生一周排课表class11等），存进session中
    public Student login(long id,String password) {
        Student stu=null;
        try {
            conn=getConnection();
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM Student WHERE id=? AND password=?");
            ps.setLong(1,id);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                stu = new Student();
                stu.setId(rs.getLong("id"));
                stu.setPassword(rs.getString("password"));
                stu.setSex(rs.getString("sex"));
                stu.setAddress(rs.getString("address"));
                stu.setBirth(rs.getString("birth"));
                stu.setId_number(rs.getString("id_number"));
                stu.setMajor(rs.getString("major"));
                stu.setName(rs.getString("name"));
                stu.setNation(rs.getString("nation"));
                stu.setImage(rs.getString("image"));

                stu.setClass11(rs.getShort("class11"));
                stu.setClass12(rs.getShort("class12"));
                stu.setClass13(rs.getShort("class13"));
                stu.setClass14(rs.getShort("class14"));
                stu.setClass15(rs.getShort("class15"));
                stu.setClass16(rs.getShort("class16"));
                stu.setClass17(rs.getShort("class17"));
                stu.setClass18(rs.getShort("class18"));
                stu.setClass19(rs.getShort("class19"));
                stu.setClass110(rs.getShort("class110"));
                stu.setClass111(rs.getShort("class111"));
                stu.setClass112(rs.getShort("class112"));

                stu.setClass21(rs.getShort("class21"));
                stu.setClass22(rs.getShort("class22"));
                stu.setClass23(rs.getShort("class23"));
                stu.setClass24(rs.getShort("class24"));
                stu.setClass25(rs.getShort("class25"));
                stu.setClass26(rs.getShort("class26"));
                stu.setClass27(rs.getShort("class27"));
                stu.setClass28(rs.getShort("class28"));
                stu.setClass29(rs.getShort("class29"));
                stu.setClass210(rs.getShort("class210"));
                stu.setClass211(rs.getShort("class211"));
                stu.setClass212(rs.getShort("class212"));

                stu.setClass31(rs.getShort("class31"));
                stu.setClass32(rs.getShort("class32"));
                stu.setClass33(rs.getShort("class33"));
                stu.setClass34(rs.getShort("class34"));
                stu.setClass35(rs.getShort("class35"));
                stu.setClass36(rs.getShort("class36"));
                stu.setClass37(rs.getShort("class37"));
                stu.setClass38(rs.getShort("class38"));
                stu.setClass39(rs.getShort("class39"));
                stu.setClass310(rs.getShort("class310"));
                stu.setClass311(rs.getShort("class311"));
                stu.setClass312(rs.getShort("class312"));

                stu.setClass41(rs.getShort("class41"));
                stu.setClass42(rs.getShort("class42"));
                stu.setClass43(rs.getShort("class43"));
                stu.setClass44(rs.getShort("class44"));
                stu.setClass45(rs.getShort("class45"));
                stu.setClass46(rs.getShort("class46"));
                stu.setClass47(rs.getShort("class47"));
                stu.setClass48(rs.getShort("class48"));
                stu.setClass49(rs.getShort("class49"));
                stu.setClass410(rs.getShort("class410"));
                stu.setClass411(rs.getShort("class411"));
                stu.setClass412(rs.getShort("class412"));

                stu.setClass51(rs.getShort("class51"));
                stu.setClass52(rs.getShort("class52"));
                stu.setClass53(rs.getShort("class53"));
                stu.setClass54(rs.getShort("class54"));
                stu.setClass55(rs.getShort("class55"));
                stu.setClass56(rs.getShort("class56"));
                stu.setClass57(rs.getShort("class57"));
                stu.setClass58(rs.getShort("class58"));
                stu.setClass59(rs.getShort("class59"));
                stu.setClass510(rs.getShort("class510"));
                stu.setClass511(rs.getShort("class511"));
                stu.setClass512(rs.getShort("class512"));
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
        return stu;
    }


    //设置学生的课表，例如添加了课程是星期一的3、4节 则该Student表中对应的的class13和class14应设置为1
    public void setStudent_Classes(long student_id,String day_code,String time_code)  {
        ArrayList<String> time = getTimes(day_code, time_code);
        try {
            conn = getConnection();
            PreparedStatement ps = null;
            if (time.size() == 2) {
                ps = conn.prepareStatement("UPDATE Student SET " + time.get(0) + "=?," + time.get(1) + "=? WHERE id=?");

                ps.setShort(1, (short) 1);
                ps.setShort(2, (short) 1);
                ps.setLong(3, student_id);
            } else if (time.size() == 3) {
                ps = conn.prepareStatement("UPDATE Student SET " + time.get(0) + "=?," + time.get(1) +"=?,"+ time.get(2)+"=? WHERE id=?");

                ps.setShort(1, (short) 1);
                ps.setShort(2, (short) 1);
                ps.setShort(3, (short) 1);
                ps.setLong(4, student_id);
            }
            ps.executeUpdate();
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
    }

    //将已选课程加入Student_Class表,并且将对应的Class的选课人数+1
    public int insertStudent_Class(long student_id,long class_id){
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT Student_Class(student_id,class_id) values(?,?)");
            ps.setLong(1, student_id);
            ps.setLong(2, class_id);
            ps.executeUpdate();

            ps=conn.prepareStatement("UPDATE Class SET number=number+1 WHERE id=?");
            ps.setLong(1,class_id);
            ps.executeUpdate();
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
        return 0;
    }



    // 学生的课表和刚选的课程是否冲突 冲突返回true, 若不冲突直接将这门课选入学生课表内
    public boolean Student_Course_Conflict(long student_id,long class_id,String day_code,String time_code) {

        ArrayList<String> time =getTimes(day_code,time_code);
        try {
            conn=getConnection();
            PreparedStatement ps=null;
            if(time.size()==2){
                ps=conn.prepareStatement("SELECT * FROM Student WHERE id=? AND "+time.get(0)+"=? AND "+time.get(1)+"=?");
                ps.setLong(1,student_id);
                ps.setShort(2, (short) 0);
                ps.setShort(3, (short) 0);
            }else if(time.size()==3){
                ps=conn.prepareStatement("SELECT * FROM Student WHERE id=? AND "+time.get(0)+"=? AND "+time.get(1)+"=? AND "+time.get(2)+"=? ");
                ps.setLong(1,student_id);
                ps.setShort(2, (short) 0);
                ps.setShort(3, (short) 0);
                ps.setShort(4, (short) 0);
            }
            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                setStudent_Classes(student_id,day_code,time_code);
                insertStudent_Class(student_id,class_id);
                return false;
            }else{
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

    public ArrayList<String> getTimes(String day_code,String time_code){
        int time_code_int=Integer.parseInt(time_code);
        ArrayList<String> time=new ArrayList<>();
        for(String s:times[time_code_int]){
            String temp="class"+day_code+s;
            time.add(temp);
        }
        return time;
    }

    //获取所有我已选的课程
    public ArrayList<Class> getMyClassTable(long student_id){
        ArrayList<Long> myClassesId=new ArrayList<>();
        ArrayList<Class> myClassTable=new ArrayList<>();
        try {
            conn=getConnection();
            //获取该学生所有课程id
            PreparedStatement ps=conn.prepareStatement("SELECT class_id from Student_Class WHERE student_id=? ");
            ps.setLong(1,student_id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                myClassesId.add(rs.getLong("class_id"));
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
            //根据得到的所有课程id获取所有课程信息
            ClassDAO classDAO=new ClassDAO();
            for(long class_id:myClassesId){
                Class lesson=classDAO.getClassById(class_id);

                QuestionDAO questionDAO=new QuestionDAO();
                int oneClassAnsweredNotReadQuestionNum=questionDAO.getOneClassAnsweredNotReadQuestionNum(student_id,class_id);
                lesson.setHaveAnswer_number(oneClassAnsweredNotReadQuestionNum);

                int grade=getGradeForOneClass(class_id,student_id);
                lesson.setScore(grade);

                int teacher_grade=getTeacher_GradeForOneClass(class_id,student_id);
                lesson.setTeacher_score(teacher_grade);

                myClassTable.add(lesson);
            }
            Collections.sort(myClassTable,comparator);
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
        return myClassTable;
    }

    //获取某节课还没打分的学生
    public ArrayList<Student> getOneClassNotScoredStudents(long class_id){
        ArrayList<Student> oneClassNotScoredStudents = null;
        ArrayList<Long> studentsID=null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT student_id from Student_Class where class_id=? and score=-1");
            ps.setLong(1, class_id);
            ResultSet rs = ps.executeQuery();
            if (rs != null)
                studentsID=new ArrayList<>();
            while(rs.next()){
                studentsID.add(rs.getLong("student_id"));
            }

            oneClassNotScoredStudents=new ArrayList<>();
            for(long student_id:studentsID){
                Student stu=findById(student_id);
                oneClassNotScoredStudents.add(stu);
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

        return oneClassNotScoredStudents;
    }

    //给学生某门课打分
    public int gradeOneStudentForOneClass(long class_id,long student_id,int score){
        int result=-1;
        PreparedStatement ps=null;
        try {
            conn=getConnection();
            ps=conn.prepareStatement("UPDATE Student_Class set score=? where student_id=? and class_id=?");
            ps.setInt(1,score);
            ps.setLong(2,student_id);
            ps.setLong(3,class_id);
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
        return result;
    }

    //获取学生某门课成绩
    public int getGradeForOneClass(long class_id,long student_id){
        int score=-1;
        PreparedStatement ps=null;
        try {
            conn=getConnection();
            ps=conn.prepareStatement("SELECT score from Student_Class where student_id=? and class_id=?");
            ps.setLong(1,student_id);
            ps.setLong(2,class_id);
           ResultSet rs=ps.executeQuery();
           if(rs.next()){
               score=rs.getInt("score");
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
        return score;
    }

    //获取某门课学生给老师打的成绩
    public int getTeacher_GradeForOneClass(long class_id,long student_id){
        int teacher_score=-1;
        PreparedStatement ps=null;
        try {
            conn=getConnection();
            ps=conn.prepareStatement("SELECT teacher_score from Student_Class where student_id=? and class_id=?");
            ps.setLong(1,student_id);
            ps.setLong(2,class_id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                teacher_score=rs.getInt("teacher_score");
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
        return teacher_score;
    }


    //给某门课打分和评价
    public int gradeOneTeacherForOneClass(long class_id,long student_id,int teacher_score,String comment){
        int result=-1;
        PreparedStatement ps=null;
        try {
            conn=getConnection();
            ps=conn.prepareStatement("UPDATE Student_Class set teacher_score=? , comment=? where student_id=? and class_id=?");
            ps.setInt(1,teacher_score);
            ps.setString(2,comment);
            ps.setLong(3,student_id);
            ps.setLong(4,class_id);
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
        return result;
    }

}

