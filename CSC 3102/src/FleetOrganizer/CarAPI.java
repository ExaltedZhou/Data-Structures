package FleetOrganizer;
/*
 * Sort vehicles according to their make,model,type, year
 * CSC 3102 Programming Project # 1
 * Albert Zhou
 * 10/4/2021
 */
public interface CarAPI 
{
  /**
    * gives the year manufactured
    * @return the year this vehicle was manufactured
    */
   int getYear();
   
   /**
    * gives the make of this vehicle
    * @return the make of this vehicle
    */
   String getMake();
   
   /**
    * gives the model of this vehicle
    * @return the model of this vehicle
    */
   String getModel();
   
   /**
    * gives the type of this vehicle
    * @return the type of this vehicle
    */
   String getType();
      
   /**
    * gives the vehicle record in format [year,make,model,type]
    * @return a string representation of this vehicle in the format [year,make,model,type]
    */
   @Override
    String toString();    
        /**
     * compares this Car and the specified Car
     * @param c a Car
     * @return a number less than 0 when this car comes before
     * the specified Car date; a number greater than 0 when this
     * car comes after the specified car; otherwise,0.
     * key: +year+make+model+type
     */
    public int compareTo(Car c);
}
