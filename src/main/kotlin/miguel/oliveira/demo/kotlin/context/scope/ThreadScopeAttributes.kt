package miguel.oliveira.demo.kotlin.context.scope;

import org.slf4j.LoggerFactory

class ThreadScopeAttributes {

  private val log = LoggerFactory.getLogger(this.javaClass)

  private var beanMap = HashMap<String, Any>()
  private var destructionCallbacks = LinkedHashMap<String, Runnable>()

  constructor()

  constructor(threadScopeAttributes: ThreadScopeAttributes) {
    beanMap = HashMap(threadScopeAttributes.beanMap)
    destructionCallbacks = LinkedHashMap(threadScopeAttributes.destructionCallbacks)
  }

  internal fun getBeanMap(): MutableMap<String, Any> {
    return beanMap
  }

  internal fun registerRequestDestructionCallback(name: String, callback: Runnable) {
    destructionCallbacks[name] = callback
  }

  fun clear() {
    processDestructionCallbacks()
    beanMap.clear()
  }

  private fun processDestructionCallbacks() {
    for (name: String in destructionCallbacks.keys) {
      log.debug(
        "Performing destruction callback for '{}' bean on thread '{}'.",
        name,
        Thread.currentThread().name
      )
      destructionCallbacks[name]?.run()
    }

    destructionCallbacks.clear()
  }

}