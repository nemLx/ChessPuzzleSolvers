package chessComponents;

import java.util.ArrayList;

/**
 * @author Nemo Li
 * Board class facilitates the printing of a solved board puzzle
 */
public class Board {

	private int dimension;
	private int piecesCount;
	private Piece[][] piecesCordinated;		// using both array and list to store pieces is convenient to lookup
	private ArrayList<Piece> piecesListed;	// and insert, and facilitate use by other functions

	/**
	 * @param dim generates board with dimension dim
	 */
	public Board(int dim) {
		
		if (dim < 1) {
			System.err.println("Illegal dimension");
			System.exit(0);
		}

		dimension = dim;
		piecesCount = 0;
		piecesCordinated = new Piece[dimension][dimension];
		piecesListed = new ArrayList<Piece>(0);
	}

	/**
	 * @param piece
	 * @return true if could be and is placed
	 */
	public boolean placePiece(Piece piece) {

		if (piecesCount == dimension * dimension || piece == null		// checking for full board, null piece
				|| piece.cordX >= dimension || piece.cordY >= dimension // and coordinate out of bound,
				|| piece.cordX < 0 || piece.cordY < 0					// and occupied positions
				|| piecesCordinated[piece.cordX][piece.cordY] != null) {
			return false;
		}

		piecesCount++;
		piecesListed.add(piece);
		piecesCordinated[piece.cordX][piece.cordY] = piece;

		return true;
	}

	/**
	 * @param x
	 * @param y
	 * @return piece at (x,y)
	 */
	public Piece getPieceAt(int x, int y) {
		
		if (x >= dimension || y >= dimension || x < 0 || y < 0) 
			return null;
			
		return piecesCordinated[x][y];
	}

	/**
	 * returns a string representation of the board
	 */
	public String toString(){
		String output = new String();
		
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				Piece printed = piecesCordinated[i][j];
				if (printed == null)
					output = output+"*"+" ";
				else
					output = output+printed.getName()+" ";
			}
			output = output+"\n";
		}
		
		return output;
	}
	
	/**
	 * prints the board to console in a readable fashion
	 */
	public void print() {
		System.out.println(toString());
	}

	/**
	 * clears all pieces on board and reset piece count
	 */
	public void clear() {
		piecesCordinated = new Piece[dimension][dimension];
		piecesListed.clear();
		piecesCount = 0;
	}

	/**
	 * @return dimension of board
	 */
	public int getDim() {
		return dimension;
	}

	/**
	 * @return number of pieces on board
	 */
	public int getPiecesCount() {
		return piecesCount;
	}
}
