package example;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

// 로또를 구매하는 프로그램 작성하기
// Set 또는 List를 사용하는 예제
public class lotto {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Set<Integer> list = new TreeSet<>();
		int count = 0;
		
		for (int i = 1; i < 46; i++) {
			list.add(i);
		}
		
		System.out.println("	==========================\r\n" + 
				"       	  Lotto 프로그램\r\n" + 
				"	--------------------------\r\n" + 
				"	 1. Lotto 구입\r\n" + 
				"	  2. 프로그램 종료\r\n" + 
				"	==========================");
		System.out.print("메뉴선택>> ");
		int menu = scan.nextInt();
		switch(menu) {
			case 1:
				System.out.print("구매금액>> ");
				int user = scan.nextInt();
				if(user >= 1000) {
					for(int i = 0; i < user/1000; i++) {
						list.clear();
						count++;
						while(list.size() < 6) {
						int num = (int)(Math.random()*45+1);
						list.add(num);
						}
						System.out.println(list);
					}
				}
				break;
			case 2:
				System.out.println("구매를 종료합니다.");
				System.exit(0);
		}
	}
}
