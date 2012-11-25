package sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DLListConstructor {
	
	private HashMap nodes;
	private HashMap rowData;
	private HashMap colData;
	
	public static void main(String args[])
	{
		new DLListConstructor();
	}
	
	/*
	 * again, there must be a better way to do this, but atm I can't think of one...
	 */
	public DLListConstructor()
	{
		rowData = new HashMap();
		colData = new HashMap();
		nodes = createUnlinkedList();
		DLNode root = linkList(nodes);
		printNodeSize(nodes);
	}

	/*
	 * using unlinked list, link the nodes
	 */
	private DLNode linkList(HashMap nodes) {
		/*
		 * TODO -1 or 0??
		 */
		for(int i = -1; i < 729; i++)
		{
			for(Object j : ((HashMap) nodes.get(i)).keySet())
			{
				DLNode node = (DLNode) ((HashMap) nodes.get(i)).get(j);
				node.setDown(getNextDown(node.getX(),node.getY()));
				node.setUp(getNextUp(node.getX(),node.getY()));
				node.setRight(getNextRight(node.getX(),node.getY()));
				node.setLeft(getNextLeft(node.getX(),node.getY()));	
			}

		}
		/*
		 * set header
		 */
		DLNode right = (DLNode) ((HashMap) nodes.get(-1)).get(0);
		DLNode left = (DLNode) ((HashMap) nodes.get(-1)).get(728);
		DLNode root = new DLNode(-1,-1);
		root.setRight(right);
		root.setLeft(left);
		root.getRight().getRight().print();
		root.getRight().getRight().getRight().getDown().print();
		return root;
	}

	private DLNode getNextLeft(int x, int y) {
		DLNode node = null;
		ArrayList<Integer> row = (ArrayList<Integer>) rowData.get(x);
		int nextY = row.indexOf(y)-1;
		if(nextY > -1)
		{
			nextY = row.get(nextY);
			node = (DLNode) ((HashMap) nodes.get(x)).get(nextY);
		}
		else
		{
			node = (DLNode) ((HashMap) nodes.get(x)).get(row.size()-1);
		}
		return node;
	}

	private DLNode getNextRight(int x, int y) {
		DLNode node = null;
		ArrayList<Integer> row = (ArrayList<Integer>) rowData.get(x);
		int nextY = row.indexOf(y)+1;
		if(nextY < row.size())
		{
			nextY = row.get(nextY);
			node = (DLNode) ((HashMap) nodes.get(x)).get(nextY);
		}
		else
		{
			node = (DLNode) ((HashMap) nodes.get(x)).get(0);
		}
		return node;
	}

	private DLNode getNextUp(int x, int y) {
		DLNode node = null;
		ArrayList<Integer> col = (ArrayList<Integer>) colData.get(y);
		int nextX = col.indexOf(x)-1;
		if(nextX > -1)
		{
			nextX = col.get(nextX);
			node = (DLNode) ((HashMap) nodes.get(nextX)).get(y);
		}
		else // return last node in list
		{
			node = (DLNode) ((HashMap) nodes.get(col.get(col.size()-1))).get(y);
		}
		return node;
	}

	private DLNode getNextDown(int x, int y) {
		DLNode node = null;
		ArrayList<Integer> col = (ArrayList<Integer>) colData.get(y);
		int nextX = col.indexOf(x)+1;
		if(nextX < col.size())
		{
			nextX = col.get(nextX);
			//System.out.println("next x = " + nextX + ", y = " + y);
			node = (DLNode) ((HashMap) nodes.get(nextX)).get(y);
			//System.out.println(node == null);
		}
		else // return first node in list (header)
		{
			node = (DLNode) ((HashMap) nodes.get(-1)).get(y);
		}
		return node;
	}

	private HashMap createUnlinkedList() {
		HashMap nodes = new HashMap();
		nodes.put(-1, new HashMap());
		rowData.put(-1, new ArrayList<Integer>());
		colData.put(-1, new ArrayList<Integer>());
		((ArrayList<Integer>)rowData.get(-1)).add(-1);
		((ArrayList<Integer>)colData.get(-1)).add(-1);
		for(int i = 0; i < 324; i ++)
		{
			((HashMap) nodes.get(-1)).put(i,new DLNode(-1,i));
			((ArrayList<Integer>)rowData.get(-1)).add(i);
			colData.put(i, new ArrayList<Integer>());
			((ArrayList<Integer>)colData.get(i)).add(-1);
		}
		int[][] matrix = DLMatrix.createMatrix();
		for(int i = 0; i < 729; i++)
		{
			nodes.put(i, new HashMap());
			if (rowData.get(i) == null) rowData.put(i, new ArrayList<Integer>());
			for(int j = 0; j < 324; j++)
			{
				if (matrix[i][j] == 0) continue;
				((HashMap) nodes.get(i)).put(j,new DLNode(i,j));
				((ArrayList<Integer>)rowData.get(i)).add(j);
				((ArrayList<Integer>)colData.get(j)).add(i);
			}
		}
		return nodes;
	}

	private void printNodeSize(HashMap nodes) {
		int size = 0;
		for(int i = -1; i < nodes.size()-1; i++)
		{
			size += ((HashMap) nodes.get(i)).size();
		}
		System.out.println(size);
	}

}
