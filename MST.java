// =======================================
// CIS 303 Analxysis of Algorithms
// MST.java -- client file for MST assignment
// Updated 12 November 2019, L. Grabowski
// =======================================
// Run with: Graph.java, MinHeap. java,
// Kruskal.java [assigned], Prim.java [assigned]
// =======================================
// DO NOT MODIFY THIS FILE
// =======================================
import java.util.*;
import java.io.*;

public class MST {

    public static void main (String [] args) throws FileNotFoundException {

	// open the file and get the graph, g
        Scanner sc = new Scanner (new File(args[0]));
		Graph g = new Graph(sc);
	
       


		// get a minimum spanning tree of g using Prim's MST alg.ong startTime = System.nanoTime();



	long startTime = System.nanoTime();
	Graph mst2 = Kruskal.getMST(g);
	long endTime = System.nanoTime();
	long duration = (endTime - startTime) / 1000000;
	// save the MST in a file
	fileGraph(mst2, "kmst", duration);
	// report its total weight
	System.out.println("Kruskal Total Weight: " + totalWeight(mst2.edges()) + "\tTime: " + duration + "ms");


	g.clear();	

	// get a minimum spanning tree of g using Prim's MST alg.
	startTime = System.nanoTime();
Graph mst = Prim.getMST(g);
// // save the MST in a file
endTime = System.nanoTime();
	duration = (endTime - startTime) / 1000000;
fileGraph(mst, "pmst", duration);
// // report its total weight
System.out.println("Prim Total Weight: " + totalWeight(mst.edges()) + "\t\tTime: " + duration + "ms");

// mark all vertices as unvisited
    }  

    public static void fileGraph(Graph g, String ext, long duration)
                              throws FileNotFoundException {
	Vertex [] va = new Vertex[g.vertices.size()];
	for (int i = 0; i < va.length; i++)
	    va[i] = g.vertices.get(i);
	Arrays.sort(va);

	Edge [] ea = new Edge[g.edges.size()];
	for (int i =0; i < ea.length; i++)
	    ea[i] = g.edges.get(i);
	Arrays.sort(ea);


	PrintStream ps =
		new PrintStream (new File(g.vertices().size() + "."+ ext));
	ps.println("Time: " + duration);
	for (int i =0; i < va.length; i++)
	    ps.print(va[i] + " ");
	ps.println();
	for (int i =0; i < ea.length; i++){
            Edge e = ea[i];
	    ps.println(e.source + " " + e.dest + " " + e.weight);
	}
    }

    public static int totalWeight(ArrayList<Edge> edges) {
	int sum = 0;
	for (Edge e: edges)
	    sum += e.weight;
	return sum;
    }

}
