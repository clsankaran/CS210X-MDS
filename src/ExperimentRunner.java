import java.util.*;

import com.cs210x.*;

/**
 * Class to deduce the identity of mystery data structures.
 */
public class ExperimentRunner {
	private static final int NUM_DATA_STRUCTURES_TO_DEDUCE = 5;
	private static final int NUM_TIMES_RUN = 1000;
	private static final int NUM_OPERATIONS = 3;
	private static final int[] Ns = { 1, 2, 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 300, 400, 500, 600, 700,
			800, 900, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000 };

	public static void main(String[] args) {
		final String cs210XTeamIDForProject4 = "ahoque";

		// Fetch the collections whose type you must deduce.
		// Note -- you are free to change the type parameter from Integer to whatever
		// you want. In this
		// case, make sure to replace (over the next 4 lines of code) Integer with
		// whatever class you prefer.
		// In addition, you'll need to pass the method getMysteryDataStructure a
		// "sample" (an instance) of
		// the class you want the collection to store.
		@SuppressWarnings("unchecked")
		final Collection210X<Integer>[] mysteryDataStructures = (Collection210X<Integer>[]) new Collection210X[NUM_DATA_STRUCTURES_TO_DEDUCE];
		for (int i = 0; i < NUM_DATA_STRUCTURES_TO_DEDUCE; i++) {
			mysteryDataStructures[i] = MysteryDataStructure.getMysteryDataStructure(cs210XTeamIDForProject4.hashCode(),
					i, new Integer(0));
		}
		// Write your code here...
		for (int mdsNum = 0; mdsNum < NUM_DATA_STRUCTURES_TO_DEDUCE; mdsNum++) { // get all 5 data structures
			System.out.println("Mystery Function " + mdsNum);
			for (int k = 0; k < NUM_OPERATIONS; k++) {
				doOperations(mysteryDataStructures, mdsNum, k); // do all 3 operations
			}
		}
	}

	/**
	 * Populates a data structure with numElemnts integers [0,numElements -1]
	 * 
	 * @param mds
	 *            array of mystery data types
	 * @param mdsIndex
	 *            index of mystery data types array
	 * @param numElements
	 *            num elements to populate with
	 */
	private static void populate(Collection210X<Integer>[] mds, int mdsIndex, int numElements) {
		mds[mdsIndex].clear();
		for (int j = 0; j < numElements; j++) {
			mds[mdsIndex].add(new Integer(j));
		}
	}

	/**
	 * Adds, removes, or searches based on numOperation
	 * 
	 * @param mds
	 *            array of mystery data types
	 * @param mdsIndex
	 *            index of mystery data tyeps array
	 * @param numOperation
	 *            which operation to do
	 */
	private static void doOperations(Collection210X<Integer>[] mds, int mdsIndex, int numOperation) {
		final Random random = new Random(); // instantiate a random number generator
		if (numOperation == 0) {
			System.out.println("N\tT (add(o))");
		} else if (numOperation == 1) {
			System.out.println("N\tT (remove(o))");
		} else {
			System.out.println("N\tT (contains(o))");
		}

		for (int i = 0; i < Ns.length; i++) {
			long total = 0;
			for (int h = 0; h < NUM_TIMES_RUN; h++) {
				populate(mds, mdsIndex, Ns[i]);
				final int elementToFind = random.nextInt(Ns[i]) + 1; // sometimes not in list
				// final int elementToFind = Ns[i] + 1; // worst case for heap
				final long start = CPUClock.getNumTicks();
				// Time how long it takes to find a single, randomly chosen item stored in the
				// mystery data structure
				if (numOperation == 0) {
					mds[mdsIndex].add(elementToFind);
				} else if (numOperation == 1) {
					mds[mdsIndex].remove(elementToFind);
				} else {
					mds[mdsIndex].contains(elementToFind);
				}
				final long end = CPUClock.getNumTicks();
				total = total + (end - start);
			}
			final long elapsedAverage = total / NUM_TIMES_RUN;
			System.out.println(Ns[i] + "\t" + elapsedAverage);
		}
	}

}
