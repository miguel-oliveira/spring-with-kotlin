package miguel.oliveira.demo.kotlin.amqp

import com.fasterxml.jackson.annotation.JsonProperty

data class Message(@JsonProperty("body") val body: Map<String, Any>)
