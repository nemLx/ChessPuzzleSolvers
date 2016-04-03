package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Nemo Li
 * 
 */
public class GUIView {

	private final String GAME_TITLE = "Chess Board Puzzles";
	private final String WELCOME = "sprites/welcomePage.png";
	private final String HELP = "Select puzzle from first pulldown list.\nSelect board size from second pulldown list.\n";
	private final String INVALID_PUZZLE = "Invalid Puzzle Selection!";
	private final String INVALID_SIZE = "Invalid Board Size Selection!";
	private final String[] puzzleStrings = { "Select Puzzle", "N-Queens", "14-Bishops", "Domination", "Ranged Rooks" };
	private final String[] sizeStrings = { "Select Board Size", "1", "2", "3", "4", "5","6", "7", "8", "9", "10" };
	private final Dimension mainFrameDim= new Dimension (1015, 833);		// these "magic" numbers are determined by trial and error
	private final Dimension boardPanelDim = new Dimension (800,800);		// and I cannot think of better way of figuring out their relation
	private final Dimension selectionPanelDim = new Dimension (200, 200);
	private final GridLayout selectionPanelLayout = new GridLayout(4,1,5,5);
	
	private final int boardCellsGap = 5;			// the gap between board cells
	private final double pieceSpriteScale = 0.7;	// this scale is by how much a piece sprite
													// is shrunk compare to the board box
	
	private GUIController controller;

	private JFrame mainFrame;
	private JPanel board;
	private JPanel selections;
	
	/**
	 * constructor, called when the game is started should display the welcome
	 * page with a picture and four buttons, one for each game
	 */
	public GUIView() {
		
		controller = new GUIController(new GameModel(), this);

		setStyle();
		constructMainFrame();
		board = (JPanel) mainFrame.getContentPane().getComponent(0);
		selections = (JPanel) mainFrame.getContentPane().getComponent(1);

		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	/**
	 * sets style of display
	 */
	private void setStyle() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * sets up the main frame by adding
	 * 		- title
	 * 		- close behavior
	 * 		- size
	 * 		- main frame panel
	 */
	private void constructMainFrame() {
		
		JPanel mainFramePanel = new JPanel();
		JPanel boardPanel = new JPanel();
		JPanel selectionPanel = new JPanel();

		constructMainFramePanel(mainFramePanel, boardPanel, selectionPanel);
		
		mainFrame = new JFrame(GAME_TITLE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setPreferredSize(mainFrameDim);
		mainFrame.setContentPane(mainFramePanel);
	}

	/**
	 * @param mainFramePanel : JPanel containing board and selection panels
	 * @param boardPanel : JPanel containing chess board
	 * @param selectionPanel : JPanel containing selecitons and buttons
	 * 
	 * constructs mainFramePanel
	 */
	private void constructMainFramePanel(
										JPanel mainFramePanel,
										JPanel boardPanel, 
										JPanel selectionPanel){

		constructBoardPanel(boardPanel);
		constructSelectionPanel(selectionPanel);
		
		mainFramePanel.setBackground(Color.black);
		mainFramePanel.setLayout(new FlowLayout());
		mainFramePanel.setAlignmentX(FlowLayout.CENTER);
		mainFramePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		mainFramePanel.add(boardPanel);
		mainFramePanel.add(selectionPanel);
	}

	/**
	 * @param board : JPanel containing chess board
	 */
	private void constructBoardPanel(JPanel board){
		
		board.setPreferredSize(boardPanelDim);
		board.setBackground(Color.black);
		setWelcomeLabel(board);	// board area defaults to a welcome graphics
	}
	
	/**
	 * @param board
	 */
	private void setWelcomeLabel(JPanel board) {
		ImageIcon abstractChessIcon = new ImageIcon(WELCOME);
		JLabel welcomeLabel = new JLabel(null, abstractChessIcon, JLabel.CENTER);
		welcomeLabel.setPreferredSize(new Dimension(800, 800));
		board.add(welcomeLabel, BorderLayout.NORTH);
	}
	
	/**
	 * @param selection : JPanel containing selections and buttons
	 * 
	 * sets up selection panel by adding combo boxes and buttons
	 */
	private void constructSelectionPanel(JPanel selection) {
		
		selection.setPreferredSize(selectionPanelDim);
		selection.setBackground(Color.black);
		selection.setLayout(selectionPanelLayout);

		addComboBoxes(selection);
		addButtons(selection);
	}
	
	/**
	 * @param selection : panel to add combo boxes to
	 * 
	 * adds two combo boxes, for puzzle and size selection
	 * also adds mouse listener to both boxes
	 */
	private void addComboBoxes(JPanel selection) {
		
		final JComboBox puzzleSelection = new JComboBox(puzzleStrings);
		final JComboBox sizeSelection = new JComboBox(sizeStrings);
		
		puzzleSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setPuzzle((String) puzzleSelection.getSelectedItem());
			}
		});

		sizeSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setSize((int) sizeSelection.getSelectedIndex());
			}
		});
		
		selection.add(puzzleSelection);
		selection.add(sizeSelection);
	}

	/**
	 * @param selection : panel to add buttons to
	 * 
	 * adds solve and help button to selection panel
	 * also adds action listeners to buttons
	 */
	private void addButtons(JPanel selection) {
		
		final JButton solveButton = new JButton("Solve");
		final JButton helpButton = new JButton("Help");

		solveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.startSolver();
			}
		});

		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.help();
			}
		});
		
		selection.add(solveButton);
		selection.add(helpButton);
	}
		
	/**
	 * @param dim : dimension for the board
	 * 
	 * constructs the gui for chess board, by creating
	 * sufficient number of JPanels
	 */
	public void constructBoard(int dim) {
		
		JPanel boardPanel = (JPanel) mainFrame.getContentPane().getComponent(0);

		boardPanel.removeAll();
		boardPanel.revalidate();
		
		boardPanel.setBackground(Color.BLACK);
		boardPanel.setLayout(new GridLayout(dim, dim, boardCellsGap, boardCellsGap));

		addCellsToBoard(dim, boardPanel);

		mainFrame.repaint();
	}

	/**
	 * @param dim : dimension of the board
	 * @param boardPanel : panel to add cells to
	 * 
	 * adds sufficient number of cells to board
	 * also adds mouse listeners to them
	 */
	private void addCellsToBoard(int dim, JPanel boardPanel) {
		
		final JPanel cells[] = new JPanel[dim * dim];

		for (int i = 0; i < dim * dim; i++) {
			final int id = i;
			cells[i] = new JPanel();
			cells[i].setBackground(Color.WHITE);
			addMouseListenerToCell(cells[i], id);
			boardPanel.add(cells[i]);
		}
	}

	/**
	 * @param cell
	 * @param i
	 * @param id
	 * 
	 * adds mouse pressed and released listener to panel cell
	 */
	private void addMouseListenerToCell(final JPanel cell, final int id) {
		cell.addMouseListener(
			new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {
					controller.mousePressed(id);
				}
				public void mouseReleased(MouseEvent arg0) {
					controller.mouseReleased();
				}
			}
		);
	}

	/**
	 * @param attacked : list of positions attacked by some piece
	 * 
	 * sets background color of all attacked positions to red
	 */
	public void showAttackable(ArrayList<Integer> attacked) {
		
		JPanel curr;
		
		for (int i = 0; i < attacked.size(); i++) {
			curr = (JPanel) board.getComponent(attacked.get(i));
			curr.setBackground(Color.RED);
		}
		
		mainFrame.repaint();
	}

	/**
	 * resets background colors of all board cells to white
	 */
	public void resetAttacked() {
		
		JPanel curr;
		
		for (int i = 0; i < board.getComponentCount(); i++) {
			curr = (JPanel) board.getComponent(i);
			curr.setBackground(Color.WHITE);
		}
		
		board.repaint();
	}
	
	/**
	 * @param id : id/position of piece to draw
	 * @param sprite : location of the sprite for the piece
	 * 
	 * adds a JLabel with the scaled sprite at the appropriate cell
	 */
	public void drawPiece(int id, String sprite) {

		JPanel curr = (JPanel) board.getComponent(id);
		
		int pieceHeight = (int) (pieceSpriteScale*curr.getHeight());	// shrinks the size of the sprite
		int pieceWidth = (int) (pieceSpriteScale*curr.getWidth());		// to a fraction of the cell
		
		curr.add(constructPieceLabel(sprite, pieceHeight, pieceWidth), BorderLayout.SOUTH);
		curr.revalidate();
		curr.repaint();
	}
	
	/**
	 * @param id : id/position of piece to remove
	 * 
	 * removes piece label off a cell
	 */
	public void erasePiece(int id) {
		
		JPanel curr = (JPanel) board.getComponent(id);
		
		curr.removeAll();
		curr.revalidate();
		curr.repaint();
	}

	/**
	 * @param sprite : location of the sprite for a piece
	 * @param h	: height of the Label for the piece
	 * @param w : width ...
	 * @return : a JLabel with the sprite on it
	 * 
	 * scales the sprite, adds it to a label
	 */
	private JLabel constructPieceLabel(String sprite, int h, int w) {
		
		Image g = null;
		
		try {
			g = ImageIO.read(new File(sprite));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g = g.getScaledInstance(w, h, 0);
		
		Dimension pieceDim = new Dimension(h,w);
		ImageIcon pieceIcon = new ImageIcon(g);
		JLabel pieceLabel = new JLabel(pieceIcon, JLabel.CENTER);
		
		pieceLabel.setPreferredSize(pieceDim);
		
		return pieceLabel;
	}

	/**
	 * removes every piece from the board
	 */
	public void resetBoard() {
		
		JPanel curr;
		
		for (int i = 0; i < board.getComponentCount(); i++) {
			curr = (JPanel) board.getComponent(i);
			curr.removeAll();
			curr.revalidate();
		}
		
		board.repaint();
	}

	/**
	 * show help message box
	 */
	public void showHelpMessage() {
		JOptionPane.showMessageDialog(mainFrame, HELP);
	}
	
	/**
	 * show invalid puzzle message box
	 */
	public void invalidPuzzleSelection() {
		JOptionPane.showMessageDialog(mainFrame, INVALID_PUZZLE);
	}

	/**
	 * show invalid size message box
	 */
	public void invalidDimensionSelection() {
		JOptionPane.showMessageDialog(mainFrame, INVALID_SIZE);
	}

	/**
	 * @param set : true to enable, false to disable
	 * 
	 * enable or diable input options
	 */
	public void enableInputs(boolean set) {
		JComboBox puzzleBox = (JComboBox) selections.getComponent(0);
		JComboBox inputBox = (JComboBox) selections.getComponent(1);
		JButton solve = (JButton) selections.getComponent(2);
		
		solve.setEnabled(set);
		puzzleBox.setEnabled(set);
		inputBox.setEnabled(set);
	}

	/**
	 * @param solutionText : a string explaining solutions
	 * 
	 * display a message box on the number of solutions
	 */
	public void showSolution(String solutionText) {
		JOptionPane.showMessageDialog(mainFrame, solutionText);
	}
}
