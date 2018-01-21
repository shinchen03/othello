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
				if (i == 9) board[i][j] = "";
			}
		}
		printState(board);
		checkState(board, "black");
		String state = blackWin(board);
		System.out.println(state);
	}
	
	public String blackWin(String[][] board) {
		int black = 0;
		int white = 0;
		int pass = 1;
		int win = 1;
		for (int i=1; i<9; i++) {
			for (int j=1; j<9; j++) {
				if (board[i][j] == "›") white++;
				if (board[i][j] == "œ") black++;
				if (board[i][j] == "~") pass = 0;
				if (board[i][j] == " ") win = 0;
			}
		}
		if (win == 1) {
			if (black > white) return "Black Win !!";
			if (black == white) return "draw";
			else return "White Win";
		}
		if (pass == 1) return "pass";
		return "continue";
	}
	
	public void printState(String[][] board) {
		// visualize game state in matrix
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				System.out.print(board[i][j]);
			}
		}
	}
	
	public void checkState(String[][] board, String turn) {
		// check the practicable points 
		for (int i=1; i<9; i++) {
			for (int j=1; j<9; j++) {
				if (board[i][j] == " " && 
						checkPracticable(board, i, j, turn)) board[i][j] = "~";
			}
		}
		printState(board);
	}
	
	public boolean checkPracticable(String[][] board, int i, int j, String turn) {
		// check whether this point is practicable
		if (turn == "black") {
			for (int m=-1; m<2; m++) {
				for (int n=-1; n<2; n++) {
					if (board[i+m][j+n] == "›") {
						int k = 1;
						while (board[i+k*m][j+k*n] == "›") {
							k++;
							if (board[i+k*m][j+k*n] == "œ") return true;
						}
					}
				}
			}
		}
		else {
			for (int m=-1; m<2; m++) {
				for (int n=-1; n<2; n++) {
					if (board[i+m][j+n] == "œ") {
						int k = 2;
						while (board[i+k*m][j+k*n] == "œ") {
							if (board[i+k*m][j+k*n] == "›") return true;
							k++;
						}
					}
				}
			}
		}
		return false;
	}
	
}
