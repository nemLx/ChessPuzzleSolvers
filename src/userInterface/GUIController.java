package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

import chessComponents.*;

/**
 * @author Nemo Li
 * 
 */
public class GUIController {

	private GameModel model;
	private GUIView view;
	
	private int frame = 0;				// frame is the global time line for animation
	private Timer animationTimer;		// a timer to trigger animation
	private int animationInterval = 20;	// interval between frames

	
	/**
	 * @param model
	 * @param view
	 */
	public GUIController(GameModel model, GUIView view) {
		this.model = model;
		this.view = view;
	}

	/**
	 * @param puzzle : name of puzzle selected
	 * 
	 * given a string puzzle, call model to select puzzle based on it
	 * 
	 * called when select puzzle pull down list is triggered
	 */
	public void setPuzzle(String puzzle) {
		model.setPuzzle(puzzle);
	}

	/**
	 * @param dim : dimension selected
	 * 
	 * given a board dimension, call model to set dimension
	 * and construct a board of dimension in view
	 * 
	 * called when select size pull down list is triggered
	 */
	public void setSize(int dim) {
		model.setBoardDimension(dim);
		
		if (dim > 0 && view != null)
			view.constructBoard(dim);
	}

	/**
	 * determines the type of puzzle to be solved, handles input errors
	 * and in the end trigger animation
	 * 
	 * called when solver button is clicked on
	 */
	public void startSolver() {
		if (frame != 0){		// frame is not 0 indicates leftover pieces
			view.resetBoard();	// from last puzzle, clean up first
		}
		
		if (model.getCurrentPuzzleName().equals(GameModel.BISHOPS)){
			model.setBoardDimension(GameModel.BISHOP_SIZE);
			view.constructBoard(GameModel.BISHOP_SIZE);		// a special case for bishop, fixed dimension
		}
		
		view.enableInputs(false);
		
		int errCode = model.solvePuzzle();
		
		if (errCode == -1){
			view.invalidDimensionSelection();
			view.enableInputs(true);
			return;
		}else if (errCode == -2){
			view.invalidPuzzleSelection();
			view.enableInputs(true);
			return;
		}
		
		startAnimation();
	}

	/**
	 * set up frame time line and timer
	 */
	private void startAnimation() {
		frame = 0;
		animationTimer = new Timer(animationInterval, new Animation());
		animationTimer.start();
	}
	
	/**
	 * @author Nemo Li
	 * 
	 * actionlistener for animation
	 */
	class Animation implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<Piece> frames = model.pieceFrame;
			ArrayList<Integer> actions = model.frameAction;
			
			if (actions.get(frame) == 1){		// frameAction indicates whether adding (1) 
				drawPiece(frames.get(frame));	// or removing frames (0)
			}else{
				erasePiece(frames.get(frame));
			}
			
			frame++;
			
			if (frame == model.frameAction.size()){	// stop animation timer upon reaching 
				animationTimer.stop();				// end of frames
				view.enableInputs(true);
				view.showSolution(model.getSolutionText());
			}
		}
	}

	/**
	 * @param id : board position
	 * 
	 * retrieves a list of position could be attacked
	 * by piece at position id
	 * 
	 * called when mouse is pressed on a board position
	 */
	public void mousePressed(int id) {
		ArrayList<Integer> attacked = model.getAttackedPositions(id);
		view.showAttackable(attacked);
	}
	
	/**
	 * reset attacked positions on board by any piece
	 * 
	 * called when mouse is released on a board position
	 */
	public void mouseReleased(){
		view.resetAttacked();
	}
	
	/**
	 * @param p : a piece to be drawn
	 * 
	 * calls drawPiece in view with piece id and sprite
	 */
	public void drawPiece(Piece p){
		view.drawPiece(p.getId(), p.getSprite());
	}
	
	/**
	 * @param p : a piece to be erased
	 * 
	 * calls eraesPiece in view with piece id
	 */
	public void erasePiece(Piece p){
		view.erasePiece(p.getId());
	}

	/**
	 * calls view to show help message;
	 */
	public void help() {
		view.showHelpMessage();
	}
}
