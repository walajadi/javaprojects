
public class Edge {
	
	public Vertex startVertex;
	public Vertex endVertex;
	public int weight;
	
	public Edge(Vertex start, Vertex end) {
		startVertex = start;
		endVertex = end;
	}
	
	public Edge(Vertex start, Vertex end,int w) {
		startVertex = start;
		endVertex = end;
		weight = w;
	}
	public Edge(Edge e){
		startVertex = e.startVertex;
		endVertex = e.endVertex;
		weight = e.weight;
	}
	
	
	public Vertex getStartVertex(){
		return startVertex;
	}
	
	public Vertex getEndVertex(){
		return endVertex;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public void setWeight(int n){
		weight = n;
	}
	
	public boolean equals(Edge e){
		return ((startVertex == e.startVertex) && (endVertex == e.endVertex) && (weight == e.weight)); 
	}
	
	public String toString(){
		return "("+ startVertex + "," + endVertex +"," + weight + ")";
	}
	
	public int compareTo(Edge e){
		if (weight<e.weight) return -1;
		if(weight>e.weight) return 1;
		else return 0;
	}

	public Vertex getOtherVertex(Vertex one) {
		if (one == startVertex) {
			return endVertex;
		} else {
			return startVertex;
		}
	}
}
