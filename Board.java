/**
 * The Board class models a 3 by 3 Tic-Tac-Toe board.  There are methods
 * for filling board positions with X or O symbols, checking for a winner,
 * and inspecting the contents of the board.
 */

public class Board {
    /*
     * Each location holds an X, O, or blank character.  The first
     * coordinate is the column, the second is row.  We can't use an
     * array of booleans, since there are three possibilities.
     */
    private char[][] theBoard;
    private char xSym;    // Character used to represent X
    private char oSym;    // Character used to represent O
    private char blank;   // Represents open spaces on board

    /** A constant corresponding to blank spaces.  It's used as the return
     * value of the getContents method, when the specified board position
     * is blank. */
    public static final int BLANK = 0;
    /** A constant corresponding to player/symbol X.  It's used to specify
     * which "side" a player instance is playing, and will also appear as
     * the result of the getContents method if the specified board position
     * is filled with an X. */
    public static final int X = 1;
    /** A constant corresponding to player/symbol O.  It's used to specify
     * which "side" a player instance is playing, and will also appear as
     * the result of the getContents method if the specified board position
     * is filled with an O. */
    public static final int O = 2;

    /**
     * The no-argument constructor creates a new blank board, using the
     * characters 'X', 'O', and '.' to represent X, O, and open spaces.
     */
    public Board() {
        xSym = 'X';
        oSym = 'O';
        blank = '.';
        theBoard = new char[3][3];
        for(int j=0; j<3; j++) {
            for(int i=0; i<3; i++) {
                theBoard[i][j] = blank;
            }
        }
    }

    /**
     * The toString method returns a multi-line string that shows the
     * current state of the board.
     *
     * @return A string representing the board state.
     */
    public String toString() {
        String s = "";
        for(int j=2; j>=0; j--) {
            for(int i=0; i<3; i++) {
                s = s + " "+theBoard[i][j]+" ";
            }
            s = s + "\n";
        }
        return s;
    }

    /**
     * Inspects the board to see if the specified position is open.
     *
     * @param col  Position's column value, zero-based.
     * @param row  Position's row value, zero-based.
     * @return  <code>true</code> if the position is open,
     * <code>false</code> otherwise.
     */
    public boolean isOpen(int col, int row) {
        return (theBoard[col][row] == blank);
    }

    /**
     * Fills a position on the board with the specified symbol (Board.X
     * or Board.O).  If the position is already filled, the method prints
     * an error message and leaves the board unmodified.
     *
     * @param col  Column of position to be filled, zero-based.
     * @param row  Row of position to be filled, zero-based.
     * @param player  One of the player constants defined by the Board
     * class (Board.X or Board.O).
     */
    public void fillPosition(int col, int row, int player) {
        if (player == X || player == O) {
            if (!isOpen(col,row)) {
                System.out.println("That spot's taken!");
                // Eventually we'll raise an exception here
            }
            else {
                theBoard[col][row] = (player==X ? xSym : oSym);
            }
        }
        else {
            System.out.println("Illegal value passed as player argument!"
                + "  Must be Board.X or Board.O!");
        }
    }

    /**
     * This method returns true if all spots on the board are filled.
     * Note that in the case of a draw, this method will return true
     * even though <code>getWinner</code> will report that neither
     * player has won.
     *
     * @return <code>true</code> if the board is completely filled,
     * <code>false</code> otherwise.
     */
    public boolean boardFilled() {
        for(int j=0; j<3; j++) {
            for(int i=0; i<3; i++) {
                if (isOpen(i,j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * The private method threeInARow returns true if three of the specified
     * character are found in a line horizontally, vertically, or diagonally.
     */
    private boolean threeInARow(char c) {
        for(int row=0; row<3; row++) {        // Check all rows
            boolean win = true;
            for(int col=0; col<3; col++) {
                win = win && (theBoard[col][row] == c);
            }
            if (win) {
                return true;
            }
        }
        for(int col=0; col<3; col++) {        // Check all cols
            boolean win = true;
            for(int row=0; row<3; row++) {
                win = win && (theBoard[col][row] == c);
            }
            if (win) {
                return true;
            }
        }
        // If we get this far, we didn't find three in a row vertically
        // or horizontally.  Our only hope is to find a diagonal.  Return
        // true if we find one, false otherwise.
        return (theBoard[0][0]==c && theBoard[1][1]==c && theBoard[2][2]==c) ||
        (theBoard[2][0]==c && theBoard[1][1]==c && theBoard[0][2]==c);
    }

    /**
     * Checks to see if a player has won the game.  If so, it returns the
     * constant corresponding to the winning player (Board.X or Board.O),
     * otherwise it returns -1.
     *
     * @return  The constant corresponding to the winning player, if a
     * player has won, or -1 if neither player has won.
     */
    public int getWinner() {
        if (threeInARow(xSym)) {
            return X;
        }
        else if (threeInARow(oSym)) {
            return O;
        }
        else {
            return -1;
        }
    }

    /**
     * This method returns the contents of the specified board position
     * using one of the three constants defined by the Board class
     * (Board.X, Board.O, or Board.BLANK).
     *
     * @param col  Column of position to be inspected, zero-based.
     * @param row  Row of position to be inspected, zero-based.
     * @return  One of the three constants defined by the Board class.
     */
    public int getContents(int col, int row) {
        if (theBoard[col][row] == xSym) {
            return Board.X;
        }
        else if (theBoard[col][row] == oSym) {
            return Board.O;
        }
        else {
            return Board.BLANK;
        }
    }
    
    /*
     * helper method that returns the size of the game board
     */
    public int getSize(){
        return theBoard.length; 
    }
    
}
