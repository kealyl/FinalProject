
public class TST<Value> {

	private int size; //size
	private Node<Value> root; //root of tree

	private static class Node<Value>
	{
		private char character;
		private Node<Value> left, middle, right;
		private Value value; //value of string
	}

	public TST() //initialises empty string TST
	{

	}

	public int size() // returns number of key value pairs in TST
	{
		return size;
	}

	public boolean contains(String key) //returns true if TST contains key
	{
		if(key==null)
		{
			throw new IllegalArgumentException("argument to contains() is null");
		}
		return get(key) != null;
	}

	public Value get(String key) //returns value associated with key
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
		return node.value;
	}

	private Node<Value> get(Node<Value> node, String key, int i) //returns subtree associated with key
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

	public void put(String key, busStops stop) //inserts key value pair into TST
	{
		if (key == null) 
		{
			throw new IllegalArgumentException("calls put() with null key");
		}
		if (!contains(key))
		{
			size++;
		}
		
		else if(stop == null) 
		{
			size--;       // delete existing key
		}
		
		root = put(root, key, stop, 0);
	}

	private Node<Value> put(Node<Value> node, String key, busStops stop, int i) 
	{
		char character = key.charAt(i);
		if (node == null) 
		{
			node = new Node<Value>();
			node.character = character;
		}
		if(character < node.character) 
		{
			node.left  = put(node.left, key, stop, i);
		}
		else if(character > node.character)
		{
			node.right = put(node.right, key, stop, i);
		}
		else if (i < key.length() - 1)
		{
			node.middle = put(node.middle, key, stop, i+1);
		}
		else
		{
			//node.value = stop;
		}
		return node;
	}

	public String longestPrefixOf(String string) //returns string in TST that is longest prefix of query
	{
		if (string == null) 
		{
			throw new IllegalArgumentException("calls longestPrefixOf() with null argument");
		}
		if (string.length() == 0) 
		{
			return null;
		}
		int length = 0;
		Node<Value> node = root;
		int i = 0;
		while (node != null && i < string.length()) 
		{
			char character = string.charAt(i);
			if(character < node.character) 
			{
				node = node.left;
			}
			else if(character > node.character) 
			{
				node = node.right;
			}
			else 
			{
				i++;
				if (node.value != null) 
				{
					length = i;
				}
				node = node.middle;
			}
		}
		return string.substring(0, length);
	}

	public Iterable<String> keys() 
	{
		Queue<String> queue = new Queue<String>();
		collect(root, new StringBuilder(), queue);
		return queue;
	}

	//returns all keys in set that start with prefix
	public Iterable<String> keysWithPrefix(String prefix) 
	{
		if (prefix == null) 
		{
			throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
		}
		Queue<String> queue = new Queue<String>();
		Node<Value> x = get(root, prefix, 0);
		if (x == null) 
		{
			return queue;
		}
		if (x.value != null) 
		{
			queue.enqueue(prefix);
		}
		collect(x.middle, new StringBuilder(prefix), queue);
		return queue;
	}

	private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue) 
	{
		if (x == null) 
		{
			return;
		}
		collect(x.left,  prefix, queue);
		if (x.value != null) 
		{
			queue.enqueue(prefix.toString() + x.character);
		}
		collect(x.middle,   prefix.append(x.character), queue);
		prefix.deleteCharAt(prefix.length() - 1);
		collect(x.right, prefix, queue);
	}

	public Iterable<String> keysThatMatch(String pattern) 
	{
		Queue<String> queue = new Queue<String>();
		collect(root, new StringBuilder(), 0, pattern, queue);
		return queue;
	}

	private void collect(Node<Value> x, StringBuilder prefix, int i, String pattern, Queue<String> queue) 
	{
		if (x == null) 
		{
			return;
		}
		char character = pattern.charAt(i);
		if (character == '.' || character < x.character) 
		{
			collect(x.left, prefix, i, pattern, queue);
		}
		if (character == '.' || character == x.character) 
		{
			if (i == pattern.length() - 1 && x.value != null) 
			{
				queue.enqueue(prefix.toString() + x.character);
			}
			if (i < pattern.length() - 1) 
			{
				collect(x.middle, prefix.append(x.character), i+1, pattern, queue);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		}
		if (character == '.' || character > x.character) 
		{
			collect(x.right, prefix, i, pattern, queue);
		}

	}

}