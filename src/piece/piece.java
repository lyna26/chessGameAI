package piece;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import board.square;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utilities.color;
import utilities.position;
public abstract class piece extends ImageView implements Ipiece {
	private color side;
	private double weight;
	private position position;
	protected String type;
    private ArrayList<position> possibleMoves;
    
    
	public piece(color side, double weight, position position) {
		this.side = side;
		this.weight = weight;
		this.position = position;
	}

	
	public color getSide() {
		return side;
	}

	

	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	public position getPosition() {
		return position;
	}

	public void setPosition(position position) {
		this.position = position;
	}	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<position> getListeMoves() {
		return possibleMoves;
	}

	public void setPossibleMoves(ArrayList<position> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}
	
	 public void setPiece(Image image){
	        this.setImage(image);
	    }
	
    
	 public void setImage(){
    	
    		String path = "piece/images/" + this .type + this.getSide() + ".png";
    		System.out.println(path);
            this.setPiece(new Image("piece/images/" + this .type + this.getSide() + ".png"));
    }
	 
	public ArrayList<position>getHorizentaleMoves(square[][] board)
	{
		ArrayList<position>moves = new ArrayList<>();
		
		int startPointY = this.getPosition().getY();
		int startPointX = this.getPosition().getX();
		
		int countX  = 1;
		
		//straight right
		while(startPointX + countX <= 7)
		{
			int res = startPointX + countX ;
			
			if (board[startPointY][res].isFree() == false)
			{
				if (board[startPointY][res].getPiece().getSide() != this.getSide())
				{
					moves.add(new position(startPointY, startPointX + countX));
				}
				
				break;
			}
			
			moves.add(new position(startPointY, startPointX + countX));
			
			countX = countX + 1;
		}
				
		countX = 1;  
		
		//straight left
		while(startPointX - countX >= 0)
		{
			int res = startPointX - countX ;
			
			if (board[startPointY][res].isFree() == false)
			{
				if (board[startPointY][res].getPiece().getSide() != this.getSide())
				{
					moves.add(new position(startPointY, startPointX + countX));
				}
				
				break;
			}
			
			moves.add(new position(startPointY, res));
			
			countX = countX + 1;
		}
		
		return moves;
	}
		
	
	public ArrayList<position> getVerticaleMoves(square[][] board)
	{
		ArrayList<position> moves = new ArrayList<>();
		
		int startPointY = this.getPosition().getY();
		int startPointX = this.getPosition().getX();
		
		int countY = 1;
		
		//straight up
		while(startPointY + countY >= 0 && startPointY + countY <= 7)
		{
			int res = startPointY + countY ;
			
			if (board[res][startPointX].isFree() == false)
			{
				if (board[res][startPointX].getPiece().getSide() != this.getSide())
				{
					moves.add(new position(res, startPointX));
				}
				
				break;
			}
			
			moves.add(new position(res, startPointX));
			
			countY++;
		}
				
		countY = 1;
		
		//straight down
		while(startPointY - countY >= 0 && startPointY - countY <= 7)
		{
			int res = startPointY - countY ;
			
			if (board[res][startPointX].isFree() == false)
			{
				if (board[res][startPointX].getPiece().getSide() != this.getSide())
				{
					moves.add(new position(res, startPointX));
				}
				
				break;
			}
			
			moves.add(new position(res, startPointX));
			
			countY++;
		}
		
		return moves;
	}
	
	public ArrayList<position> getDiagonaleMoves(square[][] board)
	{
		ArrayList<position>moves = new ArrayList<>();
		int startPointY = this.getPosition().getY();
		int startPointX = this.getPosition().getX();	
		int countY = 1;
		int countX = 1;
		
		//+right + up
		while(startPointY + countY >= 0 && startPointY + countY <= 7 && startPointX + countX <= 7)
		{
			int resY = startPointY + countY;
			int resX = startPointX + countX;
			
			if (board[resY][resX].isFree() == false)
			{
				if (board[resY][resX].getPiece().getSide() != this.getSide())
				{
					moves.add(new position(resY, resX));
				}
				
				break;
			}
		
			moves.add(new position(resY, resX));
			
			countX++;
			countY++;
		}
		
		countX  = 1;
		countY = 1;
				
		//left up
		while(startPointY + countY >= 0 && startPointY + countY <= 7 && startPointX - countX >= 0)
		{
			int resY = startPointY + countY;
			int resX = startPointX - countX;
			
			if (board[resY][resX].isFree() == false)
			{
				if (board[resY][resX].getPiece().getSide() != this.getSide())
				{
					moves.add(new position(resY, resX));
				}
				break;
			}
			
			moves.add(new position(resY, resX));
			
			countX++;
			countY++;
		}
		
		countX  = 1;
		countY = 1;
				
		//down right
		while( startPointY - countY >= 0 && startPointY - countY <= 7 && startPointX + countX <= 7)
		{
			int resY = startPointY - countY;
			int resX = startPointX + countX;
			
			if (board[resY][resX].isFree() == false)
			{
				if (board[resY][resX].getPiece().getSide() != this.getSide())
				{ 
					moves.add(new position(resY, resX));
				}
				break;
				
			}
			moves.add(new position(resY, resX));
			countX++;
			countY++;
		}
		
		countX  = 1;
		countY = 1;
		
		//down left
		while( startPointY - countY >= 0 && startPointY - countY <= 7 && startPointX - countX >= 0)
		{
			int resY = startPointY - countY;
			int resX = startPointX - countX;
			
			if (board[resY][resX].isFree() == false)
			{
				if (board[resY][resX].getPiece().getSide() != this.getSide())
				{ 
					moves.add(new position(resY, resX));
				}
				break;
				
			}
			
			moves.add(new position(resY, resX));
			
			countX++;
			countY++;
		}
		
		return moves;
	} 
}