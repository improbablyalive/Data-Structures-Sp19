/*  Intcoll4.java
 *  this file contains the class Intcoll, a collection of ints
 * 
 *  @version  20190328
 *  @author   Athena Evans
 * */

public class Intcoll4{
  //Intcoll4 Variables 
  private ListNode c;                  // c refers to the collection
  private int howMany;                 // howMant is the count of the collection
   
   public Intcoll4(){                //instantiation of Intcoll, no params
     c = new ListNode();
     howMany = 0;
   }

   public Intcoll4(int i){           //instantiation of Intcoll, param int i for first val
     c = new ListNode(i, null);
     howMany = 1;
   }

   
   
   //Intcoll4 Methods
   public void copy(Intcoll4 obj){   //makes a copy of another Intcoll object and if they are the same then copy does
                                     //nothing. If it does not match then copy replaces this Intcoll object with the 
                                     //copy. REQ ANOTHER Intcoll obj.
                                     //example --> a.copy(b) will make it so that a contains the same array as b
                                     //as a void function copy(Intcoll4 obj) returns nothing
    if(this.c != obj.c){
        ListNode copy = new ListNode();
        copy.info = obj.c.info;
        copy.link = obj.c.link;
        
        while( copy.link != null ){
            copy = copy.link;
        }
        this.c = copy;
        this.howMany = obj.getHowMany();
    }
   }

   public boolean belongs(int i){    //searches the collection for int i, returns true if found in collection and false 
                                     //if not found
    boolean result = false;
    if( howMany == 0) return result;
    
    ListNode temp = c;
    while((temp!=null) && (temp.info!=i)){ //iterate through collection
        temp = temp.link;
    }
    if(temp !=null){
        result = (temp.info == i);
    }
    
    return result;
   }

   public void insert(int i){        //searches the collection for int i using the belongs method, if belongs returns 
                                     //true then insert does nothing. If belongs returns false then i is put into the 
                                     //collection after the last non-zero value
                                     //as a void function insert(int i) returns nothing
    if( i>0 ){ //stops weird 0 problem
        if( this.belongs(i) == false){ // i is not in collection
            ListNode temp = new ListNode(i, c);
            c = temp;
            howMany++;
        }
    }
   }

   public void omit(int i){          //searches the collection for int i, if i is not found in the collection then
                                     //nothing happens. if i is found then it is removed from the collection
                                     //as a void function omit(int i) returns nothing
    if(i>0){ //stops 0 problem
        ListNode temp = c;
        ListNode prevTemp = null;
        
        while( (temp.info!=i) && (temp!=null) ){ //look for i
            prevTemp = temp;
            temp = temp.link;
        }
        if( temp!=null ){ //not last item
            if( temp.info == i){ //i was found in collection
                if( prevTemp != null ){ //not the only item in list
                    prevTemp.link = temp.link;
                }
                else c = temp.link;
                howMany--;
            }
        }
    }  
   }

   public int getHowMany(){         //interates over the c array and counts the items that are not zero, this will
                                     //return the amount of viable values in the collection, this replaces a length-
                                     //type method since the size of the array might not be the same as the true size
                                     //of the collection, see empty param instantiation
     return howMany;
   }

   public void print(){                 //formats the info of the collection and prints as a series of strings
                                        //as a void function print() returns nothing
    ListNode temp = this.c;
    while( temp != null){
        if(temp.info != 0){
            System.out.println( temp.info );
        }
        temp = temp.link;
    }
    
   }

   public boolean equals(Intcoll4 obj){ //compares another Intcoll object with this Intcoll object
                                        //if every int in both Intcoll arrays are the same, the items have the same
                                        //collection contents up until the SENTINAL and order does NOT matter,
                                        //then equals returns true, if not then equals returns false
    boolean result = false;
    ListNode temp = this.c;
    while( (temp!=null) && result ){ //traverse the collection while there are only matches
        result = obj.belongs( temp.info );
        temp = temp.link;
    }
    return result;
   }
   
   
   
   
public class ListNode{
    private int info;
    private ListNode link;

    public ListNode(){
        info = 0;
        link = null;
    }
    public ListNode(int i, ListNode p){
        info = i;
        link = p;
    }
    }

}