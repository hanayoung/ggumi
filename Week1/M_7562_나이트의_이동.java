package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
import javax.swing.text.AsyncBoxView;

/*
 bfs 풀이
 한번 갔던 곳을 방문해도 되지 않을까 해서 visited를 쓰지 않았는데,
 가장 최소 경로를 찾기때문에 최적 경로를 찾기 위해 visited를 사용하는 것이 효율적인 것 같다.
 */
public class M_7562_나이트의_이동 {

	private static int[][] visited;
	private static int l, ox, oy;
	private static Queue<int[]> queue;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {

			st = new StringTokenizer(in.readLine());
			l = Integer.parseInt(st.nextToken());

			visited = new int[l][l];

			String[] split = in.readLine().split(" ");
			int sx = Integer.parseInt(split[0]);
			int sy = Integer.parseInt(split[1]);

			split = in.readLine().split(" ");
			ox = Integer.parseInt(split[0]);
			oy = Integer.parseInt(split[1]);

			queue = new ArrayDeque<int[]>();
			queue.offer(new int[] { sx, sy });
			visited[sx][sy] = 1;
			int ans = move(sx, sy);
			System.out.println(ans);
		}

	}

	private static int[] dx = { -2, -1, 2, 1, 2, 1, -1, -2 };
	private static int[] dy = { 1, 2, 1, 2, -1, -2, -2, -1 };
	private static int cnt;

	private static int move(int x, int y) {

		queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y });

		while (!queue.isEmpty()) {
			int[] tmp = queue.poll();
			x = tmp[0];
			y = tmp[1];
			cnt++;

			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (x == ox && y == oy) {
					return visited[x][y] - 1;
				}

				if (nx < 0 || ny < 0 || nx >= l || ny >= l) {
					continue;
				}

				if (visited[nx][ny] == 0) {
					visited[nx][ny] = visited[x][y] + 1;
					queue.offer(new int[] { nx, ny });
				}
			}
		}
		return -1;

	}

}
