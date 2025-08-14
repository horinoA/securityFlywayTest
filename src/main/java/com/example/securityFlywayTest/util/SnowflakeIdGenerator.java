package com.example.securityFlywayTest.util;

import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class SnowflakeIdGenerator {
    
    private MessageService messageService;
    
    private final long nodeId; 
    private final long epoch;
    private final long node_id_bits;
    private final long sequence_bits;

    private final long NODE_ID_SHIFT;
    private final long TIMESTAMP_SHIFT;
    private final long SEQUENCE_MASK;

    private long lastTimestamp = -1L;
    private long sequence = 0L;

    
    public SnowflakeIdGenerator(SnowflakeProperties props, MessageService messageService) {
        this.messageService = messageService;
        this.nodeId = props.getNodeId();
        this.epoch = props.getEpoch();
        this.node_id_bits = props.getnodeIdBits();
        this.sequence_bits = props.getSequenceBits();
        this.NODE_ID_SHIFT = this.sequence_bits;
        this.TIMESTAMP_SHIFT = sequence_bits + node_id_bits;
        this.SEQUENCE_MASK = ~(-1L << sequence_bits);
        if (props.getNodeId() < 0 || props.getNodeId() > ~(-1L << node_id_bits)) {
            throw new IllegalArgumentException(messageService.getinvalidnodeid(~(-1L << node_id_bits)));
        }
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

        return ((currentTimestamp - epoch) << TIMESTAMP_SHIFT)
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
