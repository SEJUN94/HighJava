package kr.or.ddit.basic;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListner implements ServletContextListener, ServletContextAttributeListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("[MyServletContextListner] contextDestroyed 호출됨.");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("[MyServletContextListner] contextInitialized 호출됨.");
		
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		System.out.println("[MyServletContextListner] attributeAdded 호출됨. => " + arg0.getName());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		System.out.println("[MyServletContextListner] attributeRemoved 호출됨. => " + arg0.getName());
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		System.out.println("[MyServletContextListner] attributeReplaced 호출됨. => " + arg0.getName());
	}

}
