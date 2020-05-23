package RedBlack;

import Util.RBNodeInterface;

import java.util.ArrayList;
import java.util.List;

public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {
public T key;
public E value;
public List<E> values=new ArrayList<E>();
public boolean color;
public int size;
public RedBlackNode left;
public RedBlackNode right;
public RedBlackNode parent;

public RedBlackNode(T key, E value, boolean color, int size) {
	this.key=key;
	this.value=value;
	this.color=color;
	this.size=size;
	values.add(value);
	left=null;
	right=null;
}


    @Override
    public E getValue() {
        return this.value;
    }

    @Override
    public List<E> getValues() {
        return values;
    }

}
