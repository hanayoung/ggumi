package algo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 visited를 사용하면, 방문한 곳은 다시 방문하지 못해서 모든 경우를 나타낼 수 없다. 따라서 쓰면 틀림
 len을 통해, 각 (x, y) 위치의 최대 길이 값을 구한다.
 처음에 visited를 통해 가장 긴 값을 구했는데, 그렇게 하면 시작점을 구하기 어려웠다.
 
 최대 길이를 가지면서, 가장 작은 방을 구하기 때문에 2가지로 정렬해야한다.
 1. 기존 길이보다 크면 그 값이 최대 길이
 2. 기존 길이와 같으면, 그 중에서 가장 작은 값을 선택할 것
 */

public class S_1861_정사각형방 {

	private static int[][] map;
	private static int n;

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st;

		for (int t = 1; t <= T; t++) {

			n = Integer.parseInt(in.readLine());
			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			maxLen = -1;
			minStart = map[0][0];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int len = start(i, j);
					if (maxLen < len || (len == maxLen && minStart >= map[i][j])) {
						maxLen = len;
						minStart = map[i][j];
					}
				}
			}

			System.out.printf("#%d %d %d\n", t, minStart, maxLen);
		}

	}

	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, 1, -1 };
	private static int maxLen; // 최대 이동 거리
	private static int minStart; // 최소 시작값

	private static int start(int x, int y) {

		int len = 1;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
				continue;
			}

			if (map[x][y] + 1 == map[nx][ny]) {
				len = Math.max(len, start(nx, ny) + 1);
			}
		}

		return len;

	}

}