package Chess;

/* *********************************************************************
 * The TestRook class test the rook class to verify that all moves done
 * with the pawn are valid.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestRook {

    // creates the game
    ChessModel model = new ChessModel();

    @Test
    public void moveAroundTheBoard () {

        // Move the pawns in front of the way of the rooks
        Move blackPawn = new Move(1, 0, 3, 0);
        model.move(blackPawn);
        Move blackPawn2 = new Move(1, 7, 3, 7);
        model.move(blackPawn2);
        Move whitePawn = new Move(6, 0, 4, 0);
        model.move(whitePawn);
        Move whitePawn2 = new Move(6,7,4,7);
        model.move(whitePawn2);

        // move the black rook from r = 0 c = 0 to r = 2 c = 0
        Move blackRook = new Move (0, 0, 2, 0);
        // validate that it can move there
        assertTrue(model.isValidMove(blackRook));
        // actually move it
        model.move(blackRook);
        // move the same rook from r = 2 c = 0 to r = 2 c = 4
        blackRook = new Move(2, 0, 2, 4);
        // validate that it can move there
        assertTrue(model.isValidMove(blackRook));
        // actually move it
        model.move(blackRook);

        // move the black rook from r = 0 c = 7 to r = 2 c = 7
        Move blackRook2 = new Move(0, 7, 2, 7);
        assertTrue(model.isValidMove(blackRook2));
    }


    @Test
    public void initialMove() {

        // Creating a move to move the pawn at r = 6 c = 0 out of the white rook's way
        Move whitePawn = new Move( 6, 0, 5, 0);
        model.move(whitePawn);

        // Creates the white left rook to move to r = 6 c = 0 where the pawn left a vacant spot
        Move whiteRookLeft = new Move(7, 0, 6, 0 );
        assertTrue(model.isValidMove(whiteRookLeft));

        // Creates the white left rook to move to r = 5 c = 0 where the white pawn is currently located
        Move whiteRookLeft2 = new Move(7, 0, 5, 0 );
        // This is a false move, so this test will pass. We can't move the rook where a white piece is located
        assertFalse(model.isValidMove(whiteRookLeft2));
        // move the left white pawn to the r = 6 c = 0
        model.move(whiteRookLeft);

        // asserts that there is null in r = 7 and c = 0
        assertNull(model.pieceAt(7,0));
    }

    @Test
    public void testJumpingPieces() {

        // create a new move to simulate the white rook located at r = 7 c = 0 to jump the white pawn in front
        Move jumpPawn = new Move(7, 0, 5, 0);

        // asserts false that the rook cannot jump the white pawn
        assertFalse(model.isValidMove(jumpPawn));

        // create a new move to simulate the black rook located at r = 0 c = 0 to jump the black pawn in front
        jumpPawn = new Move(0, 0, 2, 0);
        assertFalse(model.isValidMove(jumpPawn));

        // create a new move to simulate the black rook located at r = 0 c = 7 to jump the black pawn in front
        jumpPawn = new Move(0, 7, 2, 7);
        assertFalse(model.isValidMove(jumpPawn));
    }

    @Test
    public void testDiagonalMoves() {

        Move diagonalMove = new Move(0,0, 7, 7);
        assertFalse(model.isValidMove(diagonalMove));

        diagonalMove = new Move (0, 7, 7, 0);
        assertFalse(model.isValidMove(diagonalMove));

        diagonalMove = new Move(0, 7, 7, 0);
        assertFalse(model.isValidMove(diagonalMove));

        diagonalMove = new Move(7, 7, 0, 0);
        assertFalse(model.isValidMove(diagonalMove));
    }
}
