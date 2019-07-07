package Assignment6;

/*  Stringcoll.java
 *  this file contains the class Stringcoll, a collection of strings stored in a linked list
 * 
 *  @version  20190411
 *  @author   Athena Evans
 * */

public class Stringcoll{
    //Stringcoll Variables 
    private ListNode c;                  //int array c holds the collection
    int howMany;

    //Instantiations
    public Stringcoll(){                //instantiation of Stringcoll, no params, makes an array for 500 ints
        c = new ListNode();
        howMany = 0;
    }

    public Stringcoll(int i){           //instantiation of Stringcoll, param int i for size
        c = new ListNode();
        howMany = 0;
    }



    //Stringcoll Methods
    public void copy(Stringcoll obj){   //makes a copy of another Intcoll object and if they are the same then copy does
                                        //nothing. If it does not match then copy replaces this Intcoll object with the 
                                        //copy. REQ ANOTHER Intcoll obj.
                                        //example --> a.copy(b) will make it so that a contains the same array as b
                                        //as a void function copy(Intcoll4 obj) returns nothing
        if(this.c != obj.c){
            ListNode copy = new ListNode();
            copy.word = obj.c.word;
            copy.link = obj.c.link;

            this.c = copy;
            this.howMany = obj.getHowMany();
        }
    }

    public boolean belongs(String i){      //searches the collection for int i, returns true if found in collection and false 
                                           // if not found
        boolean result = false;
        if( howMany == 0) return result;

        ListNode temp = c;
        while( (temp!=null) && (i.equals(temp.word) != true) ){ //iterate through collection
            temp = temp.link;
        }
        if(temp !=null){
            if( i.equals(temp.word) ) result = true;
            else result = false;
        }
    
        return result;
    }

    public void insert(String i){      //searches the collection for int i using the belongs method, if belongs returns 
                                       //true then insert does nothing. If belongs returns false then i is put into the 
                                       //collection after the last non-zero value
                                       //as a void function insert(int i) returns nothing
        if( !i.equals("###") ){ //stops weird sentinel problem
            if( this.belongs(i) == false){ // i is not in collection
                ListNode temp = new ListNode(i, c);
                c = temp;
                howMany++;
            }
        }
    }

    public void omit(String i){          //searches the collection for int i, if i is not found in the collection then
                                     //nothing happens. if i is found then it is removed from the collection
                                     //as a void function omit(int i) returns nothing
        if( !(i.equals("###")) ){ //stops 0 problem
            ListNode temp = c;
            ListNode prevTemp = null;

            while( (!temp.word.equals(i) ) && (temp!=null) ){ //look for i
                prevTemp = temp;
                temp = temp.link;
            }
            if( temp!=null ){ //not last item
                if( temp.word.equals(i) ){ //i was found in collection
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

    public void print(){         //formats the info of the collection and prints as a series of strings
                                 //as a void function print() returns nothing
        ListNode temp = this.c;
        while( temp != null){
            if(!temp.word.equals("###")){
                System.out.println( temp.word );
            }
            temp = temp.link;
        }
    }
    
    public boolean equals(Stringcoll obj){ //compares another Stringcoll object with this Stringcoll object
                                        //if every int in both Stringcoll arrays are the same, the items have the same
                                        //collection contents up until the SENTINAL and order does NOT matter,
                                        //then equals returns true, if not then equals returns false
        boolean result = true;
        ListNode temp = this.c;
        while( (temp!=null) && result ){ //traverse the collection while there are only matches
            result = obj.belongs( temp.word );
            temp = temp.link;
        }
        return result;
    }

    
    // inner class btNode
    private static class ListNode{
        String word; 
        ListNode link; 

        private ListNode(String s, ListNode l){
            word=s; 
            link=l;  
        }

        private ListNode(){
            word=""; 
            link=null;
        }
    }
    
    
}