package com.navin.learn.cacheLLD.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StorageFullException extends Exception {
    StorageFullException(){
        log.error("You tried to fetch wrong data...");
    }
    public StorageFullException(String message){
        log.error(message);
    }
}
