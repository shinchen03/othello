package othello;

public class Game {
	public void initial() {
		String[][] board = new String[10][10];
		// create the board
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (i == 0) board[i][j] = String.valueOf(j);
				if (j == 9) board[i][j] = "\n";
				else if (j == 0) board[i][j] = String.valueOf(i);
				else if (i == 5 && j == 4 || i == 4 && j == 5) board[i][j] ="›";
				else if (i == 4 && j == 4 || i == 5 && j == 5) board[i][j] = "œ";
				else if (i != 0) board[i][j] = " ";
			}
		}
		printState(board);
	}
	
	public void printState(String[][] board) {
		// visualize game state in matrix
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				System.out.print(board[i][j]);
			}
		}
	}
	
}
