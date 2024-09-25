import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test28 {

	static int V, E, v1, v2;

	static class Node {
		int data;
		int parent;
		int left;
		int right;

		public Node(int data) {
			this.data = data;
			this.parent = 0;
			this.left = 0;
			this.right = 0;
		}
	}

	static Node[] nodes;
	static boolean[] visited;
	
	static int size;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case < T + 1; test_case++) {
			sb.append("#" + test_case + " ");

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			V = Integer.parseInt(st.nextToken()); // 정점 개수
			E = Integer.parseInt(st.nextToken()); // 간선 개수
			v1 = Integer.parseInt(st.nextToken()); // 정점1
			v2 = Integer.parseInt(st.nextToken()); // 정점2

			nodes = new Node[V + 1];
			visited = new boolean[V + 1];

			for (int i = 1; i <= V; i++) {
				nodes[i] = new Node(i);
			}

			st = new StringTokenizer(br.readLine(), " "); // 간선

			// 트리 생성
			for (int i = 0; i < E * 2; i += 2) {

				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				if (nodes[p].left == 0)
					nodes[p].left = c;
				else
					nodes[p].right = c;

				nodes[c].parent = p;
			}

			// 공통 조상 찾기
			int commonParent = 1;
			while (true) {
				if (v1 != 1) {
					int parent = nodes[v1].parent;
					
					if(visited[parent]) {
						commonParent = parent;
						break;
					}
					visited[parent] = true;
					v1 = parent;
				}
				
				if(v2 != 1) {
					int parent = nodes[v2].parent;
					if(visited[parent]) {
						commonParent = parent;
						break;
					}
					visited[parent] = true;
					v2 = parent;
				}
			}
			
			size = 0;
			get(nodes[commonParent]);
			
			sb.append(commonParent + " " + size + "\n");
		}
		System.out.println(sb);
	}

	private static void get(Node node) {
		size++;
		if(node.left != 0) get(nodes[node.left]);
		if(node.right != 0) get(nodes[node.right]);
		
	}
}
