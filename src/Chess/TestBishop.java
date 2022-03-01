package Chess;

/* *********************************************************************
 * The TestBishop class test the bishop class to verify that all moves done
 * with the bishop are valid.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestBishop {

    // create a chess model
    ChessModel model = new ChessModel();

    // Moving own diagonal pawn from each bishop
    Move blackPawn1 = new Move(1, 1, 2, 1 );
    Move blackPawn2 = new Move(1, 6, 2, 6 );

    Move whitePawn1 = new Move(6, 1, 5, 1 );
    Move whitePawn2 = new Move(6, 4, 5, 4 );


    // Testing initial invalid moves for both black and white bishops
    Move blackBishop1 = new Move(0, 2, 1, 1 );
    Move blackBishop2 = new Move(0, 5, 1, 6 );


    Move whiteBishop1 = new Move(7, 2, 6, 1 );
    Move whiteBishop2 = new Move(7, 5, 6, 4 );


    @Test
    public void testInitialMove() {

        assertTrue(model.isValidMove(blackPawn1));
        assertTrue(model.isValidMove(blackPawn2));
        assertTrue(model.isValidMove(whitePawn1));
        assertTrue(model.isValidMove(whitePawn2));

        model.move(blackPawn1);
        model.move(blackPawn2);
        model.move(whitePawn1);
        model.move(whitePawn2);

        assertTrue(model.isValidMove(blackBishop1));
        assertTrue(model.isValidMove(blackBishop2));
        assertTrue(model.isValidMove(whiteBishop1));
        assertTrue(model.isValidMove(whiteBishop2));
    }

    @Test
    public void moveAroundTheBoard() {

        // move bishops
        model.move(blackBishop1);
        model.move(blackBishop2);
        model.move(whiteBishop1);
        model.move(whiteBishop2);


        // Testing post initial moves
        blackBishop1 = new Move(1, 1, 2, 0);
        blackBishop2 = new Move(1, 6, 3, 4);

        whiteBishop1 = new Move(6, 1, 5, 2 );
        whiteBishop2 = new Move(6, 4, 5, 5 );

        // validate movind around the board
        assertTrue(model.isValidMove(blackBishop1));
        assertTrue(model.isValidMove(blackBishop2));
        assertTrue(model.isValidMove(whiteBishop1));
        assertTrue(model.isValidMove(whiteBishop2));
    }

    @Test
    public void TakeYourOwn() {
        blackBishop1 = new Move(2, 0, 1, 0);
        blackBishop2 = new Move(3, 6, 0, 6);

        whiteBishop1 = new Move(5, 2, 6, 2);
        whiteBishop2 = new Move(5, 5, 6, 5 );

        // validate that bishops can't take their own piece
        assertFalse(model.isValidMove(blackBishop1));
        assertFalse(model.isValidMove(blackBishop2));
        assertFalse(model.isValidMove(whiteBishop1));
        assertFalse(model.isValidMove(whiteBishop2));
    }

}
