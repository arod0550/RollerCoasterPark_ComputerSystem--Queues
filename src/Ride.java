import java.util.*;

/**
 * This class represents a Ride and all the components of the rides.
 */
public class Ride {
    //Data Fields
    int numberOfRiders;
    int maxSizeHold;
    int maxPeopleOnRide;
    private int duration;
    private int timeLeft;
    private String Name = "";
    VirtualLine virtualLine = new VirtualLine();
    HoldingQueue holdingQueue = new HoldingQueue(maxSizeHold);
    List<Person>  peopleOnRide = new ArrayList<>();

    //Constructor
    /**
     * Empty Constructor.
     */
    public Ride(){}

    /**
     * default Constructor.
     * @param maxSizeHold
     * int representing the capacity of max holding list.
     * @param duration
     * time the ride takes
     * @param maxPeopleOnRide
     * int representing the max number of people on the ride.
     * @param Name
     * name of ride
     */
    public Ride(int timeLeft,int duration, int maxSizeHold, int maxPeopleOnRide, String Name) {
        this.timeLeft = timeLeft;
        this.duration = duration;
        this.maxSizeHold = maxSizeHold;
        this.maxPeopleOnRide = maxPeopleOnRide;
        this.Name = Name;
        this.holdingQueue.setMaxSize(maxSizeHold);
        numberOfRiders = 0;
    }
    //Methods

    /**
     * checks all the person's status of that holding queue
     * and returns true if none of them are available
     * false otherwise.
     * @return
     * returns true if none of them are available
     * false otherwise.
     */
    public boolean noneAvailableInHold(){
        boolean result = false;
        for(int i = 0; i < this.getHoldingQueue().size();i++){
            Person p = (Person) this.getHoldingQueue().get(i);
            if(p.getStatus() != Status.Available)
                result = true;
            else{
                return false;
            }
        }
        return result;
    }
    /**
     * checks all the person's status of that virtual queue
     * and returns true if none of them are available
     * false otherwise.
     * @return
     * returns true if none of them are available
     * false otherwise.
     */
    public boolean noneAvailableInVirtual(){
        boolean result = false;
        for(int i = 0; i < this.getVirtualLine().size();i++){
            Person p = (Person) this.getVirtualLine().get(i);
            if(p.getStatus() != Status.Available)
                result = true;
            else{
                return false;
            }
        }
        return result;
    }

    /**
     * Getter for the number of total riders
     * @return
     */
    public int getNumberOfRiders() {
        return numberOfRiders;
    }

    /**
     * Adds to the number of people who have riden the ride.
     * @param sizeOfNumPeopleInRide
     */
    public void AddtoNumRiders(int sizeOfNumPeopleInRide){
        numberOfRiders += sizeOfNumPeopleInRide;
    }

    /**
     * Setter for the number of total riders in the ride.
     * @param numberOfRiders
     */
    public void setNumberOfRiders(int numberOfRiders) {
        this.numberOfRiders = numberOfRiders;
    }

    /**
     * Getter for the the Capacity of holding queue.
     * @return
     * int representing the Capacity of the holding queue
     */
    public int getMaxSizeHold() {
        return maxSizeHold;
    }

    /**
     * Setter for the MaxSize of the Holding queue.
     * @param maxSizeHold
     * int representing Capacity of the holding queue.
     */
    public void setMaxSizeHold(int maxSizeHold) {
        this.maxSizeHold = maxSizeHold;
    }

    /**
     * Getter for the the capacity of ride.
     * @return
     * int representing the Capacity in terms of people of the ride
     */
    public int getMaxPeopleOnRide() {
        return maxPeopleOnRide;
    }

    /**
     * Setter for the capacity of the ride in terms of people.
     * @param maxPeopleOnRide
     * int representing the capacity of the ride in terms of people.
     */
    public void setMaxPeopleOnRide(int maxPeopleOnRide) {
        this.maxPeopleOnRide = maxPeopleOnRide;
    }

    /**
     * Getter for the duration of time stps the ride takes up.
     * @return
     * int representing time steps the ride takes up.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Setter for the duration of time steps the rides take up.
     * @param duration
     * int representing the new time steps the rides take up.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Getter for the minutes left until the end of the ride cycle
     * @return
     * int representing minutes left until the end of the ride cycle
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Setter for the minutes left until the end of the ride cycle.
     * @param timeLeft
     * int representing minutes left until the end of the ride cycle.
     */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     * Getter for the name of the ride.
     * @return
     * String representing the name of the ride.
     */
    public String getName() {
        return Name;
    }

    /**
     * Setter for the name of the ride.
     * @param name
     * String representing the new name of the ride.
     *
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Getter for the Virtual Line.
     * @return
     * a VirtualLine representing the Queue of people waiting for the ride.
     */
    public VirtualLine getVirtualLine() {
        return virtualLine;
    }

    /**
     * Setter for the Virtual Line.
     * @param virtualLine
     * an object of VirtualLine representing the new virtual line.
     */
    public void setVirtualLine(VirtualLine virtualLine) {
        this.virtualLine = virtualLine;
    }

    /**
     * Getter for the Virtual Line.
     * @return
     * A Holding line  representing The Queue of people who are next for the ride.
     */
    public HoldingQueue getHoldingQueue() {
        return holdingQueue;
    }

    /**
     * Setter for the Virtual Line.
     * @param holdingQueue
     * an object of HoldingQueue representing the new holding line.
     */
    public void setHoldingQueue(HoldingQueue holdingQueue) {
        this.holdingQueue = holdingQueue;
    }

    /**
     * Getter of people on the ride.
     * @return
     * an arrayList of people in the ride.
     */
    public List<Person> getPeopleOnRide() {
        return peopleOnRide;
    }

    /**
     * Setter for the people on the ride.
     * @param peopleOnRide
     * an arrayList representing the new people on the ride.
     */
    public void setPeopleOnRide(List<Person> peopleOnRide) {
        this.peopleOnRide = peopleOnRide;
    }

    /**
     * ToString fo the arrayList of people
     * @return
     * String representing the people in the list.
     */
    public String toStringList() {
        String results = "";

        if(peopleOnRide.isEmpty()){
            return "Empty.";
        }

        for (Person d : peopleOnRide) {
            results += d.toString() + ", ";
        }
        return results;
    }

    /**
     * ToString fo the holding line of people
     * @return
     * String representing the people in the holding list.
     */
    public String toStringHold() {
        String results = "";

        if(holdingQueue.isEmpty()){
            return "Empty.";
        }

        for (Object d : holdingQueue) {
            results += d.toString() + ",";
        }
        return results;
    }

    /**
     * ToString fo the virtual line of people
     * @return
     * String representing the people in the virtual line.
     */
    public String toStringVirtual() {
        String results = "";

        if(virtualLine.isEmpty()){
            return "Empty.";
        }

        for (Object d : virtualLine) {
            results += d.toString() + ",";
        }
        return results;
    }



}
