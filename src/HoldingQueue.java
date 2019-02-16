import java.util.*;

/**
 * This represents the lines before the rides.
 * The data structure is a Queue which is an extension of linked lists.
 * queue is open at both its ends.
 * One end is always used to insert data (enqueue), go on the back of the line and the other is used to remove data (dequeue) let the first person in line in.
 */
public class HoldingQueue extends VirtualLine {
    //Data fields
    private int maxSize;
    int numOfHolders;

    //Constructor
    /**
     * Default Constructor.
     */
    public HoldingQueue(int maxSize){
        super();
        this.maxSize = maxSize;
        numOfHolders = 0;
    }

    //Method
    /**
     * Adds the specified Person to the rear of the HoldingQueue.
     * @param p
     *  object of Person being added to the HoldingQueue.
     */
    @Override
    public void enqueue(Person p) throws FullQueueException {
        if(size() == maxSize){
            throw new FullQueueException();
        }
        add(p);
        numOfHolders++;
    }

    /**
     * Removes the Person at the front of the HoldingQueue.
     */
    @Override
    public void dequeue() throws EmptyQueueException {
        if(isEmpty()){
            throw new EmptyQueueException();
        }
        remove(0);
        numOfHolders--;
    }

    /**
     * Returns the student at the front of the HoldingQueue without removing them from the Queue.
     * @return
     *  Returns Person at the front of the VirtualLine without removing them from the Queue.
     */
    @Override
    public Person peek() {
        return peek();
    }

    /**
     * Getter for the max size of the line.
     * @return
     * int representing the max size of the line.
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Setter for the Max size of the line.
     * @param maxSize
     * int representing the new max size of the line.
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Getter for the number of people in the holding line.
     * @return
     * int representing the number of people in the holding line.
     */
    public int getNumOfHolders() {
        return numOfHolders;
    }

    /**
     * Setter for the number of people in the holding line.
     * @param numOfHolders
     * int representing the new number of people in the holding line.
     */
    public void setNumOfHolders(int numOfHolders) {
        this.numOfHolders = numOfHolders;
    }
}
