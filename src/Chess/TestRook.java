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

import java.util.Locale;

import static org.junit.Assert.*;

public class TestRook {
    @Test
    public void initialRookMove() {
        // creates the game
        ChessModel model = new ChessModel();

        // Creating a move to move the pawn at r = 6 c = 0 out of the white rook's way
        Move whitePawn = new Move( 6, 0, 5, 0);
        model.move(whitePawn);

        // Creating a move for the first white rook at r = c = 0
        Move whiteRookLeft = new Move(7, 0, 6, 0 );
        model.move(whiteRookLeft);

        System.out.println(model.pieceAt(7, 0 ));
        System.out.println(model.pieceAt(6, 0 ).player().toString().toLowerCase(Locale.ROOT)
                + " " + model.pieceAt(6, 0 ).type());
        System.out.println(model.pieceAt(5, 0 ).player().toString().toLowerCase(Locale.ROOT)
                + " " + model.pieceAt(5, 0 ).type());

        // asserts that there is null in r = 7 and c = 0
        assertNull(model.pieceAt(7,0));

//         assertTrue(model.isValidMove(whiteRookLeft));

    }
}
