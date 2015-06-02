import java.util.ArrayList;
import java.util.LinkedList;

public class TopologicalSort {
	
	public static LinkedList<Vertex> list = new LinkedList<Vertex>();
	
	public static int time = 0;
	public static void depthFirstSearch(Graph g) {
		Vertex[] vertices = g.vertices;
		// initializing:
		for (int i = 0; i < vertices.length; i++) {
			vertices[i].color = "white";
			vertices[i].pi = null;
		}
		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i].color == "white") {
				System.out.println(vertices[i].name + " is white, so we visit it.");
				DFSVisit(g,vertices[i]);
			} else {
				System.out.println(vertices[i].name + " is not white, so we don't visit it.");
			}
		}
	}
	
	public static void DFSVisit(Graph g, Vertex u) {
		System.out.println("Considering vertex " + u.name);
		time = time + 1; 	// white vertex u has just been discovered
		u.d = time;
		u.color = "gray";
		System.out.println(u.name + " is colored in gray");
		// explore edge (u,v):
		ArrayList<Edge> adjacents = u.edgeAdj(g.edges);
		
		for (Edge e : adjacents) {
			System.out.println("adjacent: " + e);
			Vertex adjacent = e.getOtherVertex(u);
			if (e.startVertex == u) {
				if (adjacent.color == "white") {
					System.out.println("visit");
					System.out.println("le pere de " + adjacent.name + " est " + u.name);
					adjacent.pi = u;
					
					DFSVisit(g, adjacent);
				}
				System.out.println("Edge " + e + " is explored.");
			}
			
		}

		u.color = "black";			// blacken u; it is finished
		list.addFirst(u);
		System.out.println(u.name + " is colored in black and added to list");
		time = time + 1;
		u.f = time;		
	}
	
	public static void topologicalSort(Graph g) {
		depthFirstSearch(g);
	}
	
	public static void main(String[] args) {
		
		Vertex vA = new Vertex("a");
		Vertex vB = new Vertex("b");
		Vertex vC = new Vertex("c");
		Vertex vD = new Vertex("d");
		Vertex vE = new Vertex("e");
		Vertex[] tabVertex = {vA,vB,vC,vD,vE};

		Edge e1 = new Edge(vA,vB);
		Edge e2 = new Edge(vB,vE);
		Edge e3 = new Edge(vC,vB);
		Edge e4 = new Edge(vC,vD);
		Edge e5 = new Edge(vD,vE);
		Edge[] tabEdge = {e1,e2,e3,e4,e5};
		
		Graph g = new Graph(tabVertex,tabEdge);
		topologicalSort(g);

		for (Vertex v : list) {
			System.out.println(v.name);
		}
		
	}

}