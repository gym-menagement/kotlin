package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Usehealthusage
import com.gowoobro.gymspring.entity.UsehealthusageCreateRequest
import com.gowoobro.gymspring.entity.UsehealthusageUpdateRequest
import com.gowoobro.gymspring.entity.UsehealthusagePatchRequest
import com.gowoobro.gymspring.service.UsehealthusageService
import com.gowoobro.gymspring.entity.UsehealthusageResponse
import com.gowoobro.gymspring.enums.usehealthusage.Type

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/usehealthusage")
class UsehealthusageController(
    private val usehealthusageService: UsehealthusageService) {

    private fun toResponse(usehealthusage: Usehealthusage): UsehealthusageResponse {
        return UsehealthusageResponse.from(usehealthusage)
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
    fun getUsehealthusages(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) usehealth: Long?,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) attendance: Long?,
        @RequestParam(required = false) type: Int?,
        @RequestParam(required = false) usedcount: Int?,
        @RequestParam(required = false) remainingcount: Int?,
        @RequestParam(required = false) startcheckintime: LocalDateTime?,
        @RequestParam(required = false) endcheckintime: LocalDateTime?,
        @RequestParam(required = false) startcheckouttime: LocalDateTime?,
        @RequestParam(required = false) endcheckouttime: LocalDateTime?,
        @RequestParam(required = false) duration: Int?,
        @RequestParam(required = false) note: String?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || usehealth != null || user != null || attendance != null || type != null || usedcount != null || remainingcount != null || startcheckintime != null || endcheckintime != null || startcheckouttime != null || endcheckouttime != null || duration != null || note != null || startdate != null || enddate != null || false) {
            var filtered = usehealthusageService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (usehealth != null) {
                filtered = filtered.filter { it.usehealthId == usehealth }
            }
            if (user != null) {
                filtered = filtered.filter { it.userId == user }
            }
            if (attendance != null) {
                filtered = filtered.filter { it.attendanceId == attendance }
            }
            if (type != null) {
                filtered = filtered.filter { it.type.ordinal == type }
            }
            if (usedcount != null) {
                filtered = filtered.filter { it.usedcount == usedcount }
            }
            if (remainingcount != null) {
                filtered = filtered.filter { it.remainingcount == remainingcount }
            }
            if (startcheckintime != null || endcheckintime != null) {
                filtered = filtered.filter { filterByDateRange(it.checkintime, startcheckintime, endcheckintime) }
            }
            if (startcheckouttime != null || endcheckouttime != null) {
                filtered = filtered.filter { filterByDateRange(it.checkouttime, startcheckouttime, endcheckouttime) }
            }
            if (duration != null) {
                filtered = filtered.filter { it.duration == duration }
            }
            if (note != null) {
                filtered = filtered.filter { it.note == note }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            usehealthusageService.findAll(0, Int.MAX_VALUE).content
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
    fun getUsehealthusage(@PathVariable id: Long): ResponseEntity<UsehealthusageResponse> {
        val res = usehealthusageService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getUsehealthusageByGym(@RequestParam gym: Long): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/usehealth")
    fun getUsehealthusageByUsehealth(@RequestParam usehealth: Long): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByUsehealth(usehealth)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/user")
    fun getUsehealthusageByUser(@RequestParam user: Long): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/attendance")
    fun getUsehealthusageByAttendance(@RequestParam attendance: Long): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByAttendance(attendance)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getUsehealthusageByType(@RequestParam type: Type): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByType(type)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/usedcount")
    fun getUsehealthusageByUsedcount(@RequestParam usedcount: Int): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByUsedcount(usedcount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/remainingcount")
    fun getUsehealthusageByRemainingcount(@RequestParam remainingcount: Int): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByRemainingcount(remainingcount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/checkintime")
    fun getUsehealthusageByCheckintime(@RequestParam checkintime: LocalDateTime): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByCheckintime(checkintime)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/checkouttime")
    fun getUsehealthusageByCheckouttime(@RequestParam checkouttime: LocalDateTime): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByCheckouttime(checkouttime)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/duration")
    fun getUsehealthusageByDuration(@RequestParam duration: Int): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByDuration(duration)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/note")
    fun getUsehealthusageByNote(@RequestParam note: String): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByNote(note)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getUsehealthusageByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = usehealthusageService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createUsehealthusage(@RequestBody request: UsehealthusageCreateRequest): ResponseEntity<UsehealthusageResponse> {
        return try {
            val res = usehealthusageService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createUsehealthusages(@RequestBody requests: List<UsehealthusageCreateRequest>): ResponseEntity<List<UsehealthusageResponse>> {
        return try {
            val res = usehealthusageService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateUsehealthusage(
        @PathVariable id: Long,
        @RequestBody request: UsehealthusageUpdateRequest
    ): ResponseEntity<UsehealthusageResponse> {
        val updatedRequest = request.copy(id = id)
        val res = usehealthusageService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchUsehealthusage(
        @PathVariable id: Long,
        @RequestBody request: UsehealthusagePatchRequest
    ): ResponseEntity<UsehealthusageResponse> {
        val patchRequest = request.copy(id = id)
        val res = usehealthusageService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUsehealthusage(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = usehealthusageService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteUsehealthusages(@RequestBody entities: List<Usehealthusage>): ResponseEntity<Map<String, Boolean>> {
        val success = usehealthusageService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}