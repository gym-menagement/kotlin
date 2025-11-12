package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Inquiry
import com.gowoobro.gymspring.entity.InquiryCreateRequest
import com.gowoobro.gymspring.entity.InquiryUpdateRequest
import com.gowoobro.gymspring.service.InquiryService
import com.gowoobro.gymspring.entity.InquiryResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.inquiry.Type
import com.gowoobro.gymspring.enums.inquiry.Status


@RestController
@RequestMapping("/api/inquiry")
class InquiryController(private val inquiryService: InquiryService) {

    @GetMapping
    fun getInquirys(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<InquiryResponse>> {
        val result = inquiryService.findAll(page, pageSize)
        val responsePage = result.map { InquiryResponse.from(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getInquiry(@PathVariable id: Long): ResponseEntity<InquiryResponse> {
        val result = inquiryService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(InquiryResponse.from(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getInquiryByUser(@RequestParam user: Long): ResponseEntity<List<InquiryResponse>> {
        val result = inquiryService.findByUser(user)
        return ResponseEntity.ok(result.map { InquiryResponse.from(it) } )
    }

    @GetMapping("/search/gym")
    fun getInquiryByGym(@RequestParam gym: Long): ResponseEntity<List<InquiryResponse>> {
        val result = inquiryService.findByGym(gym)
        return ResponseEntity.ok(result.map { InquiryResponse.from(it) } )
    }

    @GetMapping("/search/type")
    fun getInquiryByType(@RequestParam type: Type): ResponseEntity<List<InquiryResponse>> {
        val result = inquiryService.findByType(type)
        return ResponseEntity.ok(result.map { InquiryResponse.from(it) } )
    }

    @GetMapping("/search/title")
    fun getInquiryByTitle(@RequestParam title: String): ResponseEntity<List<InquiryResponse>> {
        val result = inquiryService.findByTitle(title)
        return ResponseEntity.ok(result.map { InquiryResponse.from(it) } )
    }

    @GetMapping("/search/content")
    fun getInquiryByContent(@RequestParam content: String): ResponseEntity<List<InquiryResponse>> {
        val result = inquiryService.findByContent(content)
        return ResponseEntity.ok(result.map { InquiryResponse.from(it) } )
    }

    @GetMapping("/search/status")
    fun getInquiryByStatus(@RequestParam status: Status): ResponseEntity<List<InquiryResponse>> {
        val result = inquiryService.findByStatus(status)
        return ResponseEntity.ok(result.map { InquiryResponse.from(it) } )
    }

    @GetMapping("/search/answer")
    fun getInquiryByAnswer(@RequestParam answer: String): ResponseEntity<List<InquiryResponse>> {
        val result = inquiryService.findByAnswer(answer)
        return ResponseEntity.ok(result.map { InquiryResponse.from(it) } )
    }

    @GetMapping("/search/answeredby")
    fun getInquiryByAnsweredby(@RequestParam answeredby: Long): ResponseEntity<List<InquiryResponse>> {
        val result = inquiryService.findByAnsweredby(answeredby)
        return ResponseEntity.ok(result.map { InquiryResponse.from(it) } )
    }

    @GetMapping("/search/answereddate")
    fun getInquiryByAnswereddate(@RequestParam answereddate: LocalDateTime): ResponseEntity<List<InquiryResponse>> {
        val result = inquiryService.findByAnswereddate(answereddate)
        return ResponseEntity.ok(result.map { InquiryResponse.from(it) } )
    }

    @GetMapping("/search/createddate")
    fun getInquiryByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<InquiryResponse>> {
        val result = inquiryService.findByCreateddate(createddate)
        return ResponseEntity.ok(result.map { InquiryResponse.from(it) } )
    }

    @GetMapping("/search/date")
    fun getInquiryByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<InquiryResponse>> {
        val result = inquiryService.findByDate(date)
        return ResponseEntity.ok(result.map { InquiryResponse.from(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = inquiryService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createInquiry(@RequestBody request: InquiryCreateRequest): ResponseEntity<InquiryResponse> {
        return try {
            val result = inquiryService.create(request)
            ResponseEntity.ok(InquiryResponse.from(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createInquirys(@RequestBody requests: List<InquiryCreateRequest>): ResponseEntity<List<InquiryResponse>> {
        return try {
            val result = inquiryService.createBatch(requests)
            return ResponseEntity.ok(result.map { InquiryResponse.from(it) } )
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
        val result = inquiryService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(InquiryResponse.from(result))
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