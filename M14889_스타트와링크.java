package ����;

import java.util.*;

public class M14889 {
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] arr = new int[N][N];
		int[] visited = new int[N]; // dfs �湮 üũ

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
			// �ɷ�ġ ���
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i] == 1 && visited[j] == 1) { // �� �� �湮������ start ��
						startGrade += arr[i][j];
					} else if (visited[i] == 0 && visited[j] == 0) { // �� �� �湮�������� link ��
						linkGrade += arr[i][j];
					}

				}
			}

			int d = Math.abs(startGrade - linkGrade);
			min = Math.min(min, d);

		} 
		
		if (index == N) { // ���� ó��
			return;
		}
		
		visited[index] = 1; // ���� ������ ���� �ִ� ���
		dfs(N, arr, cnt + 1, index + 1, visited);

		visited[index] = 0; // �����ָ� ��� ��� Ž�� �Ұ� -> ����
		dfs(N, arr, cnt, index + 1, visited); // ���� ���� ���� �ȳְ� �Ѿ�� ���

	}

}
