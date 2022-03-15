package miguel.oliveira.demo.kotlin.rabbitmq

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.dataformat.JsonLibrary
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class EventRouteBuilder(
  @Value("\${my-app.messaging.consumer-route}") private val route: String,
  private val consumer: Consumer
) : RouteBuilder() {

  override fun configure() {
    from(route)
      .unmarshal()
      .json(JsonLibrary.Jackson, Message::class.java)
      .bean(consumer)
  }
}