import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int num[];
	static int op[];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		num = new int[N]; // 숫자
		String[] split = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(split[i]);
		}

		op = new int[4]; // 연산자
		split = br.readLine().split(" ");
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(split[i]);
		}

		dfs(num[0], 1);

		System.out.println(max);
		System.out.println(min);
	}

	private static void dfs(int result, int idx) {
		
		if(idx == N) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] > 0) {
				op[i]--;
				switch (i) {
				case 0:
					dfs(result + num[idx], idx + 1);
					break;
				case 1:
					dfs(result - num[idx], idx + 1);
					break;
				case 2:
					dfs(result * num[idx], idx + 1);
					break;
				case 3:
					dfs(result / num[idx], idx + 1);
					break;
				}
				op[i]++;
			}
		}

	}
}