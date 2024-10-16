package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1074_Z {
	// 분할정복
	static int N, r, c, size, half;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		size = (int) Math.pow(2, N);

		System.out.println(zOrder(size, r, c));
	}

	private static int zOrder(int size, int r, int c) {
		// 더이상 쪼갤 수 없을때
		if (size == 1) {
			return 0;
		}
		
		half = size / 2;

		// 좌상단일때
		if (r < half && c < half) {
			return zOrder(half, r, c);
		}
		// 우상단일때
		if (r < half && c >= half) {
			return half * half + zOrder(half, r, c - half);
		}
		// 좌하단일때
		if (r >= half && c < half) {
			return 2 * half * half + zOrder(half, r - half, c);
		}
		// 우하단일때
		else {
			return 3 * half * half + zOrder(half, r - half, c - half);
		}

	}

}
