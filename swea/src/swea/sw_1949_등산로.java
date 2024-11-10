package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw_1949_등산로 {
	static int N, K, maxCnt;
	static int peak;
	static int map[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };
	static boolean visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("1949.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 한변의 길이
			K = Integer.parseInt(st.nextToken()); // 공사 할수 있는 횟수
			map = new int[N][N];
			visited = new boolean[N][N];
			maxCnt = Integer.MIN_VALUE;
			peak = -1;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] > peak && !visited[i][j]) {
						peak = map[i][j];
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == peak) {
						dfs(i, j, 1, false);
					}
				}
			}
			System.out.println(maxCnt);

		}
	}

	private static void dfs(int y, int x, int cnt, boolean isCut) {

		visited[y][x] = true;
		maxCnt = Math.max(maxCnt, cnt);

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[ny][nx]) {
				continue;
			}

			if (map[ny][nx] < map[y][x] && !visited[ny][nx]) {
				visited[ny][nx] = true;
				dfs(ny, nx, cnt + 1, isCut);
				
			} else if (!isCut && map[ny][nx] - K < map[y][x]) {
				int origin = map[ny][nx];
				map[ny][nx] = map[y][x] - 1;
				dfs(ny, nx, cnt + 1, true);
				map[ny][nx] = origin;
			}
		}
		visited[y][x] = false;

	}

}
