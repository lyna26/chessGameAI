package board;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;

import java.util.ArrayList;

import javafx.geometry.Insets;
import piece.*;
import utilities.color;
import utilities.position;

public class chessBoard{
	
	private final int nbSquare = 8;
	
	private square[][] squares;
	
	private GridPane chessBoard;
	
	public chessBoard(GridPane chessBoard) {
	
		this.setChessBoard(chessBoard);
		
		this.squares = new square[this.nbSquare][this.nbSquare];
		
		initiateBoard(this.chessBoard);
		
		addPieces();
	}
	
	public void initiateBoard(GridPane chessBoard)
	{
		for (int row=0; row<this.nbSquare;row++) 
		{
			for (int col=0; col<this.nbSquare;col++)
			{
				squares[row][col]= new square(new position(row, col), this.nbSquare - row, intToChar(col));
				squares[row][col].setPrefHeight(100);
				squares[row][col].setPrefWidth(100);
				squares[row][col].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				chessBoard.add(squares[row][col], col, row);
			}
		}
	}
	
	public void addPiece(square square, piece piece)
	{
		square.getChildren().add(piece);
		square.setFree(false);
		square.setPiece(piece);
	}
	
	private void addPieces()
	{
		for (int i = 0; i < this.nbSquare; i++)
		{
			for (int j = 0; j < this.nbSquare; j++)
			{
			
				square s = squares[i][j];
				
				System.out.print("Y : " + s.getPosition().getY());
	
				System.out.print("X : "  + s.getPosition().getX());
			
				if (s.isFree() == false)
				{
					continue;
				}
				
				if (s.getPosition().getY() == 1 )
				{	
					addPiece(s, new pawn(color.BLACK, -1, new position(1, s.getPosition().getX()), ""));
				}
				
				if (s.getPosition().getY() == 6)
				{			
					addPiece(s, new pawn(color.WHITE, 1, new position(6, s.getPosition().getX()), ""));
				}
				
				if(s.getPosition().getY() == 0)
				{
					if (s.getPosition().getX() == 0 || s.getPosition().getX() == 7)
					{
						addPiece(s, new castle(color.BLACK, - 5.1, new position (0, s.getPosition().getX())));
					}
					
					if (s.getPosition().getX() == 1 || s.getPosition().getX() == 6)
					{
						addPiece(s, new knight (color.BLACK, - 3.2, new position (0, s.getPosition().getX())));
					}
					
					if (s.getPosition().getX() == 2 || s.getPosition().getX() == 5)
					{
						addPiece(s, new bishop(color.BLACK, - 3.3, new position (0, s.getPosition().getX())));
					}
					
					if (s.getPosition().getX() == 3)
					{
						addPiece(s, new queen(color.BLACK, - 8.8, new position (0, 3)));
					}
					
					if (s.getPosition().getX() == 4)
					{
						addPiece(s, new king(color.BLACK, -100, new position (0,4)));
					}
				}
				
				if(s.getPosition().getY() == 7)
				{
					if (s.getPosition().getX() == 0 || s.getPosition().getX() == 7)
					{
						
						addPiece(s, new castle(color.WHITE, 5.1, new position (7, s.getPosition().getX())));
					}
					
					if (s.getPosition().getX() == 1 || s.getPosition().getX() == 6)
					{
						addPiece(s, new knight (color.WHITE, 3.2, new position (7, s.getPosition().getX())));
					}
					
					if (s.getPosition().getX() == 2 || s.getPosition().getX() == 5)
					{
						addPiece(s, new bishop(color.WHITE, 3.3, new position (7, s.getPosition().getX())));
					}
					
					if (s.getPosition().getX() == 3)
					{
						addPiece(s, new queen(color.WHITE, 8.8, new position (7, 3)));
					}
					
					if (s.getPosition().getX() == 4)
					{
						addPiece(s, new king(color.WHITE, 100, new position (7, 4)));
					}
				}
			}
		}
	}
		
	public char intToChar(int number)
	{
		char letter = 'a';
		
		return (char)(letter + number);
	}

	public int getNbSquare() {
		return nbSquare;
	}

	public GridPane getChessBoard() {
		return chessBoard;
	}

	public void setChessBoard(GridPane chessBoard) {
		this.chessBoard = chessBoard;
	}

	public square[][] getSquares() {
		return squares;
	}	
}
