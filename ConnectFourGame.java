package sample; //Would like to say, this is the default package IntelliJ gives me when I create a new JavaFX project

import java.util.Observable;

/**
 * Assignment #4
 * A simple ConnectFour game (does not do diagonals)
 * @author Rafid Dewan
 */
public class ConnectFourGame extends Observable
{
    private int nRows; // Number of Rows in grid
    private int nColumns; //Number of Columns in grid
    private int numToWin; //Numbers of characters in a row or column it takes to win
    private ConnectFourEnum [][] grid; //Grid for the game
    private ConnectFourEnum gameState; //The state of the game when wins, loses, or draws the match also indicates whether or not match is in progress
    private ConnectFourEnum turn;
    private int nMarks;
    /**
     * One argument constructor
     * Default constructor that creates the game
     * @param initialTurn
     */
    public ConnectFourGame(ConnectFourEnum initialTurn) {
        this(8, 8, 4, initialTurn);
    }

    /**
     * Four argument constructor
     * Gives choice as to how large the ConnectFour table will be
     * Resets the original grid and starts the game
     * @param nRows
     * @param nColumns
     * @param numToWin
     * @param initialTurn
     */
    public ConnectFourGame(int nRows, int nColumns, int numToWin, ConnectFourEnum initialTurn)
    {
        //Checks to see if the columns and rows were fit the guidelines when creating the tic tac toe table
        if ((nRows != nColumns) && (nRows != numToWin) && (nRows < 3))
        {
            throw new IllegalArgumentException("Numbers must be the same and greater than 3");
        }
        this.nRows = nRows; //Set the turn to the initial rows used in the arguments to the object
        this.nColumns = nColumns; //Set the turn to the initial columns used in the arguments to the object
        this.numToWin = numToWin; //Set the the amount off characters in a row
        this.grid = new ConnectFourEnum[nRows][nColumns]; //Creates the grid for tic tac toe
        this.nMarks = 0;
        reset(initialTurn);
    }

    /**
     * Resets the grid to the corresponding columns and rows the user entered
     * All columns are set to ' '
     * Set the initial turn, the marks, and game state to start the game
     * @param initialTurn
     */
    public void reset(ConnectFourEnum initialTurn)
    {
        //Resets the table to spaces when starting the game
        for (int i = 0; i < nColumns; i++)
        {
            for (int j = 0; j < nRows; j++)
            {
                grid[i][j] = ConnectFourEnum.EMPTY; //Set each index to a space character
            }
        }
        this.turn = initialTurn; //Set the turn to the initial turn passed in the arguments to the object
        this.gameState = ConnectFourEnum.IN_PROGRESS; //Returns an enum regarding the game state since In_Progress to signify the start of the game
    }

    /**
     * Returns who's turn it is
     * @return ConnectFourEnum
     */
    public ConnectFourEnum getTurn() { return this.turn; }

    /**
     * Return's the state of the game
     * @return ConnectFourEnum
     */
    public ConnectFourEnum getGameState()
    {
        return this.gameState;
    }

    /**
     * Takes the turn
     * @param row
     * @param column
     * @return
     */
    public ConnectFourEnum takeTurn(int row, int column)
    {
        //Checks if game state is in progress if not its game over
        if (this.gameState != ConnectFourEnum.IN_PROGRESS)
            throw new IllegalArgumentException("GAME OVER!");
        //Checks if the user enters a valid comma/row
        if (row > nColumns || column > nColumns || row < 0 || column < 0)
            throw new IllegalArgumentException("No such row and column (" + row + "," + column + " it might be out of bounds. \nPlease try another option");
        //Checks if the space  has been taken
        if(grid[row][column] != ConnectFourEnum.EMPTY)
            throw new IllegalArgumentException("Space is already occupied");
        //Checks if the specific row/column is empty and not a floating square
        if(row != 0)
        {
            if(this.grid[row - 1][column] == ConnectFourEnum.EMPTY)
                throw new IllegalArgumentException("Row and column is empty at: " + (row -1) + "," + column + ". \nPlease try a different option ");
        }
        ConnectMove move = new ConnectMove(row, column, this.turn);
        this.grid[row][column] = this.turn;
        this.gameState = findWinner(row, column); //Check to see if game is won/drawn/ongoing
        //used for switching turns
        if (this.turn == ConnectFourEnum.RED) this.turn = ConnectFourEnum.BLACK;
        else this.turn = ConnectFourEnum.RED;
        this.nMarks++;
        setChanged();
        notifyObservers(move);
        return this.gameState; //gives an indication where the game is at
    }


    /**
     * Finds the winner of the game if X won, O won, a draw or if the game is still in progress
     * @return IN_PROGRESS, RED_WON, BLACK_WON, DRAW
     **/
    private ConnectFourEnum findWinner(int row, int column) {
        ConnectFourEnum thisState = ConnectFourEnum.IN_PROGRESS;
        if (nMarks == nColumns * nRows) thisState = ConnectFourEnum.DRAW;
        else {
            int c = 1; //count kept constant checking the rows/columns
            //Check vertical
            if (row >= 3) {
                for (int i = row - 1; i >= row - 3; i--) {
                    if (this.grid[i][column] == this.grid[row][column]) {
                        c++;
                    }
                    if (c == numToWin) {
                        thisState = this.turn;
                    }
                }
            }
            c = 1;
            //Check from centre and left horizontally
            if (column <= 4) {
                ConnectFourEnum prevState = this.grid[row][column];
                ConnectFourEnum currState = null;
                for (int i = column + 1; i < nColumns; i++) {
                    currState = this.grid[row][i];
                    if ((currState == prevState)) c++;
                    else break;
                    prevState = currState;
                }
                if (c == numToWin) thisState = this.turn;
            }
            //check from  centre and right horizontally
            c = 1;
            if (column >= 3) {
                ConnectFourEnum prevState = this.grid[row][column];
                ConnectFourEnum currState = null;
                for (int i = column - 1; i >= 0; i--) {
                    currState = this.grid[row][i];
                    if ((currState == prevState)) c++;
                    else break;
                }
                if (c == numToWin) thisState = this.turn;
            }
        }
        return thisState; //If none of the cases are true, then it returns the progress of the game
    }

    /**
     * Makes the grid for the game in a form of a string
     * @return String
     */
    public String toString()
    {
        String gridPrint = new String(""); //Initialize a new string for tic tac toe
        //Nested for  loop used to print the each box in tic tac toe
        for (int i = 0; i < nRows; i++)
        {
            for(int j = 0; j < nColumns; j++)
            {
                gridPrint.concat(this.grid[i][j] + " | "); // print's the space/O/X and then a line indicating the box in each column.
            }
            gridPrint.concat("\n"); //creates rows by creating a new line after going through each column
        }
        return gridPrint; //returns the string of that grid
    }
}
