import java.util.*;

public class M1986_체스 {
	static int[] nx = { -1, 1, 0, 0, 1, -1, 1, -1 };
	static int[] ny = { 0, 0, -1, 1, 1, -1, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		String[][] arr = new String[n][m]; // 체스판

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = "0";
			}
		}

		// queen 저장
		int queenCount = sc.nextInt();
		for (int i = 0; i < queenCount; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			arr[x - 1][y - 1] = "Q";
		}

		// knight 저장
		int knightCount = sc.nextInt();
		for (int i = 0; i < knightCount; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			arr[x - 1][y - 1] = "K";
		}

		// Pawn 저장
		int pawnCount = sc.nextInt();
		for (int i = 0; i < pawnCount; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			arr[x - 1][y - 1] = "P";
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}

		// 각 말이 움직일 수 있는 모든 자리 1로 변경
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j].equals("Q")) {
					queenMove(arr, i, j, n, m);
				} else if (arr[i][j].equals("K")) {
					knightMove(arr, i, j, n, m);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}

		int cnt = 0;
		// 안전한 칸 세기 -> 0인 값 count
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j].equals("0")) {
					cnt++;
				}
			}
		}

		System.out.println(cnt);
	}

	public static void queenMove(String[][] arr, int x, int y, int n, int m) {
		if (arr[x][y].equals("P")) {
			return;
		}

		for (int i = 0; i < 8; i++) {
			int dx = x + nx[i];
			int dy = y + ny[i];

			while (dx >= 0 && dx < n && dy >= 0 && dy < m) {
				if (!arr[dx][dy].equals("0") && !arr[dx][dy].equals("1")) { // 다른 말이 있으면 멈춤
					break;
				}
				arr[dx][dy] = "1";
				dx += nx[i];
				dy += ny[i];
			}
		}
	}

	public static void knightMove(String[][] arr, int x, int y, int n, int m) {
		int[] kx = { -2, -2, -1, -1, 1, 1, 2, 2 };
		int[] ky = { -1, 1, -2, 2, -2, 2, -1, 1 };

		for (int i = 0; i < 8; i++) {
			int dx = x + kx[i];
			int dy = y + ky[i];

			if (dx >= 0 && dx < n && dy >= 0 && dy < m && arr[dx][dy].equals("0")) {
				arr[dx][dy] = "1";
			}
		}
	}
}
