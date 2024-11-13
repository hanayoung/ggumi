package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_원숭이원숭이 {
	static int monkeyX[] = { 0, 0, -1, 1 };
	static int monkeyY[] = { 1, -1, 0, 0 };
	static int horseX[] = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int horseY[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int K, N, W, H;
	static int arr[][];
	static boolean visited[][][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine()); // 말처럼 이동가능 횟수
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		visited = new boolean[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs(0, 0));

	}

	private static int bfs(int y, int x) {
		Queue<int[]> queue = new LinkedList<>(); // 큐 만들고
		queue.add(new int[] { y, x, 0, 0 }); // 시작점 큐에 넣고
		visited[y][x][0] = true; // 방문처리 해주고

		while (!queue.isEmpty()) {
			int current[] = queue.poll();
			int py = current[0];
			int px = current[1];
			int cnt = current[2];
			int k = current[3];

			if (px == W - 1 && py == H - 1) {
				return cnt;
			}

			// 원숭이 이동 -> 4방향
			for (int i = 0; i < 4; i++) {
				int ny = py + monkeyY[i];
				int nx = px + monkeyX[i];

				if (ny < 0 || nx < 0 || ny >= H || nx >= W || visited[ny][nx][k] || arr[ny][nx] == 1) {
					continue;
				}
				if (arr[ny][nx] == 0 && !visited[ny][nx][k]) {
					queue.add(new int[] { ny, nx, cnt + 1, k });
					visited[ny][nx][k] = true;
				}

			}
			// 말처럼 이동 -> 8방향
			if (k < K) {
				for (int i = 0; i < 8; i++) {
					int ny = py + horseY[i];
					int nx = px + horseX[i];

					if (ny < 0 || nx < 0 || ny >= H || nx >= W || visited[ny][nx][k + 1] || arr[ny][nx] == 1) {
						continue;
					}
					if (arr[ny][nx] == 0 && !visited[ny][nx][k + 1]) {
						queue.add(new int[] { ny, nx, cnt + 1, k + 1 });
						visited[ny][nx][k + 1] = true;
					}
				}
			}

		}
		return -1;
	}
}
