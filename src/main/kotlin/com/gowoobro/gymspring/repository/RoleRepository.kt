package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Role
import com.gowoobro.gymspring.entity.RoleCreateRequest
import com.gowoobro.gymspring.entity.RoleUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.role.Role as RoleEnum


@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    override fun findAll(pageable: Pageable): Page<Role>

    fun findByGym(gym: Long): List<Role>

    fun findByRole(role: RoleEnum): List<Role>

    fun findByName(name: String): List<Role>

    fun findByDate(date: LocalDateTime): List<Role>
}