package com.eyao.java8_01_exer;

import com.eyao.java8_01.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: lss
 * @Date: 2019/2/28 12:07
 * @Description:
 */
public class TestLambda {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 7777.99),
            new Employee("王五", 38, 8888.88),
            new Employee("赵六", 58, 5555.99),
            new Employee("田七", 8, 3333.99)
    );

    @Test
    public void test1() {
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge())
                return e1.getName().compareTo(e2.getName());
            else
                return Integer.compare(e1.getAge(), e2.getAge());
        });

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void test2() {
        String s = strHandler("\t\t\t 你很厉害哦", (str) -> str.trim());
        System.out.println(s);

        String s1 = strHandler("abcdE", (str) -> str.toUpperCase());
        System.out.println(s1);

        String s2 = strHandler("你很厉害哦", (str) -> str.substring(2, 4));
        System.out.println(s2);
    }

    // 需求：用于处理字符串
    public String strHandler(String str, MyFun mf) {
        return mf.getValue(str);
    }

    @Test
    public void test3() {
        op(100L, 200L, (x, y) -> x + y);

        op(100L, 200L, (x, y) -> x * y);
    }

    // 需求：对于两个Long 型数据类型进行处理
    public void op(Long l1, Long l2, MyFun2<Long, Long> mf) {
        System.out.println(mf.getValue(l1, l2));
    }

}
