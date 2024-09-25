package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14889_스타트와링크 {

	static int N;
	static int s[][];
	static boolean[] visited;
	static int minDifference = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		s = new int[N][N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				s[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 백트래킹으로 조합을 구현. 팀 나누기
		divideTeam(0, 0);
		System.out.println(minDifference);
	}

	// 백트래킹으로 조합을 구현...개쩐당
	//idx 현재 탐색할 사람 인덱스 몇번재 사람을 선택할지
	//count 현재까지 선택된 사람 수. N/2명 선택하면 더이상 선택안함
	private static void divideTeam(int idx, int count) {
		if (count == N / 2) {
			calculateDifference();
			return;
		}

		for (int i = idx; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				divideTeam(i + 1, count + 1);
				visited[i] = false;
			}
		}

	}

	private static void calculateDifference() {
		int startTeam = 0, linkTeam = 0;
		
		for(int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if(visited[i] && visited[j]) { //방문했으면
					startTeam += s[i][j] + s[j][i]; // 스타트팀에 계속 더해줌
				} else if (!visited[i] && !visited[j]) { //방문 안했으면
					linkTeam += s[i][j] + s[j][i]; // 링크팀에 더해줌
				}
			}
		}
		
		int difference = Math.abs(startTeam - linkTeam); //스타트팀이랑 링크팀이랑 차이의 절대값이 difference
		minDifference = Math.min(minDifference, difference); //최솟값

	}

}
