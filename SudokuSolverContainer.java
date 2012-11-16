package sudoku;

import java.util.ArrayList;

public class SudokuSolverContainer {
	
	private SudokuBlock block;
	private ArrayList<XYPair> xypairs;
	
	public SudokuSolverContainer(SudokuBlock newBlock, ArrayList<XYPair> newxypairs)
	{
		this.block = SudokuBlock.copy(newBlock);
		ArrayList<XYPair> pairs = new ArrayList<XYPair>();
		for (XYPair pair : newxypairs)
		{
			pairs.add(XYPair.copy(pair));
		}
		this.xypairs = pairs;
	}
	
	public SudokuSolverContainer(SudokuBlock newBlock)
	{
		this.block = SudokuBlock.copy(newBlock);
		this.xypairs = this.block.getAllPossibles();
	}
	
	public SudokuSolverContainer(SudokuSolverContainer oldContainer)
	{
		this(oldContainer.getBlock(),oldContainer.getXYPairs());
	}
	
	public static SudokuSolverContainer getInstance(SudokuSolverContainer oldContainer)
	{
		return new SudokuSolverContainer(oldContainer);
	}
	
	public SudokuBlock getBlock()
	{
		return this.block;
	}
	
	public void setBlock(SudokuBlock newBlock)
	{
		this.block = newBlock;
	}
	
	public ArrayList<XYPair> getXYPairs()
	{
		return this.xypairs;
	}
	
	public void setXYPairs(ArrayList<XYPair> newPairs)
	{
		this.xypairs = newPairs;
	}

	public boolean solved() {
		return this.getBlock().solved();
	}
}
