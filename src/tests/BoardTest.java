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
public class BoardTest {

	/**
	 * Test method for {@link chessComponents.Board#Board(int)}.
	 */
	@Test
	public void testBoard() {
		Board tester = new Board(5);
		assertEquals(5, tester.getDim());
	}

	/**
	 * Test method for {@link chessComponents.Board#placePiece(chessComponents.Piece)}.
	 */
	@Test
	public void testPlacePiece() {
		Board tester = new Board(10);
		Piece p1 = null;
		Queen p2 = new Queen(0,0,tester);
		Queen p3 = new Queen(100,100,tester);
		
		assertEquals(tester.placePiece(p1), false);
		assertEquals(tester.placePiece(p2), true);
		assertEquals(tester.getPiecesCount(), 1);
		assertEquals(tester.placePiece(p2), false);
		assertEquals(tester.placePiece(p3), false);
	}

	/**
	 * Test method for {@link chessComponents.Board#getPieceAt(int, int)}.
	 */
	@Test
	public void testGetPieceAt() {
		Board tester = new Board(10);
		Queen q = new Queen(2,3,tester);
		
		tester.placePiece(q);
		
		assertEquals(tester.getPieceAt(2, 3), q);
		assertEquals(tester.getPieceAt(100, -10), null);
	}

	/**
	 * Test method for {@link chessComponents.Board#clear()}.
	 */
	@Test
	public void testClear() {
		Board tester = new Board(8);
		Queen p1 = new Queen(1,2,tester);
		Queen p2 = new Queen(3,4,tester);
		Queen p3 = new Queen(5,6,tester);
		Queen p4 = new Queen(2,3,tester);
		Queen p5 = new Queen(4,7,tester);
		
		tester.placePiece(p1);
		tester.placePiece(p2);
		tester.placePiece(p3);
		tester.placePiece(p4);
		tester.placePiece(p5);
		
		tester.clear();
		
		assertEquals(tester.getPiecesCount(), 0);
		assertEquals(tester.getPieceAt(5,6), null);
		assertEquals(tester.getPieceAt(4, 7), null);
	}
	
	/**
	 * Test method for {@link chessComponents.Board#getDim()}.
	 */
	@Test
	public void testGetDim() {
		Board tester = new Board(8);
		assertEquals(tester.getDim(),8);
	}
	
	/**
	 * Test method for {@link chessComponents.Board#getPiecesCount()}.
	 */
	@Test
	public void testGetPiecesCount() {
		Board tester = new Board(8);
		Queen p1 = new Queen(1,2,tester);
		Queen p2 = new Queen(3,4,tester);
		Queen p3 = new Queen(5,6,tester);
		
		assertEquals(tester.getPiecesCount(),0);
		
		tester.placePiece(p1);
		assertEquals(tester.getPiecesCount(),1);
		
		tester.placePiece(p2);
		assertEquals(tester.getPiecesCount(),2);
		
		tester.placePiece(p3);
		assertEquals(tester.getPiecesCount(),3);
	}
	
	/**
	 * Test method for {@link chessComponents.Board#toString()}.
	 */
	@Test
	public void testToString() {
		Board tester = new Board(5);
		Queen p1 = new Queen(0,1,tester);
		Queen p2 = new Queen(1,2,tester);
		Queen p3 = new Queen(2,3,tester);
		Queen p4 = new Queen(3,0,tester);
		Queen p5 = new Queen(4,0,tester);
		
		tester.placePiece(p1);
		tester.placePiece(p2);
		tester.placePiece(p3);
		tester.placePiece(p4);
		tester.placePiece(p5);
		
		tester.print();
		
		String expected = "* Q * * * \n* * Q * * \n* * * Q * \nQ * * * * \nQ * * * * \n";
		
		assertEquals(tester.toString(),expected);
	}
}
