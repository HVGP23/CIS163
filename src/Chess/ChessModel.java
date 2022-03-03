package Chess;

/* *********************************************************************
 * The ChessModel class implements IChessModel which is responsible for the
 * chess game infrastructure. The starter code was provided by our instructor
 * Mr. Branson Beach, and it was expanded on.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

import java.util.Objects;
import java.util.Stack;

public class ChessModel implements IChessModel {
	/** board is a 2D IChessPiece */
    private IChessPiece[][] board;
	/** player of the game */
	private Player player = Player.WHITE;
	/** The king's row location */
	private int kingRow;
	/** The king's column location */
	private int kingCol;
	/** */
	Stack<IChessPiece[][]> undoBoard = new Stack<>();

	/** ****************************************************************
	 * The ChessModel Constructor creates an 8 x 8 board and
	 * assigns chess pieces to their respective location
	 **************************************************************** */
	public ChessModel() {
		// instantiate a new 2D IChessPiece that is 8 x 8
		board = new IChessPiece[8][8];

		// sets the initial location for the black Rook
		board[0][0] = new Rook(Player.BLACK);
		// sets the initial location for the black Knight
		board[0][1] = new Knight(Player.BLACK);
		// sets the initial location for the black Bishop
		board[0][2] = new Bishop(Player.BLACK);
		// sets the initial location for the black Queen
		board[0][3] = new Queen(Player.BLACK);
		// sets the initial location for the black King
		board[0][4] = new King(Player.BLACK);
		// sets the initial location for the black Bishop
		board[0][5] = new Bishop(Player.BLACK);
		// sets the initial location for the black Knight
		board[0][6] = new Knight (Player.BLACK);
		// sets the initial location for the black Rook
		board[0][7] = new Rook(Player.BLACK);
		// sets the initial location for the black Pawns
		board[1][0] = new Pawn(Player.BLACK);
		board[1][1] = new Pawn(Player.BLACK);
		board[1][2] = new Pawn(Player.BLACK);
		board[1][3] = new Pawn(Player.BLACK);
		board[1][4] = new Pawn(Player.BLACK);
		board[1][5] = new Pawn(Player.BLACK);
		board[1][6] = new Pawn(Player.BLACK);
		board[1][7] = new Pawn(Player.BLACK);

		// sets the initial location for the white Rook
        board[7][0] = new Rook(Player.WHITE);
		// sets the initial location for the white Knight
        board[7][1] = new Knight(Player.WHITE);
		// sets the initial location for the white Bishop
        board[7][2] = new Bishop(Player.WHITE);
		// sets the initial location for the white Queen
        board[7][3] = new Queen(Player.WHITE);
		// sets the initial location for the white King
        board[7][4] = new King(Player.WHITE);
		// sets the initial location for the white Bishop
        board[7][5] = new Bishop(Player.WHITE);
		// sets the initial location for the white King
        board[7][6] = new Knight (Player.WHITE);
		// sets the initial location for the white Rook
        board[7][7] = new Rook(Player.WHITE);
		// sets the initial location for the white Pawns
		board[6][0] = new Pawn(Player.WHITE);
		board[6][1] = new Pawn(Player.WHITE);
		board[6][2] = new Pawn(Player.WHITE);
		board[6][3] = new Pawn(Player.WHITE);
		board[6][4] = new Pawn(Player.WHITE);
		board[6][5] = new Pawn(Player.WHITE);
		board[6][6] = new Pawn(Player.WHITE);
		board[6][7] = new Pawn(Player.WHITE);
	}


	/**	****************************************************************
	 * The isComplete method verifies that a player's king nor allied pieces
	 * is capable of moving and protecting their king
	 *
	 * @return returns if your king is going to be captured, or it is trapped
	 **************************************************************** */
	public boolean isComplete() {
		int count = 0;
		int fromRow = kingRow;
		int fromCol = kingCol;

		// check the rest of the allied pieces to see if they can defend the king
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// if the space is empty, move on
				if (board[i][j] != null) {

					// if the piece located at i and j is an allied piece
					if (board[i][j].player().equals(currentPlayer())) {

						Move checkMoves = new Move(i, j, fromRow - 1, fromCol - 1);
						Move checkMoves2 = new Move(i, j, fromRow - 1, fromCol);
						Move checkMoves3 = new Move(i, j, fromRow - 1, fromCol + 1);
						Move checkMoves4 = new Move(i, j, fromRow, fromCol - 1);
						Move checkMoves5 = new Move(i, j, fromRow, fromCol + 1);
						Move checkMoves6 = new Move(i, j, fromRow + 1, fromCol - 1);
						Move checkMoves7 = new Move(i, j, fromRow + 1, fromCol);
						Move checkMoves8 = new Move(i, j, fromRow + 1, fromCol + 1);

						if (board[i][j].isValidMove(checkMoves, board)) {
							// if the chess piece is owned by the other player, we can take it
							if (board[fromRow - 1][fromCol - 1].player().equals(player.next())) {
								// increment by 1 if not in check
								count++;

							} else {
								// if it is valid move to protect the king's NorthWest side
								move(checkMoves);
								addBoard();
								// once moved see if that we are still in check (false if we are not in check)
								if (!inCheck(currentPlayer())) {
									// increment by 1 if not in check
									count++;
								}
								undoBoard();
							}
						}

							if (board[i][j].isValidMove(checkMoves2, board)) {
								// if the chess piece is owned by the other player, we can take it
								if (board[fromRow - 1][fromCol].player().equals(player.next())) {
									// increment by 1 if not in check
									count++;
								} else {
									// if it is valid move to protect the king's North side
									move(checkMoves2);
									addBoard();
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									undoBoard();
								}

							if (board[i][j].isValidMove(checkMoves3, board)) {
								// if the chess piece is owned by the other player, we can take it
								if (board[fromRow - 1][fromCol + 1].player().equals(player.next())) {
									// increment by 1 if not in check
									count++;
								} else {
									// if it is valid move to protect the king's NorthWest side
									move(checkMoves3);
									addBoard();
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									undoBoard();
								}
							}

							if (board[i][j].isValidMove(checkMoves4, board)) {
								// if the chess piece is owned by the other player, we can take it
								if (board[fromRow][fromCol - 1].player().equals(player.next())) {
									// increment by 1 if not in check
									count++;
								} else {
									// if it is valid move to protect the king's West side
									move(checkMoves4);
									addBoard();
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									undoBoard();
								}
							}

							if (board[i][j].isValidMove(checkMoves5, board)) {
								// if the chess piece is owned by the other player, we can take it
								if (board[fromRow][fromCol + 1].player().equals(player.next())) {
									// increment by 1 if not in check
									count++;
								} else {
									// if it is valid move to protect the king's East side
									move(checkMoves5);
									addBoard();
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									undoBoard();
								}
							}

							if (board[i][j].isValidMove(checkMoves6, board)) {
								// if the chess piece is owned by the other player, we can take it
								if (board[fromRow + 1][fromCol - 1].player().equals(player.next())) {
									// increment by 1 if not in check
									count++;
								} else {
									// if it is valid move to protect the king's SouthEast side
									move(checkMoves6);
									addBoard();
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									undoBoard();
								}
							}
							if (board[i][j].isValidMove(checkMoves7, board)) {
								// if the chess piece is owned by the other player, we can take it
								if (board[fromRow + 1][fromCol].player().equals(player.next())) {
									// increment by 1 if not in check
									count++;
								} else {
									// if it is valid move to protect the king's South side
									move(checkMoves7);
									addBoard();
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									undoBoard();
								}
							}

							if (board[i][j].isValidMove(checkMoves8, board)) {
								// if the chess piece is owned by the other player, we can take it
								if (board[fromRow + 1][fromCol + 1].player().equals(player.next())) {
									// increment by 1 if not in check
									count++;
								} else {
									// if it is valid move to protect the king's SouthWest side
									move(checkMoves8);
									addBoard();
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									undoBoard();
								}
							}
						}
					}
				}
			}
		}

		return count == 0;
	}

	/** ****************************************************************
	 * The isValidMove method verifies that a move is valid
	 *
	 * @param move object describing the move to be made.
	 * @return returns true if the move is valid for the respective chess
	 * piece
	 **************************************************************** */
	public boolean isValidMove(Move move) {
		boolean valid = false;

		if (board[move.fromRow][move.fromColumn] != null)
			if (board[move.fromRow][move.fromColumn].isValidMove(move, board))
                return true;

		return valid;
	}

	/** ****************************************************************
	 * The move method updates the chess piece's location, once moved the
	 * "from" location is updated to null.
	 *
	 * @param move a object describing the move to be made.
	 **************************************************************** */
	public void move(Move move) {
		board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}

	/**
	 * The inCheck method checks to see if the current player's king is
	 * at risk of being captured or trapped. The next move from the current
	 * player must be to protect the king.
	 *
	 * @param p the Player being checked
	 * @return returns true if the current player's king is in danger of
	 * being captured or trapped.
	 */
	public boolean inCheck(Player p) {
		boolean inCheck = false;
		int count = 0;

		// get the king's current location belonging to the current player
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// if the space is empty, move on
				if (board[i][j] != null) {
					// checks to see if the piece at location i and j is a king
					if (Objects.equals(board[i][j].type(), "King")) {
						// check if the current location is owned by black
						if (board[i][j].player().equals(p)) {
							// assign the king's location
							kingRow = i;
							kingCol = j;
						}
					}
				}
			}
		}

		// check if the opposite player's pieces put the current player's king in check
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// if the space is empty, move on
				if (board[i][j] != null) {
					// check if the current location is owned the opposite player
					if (!board[i][j].player().equals(p)) {
						// create a new move with the "from" location being the current location of the piece
						// and the "to" location is the white king's location
						if (Objects.equals(board[i][j].type(), "Pawn")) {
							Move checkMove = new Move(i, j, kingRow, kingCol);
							// check if the move is valid
							if (board[i][j].isValidMove(checkMove, board)) {
								count++;
								System.out.println(count);
							}
						}

						if (Objects.equals(board[i][j].type(), "Knight")) {
							Move checkMove = new Move(i, j, kingRow, kingCol);
							// check if the move is valid
							if (board[i][j].isValidMove(checkMove, board)) {
								count++;
								System.out.println(count);
							}
						}

						if (Objects.equals(board[i][j].type(), "Bishop")) {
							Move checkMove = new Move(i, j, kingRow, kingCol);
							// check if the move is valid
							if (board[i][j].isValidMove(checkMove, board)) {
								count++;
								System.out.println(count);
							}
						}

						if (Objects.equals(board[i][j].type(), "Queen")) {
							Move checkMove = new Move(i, j, kingRow, kingCol);
							// check if the move is valid
							if (board[i][j].isValidMove(checkMove, board)) {
								count++;
								System.out.println(count);
							}
						}

						if (Objects.equals(board[i][j].type(), "King")) {
							Move checkMove = new Move(i, j, kingRow, kingCol);
							// check if the move is valid
							if (board[i][j].isValidMove(checkMove, board)) {
								count++;
								System.out.println(count);
							}
						}

						if (Objects.equals(board[i][j].type(), "Rook")) {
							Move checkMove = new Move(i, j, kingRow, kingCol);
							// check if the move is valid
							if (board[i][j].isValidMove(checkMove, board)) {
								count++;
								System.out.println(count);
							}
						}
						inCheck = count > 0;
					}
				}
			}
		}
		return inCheck;
	}

	/** ****************************************************************
	 * The currentPlayer() method returns the current player
	 * @return returns the current player
	 **************************************************************** */
	public Player currentPlayer() {
		return player;
	}

	/** ****************************************************************
	 * The numRows method returns the amount of rows
	 * @return returns the amount of rows
	 **************************************************************** */
	public int numRows() {
		return 8;
	}
	/** ****************************************************************
	 * The numColumns method returns the amount of columns
	 * @return returns the amount of columns
	 **************************************************************** */
	public int numColumns() {
		return 8;
	}

	/** ****************************************************************
	 * The pieceAt method returns the piece at a specified location
	 *
	 * @param row
	 * @param column
	 * @return returns the chess piece located at a specified location
	 **************************************************************** */
	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

	/** ****************************************************************
	 * The setNextPlayer method sets the next player
	 **************************************************************** */
	public void setNextPlayer() {
		player = player.next();
	}

	/** ****************************************************************
	 * The setPiece method sets a specified chess piece to a specified
	 * location on the chess board
	 *
	 * @param row
	 * @param column
	 * @param piece
	 **************************************************************** */
	public void setPiece(int row, int column, IChessPiece piece) {
		board[row][column] = piece;
	}

	/** ****************************************************************
	 * The copyBoard method creates a deep copy of the current board
	 * @param board
	 * @return the copyBoard method returns a cloned board
	 **************************************************************** */
	public IChessPiece[][] copyBoard(IChessPiece[][] board) {
		// create a new IChessPiece[][] field
		IChessPiece[][] clonedBoard = new IChessPiece[8][8];

		// creates a deep copy of the current board
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				clonedBoard[i][j] = board[i][j];
			}
		}
		return  clonedBoard;
	}

	/** ****************************************************************
	 * The setBoard method sets the board
	 **************************************************************** */
	public void setBoard(IChessPiece[][] updatedBoard) {
		board = updatedBoard;
	}

	/** ****************************************************************
	 * The addBoard method pushes the cloned copy of the board onto the
	 * top of the stack
	 **************************************************************** */
	public void addBoard() {
		undoBoard.push(copyBoard(board));
	}

	/** ****************************************************************
	 * The undoBoard method sets the board to its previous state
	 **************************************************************** */
	public void undoBoard() {
		if (!undoBoard.isEmpty()) {
			setBoard(undoBoard.pop());
		}
	}

	public void AI() {
		/*
		 * Write a simple AI set of rules in the following order. 
		 * a. Check to see if you are in check.
		 * 		i. If so, get out of check by moving the king or placing a piece to block the check 
		 * 
		 * b. Attempt to put opponent into check (or checkmate). 
		 * 		i. Attempt to put opponent into check without losing your piece
		 *		ii. Perhaps you have won the game. 
		 *
		 *c. Determine if any of your pieces are in danger, 
		 *		i. Move them if you can. 
		 *		ii. Attempt to protect that piece. 
		 *
		 *d. Move a piece (pawns first) forward toward opponent king 
		 *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
		 */

		}

}
