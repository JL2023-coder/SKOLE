# Answer File - Semester 2
# Description of each Implementation
Briefly describe your implementation of the different methods. What was your idea and how did you execute it? If there were any problems and/or failed implementations please add a description.

## Task 1 - mst
*To solve mst and find the minimum spanning tree, so the shortest path to all nodes such that we use the least amount of cabel, I used kruskal's algorithm, and union find. In Kruskal's algorithm i start by creatin a new list containg all edges then sorting the edges from lowest too highest, that way we know that the lowest available edge should be used. When using this method the only thing we need to be wry of is circles, as long as no circles are being made we can just add the next lowest edge. To avoid edges we use unionFind. unionFind addEdges together as long it does not create any cycle, if a cycle is create it does not connect them and return false. I created a helpmethod find to find the root, this was used in unionFind. *

## Task 2 - lca
*To find the lca the lowest common ancestor i first populate a parentMap, using dfs search of the tree, storing parents of each node. I then created a set that will bestoring ancestors for node u, I traversed from node u up to the root using the parentMap, I then traversed upwards from node v using the parentmap, and checking if that node was in u's ancestors if it is we return that node, the lca. *

## Task 3 - addRedundant
*To find the best possible edge to add to the tree, such that in a worst case scenario most nodes would still be connected, I have tried different methods, the first was bruteforce, this had a time of O(n), wasnt even able to run the tests. To optimize it I then tried to just find the two leafs farthest away from the root, unfortunately this does not factor in subtree size, as you could guess the next idea was to factor in subtreesizes, I first calculate the different subtree sizes excluding the root, and find the two largest subtrees, I traverse down largest sub tree recursively finding the new next largest subtree exluding the root, until i get down to a leaf. Since I always picked the largest subtree downwards I know that it will connect the most nodes in a worst case scenario where an edge had been removed.*


# Runtime Analysis
For each method of the different strategies give a runtime analysis in Big-O notation and a description of why it has this runtime.

**If you have implemented any helper methods you must add these as well.**

* ``mst(WeightedGraph<T, E> g)``: O(mlogm)
    * *For the runtime analysis i will only go through lines that adds a significant or noticable amount of time. To start i have three for loops.
    1.for loop adds all edges to an arraylist allEdges, m iterations and the operation has constant time, this gives a runtime of O(m). 
    2. for loop adds each vertex to two maps, n iterations and the operations has, this gives a runtime of O(n).
    3. last for loop goes through all edges and uses unionFind to connect vertices if, unionFind returns true it will add the edge to an list mstEdges, m iterations, unionFind has a runtime of O(α(n)), which gives a total runtime for the for loop as O(m*α(n)).

    The last thing to note is that i use collections.sort for g with allEdges, given a runtime of O(mlogm). If we go through all toghether we can see we have a big O(nlogn+n⋅α(m)), m is equal to n-1, the ackermann function goes wery slowly and a lot slower than the log function, so we could simplify it to O(mlogm).*
    
    ``unionFind(Map<V, V> root, Map<V, Integer> rank, V vertex1, V vertex2)``: O(α(m))
    Has two calls to the method, find which has a runtime of O(α(m)).
    The rest of the methods has a constant runtime, which gives a total runtime of
    UnionFinds runtime is constant except for the two find calls done.
    
    ``find(Map<V, V> root, V vertex)``: O(α(m))
    find is a recursiv method that finds the root of a node, it traverse upwards given a worst case sceneario O(n), however in most it equates to O(α(m)).
* ``lca(Graph<T> g, T root, T u, T v)``: O(n)
    * *Initalizing the parent map, using dfs we only visit each node and edgeones and gives us a O(m+n), 
    Finding the ancestors of node u (while loop)is on average O(logm), or O(h), where h is heigh of tree when traversing upwards from u till the root, however in a worst case scenariowhere all nodes line up and we have to go through all, with a O(n). Finding the lca (while loop) traversing upwards from v checking if the current node is an ancestor u, has a worst case runtime of O(h), height of tree, this is because we only need to check one parent at each level until we get to the lca.
    
    This gives a total runtime of O(n+m+h+h) which equates to O(n), since h must be lower than n, and m=n-1.*
* ``addRedundant(Graph<T> g, T root)``: O(n)
    * *In the method I do one call to getgSubtreeSizes, O(n).
    I do one call to getWtoLargestSubrees, O(d).
    And one call to getLeafInSubtree, O(n),
    Gives a total runtime of O(n)

    ``getSubtreeSizes(Graph<V> g, V root)``: O(n)
    Get subtreesizes calls to getSize, which has a runtime of O(n),
    ``getSize(Graph<V> g, V current, Map<V, Integer> sizes, Set<V> visited)``: O(n)
    Get size uses a dfs search to find sizes of each subtree, gives a runtime of O(n+m which equates to O(n))
    ``getTwoLargestSubtrees(Graph<V> g, V root, Map<V, Integer> subtreeSizes)``: O(d)
    Iterates through neighbours of root, to find the next largest subtree, gives a runtime of O(d), where d or degree is number of neighbours, excluding the parent node.
    ``getLeafInSubtree(Graph<V> g, V subtreeRoot, Map<V, Integer> subtreeSizes)``: O(n)'
    Calls getLeafHelper, which has a runtime of O(n) or O(h)
    ``getLeafHelper(Graph<V> g, V current, Map<V, Integer> subtreeSizes, Set<V> visited)``: O(n)
    Uses a dfs search to find leaf of subtree, gives a runtime of O(n+m), which equates to O(n)*

