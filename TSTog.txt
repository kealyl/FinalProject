import java.util.ArrayList;
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

	// functions to delete a string from TST
	private class resultDelete
	{
		boolean isSubstring;
		boolean isSuccess;

		resultDelete(boolean isSuccess, boolean isSubstring)
		{
			this.isSubstring = isSubstring;
			this.isSuccess = isSuccess;
		}
	}

	public boolean delete(final String string)
	{

		ArrayList<directionOfNode> nodeDirections = new ArrayList<>();
		final resultDelete result = delete(getRoot(), string.toUpperCase().toCharArray(), 0, nodeDirections);
		if((string == null) || (string.isEmpty()))
		{
			return false;
		}
		if (!result.isSubstring && result.isSuccess)
		{
			tidy(getRoot(), string.toUpperCase().toCharArray(), 0);

		}
		return result.isSuccess;	
	}

	private resultDelete delete(final TSTNode node, final char[] stopName, final int i, final ArrayList<directionOfNode> nodeDirections)
	{
		final char currentCharacter = stopName[i];
		if(node==null)
		{
			return new resultDelete(false,false);
		}

		if (node.getChar() < currentCharacter)
		{
			nodeDirections.add(directionOfNode.RIGHT);
			return delete(node.getRightChild(), stopName, i, nodeDirections);
		}
		else if(node.getChar() > currentCharacter)
		{
			nodeDirections.add(directionOfNode.LEFT);
			return delete(node.getLeftChild(), stopName, i, nodeDirections);
		}
		else
		{
			if(node.isWordEnd() && i == stopName.length)
			{
				node.setWordEnd(false);
				nodeDirections.add(directionOfNode.MIDDLE);

				boolean isSubstring;
				if(node.getMiddleChild() == null)
				{
					isSubstring = false;
				}
				else
				{
					nodeDirections.add(directionOfNode.MIDDLE);
					isSubstring = true;
				}
				return new resultDelete(true, isSubstring);

			}
			nodeDirections.add(directionOfNode.MIDDLE);
			return delete(node.getMiddleChild(), stopName, i+1, nodeDirections);
		}
	}
	
	private boolean tidy(TSTNode node, final char[] stopName, final int i)
	{
		final char currentCharacter = stopName[i];
		if(node==null)
		{
			return false;
		}
		
		if(node.getChar() < currentCharacter)
		{
			return tidy(node.getRightChild(), stopName, i);
		}
		else if(node.getChar() > currentCharacter)
		{
			return tidy(node.getLeftChild(), stopName, i);
		}
		else
		{
			if(node.isWordEnd() && (i == stopName.length))
			{
				if(node.isLeafNode())
				{
					node = null;
					return true;
				}
			}
			boolean isLeafNode = tidy(node.getMiddleChild(), stopName, i+1);
			if(node.isLeafNode() && isLeafNode)
			{
				node = null;
				return true;
			}
		}
		return false;
		
	}

}
