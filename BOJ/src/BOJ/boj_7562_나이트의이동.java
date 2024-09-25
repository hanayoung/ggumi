
package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_7562_나이트의이동 {

	static int l;
	static int chess[][];

	static int dx[] = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int dy[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int cnt;
	static int startX, startY, arriveX, arriveY;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			l = Integer.parseInt(br.readLine());
			chess = new int[l][l];

			StringTokenizer st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			

			st = new StringTokenizer(br.readLine());
			arriveX = Integer.parseInt(st.nextToken());
			arriveY = Integer.parseInt(st.nextToken());
			

			System.out.println(bfs(startX, startY));

		}
	}

	private static int bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[l][l];

		queue.offer(new int[] { startX, startY, 0 });
		visited[startX][startY] = true;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int nowX = current[0];
			int nowY = current[1];
			int move = current[2];

			if (nowX == arriveX && nowY == arriveY) {
				return move;
			}

			for (int i = 0; i < 8; i++) {
				int nx = nowX + dx[i];
				int ny = nowY + dy[i];

				if (nx < 0 || ny < 0 || nx >= l || ny >= l || visited[nx][ny]) {
					continue;
				}
				
				queue.offer(new int[] {nx, ny, move + 1});
				visited[nx][ny] = true;
				
			}
		}
		
		return -1;

	}

}
