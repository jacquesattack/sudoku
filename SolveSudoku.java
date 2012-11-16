package sudoku;
import java.io.IOException;

public class SolveSudoku {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		String file = args[0];
		SudokuBlock block = new SudokuBlock(file);
		ConstraintSolver solver = new ConstraintSolver(block);
		long t1 = System.currentTimeMillis();
		solver.solveBlock();
		long t2 = System.currentTimeMillis();
		System.out.println("Total time: " + (t2-t1) + "ms");
	}
	

}
