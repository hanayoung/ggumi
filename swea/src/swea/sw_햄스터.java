package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sw_햄스터 {
	static int N, X, M; // N우리갯수 X최대 햄스터 M시행횟수 l우리번호 r우리번호 s햄스터합
	static int arrM[][]; // M을 입력받을 배열
	static int cage[];
	static int answer[];

	static int sumHamster, max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 우리 갯수
			X = Integer.parseInt(st.nextToken()); // 최대 햄스터 수
			M = Integer.parseInt(st.nextToken()); // 시행횟수
			arrM = new int[M][3];
			cage = new int[N]; 
			answer = new int[N];
			
			max = -1;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				arrM[i][0] = Integer.parseInt(st.nextToken())-1; // l번째 우리
				arrM[i][1] = Integer.parseInt(st.nextToken())-1; // r번째 우리
				arrM[i][2] = Integer.parseInt(st.nextToken()); // 햄스터의합
			}

			dfs(0);
			
			//출력
			System.out.print("#" + tc + " ");
			if (max == -1) {
				System.out.println("-1");
			} else {
				for (int i = 0; i < N; i++) {
					System.out.print(answer[i] + " ");
				}
				System.out.println();
			}
		}
	}
	
	// idx는 우리 안에서 몇번째 인덱스이냐.
	private static void dfs(int idx) {
		if(idx == N) { //케이지에 숫자 다 넣으면
			for(int i = 0; i < M; i++) { //l번째케이지 햄스터 수~ r번째 케이지 햄스터 수랑 합이 s와 같은지 비교한다.
				sumHamster = 0; // 000일때 비교 다 해봤으면 초기화해줘야함
				for(int j = arrM[i][0]; j<= arrM[i][1]; j++) {
					sumHamster += cage[j];
				}
				if(sumHamster != arrM[i][2]) return; // 합해봤는데 다르면 return. 다시 아래쪽 for문으로 돌아간다.
			}
			
			// 위에 조건 맞으면 여기서 조건에 맞는 경우를 answer배열에 넣어서 출력해줄거임.
			int sum = 0;
			for(int i = 0; i < N; i++) {
				sum += cage[i];
			}
			// max값 구하기
			if(sum > max) {
				max = sum;
				answer = Arrays.copyOf(cage, N); //cage배열의 0부터 N개의 요소를 복사해서 answer배열에 넣음.
			}	
			return;
		}
		// 케이지에 일단 0부터 넣기
		for(int i = 0; i <= X; i++) { 
			cage[idx] = i; //idx가 0부터 시작. 0번 케이지에 0넣고, 
			dfs(idx+1); //dfs(1)호출
//			System.out.print(Arrays.toString(cage)); //경우의수가 어떻게 출력되는지 볼 수 있다.
		}

	}
}
