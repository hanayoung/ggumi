package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

class M_2589_보물섬 {

	private static int t;
	static String[][] map;
	static int[][] visited;
	static int R, C;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] split = in.readLine().split(" ");

		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);

		map = new String[R][C];

		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			split = in.readLine().split("");
			for (int j = 0; j < C; j++) {
				map[i][j] = split[j];
				if (map[i][j].equals("L")) {
					queue.offer(new int[] { i, j });
				}
			}
		}

		int ans = -1;
		while (!queue.isEmpty()) {

			int[] curr = queue.poll();

			visited = new int[R][C];
			visited[curr[0]][curr[1]] = 1;
			bfs(curr[0], curr[1]);

		}

		System.out.println(max - 1);

	}

	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int max = -1;

	private static void bfs(int x, int y) {

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { x, y });

		while (!q.isEmpty()) {

			int[] tmp = q.poll();
			x = tmp[0];
			y = tmp[1];
			for (int i = 0; i < 4; i++) {

				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}

				if (visited[nx][ny] == 0 && map[nx][ny].equals("L")) {

					visited[nx][ny] = visited[x][y] + 1;
					q.offer(new int[] { nx, ny });
					if (max < visited[nx][ny]) {
						max = visited[nx][ny];
					}
				}
			}
		}

	}

}
