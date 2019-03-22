package questionServlet;

import DAO.ClassDAO;
import DAO.QuestionDAO;
import DAO.TeacherDAO;
import javabean.Class;
import javabean.Question;
import javabean.Student;
import javabean.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ray on 2017/5/25.
 */
@WebServlet("/askQuestion.do")
public class askQuestion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String class_idStr=req.getParameter("class_id");
        String teacher_idStr=req.getParameter("teacher_id");
        long class_id=Integer.parseInt(class_idStr);
        long teacher_id=Integer.parseInt(teacher_idStr);
        ClassDAO classDAO=new ClassDAO();
        TeacherDAO teacherDAO=new TeacherDAO();
        Class lesson=classDAO.getClassById(class_id);
        Teacher teacher=teacherDAO.getTeacherById(teacher_id);
        Student student= (Student) req.getSession().getAttribute("student");

        Question editQuestion=new Question(class_id,teacher_id,student.getId(),lesson.getName(),teacher.getName(),student.getName());
        req.getSession().setAttribute("editQuestion",editQuestion);

        QuestionDAO questionDAO=new QuestionDAO();
        //查看了老师的回复就设置成已查看，把1置为2
        questionDAO.setHaveReadAnswers(class_id,student.getId());


        //获取这名学生的这门课下的所有和这个老师的问题。

        ArrayList<Question> oneClass_StudentQuestions= questionDAO.getOneClass_StudentQuestions(class_id,student.getId());
        req.getSession().setAttribute("oneClass_StudentQuestions",oneClass_StudentQuestions);



        resp.sendRedirect("Student_question.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String question=req.getParameter("question");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        String question_date=sdf.format(date);
        sdf=new SimpleDateFormat("HH:mm");
        String question_time=sdf.format(date);

        Question editQuestion=(Question) req.getSession().getAttribute("editQuestion");
        editQuestion.setQuestion(question);
        editQuestion.setQuestion_date(question_date);
        editQuestion.setQuestion_time(question_time);

        QuestionDAO questionDAO=new QuestionDAO();
        questionDAO.insertQuestion(editQuestion);


        ArrayList<Question> oneClass_StudentQuestions= questionDAO.getOneClass_StudentQuestions(editQuestion.getClass_id(),editQuestion.getStudent_id());
        req.getSession().setAttribute("oneClass_StudentQuestions",oneClass_StudentQuestions);

        resp.sendRedirect("Student_question.jsp");

    }
}
