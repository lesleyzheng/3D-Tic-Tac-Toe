
package oc;
import oc.GenericOrderedCollection;
public class MyGenericDS <E> implements GenericOrderedCollection<E>{
	private Node node;
    private Node end;
    public class Node{
        private E N;
		private E C;
	//N is the number in the Node and C is the secondary Number in the node
        private Node next;
        public Node(E N){
            this.N=N;
        }
		public Node(Node N){
			if (N==null){
				this.N=null;
			}
			else if(N.C==null){
				this.N=N.N;
				this.next=new Node(N.next);
			}
			else{
				this.N=N.N;
				this.C=N.C;
				this.next=new Node(N.next);
			}
		}
	//Constructor that allows a node to be past in and a duplicate node to be created, for use in copying.
    }
    public MyGenericDS(){
        end=null;
    }
	public MyGenericDS(MyGenericDS<E> M){
		if (M==null){
			end=null;
		}
		else{
			end=new Node(M.end);
		}
	}
//Duplicate constructor that takes a data structure and allows a copy to be made without altering the original

    public void append(E toAppend){
        node= new Node(toAppend);
        node.next=end;
        end=node;
		end.C=null;
	}
//Method that allows a new number (in the form of a node) to be added to the data structure
	
	public E peek(){
		if (end== null){
			return null;
		}
		else{
			return end.N;
		}
	}
//Method that allows the most recently added node to be viewed
	
    public E pop(){
		if (end==null){
			return null;
		}
		else{
			E R=end.N;
			end=end.next;
			return R;
		}
	}
//Method that allows the most recently added node to be viewed and then removed
	
    public void remove(int index){
		if (index>this.length()){
		}
		else{
			MyGenericDS<E> tmp=new MyGenericDS<E>();
			int thisLength=this.length();
			for (int i=0; i<thisLength-index-1; i++){
				tmp.append(this.pop());
			}
			this.pop();
			int length=tmp.length();
			for (int i=0; i<length; i++){
				this.append(tmp.pop());
			}
		}
	}
//Method that will remove any given node from the data structure
	
    public String toString(){
		String R = "";
		Node New = end;
		while (New != null){
			R = New.N + ":" + New.C+ " " + R;
			New = New.next;
		}
		return R;
	}
//Method that overrides the default memory location to allow viewing of the data structure if required
	
    public int length(){
        int C=0;
        Node New=end;
        while(New!=null){
            C=C+1;
            New=New.next;
        }
        return C;
	}
//Method that returns the number of nodes in the data structure
	
	public void attach(E C){
		end.C=C;
	}
//Method that attachs a second number to the current node (without increasing the length of the data structure)
	
	public E peekSecondary(){
		if (end== null){
			return null;
		}
		else{
			if (end.C==null){
				return null;
			}
			else{
				return end.C;
			}
		}
	}
//Method that allows the viewing of the second number of the last node
}