/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.SelfBalancingBinarySerchTree;
import Domain.SBBSTNode;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author andres
 */
public class Panel extends JPanel{
    
    private final SelfBalancingBinarySerchTree tree;
    int tamanioNodo = 80;
    private final int HEIGHT;
    private final int WIDTH;
    
    public Panel(SelfBalancingBinarySerchTree tree){
        
        this.tree = tree;
        int treeHeight = this.tree.root.height;
        
        this.HEIGHT = 2*treeHeight*tamanioNodo;
        this.WIDTH = (int) Math.pow(2, treeHeight)*tamanioNodo*2;
        
        this.setPreferredSize(new Dimension(HEIGHT, WIDTH));
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        SBBSTNode root = this.tree.root;
        
        g2.setColor(Color.white);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g2.setColor(Color.black);
        
        paintNode(g2, root, WIDTH, 0, 0);
        
    }
    
    public void paintNode(Graphics2D g2, SBBSTNode node, int rightBorder, int leftBorder, int height){
        
        if(node != null){
            int middlePoint = (rightBorder+leftBorder)/2;
            g2.drawOval(middlePoint, height, tamanioNodo, tamanioNodo);
            g2.drawString(node.word, middlePoint, height);

            int newHeight = height + (2*tamanioNodo);

            if(node.left != null){
                g2.drawLine(middlePoint, height+tamanioNodo, (leftBorder + middlePoint)/2, newHeight);
                paintNode(g2, node.left, middlePoint, leftBorder, newHeight);
            }

            if(node.right != null){
                g2.drawLine(middlePoint, height+tamanioNodo, (rightBorder + middlePoint)/2, newHeight);
                paintNode(g2, node.right, rightBorder, middlePoint, newHeight);
            }
        }
        
    }
    
}
