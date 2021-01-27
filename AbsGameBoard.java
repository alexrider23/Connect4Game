package connectX;

public abstract class AbsGameBoard implements IGameBoard{
    /**
     * class that contains abstract game board, used to determine string output of game board
     */
    /**
     * @pre valid board
     * @post string of board
     * @return string of board
     */
    public String toString(){
        String s = "";
        final int DOUBLEDIGITS = 10;
        for (int i = 0; i< getNumCols(); i++) {//string of upper board that shows which column is numbered which
            if (i >= DOUBLEDIGITS) {
                s += "|" + i;
            } else {
                s += "| " + Integer.toString(i);
            }
        }
        s+= "|\n";
        for (int i = getNumRows()-1; i >=0; i--){ //string of columns
            for (int j= 0; j< getNumCols(); j++) {
                if ((whatsAtPos(i,j)) == '\u0000') { //empty
                    s += "| " + " ";
                } else { //filled
                    s += "| " + whatsAtPos(i,j);
                }
            }
            s += "|\n";
        }
        return s;
    }
}
