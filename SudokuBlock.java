package sudoku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SudokuBlock{
	
	ArrayList<ArrayList<String>> rowData = new ArrayList<ArrayList<String>>();


	public ArrayList<ArrayList<String>> getRowData()
	{
		return this.rowData;
	}
	
	// constructor
	public SudokuBlock(String fileName) throws IOException
	{
		rowData = CSVReader.readSudokuCSV(fileName);
	}
	
	public SudokuBlock(ArrayList<ArrayList<String>> newRowData)
	{
		this.rowData = newRowData;
	}
	
	public void setRowData(ArrayList<ArrayList<String>> newRowData)
	{
		this.rowData = newRowData;
	}
	
	public void printBlock()
	{
		for (ArrayList<String> strArray : rowData)
		{
			System.out.println(arrayToString(strArray));
		}
		System.out.println("\n");
	}
	
	/*
	 * x is row
	 * y is column
	 */
	public ArrayList<String> getConstraints(int x, int y)
	{
		// subtract one so that we can use intuitive numbers
		x -= 1;
		y -= 1;
		
		String cell = rowData.get(x).get(y);
		if (!cell.equals("0")) return null;
		
		ArrayList<String> constraints = new ArrayList<String>();
		// get row constraints
		for (String rowval : rowData.get(x))
		{
			constraints = addToConstraints(constraints,rowval);
		}
		
		// get column constraints
		for (ArrayList<String> row : rowData)
		{
			String colval = row.get(y);
			constraints = addToConstraints(constraints,colval);
		}
		
		// get small block constraints
		int[] xvals = getMiniBlockVals(x);
		int[] yvals = getMiniBlockVals(y);
		for (int i : xvals)
		{
			ArrayList<String> row = rowData.get(i);
			for (int j : yvals)
			{
				String cellVal = row.get(j);
				constraints = addToConstraints(constraints,cellVal);
			}
		}
		
		return constraints;
	}
	
	private int[] getMiniBlockVals(int val)
	{
		int[] retvals = new int[3];
		
		ArrayList<Integer> first = new ArrayList<Integer>();
		first.add(0);
		first.add(1);
		first.add(2);
		
		ArrayList<Integer> second = new ArrayList<Integer>();
		second.add(3);
		second.add(4);
		second.add(5);
		
		if (first.contains(val))
		{
			retvals[0] = 0;
			retvals[1] = 1;
			retvals[2] = 2;
		}
		else if (second.contains(val))
		{
			retvals[0] = 3;
			retvals[1] = 4;
			retvals[2] = 5;
		}
		else
		{
			retvals[0] = 6;
			retvals[1] = 7;
			retvals[2] = 8;
		}
		
		return retvals;
	}
	
	private ArrayList<String> addToConstraints(ArrayList<String> constraints, String val)
	{
		if(!constraints.contains(val) && !val.equals("0")) constraints.add(val);
		return constraints;
	}
	
	public void printConstraints(int x, int y)
	{
		ArrayList<String> constraints = getConstraints(x,y);
		if (constraints == null) return;
		System.out.println("    Constraints for cell (" + x + "," + y + "): " +arrayToString(constraints));
		ArrayList<String> possibleVals = getPossibles(x,y);
		System.out.println("Possible values for cell (" + x + "," + y + "): " +arrayToString(possibleVals) + "\n");
	}
	
	public ArrayList<String> getPossibles(int x, int y)
	{
		ArrayList<String> allVals = new ArrayList<String>();
		allVals.add("1");
		allVals.add("2");
		allVals.add("3");
		allVals.add("4");
		allVals.add("5");
		allVals.add("6");
		allVals.add("7");
		allVals.add("8");
		allVals.add("9");
		
		ArrayList<String> constraints = getConstraints(x,y);
		if (constraints == null) return null;
		ArrayList<String> possibleVals = new ArrayList<String>();
		for (String c : allVals)
		{
			if (!constraints.contains(c)) possibleVals.add(c);
		}
		
		return possibleVals;
		
	}
	
	public static StringBuilder arrayToString(ArrayList<String> ar)
	{
		StringBuilder retstr = new StringBuilder();
		Iterator<?> iter = ar.iterator();
		while (iter.hasNext())
		{
			Object ele = iter.next();
			retstr.append(ele);
			if(iter.hasNext()) retstr.append(",");
		}
		return(retstr);
	}
	
	public void setCellValue(int x, int y, String value)
	{
		x -= 1;
		y -= 1;
		this.rowData.get(x).set(y, value);
	}
	
	public boolean solved()
	{
		for (ArrayList<String> row : rowData)
		{
			for (String s : row)
			{
				if (s.equals("0")) return false;
			}
		}
		return true;
	}
	
	public boolean blockEquals(SudokuBlock newBlock)
	{
		if (newBlock == null) return false;
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				String thisCell = rowData.get(i).get(j);
				String compareCell = newBlock.getRowData().get(i).get(j);
				if (thisCell.equals(compareCell)) return false;
			}
		}
		return true;
	}

	public static SudokuBlock copy(SudokuBlock aBlock) {
		ArrayList<ArrayList<String>> newRowData = new ArrayList<ArrayList<String>>();
		for(ArrayList<String> row : aBlock.getRowData())
		{
			ArrayList<String> newRow = new ArrayList<String>();
			for(String s : row)
			{
				newRow.add(s);
			}
			newRowData.add(newRow);
		}
		return new SudokuBlock(newRowData);
	}

	public ArrayList<XYPair> getAllPossibles() {
		ArrayList<XYPair> xypairs = new ArrayList<XYPair>();
		XYPair xypair;
		for (int i = 1; i <= 9; i++)
		{
			for (int j = 1; j <= 9; j++)
			{
				ArrayList<String> possibles = this.getPossibles(i, j);
				if (possibles != null) 
				{
					xypair = new XYPair(i,j,possibles);
					xypairs.add(xypair);
				}
			}
		}
		return xypairs;
	}

}
