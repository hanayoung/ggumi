package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/* 
 * 백트래킹 문제
 * 조합으로 풀어야하나 고민했는데, dfs로 간단하게 해결이 가능했다.
 * 모든 경우를 탐색해야하므로, 완전탐색을 이용
 */
public class M_14888_연산자끼워넣기 {

	private static int[] operator;
	private static int[] nums;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());

		nums = new int[n];
		String[] split = in.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(split[i]);
		}

		operator = new int[4];
		split = in.readLine().split(" ");
		// +, -, *, /
		for (int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(split[i]);
		}

		calc(nums[0], 1); // 다음 연산 값이 nums[1] 이어야하므로, idx가 1부터 시작
		System.out.println(maxAns);
		System.out.println(minAns);

	}

	private static int maxAns = Integer.MIN_VALUE, minAns = Integer.MAX_VALUE;

	private static void calc(int num, int idx) {

		if (idx == n) {
			maxAns = Math.max(maxAns, num);
			minAns = Math.min(minAns, num);
			// System.out.println(num);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (operator[i] > 0) {
				operator[i]--;

				switch (i) {
				case 0:
					calc(num + nums[idx], idx + 1);
					break;
				case 1:
					calc(num - nums[idx], idx + 1);
					break;
				case 2:
					calc(num * nums[idx], idx + 1);
					break;
				case 3:
					calc(num / nums[idx], idx + 1);
					break;
				}

				operator[i]++;

			}
		}

	}

}
