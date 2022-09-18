package piece;

import java.util.ArrayList;

import board.square;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.color;
import utilities.position;

public class castle extends piece{

	public castle(color side, double weight, position position) {
		super(side, weight, position);
		this.type = "castle";
		this.setImage();
	}

	@Override
	public void getPossibleMoves(square[][] board) {
		
		ArrayList<position> movesHor = getHorizentaleMoves(board);
		ArrayList<position> movesVert = getVerticaleMoves(board);
		ArrayList<position> moves = new ArrayList<position>();
		moves.addAll(movesHor);
		moves.addAll(movesVert);
		this.setPossibleMoves(moves);
	}
}
