package swea.d2;

import java.util.*;

public class Quiz1940 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			Car rcCar = new Car();
			int N = sc.nextInt();
			for(int i=0; i<N; i++) {
				int type = sc.nextInt();
				int accelValue = 0;
				if(type != 0) accelValue = sc.nextInt();
				moveCar(rcCar, type, accelValue);
			}
			System.out.println("#"+test_case+" "+rcCar.getTotalDistance());
		}
	}
	private static void moveCar(Car car, int type, int value) {
		if(type == 1) {
			car.increaseSpeed(value);
			car.addDistance(1);
		} else if(type ==2) {
			car.decreaseSpeed(value);
			car.addDistance(1);
		} else car.addDistance(1);
	}
}
class Car {
	private int speed;
	private int totalDistance;

	public Car() {
		this.speed = 0;
		this.totalDistance = 0;
	}

	public void increaseSpeed(int value) {
		this.speed += value;
	}

	public void decreaseSpeed(int value) {
		if(this.speed - value >= 0) this.speed -= value;
	}

	public void addDistance(int sec) {
		this.totalDistance += (speed * sec);
	}

	public int getTotalDistance() {
		return this.totalDistance;
	}
}
