package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String fileName = "";
		String fileName = "11.jpg";
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", 
				"attachment; filename=\"" + fileName + "\"");
		
		/*
		
		Content-Disposition 헤더에 대하여...
		
		1. response header에서 사용되는 경우 ... ex) 파일 다운로드
		  Content-Disposition: inline (default)
		  Content-Disposition: attachment  // 파일 다운로드
		  Content-Disposition: attachment; filename="a.jpg"  // 해당이름으로 다운로드 앞에 같은 경우 "a.jpg"로 다운로드됨 
		
		*/
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:/D_Other/2.jpg"));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int c = 0;
		while((c = bis.read()) != -1) {
			bos.write(c);
		}
		bis.close();
		bos.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
