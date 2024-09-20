import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			String[] split = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		combi(0, 0);
		
		System.out.println(min);
	}
	private static void combi(int cnt, int start) {
		
		if(cnt == N/2) {
			min = Math.min(min, check(visited));
			return;
		}
		
		for(int i = start; i < N; i++) {
			visited[i] = true;
			combi(cnt + 1, i+1);
			visited[i] = false;
		}
		
	}
	private static int check(boolean[] visited) {
		int team1 = 0;
		int team2 = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i] && visited[j]) {
					team1 += map[i][j];
				}
				else if(!visited[i] && !visited[j]) {
					team2 += map[i][j];
				}
			}
		}
		return Math.abs(team1 - team2);
	}
}