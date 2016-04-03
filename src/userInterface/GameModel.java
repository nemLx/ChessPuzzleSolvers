/**
 * 
 */
package userInterface;

import java.util.ArrayList;

import chessComponents.*;

import solvers.*;

/**
 * @author Nemo Li
 *
 */
public class GameModel {
	
	public static final String SELECT_PUZZLE = "Select Puzzle";
	public static final String NQUEENS = "N-Queens";
	public static final String BISHOPS = "14-Bishops";
	public static final String DOMINATION = "Domination";
	public static final String CUSTOM = "Ranged Rooks";
	
	public static final int NUM_PUZZLES = 4;
	public static final int BISHOP_SIZE = 8;	// special fixed size for bishop puzzle
	
	public ArrayList<Piece> pieceFrame;		// contains frames of pieces during solving process
	public ArrayList<Integer> frameAction;	// contains actions add/remove a frame for each frame
	
	private String solutionText;
	private String puzzleName = SELECT_PUZZLE;
	private int dimension = -1;
	

	
	/**
	 * @param puzzle
	 */
	public void setPuzzle(String puzzle) {
		puzzleName = puzzle;
	}

	/**
	 * @param dim
	 */
	public void setBoardDimension(int dim) {
		dimension = dim;
	}

	/**
	 * @return : 1 if solved
	 * 			-1 if dimension is invalid
	 * 			-2 if puzzle selection is invalid
	 * 
	 * main solving delegate, selects puzzleSolver type
	 * and carries out solutions and animations
	 */
	public int solvePuzzle() {
		if (dimension < 1)
			return -1;
		
		if (SELECT_PUZZLE.equals(puzzleName)){
			return -2;
		}
		
		PuzzleSolver puzzle = selectPuzzle();
		
		puzzle.solve();
		
		solutionText = puzzle.constructSolutionText();
		pieceFrame = puzzle.pieceFrame;
		frameAction = puzzle.frameAction;
		
		return 1;
	}

	/**
	 * @return : correctly initialized puzzle
	 */
	private PuzzleSolver selectPuzzle() {
		PuzzleSolver puzzle = null;
		
		if (puzzleName.equals(NQUEENS)) {
			puzzle = new NQueensSolver(dimension);
		}else if (puzzleName.equals(BISHOPS)) {
			puzzle = new BishopSolver();
		}else if (puzzleName.equals(DOMINATION)){
			puzzle = new DominationSolver(dimension);
		}else if (puzzleName.equals(CUSTOM)){
			puzzle = new CustomSolver(dimension);
		}
		
		return puzzle;	// puzzle will not be null at this point
	}
	
	/**
	 * @param id : attacking piece's id
	 * @return : list of attacked positions
	 * 
	 * determines the piece type of the attacker, and
	 * all positions it attacks by its attacking pattern
	 */
	public ArrayList<Integer> getAttackedPositions(int id) {
		ArrayList<Integer> attacked = new ArrayList<Integer> (0);
		
		if (SELECT_PUZZLE.equals(puzzleName)){	// no puzzle selected, return empty list
			return attacked;
		}
		
		Piece attacker = retrieveAttacker(id);
		
		for (int i = 0; i < dimension*dimension; i++)
			if (attacker.canAttack(i))
				attacked.add(i);
		
		return attacked;
	}

	/**
	 * @param id : attacker's id
	 * @return : specific type of attacker
	 * 
	 * checks for specific type of attacker depending on the puzzle
	 */
	private Piece retrieveAttacker(int id) {
		Piece attacker;
		Board dummyBoard = new Board(dimension);
		
		if (puzzleName.equals(NQUEENS)) {
			attacker = new Queen(0,0,dummyBoard);
		}else if (puzzleName.equals(BISHOPS)) {
			attacker = new Bishop(0,0,dummyBoard);
		}else if (puzzleName.equals(DOMINATION)){
			attacker = new Queen(0,0,dummyBoard);
		}else{
			attacker = new CustomPiece(0,0,dummyBoard);
		}
		
		attacker.setId(id);
		
		return attacker;
	}
	
	/**
	 * @return : name of current puzzle
	 */
	public String getCurrentPuzzleName(){
		return puzzleName;
	}
	
	/**
	 * @return : dimension of current puzzle
	 */
	public int getCurrentPuzzleDim(){
		return dimension;
	}
	
	/**
	 * @return : a string explaining solutions
	 */
	public String getSolutionText(){
		return solutionText;
	}
}

