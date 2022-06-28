package com.navin.learn.cacheLLD.dataStructure;


import com.sun.media.sound.InvalidDataException;

import java.util.Objects;

public class DoublyLinkedList <E> {

    DoubleLinkedListNode<E> dummyHead;
    DoubleLinkedListNode<E> dummyTail;

    public DoublyLinkedList() {
        this.dummyHead = new DoubleLinkedListNode<>(null);
        this.dummyTail = new DoubleLinkedListNode<>(null);

        this.dummyHead.next = this.dummyTail;
        this.dummyTail.next = this.dummyHead;
    }

    public boolean isEmpty(){
        return this.dummyHead.next == this.dummyTail;
    }

    public void insertNodeAtEnd(final DoubleLinkedListNode<E> node){
        DoubleLinkedListNode<E> tailPrev = this.dummyTail.prev;
        this.dummyTail.prev = node;
        node.next = this.dummyTail;
        tailPrev.next = node;
        node.prev = tailPrev;
    }

    public DoubleLinkedListNode<E> insertElementAtEnd(final E element) throws InvalidDataException {
        if (Objects.isNull(element)){
            throw new InvalidDataException("Invalid Data");
        }
        DoubleLinkedListNode<E> node = new DoubleLinkedListNode<>(element);
        this.insertNodeAtEnd(node);
        return node;
    }

    public void detachNode(DoubleLinkedListNode<E> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public DoubleLinkedListNode<E> getFirstNode(){
        if(isEmpty()) return null;
        return this.dummyTail.prev;
    }
}
