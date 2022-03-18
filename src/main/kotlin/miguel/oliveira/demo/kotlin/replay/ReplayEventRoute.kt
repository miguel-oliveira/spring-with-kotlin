package miguel.oliveira.demo.kotlin.replay

import org.apache.camel.builder.RouteBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ReplayEventRoute(
  @Value("\${my-app.messaging.replay.route}") private val replayRoute: String,
  private val replayer: Replayer
) : RouteBuilder() {

  override fun configure() {
    from(replayRoute).bean(replayer);
  }

}