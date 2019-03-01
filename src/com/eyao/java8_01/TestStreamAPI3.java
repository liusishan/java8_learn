package com.eyao.java8_01;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: lss
 * @Date: 2019/3/1 13:44
 * @Description: 终止操作
 */
public class TestStreamAPI3 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.FREE),
            new Employee("李四", 38, 7777.99, Employee.Status.BUSY),
            new Employee("王五", 39, 8888.88, Employee.Status.VOCATION),
            new Employee("赵六", 58, 5555.99, Employee.Status.FREE),
            new Employee("田七", 8, 3333.99, Employee.Status.BUSY)
    );
    /*
     查找与匹配
        allMatch——检查是否匹配所有元素
        anyMatch——检查是否至少匹配一个元素
        noneMatch——检查是否没有匹配的元素
        findFirst——返回第一个元素
        findAny——返回当前流中的任意元素
        count——返回流中元素的总个数
        max——返回流中最大值
        min——返回流中最小值
     */

    @Test
    public void test2() {
        long count = employees.stream()
                .count();
        System.out.println(count);

        Optional<Employee> optional = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));//Comparator.comparingDouble(Employee::getSalary)
        System.out.println(optional.get());

        Optional<Double> op2 = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(op2.get());
    }

    @Test
    public void test1() {
        boolean b = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);

        boolean b2 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        boolean b3 = employees.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);

        Optional<Employee> op = employees.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();

        System.out.println(op.get());

        Optional<Employee> op2 = employees.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(op2);

        Optional<Employee> op3 = employees.parallelStream()// 并行流
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(op3);
    }
}
