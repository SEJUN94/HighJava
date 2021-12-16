package homeworkList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentList {
	public static void main(String[] args) {
		List<Student> student = new ArrayList<Student>();
		
		student.add(new Student("2104", "박길동", 60,48,55));
		student.add(new Student("2106", "여길동", 80,80,100));
		student.add(new Student("2109", "무길동", 35,75,45));
		student.add(new Student("2102", "총길동", 77,75,62));
		student.add(new Student("2101", "황길동", 80,75,60));
		student.add(new Student("2105", "신길동", 66,73,68));
		student.add(new Student("2103", "도길동", 100,100,100));
		student.add(new Student("2108", "홍길동", 100,100,100));
		student.add(new Student("2107", "정길동", 50,50,50));
		
		System.out.println("---------------------------정렬 전---------------------------");
		for (Student stu : student) {
			System.out.println(stu);
		}
		
		Collections.sort(student);
		
		System.out.println("------------------------학번순으로 정렬------------------------");
		for (Student stu : student) {
			System.out.println(stu);
		}
		
		System.out.println("------------------------총점순으로 정렬------------------------");
		Collections.sort(student, new DescTotal());
		
		for (int i = 0; i < student.size(); i++) { 
			System.out.println( i+1 +"등 " + student.get(i));
			
		}
		System.out.println("-----------------------------------------------------------");
		
	}
}


class DescTotal implements Comparator<Student> {
	
	public int compare(Student stu1,Student stu2) {
		// TODO Auto-generated method stub
		if(stu1.getTotal() > stu2.getTotal()) {
			return -1;
		} else if(stu1.getTotal() == stu2.getTotal()) {
			return stu1.compareTo(stu2)*-1;
		} else {
			return 1;
		}
	}
}


class Student implements Comparable<Student> {
	//필드 (학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수)
	private String studentId;
	private String studentname;
	private int language;
	private int engilsh;
	private int math;
	private int total;
	private int rank;
	
	
	//생성자
	public Student(String studentId, String studentname, int language, int engilsh, int math) {
		super();
		this.studentId = studentId;
		this.studentname = studentname;
		this.language = language;
		this.engilsh = engilsh;
		this.math = math;
		this.total = language+engilsh+math;
	}

	//메소드
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public int getEngilsh() {
		return engilsh;
	}

	public void setEngilsh(int engilsh) {
		this.engilsh = engilsh;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "[학번=" + studentId + ", 이름=" + studentname + ", 국어=" + language
				+ ", 영어=" + engilsh + ", 수학=" + math + ", 총점=" + total + "]";
	}

	@Override
	public int compareTo(Student stu) {
		// TODO Auto-generated method stub
		return this.getStudentId().compareTo(stu.getStudentId());
	}
}


