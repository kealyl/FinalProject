

public class TST<Value> {

	private int size; //size
	private Node<Value> root; //root of tree

	private static class Node<Value>
	{
		private char character;
		private Node<Value> left, middle, right;
		private Value val; //value of string
	}

	public TST() //initialises empty string TST
	{

	}

	public int size() // returns number of key value pairs in TST
	{
		return size;
	}

	public boolean contains(String key)
	{
		if(key==null)
		{
			throw new IllegalArgumentException("argument to contains() is null");
		}
		return get(key) != null;
	}

	public Value get(String key)
	{
		if(key == null) 
		{
			throw new IllegalArgumentException("calls get() with null argument");
		}
		if(key.length() == 0) 
		{
			throw new IllegalArgumentException("key must have length >= 1");
		}
		Node<Value> node = get(root, key, 0);
		if(node == null)
		{
			return null;
		}
		return node.val;
	}

	private Node<Value> get(Node<Value> node, String key, int i) 
	{
		if(node == null)
		{
			return null;
		}
		if(key.length() == 0)
		{
			throw new IllegalArgumentException("Length of key must be >= 1");
		}
		char character = key.charAt(i);
		if(character < node.character) 
		{
			return get(node.left, key, i);
		}
		else if(character > node.character) 
		{
			return get(node.right, key, i);
		}
		else if(i < key.length() - 1) 
		{
			return get(node.middle, key, i+1);
		}
		else
		{
			return node;
		}
	}



}