package miguel.oliveira.demo.kotlin.jpa

import org.springframework.stereotype.Service

@Service
class MyService(
  private val repository: MyRepository
) {

  fun getAll(): List<MyEntity> {
    return repository.findAll()
  }

  fun create(request: MyEntityCreationRequest): MyEntity {
    val entity = MyEntity(name = request.name)
    return repository.save(entity)
  }

}