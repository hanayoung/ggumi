import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int maxH = Integer.MIN_VALUE;
	static int minH = Integer.MAX_VALUE;
	static boolean[][] visited;
	static int cnt;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int[][] map;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] split = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(split[j]);

				if (maxH < map[i][j])
					maxH = map[i][j];
				if (minH > map[i][j])
					minH = map[i][j];
			}
		}

		int answer = 1; // 비가 내리지 않는 경우

		for (int k = minH; k < maxH; k++) {
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && map[i][j] > k) {
						cnt++;
						bfs(i, j, k);
					}
				}
			}
			if (answer < cnt)
				answer = cnt;
			cnt = 0;
		}

		System.out.println(answer);

	}

	private static void bfs(int x, int y, int low) {

		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y });
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int curX = temp[0];
			int curY = temp[1];

			for (int i = 0; i < 4; i++) {
				int nextX = curX + dx[i];
				int nextY = curY + dy[i];

				if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N)
					continue;

				if (!visited[nextX][nextY] && map[nextX][nextY] > low) {
					visited[nextX][nextY] = true;
					queue.offer(new int[] { nextX, nextY });
				}

			}
		}

	}
}