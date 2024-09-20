import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution{
    public static void main(String[] args) throws Exception{
        // System.setIn(new FileInputStream("input_1210.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        for(int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            st.nextToken();
            int[][] ladders = new int[100][100];
            boolean[][] visited = new boolean[100][100];
            int end = -1;
            int answer = -1;
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < 100; j++) {
                    ladders[i][j] = Integer.parseInt(st.nextToken());
                    if(ladders[i][j] == 2) end = j;
                }
            }

            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(99, end));
            visited[99][end] = true;
            while(queue.size() != 0) {
                Point p = queue.poll();
                if(p.x == 0) {
                    queue.clear();
                    answer = p.y;
                }else{
                    if(p.y-1 >=0 && p.y-1 < 100 && ladders[p.x][p.y-1] == 1 && visited[p.x][p.y-1] == false){
                        queue.add(new Point(p.x,p.y-1));
                        visited[p.x][p.y-1] = true;
                    }
                    else if(p.y+1 >=0 && p.y+1 < 100 && ladders[p.x][p.y+1] == 1 && visited[p.x][p.y+1] == false){
                        queue.add(new Point(p.x,p.y+1));
                        visited[p.x][p.y+1] = true;
                    }else if(p.x-1 >= 0 && p.x-1 < 100 && ladders[p.x-1][p.y] == 1 && visited[p.x-1][p.y] == false) {
                        queue.add(new Point(p.x-1,p.y));
                        visited[p.x-1][p.y] = true;
                    }
                }
            }
            System.out.println("#"+tc+" "+answer);
        }
    }
}
