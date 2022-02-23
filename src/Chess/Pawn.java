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

		// These if statements are only for the black pawn's first move
		if (move.fromRow == 1 && player() == Player.BLACK) {
			if (move.toRow > 3) {
				System.out.println("You moved the black pawn too much");		// DELETE
				return false;
			}

//			if (board[move.toRow][move.toColumn].player() == Player.WHITE) {
//				System.out.println("Testing the initial diagonal black pawn move");
//				return true;
//			}

			// Cannot move diagonally unless there is a chess piece to be taken
			if (move.fromColumn != move.toColumn) {
				System.out.println("Can't move diagonally");            // DELETE
				return false;
			}
		}

		// These if statements are only for the white pawn's first move
		if (move.fromRow == 6 && player() == Player.WHITE) {
			if (move.toRow < 4) {
				System.out.println("You moved the white pawn more than two spots");		// DELETE
				return false;
			}

			// doesn't allow the pawn to move diagonally
			if (move.fromColumn != move.toColumn) {
				System.out.println("Can't move diagonally");            // DELETE
				return false;
			}
		}

		// After the initial move, the white pawn cannot move more than 1 space
		if (move.fromRow - move.toRow > 1 && player() == Player.WHITE && move.fromRow != 6) {
			System.out.println("Pawn cannot move more than one space after initial move");		// DELETE
			return false;
		}

		// After the initial move, the black pawn cannot move more than 1 space
		if (move.fromRow - move.toRow < -1 && player() == Player.BLACK && move.fromRow != 1) {
			System.out.println("Pawn cannot move more than one space after initial move");		// DELETE
			return false;
		}

		// Allow diagonal move for black pawn when there is a chess piece present
		// NEED MORE: we need to make sure that the chess piece it take is the opposite color
		if (move.fromColumn - move.toColumn == 1) {

			// does not allow the pawn to move diagonally if there isn't a piece there
			if (board[move.toRow][move.toColumn] == null) {
				System.out.println("There isn't a chess piece there");		// DELETE
				return false;
			}

			// allows the black pawn to take a white chess piece
			if (board[move.toRow][move.toColumn].player() == Player.WHITE) {
				System.out.println("You took white chess piece");		// DELETE
				return true;
			}
		}

		// Allow diagonal move  for white pawn when there is a chess piece present
		// NEED MORE: we need to make sure that the chess piece it take is the opposite color
		if (move.fromColumn - move.toColumn == -1) {

			// does not allow the pawn to move diagonally if there isn't a piece there
			if (board[move.toRow][move.toColumn] == null) {
				System.out.println("There isn't a chess piece there");		// DELETE
				return false;
			}

			// allows white pawn to take a black chess piece
			if (board[move.toRow][move.toColumn].player() == Player.BLACK) {
				System.out.println("You took black chess piece");		// DELETE
				return true;
			}
		}

		// Doesn't allow the pawns to move column to column
		if (move.fromColumn - move.toColumn > 2 || move.fromColumn - move.toColumn < -2) {
			System.out.println("Can't move to another column");		// DELETE
			return false;
		}

		// Doesn't allow the white pawns to move back
		if(move.fromRow - move.toRow <= -1 && player() == Player.WHITE){
			System.out.println("white pawn cannot move back a space");		// DELETE
			return false;
		}

		// Doesn't allow the black pawns to move back
		if (move.fromRow - move.toRow >= 1 && player() == Player.BLACK){
			System.out.println("black pawn cannot move back a space");		// DELETE
			return false;
		}

		// Doesn't allow pawns to move forward if there is another chess piece present at that location on the board
		// NEED HELP: This needs to be refined
		if (board[move.toRow][move.toColumn] != null) {
			System.out.println("There is a chess piece in front of you");		// DELETE
			return false;
		}

		// Print of the move (DELETE when done testing)
		System.out.println(move);

		// if all the cases have been covered, the pawn can move
		return true;
	}
}
