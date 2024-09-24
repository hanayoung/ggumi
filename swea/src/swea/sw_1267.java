package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class sw_1267 {
	
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("1267.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int tc = 1; tc<= 10; tc++) {
			int V = Integer.parseInt(st.nextToken()); // 정점 개수
			int E = Integer.parseInt(st.nextToken()); // 간선 개수
			
			List<Integer>[] adjList = new ArrayList[V + 1];
			
			for(int i = 1; i <= V; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from].add(to);
			}
		}
		
		
	}

}
