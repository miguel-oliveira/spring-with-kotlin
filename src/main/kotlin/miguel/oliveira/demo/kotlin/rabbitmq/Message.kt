package miguel.oliveira.demo.kotlin.rabbitmq

import com.fasterxml.jackson.annotation.JsonProperty

data class Message(@JsonProperty("body") val body: Map<String, Any>)
