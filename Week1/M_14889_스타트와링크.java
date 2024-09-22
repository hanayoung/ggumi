package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M_14889_스타트와링크 {

	private static int[][] map;
	private static int[] selected;
	private static int[] nums;
	private static int n;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());

		map = new int[n][n];
		nums = new int[n];
		selected = new int[n / 2];
		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		for (int i = 0; i < n; i++) {
			nums[i] = i;
		}

		comb(0, 0);
		System.out.println(diffPower);

	}

	private static int[] team2;
	private static int diffPower = Integer.MAX_VALUE;

	private static void comb(int start, int cnt) {

		if (cnt == n / 2) {

			team2 = new int[n / 2];
			int k = 0;
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					team2[k++] = i;
				}
			}
		
			int power1 = calcPower(selected);
			int power2 = calcPower(team2);

			diffPower = Math.min(diffPower, Math.abs(power1 - power2));
			return;
		}

		for (int i = start; i < n; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			selected[cnt] = nums[i];
			comb(i + 1, cnt + 1);
			visited[i] = false;

		}
	}

	private static int calcPower(int[] team) {

		int power = 0;
		for (int i = 0; i < team.length; i++) {
			for (int j = i + 1; j < team.length; j++) {
				power += map[team[i]][team[j]];
				power += map[team[j]][team[i]];
			}
		}

		return power;
	}

}
