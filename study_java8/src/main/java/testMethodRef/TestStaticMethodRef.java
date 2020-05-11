package testMethodRef;

import java.util.Arrays;
import java.util.List;

/**
 * 测试静态方法引用
 * Class::staticMethod == (args) -> Class.staticMethod(args)
 * String::valueOf     ==    (s) -> String.valueOf(s)
 */
public class TestStaticMethodRef {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(8,4,5,7,9);
        list.sort(Integer::compare);
        System.out.println(list);
    }
}
