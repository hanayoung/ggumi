package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
	int end, weight;

	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}

public class M_1753_최단경로 {

	static int v, e, k;
	static List<Node>[] list;
	static int[] dist;
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] split = in.readLine().split(" ");

		v = Integer.parseInt(split[0]);
		e = Integer.parseInt(split[1]);
		k = Integer.parseInt(in.readLine());

		list = new ArrayList[v + 1];
		dist = new int[v + 1];
		
		Arrays.fill(dist, INF);
		for (int i = 1; i <= v; i++) {
			list[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < e; i++) {
			split = in.readLine().split(" ");
			int start = Integer.parseInt(split[0]);
			int end = Integer.parseInt(split[1]);
			int weight = Integer.parseInt(split[2]);

			list[start].add(new Node(end, weight));
		}

		List<String> ans = new ArrayList<String>();

		dijkstra(k);
		for (int i = 1; i <= v; i++) {
			if (dist[i] == INF) {
				ans.add("INF");
			} else {
				ans.add(dist[i] + "");
			}
		}

		for(String a: ans) {
			System.out.println(a);
		}

	}

	private static void dijkstra(int start) {

		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean[] check = new boolean[v + 1];
		queue.add(new Node(start, 0));
		dist[start] = 0;

		while (!queue.isEmpty()) {
			Node currNode = queue.poll();
			int curr = currNode.end;

			if (check[curr] == true)
				continue;
			check[curr] = true;

			for (Node node : list[curr]) {
				if (dist[node.end] > dist[curr] + node.weight) {
					dist[node.end] = dist[curr] + node.weight;
					queue.add(new Node(node.end, dist[node.end]));
				}
			}

		}

	}

}
