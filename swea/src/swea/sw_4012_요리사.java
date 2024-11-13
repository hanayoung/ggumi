package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw_4012_요리사 {
	static int N;
	static int A[];
	static int B[];
	static int arr[][];
	static boolean visited[];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("4012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int [N][N];
			visited = new boolean [N/2];
			A = new int[N/2];
			B = new int[N/2];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 팀 A 구하고 
			
		}
	}

}
