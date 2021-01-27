package cpsc2150.connectX;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Connect4Game {
    private static Scanner scanner;
    private static List<Character> players;
    private static IGameBoard g;

    public static void main(String args[]) {
        final int MAX = 100;
        final int MIN = 3;
        final int WINMAX = 25;
        scanner = new Scanner(System.in);
        int row;
        int col;
        int win;
        char choice;
        players = new ArrayList<Character>();
        while (true) {
            players = getPlayers();
            while (true){
                System.out.println("How many rows should be on the board?");
                scanner.nextLine();
                row = Integer.parseInt(scanner.nextLine());
                if (row <= MAX && row >=MIN){
                    break;
                }
                else {
                    if (row <MIN){
                        System.out.println("Must have at least 3 rows");
                        System.out.println("How many rows should be on the board?");
                        row = Integer.parseInt(scanner.nextLine());
                        if (row <= MAX && row >=MIN){
                            break;
                        }
                    }
                    if ( row >MAX){
                        System.out.println("Can have at most 100 rows");
                        System.out.println("How many rows should be on the board?");
                        row = Integer.parseInt(scanner.nextLine());
                        if (row <= MAX && row >=MIN){
                            break;
                        }
                    }
                }
            }
            while (true){
                System.out.println("How many columns should be on the board?");
                col= Integer.parseInt(scanner.nextLine());
                if (col <= MAX && col >=MIN){
                    break;
                }
                else {
                    if (col <MIN){
                        System.out.println("Must have at least 3 columns");
                        System.out.println("How many columns should be on the board?");
                        col = Integer.parseInt(scanner.nextLine());
                        if (col <= MAX && col >=MIN){
                            break;
                        }
                    }
                    if ( col >MAX){
                        System.out.println("Can have at most 100 columns");
                        System.out.println("How many columns should be on the board?");
                        col = Integer.parseInt(scanner.nextLine());
                        if (col <= MAX && col >=MIN){
                            break;
                        }
                    }
                }
            }
            while (true){
                System.out.println("How many in a row to win?");
                win = Integer.parseInt(scanner.nextLine());
                if (win <= WINMAX && win >=MIN){
                    break;
                }
                else {
                    if (win <MIN){
                        System.out.println("Must have at least 3 in a row to win");
                        System.out.println("How many in a row to win?");
                        win = Integer.parseInt(scanner.nextLine());
                        if (win <= WINMAX && win >=MIN){
                            break;}
                    }
                    if ( win >WINMAX){
                        System.out.println("Can have at most 25 in a row to win");
                        System.out.println("How many in a row to win?");
                        win = Integer.parseInt(scanner.nextLine());
                        if (win <= WINMAX && win >=MIN){
                            break;}
                    }
                }
            }
            while (true){
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?");
                choice = scanner.nextLine().charAt(0);
                if (choice == 'F' || choice == 'f'){
                    g= new GameBoard(row, col, win);
                    break;

                }
                if (choice == 'M' || choice == 'm'){
                    g = new GameBoardMem(row,col,win);
                    break;
                }
                System.out.println("Please enter F or M");
            }
            System.out.println(g);
            startGame(g,row,col,win,players);
            if (!playAgain()) {
                break;
            }
            scanner.nextLine();
        }

    }


    public static void print(char p) {
        System.out.println("Player " + p + ", what column do you want to place your marker in?");
    }


public static int gameDetails(IGameBoard g,int r, int c, int w, char p){
        scanner = new Scanner(System.in);
        int move;
    move = Integer.parseInt(scanner.nextLine());
    while (move >= c || move < 0) {
        if (move >= c) {
            System.out.println("Column cannot be greater than " + (c-1));
        }
        if (move < 0) {
            System.out.println("Column cannot be less than 0");
        }
        print(p);
        move = Integer.parseInt(scanner.nextLine());
    }
    while (!g.checkIfFree(move)) {
        System.out.println("Column is full");
        print(p);
        move = Integer.parseInt(scanner.nextLine());
        while (move >= c || move < 0) {
            if (move >= c) {
                System.out.println("Column cannot be greater than " +(c-1));
            }
            if (move < 0) {
                System.out.println("Column cannot be less than 0");
            }
            print(p);
            move = Integer.parseInt(scanner.nextLine());
        }
    }
    return move;
}
    public static void startGame(IGameBoard g,int r, int c, int w, List<Character> p) {
        scanner = new Scanner(System.in);
        int turn = 0;
        final int player1 = 0;
        final int player2 =1;
        final int player3 = 2;
        final int player4 = 3;
        final int player5 = 4;
        final int player6 = 5;
        final int player7 = 6;
        final int player8 = 7;
        final int player9 = 8;
        final int player10 = 9;
        final int numPlayers = p.size();
        boolean gameCondition = true;
        int move;
        while (gameCondition) {
            if (turn % numPlayers == player1) {
                print(players.get(player1));
                move = gameDetails(g,r,c,w,players.get(player1));
                g.placeToken(players.get(player1), move);
                System.out.println(g);
                if (g.checkForWin(move)) {
                    System.out.println("Player "+players.get(player1)+" Won!");
                    gameCondition = false;
                    return;
                }
                if ((g.checkTie())) {
                    System.out.println("Game is a tie");
                    gameCondition = false;
                }
                turn++;
            }
            else if (turn % numPlayers == player2) {
                print(players.get(player2));
                move = gameDetails(g,r,c,w,players.get(player2));
                g.placeToken(players.get(player2), move);
                System.out.println(g);
                if (g.checkForWin(move)) {
                    System.out.println("Player "+players.get(player2)+" Won!");
                    gameCondition = false;
                    return;
                }
                if ((g.checkTie())) {
                    System.out.println("Game is a tie");
                    gameCondition = false;
                }
                turn++;
            }
            else if (turn % numPlayers == player3) {
                print(players.get(player3));
                move = gameDetails(g,r,c,w,players.get(player3));
                g.placeToken(players.get(player3), move);
                System.out.println(g);
                if (g.checkForWin(move)) {
                    System.out.println("Player "+players.get(player3)+" Won!");
                    gameCondition = false;
                    return;
                }
                if ((g.checkTie())) {
                    System.out.println("Game is a tie");
                    gameCondition = false;
                }
                turn++;
            }
            else if (turn % numPlayers == player4) {
                print(players.get(player4));
                move = gameDetails(g,r,c,w,players.get(player4));
                g.placeToken(players.get(player4), move);
                System.out.println(g);
                if (g.checkForWin(move)) {
                    System.out.println("Player "+players.get(player4)+" Won!");
                    gameCondition = false;
                    return;
                }
                if ((g.checkTie())) {
                    System.out.println("Game is a tie");
                    gameCondition = false;
                }
                turn++;
            }
            else if (turn % numPlayers == player5) {
                print(players.get(player5));
                move = gameDetails(g,r,c,w,players.get(player5));
                g.placeToken(players.get(player5), move);
                System.out.println(g);
                if (g.checkForWin(move)) {
                    System.out.println("Player "+players.get(player5)+" Won!");
                    gameCondition = false;
                    return;
                }
                if ((g.checkTie())) {
                    System.out.println("Game is a tie");
                    gameCondition = false;
                }
                turn++;
            }
            else if (turn % numPlayers == player6) {
                print(players.get(player6));
                move = gameDetails(g,r,c,w,players.get(player6));
                g.placeToken(players.get(player6), move);
                System.out.println(g);
                if (g.checkForWin(move)) {
                    System.out.println("Player "+players.get(player6)+" Won!");
                    gameCondition = false;
                    return;
                }
                if ((g.checkTie())) {
                    System.out.println("Game is a tie");
                    gameCondition = false;
                }
                turn++;
            }
            else if (turn % numPlayers == player7) {
                print(players.get(player7));
                move = gameDetails(g,r,c,w,players.get(player7));
                g.placeToken(players.get(player7), move);
                System.out.println(g);
                if (g.checkForWin(move)) {
                    System.out.println("Player "+players.get(player7)+" Won!");
                    gameCondition = false;
                    return;
                }
                if ((g.checkTie())) {
                    System.out.println("Game is a tie");
                    gameCondition = false;
                }
                turn++;
            }
            else if (turn % numPlayers == player8) {
                print(players.get(player8));
                move = gameDetails(g,r,c,w,players.get(player8));
                g.placeToken(players.get(player8), move);
                System.out.println(g);
                if (g.checkForWin(move)) {
                    System.out.println("Player "+players.get(player8)+" Won!");
                    gameCondition = false;
                    return;
                }
                if ((g.checkTie())) {
                    System.out.println("Game is a tie");
                    gameCondition = false;
                }
                turn++;
            }
            else if (turn % numPlayers == player9) {
                print(players.get(player7));
                move = gameDetails(g,r,c,w,players.get(player9));
                g.placeToken(players.get(player9), move);
                System.out.println(g);
                if (g.checkForWin(move)) {
                    System.out.println("Player "+players.get(player9)+" Won!");
                    gameCondition = false;
                    return;
                }
                if ((g.checkTie())) {
                    System.out.println("Game is a tie");
                    gameCondition = false;
                }
                turn++;
            }
            else if (turn % numPlayers == player10) {
                print(players.get(player10));
                move = gameDetails(g,r,c,w,players.get(player10));
                g.placeToken(players.get(player10), move);
                System.out.println(g);
                if (g.checkForWin(move)) {
                    System.out.println("Player "+players.get(player10)+" Won!");
                    gameCondition = false;
                    return;
                }
                if ((g.checkTie())) {
                    System.out.println("Game is a tie");
                    gameCondition = false;
                }
                turn++;
            }

        }
    }


    public static boolean playAgain () {
        scanner = new Scanner(System.in);
        char play;

        System.out.println("Would you like to play again? Y/N");
        play = scanner.next().charAt(0);
        while (play != 'Y' && play != 'N' && play != 'y' && play != 'n') {
            System.out.println("Would you like to play again? Y/N");
            play = scanner.next().charAt(0);
        }
        if (play == 'Y' || play == 'y') {
            return true;
        } else {
            return false;
        }
    }
    public static List<Character> getPlayers() {
        List<Character> players = new ArrayList<>();
        scanner = new Scanner(System.in);
        int numPlayers;
        final int MIN = 2;
        final int MAX = 10;
        char playerToken;
        System.out.println("How many players?");
        numPlayers = Integer.parseInt(scanner.nextLine());
        while (numPlayers <MIN || numPlayers >MAX){
            if (numPlayers > MAX){
                System.out.println("Must be 10 players or fewer");
            }
            if (numPlayers < MIN){
                System.out.println("Must be at least 2 players");
            }
            System.out.println("How many players?");
            numPlayers = Integer.parseInt(scanner.nextLine());
        }
        for (int i = 0; i <numPlayers; i++){
            System.out.println("Enter the character to represent player " + (i+1));
            playerToken = Character.toUpperCase(scanner.next().charAt(0));
            while (players.contains(playerToken)){
                System.out.println(playerToken + " is already taken as a player token!");
                System.out.println("Enter the character to represent player " + (i+1));
                playerToken = Character.toUpperCase(scanner.next().charAt(0));
            }
            players.add(playerToken);
        }
        return players;
    }
}

