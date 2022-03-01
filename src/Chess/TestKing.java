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

public class TestKing {

    @Test
    public void movingForward() {
        //Creates game
        ChessModel model = new ChessModel();

        //Testing the white King does jump over pieces
        Move kingWhite1 = new Move(7, 4, 6, 4);

        //Testing the white King does not move two forward
        Move kingWhite2 = new Move(7, 4, 5, 4);

        //Testing that black King does not jump over pieces
        Move kingBlack1 = new Move(0, 4, 1, 4);

        //Testing the white King does not move two forward
        Move kingBlack2 = new Move(0, 4, 2, 4);

        //Checking invalid moves
        assertFalse(model.isValidMove(kingWhite1));
        assertFalse(model.isValidMove(kingWhite2));
        assertFalse(model.isValidMove(kingBlack1));
        assertFalse(model.isValidMove(kingBlack2));
    }


    @Test
    public void movingBack() {
        //Creates game
        ChessModel model = new ChessModel();

        //Testing the white King does jump over pieces
        Move kingWhite1 = new Move(5, 2, 7, 2);

        //Testing the white King does not move two back
        Move kingWhite2 = new Move(4, 6, 6, 4);

        //Testing that black King does not jump over pieces
        Move kingBlack1 = new Move(3, 4, 1, 4);

        //Testing the white King does not move two back
        Move kingBlack2 = new Move(2, 5, 1, 5);

        //Checking invalid moves
        assertFalse(model.isValidMove(kingWhite1));
        assertFalse(model.isValidMove(kingWhite2));
        assertFalse(model.isValidMove(kingBlack1));
        assertFalse(model.isValidMove(kingBlack2));
    }

    @Test
    public void movingLeft() {
        //Creates game
        ChessModel model = new ChessModel();

        //Sets white piece
        ChessPiece kingWhite = new King(Player.WHITE);
        model.setPiece(5,6, kingWhite);

        //Moves piece
        Move kingWhiteLeft = new Move(5,6,5,4);

        //Checking invalid moves
        assertFalse(model.isValidMove(kingWhiteLeft));

    }

    @Test
    public void movingRight() {
        //Creates game
        ChessModel model = new ChessModel();

        //Sets white piece
        ChessPiece kingWhite = new King(Player.WHITE);
        model.setPiece(4,1, kingWhite);

        //Moves piece
        Move kingWhiteRight = new Move(4,1,4,3);

        //Checking invalid moves
        assertFalse(model.isValidMove(kingWhiteRight));

    }

    @Test
    public void movingRight2() {
        //Creates the game
        ChessModel model = new ChessModel();

        //Sets black piece
        ChessPiece kingBlack = new King(Player.BLACK);
        model.setPiece(1,4, kingBlack);

        //Moves piece
        Move kingBlackRight = new Move(1,4,1,6);

        //Checking invalid moves
        assertFalse(model.isValidMove(kingBlackRight));

    }

    @Test
    public void movingLeft2() {
        //Creates game
        ChessModel model = new ChessModel();

        //Sets blacks piece
        ChessPiece kingBlack = new King(Player.BLACK);
        model.setPiece(2,3, kingBlack);

        //Moves piece
        Move kingBlackLeft = new Move(2,3,2,1);

        //Checking invalid moves
        assertFalse(model.isValidMove(kingBlackLeft));

    }

}
