package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
		filterName="LoginFilter",
		urlPatterns={"/error.jsp","/Manager_include.jsp","/Manager_template.jsp","/Student_include.jsp","/Student_template.jsp","/Teacher_include.jsp","/Teacher_template.jsp",
				"/Manager_Class_Info_Add.jsp","/Manager_Class_Info_Edit.jsp","/Manager_Class_Info_Modify.jsp",
				"/Manager_Classroom_Info_Add.jsp","/Manager_Classroom_Info_Edit.jsp","/Manager_Classroom_Info_Modify.jsp",
				"/Manager_Student_Info_Add.jsp","/Manager_Student_Info_Edit.jsp","/Manager_Student_Info_Modify.jsp",
				"/Manager_Teacher_Info_Add.jsp","/Manager_Teacher_Info_Edit.jsp","/Manager_Teacher_Info_Modify.jsp",
				"/Student_askQuestion.jsp","/Student_gradeTeachers.jsp","/Student_myClassesScore.jsp","/Student_myClassesTable.jsp",
				"/Student_question.jsp","/Student_selectCourses.jsp",
				"/Teacher_answerClassQuestions.jsp","/Teacher_classQuestions.jsp","/Teacher_gradeOneClassStudents.jsp",
				"/Teacher_showCommentClasses.jsp","/Teacher_showGradeClasses.jsp","/Teacher_showOneClassComments.jsp"

		}
		)

public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest hRequest=(HttpServletRequest) request;
		HttpSession session=hRequest.getSession();
		String userName=(String) session.getAttribute("user");
		if(userName==null){
			
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
