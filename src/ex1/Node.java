package ex1;

import java.io.Serializable;
import java.util.*; 
/* 
 * Contains HashMap data structure for all the edges that start at this node.
 * The key is the data of the node of the destination.
 * The value is the edge between this node and the destination. 
 * for example :
 * //b.key,a<->b  c.key,b<->c  | Key: e.d_getKey(), Value :Edge
 */
public class Node implements node_info ,Serializable {
/*
* @param Ni = The HashMap of the vertex 
* @param data = The key number of this vertex
*/
private HashMap<Integer, Edge> Ni ;
private int data;
private String info;
private double tag;
private double _weight;


private static int count = 0;

/*
 * Default Constructor Initializes all  the parameters
 */
public Node() {
this.data=count;
count++;
this.Ni=new HashMap<>();
this.tag=0;
this.info="";
this._weight=0;
}
public Node(int k)
{
	this.data=k	;
	this.Ni=new HashMap<>();
	this.tag=0;
	this.info="";
	this._weight=0;
}


/*
 * This function add a Edge to the HashMap of this Node
 * explain: because my task is to implemented undirected weighted graph  
 * we need to add the edge to the both of the hash map 
*/
public void addEdge(Edge e) {
	
if(this.getKey() == e.s_getKey()) {
this.Ni.put(e.d_getKey(), e);
}

if(this.getKey() == e.d_getKey()) {
this.Ni.put(e.s_getKey(), e);
}
else
	return;
    //System.out.println("Wrong edge!!!");
}

/*
* return true if this<==>key are adjacent, as an edge between them.
* each node contains HashMap and the key is the data of the destination node (edge) 
*/
public boolean hasNi(int key)
{
  return this.Ni.containsKey(key);
	     
}
/*
 * This method returns a collection with all the Neighbor nodes of this node_data 
 * the value of each node is Collection of all the edges that connected to him
 * so i just iterate the edges and add the destination node to the list of the node info 
 */

public List<node_info> getNi()
{
	 if (this.Ni != null) {
		 
	Collection<Edge> e=this.Ni.values();
	
	List<node_info> list=new ArrayList<node_info>();
	
	Iterator<Edge> itr=e.iterator();
	
	while(itr.hasNext())
	{
		Edge z=itr.next();
		node_info k= (node_info) z.get_dest();
		list.add(k);
	}
	return list;
 }
	 else return null;
}
 

public List<Integer> getNi_k()
{
	 if (this.Ni != null)
	 {
	
	List<Integer> list=new ArrayList<Integer>();
	
	for (Integer key : this.Ni.keySet()) 
	{
		list.add(key);
	}
	return list;
	
	}

	 else {
	return null;
	 }
	 
}

	 



/* i get the unique key of the node that i want to remove
 * and after i remove from the HashMap of my node the entry of the unique key of the remove node  
 * 
*/
public void removeNode(node_info node)
{
	int k=node.getKey();
	if(this.Ni.containsKey(k)) {
	this.Ni.remove(k);
	}
	
}
/* 
 * return the HashMap    
*/
public HashMap<Integer,Edge> getEdgesOf(){
	return Ni;
}

/*
 * Getters and Setter of node_info interface
 */

/*
 * Return the weight of the vertex
 */
public double getWeight() {
	return _weight;
}

public void setWeight(double w) {
	this._weight=w;
}


public int getKey()
{
return this.data;
}

public String getInfo()
{
	return this.info;
}

public void setInfo(String s)
{
	this.info=s;
}

public double getTag()
{
	return this.tag;

}

public void setTag(double t)
{
	this.tag=t;
}
		
}
