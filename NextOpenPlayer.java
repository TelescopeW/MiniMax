
public class NextOpenPlayer extends Player {
    public NextOpenPlayer(int symbol, String name) {
        super(symbol, name);
    }

    /*
     * this override makeMove method loop through the board and find the next
     * open position and put a piece there
     */
    @Override
    public void makeMove(Board theBoard) {
        int size = theBoard.getSize();

        // Loop through the board from left to right, row by row
        for (int row = size - 1; row >= 0; row--) {
            for (int col = 0; col < size; col++) {
                if (theBoard.isOpen(col, row)) {
                    theBoard.fillPosition(col, row, symbol);
                    return; //find open and fill it
                }
            }
        }
    }
}


