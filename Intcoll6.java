/* Intcoll6.java 
 * this file contains the class Intcoll, a collection of ints
 * 
 *  @version  20190308
 *  @author   Athena Evans
 */

public class Intcoll6 {
   private int howMany;          // c refers to the collection
   private btNode c;             // howMany is the collection count
   
   public Intcoll6(){            // instantiation of Intcoll6, no param
      c = null;
      howMany = 0;
   }

   public Intcoll6(int i){       // instantiation of Intcoll6, int param i is for consistency
      c = null;
      howMany = 0;
   }
    
    public void print(){         //formats the info of the collection and prints as a series of strings
                                 //as a void function print() returns nothing
        printtree(c);
    }
    private static void printtree(btNode t){
        if(t!= null){
            printtree(t.left);
            System.out.println(t.info);
            printtree(t.right);
        }
    }
    
    public void copy(Intcoll6 obj){       //makes a copy of another Intcoll object and if they are the same then copy does
                                          //nothing. If it does not match then copy replaces this Intcoll object with the 
                                          //copy. REQ ANOTHER Intcoll obj.
                                          //example --> a.copy(b) will make it so that a contains the same array as b
                                          //as a void function copy(Intcoll4 obj) returns nothing
        if (this!=obj){
            this.howMany=obj.howMany;
            c=copytree(obj.c);  
        }
    }
    private static btNode copytree(btNode t){      // copytree determines the order things are printed in
                                                   // copytree does an inorder iteration
      btNode root=null;
      if (t!=null){
        root=new btNode();
        root.info=t.info; 
        root.left=copytree(t.left);
        root.right=copytree(t.right);
      }
      return root;
    }
   
    public void insert(int i){       //searches the collection for int i using the belongs method, if belongs returns 
                                     //true then insert does nothing. If belongs returns false then i is put into the 
                                     //collection after the last non-zero value
                                     //as a void function insert(int i) returns nothing
        if (i>0){
            btNode pred=null, p=c;
            while ((p!=null)&&(p.info!=i)){
                pred=p;
                if (p.info>i) p=p.left;
                else p=p.right;
            }
            if (p==null){
                howMany++; 
                p=new btNode(i, null, null);
                if (pred!=null){
                    if (pred.info>i) pred.left=p;
                    else pred.right=p;
                }
                else c=p;
            }
        }
    }

    public void omit(int i){                    //searches the collection for int i, if i is not found in the collection then
                                                //nothing happens. if i is found then it is removed from the collection
                                                //as a void function omit(int i) returns nothing
       
        if((i>0) && (this.belongs(i))){         //non zero and is in collection check
            howMany--;
            btNode pred = null;
            btNode p = c;
            while( (p!=null) && (p.info!=i) ){  // assign p to the btNode with i
                                                // assign prev as its predicessor
                pred = p;
                if(i<p.info) p=p.left;
                else p=p.right;
            }
            
            btNode q = new btNode();            // assign the other subtree of prev to q
                                                // the loop makes it so that q points to the leftmost (if p is left)
                                                // or to the rightmost (if p is right) subtree of prev
            if( i < pred.info) q = pred.right;
            else q = pred.left;
            while(q!=null){
                if(i<q.info) q=q.right;
                else q=q.left;
            }
            
            if(p!=pred.right){
                pred.left = p.left;
                q = p.right;
            }
            else{
                pred.right = p.right;
                q = p.left;
            }
            
        }
    }

    public boolean belongs(int i){     //searches the collection for int i, returns true if found in collection and false 
                                       //if not found
        btNode p=c;
        while ((p!=null)&&(p.info!=i)){ //iterate through collection
            if (p.info>i) p=p.left;
            else p=p.right;
        }
      return (p!=null); 
    }

   public int getHowmany() {         //interates over the c array and counts the items that are not zero, this will
                                     //return the amount of viable values in the collection, this replaces a length-
                                     //type method since the size of the array might not be the same as the true size
                                     //of the collection, see empty param instantiation
       return howMany;
   }
   
   
    
    public boolean equals(Intcoll6 obj){     //compares another Intcoll object with this Intcoll object
                                             //if every int in both Intcoll arrays are the same, the items have the same
                                             //collection contents up until the SENTINAL and order does NOT matter,
                                             //then equals returns true, if not then equals returns false
        boolean result = (this.howMany == obj.howMany);
        if(result){
            // traverse and compare each node of the array
            // put all btNodes of A in an array
            int[] ARay = new int[howMany];
            int j = 0;
            j = toarray(this.c , ARay, j);
            // put all btNodes of B in an array
            int[] BRay = new int[howMany];
            int k=0;
            k = toarray(obj.c, BRay, k);
            // compare each array item to the corresponding
            if(j!= k) result = false;
            for(int i=0; i < howMany; i++){
                if( ARay[i] != BRay[i] ) result = false;
            }
        }
        return result;
    }
    public static int toarray(btNode t, int[] a, int i){             // toarray takes a btNode, int array, and staring index and fills
        int num_nodes = 0;                                           // the array starting at specified index and returns the number of 
        if( t != null){                                              // nodes in the btree
            num_nodes = toarray(t.left,a,i);
            a[num_nodes+i] = t.info;
            num_nodes = num_nodes+1+toarray(t.right, a, num_nodes+i+1);
        }
        return num_nodes;
    }
    public static void equalstree(int[] ray, int i, btNode t){          // why did I put equalstree here
                                                                        // equalstree orders items into an array, but toarray does this
        if( t != null){
            equalstree(ray, i, t.left);
            ray[i] = t.info;
            equalstree(ray, i, t.right);
        }
    }
    
    
   
   // inner class btNode
    private static class btNode{
       int info; 
       btNode left; 
       btNode right;

       private btNode(int s, btNode lt, btNode rt){
          info=s; 
          left=lt; 
          right=rt;  
       }

       private btNode(){
          info=0; 
          left=null; 
          right=null;
       }
   }
   
}
