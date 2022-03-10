package miguel.oliveira.demo.kotlin

import miguel.oliveira.demo.kotlin.context.scope.ThreadScope
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy
@EnableJpaAuditing
class MySpringDemoKotlinApplication {

  @Bean
  fun customBeanFactoryPostProcessor(): BeanFactoryPostProcessor {
    return BeanFactoryPostProcessor { beanFactory ->
      beanFactory.registerScope("thread", ThreadScope())
    }
  }

}

fun main(args: Array<String>) {
  runApplication<MySpringDemoKotlinApplication>(*args)
}
