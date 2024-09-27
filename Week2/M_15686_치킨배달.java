package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 도시 치킨 거리 = 모든 집의 치킨 거리의 합
// 조합으로 m개를 뽑고, m개의 치킨 집의 최소 거리값을 모두 구해서 더한다.
// 구한 치킨집의 최소 거리값 중 가장 최소인 것을 출력
public class M_15686_치킨배달 {

	private static int n, m;
	private static int[][] map;
	private static int[][] check;
	private static List<int[]> chickenList;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] split = in.readLine().split(" ");

		n = Integer.parseInt(split[0]); // 배열 크기
		m = Integer.parseInt(split[1]); // 최대 치킨 집 개수

		map = new int[n][n];
		check = new int[n][n];
		chickenList = new ArrayList<int[]>();

		for (int i = 0; i < n; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(split[j]);
				check[i][j] = Integer.MAX_VALUE;
				if (map[i][j] == 2) { // 치킨 집 위치
					map[i][j] = -1; // 중복 방지
					chickenList.add(new int[] { i, j });
				}
			}
		}

		// 0은 빈칸, 1은 집, 2는 치킨 집

		// 아이디어
		// 1. m개를 고르는 방법 => 백트래킹? 조합
		// 2. m개가 되면, 거기서 가장 작은 거리 찾기..
		selected = new int[m];
		visited = new boolean[chickenList.size()];
		comb(0, 0);

		System.out.println(result);
	}

	private static int[] selected;
	private static boolean[] visited;

	private static void comb(int start, int cnt) {

		if (cnt == m) {

			for (int i = 0; i < n; i++) {
				Arrays.fill(check[i], Integer.MAX_VALUE); // check 배열 초기화
			}

			for (int i = 0; i < m; i++) {
				int[] chick = chickenList.get(selected[i]); // [1, 2]
				shortest(chick);
			}

			int ans = 0;
			for (int[] m : check) {
				for (int mm : m) {
					if (mm != Integer.MAX_VALUE) {
						ans += mm;
					}
				}
			}

			result = Math.min(result, ans);

			return;
		}

		for (int i = start; i < chickenList.size(); i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			selected[cnt] = i;
			comb(i + 1, cnt + 1);
			visited[i] = false;
			selected[cnt] = 0;
		}
	}

	private static void shortest(int[] chick) {
		// 집들의 위치에서 치킨집까지의 거리를 저장

		int cx = chick[0], cy = chick[1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != -1 && map[i][j] != 0) { // 치킨 집과 빈칸이 아닐 때
					int distance = Math.abs(cx - i) + Math.abs(cy - j);
					check[i][j] = Math.min(check[i][j], distance); // 해당 위치의 가장 짧은 거리값을 넣기
				}
			}
		}

	}

}
