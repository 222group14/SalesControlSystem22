package src.graph;

import java.util.Iterator;
/**
 * 
 * DynamicGraph interface which provides dynamically growth and shrink option for Graph.
 * @version 1.0 27.05.2022
 */ 
public interface DynamicGraph {

	/**
	 * Return the number of vertices
	 * @return The number of vertices
	 */
	int getNumV ( );
	
	/**
	 * Determine whether this is a directed graph
	 * @return True if this is a directed graph
	 */
	boolean isDirected ( );
	
	/**
	 * Insert a new edge into the graph
	 * @param e The new edge
	 * @return true if it is inserted
	 */
	boolean insert ( Edge e );
	
	/**
	 * Determine whether an edge exists
	 * @param source The source vertex
	 * @param dest The destination vertex
	 * @return true if there is an edge from source to dest
	 */
	boolean isEdge ( int source, int dest );
	
	/**
	 * Get edge between two vertices
	 * @param source The source vertex
	 * @param dest The destination vertex
	 * @return The Edge between these two vertices.
	 */
	Edge getEdge ( int source, int dest );
	
	/**
	 * Return an iterator to the edges connected to a given vertex
	 * @param source The source vertex
	 * @return An Iterator to the vertices connected to source.
	 */
	Iterator<Edge> edgeIterator ( int source );


	/**
	 * Inserts an edge to the graph.
	 * @param id1 source
	 * @param id2 destination
	 * @param weight weight of edge
	 * @return true if it is inserted
	 */ 
	boolean addEdge ( int id1, int id2, double weight );

	/**
	 * Removes edge from graph.
	 * @param id1 source
	 * @param id2 destination
	 * @return true if it successfully removed, false otherwise.
	 */  
	boolean removeEdge ( int id1, int id2 );
}