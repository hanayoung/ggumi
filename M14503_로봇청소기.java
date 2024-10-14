package 백준;

import java.util.*;

public class M14503_로봇청소기 {
	static int[] nx = { -1, 0, 1, 0 }; // 북, 동, 남, 서
	static int[] ny = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int d = sc.nextInt(); // 바라보는 방향 (0:북, 1: 동, 2:남, 3:서)
		int[][] arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		int cleanZone = robotSystem(0, x, y, d, arr, N, M);

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}

		System.out.println(cleanZone);
	}

	public static int robotSystem(int cleanZone, int x, int y, int d, int[][] arr, int N, int M) {
		while (true) {
			// 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소
			if (arr[x][y] == 0) { // 청소하기
				arr[x][y] = 2;
				cleanZone++;
			}

			boolean findNotClean = false;

			// 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			for (int i = 0; i < 4; i++) {
				d = (d + 3) % 4;

				int dx = x + nx[d];
				int dy = y + ny[d];

				if (dx >= 0 && dx < N && dy >= 0 && dy < M && arr[dx][dy] == 0) { // 청소 안 된 곳 발견
					x = dx;
					y = dy;
					findNotClean = true;

					break;
				}
			}

			// 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
			if (findNotClean == false) {
				int dx = x - nx[d];
				int dy = y - ny[d];

				if (dx >= 0 && dx < N && dy >= 0 && dy < M && arr[dx][dy] != 1) { // 청소 안 된 곳 발견
					x = dx;
					y = dy;
				} else {
					break;
				}

			}

		}

		return cleanZone;
	}
}
