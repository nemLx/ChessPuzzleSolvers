package solvers;

import java.util.ArrayList;
import chessComponents.*;

/**
 * @author Nemo Li
 *
 */
public class NQueensSolver extends PuzzleSolver {

	/**
	 * @param dim dimension of the board
	 * @param board the board the puzzle is laid out
	 */
	public NQueensSolver(int dim) {
		super(dim);
	}
	
	/* (non-Javadoc)
	 * @see solvers.PuzzleSolver#solve(java.util.ArrayList)
	 */
	public void solve() {
		ArrayList<Piece> queens = new ArrayList<Piece> (0);
		solve(queens);
	}
	
	/**
	 * @param queens
	 */
	private void solve(ArrayList<Piece> queens){
		int numQueensPlaced = queens.size();
		int nextLine = numQueensPlaced;

		if (!isValidNewQueen(queens)) 	// first check if any queen could attack
			return;						// the last queen being tested

		if (!queens.isEmpty() && !(queens.get(0) == null)	// if the last queen is safe and no more
				&& numQueensPlaced == dimension) {			// queen is needed, print this solution
			refreshBoard(queens);
			solutions.add(gameBoard.toString());
			animate = false;
			return;
		}

		Queen testPiece = new Queen(0, nextLine, gameBoard);

		for (int i = 0; i < dimension; i++) {	// try out every possible position of 
			testPiece.setX(i);					// placing a queen
			setUpTestPiece(testPiece, queens);
			solve(queens);
			takeDownTestPiece(testPiece, queens);
		}
	}

	/**
	 * @param queens list of queens last of which is to be validated
	 * @return true if no other queen could attack the last queens in
	 * the list, notice it is all other queens in the list, excluding
	 * the last queen itself
	 */
	private boolean isValidNewQueen(ArrayList<Piece> queens) {

		int queensCount = queens.size();

		if (queensCount < 2)
			return true;

		Piece lastQueen = queens.get(queensCount - 1);

		for (int i = 0; i < queensCount - 1; i++) {	// queensCount-1 since do not
			if (lastQueen.canAttack(queens.get(i)))	// want to check for the last
				return false;						// queen itself
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
		
		pieceFrame.add(new Queen(frame.getX(), frame.getY(), gameBoard));
		frameAction.add(action);
	}

	@Override
	public String constructSolutionText() {
		return "Total " + solutions.size()+ " distinct solutions found.";
	}
}
