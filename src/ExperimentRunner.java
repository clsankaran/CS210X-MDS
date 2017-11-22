import java.util.*;

import com.cs210x.*; 

/**
 * Class to deduce the identity of mystery data structures.
 */
public class ExperimentRunner {
    private static final int NUM_DATA_STRUCTURES_TO_DEDUCE = 5;
    private static final int NUM_TIMES_RUN = 1000;
    private static final int[] Ns = {1, 2, 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 
    		200, 300, 400, 500, 600, 700, 800, 900, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

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
        for (int mdtNum = 0; mdtNum < NUM_DATA_STRUCTURES_TO_DEDUCE; mdtNum++) { // get all 5 data structures
            final Random random = new Random();  // instantiate a random number generator
            
            System.out.println("Mystery Function " + mdtNum);
            System.out.println("N\tT (contains(o))");
            for (int i = 0; i < Ns.length; i++) {
	            for (int j = 0; j < Ns[i]; j++) {  // populate the mystery data structure with N numbers 0 to N-1
	                mysteryDataStructures[mdtNum].add(new Integer(j));
	            }
                // This is an example of measuring an operation's time cost *without* averaging -- the times will vary wildly!
                // You really should average...
                long total = 0;
                for (int h = 0; h < NUM_TIMES_RUN; h++) {
                    final int elementToFind = random.nextInt(Ns[i]) + 1; // somtimes not find it
                    final long start = CPUClock.getNumTicks();
                    // Time how long it takes to find a single, randomly chosen item stored in the mystery data structure
                    mysteryDataStructures[mdtNum].contains(elementToFind);
                    final long end = CPUClock.getNumTicks();
                    total = total + (end - start);
                }
                final long elapsedAverage = total / NUM_TIMES_RUN;
	
	                // Write a table of numbers (for different N -- here, we are just showing one value for simplicity) showing
	                // the relationship between N and the time-cost associated with searching (with the contains method) through
	                // a collection of N data.
	                System.out.println(Ns[i] + "\t" + elapsedAverage);
            }
        }
    }
}
