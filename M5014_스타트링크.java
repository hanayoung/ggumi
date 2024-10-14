package 백준;

import java.util.*;

public class M5014_스타트링크 {
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int F = sc.nextInt();
		int S = sc.nextInt();
		int G = sc.nextInt();
		int U = sc.nextInt();
		int D = sc.nextInt();

		int[] check = new int[10000001];
		Arrays.fill(check, -1);

		// F: 전체 층, S to G, U or D 만큼 오르내릴 수 있음
		int result = updown(F, S, G, U, D, check);

		if (S == G) { // 이거 첨에 안해서 틀림
			System.out.println(result);
			return;
		}

		if (result == -1) {
			System.out.println("use the stairs");
		} else {
			System.out.println(result);
		}

	}

	public static int updown(int F, int S, int G, int U, int D, int[] check) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(S);
		check[S] = 0;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			if (current == G) {
				return check[current];
			}

			for (int next : new int[] { current - D, current + U }) {
				if (next >= 1 && next <= F && check[next] == -1) {
					check[next] = check[current] + 1;
					queue.add(next);
				}
			}
		}

		return -1;
	}
}
