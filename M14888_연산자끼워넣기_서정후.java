import java.util.*;

public class M14888_연산자끼워넣기_서정후 {
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] numbers = new int[N];
		int[] cal = new int[4];

		for (int i = 0; i < N; i++) {
			numbers[i] = sc.nextInt();
		}

		for (int i = 0; i < 4; i++) {
			cal[i] = sc.nextInt();
		}

		find(1, numbers[0], N, cal, numbers);

		System.out.println(max);
		System.out.println(min);
	}

	public static void find(int dp, int current, int N, int[] cal, int[] numbers) {
		if(dp == N) {
			max = Math.max(max, current);
			min = Math.min(min, current);
			return;
		}
		
		if(cal[0] > 0) {
			cal[0]--;
			find(dp + 1, current + numbers[dp], N, cal, numbers);
			cal[0]++;
		}
		
		if(cal[1] > 0) {
			cal[1]--;
			find(dp + 1, current - numbers[dp], N, cal, numbers);
			cal[1]++;
		}
		
		if(cal[2] > 0) {
			cal[2]--;
			find(dp + 1, current * numbers[dp], N, cal, numbers);
			cal[2]++;
		}
		
		if(cal[3] > 0) {
			cal[3]--;
			find(dp + 1, current / numbers[dp], N, cal, numbers);
			cal[3]++;
		}
	}
}
