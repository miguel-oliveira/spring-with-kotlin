package miguel.oliveira.demo.kotlin.record

import org.aspectj.lang.JoinPoint

data class RecordedMethodCallJoinPoint(val joinPoint: JoinPoint)

data class RecordedMethodCall(
  val beanName: String,
  val methodName: String,
  val parameterTypes: List<Class<*>>,
  val args: List<Any>
)
