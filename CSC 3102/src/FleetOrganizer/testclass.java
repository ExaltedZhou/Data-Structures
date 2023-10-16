package FleetOrganizer;
/*
 * Sort vehicles according to their make,model,type, year
 * CSC 3102 Programming Project # 0
 * Albert Zhou
 * 10/4/2021
 */
import java.io.IOException;
import java.util.Comparator;
import java.util.ArrayList;

public class testclass {
	public static void main(String[] args) throws IOException, HeapException
    {
		Comparator<Car> com = Comparator.comparing(Car::getYear);
		Heap<Car> testH = new Heap<Car>(com);
		Car car1= new Car(2000,"A","B","C");
		Car car2= new Car(1990,"D","E","F");
		Car car3= new Car(2010,"G","H","C");
		testH.insert(car1);
		testH.insert(car2);
		testH.insert(car3);
		for(int i=0;i<testH.size();i++) {
			
			System.out.println(testH.remove());}
		
    }     
}
