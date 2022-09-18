package piece;

import java.util.ArrayList;
import java.util.Arrays;

import board.square;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.color;
import utilities.position;

public class pawn extends piece{

	public pawn(color side, double weight, position position, String image) {
		super(side, weight, position);
		this.type = "pawn";
		this.setImage();
	}
	
	@Override
	public void getPossibleMoves(square[][] board)
	{
		ArrayList<position>moves = new ArrayList<>();
		
		ArrayList<position>Finalmoves = new ArrayList<>();
		
		
		if (this.getSide() == color.BLACK && this.getPosition().getY() != 7 )
		{
			boolean stop = false;
			
			moves.add(new position(this.getPosition().getY() + 1, this.getPosition().getX() + 1));
			moves.add(new position(this.getPosition().getY() + 1, this.getPosition().getX() - 1));
			moves.add(new position(this.getPosition().getY() + 1, this.getPosition().getX()));
			
			if (this.getPosition().getY() == 1)
			{
				moves.add(new position(this.getPosition().getY() + 2, this.getPosition().getX()));
			}
			
			for (position mov : moves)
			{
				if (mov.getX() <= 7 && mov.getY() <= 7 && mov.getX() >= 0 && mov.getY() >= 0)
				{
					if (mov.getX() != this.getPosition().getX())
					{
						if (board[mov.getY()][mov.getX()].isFree() == false && board[mov.getY()][mov.getX()].getPiece().getSide() != this.getSide())
						{
							System.out.println("po X" +  mov.getX());
							System.out.println("po Y " + mov.getY());
							Finalmoves.add(new position(mov.getY(), mov.getX()));
						}
					}
					else
					{	
						if (stop == false )
						{
							if (board[mov.getY()][mov.getX()].isFree()== true)
							{
								System.out.println("po X" +  mov.getX());
								System.out.println("po Y " + mov.getY());
								Finalmoves.add(mov);
							}
							else
							{
								stop = true;
								
								piece target = (piece)board[mov.getY()][mov.getX()].getChildren().get(0);
								
								if (target.getSide() != this.getSide())
								{
									System.out.println("po X" +  mov.getX());
									System.out.println("po Y " + mov.getY());
									Finalmoves.add(mov);
								}
							}
						}
					}
				}
			}
		}
				
		if (this.getSide() == color.WHITE && this.getPosition().getY() != 0)
		{
			
			boolean stop = false;
			
			moves.add(new position(this.getPosition().getY() - 1, this.getPosition().getX() + 1));
			moves.add(new position(this.getPosition().getY() - 1, this.getPosition().getX() - 1));
			moves.add(new position(this.getPosition().getY() - 1, this.getPosition().getX()));
			
			if (this.getPosition().getY() == 6)
			{
				moves.add(new position(this.getPosition().getY() - 2, this.getPosition().getX()));
			}
			
			System.out.println("moves liste" + Arrays.toString(moves.toArray()));
			
			
			for (position mov : moves)
			{
				System.out.println("position Y ::" + mov.getY());
				
				System.out.println("position X ::" + mov.getX());
				
				
				if (mov.getX() <= 7 && mov.getY() <= 7 && mov.getX() >= 0 && mov.getY() >= 0)
				{
					System.out.println(" x && y between 0 and 7 !");
					
					//case left and right
					if (mov.getX() != this.getPosition().getX())
					{
						if (board[mov.getY()][mov.getX()].isFree() == false && board[mov.getY()][mov.getX()].getPiece().getSide() != this.getSide())
						{
							Finalmoves.add(new position(mov.getY(), mov.getX()));
						}
					}
					else
					{
						System.out.println("no ennemies in right and left");
						if (stop == false )
						{
							if (board[mov.getY()][mov.getX()].isFree()== true)
							{
								Finalmoves.add(mov);
							}
							else
							{
								stop = true;
								piece target = (piece)board[mov.getY()][mov.getX()].getChildren().get(0);
								if (target.getSide() != this.getSide())
								{
									Finalmoves.add(mov);
								}
								
							}
						}
					}
				}
			}
		}
		
		System.out.println("Finalmoves liste" + Arrays.toString(Finalmoves.toArray()));
		
		
		this.setPossibleMoves(Finalmoves);
	}
}
