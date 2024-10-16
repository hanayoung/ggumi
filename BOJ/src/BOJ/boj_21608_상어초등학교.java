package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_21608_상어초등학교 {
	static int N;
	static int room[][];
	static int saticefy;
	static int num, love1, love2, love3, love4;
	static int happy;
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { -1, 0, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		room = new int[N + 1][N + 1];
		Map<Integer, List<Integer>> loves = new HashMap<>();

		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			love1 = Integer.parseInt(st.nextToken());
			love2 = Integer.parseInt(st.nextToken());
			love3 = Integer.parseInt(st.nextToken());
			love4 = Integer.parseInt(st.nextToken());

			// 학생의 좋아하는 학생들을 리스트로 저장
			List<Integer> love = Arrays.asList(love1, love2, love3, love4);
			
			loves.put(num, love);

			placeStudent(num, loves);
		} 
		System.out.println(happy(loves));

	}

	private static void placeStudent(int student, Map<Integer, List<Integer>> loves) {
		int bestX = -1;
		int bestY = -1;
		int maxLike = -1;
		int maxEmpty = -1;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (room[i][j] != 0)
					continue;

				int likeCnt = 0;
				int emptyCnt = 0;
				// 4방향 탐색 할거임
				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];
					// 범위 벗어나면 체크 안할거야
					if (nx < 1 || ny < 1 || nx >= N + 1 || ny >= N + 1) {
						continue;
					}
					// 빈자리면 emmptyCnt증가
					if (room[nx][ny] == 0) {
						emptyCnt++;
					} 
					// 좋아하는 학생이 인접하면 likeCnt증가
					// num 학생이 좋아하는 학생 리스트 중에서 인접한곳에 그 좋아하는 학생이 있는지 확인(contains. 리스트에 특정값이 포함돼 있는지 확인
					else if (loves.get(student).contains(room[nx][ny])) {
						likeCnt++;
					}
					// 인접칸에 좋아하는 사람이 몇명있는지 확인해서 maxlike보다 크면, maxlike에 likecnt 넣어주기
					// 이때, 인접칸에 좋아하는 사람수가 maxlike랑 같으면, 주변 빈칸이 더 많은 경우 선택해야함
					if (likeCnt > maxLike || (likeCnt == maxLike && emptyCnt > maxEmpty)) {
						bestX = i;
						bestY = j;
						maxLike = likeCnt;
						maxEmpty = emptyCnt;
					}
				}
			}
			
		}
		//모든 배열에 대해서 탐색을 다 하면, 그곳이 학생의 최적 위치가 된다
		room[bestX][bestY] = student;
	}

	private static int happy(Map<Integer, List<Integer>> loves) {
		int totalHappy = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int student = room[i][j];
				int likeCnt = 0;

				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];

					if (nx < 1 || ny < 1 || nx >= N + 1 || ny >= N + 1) {
						continue;
					}
					if (loves.get(student).contains(room[nx][ny])) {
						likeCnt++;
					}
				}
				if (likeCnt > 0) {
					totalHappy += (int) Math.pow(10, likeCnt - 1);
				}
			}
		} // 77i 77i
		return totalHappy;
	}

}
