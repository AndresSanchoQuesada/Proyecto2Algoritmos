/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data.FileData;
import Domain.SelfBalancingBinarySerchTree;
import Domain.TextsMethods;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author andres
 */
public class PrincipalWindow extends JFrame implements ActionListener {

    JDesktopPane desktopPane;// cuando no se pone la visibilidad por defecto es privated
    // private InternalRegistrarGenero primerInternalFrame;
    private JMenuBar mn;
    private JMenu jmOpciones;
    private JMenuItem jMenuItemChargeFile;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
    private JMenuItem jMenuItem5;
    private JMenuItem jMenuItem6;
    
    private JTextArea jta;
    JScrollPane scrollPane;
    TextsMethods textsMethods;
    FileData data;

    String text;
    String text2;
    SelfBalancingBinarySerchTree balancingBinarySearchTree;

    public static String finalArray[];
    public static int finalSize;

    public PrincipalWindow() {
        super("Proyect 2");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.desktopPane = new JDesktopPane();
        this.desktopPane.setLayout(null);

        init();
        this.add(this.desktopPane);

    }// VentanaPrincipal() constructor

    private void init() {
        //this.balancingBinarySearchTree = new SelfBalancingBinarySearchTree();
        this.textsMethods = new TextsMethods();
        //this.data = new FileData();

        this.mn = new JMenuBar();
        setJMenuBar(this.mn);
        this.jmOpciones = new JMenu("Opciones");
        this.mn.add(jmOpciones);

        this.jMenuItemChargeFile = new JMenuItem("Cargar Archivo");
        this.jMenuItemChargeFile.addActionListener(this);
        this.jmOpciones.add(this.jMenuItemChargeFile);

        this.jMenuItem2 = new JMenuItem("Inserto");
        this.jMenuItem2.addActionListener(this);
        this.jmOpciones.add(this.jMenuItem2);

        this.jMenuItem3 = new JMenuItem("Comprimir archivo");
        this.jMenuItem3.addActionListener(this);
        this.jmOpciones.add(this.jMenuItem3);

        this.jMenuItem4 = new JMenuItem("Safe File Compress");
        this.jMenuItem4.addActionListener(this);
        this.jMenuItem4.setEnabled(false);
        this.jmOpciones.add(this.jMenuItem4);
        
        this.jMenuItem5 = new JMenuItem("Descompress file");
        this.jMenuItem5.addActionListener(this);
        this.jMenuItem5.setEnabled(false);
        this.jmOpciones.add(this.jMenuItem5);
        
        this.jMenuItem6 = new JMenuItem("Show Tree");
        this.jMenuItem6.addActionListener(this);
        this.jMenuItem6.setEnabled(false);
        this.jmOpciones.add(this.jMenuItem6);

        this.jta = new JTextArea();
        //this.jta.setBounds(100, 100, 500, 400);
        this.jta.setBackground(Color.CYAN);
        this.jta.setEditable(false);
        this.jta.setVisible(false);
        this.add(jta);

        scrollPane = new JScrollPane(jta);
        scrollPane.setBounds(10, 20, 500, 400);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVisible(false);
        this.add(scrollPane);

        this.balancingBinarySearchTree = new SelfBalancingBinarySerchTree();

    }//init()

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jMenuItemChargeFile) {

            String aux;
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
                    finalSize = finalArray.length;

                    if (!text.equals("")) {
                        this.jta.setText(text);
                        this.jta.setVisible(true);
                        scrollPane.setVisible(true);
                    }//if

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

        } else if (e.getSource() == this.jMenuItem2) {
            //Llamar internalActualizar

            if (!jta.getText().equals("")) {
                balancingBinarySearchTree.reparatedWords(text2);
            }

        } else if (e.getSource() == this.jMenuItem3) {
            //System.out.println(balancingBinarySearchTree.countNodes());
            //llamar internalBuscar
            balancingBinarySearchTree.inorder();
            this.jMenuItem4.setEnabled(true);
            this.jMenuItem5.setEnabled(true);
            this.jMenuItem6.setEnabled(true);

        } else if (e.getSource() == jMenuItem4) {
            System.out.println(balancingBinarySearchTree.stack.size());

            JFileChooser jF1 = new JFileChooser();
            jF1.setApproveButtonText("Guardar");
            jF1.showSaveDialog(null);
            File file = new File(jF1.getSelectedFile() + "Arbol.txt");
            
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                
                while (balancingBinarySearchTree.stack.size() > 0) {

                    bw.write(balancingBinarySearchTree.stack.pop() + "\n");

                }

                bw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else if(e.getSource()==jMenuItem5){     
            balancingBinarySearchTree.descompressFile();
            
        } else if(e.getSource()==jMenuItem6){
            Panel displayTree = new Panel(balancingBinarySearchTree);
            JFrame frame = new JFrame();
            JScrollPane scrollpaneV = new JScrollPane(displayTree);
            JScrollPane scrollpaneH = new JScrollPane(displayTree);
            scrollpaneV.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollpaneH.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            frame.add(scrollpaneV);
            frame.add(scrollpaneH);
            frame.pack();
            frame.setVisible(true);
            
        }

    }// metodo actionPerformed

}