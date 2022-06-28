package com.navin.learn.cacheLLD.factory;

import com.navin.learn.cacheLLD.Cache;
import com.navin.learn.cacheLLD.policy.impl.FIFOCacheEvictionPolicy;
import com.navin.learn.cacheLLD.policy.impl.LRUCacheEvictionPolicy;
import com.navin.learn.cacheLLD.storage.impl.HashMapStorage;

public class CacheFactory <Key, Value> {
    public Cache<Key, Value> defaultCacheWithLRUEviction(final int capacity){
        return new Cache<>(new HashMapStorage<>(capacity), new LRUCacheEvictionPolicy<>());
    }
    public Cache<Key, Value> defaultCacheWithFIFOEviction(final int capacity){
        return new Cache<>(new HashMapStorage<>(capacity), new FIFOCacheEvictionPolicy<>());
    }
}
