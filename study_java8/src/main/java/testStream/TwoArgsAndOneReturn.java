package testStream;


/**
 * 2个参数，1个返回值的函数接口
 * @param <A>
 * @param <B>
 * @param <C>
 */
@FunctionalInterface
public interface TwoArgsAndOneReturn<A,B,C> {
    C run(A a,B b);
}
