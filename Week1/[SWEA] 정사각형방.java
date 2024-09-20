import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static int N;
	static int map[][];
	static boolean visited[][];

	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case < T + 1; test_case++) {
			sb.append("#" + test_case + " ");

			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;

			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				String[] split = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int result = bfs(i, j, 1);
					
					for(boolean[] v : visited) {
						Arrays.fill(v, false);
					}
					
					if (max < result) {
						max = result;
						min = map[i][j];
					}
					if (max == result && map[i][j] < min) {
						min = map[i][j];
					}
				}
			}

			sb.append(min + " " + max + "\n");
		}

		System.out.println(sb);
	}

	private static int bfs(int x, int y, int result) {

		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y, result });
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			int temp[] = queue.poll();
			int curX = temp[0];
			int curY = temp[1];
			int res = temp[2];

			for (int i = 0; i < 4; i++) {
				int nextX = curX + dx[i];
				int nextY = curY + dy[i];

				if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N)
					continue;

				if (!visited[nextX][nextY] && map[curX][curY] + 1 == map[nextX][nextY]) {
					visited[nextX][nextY] = true;
					result = res + 1;
					queue.offer(new int[] { nextX, nextY, res + 1 });
				}
			}

		}

		return result;
	}

}