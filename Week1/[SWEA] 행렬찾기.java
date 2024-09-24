import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Main {

    static int N;
    static int[][] map;
    static int[][] visited;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static class RowCol implements Comparable<RowCol> {
        public int row;
        public int col;

        public RowCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(RowCol o) {
            int thisSize = this.row * this.col;
            int otherSize = o.row * o.col;
            if (thisSize != otherSize) {
                return Integer.compare(thisSize, otherSize);
            } else {
                return Integer.compare(this.row, o.row);
            }
        }
    }

    static List<RowCol> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append(" ");

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new int[N][N];

            for (int i = 0; i < N; i++) {
                String[] split = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(split[j]);
                }
            }

            list.clear();  // 리스트 초기화
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == 0 && map[i][j] != 0) {
                        cnt++;
                        int[] bounds = bfs(i, j, cnt);
                        list.add(new RowCol(bounds[0], bounds[1])); // 행, 열 크기 저장
                    }
                }
            }

            // 리스트 정렬
            Collections.sort(list);

            // 결과 출력
            sb.append(cnt); // 개수 출력
            for (RowCol rc : list) {
                sb.append(" ").append(rc.row).append(" ").append(rc.col);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[] bfs(int startX, int startY, int cnt) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = cnt;

        int minX = startX, maxX = startX, minY = startY, maxY = startY;

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];

            // 현재 위치의 경계값 업데이트
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                // 지도 범위 체크
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;

                // 이동 가능한 칸인지 체크
                if (map[nextX][nextY] == 0) continue;

                // 방문 여부 체크
                if (visited[nextX][nextY] == 0) {
                    visited[nextX][nextY] = cnt; // 방문 표시
                    queue.offer(new int[]{nextX, nextY}); // 큐에 추가
                }
            }
        }

        // 행과 열의 크기 계산
        int rowSize = maxX - minX + 1;
        int colSize = maxY - minY + 1;

        return new int[]{rowSize, colSize};
    }
}
