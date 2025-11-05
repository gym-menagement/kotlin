package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Setting
import com.gowoobro.gymspring.entity.SettingCreateRequest
import com.gowoobro.gymspring.entity.SettingUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SettingRepository : JpaRepository<Setting, Long> {
    override fun findAll(pageable: Pageable): Page<Setting>

    override fun findById(id: String): List<Setting>

    override fun findByCategory(category: String): List<Setting>

    override fun findByName(name: String): List<Setting>

    override fun findByKey(key: String): List<Setting>

    override fun findByValue(value: String): List<Setting>

    override fun findByRemark(remark: String): List<Setting>

    override fun findByType(type: String): List<Setting>

    override fun findByData(data: String): List<Setting>

    override fun findByOrder(order: String): List<Setting>

    override fun findByDate(date: String): List<Setting>
}