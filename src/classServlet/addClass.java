package classServlet;

import DAO.ClassDAO;
import DAO.ClassroomDAO;
import javabean.Class;
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
@WebServlet("/addClass.do")
public class addClass extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        String teacher = req.getParameter("teacher");
        String[] teacherInfo = teacher.split(",");
        long teacher_id = Long.parseLong(teacherInfo[0]);
        String teacher_name = teacherInfo[1];

        String info = req.getParameter("info");

        String building = req.getParameter("building");
        String room = req.getParameter("room");

        String dayParameter = req.getParameter("day");
        String[] dayInfo = dayParameter.split(",");
        String day_code = dayInfo[0];
        String day = dayInfo[1];

        String timeParameter = req.getParameter("time");
        String[] timeInfo = timeParameter.split(",");
        String time_code = timeInfo[0];
        String time = timeInfo[1];




        ClassroomDAO classroomDAO = new ClassroomDAO();
        long classroom_id = classroomDAO.getClassroomId(building, room);

        ClassDAO classDAO = new ClassDAO();
        Class lesson =new Class(name,classroom_id,teacher_id,day,day_code,time,building,room,time_code,info);
        int result = 0;
        try {
            result = classDAO.insertClass(lesson);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (result >= 1) {
//            ArrayList<Student> students= studentDAO.getAllStudents();
//            req.getSession().setAttribute("students",students);
            req.getSession().setAttribute("classes",classDAO.getAllClass());
            req.getRequestDispatcher("/Manager_Class_Info_Add.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }


    }
}
