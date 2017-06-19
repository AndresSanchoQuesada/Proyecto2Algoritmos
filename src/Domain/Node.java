/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author andres
 */
public class Node {

    //ATTRIBUTES
    public String word;// palabra

    public String positions;// veces en que se encuentra

    public int value, balanceFactor;// valor de ASCII
    public int init;
    public Node right;
    public Node left;
    public Node next;

    public Node() {
        this.word = "";
        this.right = null;
        this.left = null;
        this.value = 0;
        this.next = null;
    }// default

    public Node(int value, int init, String word, String positions) {
        this.word = word;
        this.right = null;
        this.left = null;
        this.positions = positions;
        this.value = value;
        this.init = init;
    }// build

    //GET'S & SET'S
    public String getWord() {
        return word;
    }//getWord

    public void setWord(String word) {
        this.word = word;
    }//setWord

    public String toString() {

        String out = "";
        String[] positionns = this.positions.split(";");

        out += this.word + "[";
        for (int i = 0; i < positionns.length; i++) {// le dá un acomodo para luego mostrarlo
            if (i == positionns.length - 1) {// si es la última posición
                out += positionns[i] + "]";
            } else {
                out += positionns[i] + ",";
            }// else
        }// for i        
        return out;
    }// to string

    public String toSaveInFile() {

        String out = "";
        String[] positionns = this.positions.split(";");

        out += this.word + "¬";
        for (int i = 0; i < positionns.length; i++) {// le dá un acomodo para luego mostrarlo
            if (i == positionns.length - 1) {// si es la última posición
                out += positionns[i];
            } else {
                out += positionns[i] + ",";
            }// else
        }// for i        
        return out;
    }// toSaveInFile

}// end class Node
