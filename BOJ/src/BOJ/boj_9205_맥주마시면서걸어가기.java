package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class boj_9205_맥주마시면서걸어가기 {
	static int t, n;
	static int homeX, homeY;
	static int gsX, gsY;
	static int toX, toY;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			Point[] points = new Point[n + 2];
			boolean[] visited = new boolean[n + 2];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n + 2; i++) {

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				points[i] = new Point(x, y);
			}

			Queue<Point> queue = new LinkedList<>();
			queue.add(points[0]);
			visited[0] = true;
			boolean canReach = false;

			while (!queue.isEmpty()) {
				Point current = queue.poll();

				if (current.x == points[n + 1].x && current.y == points[n + 1].y) {
					canReach = true;
					break;
				}

				for (int i = 1; i < n + 2; i++) {
					if (!visited[i] && distance(current, points[i]) <= 1000) {
						visited[i] = true;
						queue.add(points[i]);
					}
				}

			}
			System.out.println(canReach ? "happy" : "sad");

		}
	}

	private static int distance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y = p2.y);

	}
}
