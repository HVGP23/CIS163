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

		int rowDiff = abs(move.fromRow - move.toRow);
		int colDiff = abs(move.fromColumn - move.toColumn);

		// One out of two possible moves for white piece
		if(rowDiff == 2 && colDiff == 1) {
			if(board[move.toRow][move.toColumn] == null)
				return true;
			else if (board[move.toRow][move.toColumn].player() == Player.BLACK)
				return true;
		}

		// Two out of two possible moves for this piece
//		if(rowDiff == 1 && colDiff == 2) {
//			if(board[move.toRow][move.toColumn] == null)
//				return true;
//			else if (board[move.toRow][move.toColumn].player() == Player.WHITE)
//				return true;
//		}



		return false;
		
	}

}
