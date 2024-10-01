package ����;

import java.util.*;

public class M5014_��ŸƮ��ũ {
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

		// F: ��ü ��, S to G, U or D ��ŭ �������� �� ����
		int result = updown(F, S, G, U, D, check);

		if (S == G) { // �̰� ÷�� ���ؼ� Ʋ��
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
