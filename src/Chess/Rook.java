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
		boolean valid = true;


//		// if the rook doesn't within the row return false
//		if (board[move.toRow][move.toColumn] != board[move.toRow][move.fromColumn]) {
//			System.out.println("Only move within the row");
//			valid = false;
//		}
//
//		// if the rook doesn't within the column return false
//		if (board[move.toRow][move.toColumn] != board[move.fromColumn][move.toColumn]) {
//			System.out.println("Only move within the column");
//			valid = false;
//		}

		if (board[move.toRow][move.toColumn] != null) {
			System.out.println("Can't move there");
			valid = false;
		}


        return valid;
		
	}
	
}
