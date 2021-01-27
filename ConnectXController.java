package cpsc2150.connectX;

/**
 * The controller class will handle communication between our View and our Model (IGameBoard)
 *
 * This is where you will write code
 *
 * You will need to include your IGameBoard interface
 * and both of the IGameBoard implementations from Homework 3
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class
 */

public class ConnectXController {
    //our current game that is being played
    private IGameBoard curGame;


    //The screen that provides our view
    private ConnectXView screen;



    public static final int MAX_PLAYERS = 10;
    //our play tokens are hard coded. We could make a screen to get those from the user, but
    //I want to keep this example simple
    private char[] players = {'X', 'O', 'Y', 'Z', 'A', 'K', 'E', 'J', 'N', 'H'};

   int numPlayers;

   private int turn =0;
   private boolean again = false;


    /**
     *
     * @param model the board implementation
     * @param view the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameBoard model, ConnectXView view, int np){
        this.curGame = model;
        this.screen = view;
        numPlayers = np;

    }

    /**
     *
     *
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {
        if (again){ //if it is true we play again
            newGame();
        }
        //gets the player
        char player = players[turn%numPlayers];
        //gets the correct player turn for display
        int next_turn = turn +1;
        screen.setMessage("It is " + players[next_turn%numPlayers] + "'s turn");
        if (!curGame.checkIfFree(col)){ //is full
            screen.setMessage("The column is full, please pick another column");
            return;
        }
        curGame.placeToken(player,col);
        int row = 0;
        //loop to find the row of last played token
        for (int i = 0; i < curGame.getNumRows();i++){
            if (curGame.whatsAtPos(i, col) == player){
                row = i;
            }
        }
        //sets it
        screen.setMarker(row,col,player);
        //wins the game
        if (curGame.checkForWin(col)) {
            screen.setMessage(player + " wins the game! If you would like to play again, please click any button");
            again = true;
            return;
        }
        //game is tied
        if (curGame.checkTie()){
            screen.setMessage("It is a tie game! If you would like to play again, please click any button");

            again = true;
            return;
        }
        turn++;

      
    }

    /**
     * This method will start a new game by returning to the setup screen and controller
     */
    private void newGame()
    {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}
