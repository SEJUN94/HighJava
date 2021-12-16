package homework;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

// java.nio.file은 파일 및 파일 시스템에 접근하기 위한 클래스
// IO와 NIO의 차이  입출력 방식으로 IO는 스트림 방식  nio는  채널 방식을 사용한다.
// IO는 스트림 기반 => 스트림은 입력 스트림과 출력 스트림이 구분되어 있음.
// NIO 는 채널 기반 => 채널은 스트림과는 다르게 양방향 입출력 가능
// nio의 장점은 과도한 스레드 생성을 피하고 스래드를 효과적으로 재사용한다는 장점이 있다.
// toPath메소드는 java.io.File 오브젝트로 표시되는 추상 경로 이름에서 Path를 얻는 데 사용할 수 있습니다.
public class ex1 {
	public static void main(String[] args) {
		// 1. 원본 file, 복사할 file 준비
		File tulip = new File("C:\\Users\\PC-14\\Desktop\\⁯\\고급자바\\파일복사/2.jpg");
		File copyTulip = new File("d:/D_Other/2.jpg");
		try {
			// Files클래스에서 제공하는 파일을 복사하는 메서드 .copy()
			Files.copy(tulip.toPath(), copyTulip.toPath(), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("\n\n\n\n복사가 완료되었습니다.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
