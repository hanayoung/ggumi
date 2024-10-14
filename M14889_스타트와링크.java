package 백준;

import java.util.*;

public class M14889 {
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] arr = new int[N][N];
		int[] visited = new int[N]; // dfs 방문 체크

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		dfs(N, arr, 0, 0, visited);

		System.out.println(min);

	}

	public static void dfs(int N, int[][] arr, int cnt, int index, int[] visited) {
		int startGrade = 0;
		int linkGrade = 0;

		if (cnt == N / 2) {
			// 능력치 계산
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i] == 1 && visited[j] == 1) { // 둘 다 방문했으면 start 팀
						startGrade += arr[i][j];
					} else if (visited[i] == 0 && visited[j] == 0) { // 둘 다 방문안했으면 link 팀
						linkGrade += arr[i][j];
					}

				}
			}

			int d = Math.abs(startGrade - linkGrade);
			min = Math.min(min, d);

		} 
		
		if (index == N) { // 예외 처리
			return;
		}
		
		visited[index] = 1; // 현재 선수를 팀에 넣는 경우
		dfs(N, arr, cnt + 1, index + 1, visited);

		visited[index] = 0; // 안해주면 모든 경우 탐색 불가 -> 오답
		dfs(N, arr, cnt, index + 1, visited); // 현재 선수 팀에 안넣고 넘어가는 경우

	}

}
