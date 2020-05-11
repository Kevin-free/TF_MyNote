package testStream;

/**
 * 2个参数没有返回值的函数接口
 * @param <A>
 * @param <B>
 */
@FunctionalInterface
public interface TwoArgs<A,B> {
    void run(A a,B b);
}

