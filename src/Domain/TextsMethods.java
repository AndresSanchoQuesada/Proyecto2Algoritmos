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

public class TextsMethods {
    
     SelfBalancingBinarySerchTree SELFBALANCINGBINARYSEARCHTHREE;
     
     public TextsMethods(){
         //SELFBALANCINGBINARYSEARCHTHREE = new SelfBalancingBinarySearchTree();
     }

    public void reparatedWords(String text) {
        String textArray[] = text.split(" ");
//        for(int q = 0; q < textArray.length; q++)
//            System.out.println(textArray[q]+": pocision: "+q);
        String wordArray[];
        String word;
        String wordIndex;
        int cont = 0;

        for (String textArray1 : textArray) {
            // hasta que cumpla con todas las palabras
            if (!textArray1.equals("^^") && !textArray1.equals(" ")) {
                wordIndex = "";
                word = textArray1;
                for (int j = 0; j < textArray.length; j++) {
                    if (!textArray[j].trim().equals("^^") && !textArray1.trim().equals(" ")) {
                        
                        if (word.trim().equals(textArray[j].trim())) {
                            wordIndex += String.valueOf(j) + " ";
                            //cont++;
                            textArray[j] = "^^";
                            
                        }
                    } //if
                } //for
                wordArray = wordIndex.split(" ");
                
               
                System.out.println("Palabra: "+word+" .En posiciones: "+wordIndex);
                
                 //SELFBALANCINGBINARYSEARCHTHREE.insert(word, wordArray);
     
            } //if
        } //for
    }//reparatedWords
    
    public  boolean compareWords(String word, SBBSTNode t){
        
        int charsNumbers;
         
         if(word.length() > t.word.length()){
            charsNumbers  = t.word.length();
         }else{
             charsNumbers = word.length();
         }
         
         int charW;
         int charT;
         boolean flag = false;
         
         for(int i = 0 ; i < charsNumbers ; i++){
             charW = (int) word.charAt(i);
             charT = (int) word.charAt(i);
             
             if(charW != charT){
                 if(charW > charT){
                     flag = true;
                     return flag;
                     
                 }//if
             }//if
         }//for
         return flag;
    }

}//