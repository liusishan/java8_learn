package com.eyao.java8_01;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * @Auther: lss
 * @Date: 2019/3/2 14:58
 * @Description:
 */
public class TestNewDateAPI {

    // ZonedDate、ZonedTime、ZonedDateTime
    @Test
    public void test3() {
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Nicosia"));
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.now();
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zdt);
    }

    @Test
    public void test2() {

        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    // DateTimeFormatter:格式化时间/日期
    @Test
    public void test1() {

        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();
        String strDate = ldt.format(dtf);
        System.out.println(strDate);

        System.out.println("-------------------------------");

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = dtf2.format(ldt);
        System.out.println(strDate2);

//        System.out.println("-----------------");
//        String s = ldt.format(dtf2);
//        System.out.println(s);

        LocalDateTime newDate = ldt.parse(strDate2, dtf2);
        System.out.println(newDate);
    }
}
