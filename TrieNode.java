package Trie;


import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {
	TrieNode<T> child[]= new TrieNode[95]; // Total printable ascii characters are 95 from 32 to 126 neglecting delete
	T value;
	int level;
	
	public TrieNode(T value) {
		this.value=value;
		
		for(int i=0;i<95;i++)
			child[i]=null;
	}

    @Override
    public T getValue() {
        return this.value;
    }
    public int level() {
    	return level;
    }


}