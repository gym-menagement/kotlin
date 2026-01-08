package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Membership
import com.gowoobro.gymspring.entity.MembershipCreateRequest
import com.gowoobro.gymspring.entity.MembershipUpdateRequest
import com.gowoobro.gymspring.entity.MembershipPatchRequest
import com.gowoobro.gymspring.service.MembershipService
import com.gowoobro.gymspring.entity.MembershipResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/membership")
class MembershipController(
    private val membershipService: MembershipService) {

    private fun toResponse(membership: Membership): MembershipResponse {
        return MembershipResponse.from(membership)
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
    fun getMemberships(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pagesize: Int,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (user != null || gym != null || startdate != null || enddate != null || false) {
            var filtered = membershipService.findAll(0, Int.MAX_VALUE).content
            if (user != null) {
                filtered = filtered.filter { it.userId == user }
            }
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            membershipService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pagesize > 0) (totalElements + pagesize - 1) / pagesize else 1
        val startIndex = page * pagesize
        val endIndex = minOf(startIndex + pagesize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pagesize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getMembership(@PathVariable id: Long): ResponseEntity<MembershipResponse> {
        val res = membershipService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getMembershipByUser(@RequestParam user: Long): ResponseEntity<List<MembershipResponse>> {
        val res = membershipService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/gym")
    fun getMembershipByGym(@RequestParam gym: Long): ResponseEntity<List<MembershipResponse>> {
        val res = membershipService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getMembershipByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<MembershipResponse>> {
        val res = membershipService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = membershipService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createMembership(@RequestBody request: MembershipCreateRequest): ResponseEntity<MembershipResponse> {
        return try {
            val res = membershipService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createMemberships(@RequestBody requests: List<MembershipCreateRequest>): ResponseEntity<List<MembershipResponse>> {
        return try {
            val res = membershipService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateMembership(
        @PathVariable id: Long,
        @RequestBody request: MembershipUpdateRequest
    ): ResponseEntity<MembershipResponse> {
        val updatedRequest = request.copy(id = id)
        val res = membershipService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchMembership(
        @PathVariable id: Long,
        @RequestBody request: MembershipPatchRequest
    ): ResponseEntity<MembershipResponse> {
        val patchRequest = request.copy(id = id)
        val res = membershipService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMembership(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = membershipService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteMemberships(@RequestBody entities: List<Membership>): ResponseEntity<Map<String, Boolean>> {
        val success = membershipService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}