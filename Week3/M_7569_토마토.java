package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

public class M_7569_토마토 {

	private static int m, n, h;
	private static int[][][] map;
	static Queue<int[]> queue = new ArrayDeque<>();
	static int[] dx = { 0, 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, 0, -1, 1 };
	static int[] dz = { -1, 1, 0, 0, 0, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] split = in.readLine().split(" ");
		n = Integer.parseInt(split[0]);
		m = Integer.parseInt(split[1]);
		h = Integer.parseInt(split[2]);

		map = new int[m][n][h];

		for (int l = 0; l < h; l++) {
			for (int i = 0; i < m; i++) {
				split = in.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					map[i][j][l] = Integer.parseInt(split[j]);

				}

			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int r = 0; r < h; r++) {
					if (map[i][j][r] == 1) {
						queue.offer(new int[] { i, j, r });
					}
				}
			}
		}

		tomato();
		boolean check = false;
		;
		int ans = -1;
		for (int l = 0; l < h; l++) { // 각 층을 반복
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					ans = Math.max(ans, map[i][j][l]);
					if (map[i][j][l] == 0) { // 모두 익지 못함
						check = true;
						break;
					}
				}
			}

		}

		System.out.println(check ? -1 : ans - 1); // 층 사이에 빈 줄 추가

	}

	private static void tomato() {

		while (!queue.isEmpty()) {
			int[] start = queue.poll();
			int x = start[0];
			int y = start[1];
			int z = start[2];

			for (int i = 0; i < 6; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z + dz[i];

				if (!in_range(nx, ny, nz) || map[nx][ny][nz] == -1) {
					continue;
				}

				if (map[nx][ny][nz] == 0) {
					map[nx][ny][nz] = map[x][y][z] + 1;
					queue.offer(new int[] { nx, ny, nz });
				}
			}
		}

	}

	static boolean in_range(int x, int y, int z) {
		return 0 <= x && x < m && 0 <= y && y < n && 0 <= z && z < h;
	}

}
