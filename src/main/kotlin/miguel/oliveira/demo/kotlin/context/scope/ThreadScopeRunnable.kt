package miguel.oliveira.demo.kotlin.context.scope;

class ThreadScopeRunnable(private val target: Runnable) : Runnable {

  override fun run() {
    try {
      target.run();
    } finally {
      ThreadScopeContextHolder.currentThreadScopeAttributes().clear()
    }
  }

}