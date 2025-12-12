package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Term
import com.gowoobro.gymspring.entity.TermCreateRequest
import com.gowoobro.gymspring.entity.TermUpdateRequest
import com.gowoobro.gymspring.entity.TermPatchRequest
import com.gowoobro.gymspring.service.TermService
import com.gowoobro.gymspring.entity.TermResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/term")
class TermController(
    private val termService: TermService) {

    private fun toResponse(term: Term): TermResponse {
        return TermResponse.from(term)
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
    fun getTerms(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) daytype: Long?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) term: Int?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || daytype != null || name != null || term != null || startdate != null || enddate != null || false) {
            var filtered = termService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gym == gym }
            }
            if (daytype != null) {
                filtered = filtered.filter { it.daytype == daytype }
            }
            if (name != null) {
                filtered = filtered.filter { it.name == name }
            }
            if (term != null) {
                filtered = filtered.filter { it.term == term }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            termService.findAll(0, Int.MAX_VALUE).content
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
    fun getTerm(@PathVariable id: Long): ResponseEntity<TermResponse> {
        val res = termService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getTermByGym(@RequestParam gym: Long): ResponseEntity<List<TermResponse>> {
        val res = termService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/daytype")
    fun getTermByDaytype(@RequestParam daytype: Long): ResponseEntity<List<TermResponse>> {
        val res = termService.findByDaytype(daytype)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getTermByName(@RequestParam name: String): ResponseEntity<List<TermResponse>> {
        val res = termService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/term")
    fun getTermByTerm(@RequestParam term: Int): ResponseEntity<List<TermResponse>> {
        val res = termService.findByTerm(term)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getTermByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<TermResponse>> {
        val res = termService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = termService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createTerm(@RequestBody request: TermCreateRequest): ResponseEntity<TermResponse> {
        return try {
            val res = termService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createTerms(@RequestBody requests: List<TermCreateRequest>): ResponseEntity<List<TermResponse>> {
        return try {
            val res = termService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateTerm(
        @PathVariable id: Long,
        @RequestBody request: TermUpdateRequest
    ): ResponseEntity<TermResponse> {
        val updatedRequest = request.copy(id = id)
        val res = termService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchTerm(
        @PathVariable id: Long,
        @RequestBody request: TermPatchRequest
    ): ResponseEntity<TermResponse> {
        val patchRequest = request.copy(id = id)
        val res = termService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTerm(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = termService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteTerms(@RequestBody entities: List<Term>): ResponseEntity<Map<String, Boolean>> {
        val success = termService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}