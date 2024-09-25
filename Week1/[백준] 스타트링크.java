import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int F, S, G, U, D;
	static int result;
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		F = Integer.parseInt(st.nextToken()); // 총 층 수
		S = Integer.parseInt(st.nextToken()); // 현재 위치
		G = Integer.parseInt(st.nextToken()); // 가야할 곳
		U = Integer.parseInt(st.nextToken()); // 위로가는 버튼
		D = Integer.parseInt(st.nextToken()); // 아래로 가는 버튼

		result = Integer.MAX_VALUE;
		bfs(S);

		if (result != INF) {
			System.out.println(result);
		} else {
			System.out.println("use the stairs");
		}
	}

	private static void bfs(int start) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		boolean[] visited = new boolean[F + 1];
		queue.offer(new int[] { start, 0 });
		visited[start] = true;

		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int s = temp[0];
			int c = temp[1];

			if (s == G) {
				result = Math.min(result, c);
				return;
			}

			if (s + U <= F && !visited[s + U]) {
				visited[s + U] = true;
				queue.offer(new int[] { s + U, c + 1 });
			}

			if (s - D >= 1 && !visited[s - D]) {
				visited[s - D] = true;
				queue.offer(new int[] { s - D, c + 1 });
			}
		}

	}
}