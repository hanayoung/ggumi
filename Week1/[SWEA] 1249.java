import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
  public static void main(String[] args) throws Exception{
    System.setIn(new FileInputStream("input_1249.txt"));
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());

    int T = Integer.parseInt(st.nextToken());

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(bf.readLine());
      int N = Integer.parseInt(st.nextToken());
      int[][] maps = new int[N][N];
      boolean[][] visited = new boolean[N][N];

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(bf.readLine());
        String str = st.nextToken();
        for (int j = 0; j < N; j++) {
          maps[i][j] = str.charAt(j) - '0';
        }
      }

      PriorityQueue<Node> queue = new PriorityQueue<>();
      queue.add(new Node(0,0,maps[0][0]));
      visited[0][0] = true;
      
      int answer = 0;
      while(queue.size() != 0){
        Node p = queue.poll();
        if(p.x == N-1 && p.y == N-1){
          queue.clear();
          answer = p.work;
          break;
        }
        for (int i = 0; i < 4; i++) {
          int nx = p.x + dx[i];
          int ny = p.y + dy[i];

          if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

          if(visited[nx][ny] == false) {
            queue.add(new Node(nx,ny,p.work+maps[nx][ny]));
            visited[nx][ny] = true;
          }
        }
      }

      System.out.println("#"+tc+" "+answer);
    }
  }

  static class Node implements Comparable<Node>{
    int x;
    int y;
    int work;

    public Node(int x, int y, int work){
      this.x = x;
      this.y = y;
      this.work = work;
    }

    @Override
    public int compareTo(Solution.Node o) {
      return this.work - o.work;
    }
  }
}
