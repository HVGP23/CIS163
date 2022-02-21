package Chess;

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

	// determines if the move is valid for a pawn piece

	/**
	 * The isValidMove determines if the pawm's move is valid
	 * @param move  a object describing the move to be made.
	 * @param board the in which this piece resides.
	 * @return
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// Will not allow the pawn to move from sideways
		if (move.fromRow == move.toRow) {
			return false;
		}

		// These if statements are only for the pawn's first move
		if (move.fromRow == 1 && player() == Player.BLACK) {
			if (move.toRow > 3) {
				System.out.println("You moved the pawn too much");		// DELETE
				return false;
			}
			// We will need to adjust this when we want to take another piece diagonally
			if (move.fromColumn != move.toColumn) {
				System.out.println("Can't move diagonally");			// DELETE
				return false;
			}
		}

		// These if statements are only for the pawn's first move
		if (move.fromRow == 6 && player() == Player.WHITE) {
			if (move.toRow < 4) {
				System.out.println("You moved the pawn too much");			// DELETE
				return false;
			}
			if (move.fromColumn != move.toColumn) {
				System.out.println("Can't move diagonally");			// DELETE
				return false;
			}
		}


		// After the initial move, the pawn cannot move more than 1 space
		if (move.fromRow - move.toRow > 1 && player() == Player.WHITE && move.fromRow != 6) {
			return false;
		}

		// After the initial move, the pawn cannot move more than 1 space
		if (move.fromRow - move.toRow < -1 && player() == Player.BLACK && move.fromRow != 1) {
			return false;
		}

		if (move.fromColumn - move.toColumn > 1 || move.fromColumn - move.toColumn < -1) {
			return false;
		}

		// Here you go

		return true;
	}
}
