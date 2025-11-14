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



@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    @Query("SELECT m FROM Role m LEFT JOIN FETCH m.gym")
    override fun findAll(pageable: Pageable): Page<Role>

    @Query("SELECT m FROM Role m LEFT JOIN FETCH m.gym WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Role>

    @Query("SELECT m FROM Role m LEFT JOIN FETCH m.gym WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Role>

    @Query("SELECT m FROM Role m LEFT JOIN FETCH m.gym WHERE m.roleid = :roleid")
    fun findByRoleidWithJoin(roleid: Int): List<Role>

    @Query("SELECT m FROM Role m LEFT JOIN FETCH m.gym WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<Role>

    @Query("SELECT m FROM Role m LEFT JOIN FETCH m.gym WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Role>
}