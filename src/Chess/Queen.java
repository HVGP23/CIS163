package Chess;

/* *********************************************************************
 * The queen class extends ChessPiece. The queen's constructor uses the
 * keyword super to call the parent class', ChessPiece constructor. The
 * class has two primary methods, the first one returns the type of chess piece
 * and the second returns if the player's move is a valid move for the
 * respective chess piece.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

public class Queen extends ChessPiece {

	/**
	 * Queen's Class Constructor
	 * @param player
	 */
	public Queen(Player player) {
		super(player);

	}

	/**
	 * The type method returns the name of the chess piece as a string
	 * @return the name of the chess piece as a string
	 */
	public String type() {
		return "Queen";
		
	}

	/**
	 * The isValidMove method verifies the that queen's move is valid. The queen's only
	 * move restriction is that it can not jump pieces
	 * @param move  a object describing the move to be made.
	 * @param board the in which this piece resides.
	 * @return true if the queen's move is valid
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		// inherits the bishop's attributes
		Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player());

		// inherits the rook's attributes
		Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player());

		return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
	}
}
