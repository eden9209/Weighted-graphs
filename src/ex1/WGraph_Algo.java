package ex1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WGraph_Algo implements weighted_graph_algorithms
{
	private WGraph_DS dg;

	
	public void init(weighted_graph g)
	{
		this.dg = (WGraph_DS) g;	
	}

    public weighted_graph getGraph()
    {
    	return dg;
    }
    
    public weighted_graph copy()
    {

    	WGraph_DS m = new WGraph_DS();

    	Collection<node_info> node = this.dg.getV();
    	Iterator<node_info> it = node.iterator();
    	Iterator<node_info> it2 = node.iterator();
    	
    	while (it2.hasNext())
    	{
    		Node n = new Node();
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
    	
    	
    	
    
    public boolean isConnected()
    {
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
            Collection<Edge> edge=this.dg.getE(u.getKey());
            Iterator<Edge> etr=edge.iterator();
            
            while(etr.hasNext()) 
            {
            Edge ed=etr.next(); 
            
            if(ed.d_getTag()==0)
            {
            	size_bfs++;
            	ed.d_setTag(1);
            	node_info n=(node_info) ed.get_dest();
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
    	 * Diakstra algorithm
    	 * @param src
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
    
    



    public double shortestPathDist(int src, int dest)
    {
    	diaksta(src);
		Node ans =(Node) dg.getNode(dest);
		return ans.getWeight();
    	
    }
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
    
    

}
