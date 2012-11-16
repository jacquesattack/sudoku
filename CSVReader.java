package sudoku;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * see example block2.csv to understand format of input
 */

public class CSVReader {
	
	public static ArrayList<ArrayList<String>> readSudokuCSV(String fileName) throws IOException{
		ArrayList<ArrayList<String>> sudokuBlock = new ArrayList<ArrayList<String>>();
		BufferedReader csvFile = 
		        new BufferedReader(new FileReader(fileName));
		String row = csvFile.readLine();
		
		while(row != null){
			//TODO for some reason open office is not saving csv files
			// with comma separators, so i'm using tab instead
			//String[] rowArray = row.split("\t");
			String[] rowArray = row.split(",");
			ArrayList<String> strArray = new ArrayList<String>();
			for (String s : rowArray){
				strArray.add(s);
			}
			sudokuBlock.add(strArray);
			row = csvFile.readLine();
		}
		
		csvFile.close();
		return sudokuBlock;
	}

}
