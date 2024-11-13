package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_zzz {
	static int cnt;
	static int N, r, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		int size = (int) Math.pow(2, N);
		cnt = 0;

		find(r, c, size);
		System.out.println(cnt);
	}

	private static void find(int r, int c, int size) {
		int half = size / 2;
		if (size == 1) {
			return;
		}
		// 좌상
		if (r < half && c < half) {
			find(r, c, half);
		}
		// 우상
		else if (r < half && c >= half) {
			cnt += half * half;
			find(r, c - half, half);
		}
		// 좌하
		else if (r >= half && c < half) {
			cnt += half * half * 2;
			find(r - half, c, half);
		}
		// 우하
		else if (r >= half && c >= half) {
			cnt += half * half * 3;
			find(r - half, c - half, half);
		}

	}

}
