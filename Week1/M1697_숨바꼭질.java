package ����;

import java.util.*;

public class M1697_���ٲ��� {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // ���� ��ġ
		int K = sc.nextInt(); // ���� ��ġ

		int[] check = new int[100001]; // �̵� Ƚ��
		Arrays.fill(check, -1);

		int result = find(N, K, check);
		System.out.println(result);
	}

	public static int find(int n, int k, int[] check) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(n);
		check[n] = 0;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			if (current == k) {
				return check[current];
			}

			for (int next : new int[] { current - 1, current + 1, current * 2 }) {
				if (next >= 0 && next <= 100000 && check[next] == -1) {
					check[next] = check[current] + 1;
					queue.add(next);
				}
			}
		}

		return -1;
	}
}
