/**
 * 
 */
package solvers;

import java.util.ArrayList;
import chessComponents.*;

/**
 * @author Nemo Li
 * 
 */
public class BishopSolver extends PuzzleSolver {

	private static final int DIMENSION = 8;
	private static final int MAX_NUM_BISHOPS = 14;
	private static final int END_FIRST_ROW = 7;
	private static final int START_LAST_ROW = 56;

	/**
	 * default constructor
	 */
	public BishopSolver() {
		super(DIMENSION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solvers.PuzzleSolver#solve(java.util.ArrayList)
	 */
	@Override
	public void solve() {
		ArrayList<Piece> pieces = new ArrayList<Piece>(0);
		solve(pieces, 0);
	}

	/**
	 * @param bishops
	 * @param id
	 *            This method recursively tries all possible combinations of 14
	 *            bishops on the border of the board, since to place all 14
	 *			  bishops, none of them could be in the middle
	 */
	private void solve(ArrayList<Piece> bishops, int id) {
		if (bishops.size() == MAX_NUM_BISHOPS) {	// base case: upon reaching 14 bishops
			resolveSolution(bishops);				// resolve the solutions
			return;
		}

		while (id < maxPieces) {

			if (!onBorder(id)) {	// skip a position if its is not on the border
				id++;
				continue;
			}

			Piece testPiece = new Bishop(0, 0, gameBoard);
			testPiece.setId(id);

			if (isValidNewBishop(bishops, testPiece)) {	// if the new bishop could be placed
				setUpTestPiece(testPiece, bishops);		// without others attacking it, place
				solve(bishops, id + 1);					// it and try next position
				takeDownTestPiece(testPiece, bishops);
			}

			id++;	// increment positions across the board
		}
	}

	/**
	 * @param bishops
	 *			  This method adds pieces in a solutions to the board
	 *			  and add a board string to solutions list
	 */
	private void resolveSolution(ArrayList<Piece> bishops) {
		refreshBoard(bishops);
		solutions.add(gameBoard.toString());
		animate = false;
	}

	/**
	 * @param id
	 * @return true is a position on board is on the borders
	 */
	private boolean onBorder(int id) {
		return (id < END_FIRST_ROW) || (id > START_LAST_ROW)				// top or bottom row
				|| (id % DIMENSION == 0) || ((id + 1) % DIMENSION == 0);	// left or right column
	}

	/**
	 * @param bishops
	 * @param test
	 * @return true if no bishops in the list could attack the test piece
	 */
	private boolean isValidNewBishop(ArrayList<Piece> bishops, Piece test) {
		for (int i = 0; i < bishops.size(); i++) {
			if (test.canAttack(bishops.get(i)))
				return false;
		}

		return true;
	}
	
	private void setUpTestPiece(Piece testPiece, ArrayList<Piece> pieces) {
		pieces.add(testPiece);
		processFrames(testPiece, 1);
	}
	
	private void takeDownTestPiece(Piece testPiece, ArrayList<Piece> pieces){
		pieces.remove(testPiece);
		processFrames(testPiece, 0);
	}

	private void processFrames(Piece frame, int action){
		if (!animate)
			return;
		
		pieceFrame.add(new Bishop(frame.getX(), frame.getY(), gameBoard));
		frameAction.add(action);
	}

	@Override
	public String constructSolutionText() {
		return "Total " + solutions.size() + " distinct solutions found."
				+ "\n" + "Above is one solution.";
	}
}
