package local;

public class Trie {
	
	private TreeNode root;

	public static void main(String[] args) {

	}
	
	/** Initialize your data structure here. */
    public Trie() {
    	root = new TreeNode();
    	root.isEnd = false;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        
    	return false;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	return false;
    }

    class TreeNode{
    	private TreeNode[] children;
    	private boolean isEnd;
    	private final int R = 26;
    	public TreeNode(){
    		children = new TreeNode[R];
    	}
    }
}

