### @ConditionalOnProperty

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(OnPropertyCondition.class)
public @interface ConditionalOnProperty {

    String[] value() default {}; //数组，获取对应property名称的值，与name不可同时使用  
  
    String prefix() default "";//property名称的前缀，可有可无  
  
    String[] name() default {};//数组，property完整名称或部分名称（可与prefix组合使用，组成完整的property名称），与value不可同时使用  
  
    String havingValue() default "";//可与name组合使用，比较获取到的属性值与havingValue给定的值是否相同，相同才加载配置  
  
    boolean matchIfMissing() default false;//缺少该property时是否可以加载。如果为true，没有该property也会正常加载；反之报错  
}
```

#### 使用方法

通过其两个属性**name**以及**havingValue**来实现的，其中**name**用来从**application.properties**中读取某个属性值。
 **如果该值为空，则返回false**;
 **如果值不为空，则将该值与havingValue指定的值进行比较，如果一样则返回true;否则返回false。**
 **如果返回值为false，则该configuration不生效；为true则生效。**



#### 项目中

```java
@ConditionalOnProperty(name = "jobs.manager.enabled", havingValue = "true")
@Component
```

config-repository中配置文件

![image-20200506100811041](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200506100811041.png)





### @Component 和 @Configuration

[@Component和@Configuration作为配置类的差别](https://blog.csdn.net/long476964/article/details/80626930)

[Spring @Configuration 和 @Component 区别](https://blog.csdn.net/isea533/article/details/78072133)

@Component和@Configuration都可以作为配置类

区别：

使用 @Component 没有通过 cglib 来代理 @Bean 方法的调用，得到的是不同的对象

使用 @Configuration 中所有带 `@Bean` 注解的方法都会被动态代理，因此调用该方法返回的都是同一个实例。



### @Scheduled

```java
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(Schedules.class)
public @interface Scheduled {
    String CRON_DISABLED = "-";

    String cron() default "";

    String zone() default "";

    long fixedDelay() default -1L;

    String fixedDelayString() default "";

    long fixedRate() default -1L;

    String fixedRateString() default "";

    long initialDelay() default -1L;

    String initialDelayString() default "";
}
```



#### 项目中

```java
@Scheduled(initialDelay = 1000, fixedDelayString = "${jobs.manager.schedule.time}")
public void execute() {
    jobsScheduler.schedule();
}
```

对 execute() 方法的 @Scheduled 【注解就是对下面的作用】

#### initialDelay

第一次延迟多长时间（毫秒）后再执行

#### fixedDelayString

上一次执行完毕时间点之后多长时间再执行。

与 ` fixedDelay` 意思相同，只是使用字符串的形式。唯一不同的是支持占位符。

![image-20200506101621577](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200506101621577.png)





### @Autowired，@Resource

Spring管理的Bean对象可以采用自动装配机制为属性赋值。基于注解方式进行自动装配，一般使用@Autowired,@Qualifer,@Resource这些注解。

![img](https://upload-images.jianshu.io/upload_images/5128967-a972bed5148fc81d.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

底层完成DI依赖注入操作是通过反射调用set方法，或者构造方法为属性赋值。

- @Autowired 可以修饰属性,构造方法,set方法,**默认依据类型**(属性类型,参数类型)为属性注入值.假如Spring容器中有多个相同类型的值,会参考名字进行匹配查找(属性名,set方法参数名,构造方法参数名),假如名字有相同的则注入,没有相同的会注入失败.

- @Qualifier 配合@Autowired注解按名字为属性注入值.

- @Resource 可以修饰属性或set方法,**默认依据名字**(属性名,set方法名)为属性注入值.假如spring容器中有名字相同但类型不同的bean就会注入失败,当没有找到对应名字的bean对象,此时会依据类型再次进行查找,假如相同类型则直接注入,当有多个相同类型可能会注入失败(假如是按set方法进行注入,依据方法名字没找到,还会按参数类型查找,对应类型有多个,还可能会按参数名查找,假如没找对应的,则注入失败).





### @Scope

@Scope注解是springIoc容器中的一个作用域，在 Spring IoC 容器中具有以下几种作用域：基本作用域**singleton（单例）**、**prototype(多例)**，Web 作用域（reqeust、session、globalsession），自定义作用域



1. #### singleton（单例）

   **此取值时表明容器中创建时只存在一个实例，所有引用此bean都是单一实例。**

   此外，singleton类型的bean定义从容器启动到第一次被请求而实例化开始，只要容器不销毁或退出，该类型的bean的单一实例就会一直存活，典型单例模式，如同servlet在web容器中的生命周期。

2. #### prototype（原型）

   **spring容器在进行输出prototype的bean对象时，会每次都重新生成一个新的对象给请求方。**

   虽然这种类型的对象的实例化以及属性设置等工作都是由容器负责的，但是只要准备完毕，并且对象实例返回给请求方之后，容器就不在拥有当前对象的引用，请求方需要自己负责当前对象后继生命周期的管理工作，包括该对象的销毁。

3. #### request

   再次说明request，session和global session类型只实用于web程序，通常是和XmlWebApplicationContext共同使用。

   <bean id ="requestPrecessor" class="...RequestPrecessor"  scope="request" />

   Spring容器，即XmlWebApplicationContext 会为每个HTTP请求创建一个全新的RequestPrecessor对象，当请求结束后，该对象的生命周期即告结束，**如同java web中request的生命周期**。当同时有100个HTTP请求进来的时候，容器会分别针对这10个请求创建10个全新的RequestPrecessor实例，且他们相互之间互不干扰，简单来讲，request可以看做prototype的一种特例，除了场景更加具体之外，语意上差不多。

4. #### session

   对于web应用来说，放到session中最普遍的就是用户的登录信息，对于这种放到session中的信息，我们可以使用如下形式的制定scope为session：

   <bean id ="userPreferences" class="...UserPreferences"  scope="session" />

   Spring容器会为每个独立的session创建属于自己的全新的UserPreferences实例，比request scope的bean会存活更长的时间，其他的方面没区别，**如同java web中session的生命周期**。

5. #### global session

   <bean id ="userPreferences" class="...UserPreferences"  scope="globalsession" />

   global session只有应用在基于porlet的web应用程序中才有意义，它映射到porlet的global范围的session，如果普通的servlet的web 应用中使用了这个scope，容器会把它作为普通的session的scope对待。



### @JobScope

```java
Convenient annotation for job scoped beans that defaults the proxy mode, so that it doesn't have to be specified explicitly on every bean definition. Use this on any &#64;Bean that needs to inject &#64;Values from the job context, and any bean that needs to share a lifecycle with a job execution (e.g. an JobExecutionListener). E.g.
```

每个运行的 `job` 只有一个这种 `bean` 的实例

































