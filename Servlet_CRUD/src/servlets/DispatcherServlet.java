package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controls.Controller;
import controls.MemberAddController;
import controls.MemberListController;
import controls.MemberUpdateController;
import vo.Member;


@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		try {
			ServletContext sc = this.getServletContext();
			HashMap<String,Object> model = new HashMap<>();
			model.put("dao", sc.getAttribute("dao"));
		
			Controller pageController = null;
			String pageControllerPath = null;
			
			if("/List.do".equals(servletPath)) {
			pageController = new MemberListController();
			
			
			}else if("/Add.do".equals(servletPath)) {
				pageController = new MemberAddController();
				if(request.getParameter("email") != null) {
					model.put("member", new Member()
							.setEmail(request.getParameter("email"))
							.setPwd(request.getParameter("pwd"))
							.setMname(request.getParameter("mname")));
				}
			}
			
			else if("/Update.do".equals(servletPath)) {
				pageController = new MemberUpdateController();
				
				if(request.getParameter("fmno") != null) {
					model.put("mno", request.getParameter("fmno"));	
				}
				
				if(request.getParameter("email") != null) {
					model.put("member", new Member()
							.setMno(Integer.parseInt(request.getParameter("mno")))
							.setMname(request.getParameter("mname"))
							.setEmail(request.getParameter("email")));
				}
			}
			
			
			else if("/Delete.do".equals(servletPath)) {
				pageControllerPath = "Delete";
				request.setAttribute("mno", request.getParameter("mno"));
			}
			
			else if("/Login.do".equals(servletPath)) {
				System.out.println("통과");
				pageControllerPath = "Login";
				if(request.getParameter("email") !=null) {
					request.setAttribute("member", new Member()
							.setEmail(request.getParameter("email"))
							.setPwd(request.getParameter("pwd")));
				}
			}
			
			else if("/Logout.do".equals(servletPath)) {
				pageControllerPath = "Logout";
			}
			
			String viewUrl = pageController.execute(model);
			
			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
		
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			}else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			/*request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);*/
		}
	}

}
