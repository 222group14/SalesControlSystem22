package src.graph;

import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;

/**
 * 
 * A Dijkstra Algorithm implementation.
 * @version 1.0 12.06.2022
 */ 
public class DijkstraAlgorithm {

	/**
	 * A Dijkstra Algorithm
	 * pre: It is assumed that start id is in the graph.
	 * @param graph MyGraph type graph
	 * @param start Start id
	 * @param pred Predecessors for every id
	 * @param dist Distances (id, distance) pair
	 */ 
	public static void dijkstraAlgorithm ( DynamicBranchGraph graph, int start,
								 Map<Integer, Integer> pred, Map<Integer, Double> dist ) {
		HashSet<Integer> vMinusS = new HashSet<Integer>();

		// getting vertices
		Iterator<Integer> itrV = graph.vertexIterator();
		while ( itrV.hasNext() ) {
			Integer id = itrV.next();
			if ( id != start )
				vMinusS.add(id);
		}

		// filling pred and dist
		for ( int key : vMinusS ) {
			pred.put(key, start);
			Edge edge = graph.getEdge(start, key);
			if ( edge == null )
				dist.put(key, Double.POSITIVE_INFINITY);
			else
				dist.put(key, edge.getWeight());
		}

		// main loop
		while ( vMinusS.size() != 0 ) {
			double minDist = Double.POSITIVE_INFINITY;
			int u = -1;
			boolean found = false;
			// choose minimum distance edge
			for ( int v : vMinusS ) {
				double val = dist.get(v);
		
				if ( val < minDist ) {
					minDist = val;
					u = v;
					found = true;
				}
			}

			if ( !found )
				u = vMinusS.iterator().next();
			vMinusS.remove(u);

			Iterator<Edge> itrE = graph.edgeIterator(u);
			while ( itrE.hasNext() ) {
				Edge edge = itrE.next();
				int v = edge.getDest();

				// check if it is processed or not
				if( vMinusS.contains(Integer.valueOf(v)) ) {
					double weight = edge.getWeight();
					double weightU = dist.get(u);
					
					// if distance is less
					if ( (weightU + weight) < dist.get(v) ) {
						dist.put(v, weightU + weight);
						pred.put(v, u);
					}
				}
			} // end while
		} // end outer loop
	} // end method
}