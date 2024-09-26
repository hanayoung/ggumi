package 혼자해보기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2583_영역구하기 {

	static int M, N, K;
	static int arr[][];
	static boolean visited[][];
	static int leftX, leftY, rightX, rightY;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

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

			for (int y = leftY; y < rightY; y++) {
				for (int x = leftX; x < rightX; x++) {
					arr[y][x] = 1;
					
				}
			}
		}
		// 영역 저장해줄 배열 필요함
		ArrayList<Integer> areas = new ArrayList<>();

		// 모든 배열 돌면서 bfs 확인하기
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0 && !visited[i][j]) {
					areas.add(bfs(i, j));
				}

			}

		}
		Collections.sort(areas);
		System.out.println(areas.size());
		for (int i = 0; i < areas.size(); i++) {
			System.out.print(areas.get(i) + " ");
		}

	}

	private static Integer bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { x, y });
		visited[x][y] = true;
		int areaSize = 0;

		while (!queue.isEmpty()) {
			int current[] = queue.poll();
			int cx = current[0];
			int cy = current[1];
			areaSize++;

			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (nx < 0 || ny < 0 || nx >= M || ny >= N || visited[nx][ny] == true || arr[nx][ny] == 1) {
					continue;
				}
				queue.offer(new int[] { nx, ny });
				visited[nx][ny] = true;
				
			}
		}
		return areaSize;

	}
}
