package com.example.securityFlywayTest.validator;

public class SnowflakeUtils {
    private static final long EPOCH = 1749123637176L;
    private static final long NODE_ID_BITS = 10L;
    private static final long SEQUENCE_BITS = 12L;
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + NODE_ID_BITS;

    public SnowflakeUtils() {
        
    }

    public static boolean isValidSnowflakeId(long id) {
        if (id <= 0) return false;

        long timestampPart = (id >> TIMESTAMP_SHIFT);
        long timestamp = timestampPart + EPOCH;

        long now = System.currentTimeMillis();

        return timestamp >= EPOCH && timestamp <= now;
    }
}
