package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14888_연산자끼워넣기 {
	
	// dfs 백트래킹
	static int number[];
	static int yeonsan[];
	static int N;
	static int MAX;
	static int MIN;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		number = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		yeonsan = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i <4; i++ ) {
			yeonsan[i] = Integer.parseInt(st.nextToken());
	}
		
		MAX = Integer.MIN_VALUE;
		MIN = Integer.MAX_VALUE;
				
		
		dfs(number[0], 1);
		
		System.out.println(MAX);
		System.out.println(MIN);
	}

	private static void dfs(int num, int idx) {
		// 모든 수를 다 사용한 시점은 idx ==N 이다. 최종값 계산을 위해 idx == n-1이아니라 idx == n 해야함
		if(idx == N) {
			MAX = Math.max(MAX, num);
			MIN = Math.min(MIN, num);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			//연산자가 1개 이상 남아있을 때만 실행
			if(yeonsan[i] > 0) {
				yeonsan[i]--; // 해당 연산자 개수 감소
				
				// 연산자 재귀 호출
				switch(i) {
				case 0: dfs(num + number[idx], idx + 1); break;
				case 1: dfs(num - number[idx], idx + 1); break;
				case 2: dfs(num * number[idx], idx + 1); break;
				case 3: dfs(num / number[idx], idx + 1); break;
				}
				
				// 위에서 사용해서 빼준 연산자를 다른 경로에서 사용하기 위해 다시 복구
				yeonsan[i]++;
			}
		}
		
	}
}


