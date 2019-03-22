package teacherServlet;

import DAO.StudentDAO;
import javabean.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ray on 2017/5/27.
 */
@WebServlet("/showGradeClasses.do")
public class showGradeClasses extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String class_id=req.getParameter("class_id");
        StudentDAO studentDAO=new StudentDAO();
        ArrayList<Student> oneClassNotScoredStudents=studentDAO.getOneClassNotScoredStudents(Long.parseLong(class_id));
        req.getSession().setAttribute("oneClassNotScoredStudents",oneClassNotScoredStudents);
        req.getSession().setAttribute("gradeClass",class_id);
        resp.sendRedirect("Teacher_gradeOneClassStudents.jsp");
    }
}
