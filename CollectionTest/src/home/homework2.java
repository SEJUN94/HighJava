package home;

public class homework2 {
	public static void main(String[] args) {
		Planet[] pp = Planet.values();
		for (int i = 0; i < pp.length; i++) {
			System.out.println(pp[i].name()+"의 면적 : " + pp[i].getArea()+"㎢");
		}
	}
	
	public enum Planet {
		수성(2439), 
		금성(6052), 
		지구(6371), 
		성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);
		
		// 괄호속의 값이 저장될 변수 선언(필드)
		private double radius;

		// 생성자
		private Planet(double radius) {
			this.radius = radius;
		}

		// 메서드
		public double getRadius() {
			return radius;
		}

		public long getArea() {
			return (long)(radius*radius*Math.PI*4);
		}
		
	}
}
