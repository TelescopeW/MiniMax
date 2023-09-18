import java.util.Random;
import java.util.ArrayList;

public class RandomPlayer extends Player {
    private Random random; //introduce random class object

    public RandomPlayer(int symbol, String name) {
        super(symbol, name);
        random = new Random();
    }

    /*
     * override method that find a list of open cells and them randomly pick
     * one to fill it with the current symbol
     */
    @Override
    public void makeMove(Board theBoard) {
        ArrayList<Integer> emptyCell = new ArrayList<>();//1 dimension arraylist combine with cellindex to store empty cell

        int size = theBoard.getSize();

        //make a list of empty cell
        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size; row++) {
                if (theBoard.isOpen(col, row)) {
                    int cellIndex = col * size + row;//index that combines row and column number
                    emptyCell.add(cellIndex);
                }
            }
        }

        // if no open cells found, return(stop) without modifying the board
        if (emptyCell.isEmpty()) {
            return;
        }
        int rdm = random.nextInt(emptyCell.size());
        int cellIndex = emptyCell.get(rdm);
        
        int col = cellIndex / size;
        int row = cellIndex % size;

        if (!theBoard.isOpen(col, row)) {
            System.out.println("That spot's already filled.  Try again.");
        } 
        else {
            // Fill the randomly selected open position
            theBoard.fillPosition(col, row, symbol);
        }

    }
}

