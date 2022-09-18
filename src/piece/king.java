package piece;

import java.util.ArrayList;

import board.square;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.color;
import utilities.position;

public class king extends piece{

	public king(color side, double weight, position position) 
	{
		super(side, weight, position);
		this.type = "king";
		this.setImage();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getPossibleMoves(square[][] board) 
	{
		ArrayList<position>moves = new ArrayList<>();
		
		ArrayList<position>Finalmoves = new ArrayList<>();
		
		//right , left
		moves.add(new position(this.getPosition().getY(),  this.getPosition().getX() + 1));
		moves.add(new position(this.getPosition().getY(), this.getPosition().getX() - 1));
	
		// up , down
		moves.add(new position(this.getPosition().getY() + 1, this.getPosition().getX()));
		moves.add(new position(this.getPosition().getY() - 1, this.getPosition().getX()));
		
		//up corners
		moves.add(new position(this.getPosition().getY() + 1, this.getPosition().getX() +1));
		moves.add(new position(this.getPosition().getY() + 1, this.getPosition().getX() - 1));
		
		//down corners
		moves.add(new position(this.getPosition().getY() - 1, this.getPosition().getX() - 1));
		moves.add(new position(this.getPosition().getY() - 1, this.getPosition().getX() + 1));
		
		
		for (position po : moves)
		{
			if (po.getX() <= 7 && po.getX() >= 0 && po.getY() <= 7 && po.getY() >= 0)
			{

				if (board[po.getY()][po.getX()].isFree() == false)
				{
					if (board[po.getY()][po.getX()].getPiece().getSide() != this.getSide())
					{
						Finalmoves.add(po);
					}
				}
				else
				{
					Finalmoves.add(po);
				}
			}
		}
	
		this.setPossibleMoves(Finalmoves);
	}
}




