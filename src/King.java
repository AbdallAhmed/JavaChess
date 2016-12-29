import java.util.ArrayList;

public class King extends Piece{

	public King(int t,boolean b, int x, int y,int iv)
	{
		super (t,b,x,y,iv);
		
	}
	
	public ArrayList<Integer> move(int x, int y, Piece[][] b)
	{
		Piece[][] board = b;
		Piece p = board[x][y];
		ArrayList<Integer> possiblemoves = new ArrayList<Integer>();
	
		int startx = --x;
		int starty = --y;
		int stopx = x+=2;
		int stopy = y+=2;
		
		for (int ix = startx; ix < stopx +1; ix++)
		{
			for (int iy = starty; iy < stopy +1; iy++)
			{
				if (ix > -1 && ix < 8 && iy>-1 && iy<8)
				{
					if (board[ix][iy] == null)
					{
						possiblemoves.add(ix);
						possiblemoves.add(iy);
					}
					
					else if (board[ix][iy] != null)
					{
						if(p.getType() != board[ix][iy].getType())
						{
							possiblemoves.add(ix);
							possiblemoves.add(iy);
						}
					}
					
				}
				
			}
		}
		return possiblemoves;
	}
		
	public String toString()
	{
		return "KING";
	}
}
