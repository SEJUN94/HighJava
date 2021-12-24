package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T03_ServletParameterTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	/*
		요청메시지로부터 파라미터 데이터 가져오는 방법
		
		- getParmeter() - 파라미터값이 한개인 경우에 사용
		- getParmeterValues() - 파라미터값이 여러개인 경우에 사용 ex)여러개 선택가능한 체크박스 
		- getParmeterNames() - request에 존재한 모든 파라미터
				정보를 가져올  때 사용함.
	 */
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String hobby= req.getParameter("hobby");
		String rlgn = req.getParameter("rlgn");
		
		String[] food =  req.getParameterValues("food");  // 좋아하는 음식이 여러개일수 있기에 getParmeterValues로 받음
		
		//resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<p>username : " + username + "</p>");
		out.println("<p>password : " + password+ "</p>");
		out.println("<p>gender : " + gender+ "</p>");
		out.println("<p>hobby : " + hobby + "</p>");
		out.println("<p>rlgn : " + rlgn + "</p>");
		
		if(food != null) {
			for(String f : food ) {
				out.print("<p>food: " + f + "</p>");
			}
		}
		
		Enumeration<String> params = req.getParameterNames();
		
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			
			out.println("<p>파라미터 이름: " + param + "</p>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
