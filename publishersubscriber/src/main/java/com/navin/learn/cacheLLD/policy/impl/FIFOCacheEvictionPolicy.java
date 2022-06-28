package com.navin.learn.cacheLLD.policy.impl;

import com.navin.learn.cacheLLD.dataStructure.DoubleLinkedListNode;
import com.navin.learn.cacheLLD.dataStructure.DoublyLinkedList;
import com.navin.learn.cacheLLD.policy.EvictionPolicy;
import com.sun.media.sound.InvalidDataException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class FIFOCacheEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private final DoublyLinkedList<Key> dll;
    private final HashMap<Key, DoubleLinkedListNode<Key>> map;

    public FIFOCacheEvictionPolicy(){
        this.dll = new DoublyLinkedList<>();
        this.map = new HashMap<>();
    }



    @Override
    public void keyAccessed(Key key) throws InvalidDataException {
        if(!map.containsKey(key)){
            dll.insertElementAtEnd(key);
        }
    }

    @Override
    public Key evict() {
        DoubleLinkedListNode<Key> fifoNode = dll.getFirstNode();
        if(Objects.isNull(fifoNode)){
            return null;
        }
        this.map.remove(fifoNode.getElement());
        this.dll.detachNode(fifoNode);
        return fifoNode.getElement();
    }
}
