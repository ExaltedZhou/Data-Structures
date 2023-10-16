/**
 * A testbed for a weighted digraph abstract data type implementation
 * and implementations of some classical graph algorithms that use the
 * ADT
 * @see GraphAPI, Graph, City
 * @author Duncan, Albert Zhou
 * <pre>
 * usage: GraphDemo <graphFileName>
 * <graphFileName> - a text file containing the description of the graph
 * in DIMACS file format
 * Date: 11-17-2021
 * course: csc 3102
 * programming project 3
 * Instructor: Dr. Duncan
  *
 * DO NOT REMOVE THIS NOTICE (GNU GPL V2):
 * Contact Information: duncanw@lsu.edu
 * Copyright (c) 2021 William E. Duncan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>
 * </pre>
 */
package graphdemo;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.Comparator;
import java.util.PriorityQueue; 
import java.util.concurrent.atomic.AtomicBoolean;

public class GraphDemo
{
   public static final Double INFINITY = Double.POSITIVE_INFINITY;

   public static void main(String []args) throws GraphException
   {
      if (args.length != 1)
      {
         System.out.println("Usage: GraphDemo <filename>");
         System.exit(1);
      }
     
      City c1, c2;
      Scanner console;
      int menuReturnValue, i,j;
      Function<City,PrintStream> f = aCity -> System.out.printf("%-2d  %-30s%n",aCity.getKey(),aCity.getLabel().trim());      
      Graph<City> g = readGraph(args[0]);
      if(g.isPath(new City(1),new City(5))) {
    	  System.out.println(11);
      }
      else
    	  System.out.println(00);
      long s = g.size();
      menuReturnValue = -1;
      while (menuReturnValue != 0)
      {
         menuReturnValue = menu();
         switch(menuReturnValue)
         {
             case 1: //Traversal BFS(G) and DFS(G')
                //invoke the relevant traversal method
                // Output should be aligned in two-column format as illustrated below:
                // 1     Charlottetown
                // 4     Halifax
                // 2     Edmonton         
            	
                System.out.println();
                System.out.println("BFS Traversal of the Graph In "+args[0]);
                System.out.println("==========================================================================");   
                //invoke the bfsTraverse method
                g.bfsTraverse(f);
                System.out.println("==========================================================================");
                System.out.println();               
                System.out.println("PostOrder DFS Traversal of the Graph In "+args[0]);
                System.out.println("==========================================================================");
                //invoke the dfsTraverse method  
                g.dfsTraverse(f);
                System.out.println("==========================================================================");
                System.out.println();
                System.out.println();
                break;          
             case 2: //Check if a digraph is strongly connected
            	 System.out.println();
                //Add code here to output:
                //"The digraph in <filename> is strongly connected.",
                //if the digraph is strongly connected, and 
                //"The digraph in <filename> is not strongly connected.",
                //if the digraph is not strongly connected.
                if(isStronglyConnected(g))
                    System.out.println("The digraph in" + args[0] + " is strongly connected.");
                if(!isStronglyConnected(g))
                    System.out.println("The digraph in " + args[0] + " is not strongly connected.");
                //End add code here
                System.out.printf("%n%n");
                break;                 
            case 3://Shortest-path algorithm
            	int n = (int)g.size();
                console = new Scanner(System.in);
                System.out.printf("Enter the source vertex: ");      
                int initial = console.nextInt();
                System.out.printf("Enter the destination vertex: ");      
                int dest = console.nextInt();
                double[] dist = new double[n];
                int[] pred = new int[n];
                City city1 = g.retrieveVertex(new City(initial));
                City city2 = g.retrieveVertex(new City(dest));
            	ArrayList<Integer> preq=new ArrayList();  	
            	ArrayList<Integer> preq1=new ArrayList(); 
                if (g.isPath(new City(initial), new City(dest)) && g.isPath(new City(dest), new City(initial)))
                {    
                   	System.out.printf("Shortest round trip from %s to %s:%n",g.retrieveVertex(new City(initial)).getLabel().trim(),g.retrieveVertex(new City(dest)).getLabel().trim());				   
                   	System.out.println("=========================================================================================");                              
                   	System.out.println(city1.getLabel() + " ->" + city2.getLabel() + ":");
                  	dijkstra(g,dist,pred,initial,dest); 
                  	int temp=dest;
                  	while(temp!=initial) {
                  		preq.add(temp);
                  		temp=pred[temp-1];
                  	}
                  	preq.add(initial);
                  	Integer[] pre=preq.toArray(new Integer[0]);
                  	int al=preq.size(); 
                  	for(int k=al-1;k>0;k--) {
                	   City ctA = g.retrieveVertex(new City(pre[k-1]));
                	   City ctB = g.retrieveVertex(new City(pre[k]));                	  
                	   System.out.println(ctB.getLabel()+ "	-> " + ctA.getLabel()+"	"+(dist[pre[k-1]-1]-dist[pre[k]-1]));}               
                  	 System.out.println("Distance: "+dist[dest-1]+" mi");
                  	 double dist1=dist[dest-1];
                   	 System.out.println();
                   	 dijkstra(g,dist,pred,dest,initial); 
                   	 System.out.println(city2.getLabel() + " ->" + city1.getLabel() + ":");
                   	int temp1=initial;
                  	while(temp1!=dest) {
                  		preq1.add(temp1);
                  		temp1=pred[temp1-1];
                  	}
                  	preq1.add(dest);
                  	Integer[] pre1=preq1.toArray(new Integer[0]);
                  	int al1=preq1.size(); 
                  	for(int k=al1-1;k>0;k--) {
                	   City ctA = g.retrieveVertex(new City(pre1[k-1]));
                	   City ctB = g.retrieveVertex(new City(pre1[k]));                	  
                	   System.out.println(ctB.getLabel()+ "	-> " + ctA.getLabel()+"	"+(dist[pre1[k-1]-1]-dist[pre1[k]-1]));}      
                  	 System.out.println("Distance: "+dist[initial-1]+" mi");
                  	 double dist2=dist[initial-1];
                  	 System.out.println("========================================================================================="); 
                  	 System.out.println("Round Trip Distance: "+(dist1+dist2)+" mi");
                  	 System.out.println();    
                   //Add code here to print each leg of the trip from the source to the destination
                   //using the format below, where the columns are left-aligned and the distances
                   //are displayed to the nearest hundredths.
                   //For example:
                   //Baton Rouge -> New Orleans:
                   //Baton Rouge            ->   Gonzales                  10.20 mi
                   //Gonzales               ->   Metaire                   32.00 mi
                   //Metaire                ->   New Orleans                7.25 mi
                   //Distance: 49.75 mi
                   //
                   //New Orleans -> Baton Rouge
                   //New Orleans            ->   Metaire                    8.00 mi
                   //Metaire                ->   Gonzales                  33.00 mi
                   //Gonzalen               ->   Baton Rouge               10.00 mi
                   //Distance: 51.00 mi
                   //==============================================================
                   //Round Trip Distance: 100.75 mi
                   
				   
				   //End code                     
                }
                else
                   System.out.printf("There is no path.%n%n");
                break;              
            case 4: //in-deg topoSort of V(G)       	
               System.out.println();
               int[] top = topSortInDeg(g);
               if (top != null)
               {
                   System.out.println("Topological Sorting of The Graph In "+args[0]);
                   System.out.println("==========================================================================");           
                   for (i=1; i<=g.size(); i++)
                   {
                       c1 = g.retrieveVertex(new City(top[i-1]));
                       f.apply(c1);
                   }
                   System.out.println("==========================================================================");
               }
               else
                   System.out.println("No topological ordering possible. The digraph in "+args[0]+" contains a directed cycle.");
               System.out.printf("%n%n");
               break;
            default: ;
         } //end switch
      }//end while
   }//end main

   /**
    * This method reads a text file formatted as described in the project description.
    * @param filename the name of the DIMACS formatted graph file.
    * @return an instance of a graph.
    */
   private static Graph<City> readGraph(String filename)
   {
      try
      {
         Graph<City> newGraph = new Graph();
         try (FileReader reader = new FileReader(filename)) 
         {
            char temp;
            City c1, c2, aCity;
            String tmp;
            int k, m, v1, v2, j, size=0, nEdges=0;
            Integer key, v1Key,v2Key;
            Double weight;
            Scanner in = new Scanner(reader);
            while (in.hasNext())
            {
                tmp = in.next();
                temp = tmp.charAt(0);
                if (temp == 'p')
                {
                    size = in.nextInt();
                    nEdges = in.nextInt();
                }
                else if (temp == 'c')
                {
                    in.nextLine();
                }
                else if (temp == 'n')
                {
                    key = in.nextInt();
                    tmp = in.nextLine();
                    aCity = new City(key,tmp);
                    newGraph.insertVertex(aCity); 
                }
                else if (temp == 'e')
                {
                    v1Key = in.nextInt();
                    v2Key = in.nextInt();
                    weight = in.nextDouble();
                    c1 = new City(v1Key);
                    c2 = new City(v2Key);
                    newGraph.insertEdge(c1,c2,weight); 
                }
            }
         }
         return newGraph;
      }
      catch(IOException exception)
      {
            System.out.println("Error processing file: "+exception);
      }
      return null;
   } 

   /**
    * Display the menu interface for the application.
    * @return the menu option selected.
    */  
   private static int menu()
   {
      Scanner console = new Scanner(System.in);
      //int option;
      String option;
      do
      {
         System.out.println("         BASIC WEIGHTED GRAPH APPLICATION   ");
         System.out.println("==================================================");
         System.out.println("[1] Traversal BFS(G) and DFS(G)");
         System.out.println("[2] Check G for Strong Connectivity");
         System.out.println("[3] Dijkstra's Shortest Round Trip in G");
         System.out.println("[4] Topological Ordering of V(G)");
         System.out.println("[0] Quit");
         System.out.println("==================================================");
         System.out.printf("Select an option: ");         
         option = console.nextLine().trim();
         try
         {
             int choice = Integer.parseInt(option);
             if (choice < 0 || choice > 4)
             {
                System.out.println("Invalid option...Try again");
                System.out.println();
             }
             else
                return choice;
         }
         catch(NumberFormatException e)
         {
            System.out.println("Invalid option...Try again");
         }                           
      }while(true);
   }
   

   /**
    * This method computes the cost and path arrays using the 
    * Dijkstra's single-source shortest path greedy algorithm.
    * @param g an instance of a weighted directed graph
    * @param dist an array containing shortest distances from a source vertex
    * @param pred an array containing predecessor vertices along the shortest path
    * @throws GraphException on call to retrieveEdge on non-existent edge
    */
   private static void dijkstra(Graph<City> g, double[] dist, int[] pred, int source, int destination) throws GraphException
   {
       int n = (int)g.size();
       boolean[] seen = new boolean[n];       
       class Node
       {
           public int id;
           public double key;
           public Node() {}
           public Node(int v, double k)
           {
               id = v;
               key = k;
           }           
       }
       Comparator<Node> cmp = (v1, v2) -> 
       {
           double d = v1.key - v2.key;
           if (d < 0)
               return -1;
           if (d > 0)
               return 1;
           return v1.id - v2.id;         
       };
       //Defining an instance of the PriorityQueue class that uses the comparator
       //and complete the implementation of the algorithm   
      // AtomicInteger index=new AtomicInteger(0);
       PriorityQueue<Node> pq = new PriorityQueue<>(cmp);
       if(g.isEmpty())
    	   throw new GraphException("empty graph");
    
       for(int i = 0;i<n;i++) {
    	   dist[i]=INFINITY;
    	   pred[i]=-1;
       }
       dist[source-1]=0;
     //  pred[source-1]=source;
       seen[source-1]=true;
       pq.add(new Node(source,0));
       int midCity = source;  
       while(!pq.isEmpty()) {      	       	   
    	   midCity = pq.remove().id;  
    	   if(midCity==destination)
    		   break;
    	   for(int i=1;i<=n;i++) {
    		   if(g.isEdge(new City(midCity),new City(i))) {
    			   double cost = g.retrieveEdge(new City(midCity), new City(i));
    			   if(cost==0)
    				   throw new GraphException("error");
    			   if((dist[midCity-1]+cost)<dist[i-1])
    				   dist[i-1]=dist[midCity-1]+cost;
    			   if(!seen[i-1]) {
    				   seen[i-1]=true;					     				   
    				   pred[i-1]=midCity;  
    				   Node midNode = new Node(i,dist[i-1]);
    				   pq.add(midNode);    
    			   } 
    		   	}
    	   } 
       }
   }

  /**
    * Generates a topological labeling of the specified graph by repeatedly
    * selecting a vertex with 0 in-degree and then removing the vertex and all
    * its out-going edges from the graph. It explores the digraph in lexicographical
    * order when adding a new vertex to the topological ordering.
    * @param g a digraph
    * @return an array containing topological ordering of the vertices of the specified digraph if one
    * exists; otherwise, null
    * @throws GraphException when the specified digraph is empty
    * <pre>
    * Note: This method should not mutate (modify) the original graph in anyway;
    * </pre>
    */
   private static int[] topSortInDeg(Graph<City> g) throws GraphException
   {
      //implement this method
      //In-degree-based topological sort
	 //  Comparator<City> com=Comparator.comparing(City::getLabel);
	//   Graph<City> g=new Graph<City>(com);
	//    g = readGraph(args[0]);
	   if(g.isEmpty())
		   throw new GraphException("empty");
	   int n=(int)g.size();
	   boolean[] seen=new boolean[n];
	   int[] inD=new int[n];
	   int[] top=new int[n];
	   Stack<Integer> sk=new Stack();
	   int index1=0;
	   for(int i=1;i<=n;i++) {
		   top[i-1]=-1;
		   inD[i-1]=(int)g.inDegree(new City(i));
		   if(inD[i-1]==0) {
			   sk.add(i);
		   top[index1]=i;
		   index1++;}
	   }
	   for(int l:inD)System.out.print(l);
	   for(int l:top)System.out.print(l);	
	   if(sk.isEmpty())
		   return null;
	   top[0]=sk.peek();
	   seen[top[0]-1]=true;
	   AtomicInteger index=new AtomicInteger(0);
	   while(!sk.isEmpty()) {  
		   int tp=sk.pop();
		   for(int i=1;i<=n;i++) {
			   if(g.isEdge(new City(tp), new City(i))) {
					inD[i-1]--;
					if(inD[i-1]==0&&!seen[i-1]) {
						top[index1]=i;
						seen[i-1]=true;
						sk.push(i);
						index1++;
					   }
			   }	   		
		   }
	   } 
	   
		 if(index1<n)
			 return null;
       return top;
   }   
   
   /**
    * Determines whether the digraph is strongly connected
    * @param g a digraph
    * @return true is there is a directed path between every
    * pair of vertices; otherwise, false
 * @throws GraphException 
    */
   public static boolean isStronglyConnected(Graph g) throws GraphException
   {
       //Implement this method
	
	   if(g.isEmpty())
		   throw new GraphException("empty");
	   int n=(int)g.size();   
       AtomicInteger index=new AtomicInteger(0);
       AtomicBoolean sc=new AtomicBoolean();
       boolean seen[]=new boolean[n];
       int[] vOrder=new int[n];
       Stack<Integer> stk=new Stack();
       int v=1;
       seen[0]=true;
       vOrder[0]=1;
       stk.push(v);
       while(!stk.isEmpty()) {
    	   for(int i=1;i<=n;i++) {
    		   v=stk.peek();
    		   if(g.isEdge(new City(v), new City(i))) {
    			   v=i;   			   
    			   if(!seen[i-1]) {
    				   stk.push(i);
    				   index.incrementAndGet();
    				   vOrder[index.get()]=i;
    				   seen[i-1]=true;
    			   }
    		   }     		 
    		} 
    	   v=stk.pop();
       }
       int j=vOrder[n-1];
       for(boolean k:seen) {
    	   if(!k)
    		   sc.set(false);
    	   else if(k&&g.isPath(new City(j),new City(1)))
    		   sc.set(true);    	  
       }    
       return sc.get();
   }
}

