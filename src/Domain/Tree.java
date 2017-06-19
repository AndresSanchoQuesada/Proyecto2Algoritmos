/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Domain.Node;
import Domain.Queue;

/**
 *
 * @author andres
 */
public class Tree {

    public Node root;

    public Tree() {
        this.root = null;
    }//build

    public Node search(int data, String name, Node myNode) {
        if (myNode == null) {
            return null;
        } else {// if
            if (myNode.value == data) {
                if (myNode.word.equals(name)) {
                    return myNode;
                } else if (myNode.word.equals(name) && myNode.value != data) {
                    return search(data, name, myNode);
                }//else if
            }//if
            if (myNode.value < data) {// if dentro del else
                if (myNode.word.equals(name)) {
                    return myNode;
                } else {
                    return search(data, name, myNode.right);
                }//else
            } else{
                if (myNode.word.equals(name)) {
                    return myNode;
                } else {
                    return search(data, name, myNode.left);
                }// else 
            }//else
            
        }// else general 
    }// search

    public String readingByLevels(Node node) {
        String readingLevels = "";
        Queue queue = new Queue();
        Node aux = null;
        
        if (node != null) {
            queue.enqueue(node);
            
            while (queue.size() > 0) {
                aux = queue.first();
                readingLevels += aux.word + "-";
                queue.dequeue();
                
                if (aux.left != null) {
                    queue.enqueue(aux.left);
                }//Si el nodo izquiero no es nulo, se agrega a la cola.
                
                if (aux.right != null) {
                    queue.enqueue(aux.right);
                }//Si el nodo derecho no es nulo, se agrega a la cola.
                
            }//Mientras la cola tenga elementos, se agregan nodos al String.
            
        }//Si el árbol tiene elementos, lo recorre.
        
        return readingLevels;
    }//readingByLevels.

    public int getBF(Node x) {
        if (x == null) {
            return -1;
        } else {// if general
            return x.balanceFactor;
        }// else general
    }// getBF

    public Node leftRotation(Node c) {

        Node aux = c.right;
        c.right = aux.left;
        aux.left = c;

        c.balanceFactor = Math.max(getBF(c.left), getBF(c.right) + 1);
        aux.balanceFactor = Math.max(getBF(aux.left), getBF(aux.right));

        return aux;

    }// leftRotation

    public Node rightRotation(Node c) {

        Node aux = c.left;
        c.left = aux.right;
        aux.right = c;

        c.balanceFactor = Math.max(getBF(c.left), getBF(c.right) + 1);
        aux.balanceFactor = Math.max(getBF(aux.left), getBF(aux.right));

        return aux;

    }// rightRotation

    public Node left_right_rotation(Node c) {

        Node temp;
        c.left = leftRotation(c.left);
        temp = rightRotation(c);

        return temp;

    }//left_right_rotation

    public Node right_left_rotation(Node c) {

        Node temp;
        c.right = rightRotation(c.right);
        temp = leftRotation(c);

        return temp;

    }//right_left_rotation

    public Node insertAVL(Node new_, Node subTree) {
        Node newParent = subTree;

        if (this.root == null) {
            this.root = new_;
        }// si la raíz está vacia

        if (new_.init != subTree.init) { // si son diferentes luego pregunta por el value

            if (new_.value < subTree.value) {
                if (subTree.left == null) {
                    subTree.left = new_;
                } else {
                    subTree.left = insertAVL(new_, subTree.left);

                    if ((getBF(subTree.left) - getBF(subTree.right) == 2)) {
                        if (new_.value < subTree.left.value) {
                            newParent = rightRotation(subTree);
                        } else {
                            newParent = left_right_rotation(subTree);
                        }//if else
                    }//if
                }//if else
            } else if (new_.value > subTree.value) {
                if (subTree.right == null) {
                    subTree.right = new_;
                } else {
                    subTree.right = insertAVL(new_, subTree.right);

                    if ((getBF(subTree.left) - getBF(subTree.right) == -2)) {
                        if (new_.value > subTree.right.value) {
                            newParent = leftRotation(subTree);
                        } else {
                            newParent = right_left_rotation(subTree);
                        }//if-else
                    }//if
                }//if else

            }//else if

        }// if (new_.init != subTree.init)
        else if (new_.init == subTree.init) {// si son diferentes los init

            if (new_.value < subTree.value) {
                if (subTree.left == null) {
                    subTree.left = new_;
                } else {
                    subTree.left = insertAVL(new_, subTree.left);

                    if ((getBF(subTree.left) - getBF(subTree.right) == 2)) {
                        if (new_.value < subTree.left.value) {
                            newParent = rightRotation(subTree);
                        } else {
                            newParent = left_right_rotation(subTree);
                        }//else
                    }//if
                }//if-else
            } else if (new_.value > subTree.value) {
                if (subTree.right == null) {
                    subTree.right = new_;
                } else {
                    subTree.right = insertAVL(new_, subTree.right);

                    if ((getBF(subTree.left) - getBF(subTree.right) == -2)) {
                        if (new_.value > subTree.right.value) {
                            newParent = leftRotation(subTree);
                        } else {
                            newParent = right_left_rotation(subTree);
                        }//else
                    }//if
                }//if-else

            } else if (new_.value == subTree.value) { // ascii son iguales

                if (new_.word.length() < subTree.word.length()) { // averiguar por tamaño de palabra

                    if (subTree.left == null) {
                        subTree.left = new_;
                    } else {
                        subTree.left = insertAVL(new_, subTree.left);

                        if ((getBF(subTree.left) - getBF(subTree.right) == 2)) {
                            if (new_.value < subTree.left.value) {
                                newParent = rightRotation(subTree);
                            } else {
                                newParent = left_right_rotation(subTree);
                            }//else
                        }//if
                    }//if-else
                } else if (new_.word.length() > subTree.word.length()) { 

                    if (subTree.right == null) {
                        subTree.right = new_;
                    } else {
                        subTree.right = insertAVL(new_, subTree.right);

                        if ((getBF(subTree.left) - getBF(subTree.right) == -2)) {
                            if (new_.value > subTree.right.value) {
                                newParent = leftRotation(subTree);
                            } else {
                                newParent = right_left_rotation(subTree);
                            }//else
                        }//if
                    }//if else

                }// else if

            }// si son iguales sus ascii

        }// else if si son igguales

        //updata bf
        if ((subTree.left == null) && (subTree.right != null)) {
            subTree.balanceFactor = subTree.right.balanceFactor + 1;
        } else if ((subTree.right == null) && (subTree.left != null)) {
            subTree.balanceFactor = subTree.left.balanceFactor + 1;
        } else {
            subTree.balanceFactor = Math.max(getBF(subTree.left), getBF(subTree.right)) + 1;
        }// if else

        return newParent;
    }// insert AVL

    public void insert(String nod) {
        String[] wordTemp = nod.split("/");
        int num = Integer.parseInt(wordTemp[1]);
        int ini = wordTemp[0].codePointAt(0);
        Node newNode = new Node(num, ini, wordTemp[0], wordTemp[2]);
        if (root == null) {
            root = newNode;
        } else {
            root = insertAVL(newNode, root);
        }//else
    }// insert

}//tree
