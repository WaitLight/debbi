package org.dl.debbi.user.account.utils;

public final class IDGenerator {

    // 业务启动时间，使用时减去当前时间，以扩大可用id范围
    // 多节点环境中，业务启动时间可通过专用服务器获取
    private final static long BUSINESS_START_TIMESTAMP = 1547109209631L;

    // 进程（JVM）id 长度
    // 多节点环境中，可通过专用服务器获取
    private final static int PROCESS_ID_LENGTH = 5;
    // 业务id 长度
    private final static int BUSINESS_ID_LENGTH = 5;
    // 进程id最大值
    private final static int MAX_PROCESS_ID = ~(-1 << PROCESS_ID_LENGTH);
    // 业务id最大值
    private final static int MAX_BUSINESS_ID = ~(-1 << BUSINESS_ID_LENGTH);
    // 序列长度
    // 用户在同一毫秒内生成id的顺序自增
    private final static int SEQUENCE_LENGTH = 12;
    // 最大序列号
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_LENGTH);

    // 进程id在结果位图中的位置
    private final static int PROCESS_ID_POSITION = SEQUENCE_LENGTH;
    // 业务id在结果位图中的位置
    private final static int BUSINESS_ID_POSITION = SEQUENCE_LENGTH + PROCESS_ID_LENGTH;
    // 时间在结果位图中的位置
    private final static int TIMESTAMP_POSITION = SEQUENCE_LENGTH + PROCESS_ID_LENGTH + BUSINESS_ID_LENGTH;

    // 上一次的时间戳
    private long lastTimeMillis = -1L;
    private final int processId;
    private final int businessId;
    private long sequence = 0L;

    public IDGenerator(final int processId, final int businessId) {
        if (processId > MAX_PROCESS_ID || processId < 0) {
            throw new IllegalArgumentException(String.format(
                    "Process Id can't be greater than %d or less than 0",
                    MAX_PROCESS_ID));
        }
        this.processId = processId;

        if (businessId > MAX_BUSINESS_ID || businessId < 0) {
            throw new IllegalArgumentException(String.format(
                    "biz Id can't be greater than %d or less than 0",
                    MAX_BUSINESS_ID));
        }
        this.businessId = businessId;
    }

    public synchronized long next() {
        // 扩大可用id的范围
        long currentTimeMillis = System.currentTimeMillis();
        // 时间回滚了
        if (currentTimeMillis < lastTimeMillis) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimeMillis - currentTimeMillis));
        }
        // 毫秒内
        if (lastTimeMillis == currentTimeMillis) {
            sequence = (sequence + 1L) & MAX_SEQUENCE;
            if (sequence == 0L) {
                currentTimeMillis = tilNextMillis(lastTimeMillis);
            }
        } else {
            sequence = 0L;
        }
        lastTimeMillis = currentTimeMillis;

        return (currentTimeMillis - BUSINESS_START_TIMESTAMP) << TIMESTAMP_POSITION
                | businessId << BUSINESS_ID_POSITION
                | processId << PROCESS_ID_POSITION
                | sequence;
    }

    private long tilNextMillis(final long lastTimeMillis) {
        long currentTimeMillis = System.currentTimeMillis();
        while (currentTimeMillis <= lastTimeMillis) {
            currentTimeMillis = System.currentTimeMillis();
        }
        return currentTimeMillis;
    }


    // 进程id掩码
    private final static long PROCESS_ID_MASK = MAX_PROCESS_ID << PROCESS_ID_POSITION;
    // 业务id掩码
    private final static long BUSINESS_ID_MASK = MAX_BUSINESS_ID << BUSINESS_ID_POSITION;
    // 时间戳掩码
    private final static long TIMESTAMP_MASK = Long.MAX_VALUE ^ (MAX_SEQUENCE | PROCESS_ID_MASK | BUSINESS_ID_MASK);

    public static long getSequence(final long id) {
        return (id & MAX_SEQUENCE);
    }

    public static long getProcessId(final long id) {
        return (id & PROCESS_ID_MASK) >> PROCESS_ID_POSITION;
    }

    public static long getBusinessId(final long id) {
        return (id & BUSINESS_ID_MASK) >> BUSINESS_ID_POSITION;
    }

    public static long getTimestamp(final long id) {
        return (id & TIMESTAMP_MASK) >> TIMESTAMP_POSITION;
    }

}