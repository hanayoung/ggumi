
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution{
  public static void main(String[] args) throws Exception{
    System.setIn(new FileInputStream("input_1258.txt"));
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());

    int T = Integer.parseInt(st.nextToken());

    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(bf.readLine());
      int N = Integer.parseInt(st.nextToken());

      int[][] maps = new int[N][N];
      boolean[][] visited = new boolean[N][N];
      PriorityQueue<Point> pQueue = new PriorityQueue<>((o1,o2) -> {
        if(o1.x * o1.y > o2.x * o2.y) return 1;
        else if(o1.x * o1.y == o2.x * o2.y && o1.x > o2.x) return 1;
        else return -1;
      });

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(bf.readLine());
        for (int j = 0; j < N; j++) {
          maps[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if(visited[i][j] == false && maps[i][j] != 0){
            int xLen = 1;
            int yLen = 1;
            int nx = i+1;
            visited[i][j] = true;
            while(visited[nx][j] == false && maps[nx][j] != 0){
              visited[nx][j] = true;
              nx += 1;
            }
            xLen = nx - i;

            int ny = j+1;
            while(visited[i][ny] == false && maps[i][ny] != 0){
              visited[i][ny] = true;
              ny += 1;
            }
            yLen = ny - j;
            for (int k = 0; k < xLen; k++) {
              for (int m = 0; m < yLen; m++) {
                visited[i+k][j+m] = true;
              }
            }
            pQueue.add(new Point(xLen, yLen));
          }
        }
      }
      
      int originSize = pQueue.size();
      System.out.print("#"+tc+" "+originSize+" ");
      for (int i = 0; i < originSize; i++) {
        Point p = pQueue.poll();
        System.out.print(p.x+" "+p.y+" ");
      }
      System.out.println("");
    }
  }
}

// 이중 for문으로 돌면서 0이 아니고 visited 한 적 없으면 걔부터 x축과 y축을 1씩 더해가며 0이 나오는 지점 찾기
