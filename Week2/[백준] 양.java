import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class test1 {
    static int R, C;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    static String[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);

        // . 필드
        // # 울타리
        // o 양
        // v 늑대

        map = new String[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            split = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                map[i][j] = split[j];
            }
        }

        int resultSheep = 0;
        int resultWolf = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!map[i][j].equals("#") && !visited[i][j]) {
                    int[] counts = bfs(i, j);  // 해당 구역에서 양과 늑대 수를 반환
                    int sheepInRegion = counts[0];
                    int wolfInRegion = counts[1];
                    
                    if (sheepInRegion > wolfInRegion)
                        resultSheep += sheepInRegion;
                    else
                        resultWolf += wolfInRegion;
                }
            }
        }

        System.out.println(resultSheep + " " + resultWolf);
    }

    private static int[] bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { x, y });

        visited[x][y] = true;

        int sheep = 0;
        int wolf = 0;

        if (map[x][y].equals("o")) sheep++;  // 시작 위치가 양일 경우 카운트
        if (map[x][y].equals("v")) wolf++;   // 시작 위치가 늑대일 경우 카운트

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int curX = temp[0];
            int curY = temp[1];

            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (nextX < 0 || nextX >= R || nextY < 0 || nextY >= C || map[nextX][nextY].equals("#"))
                    continue;

                if (!visited[nextX][nextY]) {
                    if (map[nextX][nextY].equals("o")) {
                        sheep++;  // 양 발견 시 카운트 증가
                    }
                    if (map[nextX][nextY].equals("v")) {
                        wolf++;  // 늑대 발견 시 카운트 증가
                    }

                    visited[nextX][nextY] = true;
                    queue.offer(new int[] { nextX, nextY });
                }
            }
        }

        return new int[] { sheep, wolf };  // 양과 늑대의 수를 배열로 반환
    }
}
