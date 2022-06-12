package src.graph;

/**
 * Edge class for graphs.
 */
public class Edge {

	/**
	 * ID of source vertex.
	 */
	private int source;

	/**
	 * ID of destination vertex.
	 */
	private int dest;
	
	/**
	 * Weight of edge.
	 */
	private double weight;

	/**
	 * Weight to indicate unweighted edge.
	 */
	private static final double UNWEIGHTED_EDGE = 1.0;

	/**
	 * Construct a weighted edge.
	 * @param source source id
	 * @param dest destination id
	 * @param weight weight of the edge.
	 */
	public Edge ( int source, int dest, double weight ) {
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}

	/**
	 * Constructs an unweighted edge.
	 * @param source source id
	 * @param dest destination id
	 */
	public Edge ( int source, int dest ) {
		this.source = source;
		this.dest = dest;
		this.weight = UNWEIGHTED_EDGE;
	}
	
	/**
	 * Returns destination vertex id.
	 * @return destination vertex id
	 */
	public int getDest ( ) {
		return this.dest;
	}
	
	/**
	 * Returns source vertex id.
	 * @return source vertex id
	 */
	public int getSource ( ) {
		return this.source;
	}
	
	/**
	 * Returns weight of the edge.
	 * @return weight of the edge
	 */
	public double getWeight ( ) {
		return this.weight;
	}
	
	/**
	 * Equality only depends on source id, and destination id.
	 * @param other other edge
	 * @return true if id's are equal, false otherwise.
	 */
	@Override
	public boolean equals ( Object other ) {
		try {
			return (this.source == ((Edge) other).source && this.dest == ((Edge) other).dest);
		}
		catch(Exception e) {
			return false;
		}
	}	
	
	/**
	 * Returns hash code for the edge.
	 * @return hash code
	 */
	@Override
	public int hashCode ( ) {
		return Integer.hashCode(source) + Integer.hashCode(dest);
	}
	
	/**
	 * Returns the String representation of the edge.
	 * @return String representation of the edge
	 */
	@Override
	public String toString ( ) {
		StringBuilder sb = new StringBuilder();
    	sb.append("[(" + source + ", " + dest + "): " + weight + "]");
    	return sb.toString();
	}

}

