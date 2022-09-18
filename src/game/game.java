package game;

import java.util.ArrayList;
import java.util.stream.Stream;

import Gamer.gamer;
import board.chessBoard;
import board.square;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import minMax.AI;
import piece.piece;
import utilities.color;
import utilities.position;

public class game {
	
	public static chessBoard cb;
	 
	 public piece currentPiece ;
	 
	 public gamer currentGamer;
	 
	 private  gamer whiteGamer;
	 
	 private gamer BlackGamer;
	 
	 public int nbTour = 1;
	 
	 public game(GridPane chessB)
	 {
		 
		 cb = new chessBoard(chessB);
		 
		 whiteGamer = new gamer (color.WHITE, "Lyna", "Human");
		 
		 BlackGamer = new gamer (color.BLACK, "EZEL", "IA");
		 
		 addpiecesToGamer();
		 
		 currentGamer = whiteGamer ;
		 
		 //playHumanVSHuman(cb.getChessBoard());
		 playHumanVSIA();
		
	}
	 
	 private void addpiecesToGamer()
	 {
		 for (int i = 0; i < 2 ; i++)
		 {
			 for (int j = 0; j < 8; j++)
			 {
				 BlackGamer.getPieces().add(cb.getSquares()[i][j].getPiece());
			 }
		 }
		 
		 for (int i = 6; i < 8 ; i++)
		 {
			 for (int j = 0; j < 8; j++)
			 {
				 whiteGamer.getPieces().add(cb.getSquares()[i][j].getPiece());
			 }
		 }
	 }
	 private void changeCurrentGamer()
	 {
		 if (currentGamer.getSide() == color.WHITE)
		 {
			 currentGamer = BlackGamer;
		 }
		 else
		 {
			 currentGamer = whiteGamer ;
		 }
	 }
	
	  private void playHumanVSHuman(GridPane chessBoard)
	  {
		        chessBoard.setOnMouseClicked(new EventHandler<MouseEvent>() 
		        {
		            @Override
		            public void handle(MouseEvent event)
		            {
		            	 EventTarget target = event.getTarget();
		            	 
		            	 //case square clicked
		            	 if ((target.toString()).contains("square") == true)
	        			 {
		            		 
		            		 if(currentPiece != null)
		            		 {
		            			 square s = (square) target;
		            			 
		            			 boolean moved = movePiece(s.getPosition());
		            			
		            			 if (moved == true)
		            			 {
		            				 changeCurrentGamer();
		            			 }
		            			 
		            			 deselectPiece();
		            			 
		            			 currentPiece = null;
		            		}
	        			 }
		            	 else //case piece clicked
		            	 {
		            		 
		            		 piece pieceTarget = (piece) target;
		            		 
		            		 if (pieceTarget.getSide() == currentGamer.getSide())
		            		 {
		            			 
			            		 if(currentPiece != null )
			            		 {
			            			 System.out.println("currentpiece is not null: ");
			     
			            			 deselectPiece();
			            		 }
			            		
			            		 currentPiece = pieceTarget;
			            		 
			            		 selectPiece();
		            		 }
		            		 else
		            		 {
		            			 square s = cb.getSquares()[pieceTarget.getPosition().getY()][pieceTarget.getPosition().getX()];
		            		 
		        		         boolean moved = movePiece(s.getPosition());
		        		           			
		        			     deselectPiece();
		        			           	  
	    			           	  if (moved == true)
	    			           	  {
	    			           		  changeCurrentGamer();
	    			           	  }
		            		 }
		            	 }
		            }
		        });
		        
			  }
	  
	  private void playHumanVSIA()
	  {
		  int x = 0 ;
		  
		  while (x < 2)
		  {
		  	
			  if (currentGamer.getType().equals("IA"))
			  {
					System.out.println("it is ia");
					
					Double lastRes = 0.0;
					
					position targetPos = null;
					
					for(piece pieceIA : currentGamer.getPieces())
					{
						System.out.print("y IA"  + pieceIA.getPosition().getY());
						System.out.print("x IA"  + pieceIA.getPosition().getX());
						
						Double res = AI.minMax(pieceIA, 2, currentGamer.getSide(), cb.getSquares());
						
						System.out.println("res = " + res);
						
						if (currentGamer.getSide() == color.WHITE)
						{
							System.out.println("cas res white");
							if (res >= lastRes )
							{
								lastRes = res;
								
								targetPos = AI.getPo(); 
								currentPiece = pieceIA;
							};
						}
						else
						{
							System.out.println("cas res blck");
							
							if (res <= lastRes )
							{
								System.out.println("in if");
								lastRes = res;
								System.out.println("lastRes" + lastRes);
								System.out.println("new target : " + AI.getPo().getX() + AI.getPo().getY());
								currentPiece = pieceIA;
								targetPos = AI.getPo(); 
								
							};
						}
						
					}
					
					movePiece(targetPos);
					
				  }
			  else
			  {
				  changeCurrentGamer();
			  }
			  x++;
			  
		  }
	  }
	  
	  private void selectPiece(){
		  
		//setting 
		DropShadow borderGlow = new DropShadow();
		borderGlow.setColor(Color.BLACK);
		borderGlow.setOffsetX(0f);
		borderGlow.setOffsetY(0f);
		
		//set the glow on the pieces
		currentPiece.setEffect(borderGlow);
		currentPiece.getPossibleMoves(cb.getSquares());
		
		//make all possible positions of the piece green and glowy
		showAllPossibleMoves();
	  }
	  
	  private void deselectPiece(){
		  currentPiece.setEffect(null);
		  hideAllPossibleMoves();
	  }
	  
	  public void showAllPossibleMoves(){
	    	
	    	System.out.println("showallpossiblemoves");
			
	    	//((Region) cb.getChessBoard().getChildren().get(0)).setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	    	
	    	//cb.getSquares()[5][3].setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	    	Glow glow = new Glow();
			
			glow.setLevel(0.3);
			
			for(position move : currentPiece.getListeMoves())
			{
				System.out.println("the possible move Y is" + move.getY());
				
				System.out.println("the possible move X is" + move.getX());
				
				cb.getSquares()[move.getY()][move.getX()].setEffect(glow);
				
				if (cb.getSquares()[move.getY()][move.getX()].isFree() == true)
				{
					cb.getSquares()[move.getY()][move.getX()].setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
				}
				else
				{
					cb.getSquares()[move.getY()][move.getX()].setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
				}
			}
	  }
	  
	  
	  public void hideAllPossibleMoves()
	  {
			for(position move : currentPiece.getListeMoves())
			{
				cb.getSquares()[move.getY()][move.getX()].setEffect(null);
				
				
				if ((move.getY()+move.getX()) % 2 == 0) 
				{

					cb.getSquares()[move.getY()][move.getX()].setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
				}
				else 
				{
					cb.getSquares()[move.getY()][move.getX()].setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
				}
			}
	  }
	  
	  public boolean movePiece(position p)
	  {
		
		Stream<position> resultat = currentPiece.getListeMoves().stream().filter(pos -> pos.getY() == p.getY() && pos.getX() == p.getX());
		
		if (resultat.count() == 1)
		{
			if (cb.getSquares()[p.getY()][p.getX()].isFree() == false)
			{
				killPiece(cb.getSquares()[p.getY()][p.getX()].getPiece());
			}
			
			cb.getSquares()[currentPiece.getPosition().getY()][currentPiece.getPosition().getX()].setFree(true);
		
			currentPiece.setPosition(p);
		
			cb.addPiece( cb.getSquares()[p.getY()][p.getX()], currentPiece);
			  
			cb.getSquares()[p.getY()][p.getX()].setFree(false);
			
			return true;
		}
		
		return false;
	  }
		  
	  public void killPiece(piece p)
	  {
		  cb.getSquares()[p.getPosition().getY()][p.getPosition().getX()].getChildren().remove(0);
		  
		  cb.getSquares()[p.getPosition().getY()][p.getPosition().getX()].setFree(true);
		  
		  if (p.getSide() == color.BLACK)
		  {
			  BlackGamer.getPieces().remove(p);
			  System.out.println("removed, new count : "  + BlackGamer.getPieces().size());
		  }
		  else
		  {
			  whiteGamer.getPieces().remove(p);
			  System.out.println("removed, new count : "  + whiteGamer.getPieces().size());
		  }
	  }
}