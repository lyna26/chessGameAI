package piece;

import java.util.ArrayList;

import board.square;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.color;
import utilities.position;

public class knight extends piece{

	public knight(color side, double weight, position position) {
		super(side, weight, position);
		this.type = "knight";
		this.setImage();
	}

	@Override
	public void getPossibleMoves(square[][] board) 
	{
		
		ArrayList<position>moves = new ArrayList<>();
		
		ArrayList<position>Finalmoves = new ArrayList<>();
		
        moves.add(new position (this.getPosition().getY() + 2, this.getPosition().getX()+1));
        moves.add(new position (this.getPosition().getY()+2, this.getPosition().getX() -1));
        
        moves.add( new position( this.getPosition().getY() +1, this.getPosition().getX() +2));
        moves.add( new position( this.getPosition().getY() +1, this.getPosition().getX() -2));
        
        moves.add( new position (this.getPosition().getY() - 2, this.getPosition().getX()+1));
        moves.add(new position (this.getPosition().getY() - 2, this.getPosition().getX() -1));
        
        moves.add( new position( this.getPosition().getY() -1, this.getPosition().getX() +2));
        moves.add( new position( this.getPosition().getY() -1, this.getPosition().getX() -2));
        
        
        for (position po : moves)
		{
        	System.out.println("po X : " + po.getX());
        	System.out.println("po Y : " + po.getY());
        	
        	if (po.getX() <= 7 && po.getY() <= 7 && po.getX() >= 0 && po.getY() >= 0)
			{
        		if (board[po.getY()][po.getX()].isFree() == false)
				{
        			System.out.println("case occupied" );
        			System.out.println("case occupied target" + board[po.getY()][po.getX()].getPiece().getSide());
        			System.out.println("case occupied this" + this.getSide());
        			
        			
					if (board[po.getY()][po.getX()].getPiece().getSide() != this.getSide())
					{
						System.out.println("not same side");
						
						Finalmoves.add(po);
					}
	        	}
        		else
        		{
        		
        			Finalmoves.add(po);
        		}
			}
        	else
        	{
        		System.out.println("not valable");
        	}
		}
        
        this.setPossibleMoves(Finalmoves);
	}
}
