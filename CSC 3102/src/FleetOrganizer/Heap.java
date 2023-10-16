package FleetOrganizer;
/*
 * Sort vehicles according to their make,model,type, year
 * CSC 3102 Programming Project # 1
 * Albert Zhou
 * 10/4/2021
 * HeapAPI
 */
import java.util.ArrayList;
import java.util.Comparator;
public class Heap<E extends Comparable<E>> implements HeapAPI<E>
{    
   /**
    * A complete tree stored in an array list representing this 
    * binary heap
    */
   private ArrayList<E> tree;
   /**
    * A comparator lambda function that compares two elements of this
    * heap when rebuilding it; cmp.compare(x,y) gives 1. negative when x less than y
    * 2. positive when x greater than y 3. 0 when x equal y
    */   
   private Comparator<? super E> cmp;
   private int length;
   
   public Heap()
   {
	  super();
      cmp = (E::compareTo);
      tree=new ArrayList<E>(); 
      length=0;
   }
   
   /**
    * A parameterized constructor that uses an externally defined comparator    
    * @param fn - a trichotomous integer value comparator function   
    */
   public Heap(Comparator<? super E> fn)
   {
	   cmp=fn;
	   tree=new ArrayList<E>();
	   length=0;
   }

   @Override
   public boolean isEmpty()
   {	   
	   return length==0;
   }

   @Override
   public void insert(E obj)
   {  	  
	  tree.add(obj);	
	  int loc=length-1;	  	  
      while(loc>0) {  
    		  rebuild((loc-1)/2);
    		  loc =(loc-1)/2;

      }
      length++;
   
   }
   @Override
   public E remove() throws HeapException
   {
      if(tree.isEmpty()) throw new HeapException("Empty Heap");
      E data = tree.get(0);
      tree.set(0, tree.get(length-1));
      if(length>1) {   	  
      	rebuild(0);}	
      length--;     
      return data;
     
   }
 
   @Override
   public E peek() throws HeapException
   {
	  if(tree.isEmpty()) throw new HeapException("Empty Heap");
      return tree.get(0);
      
   }

   @Override
   public int size()
   {
      return length;
   }
   
   /**
    * Swaps a parent and child elements of this heap at the specified indices
    * @param place an index of the child element on this heap
    * @param parent an index of the parent element on this heap
    */
   private void swap(int place, int parent)
   {
      E temp = tree.get(place);
      tree.set(place, tree.get(parent));
      tree.set(parent, temp);
   }

   /**
    * Rebuilds the heap to ensure that the heap property of the tree is preserved.
    * @param root the root index of the subtree to be rebuilt
    */
   private void rebuild(int root)
   {
	   int lft = (root*2)+1;
	   int rgt = (root*2)+2;
	   while(root<length/2&&length>0) {
		   if(cmp.compare(tree.get(root), tree.get(lft))>0||cmp.compare(tree.get(root), tree.get(rgt))>0){
			   if(cmp.compare(tree.get(lft), tree.get(rgt))>0){
					   swap(rgt,root);
					   rebuild(rgt);
			   }
			   else {
				   swap(lft,root);
				   rebuild(lft);
			   }
		   }
		   else break;		   	   
   }
}
   }
