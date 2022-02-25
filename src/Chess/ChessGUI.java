package Chess;

import java.awt.*;
import javax.swing.*;


/** *********************************************************************
 *
 * GUI front end for chess
 *
 ********************************************************************* */
public class ChessGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton undo = new JButton("Undo Move");

        ChessPanel panel = new ChessPanel();

        frame.getContentPane().add(panel);

        // add the undo button
        panel.add(undo);

        frame.setResizable(true);

        frame.setPreferredSize(new Dimension(850, 650));

        frame.pack();

        // Opens in the center of the screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
