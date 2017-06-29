/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author emilio
 */
public class SBBSTNode {
    
     public SBBSTNode left, right;
     public String word;
     String [] array;
     public int height;

     /* Constructor */

     public SBBSTNode()
     {
         left = null;
         right = null;
         word = "";
         array = null;
         height = 0;
     }

     /* Constructor */

     public SBBSTNode(String word, String [] array)
     {
         left = null;
         right = null;
         this.word = word;
         this.array = array;
         height = 0;
     }     
     
}//AVLNode
    

