/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment6;
import java.util.*;

public class StringcollClient {
   public static final String SENTINEL = "###";

   public static void main(String[] args)
   {
      String word; Scanner keyboard=new Scanner(System.in);
      Stringcoll P=new Stringcoll(), N=new Stringcoll(), L= new Stringcoll();

      System.out.println("Enter a word to be inserted or ### to quit:");
      word =keyboard.next();
      while(!SENTINEL.equals(word))
      {
         String temp = word.substring(1);
         String val = word.substring(0,1);
         
         if ( val.equals("+")) {P.insert(temp); L.insert(temp);}
         else {N.insert(temp); L.omit(temp);}
         
         System.out.println("Enter a word to be inserted or ### to quit:");
         word=keyboard.next();
      }
      System.out.println("\nThe words in collection P are:");
      P.print();
      System.out.println("\nThe words in collection N are:");
      N.print();
      System.out.println("\nThe words in collection L are:");
      L.print();
      if (P.equals(N)) System.out.println("\nP and N are equal.");
      else System.out.println("\nP and N are NOT equal.");
      Stringcoll A=new Stringcoll(); A.copy(L);
      System.out.println("\nThe words in the copy of L are:\n");
      A.print();
   }
   
    
}
