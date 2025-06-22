package baekjoon.Quiz11664;

import java.util.*;

/*
Gold4: 선분과 점 / [math]
*/
public class Quiz11664 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		double Ax = sc.nextDouble();
		double Ay = sc.nextDouble();
		double Az = sc.nextDouble();
		double Bx = sc.nextDouble();
		double By = sc.nextDouble();
		double Bz = sc.nextDouble();
		double Cx = sc.nextDouble();
		double Cy = sc.nextDouble();
		double Cz = sc.nextDouble();

		sc.close();

		double ABx = Bx - Ax;
		double ABy = By - Ay;
		double ABz = Bz - Az;

		double ACx = Cx - Ax;
		double ACy = Cy - Ay;
		double ACz = Cz - Az;

		double lenSqAB = ABx * ABx + ABy * ABy + ABz * ABz;
		double distance;
		if (lenSqAB == 0) {
			distance = Math.sqrt(ACx * ACx + ACy * ACy + ACz * ACz);
		} else {
			double dot = (ACx * ABx) + (ACy * ABy) + (ACz * ABz);
			double t = dot / lenSqAB;

			if (t < 0) {
				distance = Math.sqrt(ACx * ACx + ACy * ACy + ACz * ACz);
			} else if (t > 1) {
				double BCx = Cx - Bx;
				double BCy = Cy - By;
				double BCz = Cz - Bz;
				distance = Math.sqrt(BCx * BCx + BCy * BCy + BCz * BCz);
			} else {
				double Hx = Ax + t * ABx;
				double Hy = Ay + t * ABy;
				double Hz = Az + t * ABz;

				distance = Math.sqrt(Math.pow(Cx - Hx, 2) + Math.pow(Cy - Hy, 2) + Math.pow(Cz - Hz, 2));
			}
		}

		System.out.printf("%.10f\n", distance);
	}
}
