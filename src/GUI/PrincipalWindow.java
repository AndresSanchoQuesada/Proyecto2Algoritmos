/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author andres
 */
    public class PrincipalWindow extends JFrame implements ActionListener {
    
    JDesktopPane desktopPane;// cuando no se pone la visibilidad por defecto es privated
    // private InternalRegistrarGenero primerInternalFrame;
    private JMenuBar mn;
    private JMenu jmOpciones;
    private JMenuItem  jMenuItemChargeFile;
    private JMenuItem  jMenuItem2;
    private JMenuItem  jMenuItem3;
    
    
    public PrincipalWindow(){
        super();
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.desktopPane= new JDesktopPane();
        this.desktopPane.setLayout(null);
   
         init();
        this.add(this.desktopPane);
        
    }// VentanaPrincipal() constructor
    
    private void init(){
        
        this.mn=new JMenuBar();
        setJMenuBar(this.mn);
        this.jmOpciones=new JMenu("Opciones");
        this.mn.add(jmOpciones);
        
        this.jMenuItemChargeFile=new JMenuItem("Cargar Archivo");
        this.jMenuItemChargeFile.addActionListener(this);
        this.jmOpciones.add(this.jMenuItemChargeFile);
        
        this.jMenuItem2=new JMenuItem("item1");
        this.jMenuItem2.addActionListener(this);
        this.jmOpciones.add(this.jMenuItem2);

        this.jMenuItem3=new JMenuItem("item2");
        this.jMenuItem3.addActionListener(this);
        this.jmOpciones.add(this.jMenuItem3);
        
    }//init()

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.jMenuItemChargeFile){
            
             String aux = "";
        String text = "";
        
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(file);
            File open = file.getSelectedFile();
            if (open != null) {
                FileReader files = null;
                 try {
                     files = new FileReader(open);
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
        
    

            
        }else if(e.getSource()==this.jMenuItem2){
        //Llamar internalActualizar

            
            
            }else if(e.getSource()==this.jMenuItem3){
        
                //llamar internalBuscar
                
            }else{
                JOptionPane.showMessageDialog(null,"Error");
    }
        
    }// metodo actionPerformed
    
}
