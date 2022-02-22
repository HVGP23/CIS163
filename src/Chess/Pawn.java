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

	/**
	 * The isValidMove determines if the pawm's move is valid
	 * @param move  a object describing the move to be made.
	 * @param board the in which this piece resides.
	 * @return
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// Will not allow the pawn to move from sideways
		if (move.fromRow == move.toRow) {
			System.out.println("Can't move sideways");
			return false;
		}

		// These if statements are only for the pawn's first move
		if (move.fromRow == 1 && player() == Player.BLACK) {
			if (move.toRow > 3) {
				System.out.println("You moved the pawn too much");		// DELETE
				return false;
			}

			// Cannot move diagonally unless there is a chess piece to be taken
			if (move.fromColumn != move.toColumn) {
				// However, if the pawn has not moved and it can take a chess piece
				// We will allow it here
				if (board[move.toRow][move.toColumn].player() == Player.WHITE) {
					System.out.println("You took White chess piece");
					return true;
				}
				System.out.println("Can't move diagonally");			// DELETE
				return false;
			}
		}

		// These if statements are only for the pawn's first move
		if (move.fromRow == 6 && player() == Player.WHITE) {
			if (move.toRow < 4) {
				System.out.println("You moved the pawn too much");		// DELETE
				return false;
			}
			// Cannot move diagonally unless there is a chess piece to be taken
			if (move.fromColumn != move.toColumn) {
				// However, if the pawn has not moved and it can take a chess piece
				// We will allow it.
				if (board[move.toRow][move.toColumn].player() == Player.BLACK) {
					System.out.println("You took a Black chess piece");
					return true;
				}
				System.out.println("Can't move diagonally");			// DELETE
				return false;
			}
		}

		// After the initial move, the white pawn cannot move more than 1 space
		if (move.fromRow - move.toRow > 1 && player() == Player.WHITE && move.fromRow != 6) {
			System.out.println("Can't move back white pawn");		// DELETE
			return false;
		}

		// After the initial move, the black pawn cannot move more than 1 space
		if (move.fromRow - move.toRow < -1 && player() == Player.BLACK && move.fromRow != 1) {
			System.out.println("Can't move back black pawn");		// DELETE
			return false;
		}

		// Allow for a diagonal move to occur (DOUBLE CHECK) If there isn't a piece there it currently crashes
		if (move.fromColumn - move.toColumn == 1) {
			if (board[move.toRow][move.toColumn].player() == Player.WHITE) {
				System.out.println("You took white chess piece");		// DELETE
				return true;
			}
		}

		// Allow for a diagonal move to occur (DOUBLE CHECK) If there isn't a piece there it currently crashes
		if (move.fromColumn - move.toColumn == -1) {
			if (board[move.toRow][move.toColumn].player() == Player.BLACK) {
				System.out.println("You took black chess piece");		// DELETE
				return true;
			}
		}

		// Doesn't allow the pawn to move column to column
		if (move.fromColumn - move.toColumn > 2 || move.fromColumn - move.toColumn < -2) {
			System.out.println("Can't move to another column");		// DELETE
			return false;
		}

		// Doesn't allow the white pawns to move back
		if(move.fromRow - move.toRow <= -1 && player() == Player.WHITE){
			System.out.println("Can't move back white pawn");		// DELETE
			return false;
		}

		// Doesn't allow the black pawns to move back
		if (move.fromRow - move.toRow >= 1 && player() == Player.BLACK){
			System.out.println("Can't move back black pawn");		// DELETE
			return false;
		}

		// Doesn't allow pawns to move forward if there is another chess piece present at that location on the board
		if (board[move.toRow][move.toColumn] != null) {
			System.out.println("There is a chess piece there");		// DELETE
			return false;
		}

		// Print of the move (DELETE when done testing)
		System.out.println(move);

		// if all the cases have been covered, the pawn can move
		return true;
	}
}
