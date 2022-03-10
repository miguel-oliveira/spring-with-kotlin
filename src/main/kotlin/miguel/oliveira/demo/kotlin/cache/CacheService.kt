package miguel.oliveira.demo.kotlin.cache

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random

@Service
class CacheService {

  companion object {
    const val MY_CACHE_NAME = "MY_CACHE_NAME"
  }

  private val log: Logger = LoggerFactory.getLogger(this.javaClass)
  private val random = Random.Default

  @Cacheable(MY_CACHE_NAME)
  fun cache(): String {
    log.info("Cache by name")
    return UUID.randomUUID().toString()
  }

  @Cacheable(value = [MY_CACHE_NAME], key = "#key")
  fun cache(key: String): Int {
    log.info("Cache by name and key -> {}", key)
    return random.nextInt()
  }

  @CacheEvict(MY_CACHE_NAME)
  fun cacheEvict() {
    log.info("Cache evict by name.")
  }

  @CacheEvict(value = [MY_CACHE_NAME], key = "#key")
  fun cacheEvict(key: String) {
    log.info("Cache evict by name and key {}", key)
  }


}