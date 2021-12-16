package homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HorseRace {
	public static void main(String[] args) {
		List<Horse1> list = new ArrayList<Horse1>();
		list.add(new Horse1("Free"));     // 프리 
		list.add(new Horse1("Flo"));      // 플로 
		list.add(new Horse1("Soria"));    // 소리아 
		list.add(new Horse1("Luca"));     // 루카
		list.add(new Horse1("Lavini"));   // 라비니
		list.add(new Horse1("Paula"));    // 폴라 
		list.add(new Horse1("Marengo"));  // 마렝고 
		list.add(new Horse1("Merlin"));   // 메를린
		
		for (Horse1 race : list) {
			race.start();
		}
		for (Horse1 race : list) {
			try {
				System.out.println();
				race.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	} 
}


class Horse1 extends Thread implements Comparable<Horse1> {
	// 경마 필드 (경주마 이름을 담을 name, 경기후 랭크를 기록할 rank)
	private String hname;
	private int hrank;

	// 생성자 
	public Horse1(String hname) {
		super();
		this.hname = hname;
	}
	public Horse1(String hname, int hrank) {
		super();
		this.hname = hname;
		this.hrank = hrank;
	}
	
	// 메서드
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public int getHrank() {
		return hrank;
	}
	public void setHrank(int hrank) {
		this.hrank = hrank;
	}
	
	
	@Override
	public void run() {
		
		for (int i = 0; i < 50; i++) {
			System.out.print(getHname() + " : ");
			for (int j = 0; j < i; j++) {
				System.out.print("-");
			}
			System.out.print(">");
		for (int j = 49; j < 0; j++) {
			System.out.print("-");
		}
		
			System.out.println();
		}
		System.out.println();
		try {
			Thread.sleep(new Random().nextInt(1500));
			// Thread.sleep((int)(Math.random())*1500);
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println();
	}
	
	@Override
	public int compareTo(Horse1 o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}