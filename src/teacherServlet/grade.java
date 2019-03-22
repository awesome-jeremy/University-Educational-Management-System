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
@WebServlet("/grade.do")
public class grade extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Student> oneClassNotScoredStudents= (ArrayList<Student>) req.getSession().getAttribute("oneClassNotScoredStudents");
        String markStr;
        int mark;
        long gradeClassId=Integer.parseInt((String) req.getSession().getAttribute("gradeClass"));
        StudentDAO studentDAO=new StudentDAO();
        for(Student stu:oneClassNotScoredStudents){
           markStr=req.getParameter(""+stu.getId());
           if(markStr!="") {
               mark = Integer.parseInt(markStr);
               studentDAO.gradeOneStudentForOneClass(gradeClassId, stu.getId(), mark);
           }
        }
        resp.sendRedirect("Teacher_showGradeClasses.jsp");
    }
}
