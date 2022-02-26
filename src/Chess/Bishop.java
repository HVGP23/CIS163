package Chess;

/* *********************************************************************
 * The Bishop class extends ChessPiece. The Bishop's constructor uses the
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

public class Bishop extends ChessPiece {

	/**
	 * Bishop Class Constructor
	 * @param player
	 */
	public Bishop(Player player) {
		super(player);
	}

	/**
	 * The type method returns the name of the chess piece as a string
	 * @return the name of the chess piece as a string
	 */
	public String type() {
		return "Bishop";
	}

	/**
	 * The isValidMove method verifies the that Bishop's move is valid. Bishop's can only
	 * move diagonally and cannot jump pieces.
	 * @param move  a object describing the move to be made.
	 * @param board the in which this piece resides.
	 * @return true if the bishop's move is valid
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// Stops the bishop from jumping pieces when moving northeast
		if (move.fromRow > move.toRow && move.fromColumn < move.toColumn) {
			int j = move.fromColumn;

			for (int i = move.fromRow - 1; i > move.toRow; i--) {
				j++;
				if (board[i][j] != null) {
					System.out.println("Can't jump pieces");
					return false;
				}
			}
		}

		// Stops the bishop from jumping pieces when moving southwest
		if (move.fromRow < move.toRow && move.fromColumn > move.toColumn) {
			int j = move.fromColumn;

			for (int i = move.fromRow + 1; i < move.toRow; i++) {
				j--;
				if (board[i][j] != null) {
					System.out.println("Can't jump pieces");
					return false;
				}
			}
		}

		// This prevents the bishop from jumping any piece when moving southeast
		if (move.fromRow < move.toRow && move.fromColumn < move.toColumn) {
			int j = move.fromColumn;

			for (int i = move.fromRow + 1; i < move.toRow; i++) {
				j++;
				if (board[i][j] != null) {
					System.out.println("Can't jump pieces");
					return false;
				}
			}
		}

		if (move.fromRow > move.toRow && move.fromColumn > move.toColumn) {
			int j = move.fromColumn;

			for (int i = move.fromRow - 1; i > move.toRow; i--) {
				j--;
				if (board[i][j] != null) {
					System.out.println("Can't jump pieces");
					return false;
				}
			}
		}

		// For the Bishop, the diagonals length and width must be equal. This statement checks that
		if(abs(move.toRow - move.fromRow) != abs(move.toColumn - move.fromColumn))
			return false;

		// This statement makes it so the white team can't remove its own pieces, or jump
		if(board[move.toRow][move.toColumn] != null &&
				board[move.toRow][move.toColumn].player() == Player.WHITE && player() == Player.WHITE)
			return false;

		// This statement allows the white team to destroy black pieces
		if(board[move.toRow][move.toColumn] != null &&
				board[move.toRow][move.toColumn].player() == Player.BLACK && player() == Player.WHITE) {
			System.out.println("You destroyed a black piece");
			return true;
		}

		// This statement makes it so the black team can't remove its own pieces, or jump
		if(board[move.toRow][move.toColumn] != null &&
				board[move.toRow][move.toColumn].player() == Player.BLACK && player() == Player.BLACK)
			return false;

		// This statement allows the black team to destroy black pieces
		if(board[move.toRow][move.toColumn] != null &&
				board[move.toRow][move.toColumn].player() == Player.WHITE && player() == Player.BLACK) {
			System.out.println("You destroyed a white piece");
			return true;
		}

		return true;
	}
}