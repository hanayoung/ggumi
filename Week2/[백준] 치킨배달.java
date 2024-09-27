import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
  static int M, N;
  static int[] sel;
  static ArrayList<Point> homes;
  static ArrayList<Point> chickens;
  static int[][] maps;
  static int min = Integer.MAX_VALUE;
  public static void main(String[] args) throws Exception{
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    maps = new int[N][N];

    homes = new ArrayList<>();
    chickens = new ArrayList<>();

    sel = new int[M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(bf.readLine());
      for (int j = 0; j < N; j++) {
        maps[i][j] = Integer.parseInt(st.nextToken());
        if(maps[i][j] == 1) homes.add(new Point(i,j));
        else if(maps[i][j] == 2) chickens.add(new Point(i,j));
      }
    }

    if(M < chickens.size()){
      comb(0, 0);
    }else getChickenDis(chickens);

    System.out.println(min);
  }

  static void comb(int idx, int r){
    if(r == M){
      // 다 뽑았당
      ArrayList<Point> tmp = new ArrayList<>();
      for (int i = 0; i < sel.length; i++) {
        tmp.add(chickens.get(sel[i]));
      }
      getChickenDis(tmp);

      return;
    }
    for(int i = idx; i < chickens.size(); i++){
      sel[r] = i;
      comb(i+1, r+1);
    }
  }

  static void getChickenDis(ArrayList<Point> selChickens){
    int chickenDistance = 0;
    boolean end = false;
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    for (int i = 0; i < homes.size(); i++) {
      if(end == true) return;
      Queue<Point> queue = new LinkedList<>();
      queue.add(homes.get(i));
      int[][] visited = new int[N][N];
      visited[homes.get(i).x][homes.get(i).y] = 1;
      while(queue.size() != 0){
        Point p = queue.poll();
        if(selChickens.contains(p)){
          chickenDistance += visited[p.x][p.y]-1;
          if(chickenDistance > min) end = true;
          queue.clear();
          break;
        }
        for (int j = 0; j < 4; j++) {
          int nx = p.x + dx[j];
          int ny = p.y + dy[j];

          if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

          if(visited[nx][ny] == 0){
            visited[nx][ny] = visited[p.x][p.y] +1;
            queue.add(new Point(nx,ny));
          }
        }
      }
    }
    if(min > chickenDistance) min = chickenDistance;
  }
}

// 치킨집 개수 세기 -> M 보다 작은가? 
// M보다 크다 -> M개 뽑기
// 해당 치킨집들 가지고 치킨 거리 구하기
// 구하는 방법 : 이중 for문을 돌며, 집 찾기
// 집에서부터 bfs를 돌면서 해당 집의 치킨 거리 찾기
// 모든 집들의 치킨거리의 합 구하기
// 이 값들 중 최솟값 찾기
