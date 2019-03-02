package com.eyao.java8_01;

import java.util.concurrent.RecursiveTask;

/**
 * @Auther: lss
 * @Date: 2019/3/2 09:13
 * @Description:
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private static final long serialVersionUID = 134872112144567L;

    private long start;
    private long end;

    private static final long THERSHOLD = 10000L;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if (length <= THERSHOLD) {
            long sum = 0;

            for (long i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        } else {
            long mid = (start + end) / 2;

            ForkJoinCalculate left = new ForkJoinCalculate(start, mid);
            left.fork();// 拆分子任务，同时压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(mid + 1, end);
            right.fork();

            return left.join() + right.join();
        }
    }
}
