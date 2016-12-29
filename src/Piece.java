public class Piece {

	private boolean inPlay = true;
	private int type;
	private int posx;
	private int posy;
	private int indexval;
	
	//this class is very barren, but is used to created a 2D array that holds every piece
	public Piece(int i, boolean b, int x, int y, int iv)
	{
		inPlay = b;
		type = i;
		indexval = iv;
		posx = x;
		posy = y;
	}
	
	public Piece()
	{
		
	}
	
	public int getType()
	{
		return type;
	}
	
	public int getXCoord()
	{
		return posx;
	}
	
	public int getYCoord()
	{
		return posy;
	}
	
	public int getIndexVal()
	{
		return indexval;
	}
}
