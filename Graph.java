// =======================================
// CIS 303 Analysis of Algorithms
// Graph.java -- client file for MST assignment
// Updated 12 November 2019, L. Grabowski
// =======================================
// DO NOT MODIFY THIS FILE
// =======================================
import java.util.*;

public class Graph  {

    // fields
    // adjacency list representation of the graph
    Map <Vertex, ArrayList<Edge>> graphRep; 
    // set(list) of vertices
    ArrayList<Vertex> vertices;
    // set(list) of edges
    ArrayList<Edge> edges;

    // Param: vertices - list of vertices
    //        edges - list of edges
    // construct graph from a set of vertices and edges
    public Graph (ArrayList<Vertex> vertices, ArrayList<Edge> edges) {
        this.graphRep = new TreeMap<Vertex, ArrayList<Edge>>();
        this.vertices = vertices;
        this.edges = edges;

        for (Vertex v : vertices) 
            graphRep.put(v, new ArrayList<Edge>());
            
        for (Edge e : edges) 
            graphRep.get(e.source).add(e);
    }


    // Param: sc - Scanner open on file in graph format
    // construct graph from the input file
    public Graph (Scanner sc) {
        this.graphRep = new TreeMap<Vertex, ArrayList<Edge>>();
        this.vertices = new ArrayList<Vertex>();
        this.edges = new ArrayList<Edge>();

	    // read the vertices
        Scanner lnSc = new Scanner(sc.nextLine());
        while (lnSc.hasNext()) {
            Vertex v = new Vertex(lnSc.nextInt());

	        // put each in the set of vertices
            vertices.add(v);
        
	        // put each in the map with an empty edge list
	        graphRep.put(v, new ArrayList<Edge>());
	    }

	    // read the edges
        while (sc.hasNextLine()) {
            lnSc = new Scanner(sc.nextLine());
            Vertex source = getVertex(lnSc.nextInt());
            Vertex dest = getVertex(lnSc.nextInt());
            Integer weight = lnSc.nextInt();

            // get the edge list for the source vertex from the map
            ArrayList edgeList = graphRep.get(source);
            Edge newEdge = new Edge(source, dest, weight);

            // add this edge to the map and the edge set
            edgeList.add(newEdge);
            graphRep.put(source, edgeList);
            this.edges.add(newEdge);

            // do the same of the dest vertex (this is an undirected graph)
            edgeList = graphRep.get(dest);
            newEdge = new Edge(dest, source, weight);

            // add this edge to the map and the edge set
            edgeList.add(newEdge);
            graphRep.put(dest, edgeList);
            this.edges.add(newEdge);
        }
    }

    // Param: n, a vertex label
    // Returns: the vertex in this graph with label n
    public Vertex getVertex (int n) {
        int i = this.vertices.indexOf(new Vertex(n));
        return this.vertices.get(i);
    }

    // Post: All graph vertices marked as UNvisited
    public void clear() {
	    for (Vertex v : vertices)
	        v.visited = false;
    }

    // Returns: String rep of Graph
    public String toString() {
        String result = "";
        for (Vertex v : graphRep.keySet()) 
            result += v + ": " +  graphRep.get(v) + "\n";
            return result;
    }

    // Returns: list of vertices for this graph
    public  ArrayList<Vertex> vertices() {
	    return this.vertices;
    }

    // Returns: list of edges for this graph
    public ArrayList<Edge> edges() {
	    return this.edges;
    }

    // Param: v - a vertex
    // Returns: list of edges for which v is a the source vertex
    public ArrayList<Edge> edges(Vertex v) {
	    return graphRep.get(v);
    }
}


// class
class Vertex implements Comparable<Vertex> {

   // fields
   Integer label;
   boolean visited;

   // Param: label - int or integer
   // Constructs  UNvisted vertex
   public Vertex(Integer label) {
    	this.label = label;
    	this.visited = false;
   }

   // Param: o - an object
   // Returns: if object is a Vertex with the same label as this Vertex
   public boolean equals (Object o) {
       Vertex other = (Vertex) o;
       return this.label.equals(other.label);

   }

   // Param: other - a Vertex
   // Returns: < 0 if the integer label of this Vertex is less than
   //          the label of the other Vertex
   public int compareTo(Vertex other) {
       return this.label - other.label;
   }

   // Returns: hash Code for this Vertex (handy if using HashMap/Sets)
  public int hashCode() {
      return this.label;
  }

  // Returns: String rep of this vertex
   public String toString() {
       // uncomment line below if want to know if vertex was visited
       //       String visit = this.visited ? "(V)" : "(U)";
       String visit = "";
       return label.toString() + visit;
   }

}

// class
class Edge implements Comparable<Edge> {
   // fields
    Vertex source;
    Vertex dest;
    Integer weight;

    // Params: source and dest - Vertex
    //         int - weight
    // constructs directed edges with given weight
    public Edge(Vertex source, Vertex dest, Integer weight) {
	    this.source = source;
        this.dest = dest;
        this.weight = weight;
   }

   // Param: other - an Edge
   // Returns: < 0 if this edge weight  < other edge weight, if edge weights ==
   //          < 0 if this source label < other edge source label, if ==
   //          the label of the other Vertex
   public int compareTo(Edge other) {
       if (this.weight != other.weight) 
            return this.weight - other.weight;
       if (!this.source.equals(other.source) )
	        return this.source.label - other.source.label;
       return this.dest.label - other.dest.label;
   }

   // Returns: String rep of edge
   public String toString() {
       return source+ "->" + dest + ":" + weight;
   }
}
 

