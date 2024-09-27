package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * dfs로 풀이했을 때, 정답인지는 모르지만 시간초과 발생
 * 완전 탐색이므로, 시간 복잡도 O(2^(n+n)) ??
 */

// 경로 개수가 (2^63 - 1)이하여서 dp를 long으로 선언해야 한다.
public class M_1890_점프 {

	private static int n;
	private static int[][] map;
	private static long[][] dp;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		map = new int[n][n];
		dp = new long[n][n];

		for (int i = 0; i < n; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		dp[0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == n - 1 && j == n - 1) {
					break;
				}

				int jump = map[i][j]; // 현재 칸에서의 점프 값
				if (jump == 0) { // 점프가 0인 경우, 넘어감
					continue;
				}

				// 오른쪽으로 점프
				if (j + jump < n) {
					dp[i][j + jump] += dp[i][j];
				}

				// 아래로 점프
				if (i + jump < n) {
					dp[i + jump][j] += dp[i][j];
				}
			}
		}

		System.out.println(dp[n - 1][n - 1]);

	}

}
