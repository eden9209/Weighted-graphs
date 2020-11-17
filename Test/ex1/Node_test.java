package ex1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ex1.Edge;
import ex1.Node;
import ex1.node_info;


class Nodetest {
	
	
	@Test
	void testaddEdge() {
		
		Node n0=new Node(); //k=0
		Edge e=new Edge(1.5,0,1);
		Edge e2=new Edge(3.4,3,2); //there is no node 2 and node 3 only node with k=0
		n0.addEdge(e); // add to the HashMap of n0 : 1,0=>1  
		n0.addEdge(e2);
		
		assertEquals(n0.getEdgesOf().containsKey(1),true);
		assertEquals(n0.getEdgesOf().containsKey(0),false);
		assertEquals(n0.getEdgesOf().containsKey(2),false);


	}
	

}


