package SWEA;

import java.util.*;

public class S1861_정사각형방 {
    // 방향 설정
    private static final int[] dx = { -1, 1, 0, 0 };
    private static final int[] dy = { 0, 0, -1, 1 };
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
 
        for (int test_case = 1; test_case <= testCase; test_case++) {
            int N = sc.nextInt();
            int[][] arr = new int[N][N];
            int[][] memo = new int[N][N];
 
            // 배열 입력받기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
 
            int maxCnt = 0;
            int startNum = Integer.MAX_VALUE;
 
            // 배열 전체 탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int cnt = search(N, i, j, arr, memo);
                     
                    // 데이터 갱신하기
                    if(cnt > maxCnt || (cnt == maxCnt) && arr[i][j] < startNum){
                        maxCnt = cnt;
                        startNum = arr[i][j];
                    }
                }
            }
             
            System.out.println("#" + test_case + " " + startNum + " " + maxCnt);
        }
    }
 
    // 상하좌우 이동
    public static int search(int N, int x, int y, int[][] arr, int[][] memo) {
        //이미 계산된 경우
        if(memo[x][y] != 0) {
            return memo[x][y];
        }
         
        int maxMove = 1;
         
        // 방 이동
        for(int d = 0; d<4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
             
            //이동가능한지 확인하기
            if(nx >= 0 && nx < N && ny >= 0 && ny < N && arr[nx][ny] == arr[x][y] + 1) {
                maxMove = Math.max(maxMove, 1+search(N, nx, ny, arr, memo));
            }
        }
         
        //메모에 저장하기
        memo[x][y] = maxMove;
        return maxMove;
    }
 
}