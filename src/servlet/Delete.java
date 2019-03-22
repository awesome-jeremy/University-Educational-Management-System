package servlet;

import DAO.ClassDAO;
import DAO.ClassroomDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import javabean.Classroom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ray on 2017/5/19.
 */
@WebServlet("/Delete.do")
public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        Long id = Long.parseLong(req.getParameter("id"));
        String url=req.getHeader("Referer");





                switch (type) {
                    case "Student": {
                        StudentDAO studentDAO=new StudentDAO();
                        studentDAO.deleteById(id);
                        req.getSession().setAttribute("students",studentDAO.getAllStudents());
                        break;
                    }
                    case "Teacher": {
                        TeacherDAO teacherDAO=new TeacherDAO();
                        teacherDAO.deleteById(id);
                        req.getSession().setAttribute("teachers",teacherDAO.getAllTeachers());
                        break;
                    }
                    case "Class": {
                        ClassDAO classDAO=new ClassDAO();
                        classDAO.deleteById(id);
                        req.getSession().setAttribute("classes",classDAO.getAllClass());
                        break;
                    }
                    case "Classroom": {
                        ClassroomDAO classroomDAO=new ClassroomDAO();
                        classroomDAO.deleteById(id);
                        req.getSession().setAttribute("classrooms",classroomDAO.getAllClassrooms());
                        break;
                    }
                    default:

                }
                resp.sendRedirect(url);


    }

}
