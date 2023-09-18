import java.util.Scanner;

/** 
 * The Player class models a Tic-Tac-Toe player.  When created, instances
 * of this class are told whether they're playing X or O, and given an
 * identifying string.  The makeMove() method prints the current board,
 * then asks for user input in selecting a move.
 * 
 * @author Brad Richards
 * @version 1.2
 */

public class Player {
    protected int symbol;  /** Are we X or O?  (Board.X or Board.O) */
    protected String name; /** Player's name */

    /** A scanner so we can consult the user as necessary */
    private static Scanner scan = new Scanner(System.in);

    
    /** 
     * The constructor takes a constant from the Board class describing 
     * whether this player is X or O (Board.X or Board.O), and the 
     * player's name.
     * 
     * @param symbol  One of the player constants from the Board class
     *                (Board.X or Board.O).
     * @param name  The player's name.
     */
    public Player(int symbol, String name) {
        if (symbol == Board.X || symbol == Board.O) {
            this.symbol = symbol;
        }
        else {
            System.out.println("Invalid player constant -- must be Board.X or Board.O");
            this.symbol = Board.X;
        }
        this.name = name;
    }
    
    /**     
     * This method takes a constant from the Board class describing 
     * whether this player should be playing X or O (Board.X or Board.O), 
     * and updates the Player state so that it's playing the specified
     * symbol.  (If the symbol isn't Board.X or Board.O, the Player's 
     * state is left unchanged.)
     * 
     * @param symbol  One of the player constants from the Board class
     *                (Board.X or Board.O).
     */
    public void setSymbol(int symbol) {
        if (symbol == Board.X || symbol == Board.O) {
            this.symbol = symbol;
        }
        else {
            System.out.println("Invalid player constant -- must be Board.X or Board.O");
        }
    }
    
    /** 
     * The toString() method just returns the player's name. 
     * @return A string containing the player's name. 
     */
    public String toString() {
        return this.name;
    }
    
    /** 
     * This method is called when the player wins a game, and gives the
     * player an opportunity to do some gloating.  It returns a string
     * that can be displayed, instead of printing something itself.
     * 
     * @return  A string describing the player's response to a win.
     */
    public String celebrate() {
        return "Yippee!  I win";
    }
    
    /** 
     * This method is called if the player loses, and returns a string
     * expressing the player's feelings about the loss in a mature and
     * thoughtful way.
     * 
     * @return  A string describing the player's response to a loss.
     */
    public String mourn() {
        return "Drat.  Foiled again!";
    }
    
    /** 
     * makeMove is passed a game board, and modifies the board to reflect
     * the player's next move.  The board could be inspected when deciding
     * where our next move should be, though this version of makeMove simply
     * asks the user to specify a location on the board for the next move.
     * 
     * @param theBoard  The board on which we're to make a move.
     */
    public void makeMove(Board theBoard) {
        // Display the current board so the players can see what's open
        System.out.println("The current board:\n"+theBoard);
        
        // Get the column and row information from the user
        System.out.print(this.name + ", enter col: ");
        int col = scan.nextInt();
        System.out.print(this.name + ", enter row: ");
        int row = scan.nextInt();
        
        // If they've tried to select an already-filled spot, keep after
        // them until you get input that works.
        while (!theBoard.isOpen(col,row)) {
            System.out.println("That spot's already filled.  Try again");
            System.out.print(this.name + ", enter col: ");
            col = scan.nextInt();
            System.out.print(this.name + ", enter row: ");
            row = scan.nextInt();
        } 
        
        // If we get here, they've selected an open spot.  Fill it.
        theBoard.fillPosition(col, row, symbol);
    }
}