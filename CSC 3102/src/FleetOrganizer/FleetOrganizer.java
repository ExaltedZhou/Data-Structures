package FleetOrganizer;
/*
 * Sort vehicles according to their make,model,type, year
 * CSC 3102 Programming Project # 1
 * Albert Zhou
 * 10/4/2021
 * vehicles,Car, CarAPI, Heap, HeapAPI
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
public class FleetOrganizer 
{
    public static void main(String[] args) throws IOException, HeapException
    {
        String usage = "FleetOrganizer <data-file> <order-key>\n";
        usage += "Record for the vehicles must be organized in the data file with records, one per row,\n";
        usage +=  "and with fields comma-delimited, as follows:\n";
        usage += "<year>,<make>,<model>,<type>\n";
        try {
    	Scanner sf = new Scanner(System.in);
    	System.out.print("Enter file name and path, example src/vehicles: ");
    	String vehicle = sf.nextLine();
    	FileReader cars = new FileReader(vehicle);
    	Scanner sff = new Scanner(cars);
        Scanner sc = new Scanner(System.in);
        System.out.print("-2(-make-model-type-year)\n-1(-year-make-mode-type)\n0(-type+year-make-model)"
        			+ "\n1(+year+make+model+type)\n2(+make+model+type+year)\n"
        			+ "Enter a valid order code[-2, -1, 0, 1, 2]:");
       	int Ocode = sc.nextInt();
       	if(Ocode>2||Ocode<-2)
       		throw new HeapException("wrong code");
       	Comparator<Car> C1=Comparator.comparing(Car::getYear);
       	Comparator<Car> C2=Comparator.comparing(Car::getMake);
       	Comparator<Car> C3=Comparator.comparing(Car::getModel);
       	Comparator<Car> C4=Comparator.comparing(Car::getType);
       	Comparator<Car> C1R=C1.reversed();
       	Comparator<Car> C2R=C2.reversed();
       	Comparator<Car> C3R=C3.reversed();
       	Comparator<Car> C4R=C4.reversed();
       	Comparator<Car> C_n2= C2R.thenComparing(C3R).thenComparing(C4R).thenComparing(C1R);       	
       	Comparator<Car> C_n1= C1R.thenComparing(C2R).thenComparing(C3R).thenComparing(C4R);
       	Comparator<Car> C_0= C4R.thenComparing(C1).thenComparing(C2R).thenComparing(C3R);
       	Comparator<Car> C_1= C1.thenComparing(C2).thenComparing(C3).thenComparing(C4);
       	Comparator<Car> C_2= C2.thenComparing(C3).thenComparing(C4).thenComparing(C1);
       	Comparator<Car> com=Car::compareTo;
       	Heap<Car> carH=new Heap<Car>(com);
       	if(Ocode==2) {	
   			System.out.println("		Listing 5: Sample List when Order-Code is 2");
   			System.out.println("__________________________________________________________________________");    							   
   			System.out.println("Fleet: +make+model+type+year");
    		com = C_2;
    	}
       	if(Ocode==1) {	
    		System.out.println("		Listing 4: Sample List when Order-Code is 1");
   			System.out.println("__________________________________________________________________________");
    		System.out.println("Fleet: +year+make+model+type");
    		com = C_1;
    	}
        if(Ocode==0) {	
    		System.out.println("		Listing 3: Sample List when Order-Code is 0");
    		System.out.println("__________________________________________________________________________");
    		System.out.println("Fleet: -type+year-make-model");
    		com = C_0;
    	}
        if(Ocode==-1) {	
    		System.out.println("		Listing 2: Sample List when Order-Code is -1");
   			System.out.println("__________________________________________________________________________");
    		System.out.println("Fleet: -year-make-model-type");
    		com = C_n1;
    	}
        if(Ocode==-2) {	
    		System.out.println("		Listing 1: Sample List when Order-Code is -2");
    		System.out.println("___________________________________________________________________________");
    		System.out.println("Fleet: -make-model-type-year");
    		com = C_n2;
    	}
        
        while(sff.hasNextLine()) {		
        	String[] cRecord = (sff.nextLine()).split(",");
           	Car cEntry = new Car(Integer.parseInt(cRecord[0]),cRecord[1],cRecord[2],cRecord[3]);	
        	carH.insert(cEntry);		
        }		
        while(!carH.isEmpty()) 
        	System.out.println((carH.remove().toString()));	
        	System.out.println("__________________________________________________________________________");        	
        sf.close();
        sc.close();
        sff.close();
        }
        catch (IOException e) {
        	System.out.println("file not found");
        }
    }     
}
