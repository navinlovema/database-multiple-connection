package com.navin.learn.publisherSubscriber;

import java.util.List;


public class Subscriber implements Runnable{
    List<String> data;

    Subscriber(List<String> data){
        this.data = data;
    }

    @Override
    public void run() {

        if(data.size()>0)
        while(true){
            if(data.get(0).equalsIgnoreCase("EOF")){
                System.out.println(Thread.currentThread().getName() + "Exiting the method");
                break;
            }else{

                    System.out.println(Thread.currentThread().getName() + " consuming this" + data.remove(0));

            }
        }
    }
}
