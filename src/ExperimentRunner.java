import java.util.*;
import com.cs210x.*;

/**
 * Class to deduce the identity of mystery data structures.
 */
public class ExperimentRunner {
    private static final int NUM_DATA_STRUCTURES_TO_DEDUCE = 5;

    public static void main (String[] args) {
        final String cs210XTeamIDForProject4 = "ahoque";

        // Fetch the collections whose type you must deduce.
        // Note -- you are free to change the type parameter from Integer to whatever you want. In this
        // case, make sure to replace (over the next 4 lines of code) Integer with whatever class you prefer.
        // In addition, you'll need to pass the method getMysteryDataStructure a "sample" (an instance) of
        // the class you want the collection to store.
        @SuppressWarnings("unchecked") final Collection210X<Integer>[] mysteryDataStructures = (Collection210X<Integer>[]) new Collection210X[NUM_DATA_STRUCTURES_TO_DEDUCE];
        for (int i = 0; i < NUM_DATA_STRUCTURES_TO_DEDUCE; i++) {
            mysteryDataStructures[i] = MysteryDataStructure.getMysteryDataStructure(cs210XTeamIDForProject4.hashCode(), i, new Integer(0));
        }

        // Write your code here...
        for (int k = 0; k <=4; k++) {
            final Random random = new Random();  // instantiate a random number generator
            final int N[] = {1, 2, 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
            ;
            for (int i = 0; i < N[N.length - 1]; i++) {  // populate the mystery data structure with N numbers
                mysteryDataStructures[k].add(new Integer(i));
            }
            for (int j = 0; j < N.length; j++) {
                // This is an example of measuring an operation's time cost *without* averaging -- the times will vary wildly!
                // You really should average...
                long total = 0;
                for (int i = 0; i < 1000; i++) {
                    final int elementToFind = random.nextInt(N[j]);
                    final long start = CPUClock.getNumTicks();
                    // Time how long it takes to find a single, randomly chosen item stored in the mystery data structure
                    final boolean result = mysteryDataStructures[k].contains(elementToFind);
                    final long end = CPUClock.getNumTicks();
                    total = total + (end - start);
                }
                final long elapsedAverage = total / 1000;

                // Write a table of numbers (for different N -- here, we are just showing one value for simplicity) showing
                // the relationship between N and the time-cost associated with searching (with the contains method) through
                // a collection of N data.
                System.out.println("Mystery Function " + k);
                System.out.println("N\tT (contains(o))");
                System.out.println(N[j] + "\t" + elapsedAverage);
            }
        }
    }
}
