/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment4;

/**
 *
 * @author Merisa
 */
public class Intcoll6 {
   private int howMany;
   private btNode c;

   public Intcoll6(){
      c = null;
      howMany = 0;
   }

   public Intcoll6(int i){
      c = null;
      howMany = 0;
   }
    
    public void print(){
        printtree(c);
    }
    private static void printtree(btNode t){
        if(t!= null){
            printtree(t.left);
            System.out.println(t.info);
            printtree(t.right);
        }
    }

    private static btNode copytree(btNode t){
      btNode root=null;
      if (t!=null){
        root=new btNode();
        root.info=t.info; 
        root.left=copytree(t.left);
        root.right=copytree(t.right);
      }
      return root;
    }
    
    public void copy(Intcoll6 obj){
        if (this!=obj){
            this.howMany=obj.howMany;
            c=copytree(obj.c);  
        }
    }
   
    public void insert(int i){
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

    public void omit(int i){
        if((i>0) && (this.belongs(i))){
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

    public boolean belongs(int i){
        btNode p=c;
        while ((p!=null)&&(p.info!=i)){
            if (p.info>i) p=p.left;
            else p=p.right;
        }
      return (p!=null); 
    }

   public int getHowmany() {
       return howMany;
   }
   
   
    
    public boolean equals(Intcoll6 obj){
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
    public static int toarray(btNode t, int[] a, int i){                        // toarray takes a btNode, int array, and staring index and fills
        int num_nodes = 0;                                                      // the array starting at specified index and returns the number of 
        if( t != null){                                                         // nodes in the btree
            num_nodes = toarray(t.left,a,i);
            a[num_nodes+i] = t.info;
            num_nodes = num_nodes+1+toarray(t.right, a, num_nodes+i+1);
        }
        return num_nodes;
    }
    public static void equalstree(int[] ray, int i, btNode t){
        if( t != null){
            equalstree(ray, i, t.left);
            ray[i] = t.info;
            equalstree(ray, i, t.right);
        }
    }
    
    // btNode is described in assignment on canvas, most likely
    // inner class btNode ***insert***
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
