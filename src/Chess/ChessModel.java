package Chess;

/* *********************************************************************
 * The ChessModel class implements IChessModel which is responsible for the
 * chess game infrastructure. The starter code was provided by our instructor
 * Mr. Branson Beach, and it was expanded on.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

import java.util.Objects;

public class ChessModel implements IChessModel {
    private final IChessPiece[][] board;
	private Player player = Player.WHITE;
	private int kingRow;
	private int kingCol;

	/**
	 * The ChessModel Constructor creates an 8 x 8 board
	 *
	 */
	public ChessModel() {
		board = new IChessPiece[8][8];

//		player = Player.WHITE;
//		player2 = Player.BLACK;
//		setNextPlayer();		// make the first player be white

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
		int count = 0;

		// Possible king moves
		Move kingMove1 = new Move (kingRow, kingCol, kingRow - 1, kingCol - 1);
		Move kingMove2 = new Move (kingRow, kingCol, kingRow - 1, kingCol);
		Move kingMove3 = new Move (kingRow, kingCol, kingRow - 1, kingCol + 1);

		Move kingMove4 = new Move (kingRow, kingCol, kingRow , kingCol - 1);
		Move kingMove5 = new Move (kingRow, kingCol, kingRow, kingCol + 1);

		Move kingMove6 = new Move (kingRow, kingCol, kingRow + 1, kingCol - 1);
		Move kingMove7 = new Move (kingRow, kingCol, kingRow - 1, kingCol);
		Move kingMove8 = new Move (kingRow, kingCol, kingRow - 1, kingCol + 1);

		// validate that the king can move top left
		if (board[kingRow][kingCol].isValidMove(kingMove1, board)) {
			// if it is valid move the king to that location
			move(kingMove1);
			// once moved see if that we are still in check
			if (!inCheck(player.next()))  { 		// MIGHT NEED TO BE NEXT PLAYER
				// increment by 1 if not in check
				count++;
			}
			// create new move to put the king back
			Move kingMove1Back = new Move(kingRow - 1, kingCol - 1, kingRow, kingCol);
			// move the king back
			move(kingMove1Back);
		}

		if (board[kingRow][kingCol].isValidMove(kingMove2, board)) {
			// if it is valid move the king to that location
			move(kingMove2);
			// once moved see if that we are still in check
			if (!inCheck(player.next()))  {		// MIGHT NEED TO BE NEXT PLAYER
				// increment by 1 if not in check
				count++;
			}
			// create new move to put the king back
			Move kingMove2Back = new Move(kingRow - 1, kingCol - 1, kingRow, kingCol);
			// move the king back
			move(kingMove2Back);
		}

		if (board[kingRow][kingCol].isValidMove(kingMove3, board)) {
			// if it is valid move the king to that location
			move(kingMove3);
			// once moved see if that we are still in check
			if (!inCheck(player.next()))  {		// MIGHT NEED TO BE NEXT PLAYER
				// increment by 1 if not in check
				count++;
			}
			// create new move to put the king back
			Move kingMove3Back = new Move(kingRow - 1, kingCol - 1, kingRow, kingCol);
			// move the king back
			move(kingMove3Back);
		}

		if (board[kingRow][kingCol].isValidMove(kingMove4, board)) {
			// if it is valid move the king to that location
			move(kingMove4);
			// once moved see if that we are still in check
			if (!inCheck(player.next())) {		// MIGHT NEED TO BE NEXT PLAYER
				// increment by 1 if not in check
				count++;
			}
			// create new move to put the king back
			Move kingMove4Back = new Move(kingRow - 1, kingCol - 1, kingRow, kingCol);
			// move the king back
			move(kingMove4Back);
		}

		if (board[kingRow][kingCol].isValidMove(kingMove5, board)) {
			// if it is valid move the king to that location
			move(kingMove5);
			// once moved see if that we are still in check
			if (!inCheck(player.next()))  {		// MIGHT NEED TO BE NEXT PLAYER
				// increment by 1 if not in check
				count++;
			}
			// create new move to put the king back
			Move kingMove4Back = new Move(kingRow - 1, kingCol - 1, kingRow, kingCol);
			// move the king back
			move(kingMove4Back);
		}

		if (board[kingRow][kingCol].isValidMove(kingMove6, board)) {
			// if it is valid move the king to that location
			move(kingMove6);
			// once moved see if that we are still in check
			if (!inCheck(player.next()))  {		// MIGHT NEED TO BE NEXT PLAYER
				// increment by 1 if not in check
				count++;
			}
			// create new move to put the king back
			Move kingMove6Back = new Move(kingRow - 1, kingCol - 1, kingRow, kingCol);
			// move the king back
			move(kingMove6Back);
		}

		if (board[kingRow][kingCol].isValidMove(kingMove7, board)) {
			// if it is valid move the king to that location
			move(kingMove7);
			// once moved see if that we are still in check
			if (!inCheck(player.next()))  {		// MIGHT NEED TO BE NEXT PLAYER
				// increment by 1 if not in check
				count++;
			}
			// create new move to put the king back
			Move kingMove7Back = new Move(kingRow - 1, kingCol - 1, kingRow, kingCol);
			// move the king back
			move(kingMove7Back);
		}

		if (board[kingRow][kingCol].isValidMove(kingMove8, board)) {
			// if it is valid move the king to that location
			move(kingMove8);
			// once moved see if that we are still in check
			if (!inCheck(player.next()))  {		// MIGHT NEED TO BE NEXT PLAYER
				// increment by 1 if not in check
				count++;
			}
			// create new move to put the king back
			Move kingMove8Back = new Move(kingRow - 1, kingCol - 1, kingRow, kingCol);
			// move the king back
			move(kingMove8Back);
		}

		return count > 0;
	}

	public boolean isValidMove(Move move) {
		boolean valid = false;

		if (board[move.fromRow][move.fromColumn] != null)
			if (board[move.fromRow][move.fromColumn].isValidMove(move, board))
                return true;

		return valid;
	}

	public void move(Move move) {
		board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}

	public boolean inCheck(Player p) {
		boolean inCheck = false;
		int count = 0;

		// get the king's current location belonging to the current player
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// if the space is empty, move on
				if (board[i][j] != null) {
					// checks to see if the piece at location i and j is a king
					if (Objects.equals(board[i][j].type(), "King")) {
						// check if the current location is owned by black
						if (board[i][j].player().equals(p)) {
							// assign the king's location
							kingRow = i;
							kingCol = j;
						}
					}
				}
			}
		}

//		// DELETE AFTER TESTING
//		System.out.println("The " + board[kingRow][kingCol].player().toString().toLowerCase(Locale.ROOT)
//				+ " King is located at " + "Row: " + kingRow + " Column: " + kingCol);

		// check if the opposite player's pieces put the current player's king in check
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// if the space is empty, move on
				if (board[i][j] != null) {
					// check if the current location is owned the opposite player
					if (!board[i][j].player().equals(p)) {
						// create a new move with the "from" location being the current location of the piece
						// and the "to" location is the white king's location
						if (Objects.equals(board[i][j].type(), "Pawn")) {
							Move checkMove = new Move(i, j, kingRow, kingCol);
							// check if the move is valid
							if (board[i][j].isValidMove(checkMove, board)) {
								count++;
								System.out.println(count);
							}
						}

						if (Objects.equals(board[i][j].type(), "Knight")) {
							Move checkMove = new Move(i, j, kingRow, kingCol);
							// check if the move is valid
							if (board[i][j].isValidMove(checkMove, board)) {
								count++;
								System.out.println(count);
							}
						}

						if (Objects.equals(board[i][j].type(), "Bishop")) {
							Move checkMove = new Move(i, j, kingRow, kingCol);
							// check if the move is valid
							if (board[i][j].isValidMove(checkMove, board)) {
								count++;
								System.out.println(count);
							}
						}

						if (Objects.equals(board[i][j].type(), "Queen")) {
							Move checkMove = new Move(i, j, kingRow, kingCol);
							// check if the move is valid
							if (board[i][j].isValidMove(checkMove, board)) {
								count++;
								System.out.println(count);
							}
						}

						if (Objects.equals(board[i][j].type(), "King")) {
							Move checkMove = new Move(i, j, kingRow, kingCol);
							// check if the move is valid
							if (board[i][j].isValidMove(checkMove, board)) {
								count++;
								System.out.println(count);
							}
						}

						if (Objects.equals(board[i][j].type(), "Rook")) {
							Move checkMove = new Move(i, j, kingRow, kingCol);
							// check if the move is valid
							if (board[i][j].isValidMove(checkMove, board)) {
								count++;
								System.out.println(count);
							}
						}
						inCheck = count > 0;
					}
				}
			}
		}
		return inCheck;
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
