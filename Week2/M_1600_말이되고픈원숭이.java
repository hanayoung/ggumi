package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class M_1600_말이되고픈원숭이 {

	private static int k, w, h;
	private static int[][] map;
	private static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		k = Integer.parseInt(in.readLine());
		String[] split = in.readLine().split(" ");
		w = Integer.parseInt(split[0]);
		h = Integer.parseInt(split[1]);

		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		visited = new boolean[h][w][k + 1]; // 말 점프 횟수를 고려한 방문 체크
		int ans = jump(0, 0, 0, 0);
		System.out.println(ans);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] hx = { 1, 1, 2, 2, -1, -1, -2, -2 };
	static int[] hy = { -2, 2, -1, 1, -2, 2, -1, 1 };

	static int jump(int x, int y, int hJump, int mJump) {

		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { x, y, hJump, mJump });
		visited[x][y][0] = true;

		while (!queue.isEmpty()) {

			int[] tmp = queue.poll();
			x = tmp[0];
			y = tmp[1];
			hJump = tmp[2];
			mJump = tmp[3];

			if (x == h - 1 && y == w - 1) {
				return mJump;
			}

			if (k > hJump) {

				for (int i = 0; i < 8; i++) {
					int nx = x + hx[i];
					int ny = y + hy[i];

					if (!isRange(nx, ny)) {
						continue;
					}

					if (!visited[nx][ny][hJump + 1] && map[nx][ny] == 0) {
						visited[nx][ny][hJump + 1] = true;
						queue.offer(new int[] { nx, ny, hJump + 1, mJump + 1 });
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (!isRange(nx, ny)) {
					continue;
				}

				if (!visited[nx][ny][hJump] && map[nx][ny] == 0) {
					visited[nx][ny][hJump] = true;
					queue.offer(new int[] { nx, ny, hJump, mJump + 1 });
				}
			}
		}

		return -1;
	}

	static boolean isRange(int x, int y) {
		return x >= 0 && y >= 0 && x < h && y < w;
	}
}
