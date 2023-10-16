/*
 * Augment an AVL Tree, performing insertions and deletions
 * CSC 3102 Programming Project # 2
 * Albert Zhou
 * 10/21/2021
 * AVLTree
 */
package dendrologist;
import java.util.Scanner;
import java.util.function.Function;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

/**
 * A testbed for an augmented implementation of an AVL tree
 * @author William Duncan, YOUR NAME
 * @see AVLTreeAPI, AVLTreeException
 * <pre>
 * Date: 99-99-9999
 * CSC 3102 Programming Project # 2
 * Instructor: Dr. Duncan 
 * </pre>
 */
public class Dendrologist
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        String usage = "Dendrologist <order-code> <command-file>\n";
        usage += "  <order-code>:\n";
        usage += "  0 ordered by increasing string length, primary key, and reverse lexicographical order, secondary key\n";
        usage += "  -1 for reverse lexicographical order\n";
        usage += "  1 for lexicographical order\n";
        usage += "  -2 ordered by decreasing string\n";
        usage += "  2 ordered by increasing string\n";
        usage += "  -3 ordered by decreasing string length, primary key, and reverse lexicographical order, secondary key\n";
        usage += "  3 ordered by increasing string length, primary key, and lexicographical order, secondary key\n";      
        if (args.length != 2)
        {
            System.out.println(usage);
            throw new IllegalArgumentException("There should be 2 command line arguments.");
        }
        try {
         	Queue<String>stringQueue1=new LinkedList<>();
         	Queue<String>stringQueue2=new LinkedList<>();
        	Function<String,Boolean> fun1=(String a)->{return stringQueue1.add(a);};
        	Function<String,Boolean> fun2=(String a)->{return stringQueue2.add(a);};
        	String fName=args[0];
        	FileReader cf = new FileReader(fName);
        	Scanner sr = new Scanner(cf);
        	String OCode = args[1];
        	int OrderCode=Integer.parseInt(OCode);
        	if(OrderCode>3||OrderCode<-3) 
        		throw new IllegalArgumentException("Wrong Code!\n"+"order code\n"+
        										   "0	+string length -lexicographical order\n"+
        										   "-1	-lexicographical order\n"+"1	+lexicographical order\n"+
        										   "-2	-string length\n"+"2	+string length\n"+
        										   "-3	-string length -lexicographical order\n"+
        										   "3	+string length +lexicographical order");  
        	Comparator<String> cmp = String::compareTo;
        	Comparator<String> C1 = Comparator.comparing(String::toString);
        	Comparator<String> C2 = Comparator.comparing(String::length);
        	Comparator<String> C1R = C1.reversed();
        	Comparator<String> C2R = C2.reversed();
        	Comparator<String> C0 = C2.thenComparing(C1R);
        	Comparator<String> C_1 = C1R;
        	Comparator<String> C_2 = C2R;
        	Comparator<String> C_3 = C2R.thenComparing(C1R);
        	Comparator<String> C3 = C2.thenComparing(C1);        	       	
        	if(OrderCode==0) {
        		System.out.println("	List1 sample List when Order-Code is 0");
        		System.out.println("________________________________________ ");
        		System.out.println("Dendrologist +string length -lexicographical order");
        		cmp = C0;
        	}
        	if(OrderCode==-1) {
        		System.out.println("	List2 sample List when Order-Code is -1");
        		System.out.println("________________________________________ ");
        		System.out.println("Dendrologist -lexicographical order");
        		cmp = C_1;
        	}
        	if(OrderCode==1) {
        		System.out.println("	List3 sample List when Order-Code is 1");
        		System.out.println("________________________________________ ");
        		System.out.println("Dendrologist +lexicographical order");
        		cmp = C1;
        	}
        	if(OrderCode==-2) {
        		System.out.println("	List4 sample List when Order-Code is -2");
        		System.out.println("________________________________________ ");
        		System.out.println("Dendrologist -string length ");
        		cmp = C_2;
        	}
        	if(OrderCode==2) {
        		System.out.println("	List5 sample List when Order-Code is 2");
        		System.out.println("________________________________________ ");
        		System.out.println("Dendrologist +string length");
        		cmp = C2;
        	}
        	if(OrderCode==-3) {
        		System.out.println("	List6 sample List when Order-Code is -3");
        		System.out.println("________________________________________ ");
        		System.out.println("Dendrologist -string length -lexicographical order");
        		cmp = C_3;
        	}
        	if(OrderCode==3) {
        		System.out.println("	List7 sample List when Order-Code is 3");
        		System.out.println("________________________________________ ");
        		System.out.println("Dendrologist +string length +lexicographical order");
        		cmp = C3;
        	}
        	AVLTree<String> stringAVL = new AVLTree<>(cmp);
        	while(sr.hasNext()) {
        		String[]cmdNwd =sr.nextLine().split(" ");
        		if(cmdNwd[0].equals("stats")){
        			System.out.println("stats: size= " + stringAVL.size()+", height= " + stringAVL.height()+
        					", diameter= "+ stringAVL.diameter()+", complete?= "+stringAVL.isComplete()+
        					", full?= "+ stringAVL.isFull());
        		}
        		if(cmdNwd[0].equals("insert")) {
        			stringAVL.insert(cmdNwd[1].trim());
        			System.out.println("Inserted:"+cmdNwd[1]);
        			}
        		if(cmdNwd[0].equals("delete")) {
        			stringAVL.remove(cmdNwd[1].trim());
        			System.out.println("deleted:"+cmdNwd[1]);
        		}
        		if(cmdNwd[0].equals("traverse")) {
        			if(cmdNwd[1].equals("-0")) {
        				System.out.println("In-Order Traversal: ");
        				stringAVL.traverse(fun1);
        				stringQueue1.forEach(System.out::println);
        			} 			
        			else if(cmdNwd[1].equals("-1")){
        				System.out.println("Level-Order Traversal: ");
        				stringAVL.levelOrder(fun2); 
        				stringQueue2.forEach(System.out::println);
        			}	   			
        			else 
        				System.out.println("Traversal <-0 or -1>:");    
        			
        		}
        	} 
        	sr.close();
        }
        	catch(IOException e) {
        		System.out.println("file not found");
        	}       	        
    }   
}