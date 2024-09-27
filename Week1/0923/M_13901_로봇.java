package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 https://baby-dev.tistory.com/m/entry/Python-%EB%B0%B1%EC%A4%80-13901-%EB%A1%9C%EB%B4%87
 참고 블로그
 진짜 모르겠다 ㅡㅡ
 */
public class M_13901_로봇 {

	private static int r, c;
	private static int[][] map;
	private static int[] direction;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new int[r][c];

		// 장애물
		int t = Integer.parseInt(in.readLine());
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(in.readLine());
			int obX = Integer.parseInt(st.nextToken());
			int obY = Integer.parseInt(st.nextToken());

			map[obX][obY] = -1;
		}

		// 시작 위치
		st = new StringTokenizer(in.readLine());
		int rx = Integer.parseInt(st.nextToken());
		int ry = Integer.parseInt(st.nextToken());

		direction = new int[4];
		st = new StringTokenizer(in.readLine());
		// 1은 위, 2는 아래, 3은 왼, 4는 오른쪽
		for (int i = 0; i < 4; i++) {
			direction[i] = Integer.parseInt(st.nextToken());
		}

		map[rx][ry] = 1;
		dfs(rx, ry);
		System.out.println(ans[0] + " " + ans[1]);
	}

	// (-1, 0) (1, 0) (0, -1), (0, 1)
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };
	static int[] ans = new int[2];

	static void dfs(int sx, int sy) {

		int idx = 0, cnt = 0;
		while (true) {

			if (cnt == 4) {
				break;
			}

			int nx = sx + dx[direction[idx % 4]];
			int ny = sy + dy[direction[idx % 4]];

			if (nx < 0 || ny < 0 || nx >= r || ny >= c || map[nx][ny] != 0) {
				idx++;
				cnt++;
			} else {
				cnt = 0;
				map[nx][ny] = map[sx][sy] + 1;
				sx = nx;
				sy = ny;
				ans[0] = sx;
				ans[1] = sy;

			}

		}

	}

	/*
	 * 5 5 3 1 2 3 3 2 4 2 2 1 2 3 4
	 */

}
