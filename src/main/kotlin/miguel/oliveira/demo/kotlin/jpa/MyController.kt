package miguel.oliveira.demo.kotlin.jpa

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/entities")
class MyController(
  private val service: MyService
) {

  @GetMapping
  fun get(): ResponseEntity<List<MyEntity>> {
    return ResponseEntity.ok(service.getAll())
  }

  @PostMapping
  fun create(@RequestBody request: MyEntityCreationRequest): ResponseEntity<MyEntity> {
    val created = service.create(request)
    return ResponseEntity(created, HttpStatus.CREATED)
  }
}