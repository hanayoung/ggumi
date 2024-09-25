package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1697_숨바꼭질 {
	// 백트래킹 하니까 스택오버플로우;;;;; ㅡㅡ

	static int N;
	static int K;
	static int min = Integer.MAX_VALUE;
	static boolean[] visited = new boolean[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		move(N, 0);
		System.out.println(min);

	}

	private static void move(int now, int time) {
		if (now == K) {
			min = Math.min(min, time);
			return;
		}

		if (now < 0 || now > 100000 || visited[now]) {
			return;
		}

		visited[now] = true;

		move(now - 1, time + 1);
		move(now + 1, time + 1);
		move(now * 2, time + 1);
		
//		visited[now] = false;

	}
}
