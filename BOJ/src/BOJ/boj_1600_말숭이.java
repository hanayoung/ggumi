package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1600_말숭이 {

	static int horseX[] = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int horseY[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int K, W, H;
	static int monkeyX[] = { -1, 0, 1, 0 };
	static int monkeyY[] = { 0, 1, 0, -1 };
	static int arr[][];
	static boolean visited[][][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		visited = new boolean[H][W][K]; //말처럼 k번 이동 가능. 

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {0, 0, 0, 0}); // x, y, k, dist
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()){
			int[]current = queue.poll();
			int x = current[0];
			int y = current[1];
			int k = current[2];
			int dist = current[3];
			
			//도착지점
			if(x == H -1 && y == W -1) {
				return dist;
			}
			
			//말처럼 이동
			if(k < K) {
				for(int i = 0; i < 8; i++) {
					int nx = x + horseX[i];
					int ny = y + horseY[i];
					
					if(nx < 0 || ny < 0 || nx >= H || ny >= W) {
						continue;
					}
					if(!visited[nx][ny][k+1] && arr[nx][ny]==0) {
						visited[nx][ny][k+1] = true;
						queue.add(new int[] {nx, ny, k + 1, dist + 1});
					}
				}
			}
			
			//원숭이 이동
			for(int i = 0; i < 4; i++) {
				int nx = x + monkeyX[i];
				int ny = y + monkeyY[i];
				
				if(nx < 0 || ny < 0 || nx >= H || ny >= W) {
					continue;
				}
				if(!visited[nx][ny][k] && arr[nx][ny] == 0) {
					visited[nx][ny][k] = true;
					queue.add(new int[] {nx, ny, k, dist + 1});
				}
			}
		}
		return -1;
	}

}
