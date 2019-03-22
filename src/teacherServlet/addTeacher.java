package teacherServlet;

import DAO.StudentDAO;
import DAO.TeacherDAO;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import javabean.Teacher;
import utilities.TimeStampRandom_forFileName;
import utilities.utility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by ray on 2017/5/18.
 */
@WebServlet("/addTeacher.do")
public class addTeacher extends HttpServlet {

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
        String birth=smart.getRequest().getParameter("birth");
        String id_number=smart.getRequest().getParameter("id_number");
        String education=smart.getRequest().getParameter("education");
        String professional_title=smart.getRequest().getParameter("professional_title");
        String image="image/"+fileName;

        TeacherDAO teacherDAO=new TeacherDAO();
        int result=teacherDAO.insertTeacher(name,sex,nation,birth,id_number,education,professional_title,image);
        if(result>=1){
            ArrayList<Teacher> teachers=teacherDAO.getAllTeachers();
            req.getSession().setAttribute("teachers",teachers);
             resp.sendRedirect("Manager_Teacher_Info_Add.jsp");
        }else{
            req.getRequestDispatcher("/error.jsp").forward(req,resp);

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
