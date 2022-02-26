package Chess;

import java.util.Objects;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
	private Player player;
	private Player player2;
	private int blackRow;
	private int blackCol;
	private int whiteRow;
	private int whiteCol;




	/**
	 * The ChessModel Constructor creates an 8 x 8 board
	 *
	 */
	public ChessModel() {
		board = new IChessPiece[8][8];

		player = Player.WHITE;
		player2 = Player.BLACK;

		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight (Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);

		board[1][0] = new Pawn(Player.BLACK);
		board[1][1] = new Pawn(Player.BLACK);
		board[1][2] = new Pawn(Player.BLACK);
		board[1][3] = new Pawn(Player.BLACK);
		board[1][4] = new Pawn(Player.BLACK);
		board[1][5] = new Pawn(Player.BLACK);
		board[1][6] = new Pawn(Player.BLACK);
		board[1][7] = new Pawn(Player.BLACK);

        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight (Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);

		board[6][0] = new Pawn(Player.WHITE);
		board[6][1] = new Pawn(Player.WHITE);
		board[6][2] = new Pawn(Player.WHITE);
		board[6][3] = new Pawn(Player.WHITE);
		board[6][4] = new Pawn(Player.WHITE);
		board[6][5] = new Pawn(Player.WHITE);
		board[6][6] = new Pawn(Player.WHITE);
		board[6][7] = new Pawn(Player.WHITE);
	}

	public boolean isComplete() {
		boolean valid = false;
		return valid;
	}

	public boolean isValidMove(Move move) {
		boolean valid = false;

		if (board[move.fromRow][move.fromColumn] != null)
			if (board[move.fromRow][move.fromColumn].isValidMove(move, board) == true)
                return true;

		return valid;
	}

	public void move(Move move) {
		board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}

	public boolean inCheck(Player p) {

			// get the king's current location
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					// skip through empty spaces
					while (board[i][j] == null) {

						if (i == 7 && j == 7) {
							// I need way to handle this case
							// return false;

						} else if (j < 7) {
							j++;
						} else if (i < 7 && j == 7) {
							i++;
							j = 0;
						}
					}

					if (board[i][j].type() == "King") {

						// check if the current location is owned by black
						if (board[i][j].player().equals(p)){
							// assign black king's location
							blackRow = i;
							blackCol = j;
						}

						// check if the current location is owned by white
						if (board[i][j].player().equals(p.next())){
							// assign white king's location
							whiteRow = i;
							whiteCol = j;
						}
					}
				}
			}

		System.out.println("Black King is at Row: " + blackRow + " Col: " + blackCol);
		System.out.println("White King is at Row: " + whiteRow + " Col: " + whiteCol);

		return false;
	}


	public Player currentPlayer() {
		return player;
	}

	public int numRows() {
		return 8;
	}

	public int numColumns() {
		return 8;
	}

	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

	public void setNextPlayer() {
		player = player.next();
	}

	public void setPiece(int row, int column, IChessPiece piece) {
		board[row][column] = piece;
	}

	public void AI() {
		/*
		 * Write a simple AI set of rules in the following order. 
		 * a. Check to see if you are in check.
		 * 		i. If so, get out of check by moving the king or placing a piece to block the check 
		 * 
		 * b. Attempt to put opponent into check (or checkmate). 
		 * 		i. Attempt to put opponent into check without losing your piece
		 *		ii. Perhaps you have won the game. 
		 *
		 *c. Determine if any of your pieces are in danger, 
		 *		i. Move them if you can. 
		 *		ii. Attempt to protect that piece. 
		 *
		 *d. Move a piece (pawns first) forward toward opponent king 
		 *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
		 */

		}
}
