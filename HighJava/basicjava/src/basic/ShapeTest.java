package basic;

public class ShapeTest {
	public static void main(String[] args) {
		Shape[] shapes = new Shape[3];
		shapes[0] = new Circle(1.0);
		shapes[1] = new Triangle(2.0);
		shapes[2] = new Rectangle(2.0, 3.0);
		for (Shape shape : shapes) {
			System.out.println(shape);
		}
		
	}
}

class Shape{
	public Shape() {
		super();
	} 
	
	public double area() {
		return 0.0;
	} 
	public double perimeter() {
		return 0.0;
	}
}

class Rectangle extends Shape {
	private double width;
	private double height;
	
	public Rectangle(double width, double height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	public double area() {
		return height*width;
		
	}
	
	public double perimeter() {
		return (height+width)*2;
		
	}

	@Override
	public String toString() {
		return "사각형의 넓이: " + area() +"사각형의 둘레: " + perimeter();
	}
}

class Triangle extends Shape {
	private double side;

	public Triangle(double side) {
		super();
		this.side = side;
	}
	
	public double area() {
		return  Math.sqrt(3)/4 *side*side;
		
	}
	
	public double perimeter() {
		return side*3;
		
	}

	@Override
	public String toString() {
		return "정삼각형의 넓이: " + area() +"정삼각형의 둘레: " + perimeter();
	}
	
}

class Circle extends Shape {
	private double radius;

	public Circle(double radius) {
		super();
		this.radius = radius;
	}
	
	public double area() {
		return  radius*radius*Math.PI;
		
	}
	
	public double perimeter() {
		return 2*radius*Math.PI;
		
	}

	@Override
	public String toString() {
		return "원의 넓이: " + area() +"원의 둘레: " + perimeter();
	}
}