package drivers;

import solvers.*;
import userInterface.Console;

/**
 * @author Nemo Li
 *
 */
public class ConsoleDriver {

	private static final String NQUEENS = "nqueens";
	private static final String BISHOPS = "bishops";
	private static final String DOMINATION = "domination";
	private static final String CUSTOM = "custom";
	
	/**
	 * @param args containing game info
	 */
	public static void main(String[] args) {

		String gameTag = args[0];

		if (gameTag.equals(NQUEENS)) {
			nQueens(Integer.parseInt(args[1]));
		}else if (gameTag.equals(BISHOPS)) {
			bishops();
		}else if (gameTag.equals(DOMINATION)){
			domination(Integer.parseInt(args[1]));
		}else if (gameTag.equals(CUSTOM)){
			custom(Integer.parseInt(args[1]));
		}else{
			System.out.println("unrecognized game");
		}
	}

	/**
	 * @param d
	 */
	private static void custom(int d) {
		
		CustomSolver puzzle = new CustomSolver(d);
		
		puzzle.solve();
		
		Console.printCustomSolutions(puzzle.getSolutions(), puzzle.getMax(), d);
	}

	/**
	 * @param d
	 */
	private static void nQueens(int d) {
		
		NQueensSolver puzzle = new NQueensSolver(d);
		
		puzzle.solve();

		Console.printCountableSolutions(puzzle.getSolutions());
	}
	
	private static void bishops(){
		
		BishopSolver puzzle = new BishopSolver();

		puzzle.solve();
		
		Console.printCountableSolutions(puzzle.getSolutions());
	}
	
	/**
	 * @param d
	 */
	private static void domination(int d){
		
		DominationSolver puzzle = new DominationSolver(d);
		
		puzzle.solve();
		
		Console.printDominationSolutions(puzzle.getSolutions(), puzzle.getMinQueensNeeded(), d);
	}
}
