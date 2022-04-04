public class TST {
	
	private TSTNode root;
	
	TST() // constructor - empty TST
	{
		this.root = null;
	}
	
	public TSTNode getRoot() //returns root of TST
	{
		return root;
	}
	
	public boolean isEmptyTST() //returns true if empty TST
	{
		if(this.getRoot()==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	
	
	
	
}
