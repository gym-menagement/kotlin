package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Rockerusage
import com.gowoobro.gymspring.entity.RockerusageCreateRequest
import com.gowoobro.gymspring.entity.RockerusageUpdateRequest
import com.gowoobro.gymspring.service.RockerusageService
import com.gowoobro.gymspring.entity.RockerusageResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.math.BigDecimal
import com.gowoobro.gymspring.enums.rockerusage.Status


@RestController
@RequestMapping("/api/rockerusage")
class RockerusageController(private val rockerusageService: RockerusageService) {

    @GetMapping
    fun getRockerusages(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<RockerusageResponse>> {
        val result = rockerusageService.findAll(page, pageSize)
        val responsePage = result.map { RockerusageResponse.from(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getRockerusage(@PathVariable id: Long): ResponseEntity<RockerusageResponse> {
        val result = rockerusageService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(RockerusageResponse.from(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/rocker")
    fun getRockerusageByRocker(@RequestParam rocker: Long): ResponseEntity<List<RockerusageResponse>> {
        val result = rockerusageService.findByRocker(rocker)
        return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
    }

    @GetMapping("/search/user")
    fun getRockerusageByUser(@RequestParam user: Long): ResponseEntity<List<RockerusageResponse>> {
        val result = rockerusageService.findByUser(user)
        return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
    }

    @GetMapping("/search/membership")
    fun getRockerusageByMembership(@RequestParam membership: Long): ResponseEntity<List<RockerusageResponse>> {
        val result = rockerusageService.findByMembership(membership)
        return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
    }

    @GetMapping("/search/startdate")
    fun getRockerusageByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<RockerusageResponse>> {
        val result = rockerusageService.findByStartdate(startdate)
        return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
    }

    @GetMapping("/search/enddate")
    fun getRockerusageByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<RockerusageResponse>> {
        val result = rockerusageService.findByEnddate(enddate)
        return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
    }

    @GetMapping("/search/status")
    fun getRockerusageByStatus(@RequestParam status: Status): ResponseEntity<List<RockerusageResponse>> {
        val result = rockerusageService.findByStatus(status)
        return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
    }

    @GetMapping("/search/deposit")
    fun getRockerusageByDeposit(@RequestParam deposit: BigDecimal): ResponseEntity<List<RockerusageResponse>> {
        val result = rockerusageService.findByDeposit(deposit)
        return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
    }

    @GetMapping("/search/monthlyfee")
    fun getRockerusageByMonthlyfee(@RequestParam monthlyfee: BigDecimal): ResponseEntity<List<RockerusageResponse>> {
        val result = rockerusageService.findByMonthlyfee(monthlyfee)
        return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
    }

    @GetMapping("/search/note")
    fun getRockerusageByNote(@RequestParam note: String): ResponseEntity<List<RockerusageResponse>> {
        val result = rockerusageService.findByNote(note)
        return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
    }

    @GetMapping("/search/assignedby")
    fun getRockerusageByAssignedby(@RequestParam assignedby: Long): ResponseEntity<List<RockerusageResponse>> {
        val result = rockerusageService.findByAssignedby(assignedby)
        return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
    }

    @GetMapping("/search/assigneddate")
    fun getRockerusageByAssigneddate(@RequestParam assigneddate: LocalDateTime): ResponseEntity<List<RockerusageResponse>> {
        val result = rockerusageService.findByAssigneddate(assigneddate)
        return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
    }

    @GetMapping("/search/date")
    fun getRockerusageByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<RockerusageResponse>> {
        val result = rockerusageService.findByDate(date)
        return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = rockerusageService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createRockerusage(@RequestBody request: RockerusageCreateRequest): ResponseEntity<RockerusageResponse> {
        return try {
            val result = rockerusageService.create(request)
            ResponseEntity.ok(RockerusageResponse.from(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createRockerusages(@RequestBody requests: List<RockerusageCreateRequest>): ResponseEntity<List<RockerusageResponse>> {
        return try {
            val result = rockerusageService.createBatch(requests)
            return ResponseEntity.ok(result.map { RockerusageResponse.from(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateRockerusage(
        @PathVariable id: Long,
        @RequestBody request: RockerusageUpdateRequest
    ): ResponseEntity<RockerusageResponse> {
        val updatedRequest = request.copy(id = id)
        val result = rockerusageService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(RockerusageResponse.from(result))
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