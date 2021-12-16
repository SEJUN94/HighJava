package basic;

public class CarTest {

	public static void main(String[] args) {
		NewCar myCar = new NewCar("red");
		System.out.println("myCar의 색: " + myCar.getColor());
		System.out.println("차의 최대 속력: " + NewCar.getMaxSpeed() + "km/h");
		
		System.out.print("첫 번째 speedUp(100.0km/h): ");
		if (myCar.speedUp(100.0)) {
			System.out.print("속도 변경 가능,");
		} else {
			System.out.print("속도 변경 불가능,");
		}
		System.out.println(" 현재 차의 속력: " + myCar.getSpeed() + "km/h");
		
		System.out.print("두 번째 speedUp(90.0km/h): ");
		if(myCar.speedUp(90.0)) {
			System.out.print("속도 변경 가능,");
		} else {
			System.out.print("속도 변경 불가능,");
		}
		System.out.println(" 현재 차의 속력: " + myCar.getSpeed() +"km/h");
		
		NewCar yourCar = new NewCar("blue");
		System.out.println();
		System.out.println("yourCar의 색: " + yourCar.getColor());
		System.out.println("차의 최대 속력: " + NewCar.getMaxSpeed() + "km/h");
		
		System.out.print("첫 번째 speedUp(-100.0km/h): ");
		if(yourCar.speedUp(-100.0)) {
			System.out.print("속도 변경 가능,");
		} else {
			System.out.print("속도 변경 불가능,");
		}
		System.out.println(" 현재 차의 속력: " + yourCar.getSpeed() + "km/h");
		
		System.out.print("두 번째 speedUp(210.0km/h): ");
		if(yourCar.speedUp(210.0)) {
			System.out.print("속도 변경 가능,");
		} else {
			System.out.print("속도 변경 불가능,");
		}
		System.out.println(" 현재 차의 속력: " + yourCar.getSpeed() + "km/h");
	}

}

class Car {
	// 필드
	private double speed;
	private String color;
	private static final double MAX_SPEED = 200;

	
	// 생성자
	public Car() {
		super();
	}
	
	public Car(String color) {
		super();
		this.color = color;
	}

	// 메소드
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
			this.speed = speed;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public boolean speedUp(double speed) {
	   if(this.speed+speed < MAX_SPEED && getSpeed()+speed>0 ) {
			this.speed += speed;	
			return true;
		}else if(getSpeed()+speed >= MAX_SPEED || getSpeed()+speed<0 ) {
			this.speed = getSpeed();
			return false;
		}
		return false;
		
	}

	public static double getMaxSpeed() {
		return MAX_SPEED;
	}

	@Override
	public String toString() {
		return "Car [speed=" + speed + ", color=" + color + "]";
	}

	
	
	
}