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
	public boolean inCheck = false;



	/**
	 * The ChessModel Constructor creates an 8 x 8 board
	 *
	 */
	public ChessModel() {
		board = new IChessPiece[8][8];

		player = Player.WHITE;
		player2 = Player.BLACK;
		setNextPlayer();		// make the first player be white

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
		if (getKingsLocation(p)) {
			System.out.println("King is in check");
		}
		return getKingsLocation(p);
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

	/**
	 * The getKingsLocation method tracks the location of both kings as they move
	 * around the board. This is utilized to verify if either king is in check.
	 *
	 * @param p
	 */
	public boolean getKingsLocation(Player p) {


		// get the king's current location
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// if the space is empty, move on
				if (board[i][j] != null) {
					// checks to see if the piece at location i and j is a king
					if (Objects.equals(board[i][j].type(), "King")) {

						// check if the current location is owned by black
						if (board[i][j].player().equals(p)) {
							// assign black king's location
							blackRow = i;
							blackCol = j;
						}

						// check if the current location is owned by white
						if (board[i][j].player().equals(p.next())) {
							// assign white king's location
							whiteRow = i;
							whiteCol = j;
						}
					}
				}
			}
		}

		System.out.println("Black King is at Row: " + blackRow + " Col: " + blackCol
				+ " \nWhite King is at Row: " + whiteRow + " Col: " + whiteCol);

		// WORKING HERE

		// get the rook's current location
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// if the space is empty, move on
				if (board[i][j] != null) {
					// checks to see if the piece at location i and j is a rook
					if (Objects.equals(board[i][j].type(), "Rook")) {
						// check if the current location is owned by black
						if (pieceAt(i,j).player().equals(p)) {
							Move blackRookMove = new Move(i, j, whiteRow, whiteCol);
							// returns if the black rook can capture the white king
						inCheck = board[i][j].isValidMove(blackRookMove, board);
						}
						// check if the current location is owned by white
						if (pieceAt(i, j).player().equals(p.next())) {
							Move whiteRookMove = new Move(i, j, blackRow, blackCol);
							// returns if the white rook can take the black king
							inCheck = board[i][j].isValidMove(whiteRookMove, board);
						}
					}
				}
			}
		}

//		// get the bishop's current location
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[i].length; j++) {
//				// if the space is empty, move on
//				if (board[i][j] != null) {
//					// checks to see if the piece at location i and j is a rook
//					if (Objects.equals(board[i][j].type(), "Bishop")) {
//						// check if the current location is owned by black
//						if (pieceAt(i,j).player().equals(p)) {
//							Move blackBishopMove = new Move(i, j, whiteRow, whiteCol);
//							// returns if the black rook can capture the white king
//							inCheck = board[i][j].isValidMove(blackBishopMove, board);
//						}
//						// check if the current location is owned by white
//						if (pieceAt(i, j).player().equals(p.next())) {
//							Move whiteBishopMove = new Move(i, j, blackRow, blackCol);
//							// returns if the white rook can take the black king
//							inCheck = board[i][j].isValidMove(whiteBishopMove, board);
//						}
//					}
//				}
//			}
//		}

//		// get the knight's current location
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[i].length; j++) {
//				// if the space is empty, move on
//				if (board[i][j] != null) {
//					// checks to see if the piece at location i and j is a rook
//					if (Objects.equals(board[i][j].type(), "Knight")) {
//						// check if the current location is owned by black
//						if (pieceAt(i,j).player().equals(p)) {
//							Move blackKnightMove = new Move(i, j, whiteRow, whiteCol);
//							// returns if the black rook can capture the white king
//							inCheck = board[i][j].isValidMove(blackKnightMove, board);
//						}
//						// check if the current location is owned by white
//						if (pieceAt(i, j).player().equals(p.next())) {
//							Move whiteKnightMove = new Move(i, j, blackRow, blackCol);
//							// returns if the white rook can take the black king
//							inCheck = board[i][j].isValidMove(whiteKnightMove, board);
//						}
//					}
//				}
//			}
//		}

//		// get the queen's current location
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[i].length; j++) {
//				// if the space is empty, move on
//				if (board[i][j] != null) {
//					// checks to see if the piece at location i and j is a rook
//					if (Objects.equals(board[i][j].type(), "Queen")) {
//						// check if the current location is owned by black
//						if (pieceAt(i,j).player().equals(p)) {
//							Move blackQueenMove = new Move(i, j, whiteRow, whiteCol);
//							// returns if the black rook can capture the white king
//							inCheck = board[i][j].isValidMove(blackQueenMove, board);
//						}
//						// check if the current location is owned by white
//						if (pieceAt(i, j).player().equals(p.next())) {
//							Move whiteQueenMove = new Move(i, j, blackRow, blackCol);
//							// returns if the white rook can take the black king
//							inCheck = board[i][j].isValidMove(whiteQueenMove, board);
//						}
//					}
//				}
//			}
//		}

		return inCheck;
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
