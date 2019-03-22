package classroomServlet;

import DAO.ClassDAO;
import DAO.ClassroomDAO;
import DAO.StudentDAO;
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
 * Created by ray on 2017/5/19.
 */
@WebServlet("/classroomEdit.do")
public class classroomEdit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Classroom classroom= (Classroom) req.getSession().getAttribute("editClassroom");
        String building=req.getParameter("building");
        String room_number=req.getParameter("room_number");
        classroom.setBuilding(building);
        classroom.setRoom_number(room_number);

        ClassroomDAO classroomDAO=new ClassroomDAO();
        int result= -1;


            result = classroomDAO.modifyClassroom(classroom);


        if(result>=1){
            ArrayList<Classroom> classrooms= classroomDAO.getAllClassrooms();
            req.getSession().setAttribute("classrooms",classrooms);
            resp.sendRedirect("Manager_Classroom_Info_Edit.jsp");
        }else{
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Long id = Long.parseLong(req.getParameter("id"));

        ClassroomDAO classroomDAO=new ClassroomDAO();

        Classroom editClassroom=classroomDAO.getClassroomById(id);
        req.getSession().setAttribute("editClassroom",editClassroom);
        req.getRequestDispatcher("/"+"Manager_Classroom_Info_Modify.jsp").forward(req,resp);




    }



    }


