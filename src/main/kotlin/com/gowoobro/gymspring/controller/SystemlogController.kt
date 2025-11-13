package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Systemlog
import com.gowoobro.gymspring.entity.SystemlogCreateRequest
import com.gowoobro.gymspring.entity.SystemlogUpdateRequest
import com.gowoobro.gymspring.service.SystemlogService
import com.gowoobro.gymspring.entity.SystemlogResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.systemlog.Type
import com.gowoobro.gymspring.enums.systemlog.Result


@RestController
@RequestMapping("/api/systemlog")
class SystemlogController(
    private val systemlogService: SystemlogService) {

    private fun toResponse(systemlog: Systemlog):
    SystemlogResponse {
        
        return SystemlogResponse.from(systemlog)
    }

    @GetMapping
    fun getSystemlogs(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<SystemlogResponse>> {
        val result = systemlogService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getSystemlog(@PathVariable id: Long): ResponseEntity<SystemlogResponse> {
        val result = systemlogService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/type")
    fun getSystemlogByType(@RequestParam type: Type): ResponseEntity<List<SystemlogResponse>> {
        val result = systemlogService.findByType(type)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/content")
    fun getSystemlogByContent(@RequestParam content: String): ResponseEntity<List<SystemlogResponse>> {
        val result = systemlogService.findByContent(content)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/result")
    fun getSystemlogByResult(@RequestParam result: Result): ResponseEntity<List<SystemlogResponse>> {
        val result = systemlogService.findByResult(result)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getSystemlogByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<SystemlogResponse>> {
        val result = systemlogService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = systemlogService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createSystemlog(@RequestBody request: SystemlogCreateRequest): ResponseEntity<SystemlogResponse> {
        return try {
            val result = systemlogService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createSystemlogs(@RequestBody requests: List<SystemlogCreateRequest>): ResponseEntity<List<SystemlogResponse>> {
        return try {
            val result = systemlogService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
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
        val result = systemlogService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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