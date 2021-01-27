package cpsc2150.connectX;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    /**
     * class that contains information on the game board using a hash map
     * @invariant row, MIN<=row<=MAX_ROW
     * @invariant  column, MIN<= column <= MAX_COL
     * @invariant  winCond, MIN<= winCond <=MAX_WIN
     * Correspondence num_rows = rows
     * Correspondence num_cols = column
     * Correspondence num_win = winCond
     * Correspondence this = Board[0...columns-1]
     */
    private int columns;
    private int rows;
    private int winCondition;
    private Map<Integer, List<Character>> myMap;
    private List<Character> list;

    /**
     * @pre n/a
     * @post hashmap of gameboard*
     * @param r rows for game board
     * @param c columns for game board
     * @param w win condition for game board
     */
    GameBoardMem(int r, int c, int w) {
        myMap = new HashMap<>();
        this.columns = c;
        this.rows = r;
        this.winCondition = w;

    }

    @Override
    public int getNumRows() {
        return this.rows;
    }

    @Override
    public int getNumCols() {
        return this.columns;
    }

    @Override
    public int getNumToWin() {
        return this.winCondition;
    }

    @Override
    public char whatsAtPos(int r, int c) {
        List<Character> tmp = myMap.get(c);
        if (tmp != null) {
            if (tmp.size() > r) { //possible to get value
                char p = tmp.get(r);
                return p;
            } else {
                return '\u0000';
            }
        } else {
            return '\u0000';
        }

    }

    @Override
    public void placeToken(char p, int c) {
        List <Character> tmp = myMap.get(c);
        if (tmp!= null){
            tmp.add(p);
        }
        else {
            list = new ArrayList<>();
            myMap.put(c,list);
            list.add(p);
        }

    }

    @Override
    public boolean checkIfFree(int c) {
        List<Character> tmp = myMap.get(c);
        if (tmp != null) {
            if (tmp.size() == rows) {
                return false;
            }
            return true;
        }
        return true;
    }
}