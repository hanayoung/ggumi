package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_13503_로봇청소기 {
	static int N, M, r, c, d;
	static int room[][];
	static boolean visited[][];
	static int dx[] = { -1, 0, 1, 0 };// 0북 1동 2남 3서
	static int dy[] = { 0, 1, 0, -1 };
	static int cnt, now;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		visited = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				clean(r, c, d); // claen메서드 만들기. 시작지점 r,c

			}
		}

	}

	private static void clean(int x, int y, int dir) {

	

		if (room[x][y] == 0 && visited[x][y] == false) {
			room[x][y] = 2; // 청소한 칸이면 2로 바꿈
			visited[x][y] = true;
			cnt++;
		}
			
		for(int i = 0; i < 4; i++) {
			
			//왼쪽방향 회전
			switch(dir) {
			case 0 : dir = 3; break;
			case 1 : dir = 0; break;
			case 2 : dir = 1; break;
			case 3 : dir = 2; break;
			}
			
			int lx = x + dx[dir];
			int ly = y + dy[dir];
			
			if(lx < 0 || ly < 0 || lx >= N || ly >= M || visited[lx][ly] == true) {
				continue;
			}
			if(room[lx][ly] == 0) {
				clean(lx, ly, dir);
			}
			
			
			
			
				
				
				switch (dir) {
				case 0:
					dir = 2;
					break;
					
				case 1:
					dir = 3;
					break;
					
				case 2:
					dir = 0;
					break;
				case 3:
					dir = 1;
					break;
				}
				
				int bx = x + dx[dir];
				int by = y + dy[dir];
				
				if(bx <0 || by <0 || bx >= N || by >= N || room[bx][by] == 1) {
					continue;
				}
				clean(bx, by, dir);
			}
		}

	}

}
