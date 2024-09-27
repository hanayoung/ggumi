package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class M_2156_포도주시식 {

	private static int n, m;
	private static int[] grapes;
	private static int[] dp;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());

		grapes = new int[n];
		dp = new int[n];
		for (int i = 0; i < n; i++) {
			grapes[i] = Integer.parseInt(in.readLine());
		}

		// 6 10 9 8                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
		dp[0] = grapes[0];
		for (int i = 1; i < n; i++) {
			dp[i]= Math.max(dp[i-1], i) ;
		}

		System.out.println(Arrays.toString(dp));

	}

}
