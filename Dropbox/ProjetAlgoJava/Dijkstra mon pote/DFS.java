import java.util.ArrayList;

public class DFS {
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
		System.out.println(u.name + " is colored in black");
		time = time + 1;
		u.f = time;		
	}
	
	public static void main(String[] args) {
		/*
		Vertex vR = new Vertex("r");
		Vertex vS = new Vertex("s");
		Vertex vT = new Vertex("t");
		Vertex vU = new Vertex("u");
		Vertex vV = new Vertex("v");
		Vertex vW = new Vertex("w");
		Vertex vX = new Vertex("x");
		Vertex vY = new Vertex("y");
		Vertex[] tabVertex = {vR,vS,vT,vU,vV,vW,vX,vY};

		Edge e1 = new Edge(vR,vV);
		Edge e2 = new Edge(vR,vS);
		Edge e3 = new Edge(vS,vW);
		Edge e4 = new Edge(vS,vR);
		Edge e5 = new Edge(vT,vU);
		Edge e6 = new Edge(vT,vW);
		Edge e7 = new Edge(vT,vX);
		Edge e8 = new Edge(vU,vT);
		Edge e9 = new Edge(vU,vX);
		Edge e10 = new Edge(vU,vY);
		Edge e11 = new Edge(vV,vR);
		Edge e12 = new Edge(vW,vS);
		Edge e13 = new Edge(vW,vT);
		Edge e14 = new Edge(vW,vX);
		Edge e15 = new Edge(vX,vT);
		Edge e16 = new Edge(vX,vU);
		Edge e17 = new Edge(vX,vW);
		Edge e18 = new Edge(vX,vY);
		Edge e19 = new Edge(vY,vU);
		Edge e20 = new Edge(vY,vX);
		Edge[] tabEdge = {e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16,e17,e18,e19,e20};
		*/
		Vertex vU = new Vertex("u");
		Vertex vV = new Vertex("v");
		Vertex vW = new Vertex("w");
		Vertex vX = new Vertex("x");
		Vertex vY = new Vertex("y");
		Vertex vZ = new Vertex("z");
		Vertex[] tabVertex = {vU,vV,vW,vX,vY,vZ};

		Edge e1 = new Edge(vU,vV);
		Edge e2 = new Edge(vU,vX);
		Edge e3 = new Edge(vX,vV);
		Edge e4 = new Edge(vV,vY);
		Edge e5 = new Edge(vY,vX);
		Edge e6 = new Edge(vW,vY);
		Edge e7 = new Edge(vW,vZ);
		Edge e8 = new Edge(vZ,vZ);
		Edge[] tabEdge = {e1,e2,e3,e4,e5,e6,e7,e8};
		
		Graph g = new Graph(tabVertex,tabEdge);
		depthFirstSearch(g);
		// The predecessor subgraph of a depth-first search
		// forms a depth-first forest comprising several depth-first trees.
		for (int i = 0; i < tabVertex.length; i++) {
			Vertex v = tabVertex[i];
			System.out.println("Vertex " + v.name + " (color: " + v.color +
					", d_time: " + v.d + ", f_time: " + v.f + ", predecessor: " + v.pi);
		}
		
	}

}
