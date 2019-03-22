package teacherServlet;


import DAO.QuestionDAO;
import javabean.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ray on 2017/5/25.
 */
@WebServlet("/answerQuestions.do")
public class answerQuestions extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String class_id=req.getParameter("class_id");
        QuestionDAO questionDAO=new QuestionDAO();
        ArrayList<Question> oneClassUnansweredQuestions=questionDAO.getOneClassUnansweredQuestions(Long.parseLong(class_id));
        req.getSession().setAttribute("oneClassUnansweredQuestions",oneClassUnansweredQuestions);
        req.getRequestDispatcher("/Teacher_answerClassQuestions.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



    }
}
