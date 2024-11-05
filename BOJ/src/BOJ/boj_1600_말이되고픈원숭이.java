package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1600_말이되고픈원숭이 {
	static int arr[][];
	static int k, w, h;
	static boolean visited[][][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int hx[] = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int hy[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		arr = new int[h][w];
		visited = new boolean[h][w][k+1]; // 왜 k+1해야하지?!-> k의 횟수가 2라면, 0, 1, 2 모두를 기록해야한다. 그래서 k+1크기가 필요

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs(0, 0)); //시작점 0,0 에서 bfs를 할거다!

	}

// 원숭이가 최소한으로 이동하는 칸 수 move가 return값으로 될거고
	private static int bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>(); //일단 큐 만들고
		visited[y][x][0] = true; //0,0에서는 k값이 0이니까. 방문배열 저렇게 한다.
		queue.add(new int[] { x, y, 0, 0 }); // 현재위치 x,y, k의 횟수, 움직인 횟수(move)
		
		// 이제 큐에 남은게 없을때까지 반복하면서!!
		while (!queue.isEmpty()) {
			int[] current = queue.poll(); //꺼낸애들한테 변수명 달아줄거다
			int px = current[0]; // 현재 x
			int py = current[1]; // 현재 y
			int horse = current[2]; //현재 k값
			int move = current[3]; // 현재 얼마나 이동한 상태인지!
			
			// ** 종료 조건을 달아줘야하는데, 도착점  w-1, h-1에 도착하면 move를 return한다!!!!
			if (px == w - 1 && py == h - 1) {
				return move;
			}
			//원숭이처럼 이동-> dx, dy사용, 4방향
			for (int i = 0; i < 4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				// 범위 체크 해주고
				if (nx < 0 || ny < 0 || nx > w - 1 || ny > h - 1 || arr[ny][nx] == 1 || visited[ny][nx][horse]) {
					continue;
				}
				// 이동가능 할 경우 처리해준다.
				// 이때 원숭이처럼 이동은 k값을 사용하지 않으니까 0이고, 한번 움직일때마다 move에 1더해줘야한다.
				if (arr[ny][nx] == 0) {
					visited[ny][nx][horse] = true;
					queue.add(new int[] { nx, ny, horse, move + 1 });
				}

			}
			
			// 만약에 말처럼 이동할 수 있는 횟수가 남아있다면! 말처럼 이동할건데
			// hx, hy사용. 8방향
			if (horse < k) {
				for (int i = 0; i < 8; i++) {
					int nx = px + hx[i];
					int ny = py + hy[i];
					//범위체크 해주고
					if (nx < 0 || ny < 0 || nx > w - 1 || ny > h - 1 || visited[ny][nx][horse + 1]) {
						continue;
					}
					// 말처럼 이동했으니까 k값 자리에 +1 해준다. 
					// 마찬가지로 한칸 움직였으니까 move+1해줘야한다.
					if (arr[ny][nx] == 0) {
						visited[ny][nx][horse + 1] = true;
						queue.add(new int[] { nx, ny, horse + 1, move + 1 });
					}
				}
			}
		}
		return -1; //도착하지 못할경우 -1를 리턴한다.
	}

}
