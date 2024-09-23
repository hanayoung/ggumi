import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, sr, sc, count;
	static int[][] map;
	static int[][] deltas = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 1: 상, 2: 하, 3: 좌, 4: 우
	static int[] dir;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		int K = Integer.parseInt(br.readLine());
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1; // 장애물
		}
		
		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		map[sr][sc] = 1; // 로봇 초기 위치 방문표시
		
		dir = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<4;i++) {
			dir[i] = Integer.parseInt(st.nextToken()); // 방향 입력
		}
		
		count = 0;
		loop(0);
		
		System.out.println(sr+" "+sc); // 로봇의 마지막 위치 출력
	}
	
	static void loop(int d) {
		while(true) {
			int nextR = sr+deltas[dir[d]][0];
			int nextC = sc+deltas[dir[d]][1];
		
			if (nextR>=0 && nextR<R && nextC>=0 && nextC<C && map[nextR][nextC]==0) {
				map[nextR][nextC] = 1;
				sr = nextR; sc = nextC;
				count = 0;
			} 
			// 막혔다면 다음 방향
			else {
				d=(d+1)%4; 
				count++;
			}
			
			if (count==4) break;
		}
	}
}