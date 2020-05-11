package testMethodRef;

import java.util.function.Supplier;

/**
 * 测试实例方法引用
 */
public class TestInstanceMethodRef {
    public static void main(String[] args) {
        User user = new User("kevin", 24);
        Supplier<String> supplier = () -> user.getName();
        System.out.println("Lambda result: " + supplier.get());

        Supplier<String> supplier1 = user::getName;
        System.out.println("Method Reference result: " + supplier1.get());
    }
}
