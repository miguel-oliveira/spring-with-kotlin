package miguel.oliveira.demo.kotlin.jpa

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@EntityListeners(AuditingEntityListener::class)
class MyEntity(

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  var id: String? = null,

  @Version
  var version: Long? = 0,

  @CreatedDate
  @Column(updatable = false)
  var createdAt: Instant? = null,

  @LastModifiedDate
  var modifiedAt: Instant? = null,

  @NotBlank
  @Column(nullable = false)
  val name: String

) {

  companion object {
    const val NAME = "name"
    const val CREATED_AT = "createdAt"
    const val MODIFIED_AT = "modifiedAt"
  }

}
