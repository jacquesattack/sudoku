package sudoku;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DLMatrix{
	
	public static void main(String args[]) throws IOException
	{
		//String file = args[0];
		//SudokuBlock newBlock = new SudokuBlock(file);
		//String output = args[0];
		createMatrix();
	}
	
	public static int[][] createMatrix()
	{
		int[][] matrix = new int[729][324];
		for (int value = 0; value < 9; value++)
		{
			for(int rownum = 0; rownum < 9; rownum++)
			{
				for (int colnum = 0; colnum < 9; colnum++)
				{
					int index = (81*value) + rownum + (colnum * 9);
					// first, fill the row with 0's
					Arrays.fill(matrix[index], 0);
					// constraint 1: there is a number in position (rownum,colnum)
					matrix[index][rownum + colnum] = 1;
					// constraint 2: there is number value in row rownum
					matrix[index][81 + rownum + (value * 9)] = 1;
					// constraint 3: there is number value in col colnum
					matrix[index][81*2 + colnum + (value * 9)] = 1;
					/*
					 *  constraint 4: there is number value in block x
					 *  blocks go 1-9 from left to right, up to down:
					 *  0 1 2
					 *  3 4 5
					 *  6 7 8
					 */
					int blocknum = getBlockNum(rownum,colnum);
					matrix[index][81*3 + blocknum + (value * 9)] = 1;
				}
			}
		}
		//matrix = deleteKnownConstraints(block,matrix);
		//printMatrix(matrix,output);
		return matrix;
	}

	//TODO 
	private static int[][] deleteKnownConstraints(SudokuBlock block,
			int[][] matrix) {
		return matrix;
	}

	private static void printMatrix(int[][] matrix,String filename) {
		try {
			FileWriter writer = new FileWriter(filename);
			for(int[] row : matrix)
			{
				StringBuilder output = new StringBuilder();
				for(int val : row)
				{
					output.append(val);
					output.append(",");
				}
				output.deleteCharAt(output.lastIndexOf(","));
				writer.append(output);
				writer.append("\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("wrote to file successfully");
	}

	/*
	 * there has got to be a better way to get the block number...
	 */
	private static int getBlockNum(int rownum, int colnum) {
		int blocknum = 0;
		
		ArrayList<Integer> first = new ArrayList<Integer>();
		first.add(0);
		first.add(1);
		first.add(2);
		
		ArrayList<Integer> second = new ArrayList<Integer>();
		second.add(3);
		second.add(4);
		second.add(5);
	
		// first row
		if(first.contains(rownum) && first.contains(colnum))
		{
			blocknum = 0;
		}
		if(first.contains(rownum) && second.contains(colnum))
		{
			blocknum = 1;
		}
		if(first.contains(rownum) && !first.contains(colnum) && !second.contains(colnum))
		{
			blocknum = 2;
		}
	
		// second row
		if(second.contains(rownum) && first.contains(colnum))
		{
			blocknum = 3;
		}
		if(second.contains(rownum) && second.contains(colnum))
		{
			blocknum = 4;
		}
		if(second.contains(rownum) && !first.contains(colnum) && !second.contains(colnum))
		{
			blocknum = 5;
		}
		
		// third row
		if(!first.contains(rownum) && !second.contains(rownum) && first.contains(colnum))
		{
			blocknum = 6;
		}
		if(!first.contains(rownum) && !second.contains(rownum) && second.contains(colnum))
		{
			blocknum = 7;
		}
		if(!first.contains(rownum) && !second.contains(rownum) && !first.contains(colnum) && !second.contains(colnum))
		{
			blocknum = 8;
		}
		
		return blocknum;
	}
}
