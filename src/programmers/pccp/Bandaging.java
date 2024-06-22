package programmers.pccp;

public class Bandaging {
	public static void main(String[] args) {
		int[] bandage = {5, 1, 5};
		int[][] attacks = {{2, 10}, {9, 15}, {10, 5}, {11, 5}};
		long result = solution(bandage, 30, attacks); // 5
		System.out.println(result);
	}
	public static int solution(int[] bandage, int health, int[][] attacks) {
		int maxHealth = health;
		int lastWave = attacks[attacks.length-1][0];
		int[] damage = new int[lastWave+1];
		for(int[] attack : attacks) {
			damage[attack[0]] = attack[1];
		}
		int tryCount = 0;
		int recovery = bandage[1];
		for(int i=1; i<=lastWave; i++) {
			if(damage[i] == 0) { // 공격 x
				tryCount++;
				if(tryCount == bandage[0]) {
					recovery += bandage[2];
					tryCount = 0;
				}
				health += recovery;
				if(recovery > bandage[1]) recovery = bandage[1];
				if(health > maxHealth) health = maxHealth;
			} else { // 공격 o
				health -= damage[i];
				if(health <= 0) return -1;
				tryCount = 0;
			}
		}
		return health;
	}
}
