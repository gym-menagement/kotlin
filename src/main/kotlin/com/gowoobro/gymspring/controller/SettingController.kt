package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Setting
import com.gowoobro.gymspring.entity.SettingCreateRequest
import com.gowoobro.gymspring.entity.SettingUpdateRequest
import com.gowoobro.gymspring.service.SettingService
import com.gowoobro.gymspring.entity.SettingResponse
import com.gowoobro.gymspring.enums.setting.Type

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/setting")
class SettingController(
    private val settingService: SettingService) {

    private fun toResponse(setting: Setting): SettingResponse {
        return SettingResponse.from(setting)
    }

    private fun filterByDateRange(
        value: LocalDateTime?,
        startRange: LocalDateTime?,
        endRange: LocalDateTime?
    ): Boolean {
        if (value == null) return false
        return when {
            startRange != null && endRange != null -> value in startRange..endRange
            startRange != null -> value >= startRange
            endRange != null -> value <= endRange
            else -> true
        }
    }

    @GetMapping
    fun getSettings(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) key: String?,
        @RequestParam(required = false) value: String?,
        @RequestParam(required = false) remark: String?,
        @RequestParam(required = false) type: Int?,
        @RequestParam(required = false) data: String?,
        @RequestParam(required = false) order: Int?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (category != null || name != null || key != null || value != null || remark != null || type != null || data != null || order != null || startdate != null || enddate != null || false) {
            var filtered = settingService.findAll(0, Int.MAX_VALUE).content
            if (category != null) {
                filtered = filtered.filter { it.category == category }
            }
            if (name != null) {
                filtered = filtered.filter { it.name == name }
            }
            if (key != null) {
                filtered = filtered.filter { it.key == key }
            }
            if (value != null) {
                filtered = filtered.filter { it.value == value }
            }
            if (remark != null) {
                filtered = filtered.filter { it.remark == remark }
            }
            if (type != null) {
                filtered = filtered.filter { it.type.ordinal == type }
            }
            if (data != null) {
                filtered = filtered.filter { it.data == data }
            }
            if (order != null) {
                filtered = filtered.filter { it.order == order }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            settingService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pageSize > 0) (totalElements + pageSize - 1) / pageSize else 1
        val startIndex = page * pageSize
        val endIndex = minOf(startIndex + pageSize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pageSize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getSetting(@PathVariable id: Long): ResponseEntity<SettingResponse> {
        val res = settingService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/category")
    fun getSettingByCategory(@RequestParam category: String): ResponseEntity<List<SettingResponse>> {
        val res = settingService.findByCategory(category)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getSettingByName(@RequestParam name: String): ResponseEntity<List<SettingResponse>> {
        val res = settingService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/key")
    fun getSettingByKey(@RequestParam key: String): ResponseEntity<List<SettingResponse>> {
        val res = settingService.findByKey(key)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/value")
    fun getSettingByValue(@RequestParam value: String): ResponseEntity<List<SettingResponse>> {
        val res = settingService.findByValue(value)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/remark")
    fun getSettingByRemark(@RequestParam remark: String): ResponseEntity<List<SettingResponse>> {
        val res = settingService.findByRemark(remark)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getSettingByType(@RequestParam type: Type): ResponseEntity<List<SettingResponse>> {
        val res = settingService.findByType(type)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/data")
    fun getSettingByData(@RequestParam data: String): ResponseEntity<List<SettingResponse>> {
        val res = settingService.findByData(data)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/order")
    fun getSettingByOrder(@RequestParam order: Int): ResponseEntity<List<SettingResponse>> {
        val res = settingService.findByOrder(order)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getSettingByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<SettingResponse>> {
        val res = settingService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = settingService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createSetting(@RequestBody request: SettingCreateRequest): ResponseEntity<SettingResponse> {
        return try {
            val res = settingService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createSettings(@RequestBody requests: List<SettingCreateRequest>): ResponseEntity<List<SettingResponse>> {
        return try {
            val res = settingService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
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
        val res = settingService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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