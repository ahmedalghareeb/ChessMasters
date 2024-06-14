//Name:
//Date:
//Purpose: Chess Starter Code

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

public class FinalGame_Chess extends Applet implements ActionListener
{
    Panel p_card;  //to hold all of the screens
    Panel card1, card2, card3, card4, card5; //the two screens
    CardLayout cdLayout = new CardLayout ();

    //turns
    JLabel turnpic;
    char turn='w';
    int last = -1;

    //grid
    int row = 8;
    int col = 8;
    JButton a[] = new JButton [row * col];
    char piece[] [] = {
    {'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'},
    {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
    {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
    {'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'}};

    char select[] [] = {
    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}};

    char colour[] [] = {
    {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
    {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
    {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
    {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}};

    char bg[] [] = {
    {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'},
    {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'},
    {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'},
    {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'}};


    public void init ()
    {
        p_card = new Panel ();
        p_card.setLayout (cdLayout);
        screen1 ();
        screen2 ();
        screen3 ();
        screen4 ();
        screen5 ();
        resize (630, 700);
        setLayout (new BorderLayout ());
        add ("Center", p_card);
    }

    public void screen1 ()
    { //screen 1 is set up.
        card1 = new Panel ();
        card1.setBackground (new Color (255, 255, 255));
        JLabel title = new JLabel ("Welcome");
        JButton next = new JButton ("Next");
        next.setActionCommand ("s2");
        next.addActionListener (this);
        card1.add (title);
        card1.add (next);
        p_card.add ("1", card1);
    }

    public void screen2 ()
    { //screen 2 is set up.
        card2 = new Panel ();
        card2.setBackground (new Color (255, 255, 255));
        JLabel title = new JLabel ("Instructions");
        JButton next = new JButton ("Next");
        next.setActionCommand ("s3");
        next.addActionListener (this);
        card2.add (title);
        card2.add (next);
        p_card.add ("2", card2);
    }


    public void screen3 ()
    { //screen 3 is set up.
        card3 = new Panel ();
        card3.setBackground (new Color (255, 255, 255));
        JLabel title = new JLabel ("Game");

        // turn
        turnpic= new JLabel (createImageIcon("slytherinLogo.png"));

        JButton next = new JButton ("Next");
		JButton back = new JButton ("Back");
		JButton restart = new JButton ("Restart");

        next.setActionCommand ("s4");
        next.addActionListener (this);
		back.setActionCommand ("s2");
		back.addActionListener (this);
		restart.setActionCommand ("reset");
		restart.addActionListener (this);

        //Set up grid
        Panel p = new Panel (new GridLayout (row, col));
        int move = 0;
        for (int i = 0 ; i < row ; i++)
        {
            for (int j = 0 ; j < col ; j++)
            { 
				a [move] = new JButton(createImageIcon (piece [i] [j] + "" + colour [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".jpg"));
				a [move].setPreferredSize (new Dimension (75, 75));
				a [move].addActionListener (this);
				a [move].setActionCommand ("" + move);
				p.add (a [move]);
				move++;
            }
        }
        card3.add (title);
        card3.add (turnpic);
        card3.add (p);
		card3.add (back);
		card3.add (restart);
        card3.add (next);
        p_card.add ("3", card3);
    }

    public void screen4 ()
    { //screen 4 is set up.
        card4 = new Panel ();
        card4.setBackground (new Color (255, 255, 255));
        JLabel title = new JLabel ("You Win!");
        JButton next = new JButton ("Next");
        next.setActionCommand ("s5");
        next.addActionListener (this);
        card4.add (title);
        card4.add (next);
        p_card.add ("4", card4);
    }


    public void screen5 ()
    { //screen 5 is set up.
        card5 = new Panel ();
        card5.setBackground (new Color (255, 255, 255));
        JLabel title = new JLabel ("You Lose.");
        JButton next = new JButton ("Back to Introduction?");
        next.setActionCommand ("s1");
        next.addActionListener (this);
        JButton end = new JButton ("Quit?");
        end.setActionCommand ("s6");
        end.addActionListener (this);
        card5.add (title);
        card5.add (next);
        card5.add (end);
        p_card.add ("5", card5);
    }


    protected static ImageIcon createImageIcon (String path)
    { //change the red to your class name
        java.net.URL imgURL = FinalGame_Chess.class.getResource (path);
        if (imgURL != null)
        {
            return new ImageIcon (imgURL);
        }
        else
        {
            System.err.println ("Couldn't find file: " + path);
            return null;
        }
    }


    public void redraw ()
    {
        int move = 0;
        for (int i = 0 ; i < row ; i++)
        {
            for (int j = 0 ; j < col ; j++)
            {
				a [move].setIcon (createImageIcon (piece [i] [j] + "" + colour [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".jpg"));
				move++;
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
    turn = 'w'; // white starts
    turnpic.setIcon(createImageIcon("slytherinLogo.png"));
}

private char getPieceTypeForInitialRow(int col) {
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
    public void selectPawn(int x, int y)
    {
        // System.out.println(a[x*y].getIcon() + " " + piece [x] [y] + "" + colour [x] [y] + "" + bg [x] [y] + "" + select [x] [y] + ".jpg");
        // a[x*y].setIcon(createImageIcon (piece [x] [y] + "" + colour [x] [y] + "" + bg [x] [y] + "" + select [x] [y] + ".jpg"));
        // Pawn Movement
        if (colour[x][y] == 'b' && x == 6)
        {
            if (colour[x - 1][y] == 'x')
            {
                select[x - 1][y] = 's';
                if (colour[x - 2][y] == 'x')
                {
                    select[x - 2][y] = 's';
                }
            }
        }
        else if (colour[x][y] == 'w' && x == 1)
        {
            if (colour[x + 1][y] == 'x')
            {
                select[x + 1][y] = 's';
                if (colour[x + 2][y] == 'x')
                {
                    select[x + 2][y] = 's';
                }
            }
        }
        else if (colour[x][y] == 'b')
        {
            if (x - 1 >= 0 && y - 1 >= 0 && colour[x - 1][y - 1] == 'w')
            {
                //black attacking white at the right
                select[x - 1][y - 1] = 's';
                if (colour[x - 1][y] == 'x')
                {
                    select[x - 1][y] = 's';
                }
            }
            if (x - 1 >= 0 && y + 1 < col && colour[x - 1][y + 1] == 'w')
            {
                //black attacking white at the left
                select[x - 1][y + 1] = 's';
                if (colour[x - 1][y] == 'x')
                {
                    select[x - 1][y] = 's';
                }
            }
            else if (colour[x - 1][y] == 'x')
            {
                select[x - 1][y] = 's';
            }
        }
        else if (colour[x][y] == 'w')
        {
            if (x + 1 < row && y - 1 >= 0 && colour[x + 1][y - 1] == 'b')
            {
                //white attacking black at the right
                select[x + 1][y - 1] = 's';
                if (colour[x + 1][y] == 'x')
                {
                    select[x + 1][y] = 's';
                }
            }
            if (x + 1 < row && y + 1 < col && colour[x + 1][y + 1] == 'b')
            {
                //white attacking black at the left
                select[x + 1][y + 1] = 's';
                if (colour[x + 1][y] == 'x')
                {
                    select[x + 1][y] = 's';
                }
            }
            else if (colour[x + 1][y] == 'x')
            {
                select[x + 1][y] = 's';
            }
        }
        
        
    }

    public void selectKing(int x, int y)
    {
        if (x - 1 >=0 && y + 1 < col && colour[x - 1][y + 1] != turn)
        {
            select[x - 1][y + 1] = 's';
        }
        if (x + 1 < row && y + 1 < col && colour[x + 1][y + 1] != turn)
        {
            select[x + 1][y + 1] = 's';
        }
        if (y - 1 >= 0 && colour[x][y - 1] != turn)
        {
            select[x][y - 1] = 's';
        }
        if (x - 1 >= 0 && colour[x - 1][y] != turn)
        {
            select[x - 1][y] = 's';
        }
        if (x - 1 >= 0 && y - 1 >= 0 && colour[x - 1][y - 1] != turn)
        {
            select[x - 1][y - 1] = 's';
        }
        if (x + 1 < row && y - 1 >= 0 && colour[x + 1][y - 1] != turn)
        {
            select[x + 1][y - 1] = 's';
        }
        if (y + 1 < col && colour[x][y + 1] != turn)
        {
            select[x][y + 1] = 's';
        }
        if (x + 1 < row && colour[x + 1][y] != turn)
        {
            select[x + 1][y] = 's';
        }
    }
    public void selectKnight(int x, int y)
    {
    if (x - 2 >= 0 && y + 1 < col && colour[x - 2][y + 1] != turn)
    {
        select[x - 2][y + 1] = 's';
    }
    if (x - 2 >= 0 && y - 1 >= 0 && colour[x - 2][y - 1] != turn)
    {
        select[x - 2][y - 1] = 's';
    }
    if (x + 2 < row && y + 1 < col && colour[x + 2][y + 1] != turn)
    {
        select[x + 2][y + 1] = 's';
    }
    if (x + 2 < row && y - 1 >= 0 && colour[x + 2][y - 1] != turn)
    {
        select[x + 2][y - 1] = 's';
    }
    if (x - 1 >= 0 && y + 2 < col && colour[x - 1][y + 2] != turn)
    {
        select[x - 1][y + 2] = 's';
    }
    if (x - 1 >= 0 && y - 2 >= 0 && colour[x - 1][y - 2] != turn)
    {
        select[x - 1][y - 2] = 's';
    }
    if (x + 1 < row && y + 2 < col && colour[x + 1][y + 2] != turn)
    {
        select[x + 1][y + 2] = 's';
    }
    if (x + 1 < row && y - 2 >= 0 && colour[x + 1][y - 2] != turn)
    {
        select[x + 1][y - 2] = 's';
    }
    }

    public void selectRook(int x, int y)
    {
    // up
    for (int i = x - 1; i >= 0; i--)
    {
        if (colour[i][y] == turn)
        {
        break;
        }
        select[i][y] = 's';
        if (colour[i][y] != 'x')
        {
        break;
        }
    }
    // down
    for (int i = x + 1; i < row; i++)
    {
        if (colour[i][y] == turn)
        {
        break;
        }
        select[i][y] = 's';
        if (colour[i][y] != 'x')
        {
        break;
        }
    }
    // left
    for (int i = y - 1; i >= 0; i--)
    {
        if (colour[x][i] == turn)
        {
        break;
        }
        select[x][i] = 's';
        if (colour[x][i] != 'x')
        {
        break;
        }
    }
    // right
    for (int i = y + 1; i < col; i++)
    {
        if (colour[x][i] == turn)
        {
        break;
        }
        select[x][i] = 's';
        if (colour[x][i] != 'x')
        {
        break;
        }
    }
    } 
    public void selectBishop(int x, int y)
    {
        // up right
        for (int i = x - 1, j = y + 1; i >= 0 && j < col; i--, j++)
        {
            if (colour[i][j] == turn)
            {
                break;
            }
            select[i][j] = 's';
            if (colour[i][j] != 'x')
            {
                break;
            }
        }
        // up left
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--)
        {
            if (colour[i][j] == turn)
            {
                break;
            }
            select[i][j] = 's';
            if (colour[i][j] != 'x')
            {
                break;
            }
        }
        // down right
        for (int i = x + 1, j = y + 1; i < row && j < col; i++, j++)
        {
            if (colour[i][j] == turn)
            {
                break;
            }
            select[i][j] = 's';
            if (colour[i][j] != 'x')
            {
                break;
            }
        }
        // down left
        for (int i = x + 1, j = y - 1; i < row && j >= 0; i++, j--)
        {
            if (colour[i][j] == turn)
            {
                break;
            }
            select[i][j] = 's';
            if (colour[i][j] != 'x')
            {
                break;
            }
        }
    }
    public void selectQueen(int x, int y)
    {
        selectBishop(x, y);
        selectRook(x, y);
    }

    
    public void actionPerformed (ActionEvent e)
    { //moves between the screens
    if (e.getActionCommand ().equals ("s1"))
        cdLayout.show (p_card, "1");
    else if (e.getActionCommand ().equals ("s2"))
        cdLayout.show (p_card, "2");
    else if (e.getActionCommand ().equals ("s3"))
        cdLayout.show (p_card, "3");
    else if (e.getActionCommand ().equals ("s4"))
        cdLayout.show (p_card, "4");
    else if (e.getActionCommand ().equals ("s5"))
        cdLayout.show (p_card, "5");
    else if (e.getActionCommand ().equals ("s6"))
        System.exit (0);
	else if (e.getActionCommand ().equals ("reset"))
	{
		//reset
		resetBoard();
	}
    else
    { //code to handle the game
        int n = Integer.parseInt (e.getActionCommand ());
        int x = n / col;
        int y = n % col;
        showStatus ("(" + x + ", " + y + ")");

        if (turn != colour[x][y] && last == -1)
        {
        showStatus ("Not your turn");
        }
        else if (last == -1 && turn == colour[x][y])
        {
        if (piece[x][y] == 'p')
        {
            selectPawn(x, y);
        }
        else if (piece[x][y] == 'k')
        {
            selectKing(x, y);
        }
        else if (piece[x][y] == 'n')
        {
            selectKnight(x, y);
        }    
        else if (piece[x][y] == 'r')
        {
            selectRook(x, y);
        }
                else if (piece[x][y] == 'b')
                {
                    selectBishop(x, y);
                }
                else if (piece[x][y] == 'q')
                {
                    selectQueen(x, y);
                }
            
        
        last = n;
        }
        else
        {
        int lastx = last / col;
        int lasty = last % col;
        //move
        if (select[x][y] == 's')
        {
            //move
            piece[x][y] = piece[lastx][lasty];
            piece[lastx][lasty] = 'x';
            colour[x][y] = colour[lastx][lasty];
            colour[lastx][lasty] = 'x';
            //switch turn
            if (turn == 'w')
            {
            turn = 'b';
            turnpic.setIcon(createImageIcon("slytherinLogo.png"));
            }
            else
            {
            turn = 'w';
            turnpic.setIcon(createImageIcon("gryffindorLogo.png"));
            }
        } // Move end

        //reset
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                select[i][j] = 'u';
            }
        }
        last = -1;
        } // else end
        redraw ();
    }
    }
}
