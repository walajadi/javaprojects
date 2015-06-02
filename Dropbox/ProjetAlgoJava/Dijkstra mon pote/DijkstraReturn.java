import java.util.ArrayList;

public class DijkstraReturn {
	
	// the queue:
	public static ArrayList<Vertex> q = new ArrayList<Vertex>();
	// the empty set of vertices:
	//public static ArrayList<Vertex> s = new ArrayList<Vertex>();

	public static ArrayList<Vertex> dijkstra(Graph g, Vertex r){
		
		ArrayList<Vertex> s = new ArrayList<Vertex>();
		
		Vertex[] verTab = g.vertices;
		// initialise all vertices and add them all to the queue:
		for (int i = 0; i < verTab.length; i++) {
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
			
			Vertex neo = new Vertex(u.name);
			neo.key = u.key;
			neo.pi = u.pi;
			s.add(neo);
			
			ArrayList<Edge> adjacents = u.edgeAdj(g.edges);
			for (Edge e : adjacents) {
				if (e.startVertex == u) {
					Vertex v = e.endVertex;
					System.out.println("On considere l'arete (" + u + "," + v + ")");
					relax(u, v, e.weight);
				}
			}
		}
		
		return s;
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
