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
public class DominationSolver extends PuzzleSolver {

	private int lowerBound;		// lower bound of the number of queens needed
	private int upperBound;		// upper bound of the number of queens needed
	private int minQueensNeeded;
	private int[] attacked;		// an array specifying how many pieces could attack a certain position

	/**
	 * @param dim
	 */
	public DominationSolver(int dim) {
		super(dim);

		lowerBound = dim / 2;								// these lower and upper bound are according to
		upperBound = (int) Math.ceil(dimension / 2) + 1;	// all know results for 1*1 to 25*25 boards
		minQueensNeeded = 0;
		attacked = new int[maxPieces];
	}
	
	/**
	 * @return minimum number of queens needed to dominate
	 */
	public int getMinQueensNeeded(){
		return minQueensNeeded;
	}

	/* (non-Javadoc)
	 * @see solvers.PuzzleSolver#solve()
	 */
	public void solve() {

		Piece diagPiece = new Queen(0, 0, gameBoard);
		ArrayList<Piece> pieces = new ArrayList<Piece>(0);

		for (int i = lowerBound; i <= upperBound; i++)				// only tries values in the range of lower to upper bound
			for (int x = 0; x < dimension; x++) {
				setUpTestPiece(diagPiece, x+dimension*x, pieces);	// also assumes there will always be a solution where one
																	// of the queens is on the diagonal
				if (solve(i, 1, pieces)){
					refreshBoard(pieces);
					solutions.add(gameBoard.toString());
					return;
				}

				takeDownTestPiece(diagPiece, pieces);
			}
	}

	/**
	 * @param neededPieceCount
	 * @param currentPieceCount
	 * @param pieces
	 * @return true if a solution exists with neededPieceCount number of queens that dominate
	 *			   Helper solve function that tests all possible arrangements of a certain number
	 *			   of queens, see if any dominate, assumes that all queens are on different rows
	 *			   and columns, which could always be true, but reduces computation time
	 */
	private boolean solve(int neededPieceCount, int currentPieceCount, ArrayList<Piece> pieces) {
		
		if (currentPieceCount >= neededPieceCount) {	// base case, reached need number of queens
			if (allAttacked(pieces)) {					// if all position are attacked, then a solution
				minQueensNeeded = currentPieceCount;	// is found, other wise return false
				animate = false;
				return true;
			} else
				return false;
		}
		
		Piece testPiece = new Queen(0, 0, gameBoard);

		for (int id = 0; id < maxPieces; id++) {	// loop through all positions on board

			if (!isLegal(id, pieces))	// a position is legal if no other queens share 
				continue;				// same row or column, if not legal, skip

			setUpTestPiece(testPiece, id, pieces);
			if (solve(neededPieceCount, currentPieceCount + 1, pieces))	// place a queens in the legal position
				return true;											// then iterate with one less queen needed
			takeDownTestPiece(testPiece, pieces);
		}
		return false;
	}

	/**
	 * @param p
	 * @param id
	 * @param pieces
	 *			   Helper function sets up id and update attacked position of
	 *			   a test piece
	 */
	private void setUpTestPiece(Piece p, int id, ArrayList<Piece> pieces) {
		p.setId(id);
		pieces.add(p);
		updateAttacked(id, true);
		processFrames(p,1);
	}

	/**
	 * @param p
	 * @param pieces
	 *			   Helper function reverses the effect of a test piece
	 */
	private void takeDownTestPiece(Piece p, ArrayList<Piece> pieces) {
		pieces.remove(p);
		updateAttacked(p.getId(), false);
		processFrames(p,0);
	}
	
	/**
	 * @param frame
	 * @param action
	 */
	private void processFrames(Piece frame, int action){
		if (!animate)
			return;
		
		pieceFrame.add(new Queen(frame.getX(), frame.getY(), gameBoard));
		frameAction.add(action);
	}

	/**
	 * @param id
	 * @param pieces
	 * @return true is no other queen in the list share row or column with a position
	 */
	private boolean isLegal(int id, ArrayList<Piece> pieces) {
		int testX = id % dimension;
		int testY = (id - testX) / dimension;

		for (int i = 0; i < pieces.size(); i++) {
			Piece testPiece = pieces.get(i);
			if (id == testPiece.getId() || testX == testPiece.getX()
					|| testY == testPiece.getY())
				return false;
		}

		return true;
	}

	/**
	 * @param id
	 * @param add
	 *			   Helper function keeps attacked positions on board updated
	 */
	private void updateAttacked(int id, boolean add) {
		Piece testPiece = new Queen(0, 0, gameBoard);
		testPiece.setId(id);

		for (int i = 0; i < maxPieces; i++)
			if (testPiece.canAttack(i)) {
				if (add)
					attacked[i]++;	// if adding a queen, then all positions it attacks is incremented
				else
					attacked[i]--;	// if removing a queen, do the reverse
			}
	}

	/**
	 * @param pieces
	 * @return true if all positions on board could be attacked by the list of queens
	 */
	private boolean allAttacked(ArrayList<Piece> pieces) {
		for (int i = 0; i < maxPieces; i++)
			if (attacked[i] < 1)
				return false;

		return true;
	}

	@Override
	public String constructSolutionText() {
		return "The minimum number of queens needed to dominate "
				+ "a " + dimension + "*" + dimension + " board is "
				+ minQueensNeeded + ".\n"
				+ "Above is one possible such arrangement.";
	}

}
