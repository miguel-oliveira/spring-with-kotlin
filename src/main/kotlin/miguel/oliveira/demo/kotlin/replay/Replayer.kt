package miguel.oliveira.demo.kotlin.replay

import com.fasterxml.jackson.databind.ObjectMapper
import miguel.oliveira.demo.kotlin.record.RecordedMethodCall
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class Replayer(
  private val objectMapper: ObjectMapper,
  private val context: ApplicationContext
) {

  private val log = LoggerFactory.getLogger(this.javaClass)

  fun replay(event: String) {
    val recordedMethodCall = objectMapper.readValue(event, RecordedMethodCall::class.java)
    val types = recordedMethodCall.parameterTypes.toTypedArray()
    val args = recordedMethodCall.args.toTypedArray()
    val parsedArgs: Array<Any?> = arrayOfNulls(args.size)

    for (i in args.indices) {
      parsedArgs[i] = objectMapper.convertValue(args[i], types[i])
    }

    log.info("Replaying recorded event: {}", recordedMethodCall)
    log.info("Parsed args: {}", parsedArgs)

    val bean = context.getBean(recordedMethodCall.beanName)
    val method = bean.javaClass.getDeclaredMethod(recordedMethodCall.methodName, *types)

    method.invoke(bean, *parsedArgs)
  }
}