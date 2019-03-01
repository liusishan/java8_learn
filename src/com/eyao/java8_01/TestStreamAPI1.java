package com.eyao.java8_01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Auther: lss
 * @Date: 2019/3/1 10:33
 * @Description: 一、Stream 的三个操作步骤
 * 1. 创建 Stream
 * <p>
 * 2. 中间操作
 * <p>
 * 3. 终止操作（终端操作）
 */
public class TestStreamAPI1 {

    // 创建 Stream

    /**
     * 获取流的方式：
     * 1. 可以通过Collection 系列集合提供的 stream() 或 parallelStream()
     * 2.
     * 3.
     * 4.
     */
    @Test
    public void test1() {
        // 1. 可以通过Collection 系列集合提供的 stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2.通过Arrays 中的静态方法 stream() 获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(emps);

        // 3. 通过 Stream 类中的静态方法 of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        // 4. 创建无限
        // 迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.limit(10).forEach(System.out::println);

        // 生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);

    }
}
