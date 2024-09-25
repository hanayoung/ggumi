import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int queenMap[][]; // 퀸 위치
	static int knightMap[][]; // 나이트 위치
	static int pawnMap[][]; // 폰 위치

	static int[] dx = { 1, 2, 2, 1, -1, -2, -2, -1 }; // 나이트 이동 방향
	static int[] dy = { -2, -1, 1, 2, 2, 1, -1, -2 };

	static int map[][];
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1]; // 체스판 배열
		visited = new boolean[N + 1][M + 1]; // 방문 체크 배열

		// Queen 입력 : 1
		st = new StringTokenizer(br.readLine(), " ");
		int queenNum = Integer.parseInt(st.nextToken());
		queenMap = new int[queenNum + 1][2];

		for (int i = 1; i <= queenNum; i++) {
			queenMap[i][0] = Integer.parseInt(st.nextToken());
			queenMap[i][1] = Integer.parseInt(st.nextToken());

			map[queenMap[i][0]][queenMap[i][1]] = 1;
			visited[queenMap[i][0]][queenMap[i][1]] = true;
			
			
		}

		// Knight 입력 : 2
		st = new StringTokenizer(br.readLine(), " ");
		int knightNum = Integer.parseInt(st.nextToken());
		knightMap = new int[knightNum + 1][2];

		for (int i = 1; i <= knightNum; i++) {
			knightMap[i][0] = Integer.parseInt(st.nextToken());
			knightMap[i][1] = Integer.parseInt(st.nextToken());

			map[knightMap[i][0]][knightMap[i][1]] = 2;
			visited[knightMap[i][0]][knightMap[i][1]] = true;
		}

		// Pawn 입력 : 3
		st = new StringTokenizer(br.readLine(), " ");
		int pawnNum = Integer.parseInt(st.nextToken());
		pawnMap = new int[pawnNum + 1][2];

		for (int i = 1; i <= pawnNum; i++) {
			pawnMap[i][0] = Integer.parseInt(st.nextToken());
			pawnMap[i][1] = Integer.parseInt(st.nextToken());

			map[pawnMap[i][0]][pawnMap[i][1]] = 3;
			visited[pawnMap[i][0]][pawnMap[i][1]] = true;
		}

		// Queen과 Knight의 동작 처리
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 1) { // Queen일 때
					queenCheck(i, j);
				} else if (map[i][j] == 2) { // Knight일 때
					bfs2(i, j);
				}
			}
		}

		// 빈 칸 개수 출력
		int result = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
//				System.out.print(map[i][j] + " ");
				if (map[i][j] == 0)
					result++;
			}
//			System.out.println();
		}

		System.out.println(result);
	}

	// Knight가 이동 가능한 범위를 체크하는 함수
	private static void bfs2(int curX, int curY) {

		for (int i = 0; i < 8; i++) {
			int nextX = curX + dx[i];
			int nextY = curY + dy[i];

			if (nextX > 0 && nextX <= N && nextY > 0 && nextY <= M) { // 체스판 범위 내일 때
				if (!visited[nextX][nextY] && map[nextX][nextY] == 0) {
					map[nextX][nextY] = 4; // 나이트가 이동 가능한 칸 표시
					visited[nextX][nextY] = true;
				}
			}
		}
	}

	// Queen의 동작을 구현하는 함수
	private static void queenCheck(int x, int y) {
	    // 퀸이 이동 가능한 8방향 (상, 하, 좌, 우, 대각선)
	    int[] qdx = { -1, 1, 0, 0, -1, -1, 1, 1 }; // 방향 벡터 x
	    int[] qdy = { 0, 0, -1, 1, -1, 1, -1, 1 }; // 방향 벡터 y

	    // 8방향 모두 체크
	    for (int d = 0; d < 8; d++) {
	        int nx = x;
	        int ny = y;

	        // 각 방향으로 끝까지 이동 (범위 내에서 다른 말이 있으면 멈춤)
	        while (true) {
	            nx += qdx[d];
	            ny += qdy[d];

	            // 체스판 범위를 벗어나면 중단
	            if (nx < 0 || nx >= N+1 || ny < 0 || ny >= M+1) break;

	            // 다른 말(퀸, 나이트, 폰)이 있으면 멈춤
	            if (map[nx][ny] == 3 || map[nx][ny] == 1 || map[nx][ny] == 2) break;

	            // 퀸이 갈 수 있는 빈 칸을 표시
	            map[nx][ny] = 4; // 퀸의 이동 가능 위치로 4 표시
	            visited[nx][ny] = true;
	        }
	    }
	}

}
