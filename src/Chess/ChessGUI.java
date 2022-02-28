package Chess;

import java.awt.*;
import javax.swing.*;


/** *********************************************************************
 * GUI front end for chess
 ********************************************************************* */
public class ChessGUI {
    public static void main(String[] args) {
        // create a new JFrame
        JFrame frame = new JFrame("Chess Game");
        // allows the program to exit when the x is clicked on the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // undo button
        JButton undo = new JButton("Undo Move");
        // create a new chessPanel
        ChessPanel panel = new ChessPanel();
        // add the Chess panel to the frame
        frame.getContentPane().add(panel);
        // add the undo button
        panel.add(undo);
        // doesn't allow the window to be resizable
        frame.setResizable(false);
        // preferred window size
        frame.setPreferredSize(new Dimension(850, 650));
        // keeps everything packed in
        frame.pack();
        // Opens in the center of the screen
        frame.setLocationRelativeTo(null);
        // set's the frame to be visible
        frame.setVisible(true);
    }
}
