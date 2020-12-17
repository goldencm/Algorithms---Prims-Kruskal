
// =======================================
// Author: Conor Golden
// Email : goldencm203@potsdam.edu
// Date  : 12/13/2020
// Assig : Assignment 7b
// =======================================
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Prim {

    
    /** Graph
     * First the graph will collect the parameters vertices and edges
     * then the graph will sort the collected edges and use the nextLowestWeight
     * function to build a list of edges spanning off an initial edge
     * 
     * @param g - The filled graph to be reduced to a MST
     * @return - New graph reduced using Prims Algorithm
     */
	public static Graph getMST(Graph g) {
        Random rand = new Random();
        ArrayList<Vertex> oldVertices = new ArrayList<Vertex>();
        oldVertices.addAll(g.vertices());
        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        
        ArrayList<Edge> oldEdges = new ArrayList<Edge>();
        oldEdges.addAll(g.edges());
        Collections.sort(oldEdges);

        ArrayList<Edge> newEdges = new ArrayList<Edge>();
        visited.add(oldVertices.get(rand.nextInt(oldVertices.size())));

        for(int i = 0; i < oldVertices.size() - 1; i++) {
            newEdges.add(nextLowestWeight(oldEdges, visited));
        }
		return new Graph(visited, newEdges);
    }


    /** nextLowestWeight
     * This function will take the list of all possible edges to iterate over
     * and search for the lowest possible weight connected to the current tree
     * then remove that edge from the list and return the edge removed
     * 
     * @param edges - List of candidate edges to visit
     * @param visited - The list of vist
     * @return - returns the next lowest weight attached to the current tree
     */
    private static Edge nextLowestWeight(ArrayList<Edge> edges, ArrayList<Vertex> visited) {
        Edge canidateEdge = new Edge(null, null, Integer.MAX_VALUE);
        for(Edge e : edges) {
            if(visited.contains(e.source) && !visited.contains(e.dest) && e.weight < canidateEdge.weight)
                canidateEdge = e;
        }
        edges.remove(canidateEdge);
        visited.add(canidateEdge.dest);
        return canidateEdge;
    }

}