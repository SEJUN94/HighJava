package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. 서블릿을 시작하기 앞서 서블릿을 extends를 해주어야함.
 * @author PC-14
 */
public class T01_ServletLifeCycle extends HttpServlet {
	/*
	 * 서블릿에 대하여...
	 * 
	 * -컨테이너(서블릿 엔진)에 의해 관리되는 자바기반 웹 컨포넌트로서, 동적인 웹컨텐츠 생성을 가능하게 한다.
	 */
	public void init() throws ServletException {
		// 초기 코드 작성
		System.out.println("init() 호출됨...");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 실제적인 작업 수행이 지작되는 지점...(자바의 메인메서드 역할)
		System.out.println("service() 호출됨...");
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 GET인 경우 호출됨...
		System.out.println("doGet() 호출됨...");
		
		throw new ServletException("서블릿 예외 발생했어요...");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이POST인 경우 호출됨..
		System.out.println("doPost() 호출됨...");
	}

	@Override
	public void destroy() {
		// 객체 소멸시(컨테이너로부터 서블릿 객체 제거시) 필요한 코드 작성
		// 마무리 작업 할게 있다면 destroy 메서드에 정의 해주면됨
		System.out.println("destroy() 호출됨...");
	}
}
