package Trie;

import java.util.LinkedList;
import java.util.Queue;

public class Trie<T> implements TrieInterface {
	 TrieNode<T> root=new TrieNode(null);
	 int size=1;
	 
	 
	 
	 void del(String word) {
		 int index;
	    	int stl=word.length();
	    	TrieNode<T> traversal=root;
	    	traversal.level=0;
	    	for(int i=0;i<stl;i++) {
	    		index=word.charAt(i)-' '; // giving ' ' index 0
	    		traversal=traversal.child[index];
	    	}
	    	if(traversal.getValue()!=null) {
	    		traversal.value=null;
	    	}
	    	int le=word.length()-1;
	    	if(le>0) {
	    	String trm=word.substring(0,le);
	    	del(trm);
	    	}
		 
	 }


    @Override
    public boolean delete(String word) {
    	if(search(word)==null) {
    		System.out.println("ERROR DELETING");
    		return false;
    	}
    	else {
    		del(word);
    		return true;
    	}
    }

    @Override
    public TrieNode search(String word) {
     	int index;
    	int stl=word.length();
    	TrieNode<T> traversal=root;
    	traversal.level=0;
    	for(int i=0;i<stl;i++) {
    		index=word.charAt(i)-' '; // giving ' ' index 0
    		if(traversal.child[index]==null) {
    				return null;
    		}
    		traversal=traversal.child[index];
    	}
    	if(traversal!=null && traversal.getValue()!=null)
    		return traversal;
    	else 
    		return null;
    }
        
    

    @Override
    public TrieNode startsWith(String prefix) {
    	int index;
    	int stl=prefix.length();
    	TrieNode<T> traversal=root;
    	traversal.level=0;
    	for(int i=0;i<stl;i++) {
    		index=prefix.charAt(i)-' '; // giving ' ' index 0
    		if(traversal.child[index]==null) {
    				return null;
    		}
    		traversal=traversal.child[index];
    	}
    	if(traversal!=null)
    		return traversal;
    	else 
    		return null;
   
    }

    @Override
    public void printTrie(TrieNode trieNode) {
    	if(trieNode.child==null || trieNode.value!=null)
    		System.out.println(trieNode.getValue());
    	if(trieNode.child!=null) {
    		Queue<TrieNode<T>> q=new LinkedList<TrieNode<T>> ();
    		for(int i=0; i<95; i++) {
    			if(trieNode.child[i]!=null)
    				q.add(trieNode.child[i]);
    		}
    		while(q.peek()!=null) {
    			trieNode=q.remove();
    			printTrie(trieNode);
    		}
    		
    	}

    }
    
    void Sort(int arr[]) { 
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (arr[j] > arr[j+1])  { 
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
    }

    @Override
    public boolean insert(String word, Object value) {
    	int index;
    	int stl=word.length();
    	TrieNode<T> traversal=root;
    	traversal.level=0;
    	for(int i=0;i<stl;i++) {
    		index=word.charAt(i)-' '; // giving ' ' index 0
    		if(traversal.child[index]==null) {
    			if(i<stl-1) {
    				traversal.child[index]=new TrieNode(null);
    				traversal.child[index].level=i;
    			}
    			else if(i==stl-1) {
    				traversal.child[index]=new TrieNode(value); // inserting value at the end of the word
    				traversal.child[index].level=i;
    			}
    		}
    		traversal=traversal.child[index];
    	}
    	if(size<traversal.level) {
			size=traversal.level;
			size++;
    	}
        return true;
    }

    @Override
    public void printLevel(int level) {
    	int n=level-1;
    	TrieNode<T> traversal=root;
    	Queue<TrieNode<T>> q=new LinkedList<TrieNode<T>>();
    	Queue<Integer> q1=new LinkedList<Integer>();
    	for(int i=0; i<95; i++) {
    		if(traversal.child[i]!=null) {
    			q.add(traversal.child[i]);
    			q1.add(i+32);
    		}
    	}
    	while(n>0) {
    		int a=q.size();
    		for(int i=0; i<a; i++) {
    			traversal=q.remove();
    			q1.remove();
    			for(int j=0; j<95; j++) {
    				if(traversal.child[j]!=null) {
    	    			q.add(traversal.child[j]);
    	    			q1.add(j+32);
    	    		}	
    			}
    		}
    		n--;
    	}
    	String t="Level "+level+": ";
    	while(q1.peek()!=null) {
    		int s=q1.size();
    		int in=0;
    		int[] array= new int[s];
    		while(q1.peek()!=null) {
    			array[in]=q1.remove();
    			in++;
    		}
    		Sort(array);
    		for(int i=0; i<s; i++) {
    			q1.add(array[i]);
    		}
    		int a=q1.remove();
    		if(a!=32)
    		t=t+(char)a+", ";
    	}
    	System.out.println(t.substring(0, t.length()-2));
    		

    }

    @Override
    public void print() {
    	System.out.println("-------------"); 
    	System.out.println("Printing Trie"); 
     
    	TrieNode<T> traversal=root;
    	int level=1;
    	Queue<TrieNode<T>> q=new LinkedList<TrieNode<T>>();
    	Queue<Integer> q1=new LinkedList<Integer>();
    	for(int i=0; i<95; i++) {
    		if(traversal.child[i]!=null) {
    			q.add(traversal.child[i]);
    			q1.add(i+32);
    		}
    	}
    	String t="Level "+level+": ";
    	while(q1.peek()!=null) {
    		
    		int s=q1.size();
    		int in=0;
    		int[] array= new int[s];
    		while(q1.peek()!=null) {
    			array[in]=q1.remove();
    			in++;
    		}
    		Sort(array);
    		for(int i=0; i<s; i++) {
    			q1.add(array[i]);
    		}
    		int a=q1.remove();
    		if(a!=32)
    		t=t+(char)a+", ";
    	}
    	System.out.println(t.substring(0, t.length()-2));
    	level++;
    	
    	while(level<size+2) {
    		int a=q.size();
    		for(int i=0; i<a; i++) {
    			traversal=q.remove();
    			for(int j=0; j<95; j++) {
    				if(traversal.child[j]!=null) {
    	    			q.add(traversal.child[j]);
    	    			q1.add(j+32);
    	    		}	
    			}
    		}
    		String k="Level "+level+": ";
        	while(q1.peek()!=null) {
        		int s=q1.size();
        		int in=0;
        		int[] array= new int[s];
        		while(q1.peek()!=null) {
        			array[in]=q1.remove();
        			in++;
        		}
        		Sort(array);
        		for(int i=0; i<s; i++) {
        			q1.add(array[i]);
        		}
        		int b=q1.remove();
        		if(b!=32)
        		k=k+(char)b+", ";
        	}
        	System.out.println(k.substring(0, k.length()-2));
    		level++;
    	}
    	
    	
    	
    	
	    System.out.println("-------------");     	
	    
    
    
    }
}