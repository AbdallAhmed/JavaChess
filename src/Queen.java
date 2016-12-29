import java.util.ArrayList;


public class Queen extends Piece {

	public Queen(int t,boolean b, int x, int y,int iv)
	{
		super (t,b,x,y, iv);
		
	}
	
	public ArrayList<Integer> move(int x, int y, Piece[][] b)
	{
		Piece[][] board = b;
		Piece p = board[x][y];
		ArrayList<Integer> possiblemoves = new ArrayList<Integer>();
		
		int ogx = x;
		int ogy = y;
		
		int x1 = ++x;
		int x2 = x-=2;
		
		int y1 = ++y;
		int y2 = y-=2;
		boolean stopchecking = false;
		
		while(x1 < 8 && !stopchecking)
		{
			if (board[x1][ogy] == null)
			{
				possiblemoves.add(x1);
				possiblemoves.add(ogy);
			}
			
			else if(p.getType() != board[x1][ogy].getType())
			{
				possiblemoves.add(x1);
				possiblemoves.add(ogy);
				stopchecking = true;
			}
			else stopchecking = true;
			
			x1++;
		}
		
		stopchecking = false;
		while(x2 > -1 && !stopchecking)
		{
			if (board[x2][ogy] == null)
			{
				possiblemoves.add(x2);
				possiblemoves.add(ogy);
			}
			
			else if(p.getType() != board[x2][ogy].getType())
			{
				possiblemoves.add(x2);
				possiblemoves.add(ogy);
				stopchecking = true;
			}
			else stopchecking = true;
			
			x2--;
		}
		
		stopchecking = false;
		while(y1 < 8 && !stopchecking)
		{
			if (board[ogx][y1] == null)
			{
				possiblemoves.add(ogx);
				possiblemoves.add(y1);
			}
			
			else if(p.getType() != board[ogx][y1].getType())
			{
				possiblemoves.add(ogx);
				possiblemoves.add(y1);
				stopchecking = true;
			}
			else stopchecking = true;
			
			y1++;
		}
		
		stopchecking = false;
		while(y2 > -1 && !stopchecking)
		{
			if (board[ogx][y2] == null)
			{
				possiblemoves.add(ogx);
				possiblemoves.add(y2);
			}
			
			else if(p.getType() != board[ogx][y2].getType())
			{
				possiblemoves.add(ogx);
				possiblemoves.add(y2);
				stopchecking = true;
			}
			else stopchecking = true;
			
			y2--;
		}
		
		int nx1 = ++ogx;
		int nx2 = ogx-=2;
		int ny1 = ++ogy;
		int ny2 = ogy-=2;
		stopchecking = false;
		
		int movex = nx1;
		int movey = ny1;
		
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
		
		movex = nx1;
		movey = ny2;
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
		
		movex = nx2;
		movey = ny1;
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
		
		movex = nx2;
		movey = ny2;
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
		return "QUEEN";
	}
}
