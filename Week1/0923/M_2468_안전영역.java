package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
 * 물에 잠긴 높이를 배열의 최솟값부터 최댓값까지 모두 비교한다.
 * dfs를 통해 영역의 개수를 구함
 */
public class M_2468_안전영역 {

	static int[][] map;
	static int[][] check;
	static int n;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		map = new int[n][n];
		check = new int[n][n];

		int min = Integer.MAX_VALUE, max = -1;
		for (int i = 0; i < n; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(split[j]);
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
			}
		}

		List<Integer> ansList = new ArrayList<Integer>();
		for (int k = max; k >= min; k--) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] >= k) {
						check[i][j] = 1;
					}
				}
			}

			int ans = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (dfs(i, j)) {
						ans++;
					}
				}
			}

			ansList.add(ans);
		}

		Collections.sort(ansList);
		System.out.println(ansList.get(ansList.size() - 1));

	}

	private static boolean dfs(int x, int y) {
		if (x < 0 || y < 0 || x >= n || y >= n) {
			return false;
		}
		if (check[x][y] == 1) {
			check[x][y] = 0;
			dfs(x + 1, y);
			dfs(x - 1, y);
			dfs(x, y - 1);
			dfs(x, y + 1);
			return true;
		}
		return false;

	}

}
