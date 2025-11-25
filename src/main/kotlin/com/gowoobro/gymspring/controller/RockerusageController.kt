package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Rockerusage
import com.gowoobro.gymspring.entity.RockerusageCreateRequest
import com.gowoobro.gymspring.entity.RockerusageUpdateRequest
import com.gowoobro.gymspring.service.RockerusageService
import com.gowoobro.gymspring.entity.RockerusageResponse
import com.gowoobro.gymspring.enums.rockerusage.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.math.BigDecimal

@RestController
@RequestMapping("/api/rockerusage")
class RockerusageController(
    private val rockerusageService: RockerusageService) {

    private fun toResponse(rockerusage: Rockerusage): RockerusageResponse {
        return RockerusageResponse.from(rockerusage)
    }

    @GetMapping
    fun getRockerusages(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<RockerusageResponse>> {
        val res = rockerusageService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getRockerusage(@PathVariable id: Long): ResponseEntity<RockerusageResponse> {
        val res = rockerusageService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getRockerusageByGym(@RequestParam gym: Long): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/rocker")
    fun getRockerusageByRocker(@RequestParam rocker: Long): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByRocker(rocker)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/user")
    fun getRockerusageByUser(@RequestParam user: Long): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/membership")
    fun getRockerusageByMembership(@RequestParam membership: Long): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByMembership(membership)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/startdate")
    fun getRockerusageByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByStartdate(startdate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/enddate")
    fun getRockerusageByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByEnddate(enddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getRockerusageByStatus(@RequestParam status: Status): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/deposit")
    fun getRockerusageByDeposit(@RequestParam deposit: BigDecimal): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByDeposit(deposit)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/monthlyfee")
    fun getRockerusageByMonthlyfee(@RequestParam monthlyfee: BigDecimal): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByMonthlyfee(monthlyfee)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/note")
    fun getRockerusageByNote(@RequestParam note: String): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByNote(note)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/assignedby")
    fun getRockerusageByAssignedby(@RequestParam assignedby: Long): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByAssignedby(assignedby)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/assigneddate")
    fun getRockerusageByAssigneddate(@RequestParam assigneddate: LocalDateTime): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByAssigneddate(assigneddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getRockerusageByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = rockerusageService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createRockerusage(@RequestBody request: RockerusageCreateRequest): ResponseEntity<RockerusageResponse> {
        return try {
            val res = rockerusageService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createRockerusages(@RequestBody requests: List<RockerusageCreateRequest>): ResponseEntity<List<RockerusageResponse>> {
        return try {
            val res = rockerusageService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
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
        val res = rockerusageService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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