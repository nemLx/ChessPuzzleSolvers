/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import solvers.DominationSolver;

/**
 * @author Nemo Li
 *
 */
public class DominationSolverTest {
	
	DominationSolver puzzle1 = new DominationSolver(4);
	DominationSolver puzzle2 = new DominationSolver(6);
	
	/**
	 * Test method for {@link solvers.DominationSolver#solve()}.
	 */
	@Test
	public void testSolve() {
		puzzle1.solve();
		puzzle2.solve();
		
		String expected1 = "Q * * * \n* * * * \n"
				+"* * Q * \n* * * * \n";
		
		String expected2 = "Q * * * * * \n* * * * * * \n* * * * Q * \n"
				+"* * * * * * \n* * Q * * * \n* * * * * * \n";
		
		assertEquals(puzzle1.getSolutions().get(0), expected1);
		assertEquals(puzzle2.getSolutions().get(0), expected2);
	}

	/**
	 * Test method for {@link solvers.DominationSolver#getMinQueensNeeded()}.
	 */
	@Test
	public void testGetMinQueensNeeded() {
		puzzle1.solve();
		puzzle2.solve();
		
		assertEquals(puzzle1.getMinQueensNeeded(), 2);
		assertEquals(puzzle2.getMinQueensNeeded(), 3);
	}

}
