package sample;

import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Rafid Dewan
 */
public class ConnectFourApplication extends Application implements Observer
{
    public final static int NUM_COLUMNS = 8;
    public final static int NUM_ROWS = 8;
    public final static int NUM_TO_WIN = 4;
    public final static int BUTTON_SIZE = 20;
    private ConnectFourGame gameEngine;
    private ConnectButton[][] buttons;

    /**
     * Starts the GUI
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage)
    {
        gameEngine = new ConnectFourGame(ConnectFourEnum.BLACK);
        gameEngine.addObserver(this);
        BorderPane root = new BorderPane();
        TextField box = new TextField("Black Begins");
        GridPane gridPane = new GridPane();
        Button takeTurnBtn = new Button("Take My Turn");
        Scene scene = new Scene(root,510,380);
        //Create empty buttons
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int j = 0; j < NUM_COLUMNS; j++)
            {
                ConnectButton button = new ConnectButton("EMPTY",NUM_ROWS - i - 1, j);
                button.setMinHeight(40); //change to 40 to increase size since it was small on the screen
                button.setMaxWidth(Double.MAX_VALUE);
                button.setOnAction(new ButtonHandler());
                gridPane.add(button,j,i);
                this.buttons = new ConnectButton[NUM_ROWS][NUM_COLUMNS];
                this.buttons[i][j] = button;
            }
        }
        box.setEditable(false);
        
        root.setTop(box);
        root.setCenter(gridPane);
        root.setBottom(takeTurnBtn);
        primaryStage.setTitle("ConnectFour");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void update(Observable obs, Object arg)
    {
        ConnectMove move = (ConnectMove) arg;
        this.buttons[move.getRow()][move.getColumn()].setText(move.getColour().toString());
    }

    /**
     * Inner class to handle actions once the button is pressed
     */
    class ButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            System.out.println("You've clicked on " + event.getSource());
        }
    }
    
    
    /**
     * Activates the GUI
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
