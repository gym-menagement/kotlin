package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Usehealth
import com.gowoobro.gymspring.entity.UsehealthCreateRequest
import com.gowoobro.gymspring.entity.UsehealthUpdateRequest
import com.gowoobro.gymspring.service.UsehealthService
import com.gowoobro.gymspring.entity.UsehealthResponse
import com.gowoobro.gymspring.enums.usehealth.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/usehealth")
class UsehealthController(
    private val usehealthService: UsehealthService) {

    private fun toResponse(usehealth: Usehealth): UsehealthResponse {
        return UsehealthResponse.from(usehealth)
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
    fun getUsehealths(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) order: Long?,
        @RequestParam(required = false) health: Long?,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) rocker: Long?,
        @RequestParam(required = false) term: Long?,
        @RequestParam(required = false) discount: Long?,
        @RequestParam(required = false) startstartday: LocalDateTime?,
        @RequestParam(required = false) endstartday: LocalDateTime?,
        @RequestParam(required = false) startendday: LocalDateTime?,
        @RequestParam(required = false) endendday: LocalDateTime?,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) status: Int?,
        @RequestParam(required = false) totalcount: Int?,
        @RequestParam(required = false) usedcount: Int?,
        @RequestParam(required = false) remainingcount: Int?,
        @RequestParam(required = false) qrcode: String?,
        @RequestParam(required = false) startlastuseddate: LocalDateTime?,
        @RequestParam(required = false) endlastuseddate: LocalDateTime?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (order != null || health != null || user != null || rocker != null || term != null || discount != null || startstartday != null || endstartday != null || startendday != null || endendday != null || gym != null || status != null || totalcount != null || usedcount != null || remainingcount != null || qrcode != null || startlastuseddate != null || endlastuseddate != null || startdate != null || enddate != null || false) {
            var filtered = usehealthService.findAll(0, Int.MAX_VALUE).content
            if (order != null) {
                filtered = filtered.filter { it.orderId == order }
            }
            if (health != null) {
                filtered = filtered.filter { it.healthId == health }
            }
            if (user != null) {
                filtered = filtered.filter { it.userId == user }
            }
            if (rocker != null) {
                filtered = filtered.filter { it.rockerId == rocker }
            }
            if (term != null) {
                filtered = filtered.filter { it.termId == term }
            }
            if (discount != null) {
                filtered = filtered.filter { it.discountId == discount }
            }
            if (startstartday != null || endstartday != null) {
                filtered = filtered.filter { filterByDateRange(it.startday, startstartday, endstartday) }
            }
            if (startendday != null || endendday != null) {
                filtered = filtered.filter { filterByDateRange(it.endday, startendday, endendday) }
            }
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (status != null) {
                filtered = filtered.filter { it.status.ordinal == status }
            }
            if (totalcount != null) {
                filtered = filtered.filter { it.totalcount == totalcount }
            }
            if (usedcount != null) {
                filtered = filtered.filter { it.usedcount == usedcount }
            }
            if (remainingcount != null) {
                filtered = filtered.filter { it.remainingcount == remainingcount }
            }
            if (qrcode != null) {
                filtered = filtered.filter { it.qrcode == qrcode }
            }
            if (startlastuseddate != null || endlastuseddate != null) {
                filtered = filtered.filter { filterByDateRange(it.lastuseddate, startlastuseddate, endlastuseddate) }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            usehealthService.findAll(0, Int.MAX_VALUE).content
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
    fun getUsehealth(@PathVariable id: Long): ResponseEntity<UsehealthResponse> {
        val res = usehealthService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/order")
    fun getUsehealthByOrder(@RequestParam order: Long): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByOrder(order)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/health")
    fun getUsehealthByHealth(@RequestParam health: Long): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByHealth(health)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/user")
    fun getUsehealthByUser(@RequestParam user: Long): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/rocker")
    fun getUsehealthByRocker(@RequestParam rocker: Long): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByRocker(rocker)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/term")
    fun getUsehealthByTerm(@RequestParam term: Long): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByTerm(term)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/discount")
    fun getUsehealthByDiscount(@RequestParam discount: Long): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByDiscount(discount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/startday")
    fun getUsehealthByStartday(@RequestParam startday: LocalDateTime): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByStartday(startday)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/endday")
    fun getUsehealthByEndday(@RequestParam endday: LocalDateTime): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByEndday(endday)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/gym")
    fun getUsehealthByGym(@RequestParam gym: Long): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getUsehealthByStatus(@RequestParam status: Status): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/totalcount")
    fun getUsehealthByTotalcount(@RequestParam totalcount: Int): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByTotalcount(totalcount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/usedcount")
    fun getUsehealthByUsedcount(@RequestParam usedcount: Int): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByUsedcount(usedcount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/remainingcount")
    fun getUsehealthByRemainingcount(@RequestParam remainingcount: Int): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByRemainingcount(remainingcount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/qrcode")
    fun getUsehealthByQrcode(@RequestParam qrcode: String): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByQrcode(qrcode)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/lastuseddate")
    fun getUsehealthByLastuseddate(@RequestParam lastuseddate: LocalDateTime): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByLastuseddate(lastuseddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getUsehealthByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<UsehealthResponse>> {
        val res = usehealthService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = usehealthService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createUsehealth(@RequestBody request: UsehealthCreateRequest): ResponseEntity<UsehealthResponse> {
        return try {
            val res = usehealthService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createUsehealths(@RequestBody requests: List<UsehealthCreateRequest>): ResponseEntity<List<UsehealthResponse>> {
        return try {
            val res = usehealthService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateUsehealth(
        @PathVariable id: Long,
        @RequestBody request: UsehealthUpdateRequest
    ): ResponseEntity<UsehealthResponse> {
        val updatedRequest = request.copy(id = id)
        val res = usehealthService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUsehealth(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = usehealthService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteUsehealths(@RequestBody entities: List<Usehealth>): ResponseEntity<Map<String, Boolean>> {
        val success = usehealthService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}