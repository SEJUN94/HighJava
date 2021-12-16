package kr.or.ddit.basic;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 기본타입 입출력 보조 스트림 예제
 */
public class T13_DataIOStreamTest {
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		// DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해 준다.
		DataOutputStream dos = null;
		
		DataInputStream dis = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/test.dat");
			dos = new DataOutputStream(fos);
			
			dos.writeUTF("홍길동");  // 문자열 데이터 출력(UTF-8)
			dos.writeInt(17);      // 정수형으로 데이터 출력
			dos.writeFloat(3.14f); // 실수형(Float)으로 출력
			dos.writeDouble(3.14); // 실수형(Double)으로 출력
			dos.writeBoolean(true);// 논리형으로 출력]
			System.out.println("출력 완료...");
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				dos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
		 fis = new FileInputStream("d:/D_Other/test.dat");
		 dis = new DataInputStream(fis);
		 
		 System.out.println("문자열 자료 : " + dis.readUTF());
		 System.out.println("정수형 자료 : " + dis.readInt());
		 System.out.println("실수형(Float) 자료 : " + dis.readFloat());
		 System.out.println("실수형(Double) 자료 : " + dis.readDouble());
		 System.out.println("논리형 자료 : " + dis.readBoolean());
		}catch(IOException ex) {
			ex.printStackTrace();
	    }
	}
}
