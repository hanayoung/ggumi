package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 구현으로 풀었는데 완전 지침...
 아니 퀸의 경우 0부터 n-1까지 대각선으로 쭉 이동 가능한 경로인줄 알았는데 딱 한번 이동하는 거였다..

 */
public class M_1986_체스 {

	private static int n, m;
	private static int[][] map;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] split = in.readLine().split(" ");
		n = Integer.parseInt(split[0]);
		m = Integer.parseInt(split[1]);
		map = new int[n][m];

		// 퀸의 개수 및 퀸의 위치
		StringTokenizer st = new StringTokenizer(in.readLine());
		int qCnt = Integer.parseInt(st.nextToken());
		for (int i = 0; i < qCnt; i++) {
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = 1;
		}

		// Knight 개수와 위치
		st = new StringTokenizer(in.readLine());
		int kCnt = Integer.parseInt(st.nextToken());
		for (int i = 0; i < kCnt; i++) {
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = 2;
		}

		// Pawn의 개수와 위치
		st = new StringTokenizer(in.readLine());
		int pCnt = Integer.parseInt(st.nextToken());
		for (int i = 0; i < pCnt; i++) {
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = 3;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) { // 퀸
					queen(i, j);
				} else if (map[i][j] == 2) { // 나이트
					knight(i, j);
				}
			}
		}

		// 결과 출력
//		for (int[] row : map) {
//			System.out.println(Arrays.toString(row));
//		}

		// 안전한 칸 수 세기
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					ans++;
				}
			}
		}

		System.out.println(ans);
	}

	static int[] di = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dj = { 0, 1, 1, 1, 0, -1, -1, -1 }; // 퀸을 위한 8방탐색

	static void queen(int x, int y) { // 퀸의 이동경로 에 -1 처리

		for (int d = 0; d < 8; d++) {

			int ni = x;
			int nj = y;
			while (true) {
				ni += di[d];
				nj += dj[d];
				if (ni < 0 || nj < 0 || ni > n - 1 || nj > m - 1)
					break;
				if ((map[ni][nj] == 0 || map[ni][nj] == -1)) {
					if (map[ni][nj] == 0)
						map[ni][nj] = -1;
				} else
					break;
			}
		}
	}

	private static void knight(int x, int y) {
		// 나이트의 이동 경로
		int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
		int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };

		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 0) {
				map[nx][ny] = -1;
			}
		}
	}
}