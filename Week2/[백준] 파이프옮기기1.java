import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    static boolean[][] visited;
    static int[][] maps;
    static int N;
    static int answer = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        
        maps = new int[N][N];
        visited = new boolean[N][N];
        // 방향은 가로:0, 세로:1, 대각선:2

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0][0] = true;
        visited[0][1] = true;
        
        dfs(0,1,0);

        System.out.println(answer);
        
    }
    static void dfs(int x, int y, int dir){
        if(x == N-1 && y == N-1){
            answer += 1;
            return;
        }
        if(dir == 0 && y+1 < N){
            if(maps[x][y+1] == 0 && visited[x][y+1] == false){
                visited[x][y+1] = true;
                dfs(x, y+1, 0);
                visited[x][y+1] = false;
                if(x+1 < N && maps[x+1][y] == 0 && visited[x+1][y] == false && maps[x+1][y+1] == 0 && visited[x+1][y+1] == false){
                    visited[x+1][y+1] = true;
                    visited[x+1][y] = true;
                    dfs(x+1,y+1,2);
                    visited[x+1][y+1] = false;
                    visited[x+1][y] = false;
                }
            }
        }else if(dir == 1 && x+1 < N){
            if(maps[x+1][y] == 0 && visited[x+1][y] == false){
                visited[x+1][y] = true;
                dfs(x+1, y, 1);
                visited[x+1][y] = false;
                if(y+1 < N && maps[x][y+1] == 0 && visited[x][y+1] == false && maps[x+1][y+1] == 0 && visited[x+1][y+1] == false){
                    visited[x+1][y+1] = true;
                    visited[x][y+1] = true;
                    dfs(x+1,y+1,2);
                    visited[x+1][y+1] = false;
                    visited[x][y+1] = false;
                }
            }
        }else if(dir == 2){
            if(x+1 < N && maps[x+1][y] == 0 && visited[x+1][y] == false){
                visited[x+1][y] = true;
                dfs(x+1, y, 1);
                visited[x+1][y] = false;
                if(y+1 < N && maps[x][y+1] == 0 && visited[x][y+1] == false && maps[x+1][y+1] == 0 && visited[x+1][y+1] == false){
                    visited[x+1][y+1] = true;
                    visited[x][y+1] = true;
                    dfs(x+1,y+1,2);
                    visited[x+1][y+1] = false;
                    visited[x][y+1] = false;
                }
            }
            if(y+1 < N && maps[x][y+1] == 0 && visited[x][y+1] == false){
                visited[x][y+1] = true;
                dfs(x,y+1,0);
                visited[x][y+1] = false;
            }
        }
    }
}

// 놓여진 방향이 가로일 경우 갈 수 있는 방향 : 가로(0,1), 대각선(0,1)(1,1)(1,0)
// 세로일 경우 갈 수 있는 방향 : 세로(1,0), 대각선(0,1)(1,0)(1,1)
// 대각선일 경우 갈 수 있는 방향 : 가로(0,1), 세로(1,0), 대각선(0,1)(1,0)(1,1)
