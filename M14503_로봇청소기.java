package ����;

import java.util.*;

public class M14503_�κ�û�ұ� {
	static int[] nx = { -1, 0, 1, 0 }; // ��, ��, ��, ��
	static int[] ny = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int d = sc.nextInt(); // �ٶ󺸴� ���� (0:��, 1: ��, 2:��, 3:��)
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
			// 1. ���� ĭ�� ���� û�ҵ��� ���� ���, ���� ĭ�� û��
			if (arr[x][y] == 0) { // û���ϱ�
				arr[x][y] = 2;
				cleanZone++;
			}

			boolean findNotClean = false;

			// 2. ���� ĭ�� �ֺ� 4ĭ �� û�ҵ��� ���� �� ĭ�� �ִ� ���
			for (int i = 0; i < 4; i++) {
				d = (d + 3) % 4;

				int dx = x + nx[d];
				int dy = y + ny[d];

				if (dx >= 0 && dx < N && dy >= 0 && dy < M && arr[dx][dy] == 0) { // û�� �� �� �� �߰�
					x = dx;
					y = dy;
					findNotClean = true;

					break;
				}
			}

			// 3. ���� ĭ�� �ֺ� 4ĭ �� û�ҵ��� ���� �� ĭ�� ���� ���
			if (findNotClean == false) {
				int dx = x - nx[d];
				int dy = y - ny[d];

				if (dx >= 0 && dx < N && dy >= 0 && dy < M && arr[dx][dy] != 1) { // û�� �� �� �� �߰�
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
