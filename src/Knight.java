import java.util.ArrayList;

public class Knight extends Piece {

	
	public Knight(int t,boolean b, int x, int y,int iv)
	{
		super (t,b,x,y,iv);
		
	}
	
	public String toString() 
	{
		return "KNIGHT";
	}
	
	//incredibly awkward system that checks every position a knight can move
	public ArrayList<Integer> move(int x, int y, Piece[][] b)
	{
		Piece[][] board = b;
		Piece p = board[x][y];
		ArrayList<Integer> possiblemoves = new ArrayList<Integer>();
		
		int x1 = ++x;
		int x2 = ++x;
		int x3 = x-=3;
		int x4 = --x;
		
		int y1 = ++y;
		int y2 = ++y;
		int y3 = y-=3;
		int y4 = --y;
		
		if(x1 < 8)
		{
			if (y2 < 8)
			{	
				if (board[x1][y2] == null)
				{
					possiblemoves.add(x1);
					possiblemoves.add(y2);
				}
				
				else if (p.getType() != board[x1][y2].getType())
				{
					possiblemoves.add(x1);
					possiblemoves.add(y2);
				}
			}
			
			if (y4 > -1)
			{
				if (board[x1][y4] == null)
				{
					possiblemoves.add(x1);
					possiblemoves.add(y4);
				}
				
				else if (p.getType() != board[x1][y4].getType())
				{
					possiblemoves.add(x1);
					possiblemoves.add(y4);
				}
			}
		}
		
		if(x2 < 8)
		{
			if (y1 < 8)
			{
				if (board[x2][y1] == null)
				{
					possiblemoves.add(x2);
					possiblemoves.add(y1);
				}
				
				else if (p.getType() != board[x2][y1].getType())
				{
					possiblemoves.add(x2);
					possiblemoves.add(y1);
				}
			}
			
			if (y3 > -1)
			{
				if (board[x2][y3] == null)
				{
					possiblemoves.add(x2);
					possiblemoves.add(y3);
				}
				
				else if (p.getType() != board[x2][y3].getType())
				{
					possiblemoves.add(x2);
					possiblemoves.add(y3);
				}
			}
		}
		
		if(x3 > -1)
		{
			if (y2 < 8)
			{
				if (board[x3][y2] == null)
				{
					possiblemoves.add(x3);
					possiblemoves.add(y2);
				}
				
				else if (p.getType() != board[x3][y2].getType())
				{
					possiblemoves.add(x3);
					possiblemoves.add(y2);
				}
			}
			
			if (y4 > -1)
			{
				if (board[x3][y4] == null)
				{
					possiblemoves.add(x3);
					possiblemoves.add(y4);
				}
				
				else if (p.getType() != board[x3][y4].getType())
				{
					possiblemoves.add(x3);
					possiblemoves.add(y4);
				}
			}
		}
		
		if(x4 > -1)
		{
			if (y1 < 8)
			{
				if (board[x4][y1] == null)
				{
					possiblemoves.add(x4);
					possiblemoves.add(y1);
				}
				
				else if (p.getType() != board[x4][y1].getType())
				{
					possiblemoves.add(x4);
					possiblemoves.add(y1);
				}
			}
			
			if (y3 > -1)
			{
				if (board[x4][y3] == null)
				{
					possiblemoves.add(x4);
					possiblemoves.add(y3);
				}
				
				else if (p.getType() != board[x4][y3].getType())
				{
					possiblemoves.add(x4);
					possiblemoves.add(y3);
				}
			}
		}
		
		return possiblemoves;
	}
}
