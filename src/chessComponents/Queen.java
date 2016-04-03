package chessComponents;

/**
 * @author Nemo Li
 *
 */
public class Queen extends Piece {

	private static final String NAME = "Q";
	public static final String SPRITE = "sprites/blackQueen.png";

	/**
	 * default constructor
	 */
	public Queen() {
		super();
	}

	/**
	 * @param x
	 * @param y
	 * @param parent
	 */
	public Queen(int x, int y, Board parent) {
		super(x, y, parent);
	}

	/* (non-Javadoc)
	 * @see chessComponents.Piece#canAttack(int, int)
	 */
	@Override
	public boolean canAttack(int x, int y) {
		return (cordX == x) || (cordY == y)							// checking for row column
				|| (Math.abs(cordX - x) == Math.abs(cordY - y));	// and diagnal
	}

	/* (non-Javadoc)
	 * @see chessComponents.Piece#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getSprite() {
		return SPRITE;
	}
	
	

}