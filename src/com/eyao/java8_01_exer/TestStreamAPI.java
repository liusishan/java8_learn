package com.eyao.java8_01_exer;

import com.eyao.java8_01.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: lss
 * @Date: 2019/3/1 17:45
 * @Description:
 */
public class TestStreamAPI {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.FREE),
            new Employee("李四", 38, 7777.99, Employee.Status.BUSY),
            new Employee("王五", 39, 8888.88, Employee.Status.VOCATION),
            new Employee("赵六", 58, 5555.99, Employee.Status.FREE),
            new Employee("田七", 8, 3333.99, Employee.Status.BUSY),
            new Employee("田七", 8, 3333.99, Employee.Status.BUSY)
    );

    /**
     * 1.给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     * 给定[1,2,3,4,5] 输出[2,4,9,16,25]
     */
    @Test
    public void test1() {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};

        Arrays.stream(nums)
                .map((x) -> x * x)
                .forEach(System.out::println);
    }

    /**
     * 2. 怎么样用map 和reduce 方法数一数流中有多少个 Employee 呢？
     *
     */

}
