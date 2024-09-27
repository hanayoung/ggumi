package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 반시계 방향은 (d + 3) % 4
 2가지로 나눠서 풀이
 1. 빈칸이 없을 때(map[i][j] = 1), 방향 유지하고 한칸 후진
 2. 빈칸이 있을 때(map[i][j] = 0), 반시계 방향으로 회전 후 이동
 */
public class M_14503_로봇청소기 {

	private static int n, m;
	private static int[][] map;
	private static int[][] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] split = in.readLine().split(" ");

		n = Integer.parseInt(split[0]);
		m = Integer.parseInt(split[1]);

		map = new int[n][m];
		visited = new int[n][m];
		split = in.readLine().split(" ");
		int r = Integer.parseInt(split[0]);
		int c = Integer.parseInt(split[1]);
		int d = Integer.parseInt(split[2]);
		// 0: 북쪽, 1: 동쪽, 2: 남쪽, 3: 서쪽

		for (int i = 0; i < n; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		visited[r][c] = 1;
		move(r, c, d);
		System.out.println(cnt);

	}

	// 북동남서(상우하좌) -> 서남동북
	private static int[] dx = { -1, 0, 1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };
	private static int cnt = 1;

	private static void move(int x, int y, int d) {

		while (true) {
			boolean moved = false;

			for (int i = 0; i < 4; i++) {

				d = (d + 3) % 4; // 반시계로 회전
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
					continue;
				}

				// 빈 칸일때, 반시계 방향으로 회전 후 앞으로 전진
				if (map[nx][ny] == 0 && visited[nx][ny] == 0) {
					visited[nx][ny] = 1;
					moved = true;
					cnt++;
					x = nx;
					y = ny;
					break;
				}
			}

			if (!moved) { // 네 방향 모두 청소되어 있거나, 벽인 경우 후진
				// 후진할 곳이 벽인 경우, 작동 종료
				if (map[x - dx[d]][y - dy[d]] == 1) {
					break;
				}
				else {  // 후진할 곳이 벽이 아닌 경우, 현재 방향 유지하면서 한칸 후진
					x -= dx[d];
					y -= dy[d];
				}

			}
		}

	}

}
