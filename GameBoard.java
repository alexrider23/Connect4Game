package cpsc2150.connectX;
public class GameBoard extends AbsGameBoard implements IGameBoard{
    /**
     * class that contains info on the game board using a 2-D array
     *@invariant row, MIN<=row<=MAX_ROW
     *@invariant  column, MIN<= column <= MAX_COL
     * @invariant  winCond, MIN<= winCond <=MAX_WIN
     *
     * Correspondence num_rows = rows
     * Correspondence num_cols = column
     * Correspondence num_win = winCond
     * Correspondence this = Board[0...rows-1][0...columns-1]
     */
    private char Board[][];
    private int row;
    private int column;
    private int winCond;

    /**
     * @pre n/a
     * @post board with  num_rows rows and num_cols columns
     * @param r rows for game board
     * @param c columns for game board
     * @param w win condition for game board
     */
    GameBoard(int r, int c, int w) {
        Board = new char [r][c];
        this.column = c;
        this.row = r;
        this.winCond = w;

    }

@Override
    public boolean checkIfFree(int c) {
        if (Board[row -1][c] == '\u0000') { //if it is empty
            return true;
        } else {
            return false;
        }
    }

@Override
    public void placeToken(char p, int c){
        for (int i = 0; i < row; i++){ //starts at 0,c
            if (Board[i][c] == '\u0000'){ //checks to see if it is null
                Board[i][c] = p; //if it is place
                break;
            }
        }
    }




    @Override
    public int getNumRows() {
        return this.row;
    }

    @Override
    public int getNumCols() {
        return this.column;
    }

    @Override
    public int getNumToWin() {
        return this.winCond;
    }

@Override
    public char whatsAtPos(int r, int c){
        if (Board[r][c] == '\u0000'){ //if it is empty
            return '\u0000';
        }
        return Board[r][c];
    }


}
