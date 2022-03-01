package Chess;

/* *********************************************************************
 * The Knight class extends ChessPiece. The Knight's constructor uses the
 * keyword super to call the parent class', ChessPiece constructor. The
 * class has two primary methods, the first one returns the type of chess piece
 * and the second returns if the player's move is a valid move for the
 * respective chess piece.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

import static java.lang.Math.abs;

public class Knight extends ChessPiece {

	/**
	 * Knight's Class Constructor
	 * @param player
	 */
	public Knight(Player player) {
		super(player);
	}

	/**
	 * The type method returns the name of the chess piece as a string
	 * @return the name of the chess piece as a string
	 */
	public String type() {
		return "Knight";
	}

	/**
	 * The isValidMove method verifies the that knight's move is valid. The knight
	 * has distinct movement. It can move up/down two spaces and one space over or
	 * it can move left/right two spaces and one space over
	 * @param move  a object describing the move to be made.
	 * @param board the in which this piece resides.
	 * @return true if the knight's move is valid
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board){

		// difference between the rows
		int rowDiff0 = abs(move.fromRow - move.toRow);

		// difference between the columns
		int colDiff0 = abs(move.fromColumn - move.toColumn);

		// Up two over one OR down two over one
		if (rowDiff0 == 2 && colDiff0 == 1) {
//			System.out.println(move);	// DELETE

			// allows knight to move to an empty spot
			if (board[move.toRow][move.toColumn] == null) {
				// Print of the move (DELETE when done testing)
//				System.out.println(move);
				return true;
			}

			// allows white rook to take only black chess pieces
			if (board[move.fromRow][move.fromColumn].player() == Player.WHITE &&
					board[move.toRow][move.toColumn].player() == Player.BLACK) {
//				System.out.println("You took black chess piece");		// DELETE
				return true;
			}

			// allows black rook to take only white chess pieces
			if ((board[move.fromRow][move.fromColumn].player() == Player.BLACK) &&
					(board[move.toRow][move.toColumn].player() == Player.WHITE)) {
//				System.out.println("You took white chess piece");		// DELETE
				return true;
			}

			// Can't take a white chess piece with a white rook
			if (board[move.fromRow][move.fromColumn].player() == Player.WHITE &&
					board[move.toRow][move.toColumn].player() == Player.WHITE) {
//				System.out.println("Can't move there");		// DELETE
				return false;
			}

			// Can't take a black chess piece with a black rook
			if ((board[move.fromRow][move.fromColumn].player() == Player.BLACK) &&
					(board[move.toRow][move.toColumn].player() == Player.BLACK)) {
//				System.out.println("Can't move there");		// DELETE
				return false;
			}

			return true;
		}
		// up one over two OR down one over two
		else if (rowDiff0 == 1 && colDiff0 == 2) {
//			System.out.println(move);	// DELETE

			// allows knight to move to an empty spot
			if (board[move.toRow][move.toColumn] == null) {
				// Print of the move (DELETE when done testing)
//				System.out.println(move);
				return true;
			}

			// allows white rook to take only black chess pieces
			if (board[move.fromRow][move.fromColumn].player() == Player.WHITE &&
					board[move.toRow][move.toColumn].player() == Player.BLACK) {
//				System.out.println("You took black chess piece");		// DELETE
				return true;
			}

			// allows black rook to take only white chess pieces
			if ((board[move.fromRow][move.fromColumn].player() == Player.BLACK) &&
					(board[move.toRow][move.toColumn].player() == Player.WHITE)) {
//				System.out.println("You took white chess piece");		// DELETE
				return true;
			}

			// Can't take a white chess piece with a white rook
			if (board[move.fromRow][move.fromColumn].player() == Player.WHITE &&
					board[move.toRow][move.toColumn].player() == Player.WHITE) {
//				System.out.println("Can't move there");		// DELETE
				return false;
			}

			// Can't take a black chess piece with a black rook
			if ((board[move.fromRow][move.fromColumn].player() == Player.BLACK) &&
					(board[move.toRow][move.toColumn].player() == Player.BLACK)) {
//				System.out.println("Can't move there");		// DELETE
				return false;
			}

			return true;

		} else {
//			System.out.println("You can't take your own piece");		// DELETE
			return false;
		}
	}
}
