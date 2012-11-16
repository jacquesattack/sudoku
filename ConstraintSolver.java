package sudoku;

import java.util.ArrayList;
import java.util.Collections;

public class ConstraintSolver {
	
	private SudokuBlock myBlock;
	
	public ConstraintSolver(SudokuBlock newBlock)
	{
		myBlock = newBlock;
	}

	public void solveBlock() 
	{
		System.out.println("Initial block:");
		myBlock.printBlock();
		/*
		 * Basic strategy is to try to use constraints whenever possible,
		 * and use brute force otherwise.
		 */
		SudokuSolverContainer container = useConstraints(myBlock);
		if (!myBlock.solved()) container = recursiveGuessing(container);
		System.out.println("Complete block:");
		container.getBlock().printBlock();
	}
	
	private SudokuSolverContainer recursiveGuessing(SudokuSolverContainer container) {
		SudokuSolverContainer oldState;
		int xysize = container.getXYPairs().size();
		if (xysize > 0) 
		{
			// sort the xy pairs by fewest possible values. this improves solution time by orders of magnitude
			Collections.sort(container.getXYPairs(),new XYPairComparator());
			XYPair pair = container.getXYPairs().get(0);
			int possiblesSize = pair.getPossibles().size();
			if (possiblesSize > 0)
			{
				oldState = new SudokuSolverContainer(container);
				for (int j = 0; j < possiblesSize; j++) {
					// try a value from possible values
					container.getBlock().setCellValue(pair.getX(), pair.getY(),
							pair.getPossibles().get(j));
					SudokuSolverContainer newState = useConstraints(container);
					if (newState.solved())
					{
						return new SudokuSolverContainer(newState);
					}
					else
					{
						container = recursiveGuessing(new SudokuSolverContainer(container.getBlock()));
						if (container.solved()) return container;
					}
					container = oldState;
				}
			}
		}
		return new SudokuSolverContainer(container);
	}

	private SudokuSolverContainer useConstraints(SudokuSolverContainer container)
	{
		return useConstraints(container.getBlock());
	}

	private SudokuSolverContainer useConstraints(SudokuBlock aBlock)
	{
		SudokuBlock block = SudokuBlock.copy(aBlock);
		XYPair xypair;
		boolean changes = true;
		ArrayList<XYPair> xypairs = new ArrayList<XYPair>();
		while (changes)
		{
			xypairs = new ArrayList<XYPair>();
			changes = false;
			for (int i = 1; i <= 9; i++)
			{
				for (int j = 1; j <= 9; j++)
				{
					ArrayList<String> possibles = block.getPossibles(i, j);
					if (possibles != null) 
					{
						xypair = new XYPair(i,j,possibles);
						xypairs.add(xypair);
						if (possibles.size() == 1)
						{
							block.setCellValue(i, j, possibles.get(0));
							changes = true;
						}
					}
				}
			}
		}
		
		SudokuSolverContainer container = new SudokuSolverContainer(block,xypairs);
		return container;
	}
	

}
