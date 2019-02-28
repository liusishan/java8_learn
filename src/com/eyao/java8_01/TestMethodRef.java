package com.eyao.java8_01;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @Auther: lss
 * @Date: 2019/2/28 16:20
 * @Description: 方法引用：若 Lambda 体中的内容有方法已经实现了，我们可以使用“方法引用”
 * （可以理解为方法引用是Lambda 表达式的另外一种表现形式）
 * <p>
 * 主要有三种语法格式：
 *
 * <p>
 * 对象::实例方法名
 * <p>
 * 类::静态方法名
 * 前提：Lambda 体中的这个方法的参数列表和返回值类型要与我函数式接口中，抽象列表和返回值都相同
 * <p>
 * 类::实例方法名
 *
 * 注意:
 * ① Lambda 体中调用的方法的参数列表与返回值类型，要与该丑行方法了吗
 */
public class TestMethodRef {

    // 类::静态方法名
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com2 = Integer::compare;
    }

    // 对象::实例方法名
    @Test
    public void test1() {
        PrintStream ps1 = System.out;

        Consumer<String> con = (x) -> ps1.println(x);

        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;

        Consumer<String> con2 = System.out::println;
        con2.accept("abcdef");
    }

    @Test
    public void test2() {

    }

}
