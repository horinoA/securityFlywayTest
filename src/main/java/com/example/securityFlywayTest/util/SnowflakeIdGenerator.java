package com.example.securityFlywayTest.util;

import java.util.Locale;

import org.springframework.stereotype.Component;

import com.example.securityFlywayTest.service.MessageService;

@Component
public class SnowflakeIdGenerator {
    private final long nodeId; 
    private final static long EPOCH = 1749123637176L;
    private MessageService messageService;
    private final static long NODE_ID_BITS = 10L;
    private final static long MAX_NODE_ID = ~(-1L << NODE_ID_BITS);
    private final static long SEQUENCE_BITS = 12L;

    private final static long NODE_ID_SHIFT = SEQUENCE_BITS;
    private final static long TIMESTAMP_SHIFT = SEQUENCE_BITS + NODE_ID_BITS;
    private final static long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    private long lastTimestamp = -1L;
    private long sequence = 0L;

    
    public SnowflakeIdGenerator(SnowflakeProperties props, MessageService messageService) {
        this.messageService = messageService;
        if (props.getNodeId() < 0 || props.getNodeId() > MAX_NODE_ID) {
            throw new IllegalArgumentException(messageService.getinvalidnodeid(MAX_NODE_ID));
        }
        this.nodeId = props.getNodeId();
    }

    public synchronized long nextId() {
        long currentTimestamp = timeGen();

        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException(messageService.getMessageSource().getMessage("error.retrogradelockid",null, Locale.JAPAN));
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;

        return ((currentTimestamp - EPOCH) << TIMESTAMP_SHIFT)
                | (nodeId << NODE_ID_SHIFT)
                | sequence;
    }

    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp <= lastTimestamp) {
            currentTimestamp = timeGen();
        }
        return currentTimestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
