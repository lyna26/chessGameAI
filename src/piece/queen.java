package piece;

import java.util.ArrayList;
import java.util.List;
import board.square;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.color;
import utilities.position;

public class queen extends piece{

	public queen(color side, double weight, position position) 
	{
		super(side, weight, position);
		this.type = "queen";
		this.setImage();
	}

	@Override
	public void getPossibleMoves(square[][] board)
	{
		ArrayList<position> movesHor = getHorizentaleMoves(board);
		ArrayList<position> movesVert = getVerticaleMoves(board);
		ArrayList<position> movesDiag = getDiagonaleMoves(board);
		ArrayList<position> moves = new ArrayList<position>();
		moves.addAll(movesHor);
		moves.addAll(movesVert);
		moves.addAll(movesDiag);
		this.setPossibleMoves(moves);
	}
}
