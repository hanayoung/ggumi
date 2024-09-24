import java.io.*;
import java.util.*;


public class S1249_보급로 {
	static int[] nx = { -1, 1, 0, 0 };
	static int[] ny = { 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= tc; test_case++) {
            int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			int[][] memo = new int[N][N];
			boolean[][] visited = new boolean[N][N]; // 방문 배열 추가

			for (int i = 0; i < N; i++) {
                String line = br.readLine();

				for (int j = 0; j < N; j++) {
                    arr[i][j] = line.charAt(j) - '0';
					memo[i][j] = Integer.MAX_VALUE; // 스택 오버플로우 발생해서 추가함
				}
			}

			int result = dijkstra(N, arr, memo, visited);

			System.out.println("#" + test_case + " " + result);
		}

	}

	public static int dijkstra(int N, int[][] arr, int[][] memo, boolean[][] visited) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2])); // 비용이 작은 것부터 처리
		pq.offer(new int[] { 0, 0, arr[0][0] });
		memo[0][0] = arr[0][0];

		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			int x = current[0];
			int y = current[1];
			int cost = current[2];
			
			if (visited[x][y]) { // 이미 방문한 경우 스킵
                continue;
            }

            visited[x][y] = true; // 방문 처리

			if (memo[x][y] < cost) { // 메모리 초과나서 추가함
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int dx = x + nx[i];
				int dy = y + ny[i];

				if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
					int newCost = cost + arr[dx][dy];

					if (newCost < memo[dx][dy]) {
						memo[dx][dy] = newCost;
						pq.offer(new int[] { dx, dy, newCost });
					}
				}
			}
		}

		return memo[N - 1][N - 1];
	}

//	public static int findRoad(int N, int[][] arr, int x, int y, int[][] memo) {
//		if (memo[x][y] != Integer.MAX_VALUE) {
//			return memo[x][y];
//		}
//		
//		int minMove = Integer.MAX_VALUE;
//
//		if(x == N-1 && y == N-1) {
//			return arr[x][y];
//		}
//
//		for (int i = 0; i < 4; i++) {
//			int dx = x + nx[i];
//			int dy = y + ny[i];
//
//			if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
//				int next = findRoad(N, arr, dx, dy, memo) + arr[dx][dy];
//				minMove = Math.min(minMove, next);
//			}
//		}
//		
//		memo[x][y] = minMove;
//		return minMove;
//
//	}
}
