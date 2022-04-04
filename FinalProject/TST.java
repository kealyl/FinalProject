public class TST {
	
	private TST root;
	
	TST() // constructor - empty TST
	{
		this.root = null;
	}
	
	public TST getRoot() //returns root of TST
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
