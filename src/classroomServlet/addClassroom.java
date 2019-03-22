package classroomServlet;

import DAO.ClassroomDAO;
import DAO.StudentDAO;
import javabean.Classroom;
import javabean.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ray on 2017/5/20.
 */
@WebServlet("/addClassroom.do")
public class addClassroom extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String building=req.getParameter("building");
        String room_number=req.getParameter("room_number");


        ClassroomDAO classroomDAO=new ClassroomDAO();
        int result= 0;

            result = classroomDAO.insertClassroom(building,room_number);


        if(result>=1){
            ArrayList<Classroom> classrooms= classroomDAO.getAllClassrooms();
            req.getSession().setAttribute("classrooms",classrooms);
            req.getRequestDispatcher("/Manager_Classroom_Info_Add.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
        }
    }
}
