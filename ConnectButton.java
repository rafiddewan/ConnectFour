package sample;

import javafx.scene.control.Button;

public class ConnectButton extends Button {
    private int row;
    private int column;

    public ConnectButton(String label, int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}