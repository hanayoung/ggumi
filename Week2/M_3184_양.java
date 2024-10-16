package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M_3184_양 {

	static int R, C;
	static String[][] map;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] split = in.readLine().split(" ");

		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);

		map = new String[R][C];

		for (int i = 0; i < R; i++) {
			split = in.readLine().split("");
			for (int j = 0; j < C; j++) {
				map[i][j] = split[j];
			}
		}

		int totalW = 0, totalS = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!map[i][j].equals("#") && !map[i][j].equals("x")) {
					sheep = 0;
					wolf = 0;
					count(i, j);
					// System.out.println(sheep + " " + wolf);
					if (sheep > wolf) { // 양의 수가 많을 경우
						totalS += sheep;
					} else {
						totalW += wolf;
					}

				}
			}
		}

		System.out.println(totalS + " " + totalW);

	}

	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int sheep, wolf;

	private static void count(int x, int y) {

		// 시작 위치(x, y)의 경우도 세어주어야 한다.
		if (map[x][y].equals("v")) {
			wolf++;
		} else if (map[x][y].equals("o")){
			sheep++;
		}
		
		map[x][y] = "x";
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
				continue;
			}

			if (map[nx][ny].equals("o")) { // 양 일때,

				map[nx][ny] = "x";
				sheep++;
				count(nx, ny);

			} else if (map[nx][ny].equals("v")) { // 늑대일때

				map[nx][ny] = "x";
				wolf++;
				count(nx, ny);

			} else if (map[nx][ny].equals(".")) { // 그냥 길일 때
				count(nx, ny);
			}
		}

	}

}
