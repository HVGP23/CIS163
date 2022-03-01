package Chess;

/* *********************************************************************
 * The ChessPanel class extends JPanel which is responsible for the creating
 * the chess board, pieces, and setting the pieces in their respective locations.
 *  The starter code was provided by our instructor Mr. Branson Beach,
 * and it was expanded on.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.*;

public class ChessPanel extends JPanel {

    private JButton[][] board;
    private ChessModel model;

    // White Chess Pieces
    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;

    // Black Chess Pieces
    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    // verifies that the selected piece is the "from" location
    private boolean firstTurnFlag;

    // private class fields
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;

    private listener listener;

    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();


        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 5, 5));

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE) {
                    placeWhitePieces(r, c);
                } else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    placeBlackPieces(r, c);
                }

                // set the background color
                setBackGroundColor(r, c);
                // needed to do this so the setBackGround would actually show
                board[r][c].setOpaque(true);
                // needed to do this so the setBackGround would actually show
                board[r][c].setBorderPainted(false);
                boardpanel.add(board[r][c]);
            }
        }

        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel);
        firstTurnFlag = true;
    }

    /**
     * The setBackGroundColor() method set the color of the board. The bottom left spot, respective to the player's
     * view must be black.
     *
     * @param r
     * @param c
     */
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            // set the background color to GVSU Blue
            board[r][c].setBackground(Color.decode("#264391"));
            // Only for mac to visual the spaces, comment out for non-mac users
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /**
     * The placeWhitePieces method places the white chess pieces in their respective locations. Its parameters are
     * the rows and columns
     *
     * @param r
     * @param c
     */
    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    /**
     * The placeBlackPieces method places the black chess pieces in their respective locations. Its parameters are
     * the rows and columns
     *
     * @param r; r is the row value
     * @param c; c is the column value
     */
    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    /**
     * The createIcons() method creates the chess piece icons. We are providing the
     * PNG files from the resource folder "res"
     */
    private void createIcons() {
        // Sets the Image for white chess pieces
        wRook = new ImageIcon("res/wRook.png");
        wBishop = new ImageIcon("res/wBishop.png");
        wQueen = new ImageIcon("res/wQueen.png");
        wKing = new ImageIcon("res/wKing.png");
        wPawn = new ImageIcon("res/wPawn.png");
        wKnight = new ImageIcon("res/wKnight.png");

        // Sets the Image for the black chess pieces
        bRook = new ImageIcon("res/bRook.png");
        bBishop = new ImageIcon("res/bBishop.png");
        bQueen = new ImageIcon("res/bQueen.png");
        bKing = new ImageIcon("res/bKing.png");
        bPawn = new ImageIcon("res/bPawn.png");
        bKnight = new ImageIcon("res/bKnight.png");
    }

    /**
     * The displayBoard() method updates the board
     */
    private void displayBoard() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(wPawn);
                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(wKing);
                } else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(bKing);
                }
        }
        repaint();
    }

    // inner class that represents action listener for buttons
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
                for (int r = 0; r < model.numRows(); r++) {
                    for (int c = 0; c < model.numColumns(); c++) {
                        if (board[r][c] == event.getSource())
                            // initial click of the piece to obtain the "from" location
                            if (firstTurnFlag) {
//                                System.out.println(model.currentPlayer().toString().toLowerCase(Locale.ROOT));
                                fromRow = r;
                                fromCol = c;
                                firstTurnFlag = false;
                                // The "to" location of the piece selected
                            } else {
                                toRow = r;
                                toCol = c;
                                firstTurnFlag = true;
                                Move m = new Move(fromRow, fromCol, toRow, toCol);
                                // we cannot move a null piece to another location doesn't make sense
                                if (model.pieceAt(fromRow, fromCol) != null) {
                                    // only allows the current player to go
                                    if (model.pieceAt(fromRow, fromCol).player().equals(model.currentPlayer())) {
                                        // if the move is valid
                                        if ((model.isValidMove(m))) {
                                            // move the chess piece
                                            model.move(m);
                                            displayBoard();
                                            // next player is up
                                            model.setNextPlayer();
                                            // after the player moves, the next player must check to see if they are in check
                                            if (model.inCheck(model.currentPlayer())) {
                                                // adds the black king icon to the option pane
                                                if (model.currentPlayer() == Player.BLACK) {
                                                    JOptionPane.showMessageDialog(null,
                                                            model.currentPlayer().toString().toLowerCase(Locale.ROOT) +
                                                                    " king is danger of being captured!", "black king",
                                                            JOptionPane.INFORMATION_MESSAGE, bKing);
                                                }
                                                // adds the white king icon to the option pane
                                                if (model.currentPlayer() == Player.WHITE) {
                                                    JOptionPane.showMessageDialog(null,
                                                            model.currentPlayer().toString().toLowerCase(Locale.ROOT) +
                                                                    " king is danger of being captured!", "white king",
                                                            JOptionPane.INFORMATION_MESSAGE, wKing);
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                    }
                }
        }
    }
}