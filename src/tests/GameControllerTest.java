/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import userInterface.*;

/**
 * @author Nemo Li
 *
 */
public class GameControllerTest {
	
	GameModel model = new GameModel();
	GUIView view = null;
	GUIController controller = new GUIController(model, view);
	
	/**
	 * Test method for {@link userInterface.GUIController#setPuzzle(java.lang.String)}.
	 */
	@Test
	public void testGameController() {
		assertEquals("Select Puzzle", model.getCurrentPuzzleName());
		assertEquals(-1, model.getCurrentPuzzleDim());
	}

	/**
	 * Test method for {@link userInterface.GUIController#setPuzzle(java.lang.String)}.
	 */
	@Test
	public void testSetPuzzle() {
		controller.setPuzzle("N-Queens");
		assertEquals("N-Queens", model.getCurrentPuzzleName());
	}

	/**
	 * Test method for {@link userInterface.GUIController#setSize(int)}.
	 */
	@Test
	public void testSetSize() {
		controller.setSize(8);
		assertEquals(8, model.getCurrentPuzzleDim());
	}
}
