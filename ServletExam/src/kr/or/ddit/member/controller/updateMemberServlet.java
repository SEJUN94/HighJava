package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/update.do")
public class updateMemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 파라미터정보 가져오기
		String memId = req.getParameter("memId");
				
		// 2. 서비스 객체 가져오기
		IMemberService memberService = MemberServiceImpl.getInstance();
				
		// 3. 회원정보 조회
		MemberVO mv =memberService.getMember(memId);
				
		req.setAttribute("mv", mv);
				
		// 4. 업데이트 화면으로 포워딩
		req.getRequestDispatcher("/WEB-INF/view/member/updateForm.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("UTF-8");
		// 1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		// 2. 서비스 객체 생성하기
		IMemberService memberService = 
				MemberServiceImpl.getInstance();
		
		// 3. 회원정보 등록
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		int cnt = memberService.updateMember(mv);
		
		String msg = "";
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		req.setAttribute("msg", msg);
		
		//resp.setCharacterEncoding("UTF-8");
		// 4. 목록 조회화면으로 이동
		//req.getRequestDispatcher("/member/list.do")
		//	.forward(req, resp);
		
		String redirectUrl = req.getContextPath() 
				+ "/member/list.do?msg=" 
				+ URLEncoder.encode(msg, "UTF-8");
		
		resp.sendRedirect(redirectUrl); // 목록조회 화면으로  리다이렉트
		
	}
}