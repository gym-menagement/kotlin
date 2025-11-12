package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Rocker
import com.gowoobro.gymspring.entity.RockerCreateRequest
import com.gowoobro.gymspring.entity.RockerUpdateRequest
import com.gowoobro.gymspring.service.RockerService
import com.gowoobro.gymspring.entity.RockerResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.rocker.Available


@RestController
@RequestMapping("/api/rocker")
class RockerController(private val rockerService: RockerService) {

    @GetMapping
    fun getRockers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<RockerResponse>> {
        val result = rockerService.findAll(page, pageSize)
        val responsePage = result.map { RockerResponse.from(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getRocker(@PathVariable id: Long): ResponseEntity<RockerResponse> {
        val result = rockerService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(RockerResponse.from(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/group")
    fun getRockerByGroup(@RequestParam group: Long): ResponseEntity<List<RockerResponse>> {
        val result = rockerService.findByGroup(group)
        return ResponseEntity.ok(result.map { RockerResponse.from(it) } )
    }

    @GetMapping("/search/name")
    fun getRockerByName(@RequestParam name: String): ResponseEntity<List<RockerResponse>> {
        val result = rockerService.findByName(name)
        return ResponseEntity.ok(result.map { RockerResponse.from(it) } )
    }

    @GetMapping("/search/available")
    fun getRockerByAvailable(@RequestParam available: Available): ResponseEntity<List<RockerResponse>> {
        val result = rockerService.findByAvailable(available)
        return ResponseEntity.ok(result.map { RockerResponse.from(it) } )
    }

    @GetMapping("/search/date")
    fun getRockerByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<RockerResponse>> {
        val result = rockerService.findByDate(date)
        return ResponseEntity.ok(result.map { RockerResponse.from(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = rockerService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createRocker(@RequestBody request: RockerCreateRequest): ResponseEntity<RockerResponse> {
        return try {
            val result = rockerService.create(request)
            ResponseEntity.ok(RockerResponse.from(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createRockers(@RequestBody requests: List<RockerCreateRequest>): ResponseEntity<List<RockerResponse>> {
        return try {
            val result = rockerService.createBatch(requests)
            return ResponseEntity.ok(result.map { RockerResponse.from(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateRocker(
        @PathVariable id: Long,
        @RequestBody request: RockerUpdateRequest
    ): ResponseEntity<RockerResponse> {
        val updatedRequest = request.copy(id = id)
        val result = rockerService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(RockerResponse.from(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteRocker(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = rockerService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteRockers(@RequestBody entities: List<Rocker>): ResponseEntity<Map<String, Boolean>> {
        val success = rockerService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}