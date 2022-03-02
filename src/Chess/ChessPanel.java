package Chess;

/* *********************************************************************
 * The ChessPanel class extends JPanel which is responsible for the creating
 * the chess board, pieces, and setting the pieces in their respective locations.
 *  The starter code was provided by our instructor Mr. Branson Beach,
 * and it was expanded on.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, and Hector Garcia
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

    /** 2D array of JButtons */
    private JButton[][] board;

    /** ChessModel variable */
    private ChessModel model;

    /** Image to showcase CheckMate */
    private ImageIcon checkmate;

    /** Image to showcase white Rook */
    private ImageIcon wRook;
    /** Image to showcase white Bishop */
    private ImageIcon wBishop;
    /** Image to showcase white Queen */
    private ImageIcon wQueen;
    /** Image to showcase white King */
    private ImageIcon wKing;
    /** Image to showcase white Pawn*/
    private ImageIcon wPawn;
    /** Image to showcase white Knight */
    private ImageIcon wKnight;

    /** Image to showcase black Rook */
    private ImageIcon bRook;
    /** Image to showcase black Bishop */
    private ImageIcon bBishop;
    /** Image to showcase black Queen */
    private ImageIcon bQueen;
    /** Image to showcase black King */
    private ImageIcon bKing;
    /** Image to showcase black Pawn */
    private ImageIcon bPawn;
    /** Image to showcase black Knight */
    private ImageIcon bKnight;

    /** verifies that the selected piece is the "from" location */
    private boolean firstTurnFlag;

    /** value of the "from" row */
    private int fromRow;
    /** value of the "from" column */
    private int fromCol;
    /** value of the "to" row */
    private int toRow;
    /** value of the "to" column */
    private int toCol;

    /** listener for the JButton[][] 2D array of buttons */
    private listener listener;
    /** listener for the new game button */
    private listener newGameListener;
    /** listener for the undo button */
    private listener undoListener;
    /** listener for the human vs. cpu button */
    private listener compListener;
    /** listener for the cpu vs. cpu button */
    private listener autoListener;

    /** The primary panel that will host the secondary panel as well as the button panel */
    JPanel boardPanel   = new JPanel();
    /** The secondary panel to add the extra feature buttons */
    JPanel boardPanel2  = new JPanel();
    /** The panel for all the buttons */
    JPanel buttonPanel  = new JPanel();

    /** New Game Button */
    JButton newGameButton = new JButton("New Game");
    /** Undo Move Button */
    JButton undoButton  = new JButton("Undo Move");
    /** Human vs. CPU Button */
    JButton compButton  = new JButton("Human vs. CPU");
    /** CPU vs. CPU Button */
    JButton autoButton  = new JButton("CPU vs. CPU");

    /** ***************************************************************
     * ChessPanel Constructor creates a new chess model, adds all the
     * buttons for the chess board, adds the chess pieces to their respective
     * starting locations.
     **************************************************************** */
    public ChessPanel() {
        // instantiate a new ChessModel
        model               = new ChessModel();
        // instantiate a new 2D JButton
        board               = new JButton[model.numRows()][model.numColumns()];
        // instantiate a new listener for the chess board buttons
        listener            = new listener();
        // instantiate a new listener for the New Game button
        newGameListener     = new listener();
        // instantiate a new listener for the "Undo" Move button
        undoListener        = new listener();
        // instantiate a new listener for the Human vs. CPU button
        compListener        = new listener();
        // instantiate a new listener for the CPU vs. CPU button
        autoListener        = new listener();

        // call the createIcons methods
        createIcons();

        // set the layout for the boardPanel
        boardPanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 5, 5));
        // set the layout for the boardPanel2
        boardPanel2.setLayout(new GridLayout(4, 1, 5, 5));

        // add action listen to the New Game button
        newGameButton.addActionListener(newGameListener);
        // add action listen to the Undo Move button
        undoButton.addActionListener(undoListener);
        // add action listen to the Human vs. CPU button
        compButton.addActionListener(compListener);
        // add action listen to the CPU vs. CPU button
        autoButton.addActionListener(autoListener);

        // Nested for-loop to add action listeners as well as the respective chess pieces to the buttonPanel
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
                // add the specified button to the boardPanel
                boardPanel.add(board[r][c]);
            }
        }

        // add the boardPanel to the left side
        add(boardPanel, BorderLayout.WEST);
        // add the boardPanel2 to the right side
        add(boardPanel2, BorderLayout.EAST);

        // set the preferred size of the window to 600 x 600
        boardPanel.setPreferredSize(new Dimension(600, 600));

        add(buttonPanel);
        // add the newGameButton
        boardPanel2.add(newGameButton);
        // add the undoButton
        boardPanel2.add(compButton);
        // add the undoButton
        boardPanel2.add(autoButton);
        // add the undoButton
        boardPanel2.add(undoButton);

        // added but greyed out b/c the logic has not been created
        newGameButton.setEnabled(false);
        // added but greyed out b/c the logic has not been created
        compButton.setEnabled(false);
        // added but greyed out b/c the logic has not been created
        autoButton.setEnabled(false);

        // set to true so the first button press on the board is the "from" location
        firstTurnFlag = true;

        // add the initial board to the stack
        model.addBoard();
    }

    /** ***************************************************************
     * The setBackGroundColor() method set the color of the board. The
     * bottom left spot, respective to the player's
     * view must be black.
     *
     * @param r
     * @param c
     **************************************************************** */
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            // set the background color to GVSU Blue
            board[r][c].setBackground(Color.decode("#264391"));
            // Only for mac to visual the spaces, comment out for non-mac users
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /** ****************************************************************
     * The placeWhitePieces method places the white chess pieces in their respective locations. Its parameters are
     * the rows and columns
     *
     * @param r
     * @param c
     **************************************************************** */
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

    /** ****************************************************************
     * The placeBlackPieces method places the black chess pieces in their respective locations. Its parameters are
     * the rows and columns
     *
     * @param r; r is the row value
     * @param c; c is the column value
     **************************************************************** */
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

    /** ****************************************************************
     * The createIcons() method creates the chess piece icons. We are providing the
     * PNG files from the resource folder "res"
     **************************************************************** */
    private void createIcons() {
        // checkmate image
        checkmate = new ImageIcon("res/checkmate.png");

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

    /** ****************************************************************
     * The displayBoard() method updates the board
     **************************************************************** */
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

    /** ****************************************************************
     * Inner class that represents action listener for buttons
     **************************************************************** */
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if (undoButton == event.getSource()) {
                    model.undoBoard();
                //  display the board
                    displayBoard();
            }

            for (int r = 0; r < model.numRows(); r++) {
                for (int c = 0; c < model.numColumns(); c++) {

                    if (board[r][c] == event.getSource()) {
                        // initial click of the piece to obtain the "from" location
                        if (firstTurnFlag) {
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
                                            // display the board
                                            displayBoard();
                                            // add the current board to the stack
                                            model.addBoard();
                                            // next player is up
                                            model.setNextPlayer();
                                            // after the player moves, the next player must check to see if they are in check
                                            if (model.inCheck(model.currentPlayer())) {
                                                if (model.isComplete()) {
                                                    if (model.currentPlayer() == Player.BLACK) {
                                                        System.out.println("White Wins!");
                                                    }

                                                    if (model.currentPlayer() == Player.WHITE) {
                                                        System.out.println("Black Wins!");
                                                    }
                                                } else {
                                                    // adds the black king icon to the option pane
                                                    // tells the player they're in check
                                                    if (model.currentPlayer() == Player.BLACK) {
                                                        JOptionPane.showMessageDialog(null,
                                                                model.currentPlayer().toString().toLowerCase(Locale.ROOT) +
                                                                        " king is danger of being captured!", "Check",
                                                                JOptionPane.INFORMATION_MESSAGE, bKing);
                                                    }

                                                    // adds the white king icon to the option pane
                                                    // tells the player they're in check
                                                    if (model.currentPlayer() == Player.WHITE) {
                                                        JOptionPane.showMessageDialog(null,
                                                                model.currentPlayer().toString().toLowerCase(Locale.ROOT) +
                                                                        " king is danger of being captured!", "Check",
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
    }
}