package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2589_보물섬 {
	static char map[][];
	static int visited[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int c, r;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		c = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new char[c][r];
		visited = new int[c][r];

		for (int i = 0; i < c; i++) {
			String input = br.readLine();
			for (int j = 0; j < r; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		
		max = Integer.MIN_VALUE;

		int sx =-1, sy = -1;
		
		for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {
				if (map[i][j] == 'L') {
					sx = j;
					sy = i;
				}
			}
		}
		
		visited[sy][sx] = 1;
		bfs(sy, sx);
	
		for(int[] v: visited) {
			System.out.println(Arrays.toString(v));
		}

		

	}
	
	private static int max;
	private static void bfs(int py, int px) {

		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {py, px});
		
		while (!queue.isEmpty()) {
			int current[] = queue.poll();
			py = current[0];
			px = current[1];
//			int cnt = current[2];

			for (int i = 0; i < 4; i++) {
				int ny = py + dy[i];
				int nx = px + dx[i];

				if (ny < 0 || nx < 0 || ny >= c || nx >= r || visited[ny][nx] != 0|| map[ny][nx] == 'W') {
					continue;
				}

//				if (map[ny][nx] == 'L' && cnt == max) {
//					System.out.println(max);
//				}

				if (map[ny][nx] == 'L' && visited[ny][nx] ==0) {
				queue.add(new int[] { ny, nx});
					visited[ny][nx] = visited[py][px] + 1;
//					if (cnt > max) {
//						max = cnt + 1;
//					}
				}

			}
		}
	}

}
