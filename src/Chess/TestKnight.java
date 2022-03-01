package Chess;

/* *********************************************************************
 * The TestKnight class test the knight class to verify that all moves done
 * with the knight are valid.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestKnight {

    ChessModel model = new ChessModel();

    @Test
    public void initialMove() {
        // Testing initial valid moves for both black and white knights
        Move blackKnight1 = new Move(0, 1, 2, 0 );
        Move blackKnight2 = new Move(0, 1, 2, 2 );
        Move blackKnight3 = new Move(0, 6, 2, 5 );
        Move blackKnight4 = new Move(0, 6, 2, 7 );

        Move whiteKnight1 = new Move(7, 1, 5, 0 );
        Move whiteKnight2 = new Move(7, 1, 5, 2 );
        Move whiteKnight3 = new Move(7, 6, 5, 5 );
        Move whiteKnight4 = new Move(7, 6, 5, 7 );

        // validate the first moves
        assertTrue(model.isValidMove(blackKnight1));
        assertTrue(model.isValidMove(blackKnight2));
        assertTrue(model.isValidMove(blackKnight3));
        assertTrue(model.isValidMove(blackKnight4));

        assertTrue(model.isValidMove(whiteKnight1));
        assertTrue(model.isValidMove(whiteKnight2));
        assertTrue(model.isValidMove(whiteKnight3));
        assertTrue(model.isValidMove(whiteKnight4));
    }

    @Test
    public void moveAroundTheBoard() {
        // create initial moves
        Move blackKnight1 = new Move(0, 1, 2, 0 );
        Move blackKnight2 = new Move(0, 6, 2, 5 );
        Move whiteKnight1 = new Move(7, 1, 5, 0 );
        Move whiteKnight2 = new Move(7, 6, 5, 7 );

        // move knights to one of two available initial locations
        model.move(blackKnight1);
        model.move(blackKnight2);
        model.move(whiteKnight1);
        model.move(whiteKnight2);

        // create secondary moves
        blackKnight1 = new Move(2, 0, 3, 2 );
        blackKnight2 = new Move(2, 5, 3, 7 );
        whiteKnight1 = new Move(5, 0, 4, 2 );
        whiteKnight2 = new Move(5, 7, 4, 5 );

        // validate the secondary moves
        assertTrue(model.isValidMove(blackKnight1));
        assertTrue(model.isValidMove(blackKnight2));
        assertTrue(model.isValidMove(whiteKnight1));
        assertTrue(model.isValidMove(whiteKnight2));

        // move knights to secondary locations
        model.move(blackKnight1);
        model.move(blackKnight2);
        model.move(whiteKnight1);
        model.move(whiteKnight2);

        // create third move that will fail
        blackKnight1 = new Move(3, 2, 3, 2 );
        blackKnight2 = new Move(3, 7, 3, 7 );
        whiteKnight1 = new Move(4, 2, 4, 2 );
        whiteKnight2 = new Move(4, 5, 4, 5 );

        // validate the third moves fail
        assertFalse(model.isValidMove(blackKnight1));
        assertFalse(model.isValidMove(blackKnight2));
        assertFalse(model.isValidMove(whiteKnight1));
        assertFalse(model.isValidMove(whiteKnight2));

        // create third move that will succeed
        blackKnight1 = new Move(3, 2, 5, 3 );
        blackKnight2 = new Move(3, 7, 5, 6 );
        whiteKnight1 = new Move(4, 2, 2, 1);
        whiteKnight2 = new Move(4, 5, 2, 6 );

        // validate the third move
        assertTrue(model.isValidMove(blackKnight1));
        assertTrue(model.isValidMove(blackKnight2));
        assertTrue(model.isValidMove(whiteKnight1));
        assertTrue(model.isValidMove(whiteKnight2));
    }

    @Test
    public void testDiagonal() {
        // Testing initial valid moves for both black and white knights
        Move blackKnight1 = new Move(0, 1, 1, 0 );
        Move blackKnight2 = new Move(0, 1, 1, 2 );
        Move blackKnight3 = new Move(0, 6, 1, 5 );
        Move blackKnight4 = new Move(0, 6, 1, 7 );

        Move whiteKnight1 = new Move(7, 1, 6, 0 );
        Move whiteKnight2 = new Move(7, 1, 6, 2 );
        Move whiteKnight3 = new Move(7, 6, 6, 5 );
        Move whiteKnight4 = new Move(7, 6, 6, 7 );

        // validate that the diagonal moves fail
        assertFalse(model.isValidMove(blackKnight1));
        assertFalse(model.isValidMove(blackKnight2));
        assertFalse(model.isValidMove(blackKnight3));
        assertFalse(model.isValidMove(blackKnight4));

        assertFalse(model.isValidMove(whiteKnight1));
        assertFalse(model.isValidMove(whiteKnight2));
        assertFalse(model.isValidMove(whiteKnight3));
        assertFalse(model.isValidMove(whiteKnight4));
    }

    @Test
    public void takeYourOwn() {
        // create initial moves
        Move blackKnight1 = new Move(0, 1, 2, 0 );
        Move blackKnight2 = new Move(0, 6, 2, 5 );
        Move whiteKnight1 = new Move(7, 1, 5, 0 );
        Move whiteKnight2 = new Move(7, 6, 5, 7 );

        // move knights to one of two available initial locations
        model.move(blackKnight1);
        model.move(blackKnight2);
        model.move(whiteKnight1);
        model.move(whiteKnight2);

        // create secondary moves that will fail
        blackKnight1 = new Move(2, 0, 1, 2 );
        blackKnight2 = new Move(2, 5, 1, 7 );
        whiteKnight1 = new Move(5, 0, 6, 2 );
        whiteKnight2 = new Move(5, 7, 6, 5 );

        // validate the that the knight cannot take allied pieces
        assertFalse(model.isValidMove(blackKnight1));
        assertFalse(model.isValidMove(blackKnight2));
        assertFalse(model.isValidMove(whiteKnight1));
        assertFalse(model.isValidMove(whiteKnight2));
    }

    @Test
    public void takeYourOpponent() {
        // create initial moves
        Move blackKnight1 = new Move(0, 1, 2, 0 );
        Move blackKnight2 = new Move(0, 6, 2, 5 );
        Move whiteKnight1 = new Move(7, 1, 5, 0 );
        Move whiteKnight2 = new Move(7, 6, 5, 7 );

        // move knights to one of two available initial locations
        model.move(blackKnight1);
        model.move(blackKnight2);
        model.move(whiteKnight1);
        model.move(whiteKnight2);

        // create secondary move
        blackKnight1 = new Move(2, 0, 3, 2 );
        blackKnight2 = new Move(2, 5, 3, 7 );
        whiteKnight1 = new Move(5, 0, 4, 2 );
        whiteKnight2 = new Move(5, 7, 4, 5 );

        // validate the secondary move
        assertTrue(model.isValidMove(blackKnight1));
        assertTrue(model.isValidMove(blackKnight2));
        assertTrue(model.isValidMove(whiteKnight1));
        assertTrue(model.isValidMove(whiteKnight2));

        // move knights to secondary locations
        model.move(blackKnight1);
        model.move(blackKnight2);
        model.move(whiteKnight1);
        model.move(whiteKnight2);

        // create third move
        blackKnight1 = new Move(3, 2, 5, 1 );
        blackKnight2 = new Move(3, 7, 5, 6 );
        whiteKnight1 = new Move(4, 2, 2, 3 );
        whiteKnight2 = new Move(4, 5, 2, 4 );

        // validate the third move
        assertTrue(model.isValidMove(blackKnight1));
        assertTrue(model.isValidMove(blackKnight2));
        assertTrue(model.isValidMove(whiteKnight1));
        assertTrue(model.isValidMove(whiteKnight2));

        // move knights to third locations
        model.move(blackKnight1);
        model.move(blackKnight2);
        model.move(whiteKnight1);
        model.move(whiteKnight2);

        // create fourth move
        blackKnight1 = new Move(5, 1, 6, 3);
        blackKnight2 = new Move(5, 6, 7, 5);
        whiteKnight1 = new Move(2, 3, 0, 4);
        whiteKnight2 = new Move(2, 4, 0, 5);

        // validate the fourth move
        assertTrue(model.isValidMove(blackKnight1));
        assertTrue(model.isValidMove(blackKnight2));
        assertTrue(model.isValidMove(whiteKnight1));
        assertTrue(model.isValidMove(whiteKnight2));
    }
}
