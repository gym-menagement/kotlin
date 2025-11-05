package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Role
import com.gowoobro.gymspring.entity.RoleCreateRequest
import com.gowoobro.gymspring.entity.RoleUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    override fun findAll(pageable: Pageable): Page<Role>

    override fun findById(id: String): List<Role>

    override fun findByGym(gym: String): List<Role>

    override fun findByRole(role: String): List<Role>

    override fun findByName(name: String): List<Role>

    override fun findByDate(date: String): List<Role>
}