import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {
	static int squares[][];
	static int map[][];
	static boolean visited[][];
	static int M, N, K;
	static int ans[];

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	static int sum;
	
	static List<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");

		M = Integer.parseInt(split[0]);
		N = Integer.parseInt(split[1]);
		K = Integer.parseInt(split[2]);

		squares = new int[K][4];
		map = new int[M][N];
		visited = new boolean[M][N];
		list = new ArrayList<>();

		// 좌표 입력받기 : 왼쪽 아래, 오른쪽 위
		for (int k = 0; k < K; k++) {
			split = br.readLine().split(" ");
			squares[k][0] = Integer.parseInt(split[0]); // 왼쪽 아래 x
			squares[k][1] = Integer.parseInt(split[1]); // 왼쪽 아래 y
			squares[k][2] = Integer.parseInt(split[2]); // 오른쪽 위 x
			squares[k][3] = Integer.parseInt(split[3]); // 오른쪽 위 y
			for (int i = squares[k][0]; i < squares[k][2]; i++) { // y범위
				for (int j = squares[k][1]; j < squares[k][3]; j++) { // x범위
					map[j][i] = 1;
				}
			}

		}
		int cnt = 0;
		int num = 0;

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 0) {
					cnt++;
					bfs(i, j);
				}
			}
		}
		
		ans = new int[cnt];
		
		for(int i = 0; i < cnt; i++) {
			ans[i] = list.get(i);
		}
		
		Arrays.sort(ans);
		
		System.out.println(cnt);
		
		for(int a : ans) {
			System.out.print(a + " ");
		}
	}

	private static void bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y });
		visited[x][y] = true;
		sum = 0;

		while (!queue.isEmpty()) {
			int temp[] = queue.poll();
			int curX = temp[0];
			int curY = temp[1];
			sum++;
			for (int i = 0; i < 4; i++) {

				int nextX = curX + dx[i];
				int nextY = curY + dy[i];

				if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N)
					continue;
				if (!visited[nextX][nextY] && map[nextX][nextY] == 0) {
					visited[nextX][nextY] = true;
					queue.offer(new int[] { nextX, nextY });
				}
			}
		}
		list.add(sum);

	}

}