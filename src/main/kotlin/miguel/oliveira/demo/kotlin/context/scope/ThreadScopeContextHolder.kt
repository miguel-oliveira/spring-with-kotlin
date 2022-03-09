package miguel.oliveira.demo.kotlin.context.scope;

class ThreadScopeContextHolder {

  companion object {
    private val threadScopeAttributesHolder =
      object : InheritableThreadLocal<ThreadScopeAttributes>() {

        override fun initialValue(): ThreadScopeAttributes {
          return ThreadScopeAttributes()
        }

        override fun childValue(parentValue: ThreadScopeAttributes): ThreadScopeAttributes {
          return ThreadScopeAttributes(parentValue)
        }
      }

    fun getThreadScopeAttributes(): ThreadScopeAttributes {
      return threadScopeAttributesHolder.get()
    }

    @Throws(IllegalStateException::class)
    fun currentThreadScopeAttributes(): ThreadScopeAttributes {
      return threadScopeAttributesHolder.get()
        ?: throw IllegalStateException("No thread scoped attributes.")
    }
  }

}