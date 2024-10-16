package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

// 물통에 물을 담을 수 있는 모든 경우의 수를 구해야 한다.
// 3! 이므로 6가지

public class M_2251_물통 {

	private static int A, B, C;
	// 물의 양이 아닌 물통의 상태(A, B, C)를 모두 고려해야해 3차원 배열 필요
	private static boolean[][][] visited;
	private static List<Integer> result;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] split = in.readLine().split(" ");

		A = Integer.parseInt(split[0]);
		B = Integer.parseInt(split[1]);
		C = Integer.parseInt(split[2]); // 가득 차있는 물통

		visited = new boolean[A + 1][B + 1][C + 1];
		result = new ArrayList<>();
		calc(0, 0, C);

		Collections.sort(result);
		for(int re : result) {
			System.out.print(re + " ");
		}
		
	}

	private static void calc(int a, int b, int c) {

		if (visited[a][b][c])
			return;

		visited[a][b][c] = true;

		// a가 비었을때, c 물의 양을 추가
		if (a == 0) {
			result.add(c);
		}

		// 물을 이동시키는 모든 경우의 계산
		// 1. A -> B
		if (a + b > B) {
			calc(a + b - B, B, c); // A의 일부만 B로 이동됨
		} else {
			calc(0, a + b, c); // A의 모든 물 B로 이동
		}

		// 2. A -> C
		if (a + c > C) { // c에 a 부었는데, 넘침
			calc(a + c - C, b, C);
		} else {
			calc(0, b, a + c);
		}
		
		// 3. B -> A
		if (a + b > A) {
			calc(A, b + a - A, c);
		} else {
			calc(a + b, 0, c); 
		}
		
		// 4. B -> C
		if (b + c > C) calc(a, b + c - C, C);
		else calc(a, 0, b + c);
		
		// 5. C -> A
		if (a + c > A) calc(A, b, a + c - A);  // C의 일부를 A로 이동
        else calc(a + c, b, 0);  // C의 모든 물을 A로 이동
		
		// 6. C -> B
		 if (b + c > B) calc(a, B, b + c - B);  // C의 일부를 B로 이동
	     else calc(a, b + c, 0);  // C의 모든 물을 B로 이동

	}

}
