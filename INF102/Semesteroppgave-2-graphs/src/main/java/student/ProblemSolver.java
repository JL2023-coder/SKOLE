package student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
		Collections.sort(allEdges,g);

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

	// Help method, unionFind
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
		// Unsucesfull union, already have a connection
    	return false;
	}

	// Find for unionFind, finds root of vertex
	// Find is used to check if vertices are connected, if they have the same root they are
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
		 // Stores parents for each node
    	Map<V, V> parentMap = new HashMap<>();
    
    	// Initialize parentMap, add parents for each node
    	getParents(g, root, parentMap);
    
    	// Stores ancestors for node u
    	Set<V> ancestors = new HashSet<>();
    
    	// Starts with node u, and go upwards of the tree
		// till node is null, so top of the tree
    	V current = u;
    	while (current != null) {
        	ancestors.add(current);
        	current = parentMap.get(current);
    	}
    
    	// Starts with node v, and go upwards of the tree
		// till node is null, so top of the tree
		// if node is in ancestors to u, return current lca
    	current = v;
    	while (current != null) {
        	if (ancestors.contains(current)) {
       	    	return current;
        	}
        	current = parentMap.get(current);
    	}
    
    	return null;
	}

	// Helper method to get parents using DFS
	private <V> void getParents(Graph<V> g, V root, Map<V, V> parentMap) {
    	Set<V> visited = new HashSet<>();
    	dfs(g, root, visited, null, parentMap);
	}

	// DFS
	private <V> void dfs(Graph<V> g, V currentNode, Set<V> visited, V parent, Map<V, V> parentMap) {
    	visited.add(currentNode);
    	parentMap.put(currentNode, parent);
    	for (V neighbor : g.neighbours(currentNode)) {
        	if (!visited.contains(neighbor) &&!neighbor.equals(parent)) {
            	dfs(g, neighbor, visited, currentNode, parentMap);
        	}
    	}
	}

	

	@Override
	public <V> Edge<V> addRedundant(Graph<V> g, V root) {
		// Calculate sizes of all subtrees, store them in map
    	Map<V, Integer> subtreeSizes = getSubtreeSizes(g, root);

		// If root only has one neighbour
		if(g.degree(root)==1){
			V r = g.neighbours(root).iterator().next();
			V rootC = getLeafInSubtree(g, r, root, subtreeSizes);
			return new Edge<V>(root, rootC);
		}

    	// Find roots of the two largest subtrees
    	List<V> rootsLargestSubTree = getTwoLargestSubtrees(g, root, subtreeSizes);

    	// Get leaf from twom laregst subtrees
    	V leaf1 = getLeafInSubtree(g, rootsLargestSubTree.get(0), root, subtreeSizes);
    	V leaf2 = getLeafInSubtree(g, rootsLargestSubTree.get(1), root, subtreeSizes);

    	// Return an edge connecting these two leaf nodes
    	return new Edge<>(leaf1, leaf2);
	}

	// Get subTreeSizes
	private <V> Map<V, Integer> getSubtreeSizes(Graph<V> g, V root) {
    	Map<V, Integer> sizes = new HashMap<>();
    	getSize(g, root, sizes, new HashSet<>());
    	return sizes;
	}

	// Recursiv function
	// Gets size of subtree
	private <V> int getSize(Graph<V> g, V current, Map<V, Integer> sizes, Set<V> visited) {
    	visited.add(current);
    	int totalSize = 1;

    	for (V neighbor : g.neighbours(current)) {
        	if (!visited.contains(neighbor)) {
            	totalSize += getSize(g, neighbor, sizes, visited);
        	}
    	}
    	sizes.put(current, totalSize);
    	return totalSize;
	}

	// Gets two largest subTrees
	private <V> List<V> getTwoLargestSubtrees(Graph<V> g, V root, Map<V, Integer> subtreeSizes) {
    	V largest1 = null, largest2 = null;
    	int maxSize1 = 0, maxSize2 = 0;

    	for (V neighbor : g.neighbours(root)) {
        	int size = subtreeSizes.getOrDefault(neighbor, 0);
        	if (size > maxSize1) {
            	maxSize2 = maxSize1;
            	largest2 = largest1;
            	maxSize1 = size;
            	largest1 = neighbor;
        	} else if (size > maxSize2) {
            	maxSize2 = size;
            	largest2 = neighbor;
        	}
		}
    	return Arrays.asList(largest1, largest2);
	}

	// Gets leaf in subtree
	private <V> V getLeafInSubtree(Graph<V> g, V subtreeRoot, V root, Map<V, Integer> subtreeSizes) {
    	Set<V> visited = new HashSet<>();
    	return getLeafHelper(g, subtreeRoot, root, subtreeSizes, visited);
	}

	// Helper
	private <V> V getLeafHelper(Graph<V> g, V current, V root, Map<V, Integer> subtreeSizes, Set<V> visited) {
    	visited.add(current);
    	// If only one connection, it is a leaf
    	if (g.degree(current) == 1) {
       		return current;
    	}
		if(!visited.contains(root)){
			visited.add(root);
		}
    	// Recursively find a leaf in the largest subtree rooted at this node
    	V largestNeighbor = null;
    	int maxSize = 0;
    	for (V neighbor : g.neighbours(current)) {
        	if (!visited.contains(neighbor)) {
            	int size = subtreeSizes.getOrDefault(neighbor, 0);
            	if (size > maxSize) {
                	maxSize = size;
                	largestNeighbor = neighbor;
				}
			}
		}
		if (largestNeighbor != null) {
			return getLeafHelper(g, largestNeighbor, root, subtreeSizes, visited);
		} else {
			return current;
		}
	}
}
