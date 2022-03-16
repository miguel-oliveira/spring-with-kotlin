package miguel.oliveira.demo.kotlin.record

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Aspect
@Component
class RecordingAdvice(private val eventPublisher: ApplicationEventPublisher) {

  private val log = LoggerFactory.getLogger(this.javaClass)

  @AfterReturning("@annotation(miguel.oliveira.demo.kotlin.record.Record)")
  fun record(joinPoint: JoinPoint) {
    log.debug(
      "Registering method call with signature {} and args {}", joinPoint.signature, joinPoint.args
    )
    eventPublisher.publishEvent(RecordedMethodCallJoinPoint(joinPoint))
  }
}