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

public class ChessModel implements IChessModel {
    private final IChessPiece[][] board;
	private Player player = Player.WHITE;
	private int kingRow;
	private int kingCol;

	/**
	 * The ChessModel Constructor creates an 8 x 8 board
	 *
	 */
	public ChessModel() {
		board = new IChessPiece[8][8];

//		player = Player.WHITE;
//		player2 = Player.BLACK;
//		setNextPlayer();		// make the first player be white

		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight (Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);

		board[1][0] = new Pawn(Player.BLACK);
		board[1][1] = new Pawn(Player.BLACK);
		board[1][2] = new Pawn(Player.BLACK);
		board[1][3] = new Pawn(Player.BLACK);
		board[1][4] = new Pawn(Player.BLACK);
		board[1][5] = new Pawn(Player.BLACK);
		board[1][6] = new Pawn(Player.BLACK);
		board[1][7] = new Pawn(Player.BLACK);

        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight (Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);

		board[6][0] = new Pawn(Player.WHITE);
		board[6][1] = new Pawn(Player.WHITE);
		board[6][2] = new Pawn(Player.WHITE);
		board[6][3] = new Pawn(Player.WHITE);
		board[6][4] = new Pawn(Player.WHITE);
		board[6][5] = new Pawn(Player.WHITE);
		board[6][6] = new Pawn(Player.WHITE);
		board[6][7] = new Pawn(Player.WHITE);
	}

	public boolean isComplete() {
		int count = 0;
		int fromRow = kingRow;
		int fromCol = kingCol;

		// Possible king moves
		Move NorthWest 	= new Move (fromRow, fromCol, fromRow - 1, fromCol - 1);
		Move North 		= new Move (fromRow, fromCol, fromRow - 1, fromCol);
		Move NorthEast 	= new Move (fromRow, fromCol, fromRow - 1, fromCol + 1);

		Move West 		= new Move (fromRow, fromCol, fromRow , fromCol - 1);
		Move East 		= new Move (fromRow, fromCol, fromRow, fromCol + 1);

		Move SouthEast 	= new Move (fromRow, fromCol, fromRow + 1, fromCol - 1);
		Move South 		= new Move (fromRow, fromCol, fromRow + 1, fromCol);
		Move SouthWest 	= new Move (fromRow, fromCol, fromRow + 1, fromCol + 1);

		// validate that the king can move NorthWest
		if (board[fromRow][fromCol].isValidMove(NorthWest, board)) {
			if (board[fromRow - 1][fromCol].player() != null) {
				// if the chess piece is owned by the other player, we can take it
				if (board[fromRow - 1][fromCol - 1].player().equals(player.next())) {
//				if (inCheck(currentPlayer()))  {
					// increment by 1 if not in check
					count++;
//				}
				} else {
					// if it is valid move the king to that location
					move(NorthWest);
					// once moved see if that we are still in check
					if (inCheck(currentPlayer())) {
						// increment by 1 if not in check
						count++;
					}
					// create new move to put the king back
					Move kingMove1Back = new Move(fromRow - 1, fromCol - 1, fromRow, fromCol);
					// move the king back
					move(kingMove1Back);
				}
			}
		}

		// validate that the king can move North
		if (board[fromRow][fromCol].isValidMove(North, board)) {
			if (board[fromRow - 1][fromCol].player() == null || board[fromRow - 1][fromCol].player() != null) {
			// if the chess piece is owned by the other player, we can take it
			if (board[fromRow - 1][fromCol].player().equals(player.next())) {
//				if (inCheck(currentPlayer())) {
//					// increment by 1 if not in check
				count++;
//				}
			} else {
				// if it is valid move the king to that location
				move(North);
				// once moved see if that we are still in check
				if (!inCheck(currentPlayer())) {
					// increment by 1 if not in check
					count++;
				}
				// create new move to put the king back
				Move kingMove2Back = new Move(fromRow - 1, fromCol, fromRow, fromCol);
				// move the king back
				move(kingMove2Back);
			}
		}
		}

		// validate that the king can move NorthEast
		if (board[fromRow][fromCol].isValidMove(NorthEast, board)) {
			if (board[fromRow - 1][fromCol + 1].player() != null) {
				// if the chess piece is owned by the other player, we can take it
				if (board[fromRow - 1][fromCol + 1].player().equals(player.next())) {
//				if (inCheck(currentPlayer())) {
//					// increment by 1 if not in check
					count++;
//				}
				} else {
					// if it is valid move the king to that location
					move(NorthEast);
					// once moved see if that we are still in check
					if (!inCheck(currentPlayer())) {
						// increment by 1 if not in check
						count++;
					}
					// create new move to put the king back
					Move kingMove3Back = new Move(fromRow - 1, fromCol + 1, fromRow, fromCol);
					// move the king back
					move(kingMove3Back);
				}
			}
		}

		// validate that the king can move West
		if (board[fromRow][fromCol].isValidMove(West, board)) {
			if (board[fromRow][fromCol - 1].player() != null) {
				// if the chess piece is owned by the other player, we can take it
				if (board[fromRow][fromCol - 1].player().equals(player.next())) {
//				if (inCheck(currentPlayer())) {
//					// increment by 1 if not in check
					count++;
//				}
				} else {
					// if it is valid move the king to that location
					move(West);
					// once moved see if that we are still in check
					if (!inCheck(currentPlayer())) {
						// increment by 1 if not in check
						count++;
					}
					// create new move to put the king back
					Move kingMove4Back = new Move(fromRow, fromCol - 1, fromRow, fromCol);
					// move the king back
					move(kingMove4Back);
				}
			}
		}

		// validate that the king can move East
		if (board[fromRow][fromCol].isValidMove(East, board)) {
			if (board[fromRow][fromCol + 1].player() != null) {
				// if the chess piece is owned by the other player, we can take it
				if (board[fromRow][fromCol + 1].player().equals(player.next())) {
//				if (inCheck(currentPlayer())) {
//					// increment by 1 if not in check
					count++;
//				}
				} else {
					// if it is valid move the king to that location
					move(East);
					// once moved see if that we are still in check
					if (!inCheck(currentPlayer())) {
						// increment by 1 if not in check
						count++;
					}
					// create new move to put the king back
					Move kingMove4Back = new Move(fromRow, fromCol + 1, fromRow, fromCol);
					// move the king back
					move(kingMove4Back);
				}
			}
		}

		// validate that the king can move SouthEast
		if (board[fromRow][fromCol].isValidMove(SouthEast, board)) {
			if (board[fromRow + 1][fromCol - 1].player() != null) {
				// if the chess piece is owned by the other player, we can take it
				if (board[fromRow + 1][fromCol - 1].player().equals(player.next())) {
//				if (inCheck(currentPlayer())) {
//					// increment by 1 if not in check
					count++;
//				}
				} else {
					// if it is valid move the king to that location
					move(SouthEast);
					// once moved see if that we are still in check
					if (!inCheck(currentPlayer())) {
						// increment by 1 if not in check
						count++;
					}
					// create new move to put the king back
					Move kingMove6Back = new Move(fromRow + 1, fromCol - 1, fromRow, fromCol);
					// move the king back
					move(kingMove6Back);
				}
			}
		}

		// validate that the king can move South
		if (board[fromRow][fromCol].isValidMove(South, board)) {
			if (board[fromRow + 1][fromCol].player() != null) {
				// if the chess piece is owned by the other player, we can take it
				if (board[fromRow + 1][fromCol].player().equals(player.next())) {
//				if (inCheck(currentPlayer())) {
//					// increment by 1 if not in check
					count++;
//				}
				} else {
					// if it is valid move the king to that location
					move(South);
					// once moved see if that we are still in check
					if (!inCheck(currentPlayer())) {
						// increment by 1 if not in check
						count++;
					}
					// create new move to put the king back
					Move kingMove7Back = new Move(fromRow + 1, fromCol, fromRow, fromCol);
					// move the king back
					move(kingMove7Back);
				}
			}
		}

		// validate that the king can move SouthWest
		if (board[fromRow][fromCol].isValidMove(SouthWest, board)) {
			if (board[fromRow + 1][fromCol + 1].player() != null) {
				// if the chess piece is owned by the other player, we can take it
				if (board[fromRow + 1][fromCol + 1].player().equals(player.next())) {
//				if (inCheck(currentPlayer())) {
//					// increment by 1 if not in check
					count++;
//				}
				} else {
					// if it is valid move the king to that location
					move(SouthWest);
					// once moved see if that we are still in check
					if (!inCheck(currentPlayer())) {
						// increment by 1 if not in check
						count++;
					}
					// create new move to put the king back
					Move kingMove7Back = new Move(fromRow + 1, fromCol + 1, fromRow, fromCol);
					// move the king back
					move(kingMove7Back);
				}
			}
		}

		// check the rest of the allied pieces to see if they can defend the king
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// if the space is empty, move on
				if (board[i][j] != null) {
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
								// once moved see if that we are still in check
								if (!inCheck(currentPlayer())) {
									// increment by 1 if not in check
									count++;
								}
								// create new move to put the piece located at i, j
								Move checkMovesBack = new Move(fromRow - 1, fromCol - 1, i, j);
								// move the piece located at i, j
								move(checkMovesBack);
							}


							if (board[i][j].isValidMove(checkMoves2, board)) {
								// if the chess piece is owned by the other player, we can take it
								if (board[fromRow - 1][fromCol].player().equals(player.next())) {
									// increment by 1 if not in check
									count++;
								} else {
									// if it is valid move to protect the king's North side
									move(checkMoves2);
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									// create new move to put the piece located at i, j
									Move checkMoves2Back = new Move(fromRow - 1, fromCol, i, j);
									// move the piece located at i, j
									move(checkMoves2Back);
								}
							}

							if (board[i][j].isValidMove(checkMoves3, board)) {
								// if the chess piece is owned by the other player, we can take it
								if (board[fromRow - 1][fromCol + 1].player().equals(player.next())) {
									// increment by 1 if not in check
									count++;
								} else {
									// if it is valid move to protect the king's NorthWest side
									move(checkMoves3);
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									// create new move to put the piece located at i, j
									Move checkMoves3Back = new Move(fromRow - 1, fromCol + 1, i, j);
									// move the piece located at i, j
									move(checkMoves3Back);
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
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									// create new move to put the piece located at i, j
									Move checkMoves4Back = new Move(fromRow, fromCol - 1, i, j);
									// move the piece located at i, j
									move(checkMoves4Back);
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
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									// create new move to put the piece located at i, j
									Move checkMoves5Back = new Move(fromRow, fromCol + 1, i, j);
									// move the piece located at i, j
									move(checkMoves5Back);
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
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									// create new move to put the piece located at i, j
									Move checkMoves6Back = new Move(fromRow + 1, fromCol - 1, i, j);
									// move the piece located at i, j
									move(checkMoves6Back);
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
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									// create new move to put the piece located at i, j
									Move checkMoves7Back = new Move(fromRow + 1, fromCol, i, j);
									// move the piece located at i, j
									move(checkMoves7Back);
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
									// once moved see if that we are still in check
									if (!inCheck(currentPlayer())) {
										// increment by 1 if not in check
										count++;
									}
									// create new move to put the piece located at i, j
									Move checkMoves8Back = new Move(fromRow + 1, fromCol + 1, i, j);
									// move the piece located at i, j
									move(checkMoves8Back);
								}
							}
						}
					}
				}
			}
		}

		return count == 0;
	}

	public boolean isValidMove(Move move) {
		boolean valid = false;

		if (board[move.fromRow][move.fromColumn] != null)
			if (board[move.fromRow][move.fromColumn].isValidMove(move, board))
                return true;

		return valid;
	}

	public void move(Move move) {
		board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}

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

//		// DELETE AFTER TESTING
//		System.out.println("The " + board[kingRow][kingCol].player().toString().toLowerCase(Locale.ROOT)
//				+ " King is located at " + "Row: " + kingRow + " Column: " + kingCol);

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

	public Player currentPlayer() {
		return player;
	}

	public int numRows() {
		return 8;
	}

	public int numColumns() {
		return 8;
	}

	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

	public void setNextPlayer() {
		player = player.next();
	}

	public void setPiece(int row, int column, IChessPiece piece) {
		board[row][column] = piece;
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
