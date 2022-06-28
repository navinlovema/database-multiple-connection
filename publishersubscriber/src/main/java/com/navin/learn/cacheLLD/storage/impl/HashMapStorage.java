package com.navin.learn.cacheLLD.storage.impl;

import com.navin.learn.cacheLLD.exception.KeyNotFoundException;
import com.navin.learn.cacheLLD.exception.StorageFullException;
import com.navin.learn.cacheLLD.storage.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class HashMapStorage<Key, Value> implements Storage<Key, Value> {

    private final HashMap<Key, Value> storage;
    private final int capacity;

    public HashMapStorage(int capacity){
        this.storage = new HashMap<>();
        this.capacity = capacity;
    }


    @Override
    public void add(Key key, Value value) throws StorageFullException {
        if(this.isFull()) throw new StorageFullException("Storage Full");
        this.storage.put(key, value);
    }

    @Override
    public void remove(Key key) throws KeyNotFoundException {
        if(this.isPresent(key)) throw new KeyNotFoundException();
        this.storage.remove(key);
    }

    @Override
    public Value get(Key key) throws KeyNotFoundException {
        if(this.isPresent(key)) throw new KeyNotFoundException();
        return this.storage.get(key);
    }

    private boolean isPresent(Key key){
        return !this.storage.containsKey(key);
    }

    private boolean isFull(){
        return this.capacity == storage.size();
    }
}
