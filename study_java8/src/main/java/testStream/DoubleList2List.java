package testStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DoubleList2List {
    public static void main(String[] args) {
        /*
        String[] words  = new String[]{"Hello","World"};
        List<String[]> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()  // 去重
                .collect(Collectors.toList());
        a.forEach(System.out::print);
        // [Ljava.lang.String;@378bf509[Ljava.lang.String;@5fd0d5ae
        // 返回一个包含两个String[]的list
        */

        /*
        String[] words  = new String[]{"Hello","World"};
        List<String> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()  // 去重
                .collect(Collectors.toList());
        a.forEach(System.out::print);
        // HeloWrd
        */

        List<Integer> list1 = Arrays.asList(1);
        List<Integer> list2 = Arrays.asList(1, 2);
        List<Integer> list3 = Arrays.asList(3, 4);
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        System.out.println("转化前：" + lists);
        //转化前：[[1], [1, 2], [3, 4]]

        List<Object> objects = lists.stream()
                .map(i -> i.toArray())
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        System.out.println("转化后：" + objects);
        //转化后：[1, 1, 2, 3, 4]

    }
}
