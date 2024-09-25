package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_5014_스타트링크 {

	static int F, S, G, U, D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		boolean[] visited = new boolean[F + 1];

		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { S, 0 });
		visited[S] = true;

		while (!queue.isEmpty()) {
			int current[] = queue.poll();
			int now = current[0];
			int move = current[1];

			if (now == G) {
				System.out.println(move);
				return;
			}

			if (now + U <= F && !visited[now + U]) {
				queue.add(new int[] { now + U, move + 1 });
				visited[now + U] = true;
			}

			if (now - D >= 1 && !visited[now - D]) {
				queue.add(new int[] { now - D, move + 1 });
				visited[now - D] = true;
			}

		}
		System.out.println("use the stairs");

	}
}
