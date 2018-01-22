package othello;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game {
	
	private String turn = "black";
	private String state = "continue";
	public void initial() {
		String[][] board = new String[10][10];
		Panel newPanel = new Panel();
		
		// create the board
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (i == 0) board[i][j] = String.valueOf(j);
				if (j == 9) board[i][j] = "\n";
				else if (j == 0) board[i][j] = String.valueOf(i);
				else if (i == 5 && j == 4 || i == 4 && j == 5) board[i][j] ="Åõ";
				else if (i == 4 && j == 4 || i == 5 && j == 5) board[i][j] = "Åú";
				else if (i != 0) board[i][j] = "Å†";
				if (i == 9) board[i][j] = "";
			}
		}
		printState(board);
		checkState(board);
    	newPanel.setPanel();
    	newPanel.refreshBoard(board);
    	newPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {           
                //get the coordinate of x and y
                int x = e.getX(), y = e.getY();
                Point location = newPanel.getCoordinate(x, y);
                playGame(location.x, location.y, board);
                newPanel.refreshBoard(board);
                state = blackWin(board);
                if (state != "continue" && state != "pass") System.out.println("Over");
            }
        });
	}
	
	public void playGame(int x, int y, String[][] board) {
		if (state == "continue") {
		if (board[x][y] != "Å~") System.out.println("ÇªÇ±Ç…ãÓÇÕíuÇØÇ‹ÇπÇÒ");
		else {
			if (turn == "black") {
				board[x][y] = "Åú";
				for (int i=-1; i<2; i++) {
					for (int j=-1; j<2; j++) {
						if (board[x+i][y+j] == "Åõ") {
							int k = 1;
							while (board[x+i*k][y+j*k] == "Åõ") {
								k++;
								if (board[x+i*k][y+j*k] == "Åú") {
									while (k>1) {
										k--;
										board[x+i*k][y+j*k] = "Åú";
									}
								}
							}
						}
					}
				}
				turn = "white";
			}
			else if (turn == "white") {
				board[x][y] = "Åõ";
				for (int i=-1; i<2; i++) {
					for (int j=-1; j<2; j++) {
						if (board[x+i][y+j] == "Åú") {
							int k = 1;
							while (board[x+i*k][y+j*k] == "Åú") {
								k++;
								if (board[x+i*k][y+j*k] == "Åõ") {
									while (k>1) {
										k--;
										board[x+i*k][y+j*k] = "Åõ";
									}
								}
							}
						}
					}
				}
				turn = "black";
			}
		}
		checkState(board);
		printState(board);
		}
	}
	
	public String blackWin(String[][] board) {
		int black = 0;
		int white = 0;
		int pass = 1;
		int win = 1;
		for (int i=1; i<9; i++) {
			for (int j=1; j<9; j++) {
				if (board[i][j] == "Åõ") white++;
				if (board[i][j] == "Åú") black++;
				if (board[i][j] == "Å~") pass = 0;
				if (board[i][j] == "Å†" || board[i][j] == "Å~") win = 0;
			}
		}
		if (win == 1) {
			if (black > white) return "Black Win !!";
			if (black == white) return "draw";
			else return "White Win";
		}
		if (pass == 1) {
			if(turn == "white") turn = "black";
			else if(turn == "black") turn = "white";
			return "pass";
		}
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
	
	public void checkState(String[][] board) {
		// check the practicable points 
		for (int i=1; i<9; i++) {
			for (int j=1; j<9; j++) {
				if (board[i][j] == "Å~") board[i][j] = "Å†";
			}
		}
		for (int i=1; i<9; i++) {
			for (int j=1; j<9; j++) {
				if (board[i][j] == "Å†" && 
						checkPracticable(board, i, j, turn)) board[i][j] = "Å~";
			}
		}
		printState(board);
	}
	
	public boolean checkPracticable(String[][] board, int i, int j, String turn) {
		// check whether this point is practicable
		if (turn == "black") {
			for (int m=-1; m<2; m++) {
				for (int n=-1; n<2; n++) {
					if (board[i+m][j+n] == "Åõ") {
						int k = 1;
						while (board[i+k*m][j+k*n] == "Åõ") {
							k++;
							if (board[i+k*m][j+k*n] == "Åú") return true;
						}
					}
				}
			}
		}
		else {
			for (int m=-1; m<2; m++) {
				for (int n=-1; n<2; n++) {
					if (board[i+m][j+n] == "Åú") {
						int k = 1;
						while (board[i+k*m][j+k*n] == "Åú") {
							k++;
							if (board[i+k*m][j+k*n] == "Åõ") return true;
						}
					}
				}
			}
		}
		return false;
	}
	
}
