package com.navin.learn.twitterSnowFall;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UniqueIdGenerator {
    private static final int UNUSED_BITS = 1;
    private static final int EPOCH_NUMBER = 41;
    private static final int NODE_ID_BITS = 10;
    private static final int THREAD_COUNT = 5;
    private static final int SEQUENCE_BITS = 12;

    private static final int maxNodeId = (int) (Math.pow(2, NODE_ID_BITS)-1);
    private static final int maxSequence = (int) (Math.pow(2, SEQUENCE_BITS) - 1);

    private static final long CUSTOM_EPOCH = 1420070400000L;
    private final int nodeId;
    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;
//    private volatile long threadId = 0L;

    //create UniqueId based on the Node id;
    public UniqueIdGenerator(int nodeId){
        if(nodeId<0 || nodeId > maxNodeId)
            throw new IllegalArgumentException("Node Id must be smaller than" + maxNodeId + " and must be greater than " + 0);
        this.nodeId = nodeId;
    }

    public UniqueIdGenerator(){
        this.nodeId = createNodeId();
    }

    public synchronized void nextId() {
        long currentTimestamp = timestamp();
        if(currentTimestamp<lastTimestamp)
            throw new IllegalArgumentException("Invalid System Clock!");

        if(currentTimestamp == lastTimestamp){
            sequence = (sequence+1) & maxSequence; //checking both are equal
            if(sequence == 0) // if exhausted then finding the next currentTimestamp...
                currentTimestamp = waitNextMillis(currentTimestamp);
        }else{
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;
        long id = currentTimestamp << (NODE_ID_BITS +SEQUENCE_BITS);
        id |= ((long) nodeId <<SEQUENCE_BITS);
        id |= sequence;
        System.out.print(id+",");
    }

    private long timestamp(){
        return Instant.now().toEpochMilli()-CUSTOM_EPOCH;
    }

    private long waitNextMillis(long currentTimestamp){
        while(currentTimestamp == lastTimestamp){
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }

    private int createNodeId() {
        int result;
        try {
            StringBuilder nodeId = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaceEnumeration.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();
                byte[] macAddress = networkInterface.getHardwareAddress();
                if (macAddress != null) {
                    for (byte address : macAddress) {
                        nodeId.append(String.format("%20X", address));
                    }
                }
            }
            result = nodeId.toString().hashCode();
        }catch (Exception e){
            result = new SecureRandom().nextInt();
        }
        result = result & maxNodeId;
        return result;
    }

}

class Launcher {

    public static void main(String[] args) {
        UniqueIdGenerator uniqueIdGenerator = new UniqueIdGenerator();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        long currenttime = Instant.now().toEpochMilli();
        for (int i = 0; i < 1000000; i++) {
            executorService.execute(uniqueIdGenerator::nextId);
        }
        executorService.shutdown();
        System.out.println((Instant.now().toEpochMilli() - currenttime) / 1000 + "sec");
    }
}
