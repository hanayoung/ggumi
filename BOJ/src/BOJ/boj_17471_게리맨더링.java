package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17471_게리맨더링 {
	static int N;
	static int people[];
	static boolean selected[];
	static List<ArrayList<Integer>> graph;
	static int minDiff;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		minDiff = Integer.MAX_VALUE;
		people = new int[N];
		selected = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			for (int j = 0; j < node; j++) {
				int adj = Integer.parseInt(st.nextToken());
				graph.get(i).add(adj-1);
			}
		}

		devide(0);
		if(minDiff == Integer.MAX_VALUE) {
			minDiff = -1;
		}
		System.out.println(minDiff);
	}

	// 1에서 6까지 구역있음.
	private static void devide(int idx) {
		if (idx == N) {
			List<Integer> listA = new ArrayList<>(); // a선거구
			List<Integer> listB = new ArrayList<>(); // b선거구
			for (int i = 0; i < N; i++) {
				if (selected[i]) { // true 이면 a에 넣음
					listA.add(i);
				} else { // 아니면 b에 넣음
					listB.add(i);
				}
			}
			// 근데 하나는 꼭 포함돼야하니까
			if (listA.size() == 0 || listB.size() == 0) {
				return;
			}
			if (check(listA) && check(listB)) {
				getPeopleDiff();
			}
			return;

		}
		selected[idx] = true;
		devide(idx + 1);
		selected[idx] = false;
		devide(idx + 1);

	}

	private static boolean check(List<Integer> list) {
		Queue<Integer> queue = new LinkedList<>();
		boolean visited[] = new boolean[N];
		
		visited[list.get(0)] = true;
		queue.add(list.get(0));
		int cnt = 1;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int i = 0; i < graph.get(current).size(); i++) {
				int y = graph.get(current).get(i);
				if (list.contains(y) && !visited[y]) {
					queue.add(y);
					visited[y] = true;
					cnt++;
				}
			}
		}
		if (cnt == list.size()) {
			return true;
		} else {
			return false;
		}

	}
	private static void getPeopleDiff() {
		int peopleA = 0, peopleB = 0;
		for(int i = 0; i < N; i++) {
			if(selected[i]) {
				peopleA += people[i];
			}
			else {
				peopleB += people[i];
			}
			
		}
		int diff = Math.abs(peopleA- peopleB); // 두 선거구의 인구차
		minDiff = Math.min(minDiff, diff); // 인구차의 최솟값
		
	}

}
