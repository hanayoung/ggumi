package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class sw_10966_물놀이를가자2 {
	static int N, M;
	static char arr[][];
	static int dist[][];
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
			dist = new int [N][M];
			
			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < M; j++) {
					arr[i][j] = input.charAt(j);
				}
			}

			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 'W') {
						int answer = bfs(i,j);
					}

				}
			}
			System.out.println("#" + tc + " " + sum);
			
		}
	}

	private static int bfs(int y, int x) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		int cnt = 0;
		int sum = 0;
		

		queue.add(new int[] { y,x, 1 }); // x위치, y위치, cnt
		visited[y][x] = true;
		dist[y][x] = cnt;

		while (!queue.isEmpty()) {
			int current[] = queue.poll();
			int py = current[0];
			int px = current[1];
			cnt = current[2];

			for (int i = 0; i < 4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];

				if (nx < 0 || ny < 0 || ny >= N || nx >= M || visited[ny][nx]) {
					continue;
				}
//				if(arr[ny][nx]=='W') {
//					return cnt;
//				}

				if (arr[ny][nx] == 'L') {
					queue.add(new int[] { ny,nx, cnt + 1 });
					visited[ny][nx] = true;
					
					
				}
			}
		}
		
		return -1;
	}

}
