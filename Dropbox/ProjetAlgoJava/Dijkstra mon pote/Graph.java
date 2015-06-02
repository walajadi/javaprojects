
public class Graph {
	
	public Vertex[] vertices;
	public Edge[] edges;

	// what about vertices that are not connected to no other vertex thus not forming an edge?
			// - then an edge is just not initialised
	// and what about one vertex forming with itself a fucking edge?
			// - then the edge's starting vertex will be the same as its ending vertex
	
	// acyclic ! (exception if has cycle)
	
	public Graph(Vertex[] vertices) { 	// for unweighted graphs
		this.vertices = vertices;		// if oriented, consider adjacency
	}
	public Graph(Vertex[] vertices, Edge[] edges) { // for weighted graphs
		this.vertices = vertices;					// if oriented, consider adjacency of vertices
		this.edges = edges;							// and start/end vertices of edges
	}
	
	public int findSpecifiedVertex(String s) {
		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i].name.equals(s)) {
				return i;
			}			
		}
		return -1;
	}
	/*
	public Vertex[] giveAdjacents (Vertex v) {
		String[] adjacents = v.getAdjacents();
		Vertex[] adjacentVertices = new Vertex[adjacents.length];
		int iterator = 0;
		for (String adjacent : adjacents) {
			for (int i = 0; i < vertices.length; i++) {
				if (vertices[i].name.equals(adjacent)) {
					adjacentVertices[iterator] = vertices[i];
					iterator++;
				}			
			}
		}
		return adjacentVertices;
	}
	
	public Edge returnEdgeDirected(Vertex start, Vertex end) {
		for (Edge edge : edges) {
			if (start.name == edge.startVertex && end.name == edge.endVertex) {
				return edge;
			}
		}
		return null;
	}
	
	public Edge returnEdgeUndirected(Vertex start, Vertex end) {
		for (Edge edge : edges) {
			if (start.name == edge.startVertex && end.name == edge.endVertex) {
				return edge;
			} else if (start.name == edge.endVertex && end.name == edge.startVertex) {
				return edge;
			}
		}
		return null;
	}
	*/		
}
