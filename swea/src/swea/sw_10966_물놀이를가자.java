package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class sw_10966_물놀이를가자 {
	static int N, M;
	static char arr[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("10966.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new char[N][M];
			
			visited = new int[N][M];
			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < M; j++) {
					arr[i][j] = input.charAt(j);
					
					if(arr[i][j] == 'W') {
						visited[i][j] = 1;
					} else { // 육지잉ㄹ대
						queue.offer(new int[] {i, j});
					}
				}
			}
			
//			int sum = 0;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					if(arr[i][j] == 'W') {
//					bfs(i, j);
////					sum += answer;
//					}
//
//				}
//			}
			
			bfs();
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	private static int[][] visited;
	private static int ans = 0;
	private static Queue<int[]> queue = new LinkedList<>();
	private static void bfs() {


		while (!queue.isEmpty()) {
			int current[] = queue.poll();
			int px = current[0];
			int py = current[1];

			
			for (int i = 0; i < 4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];

				if (nx < 0 || ny < 0 || ny >= M || nx >= N || visited[nx][ny] > 0) {
					continue;
				}
				
//				
				if (arr[nx][ny] == 'L') {
					visited[nx][ny] = visited[px][py] + 1;
					ans += visited[nx][ny] -1;
					queue.add(new int[] { nx, ny});
				}

			}

		}
	}

}
