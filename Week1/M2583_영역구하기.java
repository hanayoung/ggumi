package 백준;

import java.util.*;

public class M2583_영역구하기 {
	static int[] nx = { -1, 1, 0, 0 };
	static int[] ny = { 0, 0, 1, -1 };
	static List<Integer> areaList = new ArrayList<>();
	static int cnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int K = sc.nextInt(); // 직사각형 개수
		int[][] check = new int[M][N];

		// 색칠된 부분을 배열에서 1로 표기하기 -> 똑같이 색칠하는 느낌으로,,
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

		// 색칠 안 된 부분 찾기
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
		int sum = 1; // 시작점은 기본적으로 포함시킴

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
