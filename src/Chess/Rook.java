package Chess;

public class Rook extends ChessPiece {

	public Rook(Player player) {
		super(player);
	}

	public String type() {
		return "Rook";
	}
	
	// determines if the move is valid for a rook piece
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// the rook cannot move diagonally
		if (move.fromRow != move.toRow && move.fromColumn != move.toColumn) {
			System.out.println("Rook cannot move diagonally");
			return false;
		}

		// checks to see if the rook is moving from left to right
		if (move.fromColumn < move.toColumn) {
			// doesn't allow rook to jump pieces from left to right
			for (int i = move.fromColumn + 1; i < move.toColumn; i++) {
				// checks in between the from and to columns (no jumping)
				if (board[move.fromRow][i] != null) {
					System.out.println("Can't jump pieces");
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
					System.out.println("Can't jump pieces");
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
					System.out.println("Can't jump pieces");
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
					System.out.println("Can't jump pieces");
					return false;
				}
			}
		}

		// allows rook to move to an empty spot
		if (board[move.toRow][move.toColumn] == null) {
			// Print of the move (DELETE when done testing)
			System.out.println(move);
			return true;
		}

		// allows white rook to take only black chess pieces
		if (board[move.fromRow][move.fromColumn].player() == Player.WHITE &&
				board[move.toRow][move.toColumn].player() == Player.BLACK) {
			System.out.println("You took black chess piece");		// DELETE
			return true;
		}

		// allows black rook to take only white chess pieces
		if ((board[move.fromRow][move.fromColumn].player() == Player.BLACK) &&
				(board[move.toRow][move.toColumn].player() == Player.WHITE)) {
			System.out.println("You took white chess piece");		// DELETE
			return true;
		}

		// Can't take a white chess piece with a white rook
		if (board[move.fromRow][move.fromColumn].player() == Player.WHITE &&
				board[move.toRow][move.toColumn].player() == Player.WHITE) {
			System.out.println("Can't move there");		// DELETE
			return false;
		}

		// Can't take a black chess piece with a black rook
		if ((board[move.fromRow][move.fromColumn].player() == Player.BLACK) &&
				(board[move.toRow][move.toColumn].player() == Player.BLACK)) {
			System.out.println("Can't move there");		// DELETE
			return false;
		}

		// if all the cases have been covered, the pawn can move
		return true;
	}
}
