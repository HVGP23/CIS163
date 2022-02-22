package Chess;

public class Move {

    // class fields
    public int fromRow, fromColumn, toRow, toColumn;

    /**
     * Empty constructor
     */
    public Move() {
    }

    /**
     * The move constructor assigns the class fields to the values of the parameters
     *
     * @param fromRow
     * @param fromColumn
     * @param toRow
     * @param toColumn
     */
    public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
        this.fromRow    = fromRow;
        this.fromColumn = fromColumn;
        this.toRow      = toRow;
        this.toColumn   = toColumn;
    }

    /**
     * The toString() method overrides the standard toString method in the String class to what we needed it to
     * display
     *
     * @return returns the user's move in a string method
     */
    @Override
    public String toString() {
        return "Move [fromRow =" + fromRow + ", fromColumn =" + fromColumn + ", toRow =" + toRow + ", toColumn =" + toColumn
                + "]";
    }
}
