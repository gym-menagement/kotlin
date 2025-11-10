package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Rockerusage
import com.gowoobro.gymspring.entity.RockerusageCreateRequest
import com.gowoobro.gymspring.entity.RockerusageUpdateRequest
import com.gowoobro.gymspring.service.RockerusageService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.math.BigDecimal


@RestController
@RequestMapping("/api/rockerusage")
class RockerusageController(private val rockerusageService: RockerusageService) {

    @GetMapping
    fun getRockerusages(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Rockerusage>> {
        val result = rockerusageService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getRockerusage(@PathVariable id: Long): ResponseEntity<Rockerusage> {
        val result = rockerusageService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/rocker")
    fun getRockerusageByRocker(@RequestParam rocker: Long): ResponseEntity<List<Rockerusage>> {
        val result = rockerusageService.findByRocker(rocker)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/user")
    fun getRockerusageByUser(@RequestParam user: Long): ResponseEntity<List<Rockerusage>> {
        val result = rockerusageService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/membership")
    fun getRockerusageByMembership(@RequestParam membership: Long): ResponseEntity<List<Rockerusage>> {
        val result = rockerusageService.findByMembership(membership)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/startdate")
    fun getRockerusageByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<Rockerusage>> {
        val result = rockerusageService.findByStartdate(startdate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/enddate")
    fun getRockerusageByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<Rockerusage>> {
        val result = rockerusageService.findByEnddate(enddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/status")
    fun getRockerusageByStatus(@RequestParam status: Int): ResponseEntity<List<Rockerusage>> {
        val result = rockerusageService.findByStatus(status)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/deposit")
    fun getRockerusageByDeposit(@RequestParam deposit: BigDecimal): ResponseEntity<List<Rockerusage>> {
        val result = rockerusageService.findByDeposit(deposit)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/monthlyfee")
    fun getRockerusageByMonthlyfee(@RequestParam monthlyfee: BigDecimal): ResponseEntity<List<Rockerusage>> {
        val result = rockerusageService.findByMonthlyfee(monthlyfee)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/note")
    fun getRockerusageByNote(@RequestParam note: String): ResponseEntity<List<Rockerusage>> {
        val result = rockerusageService.findByNote(note)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/assignedby")
    fun getRockerusageByAssignedby(@RequestParam assignedby: Long): ResponseEntity<List<Rockerusage>> {
        val result = rockerusageService.findByAssignedby(assignedby)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/assigneddate")
    fun getRockerusageByAssigneddate(@RequestParam assigneddate: LocalDateTime): ResponseEntity<List<Rockerusage>> {
        val result = rockerusageService.findByAssigneddate(assigneddate)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = rockerusageService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createRockerusage(@RequestBody request: RockerusageCreateRequest): ResponseEntity<Rockerusage> {
        return try {
            val result = rockerusageService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createRockerusages(@RequestBody requests: List<RockerusageCreateRequest>): ResponseEntity<List<Rockerusage>> {
        return try {
            val result = rockerusageService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateRockerusage(
        @PathVariable id: Long,
        @RequestBody request: RockerusageUpdateRequest
    ): ResponseEntity<Rockerusage> {
        val updatedRequest = request.copy(id = id)
        val result = rockerusageService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteRockerusage(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = rockerusageService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteRockerusages(@RequestBody entities: List<Rockerusage>): ResponseEntity<Map<String, Boolean>> {
        val success = rockerusageService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}