package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 한번에 끝까지 가는 dfs가 아닌 bfs로 풀이해야 시간복잡도를 줄일 수 있다.
 * 중복 경로를 탐색하지 않기 위해 visited 가 필요하다
 */

/*
 queue.poll()을 하고 나서 바로 아래서 범위체크를 하면 런타임 에러가 발생한다.
 position + 1, position - 1, position * 2 일때 각각 범위 체크를 해주어야 한다.
 특히, position - 1은 음수가 되지 않는 지 체크해야 한다.
 */
public class M_1697_숨바꼭질 {

	static int k, n;
	static boolean[] visited;
	static final int MAX = 100001;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken()); // 수빈
		k = Integer.parseInt(st.nextToken()); // 동생

		visited = new boolean[MAX];
		int ans = findK();
		System.out.println(ans);
	}

	private static int findK() {

		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] { n, 0 }); // 시작 지점과 시간
		visited[n] = true;

		while (!queue.isEmpty()) {

			int[] curr = queue.poll();
			int position = curr[0];
			int time = curr[1];

			if (position == k) {
				return time;
			}

			if (position + 1 < MAX && !visited[position + 1]) {
				visited[position + 1] = true;
				queue.offer(new int[] { position + 1, time + 1 });
			}

			if (position - 1 >= 0 && !visited[position - 1]) {
				visited[position - 1] = true;
				queue.offer(new int[] { position - 1, time + 1 });
			}

			if (position * 2 < MAX && !visited[position * 2]) {
				visited[position * 2] = true;
				queue.offer(new int[] { position * 2, time + 1 });
			}

		}
		return -1;
	}

}
