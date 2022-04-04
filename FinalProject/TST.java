import java.util.List;

import com.google.common.base.Strings;

public class TST {
	
	private TSTNode rootOfTree;	
	
	private enum directionOfNode //tells us direction we came from parent node to reach current node
	{
		LEFT,
		RIGHT,
		MIDDLE
	}
	
	TST() // constructor - empty TST
	{
		this.rootOfTree = null;
	}
	
	TST(final List<String> stops) //creates a TST with stops specified in list
	{
		for (String stop: stops)
		{
			this.insert(stop);
		}
	}
	
	private TSTNode insert(TSTNode node, final char[] stopName, final int i)
	{
		final char currentCharacter = stopName[i];
		if(node == null)
		{
			node = new TSTNode(currentCharacter);
		}
	}
	
	public TSTNode insert(final String string)
	{
		if((string == null) || (string.isEmpty()))
		{
			return null;
		}
		rootOfTree = insert(this.rootOfTree, string.toUpperCase().toCharArray(),0);
		return getRoot();
	}
	public TSTNode getRoot() //returns root of TST
	{
		return rootOfTree;
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
