package sample;

/**
 * Specifies where the chip is dropped and if it was either red or black's turn
 * @author Rafid
 */
public class ConnectMove {
    private int row;
    private int column;
    private ConnectFourEnum colour;

    /**
     * Constructor for ConnectMove
     * @param row
     * @param column
     * @param colour
     */
    public ConnectMove(int row, int column, ConnectFourEnum colour){
        this.row = row;
        this.column = column;
        this.colour = colour;
    }

    /**
     * Returns the row number
     * @return int
     */
    public int getRow(){
        return this.row;
    }

    /**
     * Returns the column number
     * @return int
     */
    public int getColumn(){
        return this.column;
    }

    /**
     * Returns which colour has moved
     * @return ConnecFourEnum
     */
    public ConnectFourEnum getColour(){
        return this.colour;
    }
}
    