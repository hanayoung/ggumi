
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    static boolean[] selected;
    static int[][] powers;
    static int N;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        selected = new boolean[N];
        powers = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                powers[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        choice(0, 0);
        System.out.println(min);
    }
    static void choice(int idx, int r){
        if(r == N/2){
            compare();
            return;
        }
        for (int i = idx; i < N; i++) {
            selected[i] = true;
            choice(i+1, r+1);
            selected[i] = false;
        }
    }

    static void compare(){
        int score_start = 0;
        int score_link = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if(selected[i] == false && selected[j] == false){
                    score_link += powers[i][j];
                    score_link += powers[j][i];
                }else if(selected[i] == true && selected[j] == true){
                    score_start += powers[i][j];
                    score_start += powers[j][i];
                } 
            }
        }
        if(Math.abs(score_link-score_start) < min) min = Math.abs(score_link-score_start);
    }
}
