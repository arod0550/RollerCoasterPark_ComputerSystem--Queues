import java.util.*;

/**
 *this class represents the lines at the park. Whether it's the ride or other lines on the system. The data structure is a Queue which is an extension of linked lists.
 * queue is open at both its ends.
 * One end is always used to insert data (enqueue), go on the back of the line and the other is used to remove data (dequeue) let the first person in line in.
 */
public class VirtualLine extends LinkedList{

    //Data Field
    int numOfPeople;

    //Constructor
    /**
     * Empty Constructor.
     */
    public VirtualLine(){
        numOfPeople = 0;
    }

    //Methods

    /**
     * Adds the specified Person to the rear of the VirtualLine.
     * @param p
     *  object of Person being added to the virtual line.
     */
    public void enqueue(Person p) throws FullQueueException {
        add(p);
        numOfPeople++;
    }

    /**
     * Removes the Person at the front of the VirtualLine.
     */
    public void dequeue() throws EmptyQueueException {
        remove(0);
        numOfPeople--;

    }

    /**
     * Returns the student at the front of the VirtualLine without removing them from the Queue.
     * @return
     *  Returns Person at the front of the VirtualLine without removing them from the Queue.
     */
    public Person peek(){
        return peek();
    }

    /**
     * Getter for the number of people in the line.
     * @return
     * int representing th number of tpeople in the line.
     */
    public int getNumOfPeople() {
        return numOfPeople;
    }

    /**
     * Setter for the number of people in the line.
     * @param numOfPeople
     *  int representing the new number of people.
     */
    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }
}
