package teacherServlet;

import DAO.TeacherDAO;
import javabean.Comment;

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
@WebServlet("/showCommentClasses.do")
public class showCommentClasses extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String class_idStr=req.getParameter("class_id");
        long class_id=Long.parseLong(class_idStr);
        TeacherDAO teacherDAO=new TeacherDAO();
        ArrayList<Comment> allCommentForOneClass=teacherDAO.getAllCommentForOneClass(class_id);
        req.getSession().setAttribute("allCommentForOneClass",allCommentForOneClass);
        resp.sendRedirect("Teacher_showOneClassComments.jsp");
    }
}
