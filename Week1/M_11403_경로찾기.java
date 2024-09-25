package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M_11403_경로찾기 {

	private static int n;
	private static int[][] map;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());

		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == 1 || (map[i][k] == 1 && map[k][j] == 1)) {
						map[i][j] = 1;
					}
				}
			}
		}
		
		for(int[] m: map) {
			for(int mm: m) {
				System.out.print(mm + " ");
			}
			
			System.out.println();
		}

	}

}
