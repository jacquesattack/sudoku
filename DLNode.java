package sudoku;

public class DLNode {
	
	private DLNode up;
	private DLNode down;
	private DLNode right;
	private DLNode left;
	private DLNode header;
	
	private int x;
	private int y;
	
	public DLNode(int newx, int newy)
	{
		setX(newx);
		setY(newy);
	}
	
	/*
	 * getters
	 */
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public DLNode getUp()
	{
		return up;
	}
	
	public DLNode getDown()
	{
		return down;
	}

	public DLNode getRight()
	{
		return right;
	}
	
	public DLNode getLeft()
	{
		return left;
	}
	
	public DLNode getHeader()
	{
		return header;
	}
	
	/*
	 * setters
	 */
	
	public void setX(int newx)
	{
		x = newx;
	}
	
	public void setY(int newy)
	{
		y = newy;
	}
	
	public void setUp(DLNode node)
	{
		up = node;
	}
	
	public void setDown(DLNode node)
	{
		down = node;
	}
	
	public void setRight(DLNode node)
	{
		right = node;
	}
	
	public void setLeft(DLNode node)
	{
		left = node;
	}
	
	public void setHeader(DLNode node)
	{
		header = node;
	}
	
	/*
	 * cover and uncover
	 */
	
	public void sideCover()
	{
		this.getRight().setLeft(this.getLeft());
		this.getLeft().setRight(this.getRight());
	}
	
	public void sideUncover()
	{
		this.getRight().setLeft(this);
		this.getLeft().setRight(this);
	}
	
	public void upDownCover()
	{
		this.getUp().setDown(this.getDown());
		this.getDown().setUp(this.getUp());
	}
	
	public void upDownUncover()
	{
		this.getUp().setDown(this);
		this.getDown().setUp(this);
	}
	
	/*
	 * print method
	 */
	
	public void print()
	{
		System.out.println("x = " + x + ", y = " + y);
	}
	
}
