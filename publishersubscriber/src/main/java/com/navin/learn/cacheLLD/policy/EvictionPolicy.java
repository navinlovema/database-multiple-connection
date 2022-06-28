package com.navin.learn.cacheLLD.policy;

import com.sun.media.sound.InvalidDataException;

public interface EvictionPolicy<Key> {
    void keyAccessed(Key key) throws InvalidDataException;
    Key evict();
}
