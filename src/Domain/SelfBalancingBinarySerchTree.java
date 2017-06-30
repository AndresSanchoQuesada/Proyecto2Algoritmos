/*
*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import GUI.PrincipalWindow;
import static GUI.PrincipalWindow.finalArray;
import static GUI.PrincipalWindow.finalSize;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;

/**
 *
 * @author emilio
 */
public class SelfBalancingBinarySerchTree {

    public SBBSTNode root;
    String packer = "";

    public Stack<String> stack = new Stack();
    String[] descompressArray;
    public static String finalText;

    /* Constructor */
    public SelfBalancingBinarySerchTree() {

        root = null;
        //TEXTSMETHODS 

    }

    /* Function to check if tree is empty */
    public boolean isEmpty() {

        return root == null;

    }

    /* Make the tree logically empty */
    public void clear() {

        root = null;

    }

    /* Function to insert data */
    public void insert(String word, String[] array) {

        root = insert(word, array, root);

    }

    /* Function to get height of node */
    private int height(SBBSTNode t) {

        return t == null ? -1 : t.height;

    }

    /* Function to max of left/right node */
    private int max(int lhs, int rhs) {

        return lhs > rhs ? lhs : rhs;

    }

    /* Function to insert data recursively */
    private SBBSTNode insert(String word, String[] array, SBBSTNode t) {

        int flag;
        int flagLeft;
        int flagRight;

        if (t == null) {

            t = new SBBSTNode(word, array);
            //System.out.println("Paso por aqui");
            //this.root = t;
        } else if ((flag = compareWords(word, t)) == 0) {

            // System.out.println("Uno izquerdo");
            t.left = insert(word, array, t.left);
//             t.left = new SBBSTNode(word, array);

            if (height(t.left) - height(t.right) == 2) {

                if ((flagLeft = compareWords(word, t.left)) == 0) {

                    t = rotateWithLeftChild(t);

                } else {

                    t = doubleWithLeftChild(t);

                }//else
            }//if

        }//if
        else if ((flag = compareWords(word, t)) == 1) {
            System.out.println("derecha");
            t.right = insert(word, array, t.right);
//             t.right = new SBBSTNode(word, array);

            if (height(t.right) - height(t.left) == 2) {

                if ((flagRight = compareWords(word, t.right)) == 1) {

                    t = rotateWithRightChild(t);

                } else {

                    t = doubleWithRightChild(t);

                }//else
            }//if

        }//if
        else

           ;  // Duplicate; do nothing

        t.height = max(height(t.left), height(t.right)) + 1;

        return t;

    }

    /* Rotate binary tree node with left child */
    private SBBSTNode rotateWithLeftChild(SBBSTNode k2) {

        SBBSTNode k1 = k2.left;

        k2.left = k1.right;

        k1.right = k2;

        k2.height = max(height(k2.left), height(k2.right)) + 1;

        k1.height = max(height(k1.left), k2.height) + 1;

        return k1;

    }

    /* Rotate binary tree node with right child */
    private SBBSTNode rotateWithRightChild(SBBSTNode k1) {

        SBBSTNode k2 = k1.right;

        k1.right = k2.left;

        k2.left = k1;

        k1.height = max(height(k1.left), height(k1.right)) + 1;

        k2.height = max(height(k2.right), k1.height) + 1;

        return k2;

    }

    /**
     *
     * Double rotate binary tree node: first left child
     *
     * with its right child; then node k3 with new left child
     */
    private SBBSTNode doubleWithLeftChild(SBBSTNode k3) {

        k3.left = rotateWithRightChild(k3.left);

        return rotateWithLeftChild(k3);

    }

    /**
     *
     * Double rotate binary tree node: first right child
     *
     * with its left child; then node k1 with new right child
     */
    private SBBSTNode doubleWithRightChild(SBBSTNode k1) {

        k1.right = rotateWithLeftChild(k1.right);

        return rotateWithRightChild(k1);

    }

    /* Functions to count number of nodes */
    public int countNodes() {

        return countNodes(root);

    }

    private int countNodes(SBBSTNode r) {

        if (r == null) {
            return 0;
        } else {

            int l = 1;

            l += countNodes(r.left);

            l += countNodes(r.right);

            return l;

        }

    }

    /* Function for inorder traversal */
    public void inorder() {
        inorder(root);
    }

    private void inorder(SBBSTNode r) {
        if (r != null) {
            inorder(r.left);
            System.out.println(Arrays.toString(r.array) + r.word);
            stack.push(Arrays.toString(r.array) + r.word);
            inorder(r.right);

            /// 
        }
        //show(stack);
    }

    public void show(Stack stack) {
        System.out.println(stack.size());
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

                System.out.println("Palabra: " + word + " .En posiciones: " + wordIndex);

                insert(word, wordArray);

            } //if
        } //for
    }//reparatedWords

    public int compareWords(String word, SBBSTNode t) {

        int charsNumbers;

        if (word.length() > t.word.length()) {
            charsNumbers = t.word.length();
        } else {
            charsNumbers = word.length();
        }

        int charW;
        int charT;
        int flag = 0;

        for (int i = 0; i < charsNumbers; i++) {
            charW = (int) word.charAt(i);
            charT = (int) word.charAt(i);

            if (charW != charT) {
                if (charW > charT) {
                    flag = 1;
                    return flag;

                }//if
            }//if
        }//for
        return flag;
    }

    public void descompressFile() {
        String aux, text, text2;
        text = "";
        text2 = "";

        JFileChooser file = new JFileChooser();
        file.showOpenDialog(file);
        File open = file.getSelectedFile();
        if (open != null) {
            FileReader files = null;
            try {
                files = new FileReader(open);
                BufferedReader read = new BufferedReader(files);

                while ((aux = read.readLine()) != null) {
                    text += aux + "\n";
                    text2 += aux + " ";

                }

                read.close();

                finalArray = text2.split(" ");
                finalSize = finalArray.length - 1;

                System.out.print("ESTO ES EL RESULTADO FINAL \n" + text);

                descompressArray = text.split("\n");
                System.out.println("Contenido des desco array: " + Arrays.toString(descompressArray));
                String index = "";
                String indexArray[];
                String word = "";
                boolean flag;
                char charNumber;
                int auxNumber = 0;
                int cont = 1;
                int cont2 = 1;
                int cont3 = 0;
                int parch = 0;
                System.out.println("tamano de dessco array " + descompressArray.length);

                for (int i = 0; i < descompressArray.length; i++) {
                    cont3++;
                    flag = false;
                    index = "";
                    word = "";
                    parch = descompressArray.length - 1;

                    while (flag == false) {
                        System.out.println("vuelta numero: " + cont3);
                        charNumber = descompressArray[i].charAt(cont);

                        if ((int) charNumber == 93) {
                            auxNumber = cont + 1;
                            cont = 0;

                            flag = true;
                        } else if (charNumber != 93 && charNumber != ' ') {

                            index += charNumber;

                        }

//                        }else if(i == parch){
//                            
//                        }
                        cont++;

//                            if(flag == true){
//                                for(int j = cont+1 ; j < descompressArray[i].length() ; j++){
//                                    word += descompressArray[i].charAt(j);
//                                }//for
//                            }
                    }//while

                    for (int j = auxNumber; j < descompressArray[i].length(); j++) {
                        word += descompressArray[i].charAt(j);
                    }

                    indexArray = index.split(",");

                    loadText(indexArray, word, descompressArray.length - 1, cont2);

                    cont2++;

                }//for
                
                char charLastNumber;
        String lastWord = "";
        String lastIndex = "";
        for (int q = 1; q < descompressArray[descompressArray.length - 1].length(); q++) {
            charLastNumber = descompressArray[descompressArray.length - 1].charAt(q);

            if ((int) charLastNumber == 93) {

                for (int p = q + 1; p < descompressArray[descompressArray.length - 1].length(); p++) {
                    charNumber = descompressArray[descompressArray.length - 1].charAt(p);
                    lastWord += charLastNumber;
                }

                break;
            } else if (charLastNumber != 93 && charLastNumber != ' ') {

                lastIndex += charLastNumber;

            }

        }
        
        String LastIndexArray [] = lastIndex.split(",");
        
                loadText(LastIndexArray, lastWord, descompressArray.length - 1, cont2);

            } //if
            catch (FileNotFoundException ex) {
                Logger.getLogger(PrincipalWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PrincipalWindow.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    files.close();
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void loadText(String[] indexArray, String word, int finalSize, int cont2) {
//        System.out.println("Esto es lo que va en el Vector final \n Posiciones:" + Arrays.toString(indexArray) + " Palabra: " + word);
//        System.out.println("Tamano del vector final "+finalArray.length);

//        char charNumber;
//        String lastWord = "";
//        String lastIndex = "";
//        for (int q = 1; q < descompressArray[descompressArray.length - 1].length(); q++) {
//            charNumber = descompressArray[descompressArray.length - 1].charAt(q);
//
//            if ((int) charNumber == 93) {
//
//                for (int p = q + 1; p < descompressArray[descompressArray.length - 1].length(); p++) {
//                    charNumber = descompressArray[descompressArray.length - 1].charAt(p);
//                    lastWord += charNumber;
//                }
//
//                break;
//            } else if (charNumber != 93 && charNumber != ' ') {
//
//                lastIndex += charNumber;
//
//            }
//
//        }

        if (cont2 <= finalSize+1) {
            for (int i = 0; i < indexArray.length; i++) {
                finalArray[Integer.parseInt(indexArray[i])] = word;

                System.out.print(Integer.parseInt(indexArray[i]) + " ");
            }

        } else {
            finalText = "";

            for (int j = 0; j < finalArray.length; j++) {
                finalText += finalArray[j] + " ";
            }

            System.out.println("ultima posicion" + finalArray[finalArray.length - 1]);

            System.out.println("Contenido de finalText:\n" + finalText);
        }

    }//loadText

}