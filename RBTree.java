package RedBlack;


public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {

	public RedBlackNode root=null;
	static boolean black=false;
	static boolean red=true;
	
	
	public void reduncle(RedBlackNode t) {              // COLOR REVERSAL WHEN RED PARENT AND RED UNCLE
		if(t==root) {
	//		System.out.println("Hi");
			t.color=black;
	//		System.out.println(t.right.color+ " Hi");
		}
		else if(t!=root && t.parent!=root && t.parent.color==red &&  t.parent==(t.parent).parent.left) {
		   // 	System.out.println("Hi");
			if((t.parent)!=root && t!=root && t.parent.parent.right.color==red) { //If red uncle
			//	System.out.println("Hi");
				t.parent.color=black;
			    (t.parent).parent.right.color=black;
		      	((t.parent).parent).color=red;
	     		t=(t.parent).parent;
     			reduncle(t);
		     }
		}
		else {
		  //  	System.out.println("Hi");
			if(t!=root && (t.parent)!=root && (t.parent).color==red && t.parent.parent.left.color==red) { //If red uncle
		//		System.out.println("Hi");
				t.parent.color=black;
				(t.parent).parent.left.color=black;
				((t.parent).parent).color=red;
				t=(t.parent).parent;
				reduncle(t);
			}
		}
	}
	
	public void swapcolor(RedBlackNode a, RedBlackNode b) {
//		System.out.println("Hi");
		a.color=!a.color;
		b.color=!b.color;
	}
	
	public void rightrotation(RedBlackNode t) {
//		System.out.println("Hi");
		RedBlackNode h=t.left;
		t.left=h.right;
		if(h.right!=null)
			h.right.parent=t;
		h.parent=t.parent;
		if(t.parent==null)
			root=h;
		else if(t==t.parent.right)
			t.parent.right=h;
		else 
			t.parent.left=h;
			h.right=t;
			t.parent=h;
	}
	
	public void leftrotation(RedBlackNode t) {
//		System.out.println("Hi");
		RedBlackNode h=t.right;
		t.right=h.left;
		if(h.left!=null)
			h.left.parent=t;
		h.parent=t.parent;
		if(t.parent==null)
			root=h;
		else if(t==t.parent.left)
			t.parent.left=h;
		else 
			t.parent.right=h;
			h.left=t;
			t.parent=h;
	}
	
	public void leftleftrotation(RedBlackNode t) {
		rightrotation((t.parent).parent);
		swapcolor(t.parent,t.parent.right);
	}
	public void rightrightrotation(RedBlackNode t) {
		leftrotation((t.parent).parent);
		swapcolor(t.parent,(t.parent).left);
	}
	public void leftrightrotation(RedBlackNode t) {
		leftrotation(t.parent);
//		System.out.println(t.left.key+" Hi");
		leftleftrotation(t.left);
	}
	public void rightleftrotation(RedBlackNode t) {
		rightrotation(t.parent);
		rightrightrotation(t.right);
	}
	
	public void blackuncle(RedBlackNode t) {           // ROTATION WHEN RED PARENT AND BLACK UNCLE
	//	System.out.println("Hi");
			if((t.parent)!=root && t!=root) { 
	//			System.out.println("Hi");
				if(((t.parent)==((t.parent).parent.left))&&(t==(t.parent).left)) {    // LEFT LEFT ROTATION
					leftleftrotation(t);
				}
				else if(((t.parent)==((t.parent).parent.left))&&(t==(t.parent).right)) { // LEFT RIGHT ROTARION
					leftrightrotation(t);
				}
				else if(((t.parent)==((t.parent).parent.right))&&(t==(t.parent).right)) { // RIGHT RIGHT ROTATION
					rightrightrotation(t);
					}
				else if(((t.parent)==((t.parent).parent.right))&&(t==(t.parent).left)) {      // RIGHT LEFT ROTATION                                                           // RIGHT LEFT ROTARION
					rightleftrotation(t);
					}
				else
					return;
				}
}

    @Override
    public void insert(T key, E value) {
    	if(root==null) {
    		RedBlackNode t=new RedBlackNode(key,value,black,1);
    		root=t;
    		root.parent=null;
    	}
    	else if(root!=null) {
    		RedBlackNode t=new RedBlackNode(key,value,red,1);     // Red node
    		RedBlackNode traversal=root;
    		int i=0;
    		while(traversal!=null) {
    			
    			  
    		    if(((traversal).key).compareTo(key)>0) {
    		    	
    		    	t.parent=traversal;
    				traversal=traversal.left;
    				if(traversal==null)
    				i--;
    			}
    			else if (((traversal).key).compareTo(key)<0) {
    				
    				t.parent=traversal;
    				traversal=traversal.right;
    				if(traversal==null)
    				i++;
    				
    			}
    			else if(((traversal).key).compareTo(key)==0) {
    				
    				traversal.values.add(value);
    				break;
    			}
    		
    		}
    		    if(traversal==null) {
    			traversal=t;
    			if(i==1)
    			    traversal.parent.right=t;
    			if(i==-1)
    				traversal.parent.left=t;
    			traversal.parent.size++;
    		    }
    		    if(traversal.parent!=root)
  //  		    System.out.println((traversal.parent.parent.left.right).key+" Hi");
    		
    		
/* ENSURE TREE IS RED BLACK ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    	
    		    if(t.parent !=root && (t.parent).color==red) { 
    	//		System.out.println(" Hi");
    			if(t.parent==(t.parent).parent.right) {  
    			//	System.out.println("Hi");                                          // If Red Uncle then color reversal
    				if((t.parent).parent.left!=null && (t.parent).parent.left.color==red)
    					reduncle(t);                                                   // If Black Uncle then rotation
    				else if ((t.parent).parent.left==null || (t.parent).parent.left.color==black) {
    	//				System.out.println("Hi");
    				    blackuncle(t);
    				}
    			}
    			else if(t.parent==(t.parent).parent.left){
      		 //   	System.out.println("Hi");
    				if((t.parent).parent.right!=null && (t.parent).parent.right.color==red) // If Red Uncle then color reversal
    					reduncle(t);
    				else if ((t.parent).parent.right==null || (t.parent).parent.left.color==black) {                                                               // If Black Uncle then rotation
//    					System.out.println(t.parent.parent.left.key+" Hi");
    					blackuncle(t);
    					
    				}
    			}
    			
    		}
    	//	System.out.println(traversal.color+" Hi");
    }
 }


    

    @Override
    public RedBlackNode<T, E> search(T key) {
    	
    		RedBlackNode traversal=root;
    		while(traversal!=null) {
    			
    		    if(((traversal).key).compareTo(key)>0) {
    				traversal=traversal.left;
    			}
    			else if (((traversal).key).compareTo(key)<0) {
    				traversal=traversal.right;
    			}
    			else if (((traversal).key).compareTo(key)==0) {
        				return traversal;
    			}
    		}
    			
    	
		return null;
		
    }
}