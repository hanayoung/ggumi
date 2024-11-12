package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class sw_4012_요리사re {
	static int A[];
	static int B[];
	static int arr[][];
	static int N, min;
	static int combi[];
	static boolean visited[];
	static List<int[]> teamA = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("4012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			A = new int[N / 2]; // A팀이 아닌 팀은 B
			B = new int[N / 2];

			visited = new boolean[N];
			combi = new int[N];// 조합 돌릴 배열
			min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				combi[i] = i;
			}

			dfs(0, 0);
			System.out.println("#"+tc+" " +min);
	}

	private static void dfs(int idx, int start) {

		if (idx == N / 2) {
//			System.out.println(Arrays.toString(A));
			int idxB = 0;
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					B[idxB++] = combi[i];
				}
			}
//			System.out.println(Arrays.toString(B));
			int sumA = 0;
			int sumB = 0;

			for (int i = 0; i < N / 2; i++) {
				for (int j = i + 1; j < N / 2; j++) {
					sumA += arr[A[i]][A[j]] + arr[A[j]][A[i]];
					sumB += arr[B[i]][B[j]] + arr[B[j]][B[i]];
				}
			}
			min = Math.min(Math.abs(sumA - sumB), min);

			return;
		}

		for (int i = start; i < N; i++) {
			A[idx] = combi[i];
			visited[i] = true;
			dfs(idx + 1, i + 1);
			visited[i] = false;
		}
	}

}
