package piece;

import java.util.ArrayList;
import java.util.List;

import board.square;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.color;
import utilities.position;

public class bishop extends piece{

	public bishop(color side, double weight, position position) 
	{
		super(side, weight, position);
		this.type = "bishop";
		this.setImage();
	}

	@Override
	public void getPossibleMoves(square[][] board) {
		this.setPossibleMoves(getDiagonaleMoves(board));
	}
}
