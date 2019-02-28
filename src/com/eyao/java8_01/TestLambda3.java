package com.eyao.java8_01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Auther: lss
 * @Date: 2019/2/28 15:02
 * @Description:Java8 内置的四大核心函数式接口：
 * Consumer<T> : 消费型接口
 * void accept(T t);
 * <p>
 * Supplier<T> : 供给型接口
 * T get();
 * <p>
 * Function<T,R> : 函数型接口
 * R apply(T t);
 * <p>
 * Predicate<T> : 断言型接口
 * boolean test(T t);
 */
public class TestLambda3 {

    // Predicate<T> : 断言型接口
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "World", "Lambda", "www", "ok");
        List<String> stringList = filterStr(list, (str) -> str.length() > 3);

        for (String s : stringList) {
            System.out.println(s);
        }
    }

    // 需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> strList = new ArrayList<>();

        for (String str : list) {
            if (predicate.test(str))
                strList.add(str);
        }
        return strList;
    }

    // Function<T,R> : 函数型接口
    @Test
    public void test3() {
        String s = strHandler("\t\t\t 你很厉害哦", (str) -> str.trim());
        System.out.println(s);

        String s1 = strHandler("你很厉害哦", (str) -> str.substring(2, 4));
        System.out.println(s1);
    }

    // 需求：用于处理字符串
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    // Supplier<T> : 供给型接口
    @Test
    public void test2() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer integer : numList) {
            System.out.println(integer);
        }
    }

    // 需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }

    // Consumer<T> 消费型接口：
    @Test
    public void test1() {
        happy(10000, (m) -> System.out.println("大保健，消费" + m + "元"));
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }
}
