package Chess;

/* *********************************************************************
 * The rook class extends ChessPiece. The rook's constructor uses the
 * keyword super to call the parent class', ChessPiece constructor. The
 * class has two primary methods, the first one returns the type of chess piece
 * and the second returns if the player's move is a valid move for the
 * respective chess piece.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

public class Rook extends ChessPiece {

	/**
	 * Rook Class Constructor
	 * @param player
	 */
	public Rook(Player player) {
		super(player);
	}

	/**
	 * The type method returns the name of the chess piece as a string
	 * @return the name of the chess piece as a string
	 */
	public String type() {
		return "Rook";
	}

	/**
	 * The isValidMove method verifies the that rook's move is valid. Rook's cannot jump pieces
	 * nor move diagonally.
	 * @param move  a object describing the move to be made.
	 * @param board the in which this piece resides.
	 * @return true if the rook's move is valid
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// the rook cannot move diagonally
		if (move.fromRow != move.toRow && move.fromColumn != move.toColumn) {
			return false;
		}

		// checks to see if the rook is moving from left to right
		if (move.fromColumn < move.toColumn) {
			// doesn't allow rook to jump pieces from left to right
			for (int i = move.fromColumn + 1; i < move.toColumn; i++) {
				// checks in between the from and to columns (no jumping)
				if (board[move.fromRow][i] != null) {
					return false;
				}
			}
		}

		// checks to see if the rook is moving from right to left
		if (move.fromColumn > move.toColumn) {
			// doesn't allow rook to jump pieces from right to left
			for (int i = move.fromColumn - 1; i > move.toColumn; i--) {
				// checks in between the from and to columns (no jumping)
				if (board[move.fromRow][i] != null) {
					return false;
				}
			}
		}

		// checks to see if the rook is moving from top to dow
		if (move.fromRow < move.toRow) {
			// doesn't allow rook to jump pieces from top to down
			for (int i = move.fromRow + 1; i < move.toRow; i++) {
				// checks in between from and to rows for chess pieces (no jumping)
				if (board[i][move.toColumn] != null) {
					return false;
				}
			}
		}

		// checks to see if the rook is moving from down to up (Good one)
		if (move.fromRow > move.toRow) {
			// doesn't allow rook to jump pieces from down to up
			for (int i = move.fromRow - 1; i > move.toRow; i--) {
				// checks in between from and to rows for chess pieces (no jumping)
				if (board[i][move.toColumn] != null) {
					return false;
				}
			}
		}

		// allows rook to move to an empty spot
		if (board[move.toRow][move.toColumn] == null) {
			return true;
		}

		// allows white rook to take only black chess pieces
		if (board[move.fromRow][move.fromColumn].player() == Player.WHITE &&
				board[move.toRow][move.toColumn].player() == Player.BLACK) {
			return true;
		}

		// allows black rook to take only white chess pieces
		if ((board[move.fromRow][move.fromColumn].player() == Player.BLACK) &&
				(board[move.toRow][move.toColumn].player() == Player.WHITE)) {
			return true;
		}

		// Can't take a white chess piece with a white rook
		if (board[move.fromRow][move.fromColumn].player() == Player.WHITE &&
				board[move.toRow][move.toColumn].player() == Player.WHITE) {
			return false;
		}

		// Can't take a black chess piece with a black rook
		if ((board[move.fromRow][move.fromColumn].player() == Player.BLACK) &&
				(board[move.toRow][move.toColumn].player() == Player.BLACK)) {
			return false;
		}

		// if all the cases have been covered, the pawn can move
		return true;
	}
}