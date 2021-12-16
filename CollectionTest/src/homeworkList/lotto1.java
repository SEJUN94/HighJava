package homeworkList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class lotto1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Set<Integer> lottoNum = new HashSet<>();
		
		
		
		String menu ="\n\n==========================\n" + 
					 " Lotto 프로그램\n" + 
					 "--------------------------\n" + 
					 " 1. Lotto 구입\n" + 
					 " 2. 프로그램 종료\n" + 
    				 "==========================\n";
		while(true) {
		int count = 0;
		System.out.println(menu);
		System.out.print("메뉴선택 : ");
		int user = scan.nextInt();
		
		a	: if(user == 1) {
			System.out.println("Lotto 구입 시작\n\n");
				System.out.print("금액 입력 : ");
				int money = scan.nextInt();
				if(money >= 1000) {
					for (int i = 0; i < money/1000; i++) {
						lottoNum.clear();
						count++;
						while(lottoNum.size() < 6) {
							int num = (int)(Math.random() * 45 +1);
							lottoNum.add(num);
						}
//						System.out.print("로또 번호"+(i+1)+" : ");
						System.out.print("로또 번호"+count+" : ");
						System.out.println(lottoNum);
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
