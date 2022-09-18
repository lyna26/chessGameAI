package board;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import piece.piece;
import utilities.color;
import utilities.position;

public class square extends StackPane{
	private position position;
	private int number;
	private char letter ;
	private boolean free;
	private piece Piece; 
	
	public square(position position, int number, char letter) {
		this.position = position;
		this.number = number;
		this.letter = letter;
		this.free = true;
		this.Piece = null;

		if ((position.getY()+position.getX()) % 2 == 0) 
		{

			setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		else 
		{
			setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		}
	}
	
	public position getPosition() {
		return position;
	}

	public int getNumber() {
		return number;
	}


	public char getLetter() {
		return letter;
	}

	
	public boolean isFree() {
		return free;
	}

	public void setFree(boolean isfree) {
		this.free = isfree;
	}

	public piece getPiece() {
		return this.Piece;
	}

	public void setPiece(piece piece) 
	{
		System.out.println("piece to add : " + piece.getType() + piece.getSide() + piece.getPosition().getY() + piece.getPosition().getX());
		
		this.Piece = piece;
	}	
}
