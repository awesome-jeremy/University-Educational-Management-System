package studentServlet;

import DAO.StudentDAO;
import javabean.Class;
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

@WebServlet("/gradeTeachers.do")
public class gradeTeachers extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student= (Student) req.getSession().getAttribute("student");
        ArrayList<Class> myClassesTable= (ArrayList<Class>) req.getSession().getAttribute("myClassesTable");
        StudentDAO studentDAO=new StudentDAO();
        for(Class lesson:myClassesTable){
            if(lesson.getTeacher_score()==-1){
                String teacher_scoreStr=req.getParameter(lesson.getId()+"score");
                String comment=req.getParameter(lesson.getId()+"comment");
                int teacher_score=Integer.parseInt(teacher_scoreStr);
               studentDAO.gradeOneTeacherForOneClass(lesson.getId(),student.getId(),teacher_score,comment);
            }
        }
        myClassesTable=studentDAO.getMyClassTable(student.getId());
        req.getSession().setAttribute("myClassesTable",myClassesTable);
        resp.sendRedirect("Student_gradeTeachers.jsp");
    }
}
