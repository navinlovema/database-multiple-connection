package com.navin.learn.cacheLLD;

import com.navin.learn.cacheLLD.exception.KeyNotFoundException;
import com.navin.learn.cacheLLD.exception.StorageFullException;
import com.navin.learn.cacheLLD.policy.EvictionPolicy;
import com.navin.learn.cacheLLD.storage.Storage;
import com.sun.media.sound.InvalidDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.Objects;

@Service
@Slf4j
public class Cache<Key, Value> {
    private final Storage<Key, Value> storage;
    private final EvictionPolicy<Key> evictionPolicy;

    public Cache(Storage<Key, Value> storage, EvictionPolicy<Key> evictionPolicy){
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public Value get(final Key key){
        try{
            Value value = this.storage.get(key);
            this.evictionPolicy.keyAccessed(key);
            return value;
        } catch (KeyNotFoundException | InvalidDataException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void put(final Key key, final Value value) throws KeyNotFoundException {
        try{
            this.storage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException e) {
            log.info("Storage is full, trying to evict");
            Key keyToBeRemoved = this.evictionPolicy.evict();
            if(Objects.isNull(key)){
                throw new InvalidStateException("Invalid state, No storage left and no key to evict");
            }
            this.storage.remove(keyToBeRemoved);
            log.info("Evicting key {}" , keyToBeRemoved);
            put(key, value);
        }catch (InvalidDataException e){
            e.printStackTrace();
        }
    }
}
