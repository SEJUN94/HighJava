package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/detail.do")
public class DetailMemberServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 파라미터정보 가져오기
		String memId = req.getParameter("memId");
		
		// 2. 서비스 객체 가져오기
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		// 3. 회원정보 조회
		MemberVO mv =memberService.getMember(memId);
		
		req.setAttribute("mv", mv);
		
		// 결과를 VIEW화면에 출력하기
		req.getRequestDispatcher("/view/member/detail.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {

		
	}
}
