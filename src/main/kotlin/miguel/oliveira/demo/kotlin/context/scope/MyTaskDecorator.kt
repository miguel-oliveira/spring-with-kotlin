package miguel.oliveira.demo.kotlin.context.scope;

import org.springframework.core.task.TaskDecorator

class MyTaskDecorator : TaskDecorator {

  override fun decorate(runnable: Runnable): Runnable {
    return ThreadScopeRunnable(runnable)
  }
}