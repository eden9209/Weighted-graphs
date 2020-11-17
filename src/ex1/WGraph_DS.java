package ex1;

import java.io.Serializable;
import java.util.*;
/*
* The implementation of weighted_graph interface.
* Contains HashMap data structure for all the nodes in the graph.
* The key is the data of the node.
* The value is the node.
* example: 1,a 2,b  .. |data,Node
*/

public class WGraph_DS implements weighted_graph,Serializable {
/*
*@param nodes = The HashMap of the graph
*@param MC = Keep track of the amount of changes (add or remove of node and edge)
*/	
private HashMap<Integer, node_info> nodes;
private int mc;
private int edge_size;	
/*
* Default constructor
*/
public WGraph_DS()
{
this.nodes=new HashMap<>();
this.mc=0;
this.edge_size=0;
}
/*
 * Copy constructor (deep copy) 
 */
public WGraph_DS(WGraph_DS other) {
	nodes = new HashMap<>();
	for(node_info n: other.nodes.values()) {
		
		this.nodes.put(n.getKey(), n);
	}
	this.edge_size = other.edge_size;
	this.mc = other.mc;
}





public node_info getNode(int key)
{
	return nodes.get(key);
	
}
 
public boolean hasEdge(int node1, int node2)
{
	Node s=(Node)this.nodes.get(node1);
    return s.hasNi(node2);
	
} 

public double getEdge(int node1, int node2)
{
	if(nodes.containsKey(node1) && nodes.containsKey(node2))
	{
		Node s=(Node) nodes.get(node1);
		if(s.getEdgesOf().containsKey(node2))
		{
        				
		return s.getEdgesOf().get(node2).get_weight();
						
		}
	}
	 
	 return -1;

}
/*
* Add a vertex to the HashMap (if it not exist)
* 
*/ 
public void addNode(int key)
{
		//node_info no = getNode(key);
	    Node n1 = new Node(key);
	
	if(!nodes.containsKey(n1.getKey())) 
	{
	mc++;
	this.nodes.put(n1.getKey(),n1);
		
	}
	else {
		return;
		//System.out.println("Node already in the graph");
	}
}

/*
* connect and edge between node1 and node2 , with an edge with weight
* if the edge already exists , the method simply update the weight of the edge.
* 
*/
public void connect(int node1, int node2, double w)
{
	if(nodes.containsKey(node1) && nodes.containsKey(node2) && (node1 != node2))
	{
		   // Node n1=new Node(node1);
	    //	Node n2=new Node(node2);
	    	Node n1 = (Node)nodes.get(node1);
		   Node n2 = (Node)nodes.get(node2);
		
		if(!n1.hasNi(node2)&&!n2.hasNi(node1))
		{
			Edge e=new Edge(w,node1,node2);
			n1.addEdge(e);
			n2.addEdge(e);
			mc++;//adding an edge
			edge_size++;
		}
		else
			// if the edge already exist in the HashMap of the two nodes
			//we want only update the weight of the edge.
		{
			n1.getEdgesOf().get(node2).set_weight(w);
			n2.getEdgesOf().get(node1).set_weight(w);
		}
		
	}
	else {
		return;
		//System.out.println("src or dst doesnt exist OR src equals to dest");
	}
}

/*
 * Return all the nodes in the HashMap 
 */  
public Collection<node_info> getV()
{
	return this.nodes.values();
}

/*
 *  Return all the node_info that come out from this vertex (using the HashMap of this vertex).
*/   
public Collection<node_info> getV(int node_id)
{
	Collection<node_info> list=new ArrayList<node_info>();
	
	 if(nodes.containsKey(node_id)) 
	 {
	 Node m=(Node)this.nodes.get(node_id);
	 list=m.getNi();
	 
	 }
	 
	 return list;	
}
/*
 * To remove a node we need to find all edges that connect to it.
 * So the fast way is to check which node is edge of the removeNode and after delete from their hash_map this node.
 * 
 * After deleting every edge, we remove the node and update the counters.
 */
public node_info removeNode(int key)
{
	if(nodes.containsKey(key))
	{
		Node n=(Node)getNode(key);
		
		List <Integer> m=n.getNi_k();
		
		if(m!=null)
		{
        for(int i=0;i<m.size();i++)			
		{
        	int z=m.get(i);
        	
		    Node h=(Node)getNode(z);
		    h.removeNode(n);
			edge_size--;
		      mc++;
		}
        
		}
		
		 n.getEdgesOf().clear();
		this.nodes.remove(key);
		return n;
		
		}
 
	   else {
	//	System.out.println("key doesnt exist");
		return null;
	}	
}
/*
* To remove an edge we need to find the source node and check if contains the destination in it's HashMap of edges
* node1 = The node of the source
* node2 = The node of the dest
*/
public void removeEdge(int node1, int node2)
{
	if(nodes.containsKey(node1) && nodes.containsKey(node2))
	{
		Node n1 = (Node) nodes.get(node1);
		Node n2 = (Node) nodes.get(node2);

		if(n1.getEdgesOf().containsKey(node2))
		{
			edge_size--;
			mc++;
			n1.getEdgesOf().remove(node2);

		}
		if(n2.getEdgesOf().containsKey(node1))
		{
			edge_size--;
			mc++;
			n2.getEdgesOf().remove(node1);

		}
		
		else {
			
			//System.out.println("Edge doesnt exist");
		}
	}
	else {
		return;
		//System.out.println("src or dest doesnt exist");
	}
}
/*
 * Return all the edges that come out from this vertex (using the HashMap of this vertex). 
 * @param list = save all the edges that come out of the parameter's node
 * 
 */
public Collection<Edge> getE(int node_id) {
	Collection<Edge> list=new ArrayList<Edge>();
	
	if(nodes.containsKey(node_id))
	{
		Node n=(Node) nodes.get(node_id);
		list.addAll(n.getEdgesOf().values());
	}
	return list;
}




   
public int nodeSize()
{
	return nodes.size();
}
   
public int edgeSize()
{
	return edge_size;
}

   
public int getMC()
{
	return mc;
}

}
