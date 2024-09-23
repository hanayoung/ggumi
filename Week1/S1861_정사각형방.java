package SWEA;

import java.util.*;

public class S1861_���簢���� {
    // ���� ����
    private static final int[] dx = { -1, 1, 0, 0 };
    private static final int[] dy = { 0, 0, -1, 1 };
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
 
        for (int test_case = 1; test_case <= testCase; test_case++) {
            int N = sc.nextInt();
            int[][] arr = new int[N][N];
            int[][] memo = new int[N][N];
 
            // �迭 �Է¹ޱ�
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
 
            int maxCnt = 0;
            int startNum = Integer.MAX_VALUE;
 
            // �迭 ��ü Ž��
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int cnt = search(N, i, j, arr, memo);
                     
                    // ������ �����ϱ�
                    if(cnt > maxCnt || (cnt == maxCnt) && arr[i][j] < startNum){
                        maxCnt = cnt;
                        startNum = arr[i][j];
                    }
                }
            }
             
            System.out.println("#" + test_case + " " + startNum + " " + maxCnt);
        }
    }
 
    // �����¿� �̵�
    public static int search(int N, int x, int y, int[][] arr, int[][] memo) {
        //�̹� ���� ���
        if(memo[x][y] != 0) {
            return memo[x][y];
        }
         
        int maxMove = 1;
         
        // �� �̵�
        for(int d = 0; d<4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
             
            //�̵��������� Ȯ���ϱ�
            if(nx >= 0 && nx < N && ny >= 0 && ny < N && arr[nx][ny] == arr[x][y] + 1) {
                maxMove = Math.max(maxMove, 1+search(N, nx, ny, arr, memo));
            }
        }
         
        //�޸� �����ϱ�
        memo[x][y] = maxMove;
        return maxMove;
    }
 
}