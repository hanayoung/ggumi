import java.util.*;

public class S1258_행렬찾기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for (int testCase = 1; testCase <= tc; testCase++) {
            int N = sc.nextInt();
            int[][] arr = new int[N][N];
            int[][] check = new int[N][N];
            List<int[]> result = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] != 0 && check[i][j] == 0) {
                        int[] size = cnt(N, arr, check, i, j);
                        result.add(size);
                    }
                }
            }
            
            //result 정렬해야함
            result.sort((a,b) ->{
            	int sizeA = a[0] * a[1];
                int sizeB = b[0] * b[1];
                if (sizeA == sizeB) {
                    if (a[0] == b[0]) {
                        return a[1] - b[1]; // 행과 크기가 같다면 열 기준으로 정렬
                    }
                    return a[0] - b[0]; // 크기가 같으면 행 기준으로 정렬
                }
                return sizeA - sizeB; // 행렬 크기 기준으로 정렬
            });

            System.out.print("#" + testCase + " " + result.size() + " ");
            for (int[] size : result) {
                System.out.print(size[0] + " " + size[1] + " ");
            }
            System.out.println();
        }
    }

    public static int[] cnt(int N, int[][] arr, int[][] check, int x, int y) {
        int row = 0;
        int col = 0;

        for (int i = x; i < N && arr[i][y] != 0; i++) {
            row++;
        }

        for (int j = y; j < N && arr[x][j] != 0; j++) {
            col++;
        }

        for (int i = x; i < x + row; i++) {
            for (int j = y; j < y + col; j++) {
                check[i][j] = 1;
            }
        }

        return new int[] { row, col };
    }
}
