package com.eyao.java8_01;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Auther: lss
 * @Date: 2019/2/28 16:20
 * @Description: 一、方法引用：若 Lambda 体中的内容有方法已经实现了，我们可以使用“方法引用”
 * （可以理解为方法引用是Lambda 表达式的另外一种表现形式）
 * <p>
 * 主要有三种语法格式：
 * <p>
 * <p>
 * 对象::实例方法名
 * <p>
 * 类::静态方法名
 * 前提：Lambda 体中的这个方法的参数列表和返回值类型要与我函数式接口中，抽象列表和返回值都相同
 * <p>
 * 类::实例方法名
 * <p>
 * 注意:
 * ① Lambda 体中调用的方法的参数列表与返回值类型，要与函数式接口中的抽象方法的参数列表和返回值保持一致！
 * ② 若 Lambda 参数列表中的第一个参数，是实例方法的调用者，参数列表第二个参数是 实例方法的参数时，可以使用ClassName::method
 * <p>
 * 二、构造器引用：
 * 格式：
 * <p>
 * ClassName::new
 * <p>
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！
 * <p>
 * 三、数组引用：
 * Type::new
 */
public class TestMethodRef {

    // 数组引用：
    @Test
    public void test7() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] apply = fun.apply(10);
        System.out.println(apply.length);

        Function<Integer, Integer[]> fun2 = Integer[]::new;
        Integer[] apply1 = fun2.apply(20);
        System.out.println(apply1.length);
    }

    //
    @Test
    public void test6() {
        Function<Integer, Employee> fun = (x) -> new Employee(x);

        Function<Integer, Employee> fun2 = Employee::new;
        Employee employee = fun2.apply(101);
        System.out.println(employee);
    }

    //  构造器引用
    @Test
    public void test5() {
        Supplier<Employee> sup = () -> new Employee();
        // 原来 Lambda 表达式
        Employee employee = sup.get();

        // 构造器引用方式 默认使用无参构造器（用函数式接口的参数列表匹配构造器参数）
        Supplier<Employee> sup2 = Employee::new;
    }

    // 类::实例方法名
    // 规则： 当参数列表中的第一个参数，是是实例方法的调用者，参数列表第二个参数是 实例方法的参数实例这种情况下就可以使用
    // 类::实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);

        BiPredicate<String, String> bp2 = String::equals;

    }


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
        Employee emp = new Employee(10, "liu", 18, 555.00);
        Supplier<String> sup = () -> emp.getName();
        String str = sup.get();
        System.out.println(str);

        Supplier<Integer> sup2 = emp::getAge;
        Integer num = sup2.get();
        System.out.println(num);
    }

}
