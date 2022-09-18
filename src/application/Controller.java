package application;

import game.game;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;


public class Controller {
	
	@FXML
    GridPane chessBoard;

    public void initialize(){

        game Game = new game(chessBoard);

    }
}
