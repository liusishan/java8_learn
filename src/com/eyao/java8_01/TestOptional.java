package com.eyao.java8_01;

import org.junit.Test;

import java.util.Optional;

/**
 * @Auther: lss
 * @Date: 2019/3/2 10:12
 * @Description:
 */
public class TestOptional {

    /*
     * 一、Optional 容器类：用于尽量避免空指针异常
     * 	Optional.of(T t) : 创建一个 Optional 实例
     * 	Optional.empty() : 创建一个空的 Optional 实例
     * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
     * 	isPresent() : 判断是否包含值
     * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
     * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
     * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
     * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
     */

    @Test
    public void test4() {
        Optional<Employee> op = Optional.ofNullable(new Employee("王八", 18, 888.8, Employee.Status.BUSY));
//        Optional<String> s = op.map((e) -> e.getName());
//        System.out.println(s.get());

        Optional<String> s1 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(s1.get());
    }

    @Test
    public void test3() {
        Optional<Employee> op = Optional.ofNullable(null);

//        if (op.isPresent()) {
//            System.out.println(op.get());
//        }
//
//        Employee employee = op.orElse(new Employee("王八", 18, 888.8, Employee.Status.BUSY));
//        System.out.println(employee);

        Employee emp = op.orElseGet(() -> new Employee());
        System.out.println(emp);
    }

    @Test
    public void test2() {

        Optional<Object> optional = Optional.empty();
        System.out.println(optional.get());
    }

    @Test
    public void test1() {
        Optional<Employee> op = Optional.of(null);

        Employee emp = op.get();
        System.out.println(emp);
    }
}
