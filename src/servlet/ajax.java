package servlet;

import DAO.ClassDAO;
import DAO.ClassroomDAO;
import DAO.QuestionDAO;
import DAO.StudentDAO;
import javabean.Class;
import javabean.Question;
import javabean.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ray on 2017/5/20.
 */
@WebServlet("/ajax.do")
public class ajax extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation=req.getParameter("operation");

        PrintWriter out= resp.getWriter();
        switch (operation){
            case "addClassroom":{
                String building=req.getParameter("building");
                String room_number=req.getParameter("room_number");
                boolean isAlreadyExist=new ClassroomDAO().isAlreadyExist(building,room_number);
                if(isAlreadyExist) {
                    out.print("已经存在该教室了");
                }
                out.close();
                break;
            }
            case "getRoom_numbers":{
                String building=req.getParameter("building");
                ClassroomDAO classroomDAO=new ClassroomDAO();
                ArrayList<String> room_numbers=classroomDAO.getRoom_numbersByBuilding(building);
                String room_numbers_Str="";
                for(String s:room_numbers){
                    room_numbers_Str=room_numbers_Str+s+",";
                    System.out.print(s);
                }
               room_numbers_Str= room_numbers_Str.substring(0,room_numbers_Str.length()-1);
                out.print(room_numbers_Str);
                out.close();
                break;
            }

            case "location_time_conflict":{
                String building=req.getParameter("building");
                String room_number=req.getParameter("room_number");
                String day=req.getParameter("day");
                String time=req.getParameter("time");
                String day_code=day.split(",")[0];
                String time_code=time.split(",")[0];

                ClassroomDAO classroomDAO=new ClassroomDAO();
                long classroom_id=classroomDAO.getClassroomId(building,room_number);

                ClassDAO classDAO=new ClassDAO();
                boolean isConflict=classDAO.location_time_conflict(classroom_id,day_code,time_code);
                String message="";
                if(isConflict){
                    message="冲突";
                }else{
                    message="不冲突";
                }
                out.print(message);
                out.close();
                break;

            }

            case "selectCourse":{
                String course_id=req.getParameter("course_id");
                long class_id=Long.parseLong(course_id);
                Student student= (Student) req.getSession().getAttribute("student");
                long student_id=student.getId();
                String day_code=req.getParameter("day_code");
                String time_code=req.getParameter("time_code");
                StudentDAO studentDAO=new StudentDAO();
                boolean isStudent_Course_Conflict=studentDAO.Student_Course_Conflict(student_id,class_id,day_code,time_code);
                String message="";
                if(isStudent_Course_Conflict){
                    message="该课程与你课表中的课程冲突，请选择其他课程!";
                }else{
                    message="选课成功!";
                    //刷新所有课程信息
                    ClassDAO classDAO=new ClassDAO();
                    req.getSession().setAttribute("classes",classDAO.getAllClass());

                    ArrayList<Class> myClassesTable=studentDAO.getMyClassTable(student_id);
                    req.getSession().setAttribute("myClassesTable",myClassesTable);
                }
                out.print(message);
                out.close();
                break;
            }

            case "insertAnswerToQuestion":{
                String question_id=req.getParameter("question_id");
                String class_id=req.getParameter("class_id");
                String answer=req.getParameter("answer");
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Date date=new Date();
                String answer_date=sdf.format(date);
                sdf=new SimpleDateFormat("HH:mm");
                String answer_time=sdf.format(date);
                Question question=new Question(Long.parseLong(question_id),answer,answer_date,answer_time);
                QuestionDAO questionDAO=new QuestionDAO();
                int result=questionDAO.insertAnswerToQuestion(question);
                if(result>=1){
                    req.getSession().setAttribute("oneClassUnansweredQuestions",questionDAO.getOneClassUnansweredQuestions(Long.parseLong(class_id)));
                    out.print("ok");
                }
                out.close();
                break;
            }

            case "test":{
                String number=req.getParameter("cnumber");
                out.print("hh");
                out.close();
                break;
            }

        }
    }
}
