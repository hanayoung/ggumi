package ����;

import java.util.*;

public class M1890_���� {
	static int[] nx = { 1, 0 };
	static int[] ny = { 0, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] arr = new int[N][N];
		long[][] memo = new long[N][N]; // ��� ���� ����

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
				memo[i][j] = -1;
			}
		}

		// ����� ���� ���ϱ�
		long cnt = findFinal(N, 0, 0, arr, memo);
		
		System.out.println(cnt);

	}

	public static long findFinal(int N, int x, int y, int[][] arr, long[][] memo) {
		if(x == N-1 && y == N-1) {
			return 1;
		}
		
		if(memo[x][y] != -1) { // ���� ��Ҷ�� ��� ���� ��ȯ
			return memo[x][y];
		}
		
		memo[x][y] = 0;
		
		for(int i = 0; i<2; i++) { //������, �Ʒ��θ� �̵� ����
			int dx = x + nx[i] * arr[x][y];
			int dy = y + ny[i] * arr[x][y];
			
			if(dx >= 0 && dx < N && dy >= 0 && dy < N && arr[x][y] > 0) {
				memo[x][y] += findFinal(N, dx, dy, arr, memo);
			}
		}
		return memo[x][y]; 
	}
}
