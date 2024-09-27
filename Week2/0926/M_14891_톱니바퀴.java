package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M_14891_톱니바퀴 {
    private static String[] wheels;
    private static int n = 8, m = 4;
    private static int k;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        wheels = new String[m];
        for (int i = 0; i < m; i++) {
            wheels[i] = in.readLine();
        }

        k = Integer.parseInt(in.readLine()); // 회전 횟수
        for (int i = 0; i < k; i++) {
            String[] split = in.readLine().split(" ");
            int idx = Integer.parseInt(split[0]) - 1; // 위치 (0-based index)
            int dir = Integer.parseInt(split[1]); // 회전 방향 1: 시계방향, -1: 반시계방향

            // 각 톱니의 회전 방향 저장하는 배열
            int[] rotation = new int[m];
            rotation[idx] = dir;

            // 오른쪽 톱니들에 대한 회전 여부 결정
            for (int j = idx + 1; j < m; j++) {
                if (wheels[j - 1].charAt(2) != wheels[j].charAt(6)) {
                    rotation[j] = -rotation[j - 1]; // 반대 방향으로 회전
                } else {
                    break; // 극이 같으면 회전하지 않음
                }
            }

            // 왼쪽 톱니들에 대한 회전 여부 결정
            for (int j = idx - 1; j >= 0; j--) {
                if (wheels[j + 1].charAt(6) != wheels[j].charAt(2)) {
                    rotation[j] = -rotation[j + 1]; // 반대 방향으로 회전
                } else {
                    break; // 극이 같으면 회전하지 않음
                }
            }

            // 모든 톱니에 대해 회전 적용
            for (int j = 0; j < m; j++) {
                if (rotation[j] != 0) {
                    rotate(j, rotation[j]);
                }
            }
        }

        // 최종 결과 계산
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (wheels[i].charAt(0) == '1') { // s극일 때
                ans += Math.pow(2, i);
            }
        }

        System.out.println(ans);
    }

    // 톱니 회전 처리 함수
    private static void rotate(int idx, int dir) {
        if (dir == 1) { // 시계 방향
            wheels[idx] = wheels[idx].charAt(7) + wheels[idx].substring(0, 7);
        } else if (dir == -1) { // 반시계 방향
            wheels[idx] = wheels[idx].substring(1) + wheels[idx].charAt(0);
        }
    }
}
