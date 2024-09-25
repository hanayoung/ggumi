import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	static int N;
	static int map[][];
	static int min;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int[][] distance;
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case < T + 1; test_case++) {
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			distance = new int[N][N];

			for (int i = 0; i < N; i++) {
				String[] split = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}

			for (int[] d : distance) {
				Arrays.fill(d, INF);
			}

			

			min = Integer.MAX_VALUE;

			bfs(0, 0);

			sb.append(min + "\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int x, int y) {

		// Queue<int[]> queue = new ArrayDeque<>();
		// 최소 힙: [x좌표, y좌표, 비용]
		
		// PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);  // 최소 힙: [x좌표, y좌표, 비용]
        
		PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
		    @Override
		    public int compare(int[] a, int[] b) {
		        return a[2] - b[2];
		    }
		});
		
		queue.offer(new int[] { x, y, 0 });
		
		distance[x][y] = 0;

		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int curX = temp[0];
			int curY = temp[1];
			int cost = temp[2];
			
			 // 이미 처리된 적 있는 더 작은 경로라면 스킵
            if (cost > distance[curX][curY]) continue;

			if (curX == N - 1 && curY == N - 1) {
				min = Math.min(min, distance[N - 1][N - 1]);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nextX = curX + dx[i];
				int nextY = curY + dy[i];

				if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N)
					continue;

				int newCost = cost + map[nextX][nextY];
				
				if (distance[nextX][nextY] > newCost) {
					distance[nextX][nextY] = newCost;
					queue.offer(new int[] { nextX, nextY, newCost });
				}
			}
		}

	}
}
