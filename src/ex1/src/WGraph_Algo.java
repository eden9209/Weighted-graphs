package ex1.src;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WGraph_Algo implements weighted_graph_algorithms 
{
private WGraph_DS dg;
	
	
public void init(weighted_graph g)
{this.dg = (WGraph_DS) g;	
}

public weighted_graph getGraph()
{return dg;
}
    
 public weighted_graph copy()
{
WGraph_DS m = new WGraph_DS();
Collection<node_info> node = this.dg.getV();
Iterator<node_info> it = node.iterator();
Iterator<node_info> it2 = node.iterator();
while (it2.hasNext())
{  Node n = new Node();
  n = (Node) it2.next();
  m.addNode(n.getKey());
 }
    	
 while (it.hasNext()) {
 Node n = new Node();
 n = (Node) it.next();
   		
 Collection<Edge> edge = this.dg.getE(n.getKey());
 Iterator<Edge> ite = edge.iterator();
    		
 while (ite.hasNext()) 
 {
  Edge ed =  ite.next();
    	
   m.connect(n.getKey(), ed.d_getKey(),ed.get_weight());
  }
 
  }
   return m;
 }
 
/*BFS algorithm:
 * 1 step: if numbers of node in the graph is 0 or 1 the graph is considered to be connected ! 
 * 2 step: insert the first node to queue1 and after check his Neighbors ! 
 * 3 step: if we visit in node with (tag=0) we are increment the size_bfs and set node.tag to 1
 * 4 step: if dg.nodeSize()==size_bfs the graph is isConnected because we visit all the nodes
 * 5 step : in the end we Initialize all tags to zero for the next use of the BFS
*/  	
      
 public boolean isConnected() {
        if(this.dg.getV().size()==0)
        return true;
     	if(this.dg.getV().size() == 1 ) 
    		return true;
    	
    	int size=this.dg.nodeSize();
    	int size_bfs=0;
    	
    	Collection<node_info> node=this.dg.getV();
    	Iterator<node_info> it=node.iterator();

       LinkedList<node_info> queue1 = new LinkedList<node_info>(); 
       node_info m=it.next();
        queue1.add(m);
        
          while (!queue1.isEmpty())
          { 
        	
        	node_info u = queue1.remove(); 
            Collection<node_info> edge=this.dg.getV(u.getKey());
            Iterator<node_info> etr=edge.iterator();
            
            while(etr.hasNext()) 
            {
            node_info n=etr.next(); 
            
            if(n.getTag()==0)
            {
            	size_bfs++;
            	n.setTag(1);;
                queue1.add(n);
                                   
           } 
                      
       }
            
          }
          
      	this.zeroTagsMaxWeight();
         if(this.dg.nodeSize()==size_bfs)
         return true;
          else 
         return false;
        
    }
 
/*
* sets tag to 0 and weight MAX_VALUE on all nodes
*/
private void zeroTagsMaxWeight()
{
		Collection<node_info> nodes = dg.getV();
		
		for (node_info node : nodes) {
			Node n=(Node)node;
			n.setTag(0);
			n.setWeight(Integer.MAX_VALUE);
			n.setInfo("");
		}
}
/*
* 
* Diakstra algorithm:
* note : this algorithm tell us the shortest path from ONE node to every other node
* 
 * 1 step: use zeroTagsMaxWeight() -> all the tags of the nodes is 0 and their weight = max value 
 * 2 step: put the first node (src) to the ArrayList and set is weight to 0 !
 * 3 step: while the queue in not empty :
 * 4 step: remove the currNode from the queue and set his tag to 1 to not visit him again 
 * 5 step : examine the edge of currNode
 *  
 *  z - the currNode
 *  h - the destNode
 *  dstNodeW - the weight of h. 
 *  edge_dataW - the weight of the edge
 *  
 *  if(dstNodeW > z.getWeight() + edge_dataW)
 *  we update h Weight to the sum of: z.getWeight()+edge_data.get_weight (shortest path)
 *  
 *  now with the help of the getIndex we visit the vertex with the smallest known
 *  distance from the start vertex
 *  
*/
private void diaksta(int src)
{
    	  zeroTagsMaxWeight();
    	  ArrayList<node_info> nodes = new ArrayList<node_info>();
    	  nodes.add(dg.getNode(src));
    	  Node n=(Node)nodes.get(0);
    			  n.setWeight(0);
    			
    			  
    		while(!nodes.isEmpty())
    		{
    		node_info currNode = nodes.get(0);
    		Node z=(Node)currNode;
    		if(currNode.getTag() == 0)
    	    {
    		currNode.setTag(1);
    		nodes.remove(0);
    		Collection<Edge> edges = dg.getE(currNode.getKey());
    	 for (Edge edge_data : edges)
    	 {
    		node_info destNode = dg.getNode(edge_data.d_getKey());
    		Node h=(Node)destNode;			
    		double dstNodeW = h.getWeight();
    		double edge_dataW = edge_data.get_weight();
    		
    		if(dstNodeW > z.getWeight() + edge_dataW)
    		{
    		h.setWeight(z.getWeight()+edge_data.get_weight());
    		///for the : shortestPath function
    		destNode.setInfo(currNode.getKey() + "");
    		
    		if(destNode.getTag() == 0)
    		{ 
    			nodes.add(getIndex(nodes, h.getWeight()),destNode);
    		
    		}
    		
    		}
    	}   
    				
    	}
    			else
    			{
    				nodes.remove(0);
    			}
    		
     }
    		
 }

/*
 * get the insert position in sorted array
 */	
private int getIndex(ArrayList<node_info> nodes,double dstN)
{
	int minIn = 0;
	int maxIn = nodes.size() - 1;
	int mid = minIn + (maxIn - minIn) / 2;

	while(minIn <= maxIn){
		if(((Node) nodes.get(mid)).getWeight() == dstN)
		{
			return mid;
		}
		if(dstN > ((Node) nodes.get(mid)).getWeight()){
			minIn = mid + 1;
		}
		if(dstN < ((Node) nodes.get(mid)).getWeight()){
			maxIn = mid - 1;
		}
		mid = minIn + (maxIn - minIn) / 2 ;
	}
	return mid;
}
    
/*
use  diaksta algo
the weight of the dest node is the answer of the shortestPathDist between src and dest
*/    
public double shortestPathDist(int src, int dest)
    {
    	diaksta(src);
		Node ans =(Node) dg.getNode(dest);
		return ans.getWeight();
    	
    }
/*
use  diaksta algo
in the info we save the path ! 
*/ 

public List<node_info> shortestPath(int src, int dest)
    {
    	diaksta(src);
    	node_info node = dg.getNode(dest);
		if(((Node) node).getWeight() >= Integer.MAX_VALUE) {
			return null;
		}
		List<node_info> ans = new ArrayList<node_info>();
		while(!node.getInfo().isEmpty()){
			ans.add(0, node);
			node = dg.getNode(Integer.parseInt(node.getInfo()));
		}
		ans.add(0, node);
		return ans;
    	
    	
    }
    
/*
* Save a graph in a text file (using Yael's algorithm)
*/
    
public boolean save(String file)
    {
    	try{
			FileOutputStream file1 = new FileOutputStream(file); 
			ObjectOutputStream out = new ObjectOutputStream(file1);
			out.writeObject(this.dg);
			out.close(); 
			file1.close(); 

			System.out.println("Object has been serialized");
			return true;
		}   
		catch(IOException ex){
			System.out.println(ex.getMessage()); 
			return false;
		}
	}

	/*
	 * Set the graph from a file (using Yael's algorithm)
	 */
     
public boolean load(String file)
    {
    	try{    
			FileInputStream file1 = new FileInputStream(file); 
			ObjectInputStream in = new ObjectInputStream(file1);
			this.dg = (WGraph_DS)in.readObject();
			in.close(); 
			file1.close(); 
			System.out.println("Object has been deserialized");
			return true;
		} 

		catch(IOException | ClassNotFoundException ex) 
		{ 
			System.out.println("IOException is caught"); 
			return false;
		} 
    	
    	
    }
    /* override equals to check if two object are equals 
     * (using Simon Code)
     */
public boolean equals(Object o)
    {
    	if(this==o) return true;
    	if(o==null|| getClass()!=o.getClass()) return false;
    	
    	WGraph_DS that =(WGraph_DS) o;
    	
    	for(node_info m:this.dg.getV())
    	{
    		if(!that.nodes.containsKey(m.getKey()))
    		{
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    
    
    

}
