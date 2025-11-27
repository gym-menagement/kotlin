package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Rockerusage
import com.gowoobro.gymspring.entity.RockerusageCreateRequest
import com.gowoobro.gymspring.entity.RockerusageUpdateRequest
import com.gowoobro.gymspring.entity.RockerusagePatchRequest
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
    fun getRockerusages(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) rocker: Long?,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) usehealth: Long?,
        @RequestParam(required = false) startstartdate: LocalDateTime?,
        @RequestParam(required = false) endstartdate: LocalDateTime?,
        @RequestParam(required = false) startenddate: LocalDateTime?,
        @RequestParam(required = false) endenddate: LocalDateTime?,
        @RequestParam(required = false) status: Int?,
        @RequestParam(required = false) deposit: BigDecimal?,
        @RequestParam(required = false) monthlyfee: BigDecimal?,
        @RequestParam(required = false) note: String?,
        @RequestParam(required = false) assignedby: Long?,
        @RequestParam(required = false) startassigneddate: LocalDateTime?,
        @RequestParam(required = false) endassigneddate: LocalDateTime?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || rocker != null || user != null || usehealth != null || startstartdate != null || endstartdate != null || startenddate != null || endenddate != null || status != null || deposit != null || monthlyfee != null || note != null || assignedby != null || startassigneddate != null || endassigneddate != null || startdate != null || enddate != null || false) {
            var filtered = rockerusageService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (rocker != null) {
                filtered = filtered.filter { it.rockerId == rocker }
            }
            if (user != null) {
                filtered = filtered.filter { it.userId == user }
            }
            if (usehealth != null) {
                filtered = filtered.filter { it.usehealthId == usehealth }
            }
            if (startstartdate != null || endstartdate != null) {
                filtered = filtered.filter { filterByDateRange(it.startdate, startstartdate, endstartdate) }
            }
            if (startenddate != null || endenddate != null) {
                filtered = filtered.filter { filterByDateRange(it.enddate, startenddate, endenddate) }
            }
            if (status != null) {
                filtered = filtered.filter { it.status.ordinal == status }
            }
            if (deposit != null) {
                filtered = filtered.filter { it.deposit == deposit }
            }
            if (monthlyfee != null) {
                filtered = filtered.filter { it.monthlyfee == monthlyfee }
            }
            if (note != null) {
                filtered = filtered.filter { it.note == note }
            }
            if (assignedby != null) {
                filtered = filtered.filter { it.assignedbyId == assignedby }
            }
            if (startassigneddate != null || endassigneddate != null) {
                filtered = filtered.filter { filterByDateRange(it.assigneddate, startassigneddate, endassigneddate) }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            rockerusageService.findAll(0, Int.MAX_VALUE).content
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

    @GetMapping("/search/usehealth")
    fun getRockerusageByUsehealth(@RequestParam usehealth: Long): ResponseEntity<List<RockerusageResponse>> {
        val res = rockerusageService.findByUsehealth(usehealth)
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

    @PatchMapping("/{id}")
    fun patchRockerusage(
        @PathVariable id: Long,
        @RequestBody request: RockerusagePatchRequest
    ): ResponseEntity<RockerusageResponse> {
        val patchRequest = request.copy(id = id)
        val res = rockerusageService.patch(patchRequest)
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