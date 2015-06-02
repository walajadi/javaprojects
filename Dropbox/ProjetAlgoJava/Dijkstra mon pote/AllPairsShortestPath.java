import java.util.ArrayList;

public class AllPairsShortestPath {
	
	public static ArrayList<int[][]> matrices = new ArrayList<int[][]>();
	
	final static int INF = 9999;

	public static void printAllPairsShortestPath(Vertex[][] pred_matrix, Vertex i, Vertex j) {
	/*
		if (i == j) {
			System.out.println(i);
		} else if (pred_matrix[i][j] == null) {
			System.out.println("No path from " + i + " to " + j + " exists.");
		} else {
			printAllPairsShortestPath(pred_matrix, i, pred_matrix[i][j]);
			System.out.println(j);
		}
	*/
	}
	
	public static void main(String[] args) {
		/*
		// example in Cormen
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		Vertex v3 = new Vertex("3");
		Vertex v4 = new Vertex("4");
		Vertex v5 = new Vertex("5");
		Vertex[] tabVertex = {v1,v2,v3,v4,v5};

		Edge e1 = new Edge(v1,v2,3);
		Edge e2 = new Edge(v1,v3,8);
		Edge e3 = new Edge(v1,v5,-4);
		Edge e4 = new Edge(v2,v5,7);
		Edge e5 = new Edge(v2,v4,1);
		Edge e6 = new Edge(v3,v2,4);
		Edge e7 = new Edge(v4,v1,2);
		Edge e8 = new Edge(v4,v3,-5);
		Edge e9 = new Edge(v5,v4,6);
		Edge[] tabEdge = {e1,e2,e3,e4,e5,e6,e7,e8,e9};
		*/
		
		// Exo 1 TD9
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		Vertex v3 = new Vertex("3");
		Vertex v4 = new Vertex("4");
		Vertex[] tabVertex = {v1,v2,v3,v4};

		Edge e1 = new Edge(v1,v2,10);
		Edge e2 = new Edge(v1,v4,4);
		Edge e3 = new Edge(v2,v1,11);
		Edge e4 = new Edge(v2,v3,6);
		Edge e5 = new Edge(v3,v1,3);
		Edge e6 = new Edge(v3,v4,8);
		Edge[] tabEdge = {e1,e2,e3,e4,e5,e6};

		// weighted, directed graph
		Graph graph = new Graph(tabVertex,tabEdge);
		
		ArrayList<ArrayList<Vertex>> sAll = new ArrayList<ArrayList<Vertex>>();
		for (Vertex v : tabVertex) {
			System.out.println("Running Dijkstra on " + v.name);
			ArrayList<Vertex> s = DijkstraReturn.dijkstra(graph,v);
			sAll.add(s);
			System.out.println();
		}
		System.out.println();
		for (ArrayList<Vertex> s : sAll) {
			System.out.println("vertex\tpi\tkey");
			for (Vertex v : s) {
				System.out.println(v.name + "\t" + v.pi + "\t" + v.key);
			}
			System.out.println();
		}
		
		int[][] weights = new int [tabVertex.length][tabVertex.length];
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights[i].length; j++) {
				for (Vertex v : sAll.get(i)) {
					if (v.name == tabVertex[j].name) {
						weights[i][j] = v.key;
					}
				}				
			}
		}
		
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights[i].length; j++) {
				System.out.print(weights[i][j] + "\t");
			}
			System.out.println();
		}
		
		// Slow All Pairs Shortest Paths :
		
		int[][] W = new int [tabVertex.length][tabVertex.length];
		
		for (int i = 0; i < W.length; i++) {
			for (int j = 0; j < W[i].length; j++) {
				Vertex vi = tabVertex[i];
				Vertex vj = tabVertex[j];
				if (i == j) {
					W[i][j] = 0;
				} else {
					boolean found = false;
					for (Edge e : tabEdge) {
						if (vi == e.startVertex && vj == e.endVertex) {
							W[i][j] = e.weight;
							found = true;
						}
					}
					if (!found) W[i][j] = INF;
				}
			}
		}
		
//		int[][] shortestPaths = slowAPSP(graph, W);
		int[][] shortestPaths = floydWarshall(graph, W);

//		System.out.println();
//		for (int i = 0; i < shortestPaths.length; i++) {
//			for (int j = 0; j < shortestPaths[i].length; j++) {
//				System.out.print(shortestPaths[i][j] + "\t");
//			}
//			System.out.println();
//		}
		
		System.out.println("All Pairs Shortest Paths : \n");
		// Cormen page 690
		for (int[][] matrix : matrices) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					System.out.print(matrix[i][j] + "\t");
				}
				System.out.println();
			}
			System.out.println();
		}
		
	}
	
	// Cormen page 689
	public static int[][] slowAPSP (Graph g, int[][] W) {
		int n = W.length;
		matrices.add(W); // L1	matrices.get(0);
		for (int m = 2; m < n; m++) {
			int[][] Lm = new int[n][n];
			Lm = extendShortestPaths(matrices.get(m-2), W, g);
			matrices.add(Lm); // Lm		matrices.get(m-1);
		}
		return matrices.get(n-2);
	}
	
	// Cormen page 688
	public static int[][] extendShortestPaths(int[][] m_1, int[][] W, Graph g) {
		int n = m_1.length;
		int[][] LL = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					LL[i][j] = 0;
				} else {
					LL[i][j] = INF;
					for (int k = 0; k < n; k++) {
						
						int wkj = INF;
						Vertex vk = g.vertices[k];
						Vertex vj = g.vertices[j];
						for (Edge e : g.edges) {
							if (vk == e.startVertex && vj == e.endVertex) {
								wkj = e.weight;
							}
						}
						if (m_1[i][k]+wkj < LL[i][j]) LL[i][j] = m_1[i][k]+wkj;
					}
				}
			}
		}		
		return LL;
	}
	
	public static int[][] floydWarshall(Graph g, int[][] W){
		int n = W.length;
		matrices.add(W); // D0	matrices.get(0);
		System.out.println("\n---------------\nFloyd-Warshall updates in matrices: \n");
		for (int k = 0; k < n-1; k++) {
			int[][] Dk = new int[n][n];
			System.out.println("matrix m" + (k+1) + ":");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j) {
						Dk[i][j] = 0;
					} else {
					
						int ijk_1 = matrices.get(k)[i][j];
						int ikk_1 = matrices.get(k)[i][k];
						int kjk_1 = matrices.get(k)[k][j];
						
						if (ijk_1 <= ikk_1 + kjk_1) {
							Dk[i][j] = ijk_1;
						} else {
							Dk[i][j] = ikk_1 + kjk_1;
							System.out.println( "vertices " + g.vertices[i].name + " & " +
												g.vertices[j].name + ": " + 
												ikk_1 + " + " + kjk_1
												);							
						}
												
					}
				}
			}
			System.out.println();
			matrices.add(Dk);
		}
		return matrices.get(n-1);
	}
	
}
