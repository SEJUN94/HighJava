package kr.or.ddit.basic;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * XML DOM을 이용한 XML문서 생성 예제
 * @author PC-14
 */
public class T01_DOMCreationTest {
/*
	W3C(World Wide Web Consortium)
	DOM(Document Object Model) 표준에 대하여...
	
	DOM은 문서에 접근하는 표준방법으로서, 이를 이용하면, 플랫폼 및 개발언어
	에 독립적으로 문서의 내용, 구조 및 스타일 정보를 동적으로 핸들링(접근, 수정, 삭제) 할 수 있다.
	
	W3C DOM 표준은 다음과 같이 크게 3가지로 나누어 볼 수 있다.
	
	Core DOM - 모든 문서타입을 위한 핵심 표준 모델(API)
	XML DOM - XML문서를 위한 표준 모델
	HTML DOM - HTML문서를 위한 표준 모델
	
	예를 들면, HTML DOM은 HTML 엘리먼트 요소 및 속성 정보를 핸들링하기 위한
	인터페이스를 제공한다.
 */
	public void createDoc() throws ParserConfigurationException {
		// XML문서를 생성하기 위한 DocumentBuilder객체 생성하기
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		// Document 객체 생성
		Document document = builder.newDocument();
		
		// root 엘리먼트 생성
		Element root = document.createElement("root");
		
		// booklist 엘리먼트 생성
		Element booklist = document.createElement("booklist");
		
		// book 엘리먼트 생성 및 속성값 설정하기
		Element book = document.createElement("book");
		book.setAttribute("isbn", "B001");
		book.setAttribute("kind", "JAVA");
		
		// 자식 엘리먼트 생성 및 설정
		Element title = document.createElement("title");
		title.setTextContent("자바초급");
		Element author = document.createElement("author");
		author.setTextContent("이순신");
		Element price = document.createElement("price");
		price.setTextContent("25000");
		
		// book 엘리먼트에 자식 엘리먼트 추가
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		booklist.appendChild(book);
		//----------------------------------------
		
		
	}
	
}
