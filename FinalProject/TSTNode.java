
public class TSTNode {
	
	private boolean isWordEnd;
	private Character character;
	TSTNode left; //left child
	TSTNode right; //right child
	TSTNode middle; //middle child
	
	public Character getChar() // returns the character at this TSTNode
	{
		return character;
	}
	
	public boolean isWordEnd() // sees if TSTNode is end of word
	{
		return isWordEnd;
	}

	public void setWordEnd(final boolean wordEnd) //sets the end of the word
	{
		isWordEnd = wordEnd;
	}
	
	TSTNode getLeftChild() // returns left child of TST node
	{
		return left;
	}
	
	TSTNode getRightChild() // returns right child of TST node
	{
		return right;
	}
	
	TSTNode getMiddleChild() // returns middle child of TST node
	{
		return middle;
	}
	
	TSTNode(final Character character, final boolean isWordEnd) //creates TST node containg 'character' & indicates if this node is at end of word
	{
		this.isWordEnd = isWordEnd;
		this.character = character;
		this.left = null;
		this.right = null;
		this.middle = null;
	}
	
	TSTNode(final Character character) //creates TST node containing 'character'
	{
		this(character, false);
	}
	
	public boolean isLeafNode()
	{
		if(this.left==null && this.right == null && this.middle == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
