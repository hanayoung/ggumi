package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M_14501_퇴사 {

	private static int N;
	private static int[][] counsel;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		counsel = new int[N][2];
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			int t = Integer.parseInt(split[0]);
			int p = Integer.parseInt(split[1]);
			counsel[i][0] = t; // 일수
			counsel[i][1] = p; // 비용
		}

		dfs(0, 0);
		System.out.println(max);

	}

	static int max = -1;

	private static void dfs(int day, int profit) {

		if (day >= N) {

			max = Math.max(max, profit);
			return;
		}

		// 현재 날짜에 상담 하지 않음
		dfs(day + 1, profit);

		if (day + counsel[day][0] <= N) {
			dfs(day + counsel[day][0], profit + counsel[day][1]);
		}

	}

}
