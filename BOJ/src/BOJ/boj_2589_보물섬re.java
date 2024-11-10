package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2589_보물섬re {
	static char arr[][];
	static boolean visited[][];
	static int c, r;
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		c = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new char[c][r];
		

		for (int i = 0; i < c; i++) {
			String input = br.readLine();
			for (int j = 0; j < r; j++) {
				arr[i][j] = input.charAt(j);
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {
				if(arr[i][j] =='L') {
					visited = new boolean[c][r]; //방문이 갱신돼야함 
					int result = bfs(i, j);
					if(result > max) {
						max = result;
					}
				}
			}
		}
		System.out.println(max);
	}

	private static int bfs(int y, int x) {
		int cnt = 0;
		Queue<int[]>queue = new LinkedList<>();
		queue.add(new int[] {y, x, 0});
		visited[y][x] = true;
		
		
		while(!queue.isEmpty()) {
			int current[] = queue.poll();
			int py = current[0];
			int px = current[1];
			cnt = current[2];
			
			for(int i = 0; i < 4; i++) {
				int ny = py + dy[i];
				int nx = px + dx[i];
				
				if(ny < 0 || nx < 0 || ny >= c || nx >= r || visited[ny][nx] || arr[ny][nx] == 'W') {
					continue;
				}
//				if(arr[ny][nx] == 'L' && visited[ny][nx]) {
//					return cnt;
//				}
				if(arr[ny][nx] == 'L' && !visited[ny][nx]) {
					queue.add(new int[] {ny,nx, cnt+1});
					visited[ny][nx] = true;
				}
			}

		}
		return cnt;
	}

}
