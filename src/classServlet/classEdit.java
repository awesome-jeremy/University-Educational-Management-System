package classServlet;

import DAO.ClassDAO;
import DAO.ClassroomDAO;
import javabean.Class;
import javabean.Classroom;

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
@WebServlet("/classEdit.do")
public class classEdit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Class lesson = (Class) req.getSession().getAttribute("editClass");


        String name = req.getParameter("name");

        String teacher = req.getParameter("teacher");
        String[] teacherInfo = teacher.split(",");
        long teacher_id = Long.parseLong(teacherInfo[0]);

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


        lesson.setName(name);
        lesson.setTeacher_id(teacher_id);
        lesson.setInfo(info);
        lesson.setRoom_number(room);
        lesson.setDay_code(day_code);
        lesson.setDay(day);
        lesson.setTime_code(time_code);
        lesson.setTime(time);

        ClassroomDAO classroomDAO = new ClassroomDAO();
        long classroom_id=classroomDAO.getClassroomId(building,room);
        lesson.setClassroom_id(classroom_id);
        int result = -1;

        ClassDAO classDAO=new ClassDAO();

            result = classDAO.modifyClass(lesson);


        if (result >= 1) {
            ArrayList<Class> classes =classDAO.getAllClass();
            req.getSession().setAttribute("classes", classes);
            resp.sendRedirect("Manager_Class_Info_Edit.jsp");
        } else {
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        ClassDAO classDAO = new ClassDAO();

        Class editClass = classDAO.getClassById(id);
        req.getSession().setAttribute("editClass", editClass);
        req.getRequestDispatcher("/" + "Manager_Class_Info_Modify.jsp").forward(req, resp);

    }


}


