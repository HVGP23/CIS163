package Chess;
import static java.lang.Math.abs;

public class King extends ChessPiece {

	public King(Player player) {
		super(player);
	}

	public String type() {
		return "King";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player());
		Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player());

		if(abs(move.toRow - move.fromRow) > 1 || abs(move.toColumn - move.fromColumn) > 1){
			return false;
		}

		return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
	}
}
