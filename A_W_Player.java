import java.util.List;
import java.util.ArrayList;

/**
 * This class makes a A_W_Player that uses minimax algorithm to play the tic tac toe game. 
 *
 * @Alex Wang
 * @version 1.2
 */

public class A_W_Player extends Player
{
    /*
     * Constructor for objects of class A_W_Player
     */
    public A_W_Player(int symbol, String name)
    {
        super(symbol, name); //call constructor from the player superclass
    }

    /*
     * override method makeMove that uses minimax to obtain best move
     */
    @Override
    public void makeMove(Board theBoard) {
        int[] bestMove = minimax(theBoard, symbol, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int col = bestMove[0];
        int row = bestMove[1];
        //System.out.println("coordinate:"+col+row);
        theBoard.fillPosition(col, row, symbol);
        
    } 

/**
 * @param board  The current game board
 * @param playerSymbol   The current player's symbo (X or O)
 * @return int[]{0,0,score}, where score = 10 if winner plays X, -10 if winner plays O, 0 if game draw. 
 */
    /*
     * this is the real king of the class, implemented minimax algorithm using 
     * recursion. 
     */
    private int[] minimax(Board board, int playerSymbol, int alpha, int beta) {
        int[] bestMove = new int[]{-1, -1, 0};
        int bestScore = (playerSymbol == Board.X) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    
        int winner = board.getWinner();
        if (winner == Board.X) {
            return new int[]{0, 0, 10}; // The "X" player wins
        } else if (winner == Board.O) {
            return new int[]{0, 0, -10}; // The "O" player wins
        } else if (board.boardFilled()) {
            return new int[]{0, 0, 0}; // It's a draw
        }
    
        List<int[]> possibleMoves = new ArrayList<>();
    
        for (int col = 0; col < board.getSize(); col++) {
            for (int row = 0; row < board.getSize(); row++) {
                if (board.isOpen(col, row)) {
                    int[] move = new int[]{col, row, 0};
                    Board possibleBoard = copyBoard(board);
                    possibleBoard.fillPosition(col, row, playerSymbol);
    
                    int score;
                    if (playerSymbol == Board.X) {
                        score = minimax(possibleBoard, Board.O, alpha, beta)[2];
                    } else {
                        score = minimax(possibleBoard, Board.X, alpha, beta)[2];
                    }
    
                    move[2] = score;
                    possibleMoves.add(move);
    
                    if (playerSymbol == Board.X) {
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove = move;
                        }
                        alpha = Math.max(alpha, bestScore);
                    } else {
                        if (score < bestScore) {
                            bestScore = score;
                            bestMove = move;
                        }
                        beta = Math.min(beta, bestScore);
                    }
    
                    if (beta <= alpha) {
                        // Prune the remaining branches
                        break;
                    }
                }
            }
        }
        
        if (playerSymbol == Board.X) {
            return bestMove;
        } else {
            return new int[]{bestMove[0], bestMove[1], bestScore};
        }
    }
    



/**
 * 
 * @param input the input Board type object that will be replicated
 * @return a Board type object called duplicatedBoard that have copied the content of the Board input
 */
public Board copyBoard(Board input){
    int size = input.getSize();
    Board duplicatedBoard = new Board(); 

    for (int col = 0; col < size; col++) {
        for (int row = 0; row < size; row++) {
            if(input.getContents(col,row) == Board.X){
                duplicatedBoard.fillPosition(col, row, Board.X);
                //System.out.println(duplicatedBoard.toString());
            }
            else if(input.getContents(col,row) == Board.O){
                duplicatedBoard.fillPosition(col, row, Board.O); 
                //System.out.println(duplicatedBoard.toString());
            }
        }
    }
    return duplicatedBoard; 
}

    
    /*
     * helper method that finds the move with the highest score
     */
    /*private int maxScoreIndex(List<int[]> moves) {
        int maxScore = Integer.MIN_VALUE; //instantiated to be smallest int
        int maxIndex = -1; 

        for (int i = 0; i < moves.size(); i++) {
            if (moves.get(i)[0] > maxScore) {
                maxScore = moves.get(i)[0];
                maxIndex = i;
            }
        }

        return maxIndex;
    }*/

    /*
     * Helper method that finds the move with the least score
     */
        /*private int minScoreIndex(List<int[]> moves) {
        int minScore = Integer.MAX_VALUE; //set as largest int
        int minIndex = -1; 

        for (int i = 0; i < moves.size(); i++) {
            if (moves.get(i)[0] < minScore) {
                minScore = moves.get(i)[0];
                minIndex = i;
            }
        }

        return minIndex;
    }*/
    
}
