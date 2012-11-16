package sudoku;

import java.util.ArrayList;

public class XYPair {
	
	private int x;
	private int y;
	private ArrayList<String> possibles;
	
	public XYPair(int first, int second, ArrayList<String> newPossibles)
	{
		x = first;
		y = second;
		possibles = newPossibles;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public ArrayList<String> getPossibles()
	{
		return this.possibles;
	}
	
	public void print()
	{
		System.out.println(x);
		System.out.println(y);
		System.out.println(SudokuBlock.arrayToString(possibles));
		System.out.println("\n");
	}
	
	public static XYPair copy(XYPair xypair)
	{
		int x = xypair.getX();
		int y = xypair.getY();
		ArrayList<String> poss = new ArrayList<String>();
		for(String s : xypair.getPossibles())
		{
			poss.add(s);
		}
		return new XYPair(x,y,poss);
	}

}
