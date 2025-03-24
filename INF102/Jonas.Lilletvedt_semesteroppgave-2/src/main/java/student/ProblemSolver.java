package student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.*;

public class ProblemSolver implements IProblem {

	@Override
	public <V, E extends Comparable<E>> ArrayList<Edge<V>> mst(WeightedGraph<V, E> g) {
		// Kruskal's algorithm
		// New lists mstEdges and allEdges in weightedGraph
		ArrayList<Edge<V>> mstEdges = new ArrayList<>();
		List<Edge<V>> allEdges = new ArrayList<>();

		// Add each edge to allEdges
		for(Edge<V> edge : g.edges()){
			allEdges.add(edge);
		}

		// Sort allEdges
		allEdges.sort(Comparator.comparing(g::getWeight));

		// Initialize root and rank maps
    	Map<V, V> root = new HashMap<>();
    	Map<V, Integer> rank = new HashMap<>();

		// Initialize sets for each vertex
		for (V vertex : g.vertices()){
			root.put(vertex, vertex);
			rank.put(vertex, 0);
		}

		// Start with lowest weighted edge, iterate through all
   	 	for(Edge<V> edge:allEdges) {
			// Uninon succesfull, add edge to mstEdges
			// uninonFind connects vertices or branches together if not already connected
        	if (unionFind(root, rank, edge.a, edge.b)) {
            	mstEdges.add(edge);
        	}
    	}

    	return mstEdges;
	}

	// Help method, union
	// Connects to branches
	// Returns true if succesfull, else false
	private static <V> boolean unionFind(Map<V, V> root, Map<V, Integer> rank, V vertex1, V vertex2){
		// Find root for both vertices
		V root1 = find(root, vertex1);
    	V root2 = find(root, vertex2);
		// Check if vertices have same root
    	if(!root1.equals(root2)) {
        	// Union by rank
			// Add smaller branch to the larger one
        	if(rank.get(root1) < rank.get(root2)){
            	root.put(root1, root2);
        	} else if(rank.get(root1) > rank.get(root2)){
            	root.put(root2, root1);
        	} else{
            	root.put(root2, root1);
            	rank.put(root1, rank.get(root1) + 1);
        	}
			// Sucessfull union
        	return true; 
    	}
		// Unnseucesfull union, alreade have a connection
    	return false;
	}

	// Find for unionFind, finds root of vertex
	// In unionFind is used to check if vertices are connected, if they have the same root they are
	private static <V> V find(Map<V, V> root, V vertex){
		// If the vertex root is itself retun vertex
		// If not recursive call until vertex is the root
    	if(!vertex.equals(root.get(vertex))){
        	root.put(vertex, find(root, root.get(vertex)));
    	}
    	return root.get(vertex);
	}

	@Override
	public <V> V lca(Graph<V> g, V root, V u, V v) {
		// Implement me :)
		return null;
	}

	@Override
	public <V> Edge<V> addRedundant(Graph<V> g, V root) {
		// Implement me :)
		return null;
	}

}
