package com.eyao.java8_01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Auther: lss
 * @Date: 2019/3/1 11:05
 * @Description: 一、Stream 的三个操作步骤
 * 1. 创建 Stream
 * <p>
 * 2. 中间操作
 * <p>
 * 3. 终止操作（终端操作）
 */
public class TestStreamAPI2 {

    List<Employee> employees = Arrays.asList(
            new Employee(10, "张三", 18, 9999.99),
            new Employee(9, "李四", 38, 7777.99),
            new Employee(8, "王五", 39, 8888.88),
            new Employee(7, "赵六", 58, 5555.99),
            new Employee(6, "田七", 8, 3333.99),
            new Employee(6, "田七", 8, 3333.99),
            new Employee(6, "田七", 8, 3333.99),
            new Employee(6, "田七", 8, 3333.99)
    );

    // 中间操作

    /*
     映射
        map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */

    @Test
    public void test5() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("----------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("----------------------");

//        Stream<Stream<Character>> stream = list.stream()
//                .map(TestStreamAPI2::filterCharacter);// {{a,a,a},{b,b,b}}
//
//        stream.forEach((sm) -> sm.forEach(System.out::println));

        System.out.println("----------------------");

        Stream<Character> sm = list.stream()
                .flatMap(TestStreamAPI2::filterCharacter); // {a,a,a,b,b,b}

        sm.forEach(System.out::println);

    }

    @Test
    public  void test6(){

        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        List list2 = new ArrayList();

        list2.add(11);
        list2.add(22);
        list2.addAll(list);

        System.out.println(list2);

    }

    public static Stream<Character> filterCharacter(String str) { // add(Object obj)  addAll(Collection coll)
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /*
        筛选与切片
        filter——接收 Lambda ， 从流中排除某些元素。
        limit——截断流，使其元素不超过给定数量。
        skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
        distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */

    @Test
    public void test4() {
        employees.stream()
                .filter((e) -> e.getSalary() < 5000)
                .skip(2) // 跳过前几个
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test3() {
        employees.stream()
                .filter((e) -> {
                    System.out.println("短路！"); // &&  ||
                    return e.getSalary() > 5000;
                })
                .limit(2)
                .forEach(System.out::println);
    }


    // 内部迭代：迭代操作右 Stream API 完成
    @Test
    public void test1() {
        // 中间操作：不会执行任何操作
        Stream s = employees.stream()
                .filter((e) -> {
                    System.out.println("Stream API 的中间操作");
                    return e.getAge() > 35;
                });

        // 终止操作：一次性执行全部内容，即“惰性求值”
        s.forEach(System.out::println);
    }

    // 外部迭代
    @Test
    public void test2() {
        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }


}
