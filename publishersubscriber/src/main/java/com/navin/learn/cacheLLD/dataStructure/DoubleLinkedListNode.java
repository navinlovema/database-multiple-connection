package com.navin.learn.cacheLLD.dataStructure;

public class DoubleLinkedListNode<E> {

    private final E ele;

    DoubleLinkedListNode<E> prev, next;

    public DoubleLinkedListNode(E ele){
        this.ele = ele;
        this.prev = null;
        this.next = null;
    }

    public E getElement(){
        return this.ele;
    }
}
