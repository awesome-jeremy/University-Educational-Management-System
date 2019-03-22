package teacherServlet;

import DAO.StudentDAO;
import DAO.TeacherDAO;
import javabean.Student;
import javabean.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ray on 2017/5/19.
 */
@WebServlet("/teacherEdit.do")
public class teacherEdit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher editTeacher = (Teacher) req.getSession().getAttribute("editTeacher");
        Long id = editTeacher.getId();
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String nation = req.getParameter("nation");
        String birth = req.getParameter("birth");
        String id_number = req.getParameter("id_number");
        String education = req.getParameter("education");
        String professional_title = req.getParameter("professional_title");

        TeacherDAO teacherDAO = new TeacherDAO();
        int result = 0;

            result = teacherDAO.modifyTeacher(id, name, sex, nation, professional_title, birth, id_number, education, password);


        if (result >= 1) {
            ArrayList<Teacher> teachers = teacherDAO.getAllTeachers();
            req.getSession().setAttribute("teachers", teachers);
            resp.sendRedirect("Manager_Teacher_Info_Edit.jsp");
        } else {
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher editTeacher = teacherDAO.findById(id);
        req.getSession().setAttribute("editTeacher", editTeacher);
        req.getRequestDispatcher("/" + "Manager_Teacher_Info_Modify.jsp").forward(req, resp);

    }


}


