package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Setting
import com.gowoobro.gymspring.entity.SettingCreateRequest
import com.gowoobro.gymspring.entity.SettingUpdateRequest
import com.gowoobro.gymspring.entity.SettingPatchRequest
import com.gowoobro.gymspring.repository.SettingRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.setting.Type


@Service
@Transactional
class SettingService(private val settingRepository: SettingRepository) {

    fun findAll(page: Int = 0, pagesize: Int = 10): Page<Setting> {
        val pageable: Pageable = PageRequest.of(page, pagesize)
        return settingRepository.findAll(pageable)
    }

    fun findById(id: Long): Setting? {
        return settingRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return settingRepository.count()
    }


    fun findByCategory(category: String): List<Setting> {
        return settingRepository.findByCategory(category)
    }

    fun findByName(name: String): List<Setting> {
        return settingRepository.findByName(name)
    }

    fun findByKey(key: String): List<Setting> {
        return settingRepository.findByKey(key)
    }

    fun findByValue(value: String): List<Setting> {
        return settingRepository.findByValue(value)
    }

    fun findByRemark(remark: String): List<Setting> {
        return settingRepository.findByRemark(remark)
    }

    fun findByType(type: Type): List<Setting> {
        return settingRepository.findByType(type)
    }

    fun findByData(data: String): List<Setting> {
        return settingRepository.findByData(data)
    }

    fun findByOrder(order: Int): List<Setting> {
        return settingRepository.findByOrder(order)
    }

    fun findByDate(date: LocalDateTime): List<Setting> {
        return settingRepository.findByDate(date)
    }


    fun create(request: SettingCreateRequest): Setting {
        val entity = Setting(
            category = request.category,
            name = request.name,
            key = request.key,
            value = request.value,
            remark = request.remark,
            type = request.type,
            data = request.data,
            order = request.order,
            date = request.date,
        )
        return settingRepository.save(entity)
    }

    fun createBatch(requests: List<SettingCreateRequest>): List<Setting> {
        val entities = requests.map { request ->
            Setting(
                category = request.category,
                name = request.name,
                key = request.key,
                value = request.value,
                remark = request.remark,
                type = request.type,
                data = request.data,
                order = request.order,
                date = request.date,
            )
        }
        return settingRepository.saveAll(entities)
    }

    fun update(request: SettingUpdateRequest): Setting? {
        val existing = settingRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            category = request.category,
            name = request.name,
            key = request.key,
            value = request.value,
            remark = request.remark,
            type = request.type,
            data = request.data,
            order = request.order,
            date = request.date,
        )
        return settingRepository.save(updated)
    }

    fun delete(entity: Setting): Boolean {
        return try {
            settingRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            settingRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Setting>): Boolean {
        return try {
            settingRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: SettingPatchRequest): Setting? {
        val existing = settingRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            category = request.category ?: existing.category,
            name = request.name ?: existing.name,
            key = request.key ?: existing.key,
            value = request.value ?: existing.value,
            remark = request.remark ?: existing.remark,
            type = request.type ?: existing.type,
            data = request.data ?: existing.data,
            order = request.order ?: existing.order,
            date = request.date ?: existing.date,
        )
        return settingRepository.save(updated)
    }
}