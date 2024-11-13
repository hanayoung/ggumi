package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_zz {
	static int N, r, c, cnt;
	static int arr[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int size = (int)Math.pow(2, N);
		cnt = 0;
		
		find(r,c, size);
		System.out.println(cnt);

		
	}
	private static void find(int x, int y, int size) {
		if(size == 1) {
			return;
		}
		// 좌상단
		if(x< size/2 && y < size/2) {
			find(x,y, size/2);
		}
		//우상단
		else if(x < size/2 && y >= size/2) {
			cnt += size * size /4 ;
			find(x, y-size/2, size/2);
		}
		else if(x >= size/2 && y < size/2) {
			cnt += (size*size/4)*2;
			find(x-size/2, y, size/2);
		}
		else if(x >= size/2 && y >= size/2) {
			cnt+= (size*size/4)*3;
			find(x-size/2, y-size/2, size/2);
		}
		
	}
}


