package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Inquiry
import com.gowoobro.gymspring.entity.InquiryCreateRequest
import com.gowoobro.gymspring.entity.InquiryUpdateRequest
import com.gowoobro.gymspring.entity.InquiryPatchRequest
import com.gowoobro.gymspring.service.InquiryService
import com.gowoobro.gymspring.entity.InquiryResponse
import com.gowoobro.gymspring.enums.inquiry.Type
import com.gowoobro.gymspring.enums.inquiry.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/inquiry")
class InquiryController(
    private val inquiryService: InquiryService) {

    private fun toResponse(inquiry: Inquiry): InquiryResponse {
        return InquiryResponse.from(inquiry)
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
    fun getInquirys(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pagesize: Int,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) type: Int?,
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) content: String?,
        @RequestParam(required = false) status: Int?,
        @RequestParam(required = false) answer: String?,
        @RequestParam(required = false) answeredby: Long?,
        @RequestParam(required = false) startanswereddate: LocalDateTime?,
        @RequestParam(required = false) endanswereddate: LocalDateTime?,
        @RequestParam(required = false) startcreateddate: LocalDateTime?,
        @RequestParam(required = false) endcreateddate: LocalDateTime?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (user != null || gym != null || type != null || title != null || content != null || status != null || answer != null || answeredby != null || startanswereddate != null || endanswereddate != null || startcreateddate != null || endcreateddate != null || startdate != null || enddate != null || false) {
            var filtered = inquiryService.findAll(0, Int.MAX_VALUE).content
            if (user != null) {
                filtered = filtered.filter { it.userId == user }
            }
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (type != null) {
                filtered = filtered.filter { it.type.ordinal == type }
            }
            if (title != null) {
                filtered = filtered.filter { it.title == title }
            }
            if (content != null) {
                filtered = filtered.filter { it.content == content }
            }
            if (status != null) {
                filtered = filtered.filter { it.status.ordinal == status }
            }
            if (answer != null) {
                filtered = filtered.filter { it.answer == answer }
            }
            if (answeredby != null) {
                filtered = filtered.filter { it.answeredbyId == answeredby }
            }
            if (startanswereddate != null || endanswereddate != null) {
                filtered = filtered.filter { filterByDateRange(it.answereddate, startanswereddate, endanswereddate) }
            }
            if (startcreateddate != null || endcreateddate != null) {
                filtered = filtered.filter { filterByDateRange(it.createddate, startcreateddate, endcreateddate) }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            inquiryService.findAll(0, Int.MAX_VALUE).content
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
    fun getInquiry(@PathVariable id: Long): ResponseEntity<InquiryResponse> {
        val res = inquiryService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getInquiryByUser(@RequestParam user: Long): ResponseEntity<List<InquiryResponse>> {
        val res = inquiryService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/gym")
    fun getInquiryByGym(@RequestParam gym: Long): ResponseEntity<List<InquiryResponse>> {
        val res = inquiryService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getInquiryByType(@RequestParam type: Type): ResponseEntity<List<InquiryResponse>> {
        val res = inquiryService.findByType(type)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/title")
    fun getInquiryByTitle(@RequestParam title: String): ResponseEntity<List<InquiryResponse>> {
        val res = inquiryService.findByTitle(title)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/content")
    fun getInquiryByContent(@RequestParam content: String): ResponseEntity<List<InquiryResponse>> {
        val res = inquiryService.findByContent(content)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getInquiryByStatus(@RequestParam status: Status): ResponseEntity<List<InquiryResponse>> {
        val res = inquiryService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/answer")
    fun getInquiryByAnswer(@RequestParam answer: String): ResponseEntity<List<InquiryResponse>> {
        val res = inquiryService.findByAnswer(answer)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/answeredby")
    fun getInquiryByAnsweredby(@RequestParam answeredby: Long): ResponseEntity<List<InquiryResponse>> {
        val res = inquiryService.findByAnsweredby(answeredby)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/answereddate")
    fun getInquiryByAnswereddate(@RequestParam answereddate: LocalDateTime): ResponseEntity<List<InquiryResponse>> {
        val res = inquiryService.findByAnswereddate(answereddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/createddate")
    fun getInquiryByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<InquiryResponse>> {
        val res = inquiryService.findByCreateddate(createddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getInquiryByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<InquiryResponse>> {
        val res = inquiryService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = inquiryService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createInquiry(@RequestBody request: InquiryCreateRequest): ResponseEntity<InquiryResponse> {
        return try {
            val res = inquiryService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createInquirys(@RequestBody requests: List<InquiryCreateRequest>): ResponseEntity<List<InquiryResponse>> {
        return try {
            val res = inquiryService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateInquiry(
        @PathVariable id: Long,
        @RequestBody request: InquiryUpdateRequest
    ): ResponseEntity<InquiryResponse> {
        val updatedRequest = request.copy(id = id)
        val res = inquiryService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchInquiry(
        @PathVariable id: Long,
        @RequestBody request: InquiryPatchRequest
    ): ResponseEntity<InquiryResponse> {
        val patchRequest = request.copy(id = id)
        val res = inquiryService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteInquiry(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = inquiryService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteInquirys(@RequestBody entities: List<Inquiry>): ResponseEntity<Map<String, Boolean>> {
        val success = inquiryService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}