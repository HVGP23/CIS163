package Chess;

/* *********************************************************************
 * The Player class simply sets the next player. This code was provided to
 * us by our instructor, Mr. Beach.
 *
 * @author Julia Garcia Navarro, Jack Lukomski, Hector Garcia
 * @version February 24, 2022
 *
 ********************************************************************* */

public enum Player {
    BLACK, WHITE;

    /**
     * Return the {@code Player} whose turn is next.
     *
     * @return the {@code Player} whose turn is next
     */
    public Player next() {
        if (this == BLACK)
            return WHITE;
        else
            return BLACK;

        //	return this == BLACK ? WHITE : BLACK; ternary condition that could be used instead
    }
}
