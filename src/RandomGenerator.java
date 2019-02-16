import java.util.*;

/**
 * this class is a random generator which selects an element of the array passed in with equal probability for each one.
 */
public class RandomGenerator {
    //Data Fields
    Random probabilityGen = new Random();

    //Constructor

    /**
     * Empty Constructor.
     */
    public RandomGenerator(){}

    //Methods

    //This selects an element of the array passed in with equal probability for each one.
    public static Ride selectRide(Ride[] rides){
        Ride result;
        int selectRide = new Random().nextInt(rides.length);
        result = rides[selectRide];
        return result;
    }
}
