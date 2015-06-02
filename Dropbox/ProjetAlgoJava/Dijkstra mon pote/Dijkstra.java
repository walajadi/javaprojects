import java.util.ArrayList;

public class Dijkstra {
	
	// the queue:
	public static ArrayList<Vertex> q = new ArrayList<Vertex>();
	// the empty set of vertices:
	public static ArrayList<Vertex> s = new ArrayList<Vertex>();

	public static void main(String[] args) {
		
		Vertex vS = new Vertex("s");
		Vertex vT = new Vertex("t");
		Vertex vX = new Vertex("x");
		Vertex vY = new Vertex("y");
		Vertex vZ = new Vertex("z");
		Vertex[] tabVertex = {vS,vT,vX,vY,vZ};

		// all weights have to be nonnegative
		Edge e1 = new Edge(vS,vT,10);
		Edge e2 = new Edge(vS,vY,5);
		Edge e3 = new Edge(vY,vT,3);
		Edge e4 = new Edge(vT,vY,2);
		Edge e5 = new Edge(vZ,vS,7);
		Edge e6 = new Edge(vT,vX,1);
		Edge e7 = new Edge(vY,vZ,2);
		Edge e8 = new Edge(vY,vX,9);
		Edge e9 = new Edge(vX,vZ,4);
		Edge e10 = new Edge(vZ,vX,6);
		Edge[] tabEdge = {e1,e2,e3,e4,e5,e6,e7,e8,e9,e10};

		// weighted, directed graph
		Graph graph = new Graph(tabVertex,tabEdge);
		dijkstra(graph,vS);
		
		System.out.println("vertex\tpi\tkey");
		for (Vertex v : s) {
			System.out.println(v.name + "\t" + v.pi + "\t" + v.key);
		}
		
		System.out.println();

	}
	
	public static void dijkstra(Graph g, Vertex r){
		
		Vertex[] verTab = g.vertices;
		// initialise all vertices and add them all to the queue:
		for (int i = 0; i < verTab.length; i++){
			verTab[i].key = 99999;
			verTab[i].pi = null;
			q.add(verTab[i]);
		}
		
		// set the key of the source vertex to 0:
		r.key = 0;		
		
		while (!q.isEmpty()) {
			
			Vertex u = extractMinimum();
			System.out.println("On considere " + u);
			q.remove(u);
			s.add(u);
			ArrayList<Edge> adjacents = u.edgeAdj(g.edges);
			for (Edge e : adjacents) {
				if (e.startVertex == u) {
					Vertex v = e.endVertex;
					System.out.println("On considere l'arete (" + u + "," + v + ")");
					relax(u, v, e.weight);
				}
			}
		}
	}
	
	public static Vertex extractMinimum() {
		Vertex result = null;
		for (Vertex v : q) {
			if (result == null) {
				result = v;
			} else {
				if (v.key < result.key) {
					result = v;
				}
			}
		}
		return result;
	}
	
	public static void relax (Vertex u, Vertex v, int w) {
		if (v.key > u.key + w) {
			System.out.println("ca veut dire que " +v.key + " est plus que " + (u.key + w) );
			System.out.println("le truc etait evalue");
			v.key = u.key + w;
			v.pi = u;
		}
	}
}
