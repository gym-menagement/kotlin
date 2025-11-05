package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Setting
import com.gowoobro.gymspring.entity.SettingCreateRequest
import com.gowoobro.gymspring.entity.SettingUpdateRequest
import com.gowoobro.gymspring.service.SettingService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/setting")
class SettingController(private val settingService: SettingService) {

    @GetMapping
    fun getSettings(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Setting>> {
        val result = settingService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getSetting(@PathVariable id: Long): ResponseEntity<Setting> {
        val result = settingService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/id")
    fun getSettingById(@RequestParam id: String): ResponseEntity<List<Setting>> {
        val result = settingService.findById(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/category")
    fun getSettingByCategory(@RequestParam category: String): ResponseEntity<List<Setting>> {
        val result = settingService.findByCategory(category)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/name")
    fun getSettingByName(@RequestParam name: String): ResponseEntity<List<Setting>> {
        val result = settingService.findByName(name)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/key")
    fun getSettingByKey(@RequestParam key: String): ResponseEntity<List<Setting>> {
        val result = settingService.findByKey(key)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/value")
    fun getSettingByValue(@RequestParam value: String): ResponseEntity<List<Setting>> {
        val result = settingService.findByValue(value)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/remark")
    fun getSettingByRemark(@RequestParam remark: String): ResponseEntity<List<Setting>> {
        val result = settingService.findByRemark(remark)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/type")
    fun getSettingByType(@RequestParam type: String): ResponseEntity<List<Setting>> {
        val result = settingService.findByType(type)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/data")
    fun getSettingByData(@RequestParam data: String): ResponseEntity<List<Setting>> {
        val result = settingService.findByData(data)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/order")
    fun getSettingByOrder(@RequestParam order: String): ResponseEntity<List<Setting>> {
        val result = settingService.findByOrder(order)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getSettingByDate(@RequestParam date: String): ResponseEntity<List<Setting>> {
        val result = settingService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = settingService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createSetting(@RequestBody request: SettingCreateRequest): ResponseEntity<Setting> {
        return try {
            val result = settingService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createSettings(@RequestBody requests: List<SettingCreateRequest>): ResponseEntity<List<Setting>> {
        return try {
            val result = settingService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateSetting(
        @PathVariable id: Long,
        @RequestBody request: SettingUpdateRequest
    ): ResponseEntity<Setting> {
        val updatedRequest = request.copy(id = id)
        val result = settingService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteSetting(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = settingService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteSettings(@RequestBody entities: List<Setting>): ResponseEntity<Map<String, Boolean>> {
        val success = settingService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}