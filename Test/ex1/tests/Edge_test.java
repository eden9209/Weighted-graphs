package ex1.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ex1.src.Edge;
import ex1.src.Node;
import ex1.src.node_info;



class Edge_test {
	
//node_info n0= new Node(); ///k=0
//node_info n1= new Node(); /// k=1

/*
 * I created an array of edge , each element contain edge: src_node,weight,dest_node
 * and i check if the method of s_getKey() works well
 * * remember s_getKey() is the key of the source node
 */
@Test
void test_s_getKey() {
	
	Edge[] edges=new Edge[10];
	for (int i = 1; i < 10; i++) {
		edges[i-1]=new Edge(2.5,i-1,i);
	}
	for (int i = 0; i < edges.length-1; i++) {
		assertEquals(edges[i].s_getKey(),i);
	}
	
}
/*
 * I created an array of edge , each element contain edge: src_node,weight,dest_node
 * and i check if the method of d_getKey() works well 
 * remember d_getKey() is the key of the dest node 
 */
@Test
void test_d_getKey() {
	
	Edge[] edges=new Edge[10];
	for (int i = 1; i < 10; i++) {
		edges[i-1]=new Edge(2.5,i-1,i);
	}
	for (int i = 0; i < edges.length-1; i++) {
		assertEquals(edges[i].d_getKey(),i+1);
	}
	
}



}



