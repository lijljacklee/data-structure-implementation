package Trie;

public class Trie {
	private final int SIZE = 26;
	private TrieNode root;
	
	Trie (){  //Initialization.
		root = new TrieNode ();
	}
	
	public class TrieNode {
		private char val;
		private String prefix;
		private TrieNode[] child;
		private int num;
		private boolean isEnd;
		
		TrieNode () {
//			val = c;
			child = new TrieNode[SIZE];
			num = 1;
			isEnd = false;
		};
	};
	
	//Insert a string into trie tree.
	public void insert (String str) {
		if (str == null || str.length() == 0)
			return ;
		TrieNode node = root;
		char[] letters = str.toCharArray();
		for (int i = 0, len = str.length(); i < len; i++){
			int pos = letters[i] - 'a';
			
			String prefix = str.substring(0,i+1);
			
			if (node.child[pos] == null) {
				node.child[pos] = new TrieNode ();
				node.child[pos].val = letters[i];
				node.child[pos].prefix = prefix;
			}else {
				node.child[pos].num++;
			}
			node = node.child[pos];
		}
		node.isEnd = true;
	}
	
	//Count the number of prefix.
	public int countPrefix (String str) {
		if (str == null || str.length() == 0)
			return -1;
		TrieNode node = root;
		char[] letters = str.toCharArray();
		for (int i = 0, len = str.length(); i < len; i++) {
			int pos = letters[i] - 'a';
			if (node.child[pos] == null) {
				return 0;
			}else {
				node = node.child[pos];
			}
		}
		return node.num;
	}
	
	//Check whether tree store the str or not.
	public boolean has (String str) {
		if (str == null || str.length() == 0)
			return false;
		TrieNode node = root;
		char[] letters = str.toCharArray();
		for (int i = 0, len = str.length(); i < len; i++) {
			int pos = letters[i] - 'a';
			if (node.child[pos] == null) {
				return false;
			}else {
				node = node.child[pos];
			}
		}
		System.out.println("The prefix is :"+ node.prefix);
		return node.isEnd;
	}
	
	//Traverse the tree.
	public void traverse (TrieNode node) {
		if (node != null) {
			System.out.print(node.val+"-");
			for (TrieNode v: node.child) {
				traverse (v);
			}
		}
	}
	
	//Get the root.
	public TrieNode getRoot () {
		return this.root;
	}
	
	public static void main (String[] argv) {
		Trie tree = new Trie();
		String[] strs={  
                "banana",
                "band",
                "bee",
                "absolute",
                "acm",
        };
		for(String str : strs){  
            tree.insert(str);
        } 
		System.out.println(tree.has("banana"));
		tree.traverse(tree.getRoot());  
        System.out.println(); 
		String[] prefix={
                "ba",
                "b",
                "band",
                "abc",
        };
		for (String p : prefix) {
			int num = tree.countPrefix(p);
			System.out.println(p +": "+ num);
		}
	}
 }
