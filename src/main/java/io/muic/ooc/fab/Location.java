package io.muic.ooc.fab;

public class Location {

    // Row and column positions.
    private final int ROW;
    private final int COL;

    /**
     * Represent a row and column.
     *
     * @param row The row.
     * @param col The column.
     */
    public Location(int row, int col) {
        this.ROW = row;
        this.COL = col;
    }

    /**
     * Implement content equality.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            Location other = (Location) obj;
            return ROW == other.getRow() && COL == other.getCol();
        } else {
            return false;
        }
    }

    /**
     * Return a string of the form row,column
     *
     * @return A string representation of the location.
     */
    public String toString() {
        return ROW + "," + COL;
    }

    /**
     * Use the top 16 bits for the row value and the bottom for the column.
     * Except for very big grids, this should give a unique hash code for each
     * (row, col) pair.
     *
     * @return A hashcode for the location.
     */
    public int hashCode() {
        return (ROW << 16) + COL;
    }

    /**
     * @return The row.
     */
    public int getRow() {
        return ROW;
    }

    /**
     * @return The column.
     */
    public int getCol() {
        return COL;
    }
}
