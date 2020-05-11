## Spring Batch



### 1. Spring Batch 架构图

![image-20200429144523920](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200429144523920.png)

图中突出了三个主要的高级组件：应用层（ `Application` ），核心层( `BatchCore` )和基础架构层( `BatchInfrastructure` )。

其中该应用层包含开发人员使用 `SpringBatch` 编写的所有自定义的批处理作业和自定义代码。 核心层包含启动和控制批处理作业所需的核心运行时类。它包括 `JobLauncher` ， `Job` 和 `Step` 的实现。

应用层和核心层都建立 在通用基础架构之上。此基础结构包含通用的读( `ItemReader` )、写( `ItemWriter` )和服务处理（如 `RetryTemplate` ）。

在开发应用时，引用 `spring-batch-infrastructure` 和 `spring-batch-core` 包，即可使用基础架构层及核心层内容，然后基于这两层进行应用业务逻辑的实现。



### 2. Spring Batch 工作原理

![image-20200429154736350](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200429154736350.png)

一个【Batch】（批处理）过程由一个【Job】（作业）组成。这个实体封装了整个批处理过程。

一个【Job】可以由一个或多个【Step】（步骤）组成。在大多数情况下，一个步骤将读取数据（通过`ItemReader`），处理数据（`ItemProcessor`），然后写入数据（`ItemWriter`）。

【JobLauncher】处理启动一个【Job】。

最后，【JobRepository】存储关于配置和执行【Job】的元数据。



| 领域对象      | 描述                                                  |
| ------------- | ----------------------------------------------------- |
| JobRepository | 作业仓库，保存Job、Step执行过程中的状态及结果         |
| JobLauncher   | 作业执行器，是执行Job的入口                           |
| Job           | 一个批处理任务，由一个或多个Step组成                  |
| Step          | 一个任务的具体的执行逻辑单位                          |
| Item          | 一条数据记录                                          |
| ItemReader    | 从数据源读数据                                        |
| ItemProcessor | 对数据进行处理，如数据清洗、转换、过滤、校验等        |
| ItemWriter    | 写入数据到指定目标                                    |
| Chunk         | 给定数量的Item集合，如读取到chunk数量后，才进行写操作 |
| Tasklet       | Step中具体执行逻辑，可重复执行                        |



### JobRepository

`JobRepository` 用于 Spring Batch 中各种持久化域对象的基本 CRUD 操作，如 `JobExecutor` 和 `StepExecutor`。 它是许多主要框架特性所需要的，例如 JobLauncher、 Job 和 Step。



### Chunk

数据块：指示这是一个Item（项）的 Step（步骤），以及在提交事务之前要处理的项数。





### 3. Config Job

```java
@Bean
public Job footballJob() {
    return this.jobBuilderFactory.get("footballJob")
                     .start(playerLoad())
                     .next(gameLoad())
                     .next(playerSummarization())
                     .end()
                     .build();
}
```

A `Job` (and typically any `Step` within it) requires a `JobRepository`. The configuration of the `JobRepository` is handled via the [`BatchConfigurer`](https://docs.spring.io/spring-batch/docs/4.2.x/reference/html/job.html#javaConfig).



#### 3.1. Restartability (可重启性)

执行批作业时的一个关键问题涉及重新启动作业时的行为。 如果已经存在针对特定 JobInstance 的 JobExecution，则启动 Job 被认为是“重新启动”。 理想情况下，所有作业都应该能够从它们停止的地方启动，但是在某些情况下这是不可能的。 确保在此场景中创建新的 JobInstance 完全取决于开发人员。 然而，Spring Batch 确实提供了一些帮助。 如果一个 Job 不应该被重新启动，而应该作为一个新的 JobInstance 的一部分运行，那么 restartable 属性可能被设置为“ false” :

```java
@Bean
public Job footballJob() {
    return this.jobBuilderFactory.get("footballJob")
                     .preventRestart()
                     ...
                     .build();
}
```

```java
// JobBuilderHelper

public B preventRestart() {
   properties.restartable = false;
   @SuppressWarnings("unchecked")
   B result = (B) this;
   return result;
}
```



换句话说，将 restartable 设置为 false 意味着“此作业不支持重新启动”。 重新启动一个不可重新启动的作业将导致一个 JobRestartException 被抛出:

```java
Job job = new SimpleJob();
job.setRestartable(false);

JobParameters jobParameters = new JobParameters();

JobExecution firstExecution = jobRepository.createJobExecution(job, jobParameters);
jobRepository.saveOrUpdate(firstExecution);

try {
    jobRepository.createJobExecution(job, jobParameters);
    fail();
}
catch (JobRestartException e) {
    // expected
}
```

```java
// SimpleJob extends AbstractJob
public void setRestartable(boolean restartable) {
   this.restartable = restartable;
}
```

这段 JUnit 代码显示了第一次为不可重新启动的作业创建 JobExecution 时不会引起任何问题。 但是，第二次尝试将抛出 JobRestartException。



#### 3.2. Intercepting Job Execution（拦截作业执行）

在作业的执行过程中，通知其生命周期中的各种事件以便执行定制代码可能是有用的。 允许在适当的时候调用 `JobListener`:

```java
public interface JobExecutionListener {

    void beforeJob(JobExecution jobExecution);

    void afterJob(JobExecution jobExecution);

}
```



可以通过作业中的 listeners 元素将 `JobListeners` 添加到 `SimpleJob` 中:

```java
@Bean
public Job footballJob() {
    return this.jobBuilderFactory.get("footballJob")
                     .listener(sampleListener())
                     ...
                     .build();
}
```



应该注意的是，无论工作的成功或失败，`afterJob` 都会被调用。 如果需要确定成功或失败，可以从 `jobexection` 中获得:

```java
public void afterJob(JobExecution jobExecution){
    if( jobExecution.getStatus() == BatchStatus.COMPLETED ){
        //job success
    }
    else if(jobExecution.getStatus() == BatchStatus.FAILED){
        //job failure
    }
}
```



与该界面相对应的注释如下:

- `@BeforeJob`

  @ beforejob

- `@AfterJob`

  @ afterjob

#### 

#### 3.3. JobParametersValidator（作业参数验证器）

在 XML 命名空间中声明的作业或使用 `AbstractJob` 的任何子类的作业可以在运行时有选择地声明作业参数的验证器。 例如，当您需要断言一个作业已经使用它的所有强制参数启动时，这是非常有用的。 有一个 `DefaultJobParametersValidator`，可用于约束简单的强制参数和可选参数的组合，对于更复杂的约束，您可以自己实现接口。

验证器的配置通过 java 构建器来支持，例如:

```java
@Bean
public Job job1() {
    return this.jobBuilderFactory.get("job1")
                     .validator(parametersValidator())
                     ...
                     .build();
}
```











