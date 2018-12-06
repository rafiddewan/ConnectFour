package sample;

import javafx.scene.control.Button;

/**
 * ConnectButton
 * @author Rafid Dewan
 */
public class ConnectButton extends Button {
    private int row;
    private int column;

    /**
     * Constructor for the button
     * @param label
     * @param row
     * @param column
     */
    public ConnectButton(String label, int row, int column)
    {
        super(label);
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the row
     * @return int
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Returns the column
     * @return int
     */
    public int getColumn()
    {
        return column;
    }

    /**
     * Returns a string
     * @return String
     */
    @Override
    public String toString()
    {
        return ("(" + getRow() + "," + getColumn() + ")");
    }
}
