package ����;

import java.util.*;

public class M3184_�� {
	static int[] nx = { 0, 0, -1, 1 };
	static int[] ny = { 1, -1, 0, 0 };
	static int R;
	static int C;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt(); 
		sc.nextLine();
		char[][] arr = new char[R][C];
		int[][] check = new int[R][C];

		for (int i = 0; i < R; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		// dfs �����鼭 �ο� let's go
		int totalS = 0;
		int totalW = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (check[i][j] == 0 && arr[i][j] != '#') {
					int[] result = dfs(arr, check, i, j);
					int s = result[0];
					int w = result[1];

					// ���� �̱�� �縸 �߰�, ���밡 �̱�� ���븸 �߰�
					if (s > w) { // ���� �̱�~
						totalS += s; 
					} else {
						totalW += w;
					}
				}
			}
		}
		
		System.out.println(totalS + " " + totalW);

	}

	public static int[] dfs(char[][] arr, int[][] check, int x, int y) {
		check[x][y] = 1;
		int sheep = 0, wolf = 0;

		// sheep & wolf ���� check
		if (arr[x][y]== 'o')
			sheep++;
		if (arr[x][y] == 'v')
			wolf++;

		for (int i = 0; i < 4; i++) {
			int dx = x + nx[i];
			int dy = y + ny[i];

			if (dx >= 0 && dx < R && dy >= 0 && dy < C && check[dx][dy] == 0 && arr[dx][dy] != '#') {
				int[] result = dfs(arr, check, dx, dy);
				sheep += result[0];
				wolf += result[1];
			}
		}

		return new int[] { sheep, wolf };

	}
}
