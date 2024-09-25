import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
  public static void main(String[] args) throws Exception{
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());

    int T = Integer.parseInt(st.nextToken());

    int[] dx = {-2, -2, 2, 2, -1, -1, 1, 1};
    int[] dy = {-1, 1, -1, 1, -2, 2, -2, 2};

    for (int tc = 0; tc < T; tc++) {
      st = new StringTokenizer(bf.readLine());
      int N = Integer.parseInt(st.nextToken());
      int answer = 0;
      st = new StringTokenizer(bf.readLine());
      Point start = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
      
      st = new StringTokenizer(bf.readLine());
      Point end = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
  
      Queue<Point> queue = new LinkedList<>();
      int[][] visited = new int[N][N];
      queue.add(start);
      visited[start.x][start.y] = 1;

      while(queue.size() != 0) {
        Point p = queue.poll();

        if(p.x == end.x && p.y == end.y){
          queue.clear();
          answer = visited[p.x][p.y]-1;
          break;
        }
        for (int i = 0; i < 8; i++) {
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];
            
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

            if(visited[nx][ny] == 0){
              queue.add(new Point(nx,ny));
              visited[nx][ny] = visited[p.x][p.y] + 1;
            }
        }
      }

      System.out.println(answer);
    }

  }
}
