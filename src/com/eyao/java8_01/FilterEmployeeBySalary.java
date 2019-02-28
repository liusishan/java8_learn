package com.eyao.java8_01;

/**
 * @Auther: lss
 * @Date: 2019/2/28 10:20
 * @Description:
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 5000;
    }
}
