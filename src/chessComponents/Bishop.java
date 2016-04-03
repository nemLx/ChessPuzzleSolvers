/**
 * 
 */
package chessComponents;

/**
 * @author Nemo Li
 *
 */
public class Bishop extends Piece {
	
	private static final String NAME = "B";
	public static final String SPRITE = "sprites/blackBishop.png";

	/**
	 * default constructor
	 */
	public Bishop() {
		super();
	}

	/**
	 * @param x
	 * @param y
	 * @param parent
	 */
	public Bishop(int x, int y, Board parent) {
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
		return Math.abs(cordX - x) == Math.abs(cordY - y);	//bishop can only attack diagonally
	}

	@Override
	public String getSprite() {
		return SPRITE;
	}

}
