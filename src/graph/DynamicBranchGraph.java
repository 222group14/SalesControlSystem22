package src.graph;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.lang.Iterable;
import src.structure.Branch;
import java.util.Set;

/**
 * 
 * An Adjancecy List graph implementation which holds Branches as Vertices
 * @version 1.0 12.06.2022
 */ 
public class DynamicBranchGraph implements DynamicGraph, Iterable<Branch> {

	/**
	 * Number of vertices.
	 */ 
	private int numV = 0;

	/**
	 * Indicates directed or not.
	 */ 
	private boolean directed;

	/**
	 * A LinkedHashMap to hold edges, id's are used as key.
	 */ 
	private Map<Integer, LinkedList<Edge>> edgeMap;

	/**
	 * A LinkedHashMap to hold vertices, id's are key.
	 */ 
	private Map<Integer, Branch> branchMap;

	/**
	 * maxID for inserting automatically.
	 */ 
	private int maxID = numV;


	/**
	 * Constructs an empty graph.
	 * @param directed indicates directed or not.
	 */ 
	public DynamicBranchGraph ( boolean directed ) {
		this.numV = 0;
		this.directed = directed;
		edgeMap = new LinkedHashMap<Integer, LinkedList<Edge>>();
		branchMap = new LinkedHashMap<Integer, Branch>();
	}

	/**
	 * Returns number of vertices.
	 * @return number of vertices.
	 */
	@Override
	public int getNumV ( ) {
		return numV;
	}

	/**
	 * Returns wanted branch's id.
	 * @param branch branch
	 * @return -1 if branch does not exits.
	 */ 
	public int getID ( Branch branch ) {
		for ( Map.Entry<Integer, Branch> entry: branchMap.entrySet() ) {
			if ( entry.getValue().equals(branch) )
				return entry.getKey();
		}
		return -1;
	}

	/**
	 * Returns if graph is directed or not.
	 * @return directed or not.
	 */
	@Override
	public boolean isDirected ( ) {
		return directed;
	}

	/**
	 * Checks if given source and destination id's are edge.
	 * @param source source id
	 * @param dest destination id
	 * @return true if it is edge.
	 */
	@Override
	public boolean isEdge ( int source, int dest ) {

		List<Edge> list = edgeMap.get(source);
		if ( list == null )
			return false;

		Edge target = new Edge(source, dest);
		for( Edge edge : list ) {
			if(edge.equals(target))
				return true;
		}
		return false;
	}
	
	/**
	 * Inserts an edge to graph.
	 * @param edge edge to be inserted
	 * @return true if it is inserted.
	 */
	@Override
	public boolean insert ( Edge edge ) {
		if ( branchMap.get(edge.getSource()) == null || branchMap.get(edge.getDest()) == null )
			return false;

		edgeMap.get(edge.getSource()).add(edge);
		if( !isDirected() ) {
			edgeMap.get(edge.getDest()).add(new Edge(edge.getDest(), edge.getSource(), edge.getWeight()));
		}
		return true;
	}
	
	/**
	 * Iterates through branches.
	 * @return branch set iterator
	 */
	@Override
	public Iterator<Branch> iterator() {
		return branchMap.values().iterator();
	} 

	/**
	 * Returns edge iterator.
	 * @param source source id
	 * @return iterator over edges.
	 */ 
	@Override
	public Iterator<Edge> edgeIterator ( int source ) {
		LinkedList<Edge> list = edgeMap.get(source);
		if ( list == null )
			return null;
		return list.iterator();
	}
	
	/**
	 * Returns branch iterator.
	 * @return iterator over vertices.
	 */ 
	public Iterator<Branch> branchIterator ( ) {
		return branchMap.values().iterator();
	}

	/**
	 * Iterator for id's.
	 * @return iterator to iterate around id's.
	 */ 
	public Iterator<Integer> vertexIterator ( ) {
		return branchMap.keySet().iterator();
	}

	/**
	 * Returns wanted edge.
	 * @param source source id
	 * @param dest destination id
	 * @return edge that found or null if it is not exists.
	 */
	@Override
	public Edge getEdge ( int source, int dest ) {
		Edge target = new Edge(source, dest);
		for( Edge edge : edgeMap.get(source) ) 
			if(edge.equals(target))
				return edge; 
		return null; 
	}
	
	/**
	 * Returns wanted branch.
	 * @param id id of the branch that wanted.
	 * @return branch reference if it is found, null if not.
	 */ 
	public Branch getBranch ( int id ) {
		return branchMap.get(id);
	}

	/**
	 * Creates a new branch and adds to the graph.
	 * @param branch branch of the branch
	 * @return Branch that created.
	 */
	public Branch add ( Branch branch ) {
		branchMap.put(++maxID, branch);
		edgeMap.put(maxID, new LinkedList<Edge>());
		numV++;
		return branch;
	}

	/**
	 * Inserts an edge to the graph.
	 * @param id1 source
	 * @param id2 destination
	 * @param weight weight of edge
	 * @return true if it is inserted.
	 */ 
	@Override
	public boolean addEdge ( int id1, int id2, double weight ) {
		return this.insert(new Edge(id1, id2, weight));
	}

	/**
	 * Inserts an edge to the graph.
	 * @param branch1 source
	 * @param branch2 destination
	 * @param weight weight of edge
	 * @return true if it is inserted.
	 */ 
	public boolean addEdge ( Branch branch1, Branch branch2, double weight ) {
		int count = 0;
		int id1 = -1;
		int id2 = -1;

		for ( Map.Entry<Integer, Branch> entry: branchMap.entrySet() ) {
			if ( entry.getValue().equals(branch1) ) {
				id1 = entry.getKey();
				count++;
			}
			else if ( entry.getValue().equals(branch2) ) {
				id2 = entry.getKey();
				count++;
			}
		}
		if ( count != 2 )
			return false;
		return this.insert(new Edge(id1, id2, weight));
	}
	/**
	 * Removes edge from graph.
	 * @param id1 source
	 * @param id2 destination
	 * @return true if it successfully removed, false otherwise.
	 */  
	public boolean removeEdge ( int id1, int id2 ) {
		LinkedList<Edge> list1 = edgeMap.get(id1);
		if ( list1 == null )
			return false;
		
		boolean result = false;
		Edge edge = new Edge(id1, id2);
		Iterator<Edge> itr = list1.iterator();
		while ( itr.hasNext() ) {
			Edge edge1 = itr.next();
			if ( edge1.equals(edge) ) {
				itr.remove();
				result = true;
				break;
			}
		}

		if ( !isDirected() && result ) {
			Edge edgeOp = new Edge(id2, id1);
			List<Edge> list2 = edgeMap.get(id2);
			itr = list2.iterator();
			while ( itr.hasNext() ) {
				Edge edge2 = itr.next();
				if ( edge2.equals(edgeOp) ) {
					itr.remove();
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Removes given branch.
	 * @param branch branch to remove
	 * @return true if it is removed
	 */ 
	public boolean remove ( Branch branch ) {
		
		boolean flag = false;
		int id = -1;

		Set<Map.Entry<Integer,Branch>> entrySet = branchMap.entrySet();
		Iterator<Map.Entry<Integer, Branch>> itr = entrySet.iterator();
		while ( itr.hasNext() ) {
			Map.Entry<Integer, Branch> entry = itr.next();
			if ( entry.getValue().equals(branch) ) {
				id = entry.getKey();
				flag = true;
				itr.remove();
				break;
			}
		}

		if ( !flag )
			return false;

		numV--;
		edgeMap.remove(id);
		for ( Map.Entry<Integer, LinkedList<Edge>> entry: edgeMap.entrySet() ) {
			List<Edge> list = entry.getValue();

			Iterator<Edge> itr2 = list.iterator();
			while ( itr2.hasNext() ) {
				Edge val = itr2.next();
				if ( val.getDest() == id )
					itr2.remove();			
			}
		}
		return true;		
	}
	/**
	 * Removes branch from graph according to the id.
	 * @param branchID id of the branch
	 * @return true if it removed, false otherwise.
	 */ 
	public boolean removeBranch ( int branchID ) {

		// remove branch
		if ( branchMap.remove(branchID) != null ) 
			numV--;
		else
			return false;

		// remove edges of this branch
		edgeMap.remove(branchID);
		for ( Map.Entry<Integer, LinkedList<Edge>> entry: edgeMap.entrySet() ) {
			List<Edge> list = entry.getValue();

			Iterator<Edge> itr = list.iterator();
			while ( itr.hasNext() ) {
				Edge val = itr.next();
				if ( val.getDest() == branchID )
					itr.remove();			
			}
		}
		return true;
	}

	/**
	 * Returns String representation of graph. Each vertices are shown, and their edges are shown on below of them.
	 * @return String representation of graph.
	 */ 
	@Override
	public String toString ( ) {
		StringBuilder sb = new StringBuilder();

		sb.append(" Adjacency List\n");
		if ( numV == 0 ) {
			sb.append(" Empty graph\n");
			return sb.toString();
		}

		for ( Map.Entry<Integer, LinkedList<Edge>> entryEdge: edgeMap.entrySet() ) {
			sb.append(" vertex (id = " + entryEdge.getKey() + ", branch name = "
						 + branchMap.get(entryEdge.getKey()).getBranchName() + ")-->\n");
			for( Edge e: entryEdge.getValue() ) {
				
				sb.append("\t" + e + "\n");
			}
		}
		return sb.toString();
	}
}