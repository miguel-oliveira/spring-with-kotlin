package miguel.oliveira.demo.kotlin.amqp

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class Consumer {

  private val log = LoggerFactory.getLogger(this.javaClass)

  fun consume(message: Message) {
    log.info("Received message: {}", message)
  }
}