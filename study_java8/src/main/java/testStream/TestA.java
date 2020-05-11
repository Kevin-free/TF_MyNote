package testStream;

import testMethodRef.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestA {
    public static void main(String[] args) {
        // 声明一个类A（User）的 List
        List<User> list = new ArrayList<>();
        list.add(new User("kevin", 20));
        list.add(new User("jerry", 21));
        list.add(new User("tom", 22));
        list.add(new User("kevin", 24));

        /**
         * 有一个类A，其有一个属性String b。对于List, 如何通过流操作获取所有属性b的list
         */
        list.stream().filter(s -> s.getName().equals("kevin")).forEach(System.out::println);
        //User{name='kevin', age=20}
        //User{name='kevin', age=24}

        /**
         * 有一个类A，其有一个属性String b。对于List, 如何通过流操作将所有b设置为“test”
         */
//        list.stream().peek(s -> s.setName("jessie")).forEach(System.out::println);
        //User{name='jessie', age=20}
        //User{name='jessie', age=21}
        //User{name='jessie', age=22}
        //User{name='jessie', age=24}

        /**
         * 流操作如何进行过滤
         */
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "hi", "", "jkl");
        // 获取空字符串的数量
        System.out.println(strings.stream().filter(string -> string.isEmpty()).count());
        // 2

    }
}
