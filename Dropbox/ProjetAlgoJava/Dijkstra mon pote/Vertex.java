import java.util.ArrayList;

public class Vertex{
	public String name;
	public int key = 0;
	public int d = 0;
	public int f = 0;
	public Vertex pi;
	public String color;
	public Edge[] adjacents;

	public Vertex(String s, Edge[] adj){
		name= s;
		adjacents = adj;
	}
	public Vertex(String s){
		name= s;
	}
	public Vertex(Vertex v){
		name = v.name;
		key = v.key;
		pi = v.pi;
		adjacents = v.adjacents;
	}
	
	
	public int getKey(){
		return key;
	}
	
	public void setKey(int n){
		key = n;
	}
	
	public void setPi(Vertex v){
		pi = v;
	}
	
	public Edge edgeMin(Edge[] tab) {
		int cmp = 0;
		ArrayList<Edge> tabEdge = new ArrayList<Edge>();
		for (int i = 0; i < tab.length; i++) {
			if (tab[i].startVertex.equals(this) || tab[i].endVertex.equals(this)) {
				tabEdge.add(tab[i]);
				cmp++;
			}
			
		}
		Edge minEd = null;
		for (Edge e : tabEdge) {
			if (minEd == null || minEd.weight > e.weight) {
				minEd = new Edge(e);
			}
		}		
		return minEd;
	}
	
	public ArrayList<Edge> edgeAdj(Edge[] tab) {
		ArrayList<Edge> tabEdge = new ArrayList<Edge>();
		for (int i = 0; i < tab.length; i++) {
			if (tab[i].startVertex.equals(this) || tab[i].endVertex.equals(this)) {
				tabEdge.add(tab[i]);
			}			
		}
		return tabEdge;
	}

	public int compareTo(Vertex vertex) {
		if (key<vertex.key) return -1;
		if(key>vertex.key) return 1;
		else return 0;
	}
	
	public String toString(){
		return name;
				
	}
	
	public boolean equals(Vertex v){
		return name.equals(v.name);
	}
	
}