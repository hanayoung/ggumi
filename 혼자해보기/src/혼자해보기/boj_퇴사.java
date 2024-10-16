package 혼자해보기;

public class boj_퇴사 {
	
	
	    public static void main(String[] args) {
	        // 예시 입력값
	        int[] T = {3, 5, 1, 1, 2, 4, 2}; // 상담 기간
	        int[] P = {10, 20, 10, 20, 15, 40, 200}; // 상담 수익
	        int N = T.length; // 상담 가능한 일 수
	        int[] dp = new int[N + 1]; // 최대 수익 저장할 배열

	        // 반복문으로 매일의 선택을 처리
	        for (int i = 0; i < N; i++) {
	            // 그날 상담을 하지 않고 다음날로 넘길 경우
	            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

	            // 상담을 할 경우, 그 상담이 끝나는 시점까지의 최대 수익 갱신
	            if (i + T[i] <= N) {
	                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
	            }
	        }

	        // 최대 수익 출력
	        System.out.println(dp[N]);
	    }
	}



