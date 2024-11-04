package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1600_말이되고픈원숭이 {
	static int arr[][];
	static int k,w,h;
	static boolean visited[][][];
	static int dx[] = {0,0,-1,1};
	static int dy[] = {1,-1,0,0};
	static int hx[] = {-2,-1,1,2,2,1,-1,-2};
	static int hy[] = {1,2,2,1,-1,-2,-2,-1};
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		arr = new int[h][w];
		visited = new boolean[h][w][k]; // 왜 k+1해야하지?!
		
		for(int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j <w;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs(0,0));
		
	}
	
// 원숭이가 최소한으로 이동하는 칸 수 
	private static int bfs(int x, int y) {
		Queue<int[]>queue = new LinkedList<>();
		visited[y][x][0] = true;
		queue.add(new int[] {x,y,0,0}); //현재위치 x,y, k의 횟수, 움직인 횟수
		
		while(!queue.isEmpty()) {
			int[]current = queue.poll();
			int px = current[0];
			int py = current[1];
			int horse = current[2];
			int move = current[3];
			
			if(px == w-1 && py == h-1) {
				return move;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx<0 || ny < 0 || nx >= w || ny >= h || arr[ny][nx] == 1||visited[ny][nx][horse]) {
					continue;
				}
				
				if(arr[ny][nx] ==0) {
					visited[ny][nx][horse] = true;
					queue.add(new int[] {nx, ny, 0, move+1});
				}
				
				
			}
			
			if(horse<k) {
				for(int i = 0; i < 8; i++) {
					int nx = px + dx[i];
					int ny = py + dy[i];
					
					if(nx < 0 || ny < 0 || nx >= w || ny >= h || visited[ny][nx][horse+1]) {
						continue;
					}
				}
			}
		}
		return null;
	}

}
