import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Board implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private boolean black;
	private JButton grid[][] = new JButton[8][8];
	private Piece pieces[][] = new Piece[8][8];
	private Piece b[] = new Piece[16];
	private Piece w[] = new Piece [16];
	private boolean possiblemoves[][] = new boolean [8][8];
	int savecoordsx, savecoordsy;
	private int turn;
	
	public Board()
	{
		//taken from stack overflow. Apparently helps to generalize the GUI cross platform?
		try {
	        UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
			} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gui();
	}
	
	
	public void gui()
	{
		/*Setting up the initial GUI of the board.
		 * things like the frame and panel 
		 */
		frame = new JFrame("Abdalll's Chess Game");
		frame.setVisible(true);
		frame.setSize(600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(136,69,19));
		panel.setLayout(new GridLayout(8,8,0,0));
	
		
		/*Setting up the buttons of the GUI.
		 * Nested loop to help achieve the alternating colors
		 */
		for (int rows = 0; rows < 8; rows++)
		{
			if (rows % 2 != 0)
				black = true;
			else black = false;
			
			for (int cols = 0; cols < 8; cols++)
			{
				grid[rows][cols] = new JButton();
				grid[rows][cols].addActionListener(this);
				if (black == true)
				{
					//not actually black, the darker brown
					grid[rows][cols].setBackground(new Color(160,82,45));
					black = false;
				}
				else 
				{
					grid[rows][cols].setBackground(new Color(245,222,179));
					black = true;
				}
				
				panel.add(grid[rows][cols]);
			}
		}
		
		frame.add(panel);
		turn = 1;
	}
	
	public void setUpGame()
	{
		/*
		 * Adding all the pieces to the board. Since they all extend the object
		 * "Piece()" we are able to use their specific objects to fill our array
		 */
		for (int fill = 0; fill < 8; fill++)
		{
			pieces[1][fill] = new Pawn(0,true,1,fill,true,fill);
			grid[1][fill].setIcon(new ImageIcon("BlackPawn.png"));
			
			pieces[6][fill] = new Pawn(1,true,6,fill,true,fill);
			grid[6][fill].setIcon(new ImageIcon("WhitePawn.png"));
		}
		
		pieces[0][0] = new Rook(0,true,0,0,8);
		pieces[0][7] = new Rook(0,true,0,7,9);
		pieces[7][0] = new Rook(1,true,7,0,8);
		pieces[7][7] = new Rook(1,true,7,7,9);
		
		pieces[0][1] = new Knight(0,true,0,1,10);
		pieces[0][6] = new Knight(0,true,0,6,11);
		pieces[7][1] = new Knight(1,true,7,1,10);
		pieces[7][6] = new Knight(1,true,7,6,11);
		
		pieces[0][2] = new Bishop(0,true,0,2,12);
		pieces[0][5] = new Bishop(0,true,0,5,13);
		pieces[7][2] = new Bishop(1,true,7,2,12);
		pieces[7][5] = new Bishop(1,true,7,5,13);
		
		pieces[0][3] = new Queen(0,true,0,3,14);
		pieces[7][3] = new Queen(1,true,7,3,14);
		
		pieces[0][4] = new King(0,true,0,4,15);
		pieces[7][4] = new King(1,true,7,4,15);
		
		//Changing the icon of the buttons to their corresponding piece
		grid[0][0].setIcon(new ImageIcon("BlackRook.png"));
		grid[0][7].setIcon(new ImageIcon("BlackRook.png"));
		grid[7][0].setIcon(new ImageIcon("WhiteRook.png"));
		grid[7][7].setIcon(new ImageIcon("WhiteRook.png"));
		
		grid[0][1].setIcon(new ImageIcon("BlackKnight.png"));
		grid[0][6].setIcon(new ImageIcon("BlackKnight.png"));
		grid[7][1].setIcon(new ImageIcon("WhiteKnight.png"));
		grid[7][6].setIcon(new ImageIcon("WhiteKnight.png"));
		
		grid[0][2].setIcon(new ImageIcon("BlackBishop.png"));
		grid[0][5].setIcon(new ImageIcon("BlackBishop.png"));
		grid[7][2].setIcon(new ImageIcon("WhiteBishop.png"));
		grid[7][5].setIcon(new ImageIcon("WhiteBishop.png"));
		
		grid[0][3].setIcon(new ImageIcon("BlackQueen.png"));
		grid[7][3].setIcon(new ImageIcon("WhiteQueen.png"));
		
		grid[0][4].setIcon(new ImageIcon("BlackKing.png"));
		grid[7][4].setIcon(new ImageIcon("WhiteKing.png"));
		
		//these arrays are for the express purpose of checking if a player is in check
		//adding one by one may not be the best method but it worked
		for (int i = 0; i<8;i++)
		{
			b[i] = pieces[1][i];
			w[i] = pieces[6][i];
		}
		
		b[8] = pieces[0][0];
		b[9] = pieces[0][7];
		b[10] = pieces[0][1];
		b[11] = pieces [0][6];
		b[12] = pieces[0][2];
		b[13] = pieces [0][5];
		b[14] = pieces[0][3];
		b[15] = pieces[0][4];
		
		w[8] = pieces[7][0];
		w[9] = pieces[7][7];
		w[10] = pieces[7][1];
		w[11] = pieces [7][6];
		w[12] = pieces[7][2];
		w[13] = pieces [7][5];
		w[14] = pieces[7][3];
		w[15] = pieces[7][4];
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//Checks whenever a button on the board is pressed.
		for (int rows=0; rows<8; rows++)
		{
			for (int cols=0; cols<8; cols++)
			{
				if (e.getSource() == grid[rows][cols])
				{
					//we immediately redo the board because we have to get rid of any highlighted spots
					recolor();
					Piece p;
				
					//if a piece is selected and it's not a position a player wants to move to
					if (pieces[rows][cols] != null && possiblemoves[rows][cols] == false)
					{
						//JOptionPane.showMessageDialog(null, "There is a piece here");
						
						for (int r = 0; r<8; r++)
							for (int c = 0; c<8; c++)
								possiblemoves[r][c] = false;
						
						p = pieces[rows][cols];
						
						//turn check
						if (p.getType() != turn)
						{
							JOptionPane.showMessageDialog(null, "It is not your turn");
						}
						
						else
						{
							//we keep the coordinates just in case the player wants to move it
							savecoordsx = p.getXCoord();
							savecoordsy = p.getYCoord();
							ArrayList<Integer> spots = new ArrayList<Integer>();
							//LONG line of if statements that checks what kind of piece we have found
							//by using the to-string method of each piece
							
							switch(p.toString())
							{
							case "PAWN":
								Pawn pa = (Pawn)p;
								spots = pa.move(rows, cols, pieces);
								break;
								
							case "KNIGHT":
								Knight  n = (Knight)p;
								spots = n.move(rows, cols, pieces);
								break;
								
							case "ROOK":
								Rook r = (Rook)p;
								spots = r.move(rows, cols, pieces);
								break;
								
							case "BISHOP":
								Bishop b = (Bishop)p;
								spots = b.move(rows, cols, pieces);
								break;
								
							case "QUEEN":
								Queen q = (Queen)p;
								spots = q.move(rows, cols, pieces);
								break;
								
							case "KING":
								King k = (King)p;
								spots = k.move(rows, cols, pieces);
								break;
								
							default: break;
							}
							
							paintSpots(spots);
						}
						
					}
					
					//if-statement that deals with when a player wants to move
					if (possiblemoves[rows][cols] == true)
					{
						//important to save the old piece, still need old info from it
						Piece pie = pieces[savecoordsx][savecoordsy];
						
						//deleting the old piece from the arrays (our Piece object still exists)
						grid[savecoordsx][savecoordsy].setIcon(null);
						pieces[savecoordsx][savecoordsy] = null;
					
						//what happens when you move onto a space that had a piece
						if (pieces[rows][cols] != null)
						{
							Piece old = pieces[rows][cols];
							
							if (old.getType() == 0)
								b[old.getIndexVal()] = null;
							else w[old.getIndexVal()] = null;
							
							pieces[rows][cols] = null;
						}
						
						//after someone selects a piece they will in theory click a spot they can move to
						if (pie.toString().equals("PAWN"))
						{
							//reset the pawn
							pieces[rows][cols] = new Pawn(pie.getType(),true, rows,cols,false,pie.getIndexVal());
							if (pie.getType() == 0)	
								grid[rows][cols].setIcon(new ImageIcon("BlackPawn.png"));
							else 
								grid[rows][cols].setIcon(new ImageIcon("WhitePawn.png"));
						
						}
						
						if (pie.toString().equals("KNIGHT"))
						{
							//reset the knight
							pieces[rows][cols] = new Knight(pie.getType(),true,rows,cols,pie.getIndexVal());
							if (pie.getType() == 0)	
								grid[rows][cols].setIcon(new ImageIcon("BlackKnight.png"));
							else 
								grid[rows][cols].setIcon(new ImageIcon("WhiteKnight.png"));
						
						}
						
						if (pie.toString().equals("ROOK"))
						{
							//reset the rook
							pieces[rows][cols] = new Rook(pie.getType(),true,rows,cols,pie.getIndexVal());
							if (pie.getType() == 0)	
								grid[rows][cols].setIcon(new ImageIcon("BlackRook.png"));
							else 
								grid[rows][cols].setIcon(new ImageIcon("WhiteRook.png"));
						
						}
						
						if (pie.toString().equals("BISHOP"))
						{
							//reset the bishop
							pieces[rows][cols] = new Bishop(pie.getType(),true,rows,cols,pie.getIndexVal());
							if (pie.getType() == 0)	
								grid[rows][cols].setIcon(new ImageIcon("BlackBishop.png"));
							else 
								grid[rows][cols].setIcon(new ImageIcon("WhiteBishop.png"));
						
						}
						
						if (pie.toString().equals("QUEEN"))
						{
							//reset the queen
							pieces[rows][cols] = new Queen(pie.getType(),true,rows,cols,pie.getIndexVal());
							if (pie.getType() == 0)	
								grid[rows][cols].setIcon(new ImageIcon("BlackQueen.png"));
							else 
								grid[rows][cols].setIcon(new ImageIcon("WhiteQueen.png"));
						
						}
						
						if (pie.toString().equals("KING"))
						{
							//reset the king
							pieces[rows][cols] = new King(pie.getType(),true,rows,cols,pie.getIndexVal());
							if (pie.getType() == 0)	
								grid[rows][cols].setIcon(new ImageIcon("BlackKing.png"));
							else 
								grid[rows][cols].setIcon(new ImageIcon("WhiteKing.png"));
						
						}
						
						
						//this controls the arrays that exist for the express purpose of checking check
						//each piece holds it's corresponding index value in the array so we can easily
						//change the array to match the new piece
						if (pie.getType() == 0)
						{
							b[pie.getIndexVal()] = pieces[rows][cols];
						}
						else w[pie.getIndexVal()] = pieces[rows][cols];
									
					
						//dealing with turns
						if (turn == 0)
						{
							checkCheck(0);
							turn = 1;
						}
						else 
						{
							checkCheck(1);
							turn = 0;
						}
						

						for (int r = 0; r<8; r++)
							for (int c = 0; c<8; c++)
								possiblemoves[r][c] = false;
						
						
					}
					
				}
			}
		}
	}
	
	//method created to recolor the board after highlighting avaiable moves
	private void recolor()
	{
		boolean b = false;
		for (int rows = 0; rows < 8; rows++)
		{
			if (rows % 2 != 0)
				b = true;
			else b = false;
			
			for (int cols = 0; cols < 8; cols++)
			{
				if (b == true)
				{
					grid[rows][cols].setBackground(new Color(160,82,45));
					b = false;
				}
				else 
				{
					grid[rows][cols].setBackground(new Color(245,222,179));
					b = true;
				}
			}
		}
		
		panel.repaint();
	}
	
	public Piece[][] getBoard()
	{
		return pieces;
	}
	
	//this takes each pieces possible moves and does the awkward traversal that was aforementioned
	public void paintSpots(ArrayList<Integer> s)
	{
		for (int x = 0; x<s.size(); x++)
		{ 
			int xpos = x;
			int ypos = ++x;
			int ax = s.get(xpos);
			int ay = s.get(ypos);
			//highlight an available move
			grid[ax][ay].setBackground(new Color(224,255,255));
			
			possiblemoves[ax][ay] = true;
			//paint the board to match the new found moves
			panel.repaint();
			
		}
		
		
	}
	
	//extremely overdone method to check the check
	private void checkCheck(int t)
	{
		ArrayList<Integer> allmoves = new ArrayList<Integer>();
		
		if (t == 0)
		{
			//check the entire array of pieces and save EVERY possible move from one team
			for (int i = 0; i <16; i++)
			{
				if (b[i] != null)
				{
					if (b[i].toString() == "PAWN")
					{
						Pawn p = (Pawn)b[i];
						allmoves.addAll(p.move(p.getXCoord(), p.getYCoord(), pieces));
					}
					
					if (b[i].toString() == "KNIGHT")
					{
						Knight p = (Knight)b[i];
						allmoves.addAll(p.move(p.getXCoord(), p.getYCoord(), pieces));
					}
					
					if (b[i].toString() == "ROOK")
					{
						Rook p = (Rook)b[i];
						allmoves.addAll(p.move(p.getXCoord(), p.getYCoord(), pieces));
					}
					
					if (b[i].toString() == "BISHOP")
					{
						Bishop p = (Bishop)b[i];
						allmoves.addAll(p.move(p.getXCoord(), p.getYCoord(), pieces));
					}
					
					if (b[i].toString() == "QUEEN")
					{
						Queen p = (Queen)b[i];
						allmoves.addAll(p.move(p.getXCoord(), p.getYCoord(), pieces));
					}
					
					if (b[i].toString() == "KING")
					{
						King p = (King)b[i];
						allmoves.addAll(p.move(p.getXCoord(), p.getYCoord(), pieces));
					}
				}
			}
		}
		
		else if (t == 1)
		{
			for (int i = 0; i <16; i++)
			{
				if (w[i] != null)
				{
					if (w[i].toString() == "PAWN")
					{
						Pawn p = (Pawn)w[i];
						allmoves.addAll(p.move(p.getXCoord(), p.getYCoord(), pieces));
					}
					
					if (w[i].toString() == "KNIGHT")
					{
						Knight p = (Knight)w[i];
						allmoves.addAll(p.move(p.getXCoord(), p.getYCoord(), pieces));
					}
					
					if (w[i].toString() == "ROOK")
					{
						Rook p = (Rook)w[i];
						allmoves.addAll(p.move(p.getXCoord(), p.getYCoord(), pieces));
					}
					
					if (w[i].toString() == "BISHOP")
					{
						Bishop p = (Bishop)w[i];
						allmoves.addAll(p.move(p.getXCoord(), p.getYCoord(), pieces));
					}
					
					if (w[i].toString() == "QUEEN")
					{
						Queen p = (Queen)w[i];
						allmoves.addAll(p.move(p.getXCoord(), p.getYCoord(), pieces));
					}
					
					if (w[i].toString() == "KING")
					{
						King p = (King)w[i];
						allmoves.addAll(p.move(p.getXCoord(), p.getYCoord(), pieces));
					}
				}
			}
		}
		
		//traverse every possible move, if one of them is the king, display a warning
		for (int x = 0; x<allmoves.size(); x++)
		{ 
			int xpos = x;
			int ypos = ++x;
			int ax = allmoves.get(xpos);
			int ay = allmoves.get(ypos);
			//highlight an available move
			
			if (pieces[ax][ay] != null)
			{
			if (pieces[ax][ay].getType() != t)
				if (pieces[ax][ay].toString() == "KING")
				{
					JOptionPane.showMessageDialog(null, "In Check");
					break;
				}
			
			}
		}
	}

	public static void main(String[] args)
	{
		Board B = new Board();
		B.setUpGame();
	}


	
}
