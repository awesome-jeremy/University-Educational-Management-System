package listener; /**
 * Created by ray on 2017/5/18.
 */

import DAO.ClassDAO;
import DAO.ClassroomDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import javabean.*;
import javabean.Class;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.ArrayList;

@WebListener()
public class Listener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private ServletContext servletContext;

    // Public constructor is required by servlet spec
    public Listener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        // TODO Auto-generated method stub
        servletContext = sce.getServletContext();
        servletContext.setAttribute("vistorNum", 4);
        servletContext.setAttribute("buildings", Classroom.buildings);



    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */

        //servletContext=se.getSession().getServletContext();
        // TODO Auto-generated method stub


        //记录在线人数
        int vistorNum = (int) servletContext.getAttribute("vistorNum");
        vistorNum++;
        servletContext.setAttribute("vistorNum", vistorNum);

//        //获取所有学生数据
//        StudentDAO studentDAO = new StudentDAO();
//        ArrayList<Student> students = studentDAO.getAllStudents();
//        se.getSession().setAttribute("students", students);
//        //获取所有教师数据
//        TeacherDAO teacherDAO = new TeacherDAO();
//        ArrayList<Teacher> teachers = teacherDAO.getAllTeachers();
//        se.getSession().setAttribute("teachers", teachers);
//        //获取所有教室信息
//        ClassroomDAO classroomDAO=new ClassroomDAO();
//        ArrayList<Classroom> classrooms=classroomDAO.getAllClassrooms();
//        se.getSession().setAttribute("classrooms",classrooms);
//        //获取第一教学楼所有room_number用作初始化
//        ArrayList<String> 第1教学大楼room_numbers=classroomDAO.getRoom_numbersByBuilding("第1教学大楼");
//        se.getSession().setAttribute("第1教学大楼room_numbers",第1教学大楼room_numbers);
//
//        //获取所有课程信息
//        ClassDAO classDAO=new ClassDAO();
//        ArrayList<Class> classes=classDAO.getAllClass();
//        se.getSession().setAttribute("classes",classes);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
        int vistorNum = (int) servletContext.getAttribute("vistorNum");
        vistorNum--;
        servletContext.setAttribute("vistorNum", vistorNum);
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
