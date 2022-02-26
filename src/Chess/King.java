package Chess;

/* *********************************************************************
 * The King class extends ChessPiece. The King's constructor uses the
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

public class King extends ChessPiece {

	/**
	 * King Class Constructor
	 * @param player
	 */
	public King(Player player) {
		super(player);
	}

	/**
	 * The type method returns the name of the chess piece as a string
	 * @return the name of the chess piece as a string
	 */
	public String type() {
		return "King";
	}

	/**
	 * The isValidMove method verifies the that King's move is valid. King's have similar
	 * functionality as the queen, however it can only move one space at a time
	 * @param move  a object describing the move to be made.
	 * @param board the in which this piece resides.
	 * @return true if the king's move is valid
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		// inherits the bishop's characteristics
		Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player());

		// inherits the rook's characteristics
		Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player());

		// restricts the king piece to one spot at a time
		if(abs(move.toRow - move.fromRow) > 1 || abs(move.toColumn - move.fromColumn) > 1){
			return false;
		}


		return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
	}
}
