package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 몽총하게 로봇을 올리고 한칸씩 이동시켰ㄷ ㅏ 흥 => 예제 2번만 틀림
 * 참고 질문 : https://www.acmicpc.net/board/view/150694
 * 참고 블로그 : https://hongjuzzang.github.io/solution/code_b20055/
 */

/**** 순서 ****/
// 1. 벨트를 먼저 돌린다.
// 2. 로봇도 같이 회전
// 3. 로봇 이동할 수 있으면 한칸 이동
// 4. 로봇을 올린다.
public class M_20055_컨베이어벨트위의로봇 {

	private static int N, K;
	private static int[] belt;
	private static boolean[] robot;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] split = in.readLine().split(" ");

		N = Integer.parseInt(split[0]); // 벨트 크기
		K = Integer.parseInt(split[1]); // 종료 조건

		split = in.readLine().split(" ");
		belt = new int[2 * N];
		robot = new boolean[N];
		for (int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(split[i]);
		}

		int ans = 0;

		// K가 0이 될때까지 진행
		while (K > 0) {
			ans++;
			rotateBelt(); // 벨트 회전
			rotateRobot(); // 로봇 회전

			moveRobot(); // 로봇 한칸씩 이동
			putRobot(); // 로봇 올리기

		}

		System.out.println(ans);
	}

	private static void moveRobot() {

		for (int i = robot.length - 1; i >= 0; i--) {
			if (robot[i]) { // 로봇이 있으면, 내구성 확인 후 앞으로 한칸 이동

				if (i + 1 < N && belt[i + 1] > 0 && !robot[i + 1]) { // N에 도달하면, 로봇 내려서 사라짐
					belt[i + 1]--;
					robot[i] = false;
					robot[i + 1] = true;

					if (belt[i + 1] == 0) {
						belt[i + 1]--;
						K--;
					}

				} else if (i + 1 == N) {
					robot[i] = false;
				}

			}
		}
	}

	private static void putRobot() {

		if (!robot[0] && belt[0] > 0 && K > 0) {
			robot[0] = true; // robot 올라감
			belt[0]--; // 벨트 내구성 - 1

			if (belt[0] == 0) { // 내구성이 0이 되는 순간
				belt[0]--; // 0 더 체크하지 않게 음수로 지정
				K--;
			}
		}
	}

	private static void rotateRobot() {

		for (int i = robot.length - 1; i > 0; i--) {
			robot[i] = robot[i - 1];
		}

		robot[0] = false; // 시작점
	}

	// 벨트 회전
	private static void rotateBelt() {

		int tmp = belt[belt.length - 1];

		for (int i = belt.length - 1; i > 0; i--) {
			belt[i] = belt[i - 1];
		}

		belt[0] = tmp;
	}

}
