/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Connect 4
 * CSE114
 */

package ConnectFour;
import java.util.*;

/**
 * Connect four class. Main class for starting Connect Four. Creates a formatted 6x6 game 
 * board and begins a game.
 * @author eddie
 *
 */
public class ConnectFour {
	public static void main(String[]args) {
		Game.startGame();
	}
}

/**
 * Game class that holds all methods for a game of Connect Four. Kept in the same class 
 * as the main ConnectFour.
 * 
 * @author eddie
 *
 */
class Game{
	
	/**
	 * Prints out a neatly formatted 6x6 game board for a game of Connect Four.
	 * @param gameBoard
	 * 		Game board represented by a String double array to access elements and check 
	 * 		for changes to print.
	 */
	public static void printBoard(String[][]gameBoard) {
		for(int i=0;i<gameBoard.length;i++) {
			for(int j=0;j<gameBoard[0].length;j++) {
				System.out.print("["+gameBoard[i][j]+"]");
			}
			System.out.println();
		}
	}
	
	/**
	 * Rotates the player moves between Red and Yellow.
	 * @param player
	 * 		The current player's token (Red/Yellow: R/Y).
	 * @return
	 * 		The opposite player's token.
	 */
	public static String playerMove(String player) {
		if(player.equals("R")) {
			return "Y";
		}
		else
		{
			return "R";
		}
	}
	
	/**
	 * Adds a player move to the game board. 
	 * @param col
	 * 		User input of which column of the game board the token is to be placed in.
	 * @param player
	 * 		Current player (R/Y).
	 * @param in
	 * 		Scanner to read for user input.
	 * @param gameBoard
	 * 		Current Connect Four's game board instance.
	 */
	public static void addMove(int col, String player, Scanner in, String[][]gameBoard) {
		int row = 0;
		int availableRow = -1;
		while(gameBoard[row][col].equals(" ")) {
			availableRow = row;
			row++;
			if(row==6) {
				row--;
				break;
			}
		}
		if (availableRow != -1) {
			gameBoard[availableRow][col] = player;
		}
		else {
			System.out.println("The column is already full. Please enter another position.");
			col=in.nextInt();
			addMove(col, player, in, gameBoard);
		}
	}
	
	/**
	 * Checks win conditions in a typical Connect Four game.
	 * @param gameBoard
	 * 		Current Connect Four's game board instance.
	 * @param player
	 * 		Current player (R/Y).
	 * @return
	 * 		true if a win condition is met, false otherwise.
	 */
	public static boolean checkWin(String[][]gameBoard,String player) {
		for(int r=0;r<gameBoard.length;r++) {
			for(int c=0;c<gameBoard[0].length-3;c++) {
				if((gameBoard[r][c]==player)&&(gameBoard[r][c+1]==player)&&(gameBoard[r][c+2]==player)&&(gameBoard[r][c+3]==player)) {
					return true;
				}
			}
		}
		for(int r=0;r<gameBoard.length-3;r++) {
			for(int c=0;c<gameBoard[0].length;c++) {
				if((gameBoard[r][c]==player)&&(gameBoard[r+1][c]==player)&&(gameBoard[r+2][c]==player)&&(gameBoard[r+3][c]==player)) {
					return true;
				}
			}
		}
		for(int r=3;r<gameBoard.length;r++) {
			for(int c=0;c<gameBoard[0].length-3;c++) {
				if(gameBoard[r][c]==player&&gameBoard[r-1][c+1]==player&&gameBoard[r-2][c+2]==player&&gameBoard[r-3][c+3]==player) {
					return true;
				}
			}
		}
		for(int r=0;r<gameBoard.length-3;r++) {
			for(int c=0;c<gameBoard[0].length-3;c++) {
				if(gameBoard[r][c]==player&&gameBoard[r+1][c+1]==player&&gameBoard[r+2][c+2]==player&&gameBoard[r+3][c+3]==player) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Method to start the Connect Four game. Creates a user interface and instantiates a game
	 * of Connect Four.
	 */
	public static void startGame() {
		System.out.println("Welcome to Connect 4!\nTo play, enter in the column that you would like to drop in your piece.\nThe first player is Red (R) and the second player is Yellow (Y).");
		boolean gameCont=true;
		String[][]board=new String[6][7];
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				board[i][j]=" ";
			}
		}
		String player="R";
		int numMoves=0;
		Scanner scan = new Scanner(System.in);
		while(gameCont) {
			System.out.println("Enter where you would like to 'drop' the disk (0-6):");
			printBoard(board);
			try {
				int col=scan.nextInt();
				if (col<0||col>6) {
					throw new IllegalArgumentException();
				}
				numMoves++;
				addMove(col,player,scan,board);
				if(checkWin(board,player)) {
					printBoard(board);
					break;
				}
				player=playerMove(player);
			}
			catch(Exception e) {
				scan.nextLine();
				System.out.println("Please enter a number between 0-6, inclusive.");
			}
			if(numMoves==42) {
				printBoard(board);
				break;
			}
		}
		if(checkWin(board,player)){
			System.out.print("Player "+player+" wins!");
		}
		else {
			System.out.println("Tie!");
		}
	}
}
