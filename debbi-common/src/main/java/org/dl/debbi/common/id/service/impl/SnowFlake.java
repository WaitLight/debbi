package org.dl.debbi.common.id.service.impl;

import org.dl.debbi.common.id.service.IdGenerator;

public final class SnowFlake implements IdGenerator {

    // 业务启动时间，使用时减去当前时间，以扩大可用id范围
    // 多节点环境中，业务启动时间可通过专用服务器获取
    private final static long BUSINESS_START_TIMESTAMP = 1547109209631L;

    // 进程（JVM）id 长度
    private final static int PROCESS_BIT_LENGTH = 5;
    // 业务id 长度
    private final static int BUSINESS_BIT_LENGTH = 5;
    // 进程id最大值
    private final static int MAX_PROCESS = ~(-1 << PROCESS_BIT_LENGTH);
    // 业务id最大值
    private final static int MAX_BUSINESS = ~(-1 << BUSINESS_BIT_LENGTH);
    // 序列长度
    // 用户在同一毫秒内生成id的顺序自增
    private final static int SEQUENCE_BIT_LENGTH = 12;
    // 最大序列号
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT_LENGTH);

    // 进程id在结果位图中的位置
    private final static int PROCESS_POSITION = SEQUENCE_BIT_LENGTH;
    // 业务id在结果位图中的位置
    private final static int BUSINESS_POSITION = SEQUENCE_BIT_LENGTH + PROCESS_BIT_LENGTH;
    // 时间在结果位图中的位置
    private final static int TIMESTAMP_POSITION = SEQUENCE_BIT_LENGTH + PROCESS_BIT_LENGTH + BUSINESS_BIT_LENGTH;

    // 上一次的时间戳
    private long lastTimeMillis = -1L;
    private final int process;
    private final int business;
    private long sequence = 0L;

    // 多节点环境中，可通过专用服务器获取
    public SnowFlake(final int process, final int business) {
        if (process > MAX_PROCESS || process < 0) {
            throw new IllegalArgumentException(String.format(
                    "Process Id can't be greater than %d or less than 0",
                    MAX_PROCESS));
        }
        this.process = process;

        if (business > MAX_BUSINESS || business < 0) {
            throw new IllegalArgumentException(String.format(
                    "biz Id can't be greater than %d or less than 0",
                    MAX_BUSINESS));
        }
        this.business = business;
    }

    @Override
    public synchronized long next() {
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

        // 减去开始时间，是为了扩大可用id的范围
        return (currentTimeMillis - BUSINESS_START_TIMESTAMP) << TIMESTAMP_POSITION
                | business << BUSINESS_POSITION
                | process << PROCESS_POSITION
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
    private final static long PROCESS_ID_MASK = MAX_PROCESS << PROCESS_POSITION;
    // 业务id掩码
    private final static long BUSINESS_ID_MASK = MAX_BUSINESS << BUSINESS_POSITION;
    // 时间戳掩码
    private final static long TIMESTAMP_MASK = Long.MAX_VALUE ^ (MAX_SEQUENCE | PROCESS_ID_MASK | BUSINESS_ID_MASK);

    public static long getSequence(final long id) {
        return (id & MAX_SEQUENCE);
    }

    public static long getProcessId(final long id) {
        return (id & PROCESS_ID_MASK) >> PROCESS_POSITION;
    }

    public static long getBusinessId(final long id) {
        return (id & BUSINESS_ID_MASK) >> BUSINESS_POSITION;
    }

    public static long getTimestamp(final long id) {
        return (id & TIMESTAMP_MASK) >> TIMESTAMP_POSITION;
    }

}