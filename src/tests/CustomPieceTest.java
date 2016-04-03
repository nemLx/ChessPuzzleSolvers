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
public class CustomPieceTest {
	
	Board b = new Board(8);
	CustomPiece p1 = new CustomPiece(1,1,b);
	CustomPiece p2 = new CustomPiece(1,2,b);
	CustomPiece p3 = new CustomPiece(1,6,b);
	CustomPiece p4 = new CustomPiece(2,6,b);
	CustomPiece p5 = new CustomPiece(7,6,b);

	/**
	 * Test method for {@link chessComponents.CustomPiece#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals(p1.getName(),"C");
	}

	/**
	 * Test method for {@link chessComponents.CustomPiece#canAttack(int, int)}.
	 */
	@Test
	public void testCanAttackIntInt() {
		assertEquals(p1.canAttack(p2), true);
		assertEquals(p1.canAttack(p3), false);
		assertEquals(p1.canAttack(p4), false);
		assertEquals(p3.canAttack(p4), true);
		assertEquals(p3.canAttack(p5), false);
		assertEquals(p4.canAttack(p5), false);
		assertEquals(p1.canAttack(p1), true);
	}

}
