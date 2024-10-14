package 백준;

import java.util.*;

public class M15686_치킨배달 {
	static int min = Integer.MAX_VALUE;
	static List<int[]> chickens = new ArrayList<>();
	static List<int[]> houses = new ArrayList<>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		// 집 & 치킨집 좌표 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1) { // 집
					houses.add(new int[] { i, j });
				} else if (arr[i][j] == 2) { // 치킨집
					chickens.add(new int[] { i, j });
				}
			}
		}

		combination(new ArrayList<>(), 0, M);
		
		System.out.println(min);

	}

	public static void combination(List<int[]> selected, int start, int M) {
		if (selected.size() == M) {
			int distance = calculate(selected);
			min = Math.min(min, distance);
			return;
		}

		for (int i = start; i < chickens.size(); i++) {
			selected.add(chickens.get(i));
			combination(selected, i + 1, M);
			selected.remove(selected.size() - 1);
		}
	}

	public static int calculate(List<int[]> selected) {
		int totalD = 0;

		for (int[] house : houses) {
			int minDistance = Integer.MAX_VALUE;
			for (int[] chicken : selected) {
				int distance = Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
				minDistance = Math.min(minDistance, distance);
			}
			totalD += minDistance;
		}

		return totalD;
	}
}
