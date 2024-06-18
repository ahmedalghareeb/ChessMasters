//Name: Abdullah AL-Ghareeb
//Date: 2021-06-10
//Purpose: Chess game with GUI

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.applet.Applet;
import java.io.*;

public class FinalGame_Chess extends Applet implements ActionListener {
    AudioClip soundFile;
    //the input below is being called here
//the “AudioClip” part plays the song when it is run
    Panel mainPanel; // to hold all of the screens
    Panel welcomePanel, instructionsPanel, instructionsPanel2, gamePanel, winPanel, losePanel;// screens
    CardLayout cdLayout = new CardLayout();// layout manager

    JMenuBar menuBar = new JMenuBar();

    // turns
    JLabel turnpic;
    char turn = 'b';
    int last = -1;
    boolean BK = false;
    boolean WK = false;
    JLabel blackScore, whiteScore;

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

    public void generateMenu() { // generate menu
        JMenu fileMenu;
        JMenuItem closeMenuItem = new JMenuItem("Close");
        closeMenuItem.setActionCommand("close");
        closeMenuItem.addActionListener(this);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setActionCommand("save");
        saveMenuItem.addActionListener(this);

        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.setActionCommand("load");
        loadMenuItem.addActionListener(this);

        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        fileMenu.add(closeMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
    }

    public void init() {// set up the main panel
        mainPanel = new Panel();
        mainPanel.setLayout(cdLayout);
        generateMenu();
        generateWelcomeScreen();
        generateInstructionScreen();
        generateInstructionScreen2();
        generateGameScreen();
        generateWinScreen();
        generateLoseScreen();
        resize(630, 700);
        setLayout(new BorderLayout());
        add("North", menuBar);
        add("Center", mainPanel);
        soundFile = getAudioClip(getDocumentBase(), "Another Story.wav");
//this attaches the sound file “letitrock”
        soundFile.loop();
//put the sound on repeat
    }

    public void generateWelcomeScreen() { // generate welcome screen
        welcomePanel = new Panel();
        // Creat label for the title of the welcome screen
        JLabel title = new JLabel("Welcome to Chess Masters");
        // Create label for the author of the welcome screen
        JLabel author = new JLabel("by <Abdullah AL-Ghareeb>");
        // Create label for the title of the welcome screen
        JLabel title2 = new JLabel("Hogwarts battle of the 4 houses");
        //setting the font for the labels
        title2.setFont(new Font("Arial", Font.PLAIN, 30));
        author.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.black);
        // Create label for the introduction image
        JLabel IntroPic = new JLabel(createImageIcon("introPic.jpg"));
        // Create button to move to the instructions screen
        JButton nextBtn = new JButton("Next");
        // setting the font for the button
        nextBtn.setBackground(new Color(222, 100, 99));
        nextBtn.setForeground(Color.white);
        nextBtn.setActionCommand("s2");
        nextBtn.addActionListener(this);

        //ADDING COMPONENTS TO THE WELCOME SCREEN
        welcomePanel.setBackground(new Color(185, 122, 87));
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.add(IntroPic, BorderLayout.CENTER);
        welcomePanel.add(title, BorderLayout.NORTH);
        welcomePanel.add(author, BorderLayout.SOUTH);
        welcomePanel.add(nextBtn, BorderLayout.EAST);
        welcomePanel.add(title2, BorderLayout.WEST);
        mainPanel.add("1", welcomePanel);
    }

    public void generateInstructionScreen() { // generate instruction screen
        instructionsPanel = new Panel();
        // setting the background color of the instructions screen
        instructionsPanel.setBackground(new Color(185, 122, 87));
        // Create label for the title of the instructions screen
        JLabel title = new JLabel("Chess Instructions and Rules");
        JLabel title2 = new JLabel("Green goes first");
        // setting the font for the title
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.black);
        // Create label for the introduction image
        JLabel PiecesMovementPic = new JLabel(createImageIcon("Piece Movement.png"));
        // Create button to move to the instructions2 screen
        JButton nextBtn = new JButton("Next");
        // setting the font for the button
        nextBtn.setBackground(new Color(222, 100, 99));
        nextBtn.setForeground(Color.white);
        nextBtn.setActionCommand("s7");
        nextBtn.addActionListener(this);
        //ADDING COMPONENTS TO THE INSTRUCTIONS SCREEN
        instructionsPanel.add(title);
        instructionsPanel.add(PiecesMovementPic);
        instructionsPanel.add(nextBtn);
        instructionsPanel.add(title2);
        mainPanel.add("2", instructionsPanel);
    }

    public void generateInstructionScreen2() {
        instructionsPanel2 = new Panel();
        // setting the background color of the instructions screen 2
        instructionsPanel2.setBackground(new Color(185, 122, 87));
        // Create label for the title of the instructions screen
        JLabel title = new JLabel("Chess Instructions and Rules 2");
        // setting the font for the title
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.black);
        // Create label for the introduction2 image
        JLabel EndGameConditionsPic = new JLabel(createImageIcon("EndGame Conditions.png"));
        // Create button to move to the game screen
        JButton nextBtn = new JButton("Next");
        // setting the font for the button
        nextBtn.setBackground(new Color(222, 100, 99));
        nextBtn.setForeground(Color.white);
        nextBtn.setActionCommand("s3");
        nextBtn.addActionListener(this);
        //ADDING COMPONENTS TO THE INSTRUCTIONS SCREEN 2
        instructionsPanel2.add(title);
        instructionsPanel2.add(nextBtn);
        instructionsPanel2.add(EndGameConditionsPic);
        mainPanel.add("7", instructionsPanel2);

    }

    public Panel setupGrid() { // generate grid
        // Set up grid
        Panel gridPanel = new Panel(new GridLayout(row, col));
        int btnIndex = 0;
        // Create the buttons for the grid
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
    }// end of setupGrid()

    public void generateGameScreen() { // generate game screen
        gamePanel = new Panel();
        // setting the background color of the game screen
        gamePanel.setBackground(new Color(185, 122, 87));
        JLabel title = new JLabel("Turn");

        // turn pic
        turnpic = new JLabel(createImageIcon("slytherinLogo.png"));
        // create a label for piece left
        JLabel pieceleft = new JLabel("pieces left:");
        //white give up
        JButton whiteconced = new JButton("Orange resign");
        whiteconced.setActionCommand("wc");
        whiteconced.addActionListener(this);
        whiteconced.setBackground(new Color(222, 100, 99));
        whiteconced.setForeground(Color.white);
        //black give up
        JButton blackconced = new JButton("Green resign");
        blackconced.setActionCommand("bc");
        blackconced.addActionListener(this);
        blackconced.setBackground(new Color(222, 100, 99));
        blackconced.setForeground(Color.white);
        // Create button to move to the win screen
        JButton nextBtn = new JButton("Next");
        // setting the font for the button
        nextBtn.setBackground(new Color(222, 100, 99));
        nextBtn.setForeground(Color.white);
        // Create button to move to the instructions screen
        JButton backBtn = new JButton("Back");
        // setting the font for the button
        backBtn.setBackground(new Color(222, 100, 99));
        backBtn.setForeground(Color.white);
        // Creating button to restart the game
        JButton restartBrn = new JButton("Restart Game");
        // setting the font for the button
        restartBrn.setBackground(new Color(222, 100, 99));
        restartBrn.setForeground(Color.white);
        // setting the action command and action listener for the buttons
        nextBtn.setActionCommand("s4");
        nextBtn.addActionListener(this);
        backBtn.setActionCommand("s2");
        backBtn.addActionListener(this);
        restartBrn.setActionCommand("reset");
        restartBrn.addActionListener(this);
        blackScore = new JLabel("green: 0000");
        whiteScore = new JLabel("orange: 0000");

        Panel gridPanel = setupGrid();
        //ADDING COMPONENTS TO THE GAME SCREEN
        gamePanel.add(title);
        gamePanel.add(turnpic);
        gamePanel.add(gridPanel);
        gamePanel.add(blackScore);
        gamePanel.add(whiteScore);
        gamePanel.add(backBtn);
        gamePanel.add(restartBrn);
        gamePanel.add(nextBtn);
        gamePanel.add(blackconced);
        gamePanel.add(whiteconced);
        gamePanel.add(pieceleft);
        mainPanel.add("3", gamePanel);
    }

    public void generateWinScreen() { // screen 4 is set up.
        winPanel = new Panel();
        // setting the background color of the win screen
        winPanel.setBackground(new Color(185, 122, 87));
        // Create label for the title of the win screen
        JLabel title = new JLabel("You Win!");
        // creating another label for the title of the win screen
        JLabel title2 = new JLabel("Congratulations!");
        // creating label for the win picture
        JLabel winPic = new JLabel(createImageIcon("Win pic.png"));
        // creating a button to move to the Game screen
        JButton playAgain = new JButton("play Again?");
        // setting the font for the button
        playAgain.setBackground(new Color(222, 100, 99));
        playAgain.setForeground(Color.white);
        // setting the action command and action listener for the button
        playAgain.setActionCommand("s3");
        playAgain.addActionListener(this);
        // creating button to move to the losing screen
        JButton nextBtn = new JButton("Next");
        // setting the font for the button
        nextBtn.setBackground(new Color(222, 100, 99));
        nextBtn.setForeground(Color.white);
        // setting the action command and action listener for the button
        nextBtn.setActionCommand("s5");
        nextBtn.addActionListener(this);
        //ADDING COMPONENTS TO THE WIN SCREEN
        winPanel.add(title);
        winPanel.add(title2);
        winPanel.add(winPic);
        winPanel.add(playAgain);
        winPanel.add(nextBtn);
        mainPanel.add("4", winPanel);
    }

    public void generateLoseScreen() { // screen 5 is set up.
        losePanel = new Panel();
        // setting the background color of the lose screen
        losePanel.setBackground(new Color(185, 122, 87));
        // Create label for the title of the lose screen
        JLabel title = new JLabel("You Lose.");
        // creating a button to move to the Game screen
        JButton playAgain = new JButton("play Again?");
        // setting the font for the button
        playAgain.setBackground(new Color(222, 100, 99));
        playAgain.setForeground(Color.white);
        // setting the action command and action listener for the button
        playAgain.setActionCommand("s3");
        playAgain.addActionListener(this);
        // creating another button to move to the welcome screen
        JButton nextBtn = new JButton("Back to Introduction?");
        nextBtn.setBackground(new Color(222, 100, 99));
        nextBtn.setForeground(Color.white);
        nextBtn.setActionCommand("s1");
        nextBtn.addActionListener(this);
        JButton end = new JButton("Quit?");
        end.setBackground(new Color(222, 100, 99));
        end.setForeground(Color.white);
        end.setActionCommand("s6");
        end.addActionListener(this);
        // Creating label for the lose picture
        JLabel losePic = new JLabel(createImageIcon("Lose pic.jpg"));
        //ADDING COMPONENTS TO THE LOSE SCREEN
        losePanel.add(title);
        losePanel.add(nextBtn);
        losePanel.add(playAgain);
        losePanel.add(losePic);
        losePanel.add(end);
        mainPanel.add("5", losePanel);
    }

    // end of generateLoseScreen()
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
        turn = 'b'; // Green starts
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
        // king movement
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
        // knight movement
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
        // rook movement
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
        // queen movement
        selectBishop(x, y);
        selectRook(x, y);
    }

    //win check
    public void win(boolean WK, boolean BK) {
        if (BK == false) {
            JOptionPane.showMessageDialog(null, "WHITE WINS", "VICTORY", JOptionPane.ERROR_MESSAGE);
            cdLayout.show(mainPanel, "4");
            resetBoard();
        }
        if (WK == false) {
            JOptionPane.showMessageDialog(null, "BLACK WINS", "VICTORY", JOptionPane.ERROR_MESSAGE);
            cdLayout.show(mainPanel, "4");
            resetBoard();
        }

    }

    public void kingcheck(boolean WK, boolean BK) {
        //checks to see if king is dead
        BK = false;
        WK = false;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (piece[i][j] == 'k' && colour[i][j] == 'b') {
                    //black king check

                    BK = true;

                }

                if (piece[i][j] == 'k' && colour[i][j] == 'w') {
                    //white king check
                    WK = true;

                }

            }
        }
        win(WK, BK);

    }

    public void handleGame(ActionEvent e) {
        // Get the row and column number of the square that was clicked.
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
        // Select the piece at the selected location.
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
        // Move the piece at the selected location to the new location.
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
        // Switch the turn.
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

    public void countPoints() {
        //start at 0 and add every move
        int blackpoints = 0;
        int whitepoints = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (colour[i][j] == 'b')
                    blackpoints++;
                else if (colour[i][j] == 'w')
                    whitepoints++;
            }
        }
        if (blackpoints == 0) {
            BK = false;
            countPoints();

        } else if (whitepoints == 0) {
            WK = false;
            countPoints();

        }
        blackScore.setText("Green: " + blackpoints);
        whiteScore.setText("Orange: " + whitepoints);
    }

    public void actionPerformed(ActionEvent e) { // moves between the screens
        if (e.getActionCommand().equals("s1"))
            cdLayout.show(mainPanel, "1");
        else if (e.getActionCommand().equals("save")) {
            // save
            saveGame();
        } else if (e.getActionCommand().equals("load")) {
            // load
            loadGame();
        } else if (e.getActionCommand().equals("close")) {
            // close
            System.exit(0);
        }
        // if statement to check if the user clicked on the instructions button
        else if (e.getActionCommand().equals("s2"))
            cdLayout.show(mainPanel, "2");
            // if statement to check if the user clicked on the start game button
        else if (e.getActionCommand().equals("s3"))
            cdLayout.show(mainPanel, "3");
            // if statement to check if the user clicked the button to the winning screen
        else if (e.getActionCommand().equals("s4"))
            cdLayout.show(mainPanel, "4");
            // if statement to check if the user clicked on the instructions button
        else if (e.getActionCommand().equals("s5"))
            cdLayout.show(mainPanel, "5");
        else if (e.getActionCommand().equals("wc")) {
            // white giveup
            WK = false;
            BK = true;
            win(WK, BK);
        } else if (e.getActionCommand().equals("bc")) {
            //black giveup
            WK = true;
            BK = false;

            win(WK, BK);
        }
        // if statement to check if the user clicked on the exit button
        else if (e.getActionCommand().equals("s6")) {
            System.exit(0);
        }
        // if statement to check if the user clicked on the instructions2 button
        else if (e.getActionCommand().equals("s7"))
            cdLayout.show(mainPanel, "7");
            // if statement to check if the user clicked on the reset button
        else if (e.getActionCommand().equals("reset")) {
            // reset
            resetBoard();
        } else {
            handleGame(e);
        }
        countPoints();
        //check if king is alive
        kingcheck(WK, BK);
    }

    public void saveObjectToFile(Object obj, String filename) {
        // Save the game to a file
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCharToFile(char obj, String filename) {
        // Save the game to a file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename);
            fos.write(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Object loadObjectFromFile(String filename) {
        // Load the game from a file
        Object obj = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            obj = ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public char loadCharFromFile(String filename) {
        // Load the game from a file
        char obj = 'x';
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            obj = (char) fis.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public void saveGame() {
        saveObjectToFile(colour, "colour.txt");
        saveObjectToFile(piece, "piece.txt");
        saveCharToFile(turn, "turn.txt");
    }

    public void loadGame() {
        colour = (char[][]) loadObjectFromFile("colour.txt");
        piece = (char[][]) loadObjectFromFile("piece.txt");
        turn = loadCharFromFile("turn.txt");
        turnpic.setIcon(createImageIcon((turn == 'w') ? "gryffindorLogo.png" : "slytherinLogo.png"));
        redraw();
    }
}
