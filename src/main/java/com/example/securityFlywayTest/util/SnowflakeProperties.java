package com.example.securityFlywayTest.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "snowflake")
public class SnowflakeProperties {
    private Long nodeId;
    private Long epoch;
    private Long nodeIdBits;
    private Long sequenceBits;

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch){
        this.epoch = epoch;
    }

    public long getnodeIdBits(){
        return nodeIdBits;
    }

    public void setNodeIdBits(long nodeIdBits){
        this.nodeIdBits = nodeIdBits;
    }

    public long getSequenceBits(){
        return sequenceBits;
    }
    
    public void setSequenceBits(long sequencebits){
        this.sequenceBits = sequencebits;
    }
    
}
