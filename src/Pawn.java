import java.util.ArrayList;


public class Pawn extends Piece {
	
	private boolean firstMove;
	
	public Pawn(int i,boolean b, int x, int y,boolean fm, int iv)
	{
		super (i,b,x,y,iv);
		firstMove = fm;
	}
	
	public ArrayList<Integer> move(int x, int y, Piece[][] b)
	{
		Piece[][] board = b;
		int save = x;
		Piece p = board[x][y];
		ArrayList<Integer> possiblemoves = new ArrayList<Integer>();
		
		//Pawns have different behavior for their first turn so we must account for that
		if (this.firstMove)
		{
			//Pawns on opposite ends are moving in opposite directions. this is for black.
			if (p.getType() == 0)
			{
				int firstspot = ++save;
				boolean firstspotworks = false;
				int secondspot = ++save;
				//checks the space immediately in front of the pawn
				if(board[firstspot][y] == null)
				{
					possiblemoves.add(firstspot);
					possiblemoves.add(y);
					firstspotworks = true;
				}
				//if the first space is available, check the second one
				if(board[secondspot][y] == null && firstspotworks)
				{
					possiblemoves.add(secondspot);
					possiblemoves.add(y);
				}
				
				int ytoleft = --y;
				int ytoright = y+=2;
				
				//these are potential routes for the pawn to knock out another piece
				if (ytoleft >0)
				{
					if (board[firstspot][ytoleft]!= null)
					{
						if (board[firstspot][ytoleft].getType() == 1)
						{
							possiblemoves.add(firstspot);
							possiblemoves.add(ytoleft);
						}
					}
				}
				
				if (ytoright < 8)
				{
					if (board[firstspot][ytoright] != null)
					{
						if (board[firstspot][ytoright].getType() == 1)
						{
							possiblemoves.add(firstspot);
							possiblemoves.add(ytoright);
						}
					}
				}
				
			}
			//controls the white pawns
			else
			{
				int firstspot = --save;
				boolean firstspotworks = false;
				int secondspot = --save;
				if(board[firstspot][y] == null)
				{
					possiblemoves.add(firstspot);
					possiblemoves.add(y);
					firstspotworks = true;
				}
				if(board[secondspot][y] == null && firstspotworks)
				{
					possiblemoves.add(secondspot);
					possiblemoves.add(y);
				}
				
				int ytoleft = --y;
				int ytoright = y+=2;
				
				if (ytoleft >0)
				{
					if (board[firstspot][ytoleft]!= null)
					{
						if (board[firstspot][ytoleft].getType() == 0)
						{
							possiblemoves.add(firstspot);
							possiblemoves.add(ytoleft);
						}
					}
				}
				
				if (ytoright < 8)
				{
					if (board[firstspot][ytoright] != null)
					{
						if (board[firstspot][ytoright].getType() == 0)
						{
							possiblemoves.add(firstspot);
							possiblemoves.add(ytoright);
						}
					}
				}
				
				
			}
		}
			
		//another set just for any turns outside the first one
		else
		{
			if(p.getType() == 0)
			{
				int firstspot = ++save;
				if(board[firstspot][y] == null)
				{
					possiblemoves.add(firstspot);
					possiblemoves.add(y);
				}
				
				int ytoleft = --y;
				int ytoright = y+=2;
				
				if (ytoleft >0)
				{
					if (board[firstspot][ytoleft]!= null)
					{
						if (board[firstspot][ytoleft].getType() == 1)
						{
							possiblemoves.add(firstspot);
							possiblemoves.add(ytoleft);
						}
					}
				}
				
				if (ytoright < 8)
				{
					if (board[firstspot][ytoright] != null)
					{
						if (board[firstspot][ytoright].getType() == 1)
						{
							possiblemoves.add(firstspot);
							possiblemoves.add(ytoright);
						}
					}
				}
				
				
			}
			
			else
			{
				int firstspot = --save;
				if(board[firstspot][y] == null)
				{
					possiblemoves.add(firstspot);
					possiblemoves.add(y);
				}
				
				int ytoleft = --y;
				int ytoright = y+=2;
				
				if (ytoleft > 0)
				{
					if (board[firstspot][ytoleft]!= null)
					{
						if (board[firstspot][ytoleft].getType() == 0)
						{
							possiblemoves.add(firstspot);
							possiblemoves.add(ytoleft);
						}
					}
				}
				
				if (ytoright < 8)
				{
					if (board[firstspot][ytoright] != null)
					{
						if (board[firstspot][ytoright].getType() == 0)
						{
							possiblemoves.add(firstspot);
							possiblemoves.add(ytoright);
						}
					}
				}
				
			}
		}
	
		return possiblemoves;
		
	}
	
	public String toString()
	{
		return "PAWN";
	}
}
