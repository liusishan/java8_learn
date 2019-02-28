package com.eyao.java8_01;

/**
 * @Auther: lss
 * @Date: 2019/2/28 10:19
 * @Description:
 */
@FunctionalInterface
public interface MyPredicate<T> {

    public boolean test(T t);
}
