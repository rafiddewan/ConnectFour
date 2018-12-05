/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Nick
 */
public class ConnectFourApplication extends Application implements Observer {
    public final static int NUM_COLUMNS = 8;
    public final static int NUM_ROWS = 8;
    public final static int NUM_TO_WIN = 4;
    public final static int BUTTON_SIZE = 20;
    private ConnectFourGame gameEngine;
    private ConnectButton[][] buttons;
    
            
    @Override
    public void start(Stage primaryStage) {
        
        gameEngine = new ConnectFourGame(ConnectFourEnum.BLACK);
        gameEngine.addObserver(this);
        BorderPane root = new BorderPane();
        TextField box = new TextField("Do Later");
        GridPane grid = new GridPane();
        Button btn = new Button("Take My Turn");
        Scene scene = new Scene(root,510,380); 
        for(int r = 0; r < NUM_ROWS; r++){
            for(int c = 0; c < NUM_COLUMNS; c++){
                ConnectButton button = new ConnectButton("EMPTY",NUM_ROWS - r - 1, c);
                button.setMinHeight(40);
                button.setMaxWidth(Double.MAX_VALUE);
                button.setOnAction(new ButtonHandler());
                grid.add(button,c,r);
                this.buttons = new ConnectButton[NUM_ROWS][NUM_COLUMNS];
                this.buttons[r][c] = button;
            }
        }
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event){
                System.out.println("Drop the Checker");
            }
        });
        
        box.setEditable(false);
        
        root.setTop(box);
        root.setCenter(grid);
        root.setBottom(btn);
        primaryStage.setTitle("ConnectFour");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void update(Observable obs, Object arg) {
       // if (arg instanceof ConnectMove){
            ConnectMove move = (ConnectMove) arg;
            this.buttons[move.getRow()][move.getColumn()].setText(move.getColour().toString());
        //}
    }

    
    class ButtonHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event){
            System.out.println("You've clicked on " + event.getSource());
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
