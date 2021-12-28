package homework;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/** 
 * 
 * Json을 이요한 파싱(레시피 정보 조회 API)
 * 
 */

public class JsonParsingExam2 {

	public void parse() {
		
		try {
			
			//DOM Document객체 생성하기 위한 메서드
			DocumentBuilderFactory dbf =
					DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			
			String svcKey = "SjEKTw%2Fsg3icrUPH5WJflFJa2NVs1JpUQY5WtE6q0UQ2N7blZ9m6ZDYXVDcOurzZMi6VgZTiWPo5LmCUzpM0bw%3D%3D";  // 레시피 재료 정보 조회 서비스
			String apiKey = "3051381/v1/uddi:d3963efd-26de-434b-9c8f-f706838f93ee"; // 개인별 발급.
			String startIdx = "1";  	// 시작 페이지
			String perPage = "20";		// 불러올 페이지 수 종료 순번(최대64)
			String lastidx = "";
			Scanner sc = new Scanner(System.in);
			
			URL url = new URL("https://api.odcloud.kr/api/"+ apiKey
					+ "?page="+ startIdx +"&perPage=" + perPage
					+"&serviceKey=" +  svcKey);
			
			
			
			System.out.println(url);
			System.out.println("--------------------------------------------------------------------");
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(url.openConnection().getInputStream()));
			
			lastidx = (long) jsonObject.get("totalCount") + "";
			

			while(Integer.parseInt(startIdx)<=((Integer.parseInt(lastidx)/20)+1)) {
				url = new URL("https://api.odcloud.kr/api/"+ apiKey
						+ "?page="+ startIdx +"&perPage=" + perPage
						+"&serviceKey=" +  svcKey);
				jsonObject = (JSONObject) parser.parse(new InputStreamReader(url.openConnection().getInputStream()));
				JSONArray list = (JSONArray) jsonObject.get("data");
				
				
	
					for(Object obj : list) {
							
							JSONObject jObj = (JSONObject) obj;
							String no = (String) jObj.get("No.");
							String coIn = (String) jObj.get("공동발명자");
							String techNm = (String) jObj.get("기술명");
							String techfd = (String) jObj.get("기술분야");
							String repIn  = (String) jObj.get("대표발명자명");
							String regNo =(String) jObj.get("등록번호")+ "-" +(String) jObj.get("등록일자");
							String appNo =(String) jObj.get("출원번호")+ "-" +(String) jObj.get("출원일자");
							
							
							
							System.out.println("NO : " + no);
							System.out.println("공동발명자 : " + coIn);
							System.out.println("기술명 : " + techNm);
							System.out.println("기술분야 : " + techfd);
							System.out.println("대표발명자명 : " + repIn);
							System.out.println("등록번호 : " + regNo);
							System.out.println("출원번호 : " + appNo);
							
							System.out.println("--------------------------------------------------------------------");
	
					}
					
				
				System.out.println("현재페이지 : " + startIdx + "/" + ((Integer.parseInt(lastidx)/20)+1));
				
				if(Integer.parseInt(startIdx)==((Integer.parseInt(lastidx)/20)+1)) {
					System.out.println("마지막 페이지 입니다.");
				}else {
					System.out.println("페이지를 넘기려면 엔터를 입력하세요...");
					sc.nextLine();
				}
				startIdx = (Integer.parseInt(startIdx)+1)+"";
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}	
	
	public static void main(String[] args) {
		new JsonParsingExam2().parse();
	}
}
