package miguel.oliveira.demo.kotlin.jpa

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

data class MyEntityCreationRequest(
  @NotBlank
  @JsonProperty("name")
  val name: String
)
