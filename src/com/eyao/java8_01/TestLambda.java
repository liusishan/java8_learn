package com.eyao.java8_01;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.junit.Test;

import javax.xml.transform.Source;
import java.util.*;

/**
 * @Auther: lss
 * @Date: 2019/2/28 09:54
 * @Description:
 */
public class TestLambda {

    // 原来的匿名内部类
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    // Lambda 表达式
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 7777.99),
            new Employee("王五", 39, 8888.88),
            new Employee("赵六", 58, 5555.99),
            new Employee("田七", 8, 3333.99)
    );

    // 需求:获取当前公司中员工年龄大于35的员工
    @Test
    public void test3() {
        List<Employee> list = filterEmployees(this.employees);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployees(List<Employee> list) {

        List<Employee> emps = new ArrayList<>();

        for (Employee emp : list) {
            if (emp.getAge() >= 35)
                emps.add(emp);
        }
        return emps;
    }

    // 需求：获取当前工资中员工大于5000的员工信息
    public List<Employee> filterEmployees2(List<Employee> list) {

        List<Employee> emps = new ArrayList<>();

        for (Employee emp : list) {
            if (emp.getSalary() >= 5000)
                emps.add(emp);
        }
        return emps;
    }

    // 优化方式一：策略设计模式
    @Test
    public void test4() {
        List<Employee> list = filterEmployee(this.employees, new FilterEmployeeByAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    @Test
    public void test5() {
        List<Employee> list = filterEmployee(this.employees, new FilterEmployeeBySalary());
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }


    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp) {

        List<Employee> emps = new ArrayList<>();

        for (Employee emp : list) {
            if (mp.test(emp))
                emps.add(emp);
        }
        return emps;
    }


}
