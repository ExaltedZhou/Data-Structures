package powerseries;
/*
create Naive Sine and Fast Sine method 
CSC 3102 Programming Project # 0
Albert Zhou
9-6-2021
Sine class
*/

public class sineAnalyzer {
	public static void main(String args[])  {
		String pi = "\u0c3c0";
		System.out.println("Using Naive Sine: ");
		System.out.printf("sin(pi/6) = %.5f", Sine.naiveSine(Math.PI/6,1000));
		System.out.printf("\nsin(pi/4) = %.5f", Sine.naiveSine(Math.PI/4,1000));
		System.out.printf("\nsin(pi/3) = %.5f", Sine.naiveSine(Math.PI/3,1000));
		System.out.printf("\nsin(pi/2) = %.5f", Sine.naiveSine(Math.PI/2,1000));
		System.out.printf("\nsin(-pi/2) = %.5f", Sine.naiveSine(Math.PI/(-2),1000));
		System.out.println("\n\nUsing Fast Sine: ");
		System.out.printf("sin(pi/6) = %.5f", Sine.fastSine(Math.PI/6,1000));
		System.out.printf("\nsin(pi/4) = %.5f", Sine.fastSine(Math.PI/4,1000));
		System.out.printf("\nsin(pi/3) = %.5f", Sine.fastSine(Math.PI/3,1000));
		System.out.printf("\nsin(pi/2) = %.5f", Sine.fastSine(Math.PI/2,1000));
		System.out.printf("\nsin(-pi/2) = %.5f", Sine.fastSine(Math.PI/(-2),1000));
		
		double x = 2*Math.PI*Math.random();
		System.out.printf("%n%nx = %.5f", x);
		System.out.println("\n======================================================");
		System.out.printf("%-6s%25s%25s", "n", "sinx: NaiveSine(ns)", "sinx: FastSine(fs)" );
		System.out.println("\n------------------------------------------------------");
		for(int n = 100; n <= 1000; n += 100) {
			long nSmartTime = System.nanoTime();
			Sine.naiveSine(x, n);
			Long nEndTime = System.nanoTime();
			Long nElapseTime = nEndTime - nSmartTime;
			
			long fastSmartTime = System.nanoTime();
			Sine.fastSine(x, n);
			Long fastEndTime = System.nanoTime();
			Long fastElapseTime = fastEndTime - fastSmartTime;		
			System.out.printf("\n%-6d%20d%25d\n", n, nElapseTime, fastElapseTime);

		}
	}
}
