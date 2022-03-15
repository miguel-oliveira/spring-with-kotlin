package miguel.oliveira.demo.kotlin.configuration

import com.rabbitmq.client.ConnectionFactory
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfiguration {

  @Bean
  fun connectionFactory(abstractConnectionFactory: AbstractConnectionFactory): ConnectionFactory {
    val connectionFactory = abstractConnectionFactory.rabbitConnectionFactory
    connectionFactory.isAutomaticRecoveryEnabled = true
    return connectionFactory
  }

}