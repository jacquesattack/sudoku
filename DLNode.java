package sudoku;

public class DLNode {
	
	private DLNode up;
	private DLNode down;
	private DLNode right;
	private DLNode left;
	private DLNode header;
	
	public DLNode()
	{
		
	}
	
	/*
	 * getters
	 */
	
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
	
	public void cover()
	{
		this.getRight().setLeft(this.getLeft());
	}
	
	public void uncover()
	{
		this.getRight().setLeft(this);
		this.getLeft().setRight(this);
	}
	
}
