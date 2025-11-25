package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Role
import com.gowoobro.gymspring.entity.RoleCreateRequest
import com.gowoobro.gymspring.entity.RoleUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.role.Roleid



@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    @EntityGraph(attributePaths = [
        "gym"
    ])
    override fun findAll(pageable: Pageable): Page<Role>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    override fun findById(id: Long): java.util.Optional<Role>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    fun findBygymId(gym: Long): List<Role>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    fun findByRoleid(roleid: Roleid): List<Role>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    fun findByName(name: String): List<Role>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    fun findByDate(date: LocalDateTime): List<Role>
}
