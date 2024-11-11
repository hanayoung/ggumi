package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sw_8275_햄스터 {
	static int N, X, M, max;
	static int arrM[][];
	static int answer[];
	static int cage[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 우리의 갯수
			X = Integer.parseInt(st.nextToken()); // 최대 햄스터
			M = Integer.parseInt(st.nextToken()); // 시행 횟수
			arrM = new int[M][3]; // 입력받을 때 쓸 배열
			cage = new int[N]; // 케이지
			answer = new int[N];
			max = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				arrM[i][0] = Integer.parseInt(st.nextToken()) - 1; // l번째 우리
				arrM[i][1] = Integer.parseInt(st.nextToken()) - 1; // r번째 우리
				arrM[i][2] = Integer.parseInt(st.nextToken()); // 햄스터 합
			}
			dfs(0); // 0번 인덱스에 햄스터 0개부터 시작
			System.out.print("#" + tc + " ");
			if (max == 0) {
				System.out.println("-1");
			} else {
				for (int ans : answer) {

					System.out.print(ans + " ");
				}
				System.out.println();

			}
		}

	}

	// 이 문제에서는 start값이 필요없어서 파라미터에서 빼야함. -> 중복 가능한 모든 경우의 수를 탐색할때는 start필요없음
	// 중복 없이 경우의 수를 찾는 일반적인 조합 문제에서 start필요
	private static void dfs(int idx) {

		if (idx == N) { // cage인덱스값이 우리N값이랑 같아지면 뭔가를 할거임
			for (int i = 0; i < M; i++) {
				int checkSum = 0;// l번째 우리부터 r번째 우리까지 합이 s랑 같아야함
				for (int j = arrM[i][0]; j <= arrM[i][1]; j++) {
					checkSum += cage[j];
				}
				if (checkSum != arrM[i][2]) { // 다르면 이전 dfs로 돌아가라
					return;
				}
//				if(checkSum == arrM[i][2]) {
//					answer = Arrays.copyOf(cage, N);
//				}
//				else {
//					return;
//				}
			}
			int sum = 0;
			for (int i = 0; i < N; i++) {
				sum += cage[i];
			}
			if (sum > max) {
				max = sum;
				answer = Arrays.copyOf(cage, N);

			}
			return; // 조건 만족해서 이제 이번 dfs는 끝났으니까 다시 이전 dfs로 돌아가라?     

		}
		for (int i = 0; i <= X; i++) {
			cage[idx] = i;
			dfs(idx + 1);
		}

	}

}
