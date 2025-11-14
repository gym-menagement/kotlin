package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Setting
import com.gowoobro.gymspring.entity.SettingCreateRequest
import com.gowoobro.gymspring.entity.SettingUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.setting.Type


@Repository
interface SettingRepository : JpaRepository<Setting, Long> {
    @Query("SELECT m FROM Setting m")
    override fun findAll(pageable: Pageable): Page<Setting>

    @Query("SELECT m FROM Setting m WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Setting>

    @Query("SELECT m FROM Setting m WHERE m.category = :category")
    fun findByCategoryWithJoin(category: String): List<Setting>

    @Query("SELECT m FROM Setting m WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<Setting>

    @Query("SELECT m FROM Setting m WHERE m.key = :key")
    fun findByKeyWithJoin(key: String): List<Setting>

    @Query("SELECT m FROM Setting m WHERE m.value = :value")
    fun findByValueWithJoin(value: String): List<Setting>

    @Query("SELECT m FROM Setting m WHERE m.remark = :remark")
    fun findByRemarkWithJoin(remark: String): List<Setting>

    @Query("SELECT m FROM Setting m WHERE m.type = :type")
    fun findByTypeWithJoin(type: Type): List<Setting>

    @Query("SELECT m FROM Setting m WHERE m.data = :data")
    fun findByDataWithJoin(data: String): List<Setting>

    @Query("SELECT m FROM Setting m WHERE m.order = :order")
    fun findByOrderWithJoin(order: Int): List<Setting>

    @Query("SELECT m FROM Setting m WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Setting>
}