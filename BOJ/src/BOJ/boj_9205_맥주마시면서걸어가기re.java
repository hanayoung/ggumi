package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_9205_맥주마시면서걸어가기_플로이드워셜 {
	static int n;
	static int arr[][];
	static int map[][];
	static boolean visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			List<int[]> list = new ArrayList<>(); // 입력받을때 arraylist로 받음
			for (int i = 0; i < n + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list.add(new int[] { x, y }); // 이렇게 하면 list 인덱스 0에 0,0 인덱스 1에 1000,0 2에 1000,1000 3에 2000,1000 이렇게
												// 들어갈거임
			} // 입력끝!
			visited = new boolean[n + 2][n + 2]; // n+2 * n+2 짜리 방문 확인 배열을 만드는데..왜 n+2냔말이지..
			// 지점 i에서 j로 갈수 있는지 없는지 확인
			for (int i = 0; i < n + 2; i++) {// 현재위치가 0~n+2일수 있다.
				for (int j = 0; j < n + 2; j++) { // 다음 위치가 0~n+2일수 있다.
					int[] pos = list.get(i); // 현재위치를 list에서 가져오고
					int[] next = list.get(j); // 다음 위치를 가져온다.
					int dis = Math.abs(pos[0] - next[0]) + Math.abs(pos[1] - next[1]); // 맨해튼 거리로 좌표거리를 계산한다.
					if (dis <= 1000) { // 거리가 1000이하이면
						visited[i][j] = true; // 갈 수 있는 곳이다!
					}
				}
			}

			for (int k = 0; k < n + 2; k++) {
				for (int i = 0; i < n + 2; i++) {
					for (int j = 0; j < n + 2; j++) {
						if (visited[i][k] && visited[k][j]) {
							visited[i][j] = true;
						}
					}
				}
			}
			if (visited[0][n + 1]) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}

		}
	}

}
