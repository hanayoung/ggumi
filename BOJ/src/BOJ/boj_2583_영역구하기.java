package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2583_영역구하기 {
	static int M;
	static int N;
	static int K;
	static int arr[][];
	static int leftX;
	static int leftY;
	static int rightX;
	static int rightY;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[M][N];
		visited = new boolean[M][N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			leftX = Integer.parseInt(st.nextToken());
			leftY = Integer.parseInt(st.nextToken());
			rightX = Integer.parseInt(st.nextToken());
			rightY = Integer.parseInt(st.nextToken());

			// 제시된 사각형범위는 1로 채우기
			for (int y = leftY; y < rightY; y++) {
				for (int x = leftX; x < rightX; x++) {
					arr[y][x] = 1;
				}
			}
		}

		// 영역의 크기를 저장할 공간이 필요
		ArrayList<Integer> areas = new ArrayList<>();

		// BFS 탐색. 모든 배열을 돌기
		// 빈영역 탐색해야 구역갯수와, 넓이를 알 수 있다.
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0 && !visited[i][j]) { //빈 영역이고, 방문 안했으면 
					areas.add(bfs(i, j)); // bfs반환값을 areas 배열에 저장한다. 
				}
			}
		}

		// 출력
		Collections.sort(areas); // 오름차순 정렬
		System.out.println(areas.size()); // 배열안에 몇개 있는지 = 구역 몇개인지
		for (int area : areas) { // 각각 구역의 넓이를 출력
			System.out.print(area + " ");
		}

	}
	// bfs함수
	private static Integer bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>(); // 큐 생성
		queue.offer(new int[] { x, y }); // 큐에 처음 위치 x, y를 넣음
		visited[x][y] = true; // 처음 위치는 방문한걸로 체크
		int areaSize = 0; //영역사이즈 = 넓이

		while (!queue.isEmpty()) {
			int[] current = queue.poll(); //지금 뭐 꺼냈는지 확인
			int cx = current[0]; // 지금 꺼낸거 0번인덱스는 cx
			int cy = current[1]; // 지금 꺼낸거 1번 인덱스는 cy
			areaSize++; //꺼낼때마다 영역사이즈 증가시키기

			// 상하좌우 이동
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (nx < 0 || ny < 0 || nx >= M || ny >= N || visited[nx][ny] == true || arr[nx][ny] == 1) {
					continue;
				}
				queue.offer(new int[] { nx, ny }); //범위에 맞는 영역은 큐에 다시 넣기
				visited[nx][ny] = true;
			}

		}
		return areaSize;
	}

}
