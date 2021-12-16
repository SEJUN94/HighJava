package home;

public class a {
	public enum Plan{
		수성(2439), 
		금성(6052), 
		지구(6371), 
		성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);
		private int radius;
		Plan(int radius){
		this.radius=radius;
		}
		public int getRadius() {
			return radius;
		}
	}
	
	public static void main(String[] args) {
		Plan p[]=Plan.values();
		for (Plan plan : p) {
		System.out.println(plan.getRadius());
			System.out.println(plan.name()+" : 의 겉넓이는 " +(long)((long)plan.getRadius()*plan.getRadius()*Math.PI*4));
		}
	}
}

