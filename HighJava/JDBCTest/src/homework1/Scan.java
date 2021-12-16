package homework1;

import java.util.Scanner;

public class Scan {
	private static Scanner s = new Scanner(System.in);
	
	
	public static String nextLine(){
		return s.nextLine();
	}
	
	public static int nextInt(){
		return Integer.parseInt(s.nextLine());
	}
	
	public static char nextChar(){
		return s.next().charAt(0);
	}
}
