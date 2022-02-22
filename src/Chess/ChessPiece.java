package Chess;

public abstract class ChessPiece implements IChessPiece {

	// Instantiation of a Player Object named owner
	private Player owner;

	/**
	 * The ChessPiece method is a protected method that can only be accessed
	 * by its class and classes within the chess package. Sets the owner of the
	 * chess piece
	 *
	 * @param player
	 */
	protected ChessPiece(Player player) {
		this.owner = player;
	}

	/**
	 * The String method type() is an abstract method which creates a 
	 * "is a" relationship.
	 */
	public abstract String type();

	/**
	 * The Player method returns the owner of the chess piece
	 *
	 * @return owner; this method returns the owner of the chess piece
	 */
	public Player player() {
		return owner;
	}

	/**
	 * The isValidMove method verifies that the move the user does is in fact valid
	 *
	 * @param move  a object describing the move to be made.
	 * @param board the in which this piece resides.
	 * @return
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = false;

		return false;
	}
}
