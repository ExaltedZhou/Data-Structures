package powerseries;
/*
create Naive Sine and Fast Sine method 
CSC 3102 Programming Project # 0
Albert Zhou
9-6-2021
sineAnalyzer
*/

public class Sine {
	public static double naiveSine(double x, int n)  {
		if(n<0)
			System.out.println ("Error, positive n");
		double Sum = 0;
		for (int i = 1; i <= n; i++) {
			double term = 1;
			for (int j = 1; j <= 2*i-1; j++) {
				term *= x/j;
			}
			if(i % 2 == 0)
				Sum -= term;
			else
				Sum += term;
		}
		return Sum;
	}
	public static double fastSine(double x, int n) {
		if(n<0)
			System.out.println("Error, positive n");
		double term = x; double sum = 0;
		for(int i = 1; i <= n; i ++) {
			if(i%2 == 0)
				sum -= term;
			else sum += term;
			term *= (x*x)/(2*i*((2*i)+1));
		}
		return sum;
	}
}
