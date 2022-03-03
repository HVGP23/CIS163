package Chess;

/* *********************************************************************
 * The IChessModel is an abstract class that tells classes that implements
 * it what to do, and not how to do it. Therefore, each class must define
 * how to accomplish what the interface is telling it to do.
 *
 * Provided code by our instructor, Mr. Beach.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

public interface IChessModel {

        /**
         * Returns whether the game is complete.
         * Test Test Test
         * @return {@code true} if complete, {@code false} otherwise.
         * Did this work?
         */
        boolean isComplete();

        /**
         * Returns whether the piece at location {@code [move.fromRow, move.fromColumn]} is allowed to move to location
         * {@code [move.fromRow, move.fromColumn]}.
         *
         * @param move object describing the move to be made.
         * @return {@code true} if the proposed move is valid, {@code false} otherwise.
         * @throws IndexOutOfBoundsException if either {@code [move.fromRow, move.fromColumn]} or {@code [move.toRow,
         *                                   move.toColumn]} don't represent valid locations on the board.
         */
        boolean isValidMove(Move move);

        /**
         * Moves the piece from location {@code [move.fromRow, move.fromColumn]} to location {@code [move.fromRow,
         * move.fromColumn]}.
         *
         * @param move a object describing the move to be made.
         * @throws IndexOutOfBoundsException if either {@code [move.fromRow, move.fromColumn]} or {@code [move.toRow,
         *                                   move.toColumn]} don't represent valid locations on the board.
         */
        void move(Move move);

        /**
         * Report whether the current player p is in check.
         *
         * @param p the Player being checked
         * @return {@code true} if the current player is in check, {@code false} otherwise.
         */
        boolean inCheck(Player p);

        /**
         * Return the current player.
         *
         * @return the current player
         */
        Player currentPlayer();
    }

