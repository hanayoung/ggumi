import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken()); // 섬 개수

            Point[] islands = new Point[N];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                islands[i] = new Point(Integer.parseInt(st.nextToken()), -1);
            }

            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                islands[i].y = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(bf.readLine()); 
            Double E = Double.valueOf(st.nextToken()); // 세율
            Double answer = 0.0;

            PriorityQueue<Node> pQueue = new PriorityQueue<>();
            boolean[] visited = new boolean[N];

            pQueue.add(new Node(0, 0.0));

            while(pQueue.size() != 0) {
                Node n = pQueue.poll();
                if(visited[n.index] == false) {
                    answer += n.cost;
                    visited[n.index] = true;
                }else continue;
                for (int i = 0; i < N; i++) {
                    if(visited[i] == false) {
                        Double dist = Math.sqrt(Math.pow(islands[i].x-islands[n.index].x, 2) + Math.pow(islands[i].y - islands[n.index].y, 2));
                        pQueue.add(new Node(i, E*dist*dist));
                    }
                }
            }
            System.out.println("#"+tc+" "+Math.round(answer));
        }
    }
}

class Node implements Comparable<Node>{
    int index;
    Double cost;

    public Node(int index, Double cost){
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        if(o.cost < this.cost) return 1;
        else return -1;
    }
}
