package miguel.oliveira.demo.kotlin.jpa

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
  fun get(
    @ModelAttribute queryParams: MyEntityQueryParams?,
    pageable: Pageable
  ): ResponseEntity<Page<MyEntity>> {
    return ResponseEntity.ok(service.get(queryParams, pageable))
  }

  @PostMapping
  fun create(@RequestBody request: MyEntityCreationRequest): ResponseEntity<MyEntity> {
    val created = service.create(request)
    return ResponseEntity(created, HttpStatus.CREATED)
  }
}