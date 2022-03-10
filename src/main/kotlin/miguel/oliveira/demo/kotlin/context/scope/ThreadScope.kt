package miguel.oliveira.demo.kotlin.context.scope;

import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.config.Scope

class ThreadScope : Scope {

  override fun get(name: String, factory: ObjectFactory<*>): Any {
    val threadScopeAttributes = ThreadScopeContextHolder.getThreadScopeAttributes()
    var scopedObject = threadScopeAttributes.getBean(name)
    if (scopedObject == null) {
      scopedObject = factory.getObject()
      threadScopeAttributes.putBean(name, scopedObject)
    }
    return scopedObject!!
  }

  override fun remove(name: String): Any? {
    return ThreadScopeContextHolder.currentThreadScopeAttributes().removeBean(name)
  }

  override fun registerDestructionCallback(name: String, callback: Runnable) {
    ThreadScopeContextHolder.currentThreadScopeAttributes()
      .registerRequestDestructionCallback(name, callback)
  }

  override fun resolveContextualObject(name: String): Any? {
    return null
  }

  override fun getConversationId(): String {
    return Thread.currentThread().name
  }

}