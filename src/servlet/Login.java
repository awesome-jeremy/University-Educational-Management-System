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
 * Created by ray on 2017/5/21.
 */
@WebServlet("/login.do")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr=req.getParameter("id");
        long id=Long.parseLong(idStr);
        String password=req.getParameter("password");
        String identity=req.getParameter("identity");


        switch (identity){
            case "Student":{
                StudentDAO studentDAO=new StudentDAO();
                Student student =studentDAO.login(id,password);


                if(student!=null){
                    req.getSession().setAttribute("student", student);
                    req.getSession().setAttribute("user",student.getName());
                    //获取我所有选课的课程
                    ArrayList<Class> myClassesTable=studentDAO.getMyClassTable(id);
                    req.getSession().setAttribute("myClassesTable",myClassesTable);
                    QuestionDAO questionDAO=new QuestionDAO();
                    req.getSession().setAttribute("answeredNotReadQuestionNum",questionDAO.getAnsweredNotReadQuestionNum(student.getId()));
                    resp.sendRedirect("Student_selectCourses.jsp");
                }else {
                   resp.sendRedirect("Login.jsp");
                }
                break;
            }

            case "Teacher":{
                TeacherDAO teacherDAO=new TeacherDAO();
                Teacher teacher=teacherDAO.login(id,password);
                if(teacher!=null){
                    req.getSession().setAttribute("teacher", teacher);
                    req.getSession().setAttribute("user",teacher.getName());
                    ArrayList<Class> allMyTeachClasses=teacherDAO.getAllMyTeachClasses(teacher.getId());
                    QuestionDAO questionDAO=new QuestionDAO();
                    req.getSession().setAttribute("unansweredQuestionNum",questionDAO.getUnansweredQuestionNum(teacher.getId()));

                    req.getSession().setAttribute("allMyTeachClasses",allMyTeachClasses);


                    resp.sendRedirect("Teacher_classQuestions.jsp");
                }else {
                    resp.sendRedirect("Login.jsp");
                }

                break;
            }

            case "Manager":{
                ManagerDAO managerDAO=new ManagerDAO();
                Manager manager=managerDAO.login(id,password);
                if(manager!=null){
                    req.getSession().setAttribute("manager", manager);
                    req.getSession().setAttribute("user","manager");
                    resp.sendRedirect("Manager_Student_Info_Edit.jsp");
                }else {
                    resp.sendRedirect("Login.jsp");
                }

                break;
            }



        }

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
        ArrayList<Class> classes=classDAO.getAllClass();
        req.getSession().setAttribute("classes",classes);


    }
}
