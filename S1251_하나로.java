import java.util.*;

public class S1251_하나로 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		for (int testcase = 1; testcase <= tc; testcase++) {
			int N = sc.nextInt(); // 섬의 개수
			int[] x = new int[N];
			int[] y = new int[N];

			int[] check = new int[1000001];
			Arrays.fill(check, -1);

			for (int i = 0; i < N; i++) {
				x[i] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				y[i] = sc.nextInt();
			}

			double E = sc.nextDouble(); // 환경 세율
			
			double result = prim(N, x, y, E);

			System.out.println("#" + testcase + " " + Math.round(result));
		}

	}

	public static double prim(int N, int[] x, int[] y, double E) {
		double[] minDist = new double[N];
		boolean[] visited = new boolean[N];
		Arrays.fill(minDist, Double.MAX_VALUE);
		minDist[0] = 0;

		double result = 0;

		for (int i = 0; i < N; i++) {
			double min = Double.MAX_VALUE;
			int idx = -1;
			
			for(int j = 0; j<N; j++) {
				if(!visited[j] && minDist[j] < min) {
					min = minDist[j];
					idx = j;
				}
			}
			
			visited[idx] = true;
			result += min;
			
			for(int j = 0; j<N; j++) {
				if(!visited[j]) {
					double dist = moneyCal(x[idx], y[idx], x[j], y[j]);
					minDist[j] = Math.min(minDist[j], E*dist);
				}
			}
		}
		
		return result;

	}

	public static double moneyCal(int x1, int y1, int x2, int y2) {
		return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
	}
}
