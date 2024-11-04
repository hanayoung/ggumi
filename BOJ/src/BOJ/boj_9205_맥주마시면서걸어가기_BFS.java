package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_9205_맥주마시면서걸어가기_BFS {
	static int n, sx, sy, dx, dy, x, y;
	static boolean canGo[][];
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
			}
		}
	}

}
