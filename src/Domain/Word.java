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
public class Word {
    public String text;
    public int position;
    
    public void Word(String text, int position){
        this.text=text;
        this.position=position;
    }
}
