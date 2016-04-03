package chessComponents;

/**
 * @author Nemo Li 
 */
public abstract class Piece {

	protected int id; // every piece is assigned an id, which is the index of
						// the piece in the board, top left being 0
	protected int dim;
	protected int cordX;
	protected int cordY;
	protected Board parentBoard;

	/**
	 * @return name of the piece
	 */
	public abstract String getName();

	/**
	 * @param x
	 * @param y
	 * @return true is this piece can attack position (x,y) according to my
	 *         definition, a piece can attack itself, therefore it is the user's
	 *         responsibility to double check if the intention is to check if
	 *         one could attack itself
	 */
	public abstract boolean canAttack(int x, int y);

	/**
	 * @param other
	 * @return true is this piece can attack other piece
	 */
	public boolean canAttack(Piece other) {
		return this.canAttack(other.cordX, other.cordY);
	}

	/**
	 * @param id
	 * @return true if this piece can attack a piece with certain id
	 */
	public boolean canAttack(int id) {
		int cordX = id % dim;
		int cordY = (id - cordX) / dim;
		return this.canAttack(cordX, cordY);
	}

	/**
	 * default constructor, does not initialize piece
	 */
	public Piece() {
		cordX = -1;
		cordY = -1;
		id = -1;
		dim = 0;
		parentBoard = null;
	}

	/**
	 * @param x
	 * @param y
	 * @param parent
	 *            Assigns a new piece with position and board
	 */
	public Piece(int x, int y, Board parent) {
		parentBoard = parent;
		dim = parent.getDim();
		if (setX(x) < 0 || setY(y) < 0) {
			System.out.print("WARNING: Piece not on board");
			cordX = -1;
			cordY = -1;
			parentBoard = null;
		}
		id = x + dim * y;
	}

	/**
	 * @param x
	 * @return non-negative value if set x coordinate
	 */
	public int setX(int x) {
		if (x < 0 || x > dim)
			return -1;
		id = x+cordY*dim;
		return cordX = x;
	}

	/**
	 * @param y
	 * @return non-negative value if set y coordinate
	 */
	public int setY(int y) {
		if (y < 0 || y > dim)
			return -1;
		id = cordX+y*dim;
		return cordY = y;
	}

	/**
	 * @return x coordinate
	 */
	public int getX() {
		return cordX;
	}

	/**
	 * @return y coordinate
	 */
	public int getY() {
		return cordY;
	}

	/**
	 * @return id of a piece
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param x
	 * @param y
	 * @return non-negative value if both x and y are set
	 */
	public int setCords(int x, int y) {
		if (setX(x) < 0 || setY(y) < 0)
			return -1;
		
		return id = x + dim * y;
	}

	/**
	 * @param n
	 * @return non-negative value if x, y, and id are set
	 */
	public int setId(int n) {
		int tempX = n % dim;
		int tempY = (n - tempX) / dim;
		
		if (setCords(tempX, tempY)<0)
			return -1;
		
		return id = n;
	}

	/**
	 * @return parent board's dimension
	 */
	public int getParentBoardDim() {
		return dim;
	}
	
	public abstract String getSprite();
}
