import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    static int[] wines;
    static int N;
    static int[][] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        wines = new int[N];
        int max = 0;

        arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            wines[i] = Integer.parseInt(st.nextToken());
        }

        arr[0][1] = wines[0];

        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < 3; j++) {
                if(j == 0) {
                    int large = Math.max(arr[i][0],arr[i][1]);
                    arr[i+1][j] = Math.max(large, arr[i][2]);
                }else if(j == 1){
                    arr[i+1][j] = arr[i][0] + wines[i+1];
                }else{
                    arr[i+1][j] = arr[i][1] + wines[i+1];
                }
            }
        }
        // System.out.println(Arrays.deepToString(arr));
        max = Math.max(arr[N-1][0], arr[N-1][1]);
        max = Math.max(max, arr[N-1][2]);
        System.out.println(max);
    }
}

// 배열[N][3] 통해서
// N-1번째 포도주를 0은 연속해서 0잔 마신 세계
// 1은 연속해서 1잔 마신 세계
// 2는 연속해서 2잔 마신 세계
// [i+1][0] 세계에서 가장 많이 마신 경우는 [i][0] vs [i][1] vs [i][2] 중에서 가장 큰 값
// [i+1][1] 세계에서 가장 많이 마신 경우는 [i][0] + wines[i+1]
// [i+1][2] 세계에서 가장 많이 마신 경우는 [i][1] + wines[i+1] -> i가 1이상어야함 
