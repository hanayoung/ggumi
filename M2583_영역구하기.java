package ����;

import java.util.*;

public class M2583_�������ϱ� {
	static int[] nx = { -1, 1, 0, 0 };
	static int[] ny = { 0, 0, 1, -1 };
	static List<Integer> areaList = new ArrayList<>();
	static int cnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int K = sc.nextInt(); // ���簢�� ����
		int[][] check = new int[M][N];

		// ��ĥ�� �κ��� �迭���� 1�� ǥ���ϱ� -> �Ȱ��� ��ĥ�ϴ� ��������,,
		int[][] arr = new int[M][N];
		for (int i = 0; i < K; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();

			for (int w = x1; w < x2; w++) {
				for (int h = y1; h < y2; h++) {
					arr[h][w] = 1;
				}
			}
		}

		// ��ĥ �� �� �κ� ã��
		for (int p = 0; p < M; p++) {
			for (int j = 0; j < N; j++) {
				if (arr[p][j] == 0 && check[p][j] == 0) {
					int areaS = whiteArea(M, N, p, j, arr, check);
					areaList.add(areaS);
					cnt++;
				}
			}
		}

		Collections.sort(areaList);

		System.out.println(cnt);
		for (int i = 0; i < areaList.size(); i++) {
			System.out.print(areaList.get(i) + " ");
		}

	}

	public static int whiteArea(int m, int n, int x, int y, int[][] arr, int[][] check) {
		check[x][y] = 1;
		int sum = 1; // �������� �⺻������ ���Խ�Ŵ

		for (int i = 0; i < 4; i++) {
			int dx = x + nx[i];
			int dy = y + ny[i];

			if (dx >= 0 && dx < m && dy >= 0 && dy < n && arr[dx][dy] == 0 && check[dx][dy] == 0) {
				sum += whiteArea(m, n, dx, dy, arr, check);
			}
		}

		return sum;
	}
}
