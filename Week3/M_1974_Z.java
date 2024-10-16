package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M_1974_Z {

	static int N, r, c;
	static long result;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		result = 0;
		fill(0, 0, N);
		
		System.out.println(result);
	}

	private static void fill(int x, int y, int size) {

		if (size == 1) {
			return;
		}

		int half = size / 2;

		if (r < x + half && c < y + half) { // 좌상(시작 점)

			fill(x, y, half);

		} else if (r < x + half && c >= y + half) { // 우상

			result += half * half;
			fill(x, y + half, half);

		} else if (r >= x + half && c < y + half) { // 좌하

			result += 2 * half * half;
			fill(x + half, y, half);

		} else {
			result += 3 * half * half;
			fill(x + half, y + half, half);
		}
	}

}
