## Java 8 学习笔记



### 1、方法引用有几种？分别给出相应的例子

方法引用的作用是，使得开发者可以直接引用现存的方法、Java类的构造方法或者实例对象。方法引用和Lambda表达式配合使用，使得 Java 类的构造方法看起来紧凑而简洁，没有很多复杂的模板代码。

方法引用的标准形式是：==类名::方法名==（**注意：只需要写方法名，不需要写括号**）

方法引用有如下四种：

| 类型                                 | 示例                                 |
| ------------------------------------ | ------------------------------------ |
| 引用静态方法                         | ContainingClass::staticMethodName    |
| 引用**某个对象**的实例方法           | containingObject::instanceMethodName |
| 引用**某个类型的任意对象**的实例方法 | ContainingType::instanceMethodName   |
| 引用构造方法                         | ClassName::new                       |

1. 引用静态方法

   静态方法直接通过类名直接调用。

   如： 对一个Integer列表进行排序

   ```java
   public class TestStaticMethodRef {
       public static void main(String[] args) {
           List<Integer> list = Arrays.asList(8,4,5,7,9);
           list.sort(Integer::compare);
           System.out.println(list);
       }
   }
   ```

   因为Integer中已经存在静态的比较方法compare()，因此可以直接用静态方法引用的方式来调用 

   out:

   ```
   [4, 5, 7, 8, 9]
   ```

   

2. 引用**某个对象**的实例方法

   这种语法与静态方法引用的语法类似，只不过这里使用对象引用而不是类名。

   ```java
   public class TestInstanceMethodRef {
       public static void main(String[] args) {
           User user = new User("kevin", 24);
           Supplier<String> supplier = () -> user.getName();
           System.out.println("Lambda result: " + supplier.get());
   
           Supplier<String> supplier1 = user::getName;
           System.out.println("Method Reference result: " + supplier1.get());
       }
   }
   ```

   out：

   ```
   Lambda result: kevin
   Method Reference result: kevin
   ```

   

   - Supplier 是个函数式接口，有一个 get 方法

   > ```java
   > @FunctionalInterface
   > public interface Supplier<T> {
   > 
   >     /**
   >      * Gets a result.
   >      *
   >      * @return a result
   >      */
   >     T get();
   > }
   > ```

   

3. 引用**某个类型的任意对象**的实例方法

   ```java
   public class TestMethodRef {
       public static void main(String[] args) {
           String[] stringArray = { "Barbara", "James", "Mary", "John",
                   "Patricia", "Robert", "Michael", "Linda" };
           Arrays.sort(stringArray, String::compareToIgnoreCase);
       }
   }
   ```

   

4. 引用构造方法

   ```java
   // Lambda
   Supplier<List<User>> userSupplier = () -> new ArrayList<>();
   ```

   ```java
   // Method Reference
   Supplier<List<User>> userSupplier2 = ArrayList<User>::new;
   ```



### 2、流操作如何实现聚合？给2中方法

流：Java 8 API 的一个新成员，使用这个API可以简明搞笑的处理数据及，可以理解陈遍历数据集的内部迭代器。

简明：使用**声明式方式**查询语句来表达，无需临时编写实现代码。

高效：可以透明的进行**并行**处理。

**聚合：它可以把一个Stream的所有元素按照聚合函数聚合成一个结果。**

- 使用`collect`进行聚合：stream().collect(Collectors.toList())
- 使用`reduce`进行聚合：stream().reduce()

```java
public static void main(String[] args) {
    int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce(0, (acc, n) -> acc + n);
    System.out.println(sum); // 45
}
```

reduce() 方法将一个Stream的每个元素依次作用于BinaryOperator，并将结果合并。

reduce() 是聚合方法，聚合方法会立刻对Stream进行计算。



### 3、流操作如何求最大值？2中方法

- Arrays.asList(1,2,3,4).stream().reduce(Integer::max).get()  // 方法引用
- Arrays.asList(1,2,3,4).stream().reduce((a,b) -> a > b ? a : b)  // 聚合



### 4、最小值？2种

- Arrays.asList(1,2,3,4).stream().reduce(Integer::min).get()  // 方法引用
- Arrays.asList(1,2,3,4).stream().reduce((a,b) -> a < b ? a : b)  // 聚合



### 5、流转换成map

List 转 Map，用到的是 `Collectors.toMap`

```java
// 声明一个List
List<User> list = new ArrayList<>();
list.add(new User("kevin", 20));
list.add(new User("jerry", 21));
list.add(new User("tom", 22));
System.out.println(list);
// 将 List 转 Map
Map<String, Integer> map = list.stream().collect(Collectors.toMap(User::getName, User::getAge));
System.out.println(map);
```

out：

```java
{kevin=20, tom=22, jerry=21}
```



### 6、list根据字段分组

`Collectors.groupingBy`

```java
// 根据字符长度分组
Map<Integer, List<String>> stringMap = Arrays.asList("tt", "14", "hello").stream().collect(Collectors.groupingBy(String::length));
System.out.println("stringMap: " + stringMap);
```

out：

```
stringMap: {2=[tt, 14], 5=[hello]}
```



### 7、说说你对Optional的理解

```java
/**
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
```



### 8、流操作计算集合中的元素数量

```java
//其中：前半部分是把集合中的元素变为1，然后在用reduce聚合把他们从0开始相加，从而计算数量
int count = Arrays.asList(1, 2, 3, 4).stream().map(s -> 1).reduce(0, (a, b) -> a + b);
System.out.println("count:" + count); // 4
```



### 9、流操作获取集合第一个对象

```java
System.out.println(Arrays.asList(1, 2, 3).stream().findFirst().get()); // 1
```



### 10、**流操作根据对象的字段排序。写一个类，然后创建一个该类的list，然后对其进行排序。**

```java
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
```



### 11、什么是函数式接口？列举Java8 常用的

函数式接口定义且只定义了一个抽象方法。

- Predicate：定义了 boolean test(T t) 抽象方法，需要表示一个涉及 T 的布尔表达式时可以使用。
- Consumer：定义了 void accept(T t)抽象方法，需要访问某对象并对其进行某些操作时可以使用。
- Function：定义了 R apply(T t)抽象方法，它接受一个 泛型T的对象，并返回一个泛型 R 的对象。如果需要将一个对象转为其他对象是可以使用。
- Supplier：定义了 T get() 抽象方法， 不接收参数返回 Lambda 表达式的值。
- BiFunction：定义了 R apply(T t, U u)抽象方法，接收t和u参数，返回R对象，如果需要两个对象中的某些值来组装成另一个对象，可以使用。
- BiPredicate：定义了 boolean test(T t, U u) 抽象方法，接收 t 和 u参数，返回比较的接口，需要两个对象做比较可以使用。
- BiConsumer：定义了 void accept(T t, U u)抽象方法。



### 12、写一个2个参数没有返回值的函数接口

```
/**
 * 2个参数没有返回值的函数接口
 * @param <A>
 * @param <B>
 */
@FunctionalInterface
public interface TwoArgs<A,B> {
    void run(A a,B b);
}
```



### 13、**写一个2个参数，1个返回值的函数接口**

```
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
```



### 14、**有一个类A，对于List>如何通过流操作转化成List?**

```java
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
```



### 15、**有一个类A，其有一个属性String b。对于List, 如何通过流操作获取所有属性b的list**

```java
list.stream().filter(s -> s.getB().equals("b")).forEach(System.out::println);
```



### 16、**有一个类A，其有一个属性String b。对于List, 如何通过流操作将所有b设置为“test”**

```java
list.stream().peek(s -> s.setB("test")).forEach(System.out::println);
```



### 17、**流操作如何进行过滤**

filter 方法用于通过设置的条件过滤出满足条件的元素。以下代码片段使用 filter 方法过滤出空字符串：

```java
List<String> strings = Arrays.asList("abc", "", "bc", "efg", "hi", "", "jkl");
// 获取空字符串的数量
System.out.println(strings.stream().filter(string -> string.isEmpty()).count()); // 2
```



















