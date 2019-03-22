package servlet;

import DAO.*;
import javabean.*;
import javabean.Class;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ray on 2017/5/31.
 */
@WebServlet("/refresh.do")
public class refresh extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identity=req.getParameter("identity");
        //获取所有学生数据
        StudentDAO studentDAO = new StudentDAO();
        ArrayList<Student> students = studentDAO.getAllStudents();
        req.getSession().setAttribute("students", students);
        //获取所有教师数据
        TeacherDAO teacherDAO = new TeacherDAO();
        ArrayList<Teacher> teachers = teacherDAO.getAllTeachers();
        req.getSession().setAttribute("teachers", teachers);
        //获取所有教室信息
        ClassroomDAO classroomDAO=new ClassroomDAO();
        ArrayList<Classroom> classrooms=classroomDAO.getAllClassrooms();
        req.getSession().setAttribute("classrooms",classrooms);
        //获取第一教学楼所有room_number用作初始化
        ArrayList<String> 第1教学大楼room_numbers=classroomDAO.getRoom_numbersByBuilding("第1教学大楼");
        req.getSession().setAttribute("第1教学大楼room_numbers",第1教学大楼room_numbers);

        //获取所有课程信息
        ClassDAO classDAO=new ClassDAO();
        ArrayList<javabean.Class> classes=classDAO.getAllClass();
        req.getSession().setAttribute("classes",classes);
        String url=req.getHeader("Referer");
        switch (identity){
            case "manager":{

                break;
            }
            case "student":{
                Student student= (Student) req.getSession().getAttribute("student");
                //获取我所有选课的课程
                ArrayList<Class> myClassesTable=studentDAO.getMyClassTable(student.getId());
                req.getSession().setAttribute("myClassesTable",myClassesTable);
                QuestionDAO questionDAO=new QuestionDAO();
                req.getSession().setAttribute("answeredNotReadQuestionNum",questionDAO.getAnsweredNotReadQuestionNum(student.getId()));

                break;
            }
            case "teacher":{
                Teacher teacher= (Teacher) req.getSession().getAttribute("teacher");
                ArrayList<Class> allMyTeachClasses=teacherDAO.getAllMyTeachClasses(teacher.getId());
                QuestionDAO questionDAO=new QuestionDAO();
                req.getSession().setAttribute("unansweredQuestionNum",questionDAO.getUnansweredQuestionNum(teacher.getId()));

                req.getSession().setAttribute("allMyTeachClasses",allMyTeachClasses);

                break;
            }

        }
        resp.sendRedirect(url);
    }
}
