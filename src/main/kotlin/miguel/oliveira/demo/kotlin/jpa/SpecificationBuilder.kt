package miguel.oliveira.demo.kotlin.jpa

import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import java.time.Instant
import java.util.*
import java.util.stream.Stream
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Expression
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Service
class SpecificationBuilder {

  private val likeEscapeChar = '!'
  private val likeEscapeString = "!"

  fun build(queryParams: MyEntityQueryParams?): Specification<MyEntity> {
    val specificationStream: Stream<Specification<MyEntity>> =
      Stream.of(
        likeSpecification({ x -> x.get(MyEntity.NAME) }, queryParams?.name),
        instantSpecification(
          { x -> x.get(MyEntity.CREATED_AT) },
          queryParams?.createdAtFrom,
          queryParams?.createdAtTo
        ),
        instantSpecification(
          { x -> x.get(MyEntity.MODIFIED_AT) },
          queryParams?.modifiedAtFrom,
          queryParams?.modifiedAtTo
        ),
        inSpecification({ x -> x.get(MyEntity.NAME) }, queryParams?.nameIn)
      )

    return specificationStream
      .reduce { acc, curr -> acc.and(curr) }
      .orElse(Specification.where(null))
  }

  private fun <T> likeSpecification(
    expression: (Root<T>) -> Expression<String>,
    value: String?
  ): Specification<T> {
    return likeSpecification({ root, _ -> expression.invoke(root) }, value)
  }

  private fun <T> likeSpecification(
    expression: (Root<T>, CriteriaBuilder) -> Expression<String>,
    value: String?
  ): Specification<T> {

    return if (value != null) {
      Specification { root, _, cb ->
        likePredicate(
          cb,
          expression.invoke(root, cb),
          value
        )
      }
    } else {
      Specification.where(null)
    }

  }

  private fun likePredicate(
    cb: CriteriaBuilder,
    expression: Expression<String>,
    value: String
  ): Predicate {

    val lower = cb.lower(expression)
    val requiresEscape = value.contains("%") || value.contains("_")

    return if (requiresEscape) {
      val escaped = value.replace(likeEscapeString, likeEscapeChar + likeEscapeString)
        .replace("%", "$likeEscapeChar%")
        .replace("_", "$likeEscapeChar\\_")
      val like = "%${escaped.lowercase(Locale.getDefault())}%"
      cb.like(lower, like, likeEscapeChar)
    } else {
      val like = "%${value.lowercase(Locale.getDefault())}%"
      cb.like(lower, like)
    }
  }

  private fun <T> instantSpecification(
    expression: (Root<T>) -> Expression<Instant>,
    valueFrom: Long?,
    valueTo: Long?
  ): Specification<T> {

    return if (valueFrom != null && valueTo != null) {
      Specification { root, _, cb ->
        cb.between(
          expression.invoke(root),
          Instant.ofEpochMilli(valueFrom),
          Instant.ofEpochMilli(valueTo)
        )
      }
    } else if (valueFrom != null) {
      Specification { root, _, cb ->
        cb.greaterThanOrEqualTo(
          expression.invoke(root),
          Instant.ofEpochMilli(valueFrom)
        )
      }
    } else if (valueTo != null) {
      Specification { root, _, cb ->
        cb.lessThanOrEqualTo(
          expression.invoke(root),
          Instant.ofEpochMilli(valueTo)
        )
      }
    } else {
      Specification.where(null)
    }
  }

  private fun <T, V> inSpecification(
    expression: (Root<T>) -> Expression<V>,
    values: Collection<V>?
  ): Specification<T> {

    return if (!CollectionUtils.isEmpty(values)) {
      Specification { root, _, _ ->
        expression.invoke(root).`in`(values)
      }
    } else {
      Specification.where(null)
    }

  }

}