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
    override fun findAll(pageable: Pageable): Page<Setting>

    override fun findById(id: Long): java.util.Optional<Setting>

    fun findByCategory(category: String): List<Setting>

    fun findByName(name: String): List<Setting>

    fun findByKey(key: String): List<Setting>

    fun findByValue(value: String): List<Setting>

    fun findByRemark(remark: String): List<Setting>

    fun findByType(type: Type): List<Setting>

    fun findByData(data: String): List<Setting>

    fun findByOrder(order: Int): List<Setting>

    fun findByDate(date: LocalDateTime): List<Setting>
}
