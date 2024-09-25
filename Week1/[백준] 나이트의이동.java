import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class test25 {

	static int I, curX, curY, goalX, goalY;
	static boolean[][] visited;

	static int result;
	static int INF = Integer.MAX_VALUE;

	static int[] dx = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] dy = { -2, -1, 1, 2, 2, 1, -1, -2 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case < T + 1; test_case++) {
			I = Integer.parseInt(br.readLine()); // 체스판의 크기
			visited = new boolean[I][I]; // 방문 여부 배열 초기화

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			curX = Integer.parseInt(st.nextToken()); // 현재 위치 X
			curY = Integer.parseInt(st.nextToken()); // 현재 위치 Y

			st = new StringTokenizer(br.readLine(), " ");
			goalX = Integer.parseInt(st.nextToken()); // 목표 위치 X
			goalY = Integer.parseInt(st.nextToken()); // 목표 위치 Y

			result = bfs(curX, curY);

			System.out.println(result); // 결과 출력
		}

	}

	private static int bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y, 0 });
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int curX = temp[0];
			int curY = temp[1];
			int cnt = temp[2];

			// 목표 위치에 도착한 경우
			if (curX == goalX && curY == goalY) {
				return cnt; // 횟수를 반환
			}

			// 8방향 이동 탐색
			for (int i = 0; i < 8; i++) {
				int nextX = curX + dx[i];
				int nextY = curY + dy[i];

				// 체스판 범위 내에 있을 경우
				if (nextX >= 0 && nextX < I && nextY >= 0 && nextY < I) {
					// 방문하지 않은 곳이면 이동
					if (!visited[nextX][nextY]) {
						visited[nextX][nextY] = true; // 방문 처리
						queue.offer(new int[] { nextX, nextY, cnt + 1 });
					}
				}
			}
		}

		// 목표 위치에 도달하지 못했을 경우
		return 0;
	}
}