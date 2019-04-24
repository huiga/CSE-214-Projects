package ConnectFour;
import java.util.*;
public class ConectFour {
	public static void main(String[]args) {
		Game.startGame();
	}
}

class Game{
	public static void printBoard(String[][]gameBoard) {
		for(int i=0;i<gameBoard.length;i++) {
			for(int j=0;j<gameBoard[0].length;j++) {
				System.out.print("["+gameBoard[i][j]+"]");
			}
			System.out.println();
		}
	}
	public static String playerMove(String player) {
		if(player.equals("R")) {
			return "Y";
		}
		else
		{
			return "R";
		}
	}
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
