import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution{
  public static void main(String[] args) throws Exception{
    System.setIn(new FileInputStream("input_1861.txt"));
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());
    int T = Integer.parseInt(st.nextToken());

    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,1,-1};

    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(bf.readLine());
      int N = Integer.parseInt(st.nextToken());
      int answer = 0;
      int roomNum = -1;
      int[][] rooms = new int[N][N];

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(bf.readLine());
        for (int j = 0; j < N; j++) {
          rooms[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          Queue<Point> queue = new LinkedList<>();
          boolean[][] visited = new boolean[N][N];
          visited[i][j] = true;
          queue.add(new Point(i,j));
          int move = 1;
          while(queue.size() != 0) {
            Point r = queue.poll();
            for (int k = 0; k < 4; k++) {
              int nx = r.x + dx[k];
              int ny = r.y + dy[k];

              if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

              if(visited[nx][ny] == false && rooms[nx][ny] - rooms[r.x][r.y] == 1){
                queue.add(new Point(nx,ny));
                visited[nx][ny] = true;
                move += 1;
              }
            }
          }
          if(answer < move) {
            answer = move;
            roomNum = rooms[i][j];
          }else if(answer == move && roomNum > rooms[i][j]) roomNum = rooms[i][j];
        }
      }
      System.out.println("#"+tc+" "+roomNum+" "+answer);
    }
  }
}
