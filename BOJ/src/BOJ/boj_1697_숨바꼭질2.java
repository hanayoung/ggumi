package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1697_숨바꼭질2 {

	static int N;
	static int K;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 방문했는지, 해당 위치까지 도달하는데 걸린 시간
		int[] visited = new int[100001];

		System.out.println(bfs(N, K, visited));
	}

	private static int bfs(int N, int K, int[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(N); // 수빈이의 시작 위치. bfs가 시작하는 위치
		visited[N] = 1;// 시작점은 방문 표시 (1로 초기화, 걸린 시간을 세기 위해, 방문1 방문안함0). 1초단위로 게산하려고 1로함

		while (!queue.isEmpty()) { // 큐가 비어있지 않으면 반복
			int current = queue.poll(); // 현재위치에 큐 첫번째 요소 넣기

			// 동생 찾으면 종료
			if (current == K) {
				return visited[current] - 1; // 시작을 1로 했으니까 결과값에서 1빼줘야함
			}
			// 이동할 수 있는 세가지 경우 -1, +1, *2
			if (current - 1 >= 0 && visited[current - 1] == 0) {
				visited[current - 1] = visited[current] + 1;
				queue.add(current - 1);
			}
			if (current + 1 <= 100000 && visited[current + 1] == 0) {
				visited[current + 1] = visited[current] + 1;
				queue.add(current + 1);
			}
			if (current * 2 <= 100000 && visited[current * 2] == 0) {
				visited[current * 2] = visited[current] + 1;
				queue.add(current * 2);
			}
		}
		return -1;
	}
}
