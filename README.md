# Spring with kotlin

Simple Spring application written in kotlin showcasing some of Spring's capabilities:

* [AMQP](https://github.com/miguel-oliveira/spring-demo-kotlin/tree/master/src/main/kotlin/miguel/oliveira/demo/kotlin/amqp)
  - Simple message consumer
  using [Apache Camel Spring RabbitMQ](https://camel.apache.org/components/3.15.x/spring-rabbitmq-component.html).


* [Cache](https://github.com/miguel-oliveira/spring-demo-kotlin/tree/master/src/main/kotlin/miguel/oliveira/demo/kotlin/cache)
  - Rest Controller with caching endpoint by name and key, and a cache evicting endpoint by the same name and key.


* [Custom Spring Bean Thread Scope](https://github.com/miguel-oliveira/spring-demo-kotlin/tree/master/src/main/kotlin/miguel/oliveira/demo/kotlin/context/scope)
  - Supports destruction callback registration and clean up;
  - This thread scope is also inheritable, which means the scope will be propagated to spawned
    threads. [Here](https://github.com/miguel-oliveira/spring-demo-kotlin/blob/master/src/main/kotlin/miguel/oliveira/demo/kotlin/configuration/AsyncConfiguration.kt)
    is an example of a spring simple async task executor configuration which makes use of this capability. Note that
    since the scope is only propagated when spawning a new thread, the task executor must not
    reuse threads.


* [Jpa](https://github.com/miguel-oliveira/spring-demo-kotlin/tree/master/src/main/kotlin/miguel/oliveira/demo/kotlin/jpa)
  - Simple [entity](https://github.com/miguel-oliveira/spring-demo-kotlin/blob/master/src/main/kotlin/miguel/oliveira/demo/kotlin/jpa/MyEntity.kt)
  with creation and modification auditing;
  - [Rest Controller](https://github.com/miguel-oliveira/spring-demo-kotlin/blob/master/src/main/kotlin/miguel/oliveira/demo/kotlin/jpa/MyController.kt)
  with POST and GET endpoints for the entity;
  - GET endpoint supports pagination and filtering. Filtering is done using a
  custom [Jpa Specification builder](https://github.com/miguel-oliveira/spring-demo-kotlin/blob/master/src/main/kotlin/miguel/oliveira/demo/kotlin/jpa/SpecificationBuilder.kt)
  .


* [Recording](https://github.com/miguel-oliveira/spring-demo-kotlin/tree/master/src/main/kotlin/miguel/oliveira/demo/kotlin/record)
  and [replaying](https://github.com/miguel-oliveira/spring-demo-kotlin/tree/master/src/main/kotlin/miguel/oliveira/demo/kotlin/replay)
  of method invocations using [Spring AOP](https://docs.spring.io/spring-framework/docs/2.5.x/reference/aop.html) and reflection
