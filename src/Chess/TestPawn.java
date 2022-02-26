package Chess;

/* *********************************************************************
 * The TestPawn class test the pawn class to verify that all moves done
 * with the pawn are valid.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPawn {

    @Test
    public void initialPawnMoveTest() {

        // creates the game
        ChessModel model = new ChessModel();

        // Testing the initial white pawn first move with single space
        Move whiteInit1 = new Move( 6, 0, 5, 0);

        // Testing the initial white pawn first move with two spaces
        Move whiteInit2 = new Move(6, 0, 4, 0);

        // Testing the initial black pawn first move with one single space
        Move blackInit1 = new Move(1, 0, 2, 0);

        // Testing the initial black pawn first move with two spaces
        Move blackInit2 = new Move(1, 0, 3, 0);

        // Valid testing for initial move for white pawn (1 forward move)
        assertTrue(model.isValidMove(whiteInit1));

        // Valid test for initial move for white pawn (2 forward moves)
        assertTrue(model.isValidMove(whiteInit2));

        // Valid testing for initial move for black pawn (1 forward move)
        assertTrue(model.isValidMove(blackInit1));

        // Valid testing for initial move for black pawn (2 forward moves)
        assertTrue(model.isValidMove(blackInit2));

        /* **************************************************************************************
         * For complicated tests, we will need to do several moves to prep the test case
         ************************************************************************************** */

        // move white pawn from r = 6 & c = 0 to r = 5 & c = 0
        model.move(whiteInit1);

        // move white pawn from r = 6 & c = 0 to r = 4 & c = 0
        model.move(whiteInit2);

        // move white pawn from r = 6 & c = 0 to r = 3 & c = 0 which is invalid (moved too many spaces)
        Move whiteInit3 = new Move(6, 0, 3, 0);
        assertFalse(model.isValidMove(whiteInit3));

        // move white pawn from r = 2 & c = 1 to r = 4 & c = 2 which is invalid (moved to many spaces)
        Move whiteInit4 = new Move(6, 3, 3, 3);
        assertFalse(model.isValidMove(whiteInit4));
    }

    @Test
    public void pawnDiagonalTest() {
        // creates the game
        ChessModel model = new ChessModel();

        // Testing the initial white pawn first diagonal move to the right
        Move whiteInit1 = new Move(6, 0, 5, 1);

        // Testing the initial white pawn first diagonal move to the left
        Move whiteInit2 = new Move(6, 1, 5, 0);

        // Testing the initial black pawn first diagonal move to the right
        Move blackInit1 = new Move(1, 0, 2, 1);

        // Testing the initial white pawn first diagonal move to the left
        Move blackInit2 = new Move(1, 1, 2, 0);

        // checking diagonal moves when there is not an opponent's chess piece at the location
        assertFalse(model.isValidMove(whiteInit1));
        assertFalse(model.isValidMove(whiteInit2));
        assertFalse(model.isValidMove(blackInit1));
        assertFalse(model.isValidMove(blackInit2));

    }

    @Test
    public void pawnBackTest() {
        // creates the game
        ChessModel model = new ChessModel();

        // Testing the initial white pawn first move with single space
        Move whiteInit1 = new Move(4, 0, 7, 0);

        // Testing the initial black pawn first move with one single space
        Move blackInit1 = new Move(4, 0, 0, 0);

        // verifying that the pawn cannot move back
        assertFalse(model.isValidMove(whiteInit1));
        assertFalse(model.isValidMove(blackInit1));
    }

    @Test
    public void testDiagonalAtInitialMove() {
        // creates the game
        ChessModel model = new ChessModel();

        ChessPiece blackPawn = new Pawn(Player.BLACK);

        model.setPiece(5, 3,blackPawn);

        Move whitePawn = new Move(6,2,5, 3);

        assertTrue(model.isValidMove(whitePawn));

    }
}
