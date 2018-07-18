# 断路器Hystrix的工作流程：

1、创建HystrixCommand或HystrixObservableCommand对象<br />
首先构建一个HystrixCommand或是HystrixObservableCommand对象，用来表示对依赖服务的的操作请求，同时传递所有需要的参数。<br />
HystrixCommand：用在依赖的服务返回单个操作结果的时候。<br />
HystrixObservableCommand：用在依赖的服务返回多个操作结果的时候。<br />
2、命令执行<br />
execute()：同步执行，从依赖的服务返回一个单一的结果对象，或是在发生错误的时候抛出异常<br />
queue()：异步执行，直接返回一个Future对象，其中包含了服务执行结束时要返回的单一结果对象<br />
observe()：返回Observable对象，它代表了操作的多个结果，它是一个Hot Observable<br />
toObservable()：同样会返回Observable对象，也代表了操作的多个结果，返回的是一个Cold Observable<br />
RxJava响应式编程：Observable是"事件源"或"被观察者"，Subscriber是"订阅者"或"观察者"<br />

断路器HystrixCircuitBreaker是Hystrix的核心组件

Spring Cloud Feign基于Netflix Feign实现，整合了Spring Cloud Ribbon和DSpring Cloud Hystrix，除了提供这两者<br />
的功能外，它还提供了一种声明式的Web服务客户端定义方式<br />
Spring Cloud Feign具备可插拔的注解支持，包括Feign注解和JAX-RS注解，并且在Netflix Feign的基础上拓展了对SpringMVC的<br />
的注解支持
