package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1986_체스 {

	static int n;
	static int m;
	static int chess[][];
	static int queen;
	static int knight;
	static int pawn;
	static int knightX[] = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int knightY[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int queenX[] = { 0, 0, -1, 1, 1, 1, -1, -1 };
	static int queenY[] = { 1, -1, 0, 0, 1, -1, -1, 1 };
	static int cnt = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		chess = new int[n + 1][m + 1];

		st = new StringTokenizer(br.readLine());
		queen = Integer.parseInt(st.nextToken());
		for (int i = 0; i < queen; i++) {
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			chess[x][y] = 1;
		}
		st = new StringTokenizer(br.readLine());
		knight = Integer.parseInt(st.nextToken());
		for (int i = 0; i < knight; i++) {
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			chess[x][y] = 2;
		}
		st = new StringTokenizer(br.readLine());
		pawn = Integer.parseInt(st.nextToken());
		for (int i = 0; i < pawn; i++) {
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			chess[x][y] = -1;
		}

		queenMove(n, m);
		knightMove(n, m);

		// 안전한 자리 = 0
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if (chess[i][j] == 0) {
					cnt++;
				}
			}
		}

		System.out.println(cnt);

	}

	private static void knightMove(int n, int m) {
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if (chess[i][j] == 2) {
					for (int d = 0; d < 8; d++) {
						int nx = i + knightX[d];
						int ny = j + knightY[d];
						if(nx < 0 || ny < 0 || nx >= n + 1 || ny >= m+1||chess[nx][ny]!=0) {
							continue;
						}
						chess[nx][ny] = -1;
					}
				}
			}
		}

	}

	private static void queenMove(int n, int m) {
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if (chess[i][j] == 1) {
					for (int d = 0; d < 8; d++) {
						int nx = i;
						int ny = j;
						while (true) {
							nx += queenX[d];
							ny += queenY[d];
							if (nx < 0 || ny < 0 || nx >= n + 1 || ny >= m + 1 || chess[nx][ny] != 0)
								break;
							chess[nx][ny] = -1;
						}
					}
				}
			}
		}

	}

}
