/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Domain.Node;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author andres
 */
public class FileData {
    
    public String openFile() {
        String aux = "";
        String text = "";
        try {
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(file);
            File open = file.getSelectedFile();
            if (open != null) {
                FileReader files = new FileReader(open);
                BufferedReader read = new BufferedReader(files);
                aux = read.readLine();

                while (aux != null) {
                    text += aux;
                    aux = read.readLine();
                    if (aux != null) {
                        text += " ";
                    }//if
                }//while
                read.close();
            }//if
        } catch (IOException e) {
            System.out.println("error: " + e);
        }//try-catch
        return text;
    }//openFIle
    
    public void saveFile(List<String> nodosString) throws IOException {

        JFileChooser file = new JFileChooser();
        file.showSaveDialog(file);
        File save = file.getSelectedFile();
        if (save != null) {
            FileWriter save2 = new FileWriter(save + ".txt");
            for (int i = 0; i < nodosString.size(); i++) {

                String[] temp = nodosString.get(i).split("/");
                int num = Integer.parseInt(temp[1]);
                int ini = temp[0].codePointAt(0);
                Node newNode = new Node(num, ini, temp[0], temp[2]);

                save2.write(newNode.toSaveInFile() + ";");
            }// fin for i
            save2.close();
        }// if

    }//saveFile
    
        public String nodesToText(List<String> nodosString) {

        List<Node> nodos = new ArrayList<>();

        for (int x = 0; x < nodosString.size(); x++) {

            String[] temp = nodosString.get(x).split("¬");
            Node newNode = new Node(0, 0, temp[0], temp[1]);
            nodos.add(newNode);
            // cambio de string a nodos   
        }//for x

        String out = "";
        String[] text = new String[countPositions(nodos)];

        for (int i = 0; i < nodos.size(); i++) {

            Node nodo = nodos.get(i);
            String[] tempPositions = nodo.positions.split(",");

            for (int j = 0; j < tempPositions.length; j++) {
                int position = Integer.parseInt(tempPositions[j]);
                text[position] = nodo.word;
                // recorrido de las posiciones
            }//for j
            // recorrido de los nodos   

        }//for i

        for (int a = 0; a < text.length; a++) {
            out += text[a] + " ";
            // llenado del texto
        }//for a
        return out;

    }// nodes to text

    public String openNodesFile() {
        String aux = "";
        String text[] = null;
        try {
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(file);
            File open = file.getSelectedFile();
            if (open != null) {
                FileReader files = new FileReader(open);
                BufferedReader read = new BufferedReader(files);
                aux = read.readLine();
                text = aux.split(";");
                read.close();
            }//if
        } catch (IOException e) {
            System.out.println("error: " + e);
        }//tyr-catch

        List<String> list = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            list.add(text[i]);
        }//for i
        String out = nodesToText(list);
        return out;
    }//openFIle

    public int countPositions(List<Node> nodos) {

        int counter = 0;
        for (int i = 0; i < nodos.size(); i++) {

            Node nodo = nodos.get(i);
            String[] tempPositions = nodo.positions.split(",");

            for (int j = 0; j < tempPositions.length; j++) {
                counter++;
            }// recorrido de las posiciones

        }// recorrido de los nodos

        return counter;

    }// countPositions
    
}
