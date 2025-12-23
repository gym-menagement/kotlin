package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Membership
import com.gowoobro.gymspring.entity.MembershipCreateRequest
import com.gowoobro.gymspring.entity.MembershipUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime




@Repository
interface MembershipRepository : JpaRepository<Membership, Long> {
    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    override fun findAll(pageable: Pageable): Page<Membership>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    override fun findById(id: Long): java.util.Optional<Membership>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByuserId(user: Long): List<Membership>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findBygymId(gym: Long): List<Membership>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByDate(date: LocalDateTime): List<Membership>
}
