package 백준;

import java.util.*;

public class M1890_점프 {
	static int[] nx = { 1, 0 };
	static int[] ny = { 0, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] arr = new int[N][N];
		long[][] memo = new long[N][N]; // 경로 개수 저장

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
				memo[i][j] = -1;
			}
		}

		// 경로의 개수 구하기
		long cnt = findFinal(N, 0, 0, arr, memo);
		
		System.out.println(cnt);

	}

	public static long findFinal(int N, int x, int y, int[][] arr, long[][] memo) {
		if(x == N-1 && y == N-1) {
			return 1;
		}
		
		if(memo[x][y] != -1) { // 계산된 장소라면 경로 개수 반환
			return memo[x][y];
		}
		
		memo[x][y] = 0;
		
		for(int i = 0; i<2; i++) { //오른쪽, 아래로만 이동 가능
			int dx = x + nx[i] * arr[x][y];
			int dy = y + ny[i] * arr[x][y];
			
			if(dx >= 0 && dx < N && dy >= 0 && dy < N && arr[x][y] > 0) {
				memo[x][y] += findFinal(N, dx, dy, arr, memo);
			}
		}
		return memo[x][y]; 
	}
}
