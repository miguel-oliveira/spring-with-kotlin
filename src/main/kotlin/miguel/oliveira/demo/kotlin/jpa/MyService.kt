package miguel.oliveira.demo.kotlin.jpa

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MyService(
  private val repository: MyRepository,
  private val specBuilder: SpecificationBuilder
) {

  fun get(queryParams: MyEntityQueryParams?, pageable: Pageable): Page<MyEntity> {
    val specification = specBuilder.build(queryParams)
    return repository.findAll(specification, pageable)
  }

  fun create(request: MyEntityCreationRequest): MyEntity {
    val entity = MyEntity(name = request.name)
    return repository.save(entity)
  }

}