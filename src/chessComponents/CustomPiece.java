/**
 * 
 */
package chessComponents;

/**
 * @author Nemo Li
 *
 */
public class CustomPiece extends Piece {
	
	private static final String NAME = "C";
	public static final String SPRITE = "sprites/blackRook.png";

	/**
	 * 
	 */
	public CustomPiece() {
		super();
	}

	/**
	 * @param x
	 * @param y
	 * @param parent
	 */
	public CustomPiece(int x, int y, Board parent) {
		super(x, y, parent);

	}

	/* (non-Javadoc)
	 * @see chessComponents.Piece#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see chessComponents.Piece#canAttack(int, int)
	 */
	@Override
	public boolean canAttack(int x, int y) {
		int range = getParentBoardDim()/2;	// by design, this custom piece could only attack in its
											// x and y direction, by half of the dimension of the board
		return (cordX == x && Math.abs(cordY-y) <= range) ||
				(cordY == y && Math.abs(cordX-x) <= range);
	}

	@Override
	public String getSprite() {
		return SPRITE;
	}

}
