package studentServlet;

import DAO.StudentDAO;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import javabean.Student;
import utilities.TimeStampRandom_forFileName;
import utilities.utility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;


/**
 * Created by ray on 2017/5/18.
 */
@WebServlet("/addStudent.do")
public class addStudent extends javax.servlet.http.HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SmartUpload smart=new SmartUpload();
        JspFactory fac=JspFactory.getDefaultFactory();
        PageContext pageContext=fac.getPageContext(this, req,resp, null, false, JspWriter.DEFAULT_BUFFER, true);
        smart.initialize(pageContext);
        try {
            smart.upload();
        } catch (SmartUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String fileExt=smart.getFiles().getFile(0).getFileExt();
        String fileName= TimeStampRandom_forFileName.getFileName()+"."+fileExt;
        try {
            smart.getFiles().getFile(0).saveAs(utility.imageFileUrl+fileName);
        } catch (SmartUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String name=smart.getRequest().getParameter("name");
        String sex=smart.getRequest().getParameter("sex");
        String nation=smart.getRequest().getParameter("nation");
        String major=smart.getRequest().getParameter("major");
        String birth=smart.getRequest().getParameter("birth");
        String id_number=smart.getRequest().getParameter("id_number");
        String address=smart.getRequest().getParameter("address");
        String image="image/"+fileName;

        StudentDAO studentDAO=new StudentDAO();
        int result= 0;

            result = studentDAO.insertStudent(name,sex,nation,major,birth,id_number,address,image);


        if(result>=1){
            ArrayList<Student> students= studentDAO.getAllStudents();
            req.getSession().setAttribute("students",students);
            req.getRequestDispatcher("/Manager_Student_Info_Add.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
