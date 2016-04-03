/**
 * 
 */
package tests;

import static org.junit.Assert.*;
import chessComponents.*;

import org.junit.Test;

/**
 * @author Nemo Li
 *
 */
public class QueenTest {
	
	Board b = new Board(8);
	Queen p1 = new Queen(1,1,b);
	Queen p2 = new Queen(1,6,b);
	Queen p3 = new Queen(6,3,b);
	Queen p4 = new Queen(5,5,b);
	Queen p5 = new Queen(2,4,b);
	
	
	/**
	 * Test method for {@link chessComponents.Queen#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals(p1.getName(), "Q");
	}

	/**
	 * Test method for {@link chessComponents.Queen#canAttack(int, int)}.
	 */
	@Test
	public void testCanAttackIntInt() {
		assertEquals(p1.canAttack(p2), true);
		assertEquals(p1.canAttack(p3), false);
		assertEquals(p1.canAttack(p4), true);
		assertEquals(p3.canAttack(p5), false);
	}

}
