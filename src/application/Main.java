package application;
	
import java.io.File;
import java.io.IOException;

import board.chessBoard;
import board.square;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import piece.bishop;
import piece.castle;
import piece.king;
import piece.knight;
import piece.pawn;
import piece.piece;
import piece.queen;
import utilities.color;
import utilities.position;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(new File("src/application/Sample.fxml").toURI().toURL());
		Parent root  = fxmlLoader.load(); 
		primaryStage.setTitle("chessGame");
		primaryStage.setScene(new Scene(root, 800, 800));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
