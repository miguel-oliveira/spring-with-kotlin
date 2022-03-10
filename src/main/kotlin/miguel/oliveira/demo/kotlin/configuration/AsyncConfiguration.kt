package miguel.oliveira.demo.kotlin.configuration

import miguel.oliveira.demo.kotlin.context.scope.ThreadScopeTaskDecorator
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.Executor

@EnableAsync
@Configuration
class AsyncConfiguration : AsyncConfigurer {

  override fun getAsyncExecutor(): Executor? {
    val executor = SimpleAsyncTaskExecutor()
    executor.setTaskDecorator(ThreadScopeTaskDecorator())
    return executor
  }
}