/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;


import java.lang.reflect.Array;

/**
 *
 * @author emilio
 */
public class TextsMethods {

    public void reparatedWords(String text) {
        String textArray[] = text.split(" ");
        for(int q = 0; q < textArray.length; q++)
            System.out.println(textArray[q]+": pocision: "+q);
        String wordArray[];
        String word = "";
        String wordIndex = "";
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
                System.out.println("Palabra: "+word+" en posiciones: "+wordIndex);
                
                //wordArray = wordIndex.split(" ");
                
                //cont = 0;
            } //if
        } //for
//        System.err.println(textArray[0]+" "+textArray[1]+" "+textArray[2]);

    }//reparatedWords

}//