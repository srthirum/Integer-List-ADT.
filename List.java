
class List{
  
  private class Node{
    int data;
    Node prev;
    Node next;
    
    //constructors
    Node(int data){
      this.data=data;
      prev=null;
      next=null;
    }
    
    Node(int data, Node prev, Node next){
      this.data=data;
      this.prev=prev;
      this.next=next;
    }
    
    //toString
    //returns string representation of the data
    public String toString(){
      return String.valueOf(data);
    }
    
  }
  
  private Node front;
  private Node back;
  private Node cursor;
  private int length;
  private int index;
  
  //constructor
  //Creates a new empty list
  List(){
    front=null;
    back=null;
    cursor=null;
    length=0;
    index=-1;
  }
  
  //Access Functions
  
  //length()
  //Returns the number of elements in this List
  int length(){
    return length;
  }
  
  //index()
  //If cursor is defined, returns the index of the cursor element, otherwise returns -1
  int index(){
    return index;
  }
  
  //front()
  //Returns the front element.
  //Pre: length()>0
  int front(){
    if(length<1){
      throw new RuntimeException("List Error: front() called on an empty List");
    }
    return front.data;
  }
  
  //back()
  //Returns back element.
  //Pre: length()>0
  int back(){
    if(length<1){
      throw new RuntimeException("List Error: back() called on an empty List");
    }
    return back.data;
  }
  
  //get()
  //Returns cursor element
  //Pre: length()>0, index()>=0
  int get(){
    if(length<1){
      throw new RuntimeException("List Error: get() called on an empty List");
    }
    if(index<0){
      throw new RuntimeException("List Error: get() called with undefined index on List");
    }
    return cursor.data;
  }
  
  //equals()
  //Returns true if this List and L are the same integer sequence.
  //The cursor is ignored in both lists.
  boolean equals(List L){
    if(L.length != this.length){
      return false;
    }
    Node N=this.front;
    Node M=L.front;
    while(N.next!=null && M.next!=null){
      if(N.data!=M.data){
        return false;
      }
      N=N.next;
      M=M.next;
    }
    return true;
  }
  
  //Manipulation Procedures
  
  //clear()
  //Resets this List to its original empty state
  void clear(){
    front=null;
    back=null;
    cursor=null;
    length=0;
    index=-1;
  }
  
  //moveFront()
  //If list is non-empty, places the cursor under the front element, otherwise does nothing
  void moveFront(){
    if(length>0){
      index=0;
      cursor=front;
    }
  }
  
  //moveBack()
  //If list is non-empty, places the cursor under the back element, otherwise does nothing
  void moveBack(){
    if(length>0){
      cursor=back;
      index=length-1;
    }
  }
  
  //movePrev()
  //If cursor is defined and not at front, moes cursor one step towards front of this list.
  //If cursor is defined and front, cursor becomes undefined, if cursor is undefined does nothing
  void movePrev(){
    if(cursor!=null){
      if(index==0){
        cursor=null;
        index=-1;
      }else{
        cursor=cursor.prev;
        index--;
      }
    }  
  }
  
  //moveNext()
  //If cursor is defined and not at back, moes cursor one step towards back of this list.
  //If cursor is defined and back, cursor becomes undefined, if cursor is undefined does nothing
  void moveNext(){
    if(cursor!=null){
      if(index==length-1){
        cursor=null;
        index=-1;
      }else{
        cursor=cursor.next;
        index++;
      }
    }  
  }
  
  //prepend()
  //Insert new element into List. If List is non-empty, insertion takes place before front element
  void prepend(int data){
    Node temp = new Node(data, null, this.front);
    if(front==null)
      back=temp;
    else
      front.prev=temp;
    front=temp;
    length++;
  }
  
  //append()
  //Insert new element into List. If List is non-empty, insertion takes place after back element
  void append(int data){
    Node temp= new Node(data, this.back, null);
    if(front==null)
      front=temp;
    else
      back.next=temp;
    back=temp;
    length++;
  }
  
  //insertBefore()
  //Insert new element before cursor.
  //Pre: length()>0, index()>=0
  void insertBefore(int data){
    if(length<1){
      throw new RuntimeException("List Error: insertBefore() called on empty List");
    }
    if(index<0){
      throw new RuntimeException("List Error: insertBefore() called with undefined index on List");
    }
    Node temp= new Node(data, cursor.prev, cursor);
    if(cursor.prev!=null)
      cursor.prev.next=temp;
    else
      front=temp;
    cursor.prev=temp;
    length++;
  }
  
  //insertAfter()
  //Insert new element after cursor.
  //Pre: length()>0, index()>=0
  void insertAfter(int data){
    if(length<1){
      throw new RuntimeException("List Error: insertAfter() called on empty List");
    }
    if(index<0){
      throw new RuntimeException("List Error: insertAfter() called with undefined index on List");
    }
    Node temp= new Node(data, cursor, cursor.next);
    if(cursor.next!=null)
      cursor.next.prev=temp;
    else 
      back = temp;
    cursor.next=temp;
    length++;
  }
  
  //deleteFront()
  //Deletes the front element.
  //Pre: length()>0
  void deleteFront(){
    if(length<1){
      throw new RuntimeException("List Error: deleteFront() called on empty List");
    }
    if(cursor==front){
      cursor=null;
      index=-1;
    }
    front=front.next;
    front.prev=null;
    length--;
  }
  
  //deleteBack()
  //Deletes the back element.
  //Pre: length()>0
  void deleteBack(){
    if(length<1){
      throw new RuntimeException("List Error: deleteBack() called on empty List");
    }
    if(cursor==back){
      cursor=null;
      index=-1;
    }
    back=back.prev;
    back.next=null;
    length--;
  }
  
  //delete()
  //Deletes cursor element, making cursor undefined
  //Pre: length()>0, index()>=0
  void delete(){
    if(length<1){
      throw new RuntimeException("List Error: delete() called on empty List");
    }
    if(index<0){
      throw new RuntimeException("List Error: delete() called with undefined index on List");
    }
    if(cursor==front){
      deleteFront();
    }else if(cursor==back){
      deleteBack();
    }else{
      cursor.prev.next=cursor.next;
      cursor.next.prev=cursor.prev;
      cursor=null;
      index=-1;
      length--;
    }
  }
  
  //Other Methods
  
  //toString()
  //Overrides Object's toString method. Returns a String representation of this List consisting
  //of a space separated sequence of integers, with front on left
  public String toString(){
    Node temp=front;
    String out=new String();
    while(temp != null){
      out=out+String.valueOf(temp.data) + " ";
      temp=temp.next;
    }
    return out;
  }
  
  //List copy()
  //Returns a new List representing the same integer sequence as this List. The cursor in the new
  //list is undefined, regardless of the state of the cursor in this List. This List is unchanged.
  List copy(){
    List N = new List();
    Node temp=front;
    while(temp != null){
      N.append(temp.data);
      temp=temp.next;
    }
    return N;
  }
  
  //List concat()
  //Returns a new List which is the concatenation of this list followed by L. The cursor in the new
  //List is undefined, regardless of the states of the cursors in this List and L. The states of 
  //this List and L are unchanged.
  List concat(List L){
    List N=copy();
    Node temp = L.front;
    while(temp != null){
      N.append(temp.data);
      temp=temp.next;
    }
    return N;
  }
  
}