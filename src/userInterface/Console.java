/**
 * 
 */
package userInterface;

import java.util.ArrayList;

/**
 * @author Nemo Li
 * 
 */
public class Console {

	/**
	 * @param solutions
	 *            prints out all strings in solutions and the number of
	 *            solutions in them
	 */
	public static void printCountableSolutions(ArrayList<String> solutions) {

		for (int i = 0; i < solutions.size(); i++)
			System.out.print(solutions.get(i) + "\n");

		System.out.println("Total " + solutions.size()
				+ " distinct solutions found.");
	}

	/**
	 * @param solutions
	 * @param minQueensNeeded
	 * @param dimension
	 *            print function specifically for domination puzzle prints one
	 *            solutions and the minimum number of queens needed to dominate
	 */
	public static void printDominationSolutions(ArrayList<String> solutions,
			int minQueensNeeded, int dimension) {
		System.out.print(solutions.get(0));

		System.out.println("The minimum number of queens needed to dominate "
				+ "a " + dimension + "*" + dimension + " board is "
				+ minQueensNeeded + ".\n"
				+ "Above is one possible such arrangement.");
	}

	/**
	 * @param solutions
	 * @param max
	 * @param dimension
	 *            print function specifically designed for custom puzzle prints
	 *            all distinct solutions and the maximum number of custom pieces
	 *            could be placed on the board without any pair attacking each
	 *            other
	 */
	public static void printCustomSolutions(ArrayList<String> solutions,
			int max, int dimension) {

		for (int i = 0; i < solutions.size(); i++)
			System.out.print(solutions.get(i) + "\n");

		System.out.println("The maximum number of custom pieces could be placed on "
						+ "a "+ dimension + "*" + dimension + " board is " + max + ".\n"
						+ "Above are " + solutions.size() + " distinct solutions");
	}
}
