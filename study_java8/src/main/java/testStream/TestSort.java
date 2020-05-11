package testStream;

import testMethodRef.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestSort {
    public static void main(String[] args) {
        User user1 = new User("kevin", 10);
        User user2 = new User("jerry", 26);
        User user3 = new User("tom", 6);

        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);

        System.out.println("-----------origin:");
        list.forEach(o -> System.out.println(o));

        List<User> sorted = list.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
        System.out.println("-----------sorted:");
        sorted.forEach(o -> System.out.println(o));


    }
}
