import java.util.*;

public class M2468_안전영역 {
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		int maxCnt = 1; // 최소 영역 1로 초기화함

		for (int h = 1; h <= 100; h++) {
			visited = new boolean[N][N]; // 높이마다 다시 초기화해야됨
			int cnt = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && arr[i][j] > h) {
						rainDFS(N, arr, h, i, j);
						cnt++;
					}
				}
			}
			
			maxCnt = Math.max(maxCnt, cnt);
		}

		System.out.println(maxCnt);

	}

	public static void rainDFS(int N, int[][] arr, int height, int x, int y) {
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];

			if (xx >= 0 && xx < N && yy >= 0 && yy < N && visited[xx][yy] != true && arr[xx][yy] > height) {
				visited[xx][yy] = true;
				rainDFS(N, arr, height, xx, yy);
			}
		}

	}
}
