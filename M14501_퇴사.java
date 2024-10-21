package น้มุ;

import java.util.*;

public class M14501_ล๐ป็ {
	static int[] nx = { -1, 1, 0, 0 };
	static int[] ny = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] T = new int[N];
		int[] P = new int[N];
		int[] dp = new int[N+1];
		
		for (int i = 0; i < N; i++) {
            T[i] = sc.nextInt();
            P[i] = sc.nextInt(); 
        }

        for (int i = 0; i < N; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            
            if (i + T[i] <= N) { 
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }
        }

        System.out.println(dp[N]);
    }
}