package com.navin.learn.cacheLLD.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KeyNotFoundException extends Exception {
    public KeyNotFoundException(){
        log.error("You tried to fetch wrong data...");
    }
    KeyNotFoundException(String message){
      log.error(message);
    }
}
