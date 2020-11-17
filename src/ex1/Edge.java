package ex1;
import java.io.Serializable;
import java.util.*; 


/* 
Edge class:
*Contains the inner class that includes the methods in the interface of node_info
*/
public class Edge implements Serializable {
/* 	
@param src = The vertex that the edge came out of.
*@param dst = The vertex that the edge came in to. 
*@param weight = The weight of the Edge, For Graph_Algo	
*/
private node_info src;
private node_info dest;
private double weight;

/*
 * Constructor with all the parameters
 *   
 *  */

public Edge(double weight,int ksrc,int kdest)
{
   this.weight=weight;
   this.src=new node_info(ksrc);
   this.dest=new node_info(kdest);	   
   
}

/*
 * Getters and Setters of the outer class:
 */
public double get_weight() {
	return weight;
}
public node_info get_src() {
	return src;
}
public node_info get_dest() {
	return dest;
}
public void set_src(node_info src) {
	this.src=src;
}
public void set_dest(node_info dest) {
	this.dest = dest;
}
public void set_weight(double _weight) {
	this.weight = weight;
}

/*
 * Getters and Setters of the Inner class:
 */
public void s_setkey(int k)
{
	src.key=k;
}
public int s_getKey()
{
return src.key;
}
public void d_setkey(int k)
{
	dest.key=k;
}
public int d_getKey()
{
return dest.key;
}

public String s_getInfo()
{
return src.info;
}
public void s_setInfo(String s)
{
src.info=s;
}

public String d_getInfo()
{
	return dest.info;
}
public void d_setInfo(String s)
{
	dest.info=s;
}

public double s_getTag()
{
return src.tag;
}
public void s_setTag(double t)
{
	src.tag=t;
}
public double d_getTag()
{
	return dest.tag;
}
public void d_setTag(double t)
{
	dest.tag=t;
}
/////////////////////////////////

//////////////////
/*
 * Inner class , private permission
 */	

 private class node_info
{
    private int key;
	private String info;
	private double tag;
	
	public node_info(int k)
	{
		key=k;
		
	}
	

}


}
