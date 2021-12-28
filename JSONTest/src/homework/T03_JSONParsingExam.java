package homework;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class T03_JSONParsingExam {
	public void parse() {
		
		try {
						
			String svcKey = "mMeSqEwboegDKBZpmaRZcFA%2BGlhZ25E7vZb%2BQDgIg14wwJXrcpKjQvCvoBREncTGZrqzduyRXKLa%2BpzCfgGAZA%3D%3D";
			String returnType = "JSON"; // 리턴 타입 정하기
			String cityName = "서울"; // 궁금한 시도 이름 작성
			String pageNo = "1"; // 열람할 페이지 수
			String numOfRows = "30"; // 열람하고자 하는 정보 갯수
			
			URL url = new URL("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=" + URLEncoder.encode(cityName, "UTF-8") + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&returnType=" + returnType + "&serviceKey=" + svcKey + "&ver=1.0");
			
			System.out.println(url);
//			System.out.println("this is json");
			
			// JSON 파서로부터 입력받은 파일을 파싱하도록 요청
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(url.openConnection().getInputStream()));
			JSONObject svcObject = (JSONObject) jsonObject.get("response");
			JSONObject bodyObject = (JSONObject) svcObject.get("body");
			
			JSONArray list = (JSONArray) bodyObject.get("items");
			String title = String.format("%s %s %s %s %s %s %s %10s", "이산화황등급", "통합대기환경수치", "이산화황수치" ,"일산화탄소수치", "일산화탄소등급", "도시명", "관측소명", "관측일시");
			System.out.println(title);
			
			for (Object obj : list) {
				
				JSONObject jobj = (JSONObject) obj;
				
				String formatStr = String.format("%5s %8s %12s %8s %5s %7s %5s %20s", jobj.get("so2Grade"), jobj.get("khaiValue"),
						jobj.get("so2Value"),jobj.get("coValue"),jobj.get("coGrade"),jobj.get("sidoName"),jobj.get("stationName"),  jobj.get("dataTime"));

				System.out.println(formatStr);
				System.out.println("-------------------------------------------------------------------------------------------------------");
			}
			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} // try-catch fin.
		
		
	} // parse() fin.
	
	public static void main(String[] args) {
		new T03_JSONParsingExam().parse();
	}
	
} // class fin.