package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class M_2583_영역구하기 {

	static int m, n;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());

			for (int j = x; j < sx; j++) {
				for (int t = y; t < sy; t++) {
					map[j][t] = 1;
				}
			}
		}

//		for (int[] mm : map) {
//			System.out.println(Arrays.toString(mm));
//		}

		List<Integer> nums = new ArrayList<Integer>();
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && map[i][j] != 1) {
					area(i, j);
					nums.add(cnt);
					cnt = 0;
					ans++;
				}
			}
		}

		System.out.println(ans);
		Collections.sort(nums);
		for(int n: nums) {
			System.out.print(n + " ");
		}

	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int cnt = 0;

	static void area(int x, int y) {

		visited[x][y] = true;
		cnt++;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
				continue;
			}

			if (!visited[nx][ny] && map[nx][ny] != 1) {
				visited[nx][ny] = true;
				area(nx, ny);
			}
		}
	}

}
