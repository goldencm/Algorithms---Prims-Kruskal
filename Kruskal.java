// =======================================
// Author: Conor Golden
// Email : goldencm203@potsdam.edu
// Date  : 12/13/2020
// Assig : Assignment 7b
// =======================================
import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {


        /** Graph
         * Graph takes the current list of edges and vertices provided in parameter g
         * then sorts the collected edges and iterates through the list of edges adding
         * each edge based on wether or not the edge is not contained, it's vertices are
         * in two sublists, or is added to one list because one of its elements in contained
         * within that list.
         * 
         * 
         * @param g - The filled graph to be reduced to a MST
         * @return - New graph reduced using Kruskels Algorithm
         */
	public static Graph getMST(Graph g) {
        ArrayList<Vertex> vertices = g.vertices();
        ArrayList<Edge> edges = g.edges();
        Collections.sort(edges);
        
        ArrayList<ArrayList<Edge>> edgeSets = new ArrayList<ArrayList<Edge>>();
        ArrayList<Edge> temp = new ArrayList<Edge>();
        temp.add(edges.get(0));
        edgeSets.add(temp);
        temp = new ArrayList<Edge>();

        int[] contains;
        int setindex1 = 0;
        int setindex2 = 0;

        /** This messy block of if's runs on this logic respectably
         * First check if setindex1 and setindex2 either contain a -1
         * If so this indicates that at least one of the vertices in the
         * edge are not contained in a current sublist
         * 
         * If both are not contained in a sublist add the edge to a new sublist
         * else add the edge to the respective sublist
         * 
         * Then if setindex1 and setindex2 must both have some sort of location
         * within the sublists so merge the two lists and add the edge to the new
         * list. If one of those lists happen to be the master sublist this takes
         * precedence
         * 
         * 
         */
        for(int i = 1; i < edges.size(); i++) {
            temp.add(edges.get(i));
            
            contains = contains(edges.get(i), edgeSets);
            setindex1 = contains[0];
            setindex2 = contains[1]; 

            if (setindex2 == -1 || setindex1 == -1) {
                if(setindex2 == -1 && setindex1 == -1)  
                    edgeSets.add(temp); 
                 else  if (setindex2 == -1)
                    edgeSets.get(setindex1).add(edges.get(i)); 
                 else 
                    edgeSets.get(setindex2).add(edges.get(i)); 
                 
            } else if (setindex1 != setindex2) {
                if (setindex2 == 0) {
                    edgeSets.set(setindex2, merge(edgeSets.get(setindex2), edgeSets.get(setindex1))); 
                    edgeSets.get(setindex2).add(edges.get(i));
                } else
                    edgeSets.set(setindex1, merge(edgeSets.get(setindex1), edgeSets.get(setindex2))); 
                    edgeSets.get(setindex1).add(edges.get(i)); 
            }
            //Allocate new Space for a new ArrayList
            temp = new ArrayList<Edge>();
        }

        return new Graph(vertices, edgeSets.get(0));
    }


    /** merge
     * merges two collection of edge lists, clears the second, and returns the first
     * 
     * @param e1 - Edge list to collect all elements in e2
     * @param e2 - Edge list to be collected from and cleared
     * @return - the new collection of merged lists
     */
    private static ArrayList<Edge> merge(ArrayList<Edge> e1, ArrayList<Edge> e2) {
        e1.addAll(e2);
        e2.clear();
        return e1;
    }


    /** contains
     * This method will search of the entire collected list of subarrays for the index
     * of the sub array that holds the source and destination variables respectably
     * and returns them in an array
     * 
     * @param a - The edge used to search over the entire list of current edges
     * @param b - All the edges collected so far in a 2 dimensional array
     * @return - an array for the index of the source and destination in the 1st dim of b
     */
    private static int[] contains(Edge a, ArrayList<ArrayList<Edge>> b) {
        int[] containChecker = {-1 , -1};
        for(int i = 0; i < b.size(); i++) {
            
            if (containChecker[0] == -1)
                containChecker[0] = containsEdgeSource(a, b.get(i)) ? i : -1 ;

            if (containChecker[1] == -1)
                containChecker[1] = containsEdgeDest(a, b.get(i)) ? i : -1 ;

            if (containChecker[0] != -1 && containChecker[1] != -1) 
                return containChecker;
            
        }
        return containChecker;
    }


    /** containsEdgeSource
     * Searches the destination variable to see if is contained in the list 
     * 
     * @param a - The edge used to search over the sublist
     * @param b - sublist of edges
     * @return - wether or not the sublist contains the source vecrtice in the sublist
     */
    private static boolean containsEdgeSource(Edge a, ArrayList<Edge> b) {
        for(int i = 0; i < b.size(); i++) {
            if (a.source == b.get(i).source || a.source == b.get(i).dest)
                return true;
        }
        return false;
    }


    /** containsEdgeDest
     * Searches the destination variable to see if is contained in the list 
     * 
     * @param a - The edge used to search over the sublist
     * @param b - sublist of edges
     * @return - wether or not the sublist contains the destination vecrtice in the sublist
     */
    private static boolean containsEdgeDest(Edge a, ArrayList<Edge> b) {
        for(int i = 0; i < b.size(); i++) {
            if (a.dest == b.get(i).dest || a.dest == b.get(i).source ) 
                return true;
        }
        return false;
    }

   
    


   
}
