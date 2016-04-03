/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessComponents.Board;
import chessComponents.Bishop;

/**
 * @author nemo
 *
 */
public class BishopTest {
	
	Board b = new Board(8);
	Bishop p1 = new Bishop(1,1,b);
	Bishop p2 = new Bishop(1,6,b);
	Bishop p3 = new Bishop(6,3,b);
	Bishop p4 = new Bishop(5,5,b);
	Bishop p5 = new Bishop(2,4,b);

	/**
	 * Test method for {@link chessComponents.Bishop#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals(p1.getName(),"B");
	}

	/**
	 * Test method for {@link chessComponents.Bishop#canAttack(int, int)}.
	 */
	@Test
	public void testCanAttackIntInt() {
		assertEquals(p1.canAttack(p2),false);
		assertEquals(p1.canAttack(p4),true);
	}

	/**
	 * Test method for {@link chessComponents.Bishop#Bishop(int, int, chessComponents.Board)}.
	 */
	@Test
	public void testBishopIntIntBoard() {
		assertEquals(p1.getParentBoardDim(),8);
		assertEquals(p2.getX(),1);
		assertEquals(p3.getY(),3);
	}

}
