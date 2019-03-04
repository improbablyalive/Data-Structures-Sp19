/*  Intcoll5.java
 *  this file contains the class Intcoll, a collection of ints
 * 
 *  @version  20190301
 *  @author   Athena Evans
 * */

import java.util.LinkedList;
import java.util.ListIterator;

public class Intcoll5{
  //Intcoll5 Variables 
  private LinkedList<Integer> c;                  // c refers to the collection
  private int howMany;                 // howMant is the count of the collection
   
   //Instantiations
   public Intcoll5(){                //instantiation of Intcoll, no params
    c = new LinkedList<Integer>();
    howMany = 0;
   }

   public Intcoll5(int i){           //instantiation of Intcoll, param int i for first val
     //this version exists for consistancy
    c = new LinkedList<Integer>();
    howMany = 0;
   }

   
   
   //Intcoll5 Methods
    public void copy(Intcoll5 obj){   //makes a copy of another Intcoll object and if they are the same then copy does
                                     //nothing. If it does not match then copy replaces this Intcoll object with the 
                                     //copy. REQ ANOTHER Intcoll obj.
                                     //example --> a.copy(b) will make it so that a contains the same array as b
                                     //as a void function copy(Intcoll5 obj) returns nothing
    if( this != obj){
        this.howMany = obj.howMany;
        LinkedList<Integer> temp = new LinkedList<Integer>();
        ListIterator<Integer> I = obj.c.listIterator();
        while( I.hasNext() ){
            temp.add( I.next() );
        }
        this.c = temp;
    }
    }

   public boolean belongs(int i){    //searches the collection for int i, returns true if found in collection and false 
                                     //if not found
    boolean result;
    result = c.contains( i );
    return result;
    
   }

   public void insert(int i){        //searches the collection for int i using the belongs method, if belongs returns 
                                     //true then insert does nothing. If belongs returns false then i is put into the 
                                     //collection after the last non-zero value
                                     //as a void function insert(int i) returns nothing
     Integer I=new Integer(i);
     if ((i>0)&&(!c.contains(i))){  
	c.addFirst(I); 
     }
   }

   public void omit(int i){          //searches the collection for int i, if i is not found in the collection then
                                     //nothing happens. if i is found then it is removed from the collection
                                     //as a void function omit(int i) returns nothing
    Integer temp = new Integer(i);
    if( i>0 ){
        this.c.remove( temp );
        howMany--;
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
    ListIterator<Integer> I = this.c.listIterator();
    while( I.hasNext() ){
        Integer nextTemp = I.next();
        System.out.println( nextTemp.intValue() );
    }
   }

   public boolean equals(Intcoll5 obj){ //compares another Intcoll object with this Intcoll object
                                        //if every int in both Intcoll arrays are the same, the items have the same
                                        //collection contents up until the SENTINAL and order does NOT matter,
                                        //then equals returns true, if not then equals returns false
    boolean result;
    ListIterator<Integer> I = this.c.listIterator();
    result = (this.howMany == obj.howMany);
    
    while( result && I.hasNext() ){
        int objVal = I.next();
        result = obj.c.contains( objVal );
    }
    return result;
   }

}