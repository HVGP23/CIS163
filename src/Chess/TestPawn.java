package Chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPawn {

    @Test
    public void InitialPawnMoveTest() {

        // creates the game
        ChessModel pawn = new ChessModel();

        // Testing the initial white pawn first move with one
        Move white0 = new Move(1, 0, 2, 0);
        Move white1 = new Move(1, 1, 2, 1);
        Move white2 = new Move(1, 2, 2, 2);
        Move white3 = new Move(1, 3, 2, 3);
        Move white4 = new Move(1, 4, 2, 4);
        Move white5 = new Move(1, 5, 2, 5);
        Move white6 = new Move(1, 6, 2, 6);
        Move white7 = new Move(1, 7, 2, 7);

        // Testing the initial black pawn first move with one single move
        Move black0 = new Move(6, 0, 5, 0);
        Move black1 = new Move(6, 1, 5, 1);
        Move black2 = new Move(6, 2, 5, 2);
        Move black3 = new Move(6, 3, 5, 3);
        Move black4 = new Move(6, 4, 5, 4);
        Move black5 = new Move(6, 5, 5, 5);
        Move black6 = new Move(6, 6, 5, 6);
        Move black7 = new Move(6, 7, 5, 7);

        // Valid testing for initial move for white pawn
        assertTrue(pawn.isValidMove(white0));
        assertTrue(pawn.isValidMove(white1));
        assertTrue(pawn.isValidMove(white2));
        assertTrue(pawn.isValidMove(white3));
        assertTrue(pawn.isValidMove(white4));
        assertTrue(pawn.isValidMove(white5));
        assertTrue(pawn.isValidMove(white6));
        assertTrue(pawn.isValidMove(white7));

        // Valid testing for initial move for black pawn
        assertTrue(pawn.isValidMove(black0));
        assertTrue(pawn.isValidMove(black1));
        assertTrue(pawn.isValidMove(black2));
        assertTrue(pawn.isValidMove(black3));
        assertTrue(pawn.isValidMove(black4));
        assertTrue(pawn.isValidMove(black5));
        assertTrue(pawn.isValidMove(black6));
        assertTrue(pawn.isValidMove(black7));

        //  For complicated tests, you'll do several moves to prep the test case
        // for example these moves will the white pawns to row 5 from 6
        pawn.move(white0);
        pawn.move(white1);
        pawn.move(white2);
        pawn.move(white3);
        pawn.move(white4);
        pawn.move(white5);
        pawn.move(white6);
        pawn.move(white7);
//
//        Move move2 = new Move(4, 5, 4, 2);
//        assertFalse(before.isValidMove(move2));

    }
}
