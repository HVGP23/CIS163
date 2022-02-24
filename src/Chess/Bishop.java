package Chess;

import static java.lang.Math.abs;

public class Bishop extends ChessPiece {

	public Bishop(Player player) {
		super(player);
	}

	public String type() {
		return "Bishop";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// For the Bishop, the diagonals length and width must be equal. This statement checks that
		if(abs(move.toRow - move.fromRow) != abs(move.toColumn - move.fromColumn))
			return false;

		// This statement makes it so the white team can't remove its own pieces, or jump
		if(board[move.toRow][move.toColumn] != null && board[move.toRow][move.toColumn].player() == Player.WHITE && player() == Player.WHITE)
			return false;

		// This statement allows the white team to destroy black pieces
		if(board[move.toRow][move.toColumn] != null && board[move.toRow][move.toColumn].player() == Player.BLACK && player() == Player.WHITE)
			return true;

		// This statement makes it so the black team can't remove its own pieces, or jump
		if(board[move.toRow][move.toColumn] != null && board[move.toRow][move.toColumn].player() == Player.BLACK && player() == Player.BLACK)
			return false;

		// This statement allows the black team to destroy black pieces
		if(board[move.toRow][move.toColumn] != null && board[move.toRow][move.toColumn].player() == Player.WHITE && player() == Player.BLACK)
			return true;

		return true;


	}
}
