package solvers;

import java.util.ArrayList;

import chessComponents.*;

/**
 * @author Nemo Li
 *
 */
public abstract class PuzzleSolver {
	
	public ArrayList<Piece> pieceFrame;
	public ArrayList<Integer> frameAction;
	
	protected boolean animate = true;
	protected int dimension;
	protected int maxPieces;
	protected ArrayList<String> solutions;
	protected Board gameBoard;
	
	public PuzzleSolver(int dim){
		dimension = dim;
		maxPieces = dimension * dimension;
		solutions = new ArrayList<String> (0);
		gameBoard = new Board(dimension);
		pieceFrame = new ArrayList<Piece> (0);
		frameAction = new ArrayList<Integer> (0);
	}
	
	/**
	 * @return
	 */
	public ArrayList<String> getSolutions(){
		return solutions;
	}
	
	/**
	 * @return number of solutions to a puzzle
	 */
	public int getSolutionsCount(){
		return solutions.size();
	}
	
	/**
	 * @param queens
	 * A recursive method tries to solve the puzzle,
	 * given a set of pieces that are already placed on board
	 */
	public abstract void solve();
	
	/**
	 * @param pieces
	 */
	protected void refreshBoard(ArrayList<Piece> pieces) {
		gameBoard.clear();
		for (int i = 0; i < pieces.size(); i++)
			gameBoard.placePiece(pieces.get(i));
	}

	/**
	 * @return a string stating the solutions
	 */
	public abstract String constructSolutionText();
}
