# Weighted-graphs
Implementation of undirected weighted Graph : Data Structure And Algorithms

Author : Eden Shemesh

In This assignment I implemented a Undirected and weighted graphs.

The Project contains:
4 Class: Node (implements node_info) , Edge , WGraph_DS(implements weighted_graph) , WGraph_Algo(implements weighted_graph_algorithms) .
3 interfaces : node_info , weighted_graph , weighted_graph_algorithms .
4 Junit test case : Edge_Test , Node_test , WGraph_DSTest , WGraph_AlgoTest.

 the implementation of node_info interface is the Node class:
 Contains HashMap data structure for all the edges that start at this node.
 The key is the data of the node of the destination.
 The value is the edge between this node and the destination. 
 for example :
 b.key,a<->b  c.key,b<->c  | Key: e.d_getKey(), Value :Edge
 


 the implementation of weighted_graph interface is the WGraph_DS class:
 The implementation of weighted_graph interface.
 Contains HashMap data structure for all the nodes in the graph.
 The key is the data of the node.
 The value is the node.
 example: 1,a 2,b  .. |data,Node

 the implementation of weighted_graph_algorithms interface is the  WGraph_Algo class:
 include Algorithms:
 1. init from graph = initial new graph from other graph
 2. isConnected = check if the graph is strongly connected
 3. shortestPath = return the shortest route through the nodes (source and destination) 
 4. copy = deep copy a graph
 5. save ( File)
 6. Load ( File )

