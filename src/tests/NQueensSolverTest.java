/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import solvers.NQueensSolver;

/**
 * @author Nemo Li
 * 
 */
public class NQueensSolverTest {

	NQueensSolver puzzle = new NQueensSolver(4);

	/**
	 * Test method for {@link solvers.NQueensSolver#solve(java.util.ArrayList)}.
	 */
	@Test
	public void testSolve() {
		assertEquals(puzzle.getSolutionsCount(), 0);
		
		puzzle.solve();
		
		assertEquals(puzzle.getSolutionsCount(), 2);
		
		String expected = "* * Q * \nQ * * * \n* * * Q \n* Q * * \n";
		
		assertEquals(puzzle.getSolutions().get(0),expected);
	}
}
