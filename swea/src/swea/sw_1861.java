package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw_1861 {

	static int N;
	static int room[][];
	static int cnt;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

//		System.setIn(new FileInputStream("1861.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			room = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int startroom = -1;
			int max = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int nowroom = dfs(i, j);

					if (nowroom > max) {
						max = nowroom;
						startroom = room[i][j];
					}

					else if (nowroom == max && room[i][j] < startroom) {
						startroom = room[i][j];
					}

				}
			}

			System.out.println("#" + tc + " " + startroom + " " + max);

		}
	}

	private static int dfs(int x, int y) {

		cnt = 1;

		for (int i = 0; i < 4; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;

			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}

			if (room[nx][ny] == room[x][y] + 1) {
				cnt += dfs(nx, ny);
			}

		}

		return cnt;
	}

}
