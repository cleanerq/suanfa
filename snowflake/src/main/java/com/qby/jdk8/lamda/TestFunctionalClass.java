package com.qby.jdk8.lamda;

import java.math.BigDecimal;
import java.util.function.*;

public class TestFunctionalClass {
    public static void main(String[] args) {
        // 判断真假
        Predicate<Integer> predicate = x -> x > 185;
        Student student = new Student("9龙", 23, 175);
        System.out.println(
                "9龙的身高高于185吗？：" + predicate.test(student.getStature()));

        // 消费消息
        Consumer<String> consumer = System.out::println;
        consumer.accept("命运由我不由天");

        // 将T映射为R（转换功能）
        Function<Student, String> function = Student::getName;
        String name = function.apply(student);
        System.out.println(name);

        // 生产消息
        Supplier<Integer> supplier =
                () -> Integer.valueOf(BigDecimal.TEN.toString());
        System.out.println(supplier.get());

        // 一元操作
        UnaryOperator<Boolean> unaryOperator = uglily -> !uglily;
        Boolean apply2 = unaryOperator.apply(true);
        System.out.println(apply2);

        // 二元操作
        BinaryOperator<Integer> operator = (x, y) -> x * y;
        Integer integer = operator.apply(2, 3);
        System.out.println(integer);

        test(() -> "我是一个演示的函数式接口");
    }

    /**
     * 演示自定义函数式接口使用
     *
     * @param worker
     */
    public static void test(Worker worker) {
        String work = worker.work();
        System.out.println(work);
    }

    public interface Worker {
        String work();
    }

//9龙的身高高于185吗？：false
//命运由我不由天
//9龙
//10
//false
//6
//我是一个演示的函数式接口
}
