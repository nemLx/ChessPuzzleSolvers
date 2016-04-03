/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import chessComponents.*;

/**
 * @author Nemo Li
 *
 */
public class PieceTest {
	
	Board tester = new Board (8);

	/**
	 * Test method for {@link chessComponents.Piece#getName()}.
	 */
	@Test
	public void testGetName() {
		Queen p = new Queen(0,0,tester);
		assertEquals(p.getName(),"Q");
	}

	/**
	 * Test method for {@link chessComponents.Piece#Piece()}.
	 */
	@Test
	public void testPiece() {
		Piece p = new Queen();
		
		assertEquals(p.getX(),-1);
		assertEquals(p.getY(),-1);
	}

	/**
	 * Test method for {@link chessComponents.Piece#Piece(int, int, chessComponents.Board)}.
	 */
	@Test
	public void testPieceIntIntBoard() {
		Queen p = new Queen(3,7,tester);
		
		assertEquals(p.getParentBoardDim(), 8);
		assertEquals(p.getX(),3);
		assertEquals(p.getY(),7);
	}

	/**
	 * Test method for {@link chessComponents.Piece#setX(int)}.
	 */
	@Test
	public void testSetX() {
		Queen p = new Queen(3,7,tester);
		
		assertEquals(p.setX(-3),-1);
		assertEquals(p.setX(10),-1);
	}

	/**
	 * Test method for {@link chessComponents.Piece#setY(int)}.
	 */
	@Test
	public void testSetY() {
		Queen p = new Queen(3,7,tester);
		
		assertEquals(p.setY(-3),-1);
		assertEquals(p.setY(10),-1);
	}
	
	/**
	 * Test method for {@link chessComponents.Piece#setCords(int,int)}.
	 */
	@Test
	public void testSetCords() {
		Queen p = new Queen(3,7,tester);
		
		assertEquals(p.setCords(-1,10), -1);
	}
	
	/**
	 * Test method for {@link chessComponents.Piece#setId(int)}.
	 */
	@Test
	public void testSetId() {
		Queen p = new Queen(3,7,tester);
		
		assertEquals(p.setId(-1), -1);
	}

	/**
	 * Test method for {@link chessComponents.Piece#getX()}.
	 */
	@Test
	public void testGetX() {
		Queen p = new Queen(3,7,tester);
		
		assertEquals(p.getX(),3);
	}

	/**
	 * Test method for {@link chessComponents.Piece#getY()}.
	 */
	@Test
	public void testGetY() {
		Queen p = new Queen(3,7,tester);
		
		assertEquals(p.getY(),7);
	}
	
	/**
	 * Test method for {@link chessComponents.Piece#getId()}.
	 */
	@Test
	public void getId() {
		Queen p = new Queen(3,7,tester);
		
		assertEquals(p.getId(),59);
	}

	/**
	 * Test method for {@link chessComponents.Piece#getParentBoardDim()}.
	 */
	@Test
	public void testGetParentBoardDim() {
		Piece p = new Queen();
		Queen q = new Queen(1,7,tester);
		
		assertEquals(p.getParentBoardDim(), 0);
		assertEquals(q.getParentBoardDim(), 8);
	}

}
