/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import solvers.*;

/**
 * @author Nemo Li
 *
 */
 
public class BishopSolverTest {
	
	BishopSolver puzzle = new BishopSolver();
	
	/**
	 * Test method for {@link solvers.BishopSolver#solve(java.util.ArrayList)}.
	 */
	@Test
	public void testSolve() {
		assertEquals(puzzle.getSolutionsCount(), 0);
		
		puzzle.solve();
		
		assertEquals(puzzle.getSolutionsCount(), 256);
		
		String firstExpected = "B * * * * * * * \nB * * * * * * B \n"
				+"B * * * * * * B \nB * * * * * * B \nB * * * * * * B \n"
				+"B * * * * * * B \nB * * * * * * B \nB * * * * * * * \n";
		
		assertEquals(puzzle.getSolutions().get(0), firstExpected);
		
		String lastExpected = "* B B B B B B B \n* * * * * * * * \n"
				+"* * * * * * * * \n* * * * * * * * \n* * * * * * * * \n"
				+"* * * * * * * * \n* * * * * * * * \n* B B B B B B B \n";
		
		assertEquals(puzzle.getSolutions().get(255), lastExpected);
	}

}
