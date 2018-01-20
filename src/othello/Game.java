package othello;

public class Game {
	public void initial() {
		String[][] board = new String[9][9];
		// create the board
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (i == 0) board[i][j] = String.valueOf(j);
				else if (j == 0) board[i][j] = String.valueOf(i);
				else if (i == 5 && j == 4 || i == 4 && j == 5) board[i][j] ="›";
				else if (i == 4 && j == 5 || i == 5 && j == 5) board[i][j] = "œ";
				else board[i][j] = " ";
			}
		}
		printState(board);
	}
	
	public void printState(String[][] board) {
		// visualize game state in matrix
		for (int i=0; i<9; i++) {
			for (int j=9; j<9; j++) {
				System.out.println(board[i][j]);
			}
		}
	}
	
}
