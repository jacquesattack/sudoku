package sudoku;

import java.util.Comparator;

public class XYPairComparator implements Comparator<XYPair>{

	@Override
	public int compare(XYPair arg0, XYPair arg1) {
		return arg0.getPossibles().size() - arg1.getPossibles().size();
	}

}
