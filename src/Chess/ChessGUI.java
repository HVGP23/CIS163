package Chess;

/* *********************************************************************
 * The ChessGUI class creates a graphical user interface for the chess
 * game. There are four buttons added to a separate panel which are
 * new game, human vs. cpu, cpu vs. cpu, and undo move.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, and Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

import java.awt.*;
import javax.swing.*;

/** *********************************************************************
 * The ChessGUI class creates a new JFrame to add the ChessPanel
 * ********************************************************************* */
public class ChessGUI {
    public static void main(String[] args) {
        // create a new JFrame
        JFrame frame = new JFrame("Chess Game");

        // allows the program to exit when the x is clicked on the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a new chessPanel
        ChessPanel panel = new ChessPanel();

        // add the Chess panel to the frame
        frame.getContentPane().add(panel);

        // doesn't allow the window to be resizable
        frame.setResizable(false);

        // preferred window size
        frame.setPreferredSize(new Dimension(750, 650));

        // keeps everything packed in
        frame.pack();

        // Opens in the center of the screen
        frame.setLocationRelativeTo(null);

        // set's the frame to be visible
        frame.setVisible(true);
    }
}