package minMax;

import board.square;
import piece.piece;
import utilities.color;
import utilities.position;

public class AI 
{
	
	private static position po ; 
	
	//function minMAX
	public static double minMax(piece p, int depth, color clr, square[][] chessboard)
	{
		System.out.println("depth is  : "+ depth);
		
		if (p == null)
		{
			System.out.println(" p is null");
			
			return 0;
		}
		else
		{
			System.out.println("type p is" + p.getType());
		}

		
		if (depth == 0)
		{
			System.out.println("depth is 0");
			
			return p.getWeight();
		}
		
		
		p.getPossibleMoves(chessboard);
		
		
		if (clr == color.WHITE)
		{
			System.out.println("white piece");
			
			Double maxEval = Double.NEGATIVE_INFINITY;
			
			Double eval = null;
			
			for(position pos : p.getListeMoves())
			{
			
				if (chessboard[pos.getY()][pos.getX()].isFree() == false && chessboard[pos.getY()][pos.getX()].getPiece().getSide() != clr)
				{
					eval = minMax(chessboard[pos.getY()][pos.getX()].getPiece(), depth - 1, color.BLACK, chessboard);
				}
				else
				{
					eval = 0.0;
				}
				
				maxEval= Math.max(maxEval, eval) ;
			}
			
			po = p.getPosition();
			return maxEval;
		}
		else
		{
			System.out.println("black piece");
			
			double minEval = Double.POSITIVE_INFINITY;
			
			Double eval = null;
			
			if (p.getListeMoves().size() > 0)
			{
				for(position pos : p.getListeMoves())
				{
					if (chessboard[pos.getY()][pos.getX()].isFree() == false && chessboard[pos.getY()][pos.getX()].getPiece().getSide() != clr)
					{
						System.out.println("an ennemi foudn bu IA! ");
						
						eval = minMax(chessboard[pos.getY()][pos.getX()].getPiece(), depth - 1, color.WHITE, chessboard);
						
						System.out.println("eval value! "  + eval);
					}
					else
					{
						if (chessboard[pos.getY()][pos.getX()].isFree() == true)
						{
							eval = 0.0;
						}
						else
						{
							eval = 0.1;
						}
						
						depth--;
					}
					
					if (eval <= minEval)
					{
						minEval = eval;
						po = pos;
						System.out.println("new po :) " + po.getX() + po.getY());
					}
					
					System.out.println("min value! "  + minEval);
				}
			}
			else
			{
				minEval = 0.1;
			}
			
			return minEval;
		}
	}



	public static position getPo() {
		return po;
	}
}
