package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw_1210 {
	// 1. 좌/우 우선이동 -> 지나온 길 없애기 (0으로 만들기)
	// 2. 도착지에서 거꾸로 출발지를 찾자. -> '위'로 이동
	// 3. 99행에서 2찾기 (출발지)
	// 4. 좌우를 0으로 싸기(범위 체크 안해줘도됨)
	
	//102*102 크기의 사다리 
	// 범위체크 안할려고 양 옆을 0으로 싸기
	static int data[][] = new int [102][102];
	
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("1210.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc<=10; tc++) {
			int t = Integer.parseInt(br.readLine());
			for(int i = 1; i <= 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= 100; j++) {
					data[i][j] = Integer.parseInt(st.nextToken());
					
				}
			}
			// 도착지찾기. 일단 0에서 시작.
			int destination  = 0;
			// 전체 배열을 일단 볼건데
			for(int j = 1; j <= 100; j++) {
				// 마지막줄이 2이면 도착지에 j 값을 넣기. -> destination = j
				if(data[100][j] == 2) {
					destination = j;
					break;
				}
			}
			
			
			// 마지막줄에서부터 위로 올라가면서 움직이는거니까 row는100부터 시작
			int row = 100;
			// row = 0 이면 젤 첫째줄이니까. 첫째줄에서는 더이상 움직일 필요없음
			while (row > 0) {
				// 왼쪽으로 움직이기
				// 현재지점에서 왼쪽봤을때 왼쪽이 1이면 움직이기 가능
				if(data[row][destination - 1] == 1) {
					// 왼쪽이 1일때까지 계속 움직이기
					while (data[row][destination - 1] == 1) {
						destination--;
					}
				}
				
				// 오른쪽으로 움직이기
				// 현재지점에서 오른쪽봤을때 오른쪽이 1이면 움직이기가능
				else if (data[row][destination + 1] == 1) {
					// 오른쪽이 1일때까지 계속 움직이기
					while(data[row][destination + 1] == 1) {
						destination++;
					}
				}
				// 왼, 오 둘다 못움직이면 위로 올라가기. 
					row--;
				
			}
			// 좌우에 0 추가해서 배열 크기가 달라졌으니까, 답 낼때 -1 해줘야함
			System.out.println("#" + t + " " + (destination - 1));
		}
	}

}
