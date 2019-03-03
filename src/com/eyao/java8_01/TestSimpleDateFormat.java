package com.eyao.java8_01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: lss
 * @Date: 2019/3/2 12:55
 * @Description:
 */
public class TestSimpleDateFormat {
    public static void main(String[] args){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> call = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return sdf.parse("20161218");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
    }
}
