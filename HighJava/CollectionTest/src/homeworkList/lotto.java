package homeworkList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class lotto {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		List<Integer> lottoNum = new ArrayList<Integer>();
		for (int i = 1; i < 46; i++) {
			lottoNum.add(i);
		}
		String menu ="\n\n==========================\n" + 
					 " Lotto 프로그램\n" + 
					 "--------------------------\n" + 
					 " 1. Lotto 구입\n" + 
					 " 2. 프로그램 종료\n" + 
    				 "==========================\n";
		while(true) {
		System.out.println(menu);
		System.out.print("메뉴선택 : ");
		int user = scan.nextInt();
		
		a	: if(user == 1) {
				System.out.print("금액 입력 : ");
				int money = scan.nextInt();
				if(money >= 1000) {
				System.out.println("Lotto 구입 시작\n\n");
				System.out.println("로또 번호 : ");
				for (int i = 1; i <= money/1000; i++) {
					Collections.shuffle(lottoNum);
					System.out.println();
					for (int j = 0; j < 6; j++) {
						System.out.print(lottoNum.get(j)+" ");
					}
//				while(lottoNum.size() < 6) {
//					int lonum = (int)(Math.random() * 45 +1);
//					lottoNum.add(lonum);
//				}
				} 
				}else {
					System.out.println("금액이 부족해 구매할수 없습니다.");
					break a;
				}
				int result = money%1000;
				System.out.printf("\n받은금액은 %d원 이고 거스름돈은 %d원 입니다.\n",money,result);
			} else if(user == 2) {
				System.out.println("\n\n다음에 또 방문해주세요.");
				System.exit(0);
			}
		}
	}
}
