package com.navin.learn.cacheLLD.storage;

import com.navin.learn.cacheLLD.exception.KeyNotFoundException;
import com.navin.learn.cacheLLD.exception.StorageFullException;

public interface Storage<Key, Value>{
    void add(Key key, Value value) throws StorageFullException;
    void remove(Key key) throws KeyNotFoundException;
    Value get(Key key) throws KeyNotFoundException;

}
