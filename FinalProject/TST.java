import java.util.List;

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
		if(node.getChar() > currentCharacter)
		{
			node.left = insert(node.getLeftChild(), stopName, i);
		}
		else if(node.getChar() < currentCharacter)
		{
			node.right = insert(node.getRightChild(), stopName, i);
		}
		else
		{
			if((i+1) < stopName.length)
			{
				node.middle = insert(node.getMiddleChild(), stopName, i+1);
			}
			else
			{
				node.setWordEnd(true);
			}
		}
		return node;
	}
	
	public TSTNode insert(final String string) //inserts a string into TST
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
	
	
	//search for word (bus stop name) in the TST
	private boolean search(final TSTNode node, final char[] stopName, final int i)
	{
		final char currentCharacter = stopName[i];
		if(node == null)
		{
			return false;
		}
		if(node.getChar() < currentCharacter)
		{
			return search(node.getRightChild(), stopName, i);
		}
		else if(node.getChar() > currentCharacter)
		{
			return search(node.getLeftChild(), stopName, i);
		}
		else
		{
			if(i == (stopName.length-1))
			{
				return node.isWordEnd();
			}
			return search(node.getMiddleChild(), stopName, i+1);
		}
	}
	public boolean search(final String searchWord)
	{
		if((searchWord == null) || (searchWord.isEmpty()))
		{
			return false;
		}
		boolean searchResult = search(getRoot(), searchWord.toUpperCase().toCharArray(), 0);
		return searchResult;
	}
	
	
	
	
	
	
	
}
