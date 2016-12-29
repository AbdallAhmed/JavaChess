import java.util.ArrayList;


public class Bishop extends Piece {

	
	public Bishop(int t,boolean b, int x, int y,int iv)
	{
		super (t,b,x,y,iv);
		
	}
	
	public ArrayList<Integer> move(int x, int y, Piece[][] b)
	{
		Piece[][] board = b;
		Piece p = board[x][y];
		ArrayList<Integer> possiblemoves = new ArrayList<Integer>();
		
		int x1 = ++x;
		int x2 = x-=2;
		int y1 = ++y;
		int y2 = y-=2;
		boolean stopchecking = false;
		
		int movex = x1;
		int movey = y1;
		
		while (movex < 8 && movey < 8 && !stopchecking)
		{
			if (board[movex][movey] == null)
			{
				possiblemoves.add(movex);
				possiblemoves.add(movey);
			}
			
			else if(p.getType() != board[movex][movey].getType())
			{
				possiblemoves.add(movex);
				possiblemoves.add(movey);
				stopchecking = true;
			}
			else stopchecking = true;
			
			movex++;
			movey++;
		}
		
		movex = x1;
		movey = y2;
		stopchecking = false;
		
		while (movex < 8 && movey > -1 && !stopchecking)
		{
			if (board[movex][movey] == null)
			{
				possiblemoves.add(movex);
				possiblemoves.add(movey);
			}
			
			else if(p.getType() != board[movex][movey].getType())
			{
				possiblemoves.add(movex);
				possiblemoves.add(movey);
				stopchecking = true;
			}
			else stopchecking = true;
			
			movex++;
			movey--;
		}
		
		movex = x2;
		movey = y1;
		stopchecking = false;
		
		while (movex > -1 && movey < 8 && !stopchecking)
		{
			if (board[movex][movey] == null)
			{
				possiblemoves.add(movex);
				possiblemoves.add(movey);
			}
			
			else if(p.getType() != board[movex][movey].getType())
			{
				possiblemoves.add(movex);
				possiblemoves.add(movey);
				stopchecking = true;
			}
			else stopchecking = true;
			
			movex--;
			movey++;
		}
		
		movex = x2;
		movey = y2;
		stopchecking = false;
		
		while (movex > -1 && movey > -1 && !stopchecking)
		{
			if (board[movex][movey] == null)
			{
				possiblemoves.add(movex);
				possiblemoves.add(movey);
			}
			
			else if(p.getType() != board[movex][movey].getType())
			{
				possiblemoves.add(movex);
				possiblemoves.add(movey);
				stopchecking = true;
			}
			else stopchecking = true;
			
			movex--;
			movey--;
		}
		
		return possiblemoves;
	}
	
	public String toString()
	{
		return "BISHOP";
	}
}
