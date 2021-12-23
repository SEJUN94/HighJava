package kr.or.ddit.basic;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * JSON 문서 파싱 예제(레시피 정보 조회 API)
 */
public class JsonParsingExam {
	public void parse() {
		try {
			// DOM Document 객체 생성하기 위한 메서드
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			
			String svcKey = "Grid_20150827000000000227_1";  // 레시피 재료 정보 조회 서비스
			String apiKey = "1df7e8571e8df3f8cbc9b87691ca7d3e4d04f03c593d477e52bf67b03f0b6e1c"; // 개인별 발급.
			String startIdx = "1";  	// 레시피 재료 시작 순번
			String endIdx = "5";		// 레시피 재료 종료 순번
			String recipeId = "195428";	// 래시피가 궁금한 음식 ID

			URL url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey
					+ "/json/"+ svcKey + "/"+startIdx +"/" + endIdx
					+"?RECIPE_ID=" +  recipeId);
			
			//System.out.println(url);
			
			// Json 파서로부터 입력받은 파일을 파싱하도록 요청
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject)parser.parse(new InputStreamReader(url.openConnection().getInputStream()));
			
			JSONObject svcObject = (JSONObject) jsonObject.get(svcKey);
			long totalCnt = (long) svcObject.get("totalCnt");
			endIdx = totalCnt + "";
			
			
			url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey
					+ "/json/"+ svcKey + "/"+startIdx +"/" + endIdx
					+"?RECIPE_ID=" +  recipeId);
			
			jsonObject = (JSONObject)parser.parse(new InputStreamReader(url.openStream()));
			
			svcObject = (JSONObject) jsonObject.get(svcKey);
			
			JSONObject result = (JSONObject) svcObject.get("result");
			
			String code = (String) result.get("code");
			
			
			if(code.equals("INFO-000")) { // 정상이면...
				
				JSONArray list = (JSONArray)svcObject.get("row");
				
				for (Object obj : list) {
					
					JSONObject jObj = (JSONObject)obj;
					
					String formatStr = String.format("%3s %8s %10s %10s %15s", jObj.get("ROW_NUM"), recipeId, jObj.get("IRDNT_SN"), jObj.get("IRDNT_NM"), jObj.get("IRDNT_CPCTY"));
					
					System.out.println(formatStr);
					System.out.println("------------------------------------------------");
				}
				
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new JsonParsingExam().parse();
	}
}
