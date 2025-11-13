package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Setting
import com.gowoobro.gymspring.entity.SettingCreateRequest
import com.gowoobro.gymspring.entity.SettingUpdateRequest
import com.gowoobro.gymspring.service.SettingService
import com.gowoobro.gymspring.entity.SettingResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.setting.Type


@RestController
@RequestMapping("/api/setting")
class SettingController(
    private val settingService: SettingService) {

    private fun toResponse(setting: Setting):
    SettingResponse {
        
        return SettingResponse.from(setting)
    }

    @GetMapping
    fun getSettings(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<SettingResponse>> {
        val result = settingService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getSetting(@PathVariable id: Long): ResponseEntity<SettingResponse> {
        val result = settingService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/category")
    fun getSettingByCategory(@RequestParam category: String): ResponseEntity<List<SettingResponse>> {
        val result = settingService.findByCategory(category)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getSettingByName(@RequestParam name: String): ResponseEntity<List<SettingResponse>> {
        val result = settingService.findByName(name)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/key")
    fun getSettingByKey(@RequestParam key: String): ResponseEntity<List<SettingResponse>> {
        val result = settingService.findByKey(key)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/value")
    fun getSettingByValue(@RequestParam value: String): ResponseEntity<List<SettingResponse>> {
        val result = settingService.findByValue(value)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/remark")
    fun getSettingByRemark(@RequestParam remark: String): ResponseEntity<List<SettingResponse>> {
        val result = settingService.findByRemark(remark)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getSettingByType(@RequestParam type: Type): ResponseEntity<List<SettingResponse>> {
        val result = settingService.findByType(type)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/data")
    fun getSettingByData(@RequestParam data: String): ResponseEntity<List<SettingResponse>> {
        val result = settingService.findByData(data)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/order")
    fun getSettingByOrder(@RequestParam order: Int): ResponseEntity<List<SettingResponse>> {
        val result = settingService.findByOrder(order)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getSettingByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<SettingResponse>> {
        val result = settingService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = settingService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createSetting(@RequestBody request: SettingCreateRequest): ResponseEntity<SettingResponse> {
        return try {
            val result = settingService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createSettings(@RequestBody requests: List<SettingCreateRequest>): ResponseEntity<List<SettingResponse>> {
        return try {
            val result = settingService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateSetting(
        @PathVariable id: Long,
        @RequestBody request: SettingUpdateRequest
    ): ResponseEntity<SettingResponse> {
        val updatedRequest = request.copy(id = id)
        val result = settingService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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