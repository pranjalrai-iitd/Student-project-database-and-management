package PriorityQueue;

public class MaxHeap<T extends Comparable<T>> implements PriorityQueueInterface<T> {
	Pair<T>[] list;
	int i=0;
	int size=0;

	public MaxHeap() {
		list=new Pair[10000];
	}
	
	public int parent(int key) {
		return (key)/2;
	}
	
	public int left(int key) {
		return (2*key);
	}
	
	public int right(int key) {
		return (2*key+1);
	}
	
	public void swap(int key1, int key2) {
		Pair<T> temp=list[key2];
		list[key2]= list[key1];
		list[key1]=temp;
	}
	
public void sort(int key) {
	if(key!=size && right(key)<=size) {
	int largest=key;
	
	if(largest!=size && left(key)<size && left(largest)!=size &&list[largest].compareTo(list[left(key)])<0) {
		largest=left(key);
	}
	if(largest<size && right(key)<size && right(largest)!=size &&list[largest].compareTo(list[(right(key))])<0) {
		largest=(right(key));
	}
	
	if(largest!=key) {
		swap(key, largest);
		if(largest!=size && left(largest)!=size && right(largest)!=size) {
	
		    sort(largest);
		
	}	
	}
	}
}

    @Override
    public void insert(T element) {
 //   	System.out.println("HO"+i);
    	Pair<T> ins=new Pair(element, i);
    	i++;
    	list[++size]=ins;
    	int current =size;
   		while(((( list[current]).compareTo(list[parent(current)]))>0)) {
   			
   			swap(current,parent(current));
   		current=parent(current);     // to traverse upwards in the binary tree
//System.out.println(">...................."+j+list.get(parent(current)).element);
    	} 
   //		sort(current);

    }
    
    public int getSize() {
    	return size;
    	}

    @Override
    public T extractMax() {
    	if(size!=0) {
    	Pair<T> ret=list[1];
    	Pair<T> r= list[size];
    	list[1]=r;
    	size--;;
       	sort(1);
       	return ret.getElement();
    	}
    	else return null; 
    } 

	



}