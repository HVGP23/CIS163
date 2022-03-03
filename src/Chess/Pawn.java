package Chess;

/* *********************************************************************
 * The pawn class extends ChessPiece. The pawn's constructor uses the
 * keyword super to call the parent class', ChessPiece constructor. The
 * class has two primary methods, the first one returns the type of chess piece
 * and the second returns if the player's move is a valid move for the
 * respective chess piece.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

public class Pawn extends ChessPiece {
	/**
	 * The Pawn Constructor uses the ChessPiece constructor (parent class)
	 * @param player
	 */
	public Pawn(Player player) {
		super(player);
	}

	/**
	 * The String type() returns the name of the chess piece, in this case it is a pawn
	 * @return returns the name of the chess piece
	 */
	public String type() {
		return "Pawn";
	}

	/**
	 * The isValidMove determines if the pawn's move is valid
	 * @param move  a object describing the move to be made.
	 * @param board the in which this piece resides.
	 * @return if the move is valid
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// will not allow the pawn to be "moved" to its current location
		if (move.fromRow == move.toRow && move.fromColumn == move.toColumn) {
			return false;
		}

		// Will not allow the pawn to move from sideways
		if (move.fromRow == move.toRow) {
			return false;
		}

		// These if statements are only for the black pawn's first move
		if (move.fromRow == 1 && player() == Player.BLACK) {
			// doesn't allow the black pawn to move more than two spaces
			if (move.toRow > 3) {
				return false;
			}
		}

		// These if statements are only for the white pawn's first move
		if (move.fromRow == 6 && player() == Player.WHITE) {
			// doesn't allow the white pawn to move more than two spaces
			if (move.toRow < 4) {
				return false;
			}
		}

		// After the initial move, the white pawn cannot move more than 1 space
		if (move.fromRow - move.toRow > 1 && player() == Player.WHITE && move.fromRow != 6) {
			return false;
		}

		// After the initial move, the black pawn cannot move more than 1 space
		if (move.fromRow - move.toRow < -1 && player() == Player.BLACK && move.fromRow != 1) {
			return false;
		}

		// Doesn't allow the white pawns to move back
		if(move.fromRow - move.toRow <= -1 && player() == Player.WHITE){
			return false;
		}

		// Doesn't allow the black pawns to move back
		if (move.fromRow - move.toRow >= 1 && player() == Player.BLACK){
			return false;
		}

		// Allow diagonal move for pawns when there is an opponent's chess piece present
		if ((move.fromColumn - move.toColumn == -1) || (move.fromColumn - move.toColumn == 1)) {
			// does not allow the pawn to move diagonally if there isn't a piece there
			if (board[move.toRow][move.toColumn] == null) {
				return false;
			}
			// allows white pawn to take only black chess pieces
			if (board[move.fromRow][move.fromColumn].player() == Player.WHITE &&
					board[move.toRow][move.toColumn].player() == Player.BLACK) {
				return true;
			}
			// allows black pawn to take only white chess pieces
			if ((board[move.fromRow][move.fromColumn].player() == Player.BLACK) &&
					(board[move.toRow][move.toColumn].player() == Player.WHITE)) {
				return true;
			}
		}

		// Doesn't allow the pawns to move column to column
		if (move.fromColumn - move.toColumn > 2 || move.fromColumn - move.toColumn < -2) {
			return false;
		}

		// Doesn't allow pawns to move forward if there is another chess piece present at that location on the board
		if (board[move.toRow][move.toColumn] != null) {
			return false;
		}

		// if all the cases have been covered, the pawn can move
		return true;
	}
}