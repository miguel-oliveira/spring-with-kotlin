package miguel.oliveira.demo.kotlin.cache

import miguel.oliveira.demo.kotlin.cache.CacheController.Companion.BEAN_NAME
import miguel.oliveira.demo.kotlin.record.Record
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@CrossOrigin
@RestController(BEAN_NAME)
class CacheController(private val cacheService: CacheService) {

  companion object {
    const val BEAN_NAME = "CacheController"
  }

  @GetMapping(path = ["/cache"])
  @Record(BEAN_NAME)
  fun cache(@RequestParam(required = false) key: String?): ResponseEntity<String> {
    val cachedValue = if (key != null) cacheService.cache(key).toString() else cacheService.cache()
    return ResponseEntity.ok(cachedValue);
  }

  @GetMapping(path = ["/cache-evict"])
  @Record(BEAN_NAME)
  fun cacheEvict(@RequestParam(required = false) key: String?): ResponseEntity<Void> {
    if (key != null) {
      cacheService.cacheEvict(key)
    } else {
      cacheService.cacheEvict()
    }
    return ResponseEntity.ok().build()
  }
}
