/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import solvers.CustomSolver;

/**
 * @author Nemo Li
 *
 */
public class CustomSolverTest {

	CustomSolver puzzle = new CustomSolver(5);
	
	/**
	 * Test method for {@link solvers.CustomSolver#solve()}.
	 */
	@Test
	public void testSolve() {
		puzzle.solve();
		
		String expected = "C * * C * \n* C * * C \n* * C * * \n"
				+"C * * C * \n* C * * C \n";
		
		assertEquals(puzzle.getSolutions().get(0), expected);
		assertEquals(puzzle.getSolutionsCount(), 2);
	}

	/**
	 * Test method for {@link solvers.CustomSolver#getMax()}.
	 */
	@Test
	public void testGetMax() {
		puzzle.solve();
		
		assertEquals(puzzle.getMax(), 9);
	}

}
