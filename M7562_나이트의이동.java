package 백준;

import java.util.*;

public class M7562_나이트의이동 {
	static int[] nx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] ny = { -1, -2, -2, -1, 1, 2, 2, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		for (int testCase = 0; testCase < tc; testCase++) {
			int N = sc.nextInt();
			int startX = sc.nextInt();
			int startY = sc.nextInt();
			int endX = sc.nextInt();
			int endY = sc.nextInt();

			int[][] check = new int[N][N];
			int move = nightMove(check, N, startX, startY, endX, endY);
			System.out.println(move);
		}
	}

	public static int nightMove(int[][] check, int N, int x, int y, int x2, int y2) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { x, y });
		check[x][y] = 1;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();

			if (current[0] == x2 && current[1] == y2) {
				return check[current[0]][current[1]] - 1;
			}

			for (int i = 0; i < 8; i++) {
				int dx = current[0] + nx[i];
				int dy = current[1] + ny[i];

				if (dx >= 0 && dx < N && dy >= 0 && dy < N && check[dx][dy] == 0) {
					check[dx][dy] = check[current[0]][current[1]] + 1;
					queue.add(new int[] { dx, dy });
				}
			}

		}
		return -1;
	}
}
