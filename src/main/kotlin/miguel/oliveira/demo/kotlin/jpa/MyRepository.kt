package miguel.oliveira.demo.kotlin.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface MyRepository : JpaRepository<MyEntity, String>, JpaSpecificationExecutor<MyEntity>