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
public class Queue {

    //**********ATRIBUTOS**********
    private Node head;
    private Node tail;
    private int size;

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }//Constructor.

    public void enqueue(Node node) {

        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }//Entra como el inicio y final si la lista está vacía, sino, se inserta
        //al final.    
        this.size++;
    }//Fin del método enqueue que inserta siempre al final de la cola.

    public void dequeue() {
        if (this.size == 0) {

        } else {
            Node temp = this.head.next;
            this.head = temp;
            this.size--;
        }//Si la lista está vacía no hace nada, sino, elimina el primero de la cola.
    }//Fin del metodo dequeue que quita el primer elemento de la cola.

    public int size() {
        return size;
    }//Retorna el tamaño de la pila.

    public Node first() {
        return this.head;
    }//Retorna el primer elemento de la pila.

}//Fin de la clase Queue.
