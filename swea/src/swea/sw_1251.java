package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class sw_1251 {

	// prim 알고리즘 사용
	// 시작노드에서 출발. 방문하지 않은 노드 중 가장 적은 비용으로 연결 할 수 잇는 노드를 계속해서 선택
	// 1. 초기화
	// 하나의 시작 노드 선택-> 임의로 0번 섬부터 시작
	// 해당 노드를 시작점으로, 그 노드를 연결하는 최소 비용을 기록하는 배열 초기화 mknEdge[]
	// 방문한 섬을 체크하는 visited[] 사용
	// 2. 우선순위큐로 연결할 노드 선택
	// 현재까지 방문한 섬에서 가장 비용이 적게드는 경로 선택. 비용이 적은 경로를 찾아 우선방문
	// 3. 다른 섬과의 연결비용 계산
	// 두 섬 사이의 거리 공식 이용. 거리의 제곱에 해당하는 비용 계산. E계산
	// double dist = getDistance(x[current.idx], y[curretn.idx], x[i], y[i]에서 두섬 간의
	// 거리를 계산. 그 값을 이용해 cost구하기
	// 4. 다른 섬으로 이동
	// 방문하지 않은 섬중에서 비용이 최소인 섬으로 이동, 그 섬과 연결된 다른 섬들을 대상으로 같은 과정 반복
	// 5. 최종 비용 계산

	static int N; // 섬의 갯수
	static long[] X; // 섬들의 정수x좌표
	static long[] Y; // 섬들의 정수 y좌표
	static double E; // 환경 부담 세율 실수 E

	// 우선순위 큐가 내부적으로 edge객체를 정렬하게 할거에요
	// 객체를 서로 비교할건데, 우선순위 큐에서 비용에 따라 정렬할겁니다
	// 우선순위큐는 가장 작은 값을 우선 처리합니다
	static class Edge implements Comparable<Edge> {
		int island;
		long cost;

		public Edge(int island, long cost) {
			this.island = island;
			this.cost = cost;
		}

		// compareto 메서드는 우선순위큐가 어떤 순서로 edge객체를 정렬할지 정의
		// this.cost->현재 객체 비용, o.cost->비교할 대상 객체 비용 => 작은 edge가 앞에 오도록
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}

	}

	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 섬의 개수
			// 각 섬은 2차원 좌표평면에서 특정위치에 있다.-> 섬들의 좌표를 저장할 배열 필요
			// 2차원 배열을 사용하지 않는 이유는 prim 알고리즘에서는 각 섬간의 거리가 더 중요하기 때문
			X = new long[N];
			Y = new long[N];

			// 섬 좌표 입력
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++)
				X[i] = Long.parseLong(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				Y[i] = Long.parseLong(st.nextToken());

			E = Double.parseDouble(br.readLine());

			// prim 알고리즘 시작
			long[] minEdge = new long[N];
			boolean[] visited = new boolean[N];
			// minEdge의 모든 요소를 double.max_value로 초기화
			// arrays.fill. 모든 요소를 특정값으로 채울 때 사용. (배열, 배열에 채울 값)
			Arrays.fill(minEdge, (long) Double.MAX_VALUE);

			// 임의의 시작점 설정
			minEdge[0] = 0;

			// 우선순위 큐 쓸거얌~~ edge는 비용이구염, 비용이 적은거 먼저
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			pq.add(new Edge(0, 0)); // 0번 섬으로 가는 비용이 0.0이다.

			long result = 0; // 최종 결과를 저장하는 변수를 만들어주고
			int cnt = 0; // 방문한 섬 개수 세야해요

			while (!pq.isEmpty()) { // 큐가 빌때까지 가장 비용이 적은 섬 선택할구얌
				Edge current = pq.poll(); // 현재비용이 가장적은 섬 edge꺼내기

				if (visited[current.island])
					continue; // 이미 방문했으면 넘어가기

				visited[current.island] = true; // 방문한 친구 true로 바꿔주고
				result += current.cost; // result 변수에 현재까지의 비용 계속 더해주기

				if (++cnt == N)
					break; // cnt먼저 증가시키고 조건 확인

				for (int i = 0; i < N; i++) {
					if (!visited[i]) {
						long dist = getDistance(X[current.island], Y[current.island], X[i], Y[i]);
						long cost = Math.round(dist * E);

						if (minEdge[i] > cost) {
							minEdge[i] = cost;
							pq.add(new Edge(i, cost));

						}
					}
				}
			}
			System.out.println("#" + tc + " " + Math.round(result));
		}
	}

	private static long getDistance(long x1, long y1, long x2, long y2) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}
}
