package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class sw_14362_무한로봇 {
	// S 그 방향으로 1 이동, L 왼쪽으로 90도 회전, R 오른쪽으로 90도 회전
	static int dx[] = { 1, 0, -1, 0 }; // 우, 하, 좌, 상
	static int dy[] = { 0, -1, 0, 1 };
	static int map[][] = new int[2500][2500];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("14362.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			List<Character> list = new ArrayList<>();
			String input = br.readLine();

			int dir = 0;
			int x = 0;
			int y = 0;
			int max = Integer.MIN_VALUE;
			int dist = -1;

			while (true) {
				if (dist == 0) {
					System.out.println("#" + tc + " " + max);
					break;
				}

				if (dist > (int) Math.pow(2100, 2) + (int) Math.pow(2100, 2)) {
					System.out.println("#" + tc + " " + "oo");
					break;
				}

				for (int i = 0; i < input.length(); i++) {
					char answer = input.charAt(i);
					list.add(answer);
				}
				for (int i = 0; i < list.size(); i++) {

					if (list.get(i) == 'S') {
						x = x + dx[dir];
						y = y + dy[dir];
					} else if (list.get(i) == 'L') {
						dir = (dir + 3) % 4;
					} else if (list.get(i) == 'R') {
						dir = (dir + 1) % 4;
					}
					dist = (int) Math.pow(x - 0, 2) + (int) Math.pow(y - 0, 2);
					if (dist > max) {
						max = dist;
					}
				}
			}

		}
	}

}
