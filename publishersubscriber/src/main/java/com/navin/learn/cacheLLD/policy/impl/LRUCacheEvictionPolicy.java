package com.navin.learn.cacheLLD.policy.impl;

import com.navin.learn.cacheLLD.dataStructure.DoubleLinkedListNode;
import com.navin.learn.cacheLLD.dataStructure.DoublyLinkedList;
import com.navin.learn.cacheLLD.policy.EvictionPolicy;
import com.sun.media.sound.InvalidDataException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class LRUCacheEvictionPolicy<Key> implements EvictionPolicy<Key> {
    private final Map<Key, DoubleLinkedListNode<Key>> map;
    private final DoublyLinkedList<Key> dll;

    public LRUCacheEvictionPolicy(){
        this.map = new HashMap<>();
        this.dll = new DoublyLinkedList<>();
    }


    @Override
    public void keyAccessed(Key key) throws InvalidDataException {
        DoubleLinkedListNode<Key> value = this.map.get(key);
        if(this.map.containsKey(value)){
            this.dll.detachNode(value);
            this.dll.insertNodeAtEnd(this.map.get(key));
        }else{
            final DoubleLinkedListNode<Key> node = dll.insertElementAtEnd(key);
            this.map.put(key, node);
        }
    }

    @Override
    public Key evict() {
        final DoubleLinkedListNode<Key> lruNode = this.dll.getFirstNode();
        if(Objects.isNull(lruNode)){
            return null;
        }
        this.dll.detachNode(lruNode);
        this.map.remove(lruNode.getElement());
        return lruNode.getElement();
    }
}
