package algo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
크루스칼 알고리즘 구현 - union 사용
- 정렬 시, 오름차순으로 정렬해야 한다.
- 처음 좌표 간의 거리를 구할 때, |x[i+1] - x[1]| + |y[i+1] - y[i]| 이렇게 구해서 넣어줬는데, 모든 좌표 간의 거리를 구해줘야했다.
그리고 어짜피 L^2 할 때 제곱을 해줘야하므로 처음부터 L^2를 구한다.
 
 */

/*
*** 좌표값을 쓰면 안되는 이유(GPT 답변) ***
여기서 from과 to는 정점의 번호를 나타냅니다.
i와 j는 각각 두 섬의 정점 번호를 나타내고, 이를 사용해 두 섬 간의 거리를 계산한 후, 간선 Edge(i, j, distance)를 생성합니다.
왜 좌표 값을 사용하면 안 되는가?

x[i]와 y[i]는 각각 섬의 좌표 값입니다. 이 좌표 자체가 간선의 출발지와 목적지를 나타내는 것이 아니라, 해당 좌표를 가진 정점들의 번호가 중요합니다.
간선은 "정점 번호 간의 연결"을 나타내는 것이지, 좌표 간의 연결을 직접 다루지 않습니다. 정점 번호 i, j를 사용해서 두 정점 간 거리를 계산하고, 이를 간선에 할당하는 것이 맞는 방식입니다.

*/
public class S_1251_하나로 {

	private static int[] parents;
	private static int n;
	private static double e;
	private static PriorityQueue<Edge> pre;

	static class Edge implements Comparable<Edge> {

		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {

			return Double.compare(this.weight, o.weight); // 오름차순
		}

	}

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st;

		for (int t = 1; t <= T; t++) {

			n = Integer.parseInt(in.readLine());
			pre = new PriorityQueue<Edge>();
			parents = new int[n];

			st = new StringTokenizer(in.readLine());
			int[] x = new int[n];
			for (int i = 0; i < n; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(in.readLine());
			int[] y = new int[n];
			for (int i = 0; i < n; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}

			// 모든 좌표 쌍에 대해 거리 계산해 간선 추가
			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {
					double distance = Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2);
					pre.offer(new Edge(i, j, distance));
				}

			}

			e = Double.parseDouble(in.readLine()); // 환경 부담 세율
			// 지불 금액 = E * L^2

			makeSet();

			double cost = 0.0;
			// int edgeCnt = 0;
			while (!pre.isEmpty()) {

				Edge edge = pre.poll();
				if (union(edge.from, edge.to)) {
					cost += e * edge.weight;
					// edgeCnt++;
				}
			}

			System.out.printf("#%d %.0f\n", t, cost);

		}
	}

	private static void makeSet() {

		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}

	private static int find(int v) {
		if (parents[v] == v) {
			return v;
		} else {
			return parents[v] = find(parents[v]);
		}
	}

	private static boolean union(int from, int to) {

		from = find(from);
		to = find(to);

		if (from == to) {
			return false;
		}
		parents[from] = to;

		return true;
	}

}
