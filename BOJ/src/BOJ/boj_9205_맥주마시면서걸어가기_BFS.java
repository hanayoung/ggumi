package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_9205_맥주마시면서걸어가기_BFS {
	static int n, sx, sy, dx, dy, x, y;
	static boolean visited[];
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			List<int[]>list = new ArrayList<>();
			 StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n+2; i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				if(i == 0) {
					sx = x;
					sy = y;
				}
				else if(i == n +1) {
					dx = x;
					dy = y;
				}
				else {
					list.add(new int[] {x,y});
				}
			}
			if(bfs(list)) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
		}
	}
	private static boolean bfs(List<int[]>list) {
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[n];
		queue.add(new int[] {sx,sy});
		
		while(!queue.isEmpty()) {
			int[]current = queue.poll();
			
			int posX = current[0];
			int posY = current[1];
			if(Math.abs(posX-dx)+Math.abs(posY-dy)<=1000) {
				return true;
			}
			
			for(int i = 0; i < n; i++) {
				if(!visited[i]) {
					int nx = list.get(i)[0];
					int ny = list.get(i)[1];
					int dis = Math.abs(posX - nx) + Math.abs(posY-ny);
					if(dis <=1000) {
						visited[i] = true;
						queue.add(new int[] {nx,ny});
					}
				}
			}
					
		}
		
		return false;
	}

}
