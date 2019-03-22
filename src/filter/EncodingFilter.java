package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(
		filterName="EncodingFilter",
		urlPatterns={"/*"},
		initParams={
				@WebInitParam(name="encoding",value="UTF-8"),
				@WebInitParam(name="contentType",value="text/html;charset=utf-8")
		}
		)
public class EncodingFilter implements Filter {
	private FilterConfig config;
    /**
     * Default constructor. 
     */
    public EncodingFilter() {
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
		String encoding=config.getInitParameter("encoding");
		String contentType=config.getInitParameter("contentType");
		request.setCharacterEncoding(encoding);
		response.setContentType(contentType);
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		config=fConfig;
	}

}
