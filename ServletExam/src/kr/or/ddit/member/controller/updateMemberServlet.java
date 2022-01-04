package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/update.do")
@MultipartConfig(
		fileSizeThreshold = 1024*1024*3
		, maxFileSize = 1024*1024*40
		, maxRequestSize = 1024*1024*50)
public class updateMemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 파라미터정보 가져오기
		String memId = req.getParameter("memId");
				
		// 2. 서비스 객체 가져오기
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO atchFileVO = new AtchFileVO();
		
		// 기존의 첨부파일아이디 정보 가져오기와 설정하기
		
		
		// 3. 회원정보 조회
		MemberVO mv =memberService.getMember(memId);
		
		if(mv.getAtchFileId() > 0) { // 첨부파일이 존재하면...
			// 첨부파일 정보 조회
			AtchFileVO fileVO = new AtchFileVO();
			fileVO.setAtchFileId(mv.getAtchFileId());
			List<AtchFileVO> atchFileList = null;
			try {
				
				atchFileList = fileService.getAtchFileList(fileVO);
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}

			req.setAttribute("atchFileList", atchFileList);
		}
		
				
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
		String atchFileId = req.getParameter("atchFileId");
		
		// 2. 서비스 객체 생성하기
		IMemberService memberService = 
				MemberServiceImpl.getInstance();
		IAtchFileService fileService =
				AtchFileServiceImpl.getInstance();
		AtchFileVO atchFileVO = new AtchFileVO();
		
		try {
			// 첨부파일 목록 저장(공통기능)
			atchFileVO = fileService.saveAtchFileList(req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 기존의 첨부파일아이디 정보 가져오기와 설정하기
		if(atchFileVO != null && atchFileVO.getAtchFileId() > 0) {
			
		}else {
			atchFileVO = new AtchFileVO(); // 초기화
			atchFileVO.setAtchFileId(atchFileId == null ? -1 : Long.parseLong(atchFileId)); 
			
		}
		
		
		// 3. 회원정보 수정
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		if(atchFileVO == null) { // 신규 첨부파일이 존재하지 않는 경우...
			mv.setAtchFileId(Long.parseLong(atchFileId));
		}else {
			mv.setAtchFileId(atchFileVO.getAtchFileId());
		}
		      
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
