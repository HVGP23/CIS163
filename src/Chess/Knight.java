package Chess;

import static java.lang.Math.abs;

public class Knight extends ChessPiece {

	public Knight(Player player) {
		super(player);
	}

	public String type() {
		return "Knight";
	}

	public boolean isValidMove(Move move, IChessPiece[][] board){

		// difference between the rows
		int rowDiff0 = abs(move.fromRow - move.toRow);

		// difference between the columns
		int colDiff0 = abs(move.fromColumn - move.toColumn);

		// Up two over one OR down two over one
		if (rowDiff0 == 2 && colDiff0 == 1) {
			return true;
		}

		// up one over two OR down one over two
		if (rowDiff0 == 1 && colDiff0 == 2) {
			return true;
		}

		if (board[move.toRow][move.toColumn] == null) {
			return true;
		}

		// if there is a chess piece where we are moving to
		if (board[move.toRow][move.toColumn] != null) {

			// allows white rook to take only black chess pieces
			if (board[move.fromRow][move.fromColumn].player() == Player.WHITE &&
					board[move.toRow][move.toColumn].player() == Player.BLACK) {
				System.out.println("You took black chess piece");		// DELETE
				return true;
			}

			// allows black rook to take only white chess pieces
			 if ((board[move.fromRow][move.fromColumn].player() == Player.BLACK) &&
					(board[move.toRow][move.toColumn].player() == Player.WHITE)) {
				 System.out.println("You took white chess piece");        // DELETE
				 return true;
			 }
		}

		System.out.println(move);	// DELETE

		return false;
	}
}
