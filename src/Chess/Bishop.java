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
