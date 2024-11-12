package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw_나무높이 {
	// 홀수날 -> 나무높이 +1, 짝수날 -> 나무높이 +2, 물 안주면 -> +0
	static int N;
	static int tree[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("나무높이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			tree = new int[N];
			int maxHeight = 0;
			int cntNothing = 0;
			int answerDay = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, tree[i]);
			}
			int cnt1 = 0, day1 = 0;
			int cnt2 = 0, day2 = 0;
			int cnt0 = 0, day0 = 0;
			
			for (int i = 0; i < N; i++) {

				int diff = (maxHeight - tree[i]);

				if (tree[i] != maxHeight) {
					int mod = diff % 3; // 3으로 나눈 나머지
					if (mod == 0) {
						day0 += diff /3*2;
						cnt0++;
					} else if (mod == 1) {
						day1 += diff/3*2 +1;
						cnt1++;
					} else if (mod == 2) {
						day2 += diff/3 *2 +2;
						cnt2++;
					}
				}
			}
			answerDay = day0+day1+day2;
			
			if(cnt2<=cnt1) {
				cntNothing = cnt2;
				answerDay = answerDay - cnt2;
				System.out.println(answerDay);
			}
			else {
				cntNothing = cnt1;
				answerDay = answerDay - cnt1;
				System.out.println(answerDay);
			}



		}

	}

}
