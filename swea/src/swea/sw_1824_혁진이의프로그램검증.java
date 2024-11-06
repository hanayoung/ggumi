package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw_1824_혁진이의프로그램검증 {
	static char map[][];
	static int dx[] = { 0, 1, 0, -1 }; // 0상, 1우, 2하, 3좌
	static int dy[] = { -1, 0, 1, 0 };
	static int memory;
	static int nr;
	static int nc;
	static int dir;
	static int r, c, cnt;
	static int random;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("1824.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			map = new char[r][c];
			for (int i = 0; i < r; i++) {
				String input = br.readLine();
				for (int j = 0; j < c; j++) {
					map[i][j] = input.charAt(j);
				}
			}
			

			memory = 0;
			nr = 0;
			nc = 0;
			dir = 1;
			cnt = 0;
			boolean visited[][][][] = new boolean[r][c][16][4];
			
			

			while (true) {
				
				char command = map[nr][nc];
				
				
				if (command == '@') {
					System.out.println("#"+tc+" "+"YES");
					break;
				}
				if(visited[nr][nc][memory][dir]) {
					System.out.println("#"+tc+" "+"NO");
					break;
				}
				visited[nr][nc][memory][dir] = true;
				

				if (command == '<') { // 좌
					left();
					cnt++;

				} else if (command == '>') { // 우
					right();
					cnt++;

				} else if (command == '^') { // 상
					up();
					cnt++;

				} else if (command == 'v') { // 하
					down();
					cnt++;

				} else if (command == '_') {
					if (memory == 0) {
						right();
						cnt++;
					} else {
						left();
						cnt++;
					}
				}

				else if (command == '|') {
					if (memory == 0) {
						down();
						cnt++;

					} else {
						up();
						cnt++;

					}
				}

				else if (command == '?') {
					dir = (int) (Math.random()*4);
					move();
					cnt++;
				}
				
				else if(command == '.') {
					move();
					cnt++;
					continue;
					
				}
				
				else if(command>='0' && command<='9') {
					memory = map[nr][nc]-'0';
					move();
					cnt++;
				}
				
				else if(command == '+') {
					if(memory == 15) {
						memory = 0;
						move();
						cnt++;
					}
					else {
						memory = memory+1;
						move();
						cnt++;
					}
				}
				else if(command == '-') {
					if(memory == 0) {
						memory = 15;
						move();
						cnt++;
					}
					else {
						memory = memory-1;
						move();
						cnt++;
					}
				}

			}
			

		}
	}
	
	private static void move() {
		if(dir == 1) {
			right();
		}
		else if(dir == 2) {
			down();
		}
		else if(dir == 3) {
			left();
		}
		else if(dir == 0) {
			up();
		}
	}

	private static void down() {
		dir = 2;
		nr = nr + dy[dir];
		nc = nc + dx[dir];
		if (nr >= r) {
			nr = 0;
		}

	}

	private static void up() {
		dir = 0;
		nr = nr + dy[dir];
		nc = nc + dx[dir];
		if (nr < 0) {
			nr = r - 1;
		}

	}

	private static void right() {
		dir = 1;
		nr = nr + dy[dir];
		nc = nc + dx[dir];
		if (nc >= c) {
			nc = 0;
		}

	}

	private static void left() {
		dir = 3;
		nr = nr + dy[dir];
		nc = nc + dx[dir];
		if (nc < 0) {
			nc = c - 1;
		}

	}

}
