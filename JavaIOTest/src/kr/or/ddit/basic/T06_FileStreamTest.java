package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;

public class T06_FileStreamTest {
	public static void main(String[] args) {
		
		// 파일에 출력하기
		FileOutputStream fos = null;
		
		try {
			// 출력용 OutputStream객체 생성
			fos = new FileOutputStream("d:/D_Other/out.txt");
			
			for (char ch='a'; ch<='z'; ch++) {
				fos.write(ch);
			}
			
			System.out.println("파일에 쓰기 작업 완료...");
			
			
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
