import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the person going into the park.
 */
public class Person {
    //Data Fields
    private int number;//ID of the person
    private int maxLines;
    private int rideCounter;
    private String category = "";
    Status Status;
    List<Ride> lines = new ArrayList<>();

    //Constructors

    /**
     * Empty Constructor.
     */
    public Person(){}

    /**
     * Default constructor of the Person class.
     * @param number
     *  Int, represents the Id of the person.
     * @throws IllegalArgumentException
     *  if the number is not positive.
     * </dt>Preconditions
     * The number must be positive.</>
     *
     */
    public Person(int number, String category){
        if(number < 0)
            throw new IllegalArgumentException("Parameter is negative");
        this.number = number;
        this.category = category;
        rideCounter = 0;
    }

    //Methods

    /**
     * Getter for the number of rides the person has been on.
     * @return
     * int representing the number of rides the person has been on.
     */
    public int getRideCounter() {
        return rideCounter;
    }

    /**
     * Setter for the number of rides the person has been on.
     * @param rideCounter
     */
    public void setRideCounter(int rideCounter) {
        this.rideCounter = rideCounter;
    }

    /**
     * Adds 1 to counter to symbolize person went on ride.
     */
    public void rideCounterPlus(){
        rideCounter++;
    }
    /**
     * String represents category of the person.
     * @return
     * String representing the category of the person.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter for the Category of the person.
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Getter for the arraylist of lines the person is on.
     * @return
     */
    public List<Ride> getLines() {
        return lines;
    }

    /**
     * setter for the arraylist of lines the person is on.
     * @param lines
     * an array representing he number of lines the person is on.
     */
    public void setLines(List<Ride> lines) {
        this.lines = lines;
    }

    /**
     * Getter for the Id of the person
     * @return integer representing the id of the person.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter for the Id of the person.
     * @param number an integer representing the Id of the person.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter for the maximum number of lines the person can be on.
     * @return integer representing the maximum number of lines the person can be on.
     */
    public int getMaxLines() {
        return maxLines;
    }

    /**
     * Setter for the number of maximum number of lines the person can be on.
     * @param maxLines intger representing the maximum number of lines the person can be on.
     */
    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
    }

    /**
     * Getter for the current status of the person.
     * @return enum represeting the status of the person.
     */
    public Status getStatus() {
        return Status;
    }

    /**
     * Setter for the current status of the person.
     * @param status
     */
    public void setStatus(Status status) {
        Status = status;
    }

    /**
     * provides all factors of an object of person.
     * @return
     *      a string representing person's ID and category.
     */
    @Override
    public String toString() {
        return  "" + category +" "+ number;
    }

    /**
     * ToString for the rides people are on.
     * @return
     * String representing the rides each person is on.
     */
    public String toStringRides() {
        String results = "";

        if(lines.isEmpty()){
            return "None.";
        }

        for (Ride r: lines) {
            results += r.getName() + "       ";
        }
        return results;
    }
}
