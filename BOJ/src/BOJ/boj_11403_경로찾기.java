package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11403_경로찾기 {
	// 모든 정점 쌍 사이에 경로가 있는지 -> 각 정점에서 다른 모든 정점으로 갈 수 있는지
	// 모든쌍 문제는 플로이드 워셜
	// 인접행렬
	
	static int N;
	static int arr[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 플로이드-워셜
		for (int k = 0; k < N; k++) { // 경유지
			for (int i = 0; i < N; i++) { // 출발지
				for (int j = 0; j < N; j++) { // 도착지
					if (arr[i][k] == 1 && arr[k][j] == 1) { //i->k 간선 있고, k->j간선있으면
						arr[i][j] = 1; //i->j간선있음
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
