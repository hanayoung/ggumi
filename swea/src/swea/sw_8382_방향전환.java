package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class sw_8382_방향전환 {
	static int T, x1, y1, x2, y2;
	static int dx[] = { 1, -1, 0, 0 }; // 가로, 세로
	static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("8382.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken()) + 100;
			y1 = Integer.parseInt(st.nextToken()) + 100;
			x2 = Integer.parseInt(st.nextToken()) + 100;
			y2 = Integer.parseInt(st.nextToken()) + 100;
			
			System.out.println("#"+ tc +" " + bfs(x1,y1));
		}
	}

	private static int bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[201][201][2];

		queue.add(new int[] { x1, y1, 0, 0 });
		visited[y1][x1][0] = true; // x1,y1출발,가로0

		queue.add(new int[] { x1, y1, 1, 0 });
		visited[y1][x1][1] = true; // x1, y1출발, 세로1

		while (!queue.isEmpty()) {
			int current[] = queue.poll();
			int px = current[0];
			int py = current[1];
			int dir = current[2];
			int move = current[3];

			if (px == x2 && py == y2) {
				return move;
			}

			// 가로 -> 세로로 움직여야함
			if (dir == 0) {
				for (int i = 2; i < 4; i++) {
					int nx = px + dx[i];
					int ny = py + dy[i];

					if (nx < 0 || ny < 0 || nx >= 201 || ny >= 201 || visited[ny][nx][1]) {
						continue;
					}
					queue.add(new int[] { nx, ny, 1, move + 1 });
					visited[ny][nx][1] = true;
				}
			}
			if (dir == 1) {
				for (int i = 0; i < 2; i++) {
					int nx = px + dx[i];
					int ny = py + dy[i];

					if (nx < 0 || ny < 0 || nx >= 201 || ny >= 201 || visited[ny][nx][0]) {
						continue;
					}
					queue.add(new int[] { nx, ny, 0, move + 1 });
					visited[ny][nx][0] = true;
				}
			}
		}
		return -1;

	}
}
