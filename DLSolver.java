package sudoku;

import java.util.HashSet;
import java.util.Set;

public class DLSolver {
	
	DLNode root;
	Set<Integer> solutions;
	
	public static void main(String args[])
	{
		DLSolver solver = new DLSolver();
		long t1 = System.currentTimeMillis();
		solver.solve();
		long t2 = System.currentTimeMillis();
		System.out.println("Total time: " + (t2-t1) + "ms");
	}
	
	public DLSolver()
	{
		
	}
	
	public void solve()
	{
		solutions = new HashSet<Integer>();
		DLListConstructor list = new DLListConstructor();
		DLNode root = list.getRoot();
		setRoot(root);
		//printAllNodes();
		recurse(root.getRight().getDown());
		
		System.out.println("Solutions:");
		System.out.println(solutions.toString());
	}
	
	public void printAllNodes()
	{
		DLNode down;
		DLNode right = root.getRight();
		while(right != root)
		{
			//right.print();
			down = right.getDown();
			while(down != right)
			{
				//down.print();
				down = down.getDown();
			}
			right = right.getRight();
		}
	}
	
	public void setRoot(DLNode newRoot)
	{
		root = newRoot;
	}
	
	/*
	 * recursive methods below
	 */

	private void recurse(DLNode node) {
		//node.print();
		solutions.add(node.getX());
		coverAll(node);
		if(solved()) return;
		if (hasNext()) {
			recurse(next());
		} else {
			solutions.remove(node.getX());
			uncoverAll(node);
		}
		
		if(!solved())
		{
			uncoverAll(node);
			solutions.remove(node.getX());
			if(hasDown(node))
			{
				recurse(node.getDown());
			}
		}

	}

	private boolean hasDown(DLNode node) {
		return node.getDown().getX() != -1;
	}

	private boolean solved() {
		return root.getRight() == root;
	}

	private DLNode next() {
		DLNode next = root.getRight().getDown();
		return next;
	}

	private boolean hasNext() {
		boolean hasNext = false;
		if(root.getRight() != root && root.getRight().getDown() != root.getRight()) 
			hasNext = true;
		return hasNext;
	}

	//TODO
	private void uncoverAll(DLNode node) {
		node.getHeader().sideUncover();
		uncoverAllDown(node);
		uncoverAllUp(node);
		DLNode right = node.getRight();
		if(right.getY() > node.getY())
		{
			uncoverAll(right);
		}
	}

	private void uncoverAllUp(DLNode node) {
		DLNode up = node.getUp();
		if(up.getX() < node.getX() && up.getX() != -1)
		{
			uncoverAllRight(up);
			uncoverAllUp(up);
		}
		
	}

	private void uncoverAllRight(DLNode node) {
		DLNode right = node.getRight();
		while(right != node)
		{
			right.upDownUncover();
			right = right.getRight();
		}
	}

	private void uncoverAllDown(DLNode node) {
		DLNode down = node.getDown();
		if(down.getX() > node.getX())
		{
			uncoverAllRight(down);
			uncoverAllDown(down);
		}
	}

	private void coverAll(DLNode node) {
		/*
		 * printing
		 */
		//System.out.println("Covering all for node:");
		//node.print();
		
		// cover this header
		node.getHeader().sideCover();
		
		/*
		 * printing
		 */
		//System.out.println("Covered header ");
		//node.getHeader().print();
		
		coverAllDown(node);
		coverAllUp(node);
		DLNode right = node.getRight();
		
		/*
		 * printing
		 */
		//System.out.println("Next right is:");
		//right.print();
		if(right.getY() > node.getY())
		{
			coverAll(right);
		}
		//System.out.println("");
	}

	private void coverAllUp(DLNode node) {
		//System.out.println("covering up...");
		DLNode up = node.getUp();
		if(up.getX() < node.getX() && up.getX() != -1)
		{
			//System.out.println("Covering down:");
			//up.print();
			coverAllRight(up);
			coverAllUp(up);
		}
	}

	private void coverAllDown(DLNode node) {
		//System.out.println("covering down...");
		DLNode down = node.getDown();
		if(down.getX() > node.getX())
		{
			//System.out.println("Covering down:");
			//down.print();
			coverAllRight(down);
			coverAllDown(down);
		}
	}

	private void coverAllRight(DLNode node) {
		//System.out.println("covering right...");
		DLNode right = node.getRight();
		while(right != node)
		{
			right.upDownCover();
			//System.out.println("Just covered:" + right.getX() + "," + right.getY());
			right = right.getRight();
		}
	}

}
