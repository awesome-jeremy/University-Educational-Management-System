package studentServlet;

import DAO.StudentDAO;
import DAO.TeacherDAO;
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
@WebServlet("/studentEdit.do")
public class studentEdit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student editStudent= (Student) req.getSession().getAttribute("editStudent");
        Long id=editStudent.getId();
        String password=req.getParameter("password");
        String name=req.getParameter("name");
        String sex=req.getParameter("sex");
        String nation=req.getParameter("nation");
        String major=req.getParameter("major");
        String birth=req.getParameter("birth");
        String id_number=req.getParameter("id_number");
        String address=req.getParameter("address");

        StudentDAO studentDAO=new StudentDAO();
        int result= 0;

            result = studentDAO.modifyStudent(id,name,sex,nation,major,birth,id_number,address,password);


        if(result>=1){
            ArrayList<Student> students= studentDAO.getAllStudents();
            req.getSession().setAttribute("students",students);
            resp.sendRedirect("Manager_Student_Info_Edit.jsp");
        }else{
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        StudentDAO studentDAO=new StudentDAO();

                        Student editStudent=studentDAO.findById(id);
                        req.getSession().setAttribute("editStudent",editStudent);
                        req.getRequestDispatcher("/"+"Manager_Student_Info_Modify.jsp").forward(req,resp);



                }



    }


