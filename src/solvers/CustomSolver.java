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
public class CustomSolver extends PuzzleSolver {

	private int max;

	/**
	 * @param dim
	 */
	public CustomSolver(int dim) {
		super(dim);
		max = 0;
	}

	/**
	 * @return maximum pieces could be placed on this board
	 */
	public int getMax() {
		return max;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solvers.PuzzleSolver#solve()
	 */
	@Override
	public void solve() {
		ArrayList<Piece> pieces = new ArrayList<Piece>(0);
		solve(pieces, 0);
	}

	/**
	 * @param pieces
	 * @param cordY
	 *            This method assumes there could be at most two pieces on a
	 *            single row and it tests all possible positions of either one
	 *            or two pieces on all rows, and records those with maximum
	 *            number of pieces, notice the maximum number of piece occur
	 *            in the very first solution
	 */
	private void solve(ArrayList<Piece> pieces, int cordY) {
		if (cordY == dimension) {
			resolveSolution(pieces);
			return;
		}
		Piece testPieceOne = new CustomPiece(0, 0, gameBoard);
		Piece testPieceTwo = new CustomPiece(0, 0, gameBoard);
		boolean hasSecondPiece = false;

		for (int cordX = 0; cordX < dimension; cordX++){	// outer loop for testing first piece
			if (!isSafe(cordX, cordY, pieces)) continue;
		
			setUpTestPiece(cordY, cordX, testPieceOne, pieces);	
			for (int secondCordX = cordX + dimension / 2; secondCordX < dimension; secondCordX++){	// inner loop for testing
				if (!isSafe(secondCordX, cordY, pieces)) continue;									// second piece, if there
																									// is one, solve for the
				hasSecondPiece = true;																// next row with both pieces
				setUpTestPiece(cordY, secondCordX, testPieceTwo, pieces);							// added
				solve(pieces, cordY + 1);															
				takeDownTestPiece(testPieceTwo, pieces);
			}
			if (!hasSecondPiece)				// if there is no second piece could be placed
				solve(pieces, cordY + 1);		// solve for next row with only first piece
			takeDownTestPiece(testPieceOne, pieces);
		}
	}

	/**
	 * @param pieces
	 *			   This method adds pieces in a solution to the board
	 *			   and adds the board string to solutions list
	 *			   then possibly updates the max
	 */
	private void resolveSolution(ArrayList<Piece> pieces) {
		if (pieces.size() < max)
			return;
		
		refreshBoard(pieces);
		solutions.add(gameBoard.toString());
		max = pieces.size();
		animate = false;
	}

	/**
	 * @param cordY
	 * @param cordX
	 * @param testPiece
	 * @param pieces
	 *			   Helper method set a test piece's coordinates and adds to the piece list
	 */
	private void setUpTestPiece(int cordY, int cordX, Piece testPiece, ArrayList<Piece> pieces) {
		testPiece.setCords(cordX, cordY);
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
		
		pieceFrame.add(new CustomPiece(frame.getX(), frame.getY(), gameBoard));
		frameAction.add(action);
	}

	/**
	 * @param x
	 * @param y
	 * @param pieces
	 * @return true is a position cannot be attacked by any of the pieces in the list
	 */
	private boolean isSafe(int x, int y, ArrayList<Piece> pieces) {
		for (int i = 0; i < pieces.size(); i++)
			if (pieces.get(i).canAttack(x, y))
				return false;
		return true;
	}

	@Override
	public String constructSolutionText() {
		return "The maximum number of custom pieces could be placed on "
				+ "a "+ dimension + "*" + dimension + " board is " + max + ".\n"
				+ "Above is one solution.";
	}
}
