package miguel.oliveira.demo.kotlin.cache

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class CacheController(private val cacheService: CacheService) {

  @GetMapping(path = ["/cache"])
  fun cache(@RequestParam(required = false) key: String?): ResponseEntity<String> {
    val cachedValue = if (key != null) cacheService.cache(key).toString() else cacheService.cache()
    return ResponseEntity.ok(cachedValue);
  }

  @GetMapping(path = ["/cache-evict"])
  fun cacheEvict(@RequestParam(required = false) key: String?): ResponseEntity<Void> {
    if (key != null) {
      cacheService.cacheEvict(key)
    } else {
      cacheService.cacheEvict()
    }
    return ResponseEntity.ok().build()
  }
}