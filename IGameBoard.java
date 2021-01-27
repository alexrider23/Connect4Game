package connectX;

public interface IGameBoard {
    /**
     * This interface represents the concept of a GameBoard for connect 4 game using a matrix.
     *
     * Initialization ensures:
     *      GameBoard contains user input of rows, columns and win condition
     *      rows are less than MAX_ROW, columns are less than MAX_COL, and win condition is less than MAX_WIN
     *
     * Defines:     num_rows = N
     *              num_cols = N
     *              num_win = N
     *              MAX_ROW = 100
     *              MAX_COL = 100
     *              MAX_WIN = 25
     *              NUM = 3
     *
     *Constraints:
     *              MIN<= num_rows <= MAX_ROW
     *              MIN <= num_cols <= MAX_COL
     *              MIN <+ num_win <= MAX_WIN
     */
    /**
     * @pre row >= MIN && row <= MAX_ROW
     * @post value of row
     * @return value of row
     */
    int getNumRows();

    /**
     * @pre col >= MIN && col <= MAX_COL
     * @post  value of col
     * @return value of col
     */
    int getNumCols();

    /**
     * @pre win >= MIN && win <= MAX_WIN
     * @post value of win
     * @return value of win
     */
    int getNumToWin();
    /**
     * @pre input 0<=r<=MAX_ROW, 0<=c<=MAX_COL
     * @post value at Board[r][c]
     * @param r row that is to be checked
     * @param c column that is to be checked
     * @return character at Board[r][c]
     */
    char whatsAtPos(int r, int c);
    /**
     * @pre checkIfFree==true
     * @post p will be placed in the lowest free spot
     * @param p character p to be placed inside game board
     * @param c column where character is to be placed
     */
    void placeToken(char p, int c);
    /**
     * @pre c >= 0 & c <MAX_COL
     * @post true if empty else false
     * @param c column to be checked
     * @return true if column is empty else false
     */
    boolean checkIfFree(int c);
    /**
     * @pre p = 'X" or 'O', 0<=c<=MAX_COL, 0<=r<=MAX_ROW
     * @post true if tokens in hori == num_wins at Board[r][i] else false
     * @param r row that is to be placed in
     * @param c column that is to be placed in
     * @param p 'X' or 'O' to be placed
     * @return true if there are num_win number tokens of the same color placed horizontally at row
     */
    default boolean checkHorizWin(int r, int c, char p ) {
        int counter = 0;
        for (int i = 0; i < getNumCols(); i++) { //loops through the row
            if (whatsAtPos(r,i) == p){ //if same color
                counter++; //increase token
                if (counter == getNumToWin()){ //number tokens in a row so win
                    return true;
                }
            }
            else { //resets counter to 0 if next token not the same
                counter = 0;
            }
        }
        return false; //no win
    }
    /**
     * @pre p = 'X" or 'O', 0<=c<=MAX_COL, 0<=r<=MAX_ROW
     * @post true if tokens verti == num_wins at Board[i][c] else false
     * @param r row that is to be placed in
     * @param c column that is to be placed in
     * @param p 'X' or 'O' to be placed
     * @return true if there are num_win amount of tokens of the same color placed vertically in a row
     */
    default boolean checkVertWin (int r, int c, char p){
        int counter = 0;
        for (int i = 0; i < getNumRows(); i++){ //goes through the rows
            if (whatsAtPos(i,c) == p){ //if next row has same token
                counter++;
                if (counter ==getNumToWin()){ //number tokens vertically
                    return true;
                }
            }
            else { //resets token to 0 if next one is not the same
                counter = 0;
            }
        }
        return false;
    }
    /**
     * @pre p = 'X" or 'O', 0<=c<=MAX_COL, 0<=r<=MAX_ROW
     * @post true if tokens == num_win diag at Board[r][c] else false
     * @param r row that is to be placed in
     * @param c column that is to be placed in
     * @param p 'X' or 'O' to be placed
     * @return true if there are num_win number of tokens of the same color placed diagonally
     */
    default boolean checkDiagWin(int r, int c, char p){

        int counter = 1;
        for (int i = 1; i < getNumToWin(); i++){ //checks number right diag upper
            if(r-i < 0 || c+i >=getNumCols()){ //out of index so skip
                continue;
            }
            if (whatsAtPos(r-i,c+i) == p){ //upper right diag check
                counter++; //increase
                if (counter == getNumToWin()){//wins
                    return true;
                }
            }
            else {
                break;
            }
        }

        for (int j = 1; j<getNumToWin(); j++){ //checks number right diag lower
            if (c-j < 0 || r+j >= getNumRows()){ //out of index
                continue;
            }
            if (whatsAtPos(r+j,c-j) == p){ //if it is increase counter
                counter++;
                if (counter == getNumToWin()){ //wins
                    return true;
                }
            }
            else {
                break;
            }
        }
        counter = 1; //resets counter to 1 to check left diag
        for (int a = 1; a<getNumToWin(); a++) { //checks number left diag upper
            if (r + a >= getNumRows() || c + a >= getNumCols()) { //out of bounds
                continue;
            }
            if (whatsAtPos(r+a,c+a) == p) { //if it is increase
                counter++;
                if (counter == getNumToWin()) { //wins
                    return true;
                }
            } else {
                break;
            }
        }
        for (int b = 1; b <getNumToWin(); b++){ //checks number left diag lower
            if (r-b < 0 || c-b < 0){ //out of bounds
                continue;
            }
            if (whatsAtPos(r-b,c-b) == p){ //increase counter if it is
                counter++;
                if (counter == getNumToWin()){ //wins
                    return true;
                }
            }
            else {
                break;
            }
        }
        return false; //does not meet conditions so false
    }
    /**
     * @pre c 0<=c<=MAX_COL
     * @post true if win else false
     * @param c column to check if player wins
     * @return true if player does win else false
     */
    default boolean checkForWin(int c){ //check if free, start from bottom and check if free and once we find we minus 1
        int counter = 0;
        if (checkIfFree(c)) {
            for (int i = 0; i < getNumRows(); i++) {
                if (whatsAtPos(i,c)!= '\u0000'){
                    counter++;
                }
            }
            counter = counter -1; //gets it at the right index
        }
        else {
            counter = getNumRows()-1; //gets it at the right index
        }
        if (this.checkHorizWin(counter, c, whatsAtPos(counter,c))) { //hori win
            return true;
        }
        if (this.checkDiagWin(counter, c,  whatsAtPos(counter,c))) { //diag win
            return true;
        }
        if (this.checkVertWin(counter, c,  whatsAtPos(counter,c))) { //verti win
            return true;
        }
        return false;

    }
    /**
     * @pre no previous play has resulted in a win
     * @post true if all filled else false
     * @return true if all columns filled else false
     */
    default boolean checkTie(){
        int tieCol = 0; //counter
        for (int i = 0; i <getNumCols(); i ++){//iterates through column
            if (!checkIfFree(i)){ //if not free then increase counter
                tieCol++;
            }
        }
        if (tieCol == getNumCols()){ //completely filled
            return true;
        }
        else { //not filled
            return false;
        }
    }


}
