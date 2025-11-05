package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Rocker
import com.gowoobro.gymspring.entity.RockerCreateRequest
import com.gowoobro.gymspring.entity.RockerUpdateRequest
import com.gowoobro.gymspring.service.RockerService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rocker")
class RockerController(private val rockerService: RockerService) {

    @GetMapping
    fun getRockers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Rocker>> {
        val result = rockerService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getRocker(@PathVariable id: Long): ResponseEntity<Rocker> {
        val result = rockerService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/id")
    fun getRockerById(@RequestParam id: String): ResponseEntity<List<Rocker>> {
        val result = rockerService.findById(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/group")
    fun getRockerByGroup(@RequestParam group: String): ResponseEntity<List<Rocker>> {
        val result = rockerService.findByGroup(group)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/name")
    fun getRockerByName(@RequestParam name: String): ResponseEntity<List<Rocker>> {
        val result = rockerService.findByName(name)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/available")
    fun getRockerByAvailable(@RequestParam available: String): ResponseEntity<List<Rocker>> {
        val result = rockerService.findByAvailable(available)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getRockerByDate(@RequestParam date: String): ResponseEntity<List<Rocker>> {
        val result = rockerService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = rockerService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createRocker(@RequestBody request: RockerCreateRequest): ResponseEntity<Rocker> {
        return try {
            val result = rockerService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createRockers(@RequestBody requests: List<RockerCreateRequest>): ResponseEntity<List<Rocker>> {
        return try {
            val result = rockerService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateRocker(
        @PathVariable id: Long,
        @RequestBody request: RockerUpdateRequest
    ): ResponseEntity<Rocker> {
        val updatedRequest = request.copy(id = id)
        val result = rockerService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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