package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 대각선으로 이동 시, 오른쪽과 아래가 모두 0이어야 이동이 가능하다!! 
public class M_17070_파이프옮기기1 {

	private static int n;
	private static int[][] map;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(in.readLine());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		ans = 0;
		pipe(0, 1, 0);
		System.out.println(ans);
	}

	// 오른쪽(0, 1), 오른쪽 아래(1, 1), 아래(1, 0)
	private static int ans;

	// 약
	private static void pipe(int x, int y, int dir) {

		if (x >= n || y >= n || map[x][y] == 1) {
			return;
		}

		// 대각선 이동시, 이동이후 위치에서 (위, 왼쪽)이 1이면 return
		if (dir == 2) {
			if (((y - 1 >= 0) && map[x][y - 1] == 1) || ((x - 1 >= 0) && map[x - 1][y] == 1))
				return;
		}

		if (x == n - 1 && y == n - 1) {
			ans++;
			return;
		}

		// 0: 가로, 1: 세로, 2: 대각선
		if (dir == 0) { // 가로 -> 오른쪽, 오른쪽 아래
			pipe(x, y + 1, 0);
			pipe(x + 1, y + 1, 2);

		} else if (dir == 1) { // 세로 -> 오른아래, 아래
			pipe(x + 1, y + 1, 2);
			pipe(x + 1, y, 1);

		} else if (dir == 2) {
			pipe(x + 1, y, 1);
			pipe(x + 1, y + 1, 2);
			pipe(x, y + 1, 0);
		}
	}

}
