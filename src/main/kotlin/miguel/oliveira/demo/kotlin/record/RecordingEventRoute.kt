package miguel.oliveira.demo.kotlin.record

import org.apache.camel.ExchangePattern
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.rabbitmq.RabbitMQConstants
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RecordingEventRoute(
  @Value("\${my-app.messaging.record.route}") private val recordRoute: String,
  @Value("\${my-app.messaging.record.key}") private val recordRoutingKey: String
) : RouteBuilder() {

  companion object {
    const val DIRECT = "direct:record"
  }

  override fun configure() {
    from(DIRECT)
      .setHeader(RabbitMQConstants.ROUTING_KEY, constant(recordRoutingKey))
      .to(ExchangePattern.InOnly, recordRoute)
  }

}