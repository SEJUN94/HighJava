package kr.or.ddit.basic;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class T02_DOMParsingTest {
	public void parse() {
		try {
			// DOM Document 객체 생성하기 위한 메서드
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			
			File file = new File("./src/new_book.xml");
			
			// DOM 파서로부터 입력받은 파일을 파싱하도록 요청
			Document document = builder.parse(file);
			
			// DOM Document 객체로부터 루트 엘리먼트 및 자식 객체 가져오기
			Element root = document.getDocumentElement();
			System.out.println("루트 엘리먼트 태그명: " + root.getTagName());
			
			// 하위 엘리먼트 접근방법1 : getElementsByTagName() 이용
			NodeList bookNodeList = root.getElementsByTagName("book");
			Node firstBookNode = bookNodeList.item(0); // 첫번째 항목
			Element firstBookElement = (Element) firstBookNode;
			
			// 속성 접근 방법1 : 엘리먼트 객체의 getAttribute() 이용
			System.out.println("엘리먼트 객체의 getAttribute() 이용 : " + firstBookElement.getAttribute("isbn"));
			
			// 속성 접근방법2 : 노드객체의 getAttributes()메서드 이용
			// 				(속성노드를 접근하기)
			NamedNodeMap nodeMap = firstBookNode.getAttributes();
			System.out.println("노드 객체의 getAttributes() 이용 : " + nodeMap.getNamedItem("isbn").getNodeValue());
			
			// 하위 엘리먼트 접근방법2 : getChildNodes() 이용
			NodeList firstBookChildNodeList = firstBookNode.getChildNodes();
			
			// 엔터키에 해당하는 부분이 읽힐 수 있으므로, getChildNodes()
			// 보다는 getElementByTagName()을 이용해 접근하는게 좋다.
			// #text노드(공백문자) 때문에 인덱스 값을 1로 설정해야 함.
			Node titleNode = firstBookChildNodeList.item(1);
			Element titleElement = (Element) titleNode;
			System.out.println("titleElement.getTagName() : " + titleElement.getTagName());
			System.out.println("titleElement.getTextContent() : " + titleElement.getTextContent());
			
			// 전체 출력하기
			System.out.println("------------------------------------------------");
			System.out.printf("%8s %8s %15s %12s %8s \n", "ISBN", "분류", "제목", "저자", "가격");
			System.out.println("------------------------------------------------");
			for(int i=0; i<bookNodeList.getLength(); i++) {
				Node bookNode = bookNodeList.item(i);
				Element bookEl = (Element) bookNode;
				
				String isbn = bookEl.getAttribute("isbn");
				String kind = bookEl.getAttribute("kind");
				String title = bookEl.getElementsByTagName("title").item(0).getTextContent().trim();
				String author = bookEl.getElementsByTagName("author").item(0).getTextContent().trim();
				String price= bookEl.getElementsByTagName("price").item(0).getTextContent().trim();
				
				String resultStr = String.format("%8s %8s %9s %10s %8s", isbn, kind, title, author, price);
				System.out.println(resultStr);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new T02_DOMParsingTest().parse();
	}
}
