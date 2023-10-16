package AVLInteger;
import java.util.Scanner;
import java.util.function.Function;
import dendrologist.AVLTree;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class AVLInteger {
	public static void main(String[]args) {
		try {
		String intFN=args[0];
		FileReader ifn = new FileReader(intFN);
		
		Scanner sc = new Scanner(ifn);
		Queue<Integer> q1 = new LinkedList();
		Function<Integer,Boolean> func1 = (Integer a)->{return q1.add(a);};
		Queue<Integer> q2 = new LinkedList();
		Function<Integer,Boolean> func2 = (Integer a)->{return q2.add(a);};
		Comparator<Integer>cm = Integer::compareTo;
		AVLTree<Integer> intAVL = new AVLTree<Integer>(cm);
		while(sc.hasNextLine()) {
			String[]strArr = sc.nextLine().split(" ");
			if(strArr[0].equals("stats")) {
				System.out.println("height = "+intAVL.height()+"; size = "+intAVL.size()+";isComplete?"+intAVL.isComplete());
			}
			if(strArr[0].equals("insert")) {
				intAVL.insert(Integer.parseInt(strArr[1]));
				System.out.println("insert: "+Integer.parseInt(strArr[1]));
			}
			if(strArr[0].equals("delete")) {
				intAVL.remove(Integer.parseInt(strArr[1]));
				System.out.println("delete: "+Integer.parseInt(strArr[1]));
			}
			if(strArr[0].equals("traverse")) {
				if(strArr[1].equals("-0")) {
					intAVL.traverse(func1);
					q1.forEach(System.out::println);
				}
				if(strArr[1].equals("-1")) {
					intAVL.levelOrder(func2);
					q2.forEach(System.out::println);
				}
				
			}
				
		}
		sc.close();
	}
		catch(IOException e) {
			System.out.println("filenotfound");
		}
	} 
	
}
