package miguel.oliveira.demo.kotlin.record

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.ProducerTemplate
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import java.lang.reflect.Method

@Component
class RecordEventPublisher(
  private val producer: ProducerTemplate,
  private val objectMapper: ObjectMapper
) {

  private val log = LoggerFactory.getLogger(this.javaClass)

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = true)
  fun publish(methodCallJoinPoint: RecordedMethodCallJoinPoint) {

    val joinPoint = methodCallJoinPoint.joinPoint

    val signature = joinPoint.signature as MethodSignature

    val recordedMethodCall =
      RecordedMethodCall(
        extractBeanName(signature.method),
        signature.name,
        signature.parameterTypes.toList(),
        joinPoint.args.toList()
      )

    log.info("Publishing recorded event: {}", recordedMethodCall)
    producer.asyncRequestBody(
      RecordingEventRoute.DIRECT,
      objectMapper.writeValueAsString(recordedMethodCall)
    )
  }

  private fun extractBeanName(method: Method): String {
    return method.getAnnotation(Record::class.java).beanName
  }
}