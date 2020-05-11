package testStream;

import testMethodRef.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream流
 */
public class Main {
    public static void main(String[] args) {
        /**
         * 流操作如何实现聚合
         */
        /*
        reduce的第一个参数表示初始值为0；
        reduce的第二个参数为需要进行的归约操作
         */
        int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce(0, (acc, n) -> acc + n);
        System.out.println("sum: " + sum); // 45
        System.out.println("============================");

        /**
         流操作获取最大值
         */
        int max = Arrays.asList(1, 2, 3).stream().reduce(Integer::max).get();
        System.out.println("method reference max: " + max); // 3
        /*
        Optional是Java8新加入的一个容器，这个容器只存1个或0个元素，它用于防止出现NullpointException
         */
        Optional<Integer> optionalmax = Arrays.asList(1, 2, 3).stream().reduce((a, b) -> a > b ? a : b);
        System.out.println("stream max: " + optionalmax.get());
        System.out.println("============================");

        /**
         流操作获取最小值
         */
        int min = Arrays.asList(1, 2, 3).stream().reduce(Integer::min).get();
        System.out.println("method reference min: " + min); // 1
        /*
        Optional是Java8新加入的一个容器，这个容器只存1个或0个元素，它用于防止出现NullPointException
         */
        Optional<Integer> optionalmin = Arrays.asList(1, 2, 3).stream().reduce((a, b) -> a < b ? a : b);
        System.out.println("stream min: " + optionalmin.get());
        System.out.println("============================");


        /**
         * 5、流转换成map
         */
        // 声明一个List
        List<User> list = new ArrayList<>();
        list.add(new User("kevin", 20));
        list.add(new User("jerry", 21));
        list.add(new User("tom", 22));
        System.out.println(list);
        // 将 List 转 Map
        Map<String, Integer> map = list.stream().collect(Collectors.toMap(User::getName, User::getAge));
        System.out.println(map);
        //{kevin=20, tom=22, jerry=21}
        System.out.println("============================");


        /**
         * list 根据字段分组
         */
        // 根据字符长度分组
        Map<Integer, List<String>> stringMap = Arrays.asList("tt", "14", "hello").stream().collect(Collectors.groupingBy(String::length));
        System.out.println("stringMap: " + stringMap);
        //stringMap: {2=[tt, 14], 5=[hello]}
        System.out.println("============================");

        /**
         * 说说你对Optional的理解
         *
         * Optional是Java8中新增的类，主要用来提醒开发人员对异常的处理。当我们调用某个方法的返回值为Optional时，要注意到此时的返回值可能为null，所以要显示的对null进行判空。
         * 旨在减少代码中的 NullPointerExceptions，虽然还不能完全消除这些异常。
         * 它也是精心设计，自然融入 Java 8 函数式支持的功能。
         * 总的来说，这个简单而强大的类有助于创建简单、可读性更强、比对应程序错误更少的程序。
         *
         * 它提供如下方法：
         *
         * isPresent()
         * 判断容器中是否有值。
         *
         * ifPresent(Consume lambda)
         * 容器若不为空则执行括号中的Lambda表达式。
         *
         * T get()
         * 获取容器中的元素，若容器为空则抛出NoSuchElement异常。
         *
         * T orElse(T other)
         * 获取容器中的元素，若容器为空则返回括号中的默认值。
         */

        /**
         * 流操作计算集合中的元素数量
         */
        //其中：前半部分是把集合中的元素变为1，然后在用reduce聚合把他们从0开始相加，从而计算数量
        int count = Arrays.asList(1, 2, 3, 4).stream().map(s -> 1).reduce(0, (a, b) -> a + b);
        System.out.println("count:" + count); // 4


        /**
         * 流操作取集合第一个对象
         */
        System.out.println(Arrays.asList(1, 2, 3).stream().findFirst().get()); // 1



    }
}
