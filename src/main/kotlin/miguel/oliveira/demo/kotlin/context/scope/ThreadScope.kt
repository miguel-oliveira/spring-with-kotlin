package miguel.oliveira.demo.kotlin.context.scope;

import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.config.Scope

class ThreadScope : Scope {

  override fun get(name: String, factory: ObjectFactory<*>): Any {
    val scope: MutableMap<String, Any> = ThreadScopeContextHolder.currentThreadScopeAttributes()
      .getBeanMap()
    // NOTE: Do NOT modify the following to use Map::computeIfAbsent. For details,
    // see https://github.com/spring-projects/spring-framework/issues/25801.
    // NOTE: Do NOT modify the following to use Map::computeIfAbsent. For details,
    // see https://github.com/spring-projects/spring-framework/issues/25801.
    var scopedObject = scope[name]
    if (scopedObject == null) {
      scopedObject = factory.getObject()
      scope[name] = scopedObject
    }
    return scopedObject!!
  }

  override fun remove(name: String): Any? {
    return ThreadScopeContextHolder.currentThreadScopeAttributes().getBeanMap().remove(name)
  }

  override fun registerDestructionCallback(name: String, callback: Runnable) {
    ThreadScopeContextHolder.currentThreadScopeAttributes()
      .registerRequestDestructionCallback(name, callback)
  }

  override fun resolveContextualObject(p0: String): Any? {
    return null
  }

  override fun getConversationId(): String? {
    return Thread.currentThread().name
  }

}