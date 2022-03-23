package miguel.oliveira.demo.kotlin.jpa

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

data class MyEntityQueryParams(
  val name: String?,
  val createdAtFrom: Long?,
  val createdAtTo: Long?,
  val modifiedAtFrom: Long?,
  val modifiedAtTo: Long?,
  val nameIn: Set<String>?
)

data class MyEntityCreationRequest(
  @NotBlank
  @JsonProperty("name")
  val name: String
)