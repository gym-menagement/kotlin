package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Systemlog
import com.gowoobro.gymspring.entity.SystemlogCreateRequest
import com.gowoobro.gymspring.entity.SystemlogUpdateRequest
import com.gowoobro.gymspring.service.SystemlogService
import com.gowoobro.gymspring.entity.SystemlogResponse
import com.gowoobro.gymspring.enums.systemlog.Type
import com.gowoobro.gymspring.enums.systemlog.Result

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/systemlog")
class SystemlogController(
    private val systemlogService: SystemlogService) {

    private fun toResponse(systemlog: Systemlog): SystemlogResponse {
        return SystemlogResponse.from(systemlog)
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
    fun getSystemlogs(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) type: Type?,
        @RequestParam(required = false) content: String?,
        @RequestParam(required = false) result: Result?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (type != null || content != null || result != null || startdate != null || enddate != null || false) {
            var filtered = systemlogService.findAll(0, Int.MAX_VALUE).content
            if (type != null) {
                filtered = filtered.filter { it.type == type }
            }
            if (content != null) {
                filtered = filtered.filter { it.content == content }
            }
            if (result != null) {
                filtered = filtered.filter { it.result == result }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            systemlogService.findAll(0, Int.MAX_VALUE).content
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
    fun getSystemlog(@PathVariable id: Long): ResponseEntity<SystemlogResponse> {
        val res = systemlogService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/type")
    fun getSystemlogByType(@RequestParam type: Type): ResponseEntity<List<SystemlogResponse>> {
        val res = systemlogService.findByType(type)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/content")
    fun getSystemlogByContent(@RequestParam content: String): ResponseEntity<List<SystemlogResponse>> {
        val res = systemlogService.findByContent(content)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/result")
    fun getSystemlogByResult(@RequestParam result: Result): ResponseEntity<List<SystemlogResponse>> {
        val res = systemlogService.findByResult(result)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getSystemlogByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<SystemlogResponse>> {
        val res = systemlogService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = systemlogService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createSystemlog(@RequestBody request: SystemlogCreateRequest): ResponseEntity<SystemlogResponse> {
        return try {
            val res = systemlogService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createSystemlogs(@RequestBody requests: List<SystemlogCreateRequest>): ResponseEntity<List<SystemlogResponse>> {
        return try {
            val res = systemlogService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateSystemlog(
        @PathVariable id: Long,
        @RequestBody request: SystemlogUpdateRequest
    ): ResponseEntity<SystemlogResponse> {
        val updatedRequest = request.copy(id = id)
        val res = systemlogService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteSystemlog(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = systemlogService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteSystemlogs(@RequestBody entities: List<Systemlog>): ResponseEntity<Map<String, Boolean>> {
        val success = systemlogService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}