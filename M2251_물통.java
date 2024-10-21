package 백준;

import java.util.*;

public class M2251_물통 {
	static int[] max = new int[3];
	static List<Integer> result = new ArrayList<>();
	static boolean[][][] visited = new boolean[201][201][201];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 3; i++) {
			max[i] = sc.nextInt();
		}

		bfs();
		
		Collections.sort(result);
		for(int r : result) {
			System.out.print(r + " ");
		}

	}

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 0, 0, max[2] });
		visited[0][0][max[2]] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int a = cur[0], b = cur[1], c = cur[2];

			if (a == 0) {
				result.add(c);
			}

			// 물 옮기기
			pourWater(queue, a, b, c, 0, 1);
			pourWater(queue, a, b, c, 0, 2);
			pourWater(queue, a, b, c, 1, 0);
			pourWater(queue, a, b, c, 1, 2);
			pourWater(queue, a, b, c, 2, 0);
			pourWater(queue, a, b, c, 2, 1);

		}
	}

	private static void pourWater(Queue<int[]> queue, int a, int b, int c, int from, int to) {
		int[] current = { a, b, c };
		int[] next = { a, b, c };
		
		int[] maxCapacity = {max[0], max[1], max[2]};
		
		if(current[from] > 0 && current[to] < maxCapacity[to]) {
			int move = Math.min(current[from], maxCapacity[to] - current[to]);
			next[from] -= move;
			next[to] += move;
			
			if(!visited[next[0]][next[1]][next[2]]) {
				visited[next[0]][next[1]][next[2]] = true;
				queue.add(new int[] {next[0], next[1], next[2]});
			}
		}

	}
}
