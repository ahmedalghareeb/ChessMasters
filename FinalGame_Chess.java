//Name: Abdullah AL-Ghareeb
//Date: 2021-06-10
//Purpose: Chess game with GUI

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

public class FinalGame_Chess extends Applet implements ActionListener {
	Panel mainPanel; // to hold all of the screens
	Panel welcomePanel, instructionsPanel, gamePanel, winPanel, losePanel;// screens
	CardLayout cdLayout = new CardLayout();// layout manager

	// turns
	JLabel turnpic;
	char turn = 'b';
	int last = -1;

	// grid
	int row = 8;
	int col = 8;
//
	JButton pieceBtnList[] = new JButton[row * col];

// piece[][] represents the initial position of the chess pieces on the board
// 'r' = rook, 'n' = knight, 'b' = bishop, 'k' = king, 'q' = queen, 'p' = pawn, 'x' = empty square
	char piece[][] = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}, // Black pieces in the first row
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'}, // Black pawns in the second row
			{'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, // Empty squares in the middle rows
			{'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
			{'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
			{'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'}, // White pawns in the seventh row
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}  // White pieces in the eighth row
	};

	// select[][] keeps track of which squares are currently selected for a potential move
// 'u' = unselected, 's' = selected
	char select[][] = {
			{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
			{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
			{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
			{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
			{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
			{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
			{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
			{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}
	};

	// colour[][] represents the color of each piece on the board
// 'w' = white, 'b' = black, 'x' = empty square
	char colour[][] = {
			{'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
			{'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
			{'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, // Empty squares in the middle rows
			{'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
			{'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
			{'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
			{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, // Black pieces in the last two rows
			{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}// White pieces in the first two rows
	};

	// bg[][] represents the background color of each square on the chessboard
// 'w' = white, 'b' = black
	char bg[][] = {
			{'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'}, // Alternating black and white squares
			{'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'},
			{'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
			{'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'},
			{'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
			{'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'},
			{'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
			{'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'}
	};
	// end of grid
	public void init() {// set up the main panel
		mainPanel = new Panel();
		mainPanel.setLayout(cdLayout);
		generateWelcomeScreen();
		generateInstructionScreen();
		generateGameScreen();
		generateWinScreen();
		generateLoseScreen();
		resize(630, 700);
		setLayout(new BorderLayout());
		add("Center", mainPanel);
	}

	public void generateWelcomeScreen() { // generate welcome screen
		welcomePanel = new Panel();
		welcomePanel.setBackground(new Color(255, 255, 255));
		JLabel title = new JLabel("Welcome");
		JButton nextBtn = new JButton("Next");
		nextBtn.setActionCommand("s2");
		nextBtn.addActionListener(this);
		welcomePanel.add(title);
		welcomePanel.add(nextBtn);
		mainPanel.add("1", welcomePanel);
	}

	public void generateInstructionScreen() { // generate instruction screen
		instructionsPanel = new Panel();
		instructionsPanel.setBackground(new Color(185, 122, 87));
		JLabel title = new JLabel("Chess Instructions and Rules");
		title.setFont (new Font ("Arial", Font.BOLD, 40));
		title.setForeground (Color.black);
		JLabel ChessBoard = new JLabel ("1. Chessboard: An 8x8 grid with 64 squares alternating in color (light and dark).");
		ChessBoard.setFont(new Font("Arial", Font.PLAIN, 15));
		ChessBoard.setForeground(Color.white);
		JLabel ChessPieces = new JLabel("2. Pieces: Each player starts with 16 pieces - 1 king, 1 queen, 2 rooks, 2 knights, 2 bishops, and 8 pawns.");
		ChessPieces.setFont(new Font("Arial", Font.PLAIN, 15));
		ChessPieces.setForeground(Color.white);
		JLabel ChessMoves = new JLabel("3. Moves: Each player can move any of their pieces to an empty square on the chessboard.");
		add(ChessPieces);
		add (title);
		add(ChessBoard);
		JButton nextBtn = new JButton("Next");
		nextBtn.setActionCommand("s3");
		nextBtn.addActionListener(this);
		instructionsPanel.add(title);
		instructionsPanel.add(ChessBoard);
		instructionsPanel.add(nextBtn);
		mainPanel.add("2", instructionsPanel);
	}

	public Panel setupGrid() { // generate grid
		// Set up grid
		Panel gridPanel = new Panel(new GridLayout(row, col));
		int btnIndex = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				pieceBtnList[btnIndex] = new JButton(
						createImageIcon(piece[i][j] + "" + colour[i][j] + "" + bg[i][j] + "" + select[i][j] + ".jpg"));
				pieceBtnList[btnIndex].setPreferredSize(new Dimension(75, 75));
				pieceBtnList[btnIndex].addActionListener(this);
				pieceBtnList[btnIndex].setActionCommand("" + btnIndex);
				gridPanel.add(pieceBtnList[btnIndex]);
				btnIndex++;
			}
		}

		return gridPanel;
	}//

	public void generateGameScreen() { // generate game screen
		gamePanel = new Panel();
		gamePanel.setBackground(new Color(255, 255, 255));
		JLabel title = new JLabel("Turn");

		// turn pic
		turnpic = new JLabel(createImageIcon("slytherinLogo.png"));

		JButton nextBtn = new JButton("Next");
		JButton backBtn = new JButton("Back");
		JButton restartBrn = new JButton("Restart Game");

		nextBtn.setActionCommand("s4");
		nextBtn.addActionListener(this);
		backBtn.setActionCommand("s2");
		backBtn.addActionListener(this);
		restartBrn.setActionCommand("reset");
		restartBrn.addActionListener(this);

		Panel gridPanel = setupGrid();

		gamePanel.add(title);
		gamePanel.add(turnpic);
		gamePanel.add(gridPanel);
		gamePanel.add(backBtn);
		gamePanel.add(restartBrn);
		gamePanel.add(nextBtn);
		mainPanel.add("3", gamePanel);
	}

	public void generateWinScreen() { // screen 4 is set up.
		winPanel = new Panel();
		winPanel.setBackground(new Color(255, 255, 255));
		JLabel title = new JLabel("You Win!");
		JButton nextBtn = new JButton("Next");
		nextBtn.setActionCommand("s5");
		nextBtn.addActionListener(this);
		winPanel.add(title);
		winPanel.add(nextBtn);
		mainPanel.add("4", winPanel);
	}

	public void generateLoseScreen() { // screen 5 is set up.
		losePanel = new Panel();
		losePanel.setBackground(new Color(255, 255, 255));
		JLabel title = new JLabel("You Lose.");
		JButton nextBtn = new JButton("Back to Introduction?");
		nextBtn.setActionCommand("s1");
		nextBtn.addActionListener(this);
		JButton end = new JButton("Quit?");
		end.setActionCommand("s6");
		end.addActionListener(this);
		losePanel.add(title);
		losePanel.add(nextBtn);
		losePanel.add(end);
		mainPanel.add("5", losePanel);
	}

	protected static ImageIcon createImageIcon(String path) { // change the red to your class name
		java.net.URL imgURL = FinalGame_Chess.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public void redraw() {
		int btnIndex = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				pieceBtnList[btnIndex].setIcon(
						createImageIcon(piece[i][j] + "" + colour[i][j] + "" + bg[i][j] + "" + select[i][j] + ".jpg"));
				btnIndex++;
			}
		}
	}

	public void resetBoard() {
		// Reset select and colour arrays
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				select[i][j] = 'u'; // unselected
				if (i < 2) {
					colour[i][j] = 'w'; // white pieces rows
				} else if (i > 5) {
					colour[i][j] = 'b'; // black pieces rows
				} else {
					colour[i][j] = 'x'; // empty spaces
				}
			}
		}

		// Reset the board pieces
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (i == 1 || i == 6) {
					piece[i][j] = 'p'; // pawns
				} else if (i == 0 || i == 7) {
					char pieceType = getPieceTypeForInitialRow(j);
					piece[i][j] = pieceType;
				} else {
					piece[i][j] = 'x'; // empty space
				}
			}
		}

		// Redraw the board and set the turn
		redraw();
		turn = 'b'; // white starts
		turnpic.setIcon(createImageIcon("slytherinLogo.png"));
	}

	public char getPieceTypeForInitialRow(int col) {
		switch (col) {
			case 0:
			case 7:
				return 'r'; // rooks
			case 1:
			case 6:
				return 'n'; // knights
			case 2:
			case 5:
				return 'b'; // bishops
			case 3:
				return 'k'; // king
			case 4:
				return 'q'; // queen
			default:
				return 'x'; // should not happen
		}
	}

	// Pieces Movement selection
	public void selectPawn(int x, int y) {
		// System.out.println(pieceBtnList[x*y].getIcon() + " " + piece [x] [y] + "" +
		// colour [x] [y] + "" + bg [x] [y] + "" + select [x] [y] + ".jpg");
		// pieceBtnList[x*y].setIcon(createImageIcon (piece [x] [y] + "" + colour [x]
		// [y] + "" + bg [x] [y] + "" + select [x] [y] + ".jpg"));
		// Pawn Movement
		if (colour[x][y] == 'b' && x == 6) {
			if (colour[x - 1][y] == 'x') {
				select[x - 1][y] = 's';
				if (colour[x - 2][y] == 'x') {
					select[x - 2][y] = 's';
				}
			}
		} else if (colour[x][y] == 'w' && x == 1) {
			if (colour[x + 1][y] == 'x') {
				select[x + 1][y] = 's';
				if (colour[x + 2][y] == 'x') {
					select[x + 2][y] = 's';
				}
			}
		} else if (colour[x][y] == 'b') {
			if (x - 1 >= 0 && y - 1 >= 0 && colour[x - 1][y - 1] == 'w') {
				// black attacking white at the right
				select[x - 1][y - 1] = 's';
				if (colour[x - 1][y] == 'x') {
					select[x - 1][y] = 's';
				}
			}
			if (x - 1 >= 0 && y + 1 < col && colour[x - 1][y + 1] == 'w') {
				// black attacking white at the left
				select[x - 1][y + 1] = 's';
				if (colour[x - 1][y] == 'x') {
					select[x - 1][y] = 's';
				}
			} else if (colour[x - 1][y] == 'x') {
				select[x - 1][y] = 's';
			}
		} else if (colour[x][y] == 'w') {
			if (x + 1 < row && y - 1 >= 0 && colour[x + 1][y - 1] == 'b') {
				// white attacking black at the right
				select[x + 1][y - 1] = 's';
				if (colour[x + 1][y] == 'x') {
					select[x + 1][y] = 's';
				}
			}
			if (x + 1 < row && y + 1 < col && colour[x + 1][y + 1] == 'b') {
				// white attacking black at the left
				select[x + 1][y + 1] = 's';
				if (colour[x + 1][y] == 'x') {
					select[x + 1][y] = 's';
				}
			} else if (colour[x + 1][y] == 'x') {
				select[x + 1][y] = 's';
			}
		}
	}

	public void selectKing(int x, int y) {
		if (x - 1 >= 0 && y + 1 < col && colour[x - 1][y + 1] != turn) {
			select[x - 1][y + 1] = 's';
		}
		if (x + 1 < row && y + 1 < col && colour[x + 1][y + 1] != turn) {
			select[x + 1][y + 1] = 's';
		}
		if (y - 1 >= 0 && colour[x][y - 1] != turn) {
			select[x][y - 1] = 's';
		}
		if (x - 1 >= 0 && colour[x - 1][y] != turn) {
			select[x - 1][y] = 's';
		}
		if (x - 1 >= 0 && y - 1 >= 0 && colour[x - 1][y - 1] != turn) {
			select[x - 1][y - 1] = 's';
		}
		if (x + 1 < row && y - 1 >= 0 && colour[x + 1][y - 1] != turn) {
			select[x + 1][y - 1] = 's';
		}
		if (y + 1 < col && colour[x][y + 1] != turn) {
			select[x][y + 1] = 's';
		}
		if (x + 1 < row && colour[x + 1][y] != turn) {
			select[x + 1][y] = 's';
		}
	}

	public void selectKnight(int x, int y) {
		if (x - 2 >= 0 && y + 1 < col && colour[x - 2][y + 1] != turn) {
			select[x - 2][y + 1] = 's';
		}
		if (x - 2 >= 0 && y - 1 >= 0 && colour[x - 2][y - 1] != turn) {
			select[x - 2][y - 1] = 's';
		}
		if (x + 2 < row && y + 1 < col && colour[x + 2][y + 1] != turn) {
			select[x + 2][y + 1] = 's';
		}
		if (x + 2 < row && y - 1 >= 0 && colour[x + 2][y - 1] != turn) {
			select[x + 2][y - 1] = 's';
		}
		if (x - 1 >= 0 && y + 2 < col && colour[x - 1][y + 2] != turn) {
			select[x - 1][y + 2] = 's';
		}
		if (x - 1 >= 0 && y - 2 >= 0 && colour[x - 1][y - 2] != turn) {
			select[x - 1][y - 2] = 's';
		}
		if (x + 1 < row && y + 2 < col && colour[x + 1][y + 2] != turn) {
			select[x + 1][y + 2] = 's';
		}
		if (x + 1 < row && y - 2 >= 0 && colour[x + 1][y - 2] != turn) {
			select[x + 1][y - 2] = 's';
		}
	}

	public void selectRook(int x, int y) {
		// up
		for (int i = x - 1; i >= 0; i--) {
			if (colour[i][y] == turn) {
				break;
			}
			select[i][y] = 's';
			if (colour[i][y] != 'x') {
				break;
			}
		}
		// down
		for (int i = x + 1; i < row; i++) {
			if (colour[i][y] == turn) {
				break;
			}
			select[i][y] = 's';
			if (colour[i][y] != 'x') {
				break;
			}
		}
		// left
		for (int i = y - 1; i >= 0; i--) {
			if (colour[x][i] == turn) {
				break;
			}
			select[x][i] = 's';
			if (colour[x][i] != 'x') {
				break;
			}
		}
		// right
		for (int i = y + 1; i < col; i++) {
			if (colour[x][i] == turn) {
				break;
			}
			select[x][i] = 's';
			if (colour[x][i] != 'x') {
				break;
			}
		}
	}

	public void selectBishop(int x, int y) {
		// up right
		for (int i = x - 1, j = y + 1; i >= 0 && j < col; i--, j++) {
			if (colour[i][j] == turn) {
				break;
			}
			select[i][j] = 's';
			if (colour[i][j] != 'x') {
				break;
			}
		}
		// up left
		for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
			if (colour[i][j] == turn) {
				break;
			}
			select[i][j] = 's';
			if (colour[i][j] != 'x') {
				break;
			}
		}
		// down right
		for (int i = x + 1, j = y + 1; i < row && j < col; i++, j++) {
			if (colour[i][j] == turn) {
				break;
			}
			select[i][j] = 's';
			if (colour[i][j] != 'x') {
				break;
			}
		}
		// down left
		for (int i = x + 1, j = y - 1; i < row && j >= 0; i++, j--) {
			if (colour[i][j] == turn) {
				break;
			}
			select[i][j] = 's';
			if (colour[i][j] != 'x') {
				break;
			}
		}
	}

	public void selectQueen(int x, int y) {
		selectBishop(x, y);
		selectRook(x, y);
	}

	public void handleGame(ActionEvent e) {
		int n = Integer.parseInt(e.getActionCommand());
		int x = n / col;
		int y = n % col;

		showStatus("(" + x + ", " + y + ")");

		if (last == -1) { // Selecting a piece
			if (turn == colour[x][y]) {
				selectPiece(x, y);
				last = n;
			} else {
				showStatus("Not your turn");
			}
		} else { // Moving a piece
			movePiece(x, y);
		}

		redraw();
	}

	public void selectPiece(int x, int y) {
		switch (piece[x][y]) {
			case 'p':
				selectPawn(x, y);
				break;
			case 'k':
				selectKing(x, y);
				break;
			case 'n':
				selectKnight(x, y);
				break;
			case 'r':
				selectRook(x, y);
				break;
			case 'b':
				selectBishop(x, y);
				break;
			case 'q':
				selectQueen(x, y);
				break;
			default:
				showStatus("Invalid selection");
		}
	}

	private void movePiece(int x, int y) {
		int lastx = last / col;
		int lasty = last % col;

		if (select[x][y] == 's') {
			piece[x][y] = piece[lastx][lasty];
			piece[lastx][lasty] = 'x';
			colour[x][y] = colour[lastx][lasty];
			colour[lastx][lasty] = 'x';
			switchTurn();
		}

		resetSelections();
		last = -1;
	}

	private void switchTurn() {
		turn = (turn == 'w') ? 'b' : 'w';
		turnpic.setIcon(createImageIcon((turn == 'w') ? "gryffindorLogo.png" : "slytherinLogo.png"));
	}

	private void resetSelections() {
		// reset
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				select[i][j] = 'u';
			}
		}
	}

	public void actionPerformed(ActionEvent e) { // moves between the screens
		if (e.getActionCommand().equals("s1"))
			cdLayout.show(mainPanel, "1");
		// if statement to check if the user clicked on the instructions button
		else if (e.getActionCommand().equals("s2"))
			cdLayout.show(mainPanel, "2");
		// if statement to check if the user clicked on the start game button
		else if (e.getActionCommand().equals("s3"))
			cdLayout.show(mainPanel, "3");
		// if statement to check if the user clicked the button to the winning screen
		else if (e.getActionCommand().equals("s4"))
			cdLayout.show(mainPanel, "4");
		// if statement to check if the user clicked on the instuctions button
		else if (e.getActionCommand().equals("s5"))
			cdLayout.show(mainPanel, "5");
		// if statement to check if the user clicked on the exit button
		else if (e.getActionCommand().equals("s6"))
			System.exit(0);
		// if statement to check if the user clicked on the reset button
		else if (e.getActionCommand().equals("reset")) {
			// reset
			resetBoard();
		} else {
			handleGame(e);
		}
	}
}
