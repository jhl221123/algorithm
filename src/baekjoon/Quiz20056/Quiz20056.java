package baekjoon.Quiz20056;

import java.util.*;
import java.io.*;

/*
1. FireballHolder를 이차원 배열로 관리하며, 아래와 같은 역할을 수행한다.
1-1. 해당 격자에 Fireball이 두 개 이상인지 확인할 수 있다.
1-2. 해당 격자에 Fireball 질량 합을 구할 수 있다.
1-3. 모든 Fireball을 합친 후, 네 개의 Fireball로 분해할 수 있다.
1-3-1. 모든 Fireball의 질량을 합한 후, 5로 나눈다.
1-3-2. 만약 질량이 0이라면 해당 격자에는 어떠한 Fireball도 남지 않는다.
1-3-3. 모든 Fireball의 속력을 합한 후, Fireball 개수로 나눈다.
1-3-4. 모든 Fireball 방향을 확인한 후, 조건에 맞는 방향을 부여한다.
2. Fireball은 아래와 같은 역할을 수행한다.
2-1. 방향과 속력을 이용해서 현재 좌표를 변경한다.
2-2. 방향에 따라 서로 다른 파라미터를 제공하는 메서드, 주어진 격자 내에서 이동하는 메서드를 활용한다.
3. FireballHolder는 내부적으로 두 개의 대기열을 가지며, 명령을 수행할 때마다 번갈아가며 사용한다.
3-1. 명령을 수행시 모든 좌표를 순회하며 Fireball 위치를 변경하고, 해당 좌표의 FireballHolder에 추가한다.
*/
public class Quiz20056 {

	private static final int BASE_TYPE = 1;

	private static int N, M, K;
	private static FireballHolder[][] map;

	public static void main(String[] args) throws IOException {
		initialize();
		System.out.println(sumTotalWeight(blastFireballs()));
	}

	private static void initialize() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new FireballHolder[N][N];
		for(int row=0; row<N; row++) {
			for(int col=0; col<N; col++) {
				map[row][col] = new FireballHolder(row, col);
			}
		}

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());

			map[row][col].addFireball(new Fireball(row, col, weight, speed, direction), BASE_TYPE);
		}
	}

	private static int sumTotalWeight(int currentType) {
		int totalWeight = 0;

		for(int row=0; row<N; row++) {
			for(int col=0; col<N; col++) {
				totalWeight += map[row][col].sumWeight(currentType);
			}
		}

		return totalWeight;
	}

	private static int blastFireballs() {
		int currentType = BASE_TYPE;

		while(K-- > 0) {
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					FireballHolder holder = map[row][col];

					if(holder.countFireballs(currentType) == 0) {
						continue;
					}

					// 1. 파이어볼 좌표 변경
					holder.moveFireballs(currentType, N);

					// 2. 파이어볼 좌표 이동
					ArrayDeque<Fireball> fireballs = holder.getFireballs(currentType);
					int size = fireballs.size();
					for(int i = 0; i< size; i++) {
						Fireball fireball = fireballs.removeFirst();
						map[fireball.getRow()][fireball.getCol()].addFireball(fireball, currentType * -1);
					}
				}
			}

			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					FireballHolder holder = map[row][col];

					if(holder.countFireballs(currentType * -1) >= 2) {
						holder.integrate(currentType * -1);
					}
				}
			}

			currentType *= -1;
		}

		return currentType;
	}

	static class Fireball {
		private int row;
		private int col;
		private int weight;
		private int speed;
		private int direction;

		public Fireball(int r, int c, int w, int s, int d) {
			this.row = r;
			this.col = c;
			this.weight = w;
			this.speed = s;
			this.direction = d;
		}

		public void move(int N) {
			switch(this.direction) {
				case 0:
					changePoint(-1, 0, this.speed % N, N);
					break;
				case 1:
					changePoint(-1, 1, this.speed, N);
					break;
				case 2:
					changePoint(0, 1, this.speed % N, N);
					break;
				case 3:
					changePoint(1, 1, this.speed, N);
					break;
				case 4:
					changePoint(1, 0, this.speed % N, N);
					break;
				case 5:
					changePoint(1, -1, this.speed, N);
					break;
				case 6:
					changePoint(0, -1, this.speed % N, N);
					break;
				case 7: changePoint(-1, -1, this.speed, N);
			}
		}

		private void changePoint(int rowDelta, int colDelta, int iter, int N) {
			this.row = (this.row + (iter * rowDelta) % N + N) % N;
			this.col = (this.col + (iter * colDelta) % N + N) % N;
		}

		public int getRow() {
			return this.row;
		}

		public int getCol() {
			return this.col;
		}

		public int getWeight() {
			return this.weight;
		}

		public int getSpeed() {
			return this.speed;
		}

		public int getDirection() {
			return this.direction;
		}
	}

	static class FireballHolder {
		private int row;
		private int col;
		private ArrayDeque<Fireball> queueA;
		private ArrayDeque<Fireball> queueB;

		public FireballHolder(int r, int c) {
			this.row = r;
			this.col = c;
			this.queueA = new ArrayDeque<>();
			this.queueB = new ArrayDeque<>();
		}

		public void addFireball(Fireball fireball, int type) {
			if(type == BASE_TYPE) {
				queueA.addLast(fireball);
			} else {
				queueB.addLast(fireball);
			}
		}

		public void moveFireballs(int type, int N) {
			if(type == BASE_TYPE) {
				for(int i=0; i<queueA.size(); i++) {
					Fireball fireball = queueA.removeFirst();
					fireball.move(N);
					queueA.addLast(fireball);
				}
			} else {
				for(int i=0; i<queueB.size(); i++) {
					Fireball fireball = queueB.removeFirst();
					fireball.move(N);
					queueB.addLast(fireball);
				}
			}
		}

		public ArrayDeque<Fireball> getFireballs(int type) {
			if(type == BASE_TYPE) {
				return this.queueA;
			}

			return this.queueB;
		}

		public int countFireballs(int type) {
			if(type == BASE_TYPE) {
				return queueA.size();
			}

			return queueB.size();
		}

		public int sumWeight(int type) {
			if(type == BASE_TYPE) {
				return calculateTotalWeight(this.queueA);
			}

			return calculateTotalWeight(this.queueB);
		}

		public void integrate(int type) {
			if(type == BASE_TYPE) {
				integrateAndDivide(this.queueA);
			}

			integrateAndDivide(this.queueB);
		}

		private int calculateTotalWeight(ArrayDeque<Fireball> queue) {
			int size = queue.size();
			int sum = 0;

			for(int i=0; i<size; i++) {
				Fireball fireball = queue.removeFirst();
				sum += fireball.getWeight();
				queue.addLast(fireball);
			}

			return sum;
		}

		private void integrateAndDivide(ArrayDeque<Fireball> queue) {
			int size = queue.size();
			int totalWeight = 0;
			int totalSpeed = 0;
			int[] directionFlag = new int[2];

			for(int i=0; i<size; i++) {
				Fireball fireball = queue.removeFirst();
				totalWeight += fireball.getWeight();
				totalSpeed += fireball.getSpeed();
				directionFlag[fireball.getDirection() % 2]++;
			}

			int weight = totalWeight / 5;
			if(weight != 0) {
				int speed = totalSpeed / size;

				if(directionFlag[0] == size || directionFlag[0] == 0) {
					for(int d=0; d<=6; d+=2) {
						queue.addLast(new Fireball(this.row, this.col, weight, speed, d));
					}
				} else {
					for(int d=1; d<=7; d+=2) {
						queue.addLast(new Fireball(this.row, this.col, weight, speed, d));
					}
				}
			}
		}
	}
}
