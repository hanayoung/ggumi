package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_7569_토마토 {
	static int M, N, H;
	static int[][][] box;
	static boolean[][][] visited;
	static int dx[] = { -1, 1, 0, 0, 0, 0 };//좌, 우, 상, 하, 위, 아래
	static int dy[] = { 0, 0, 1, -1, 0, 0 };
	static int dz[] = { 0, 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		box = new int[N][M][H];
		visited = new boolean[N][M][H];
		for(int i = 0; i <N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				for(int k = 0; k < H; k++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
					if(box[i][j][k] == 1) {
						
					}
				}
			}
		}
	}

}
