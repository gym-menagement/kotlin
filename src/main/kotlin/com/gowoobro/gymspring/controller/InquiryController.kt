package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Inquiry
import com.gowoobro.gymspring.entity.InquiryCreateRequest
import com.gowoobro.gymspring.entity.InquiryUpdateRequest
import com.gowoobro.gymspring.service.InquiryService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/inquiry")
class InquiryController(private val inquiryService: InquiryService) {

    @GetMapping
    fun getInquirys(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Inquiry>> {
        val result = inquiryService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getInquiry(@PathVariable id: Long): ResponseEntity<Inquiry> {
        val result = inquiryService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getInquiryByUser(@RequestParam user: Long): ResponseEntity<List<Inquiry>> {
        val result = inquiryService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/gym")
    fun getInquiryByGym(@RequestParam gym: Long): ResponseEntity<List<Inquiry>> {
        val result = inquiryService.findByGym(gym)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/type")
    fun getInquiryByType(@RequestParam type: Int): ResponseEntity<List<Inquiry>> {
        val result = inquiryService.findByType(type)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/title")
    fun getInquiryByTitle(@RequestParam title: String): ResponseEntity<List<Inquiry>> {
        val result = inquiryService.findByTitle(title)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/content")
    fun getInquiryByContent(@RequestParam content: String): ResponseEntity<List<Inquiry>> {
        val result = inquiryService.findByContent(content)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/status")
    fun getInquiryByStatus(@RequestParam status: Int): ResponseEntity<List<Inquiry>> {
        val result = inquiryService.findByStatus(status)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/answer")
    fun getInquiryByAnswer(@RequestParam answer: String): ResponseEntity<List<Inquiry>> {
        val result = inquiryService.findByAnswer(answer)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/answeredby")
    fun getInquiryByAnsweredby(@RequestParam answeredby: Long): ResponseEntity<List<Inquiry>> {
        val result = inquiryService.findByAnsweredby(answeredby)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/answereddate")
    fun getInquiryByAnswereddate(@RequestParam answereddate: LocalDateTime): ResponseEntity<List<Inquiry>> {
        val result = inquiryService.findByAnswereddate(answereddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/createddate")
    fun getInquiryByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<Inquiry>> {
        val result = inquiryService.findByCreateddate(createddate)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = inquiryService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createInquiry(@RequestBody request: InquiryCreateRequest): ResponseEntity<Inquiry> {
        return try {
            val result = inquiryService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createInquirys(@RequestBody requests: List<InquiryCreateRequest>): ResponseEntity<List<Inquiry>> {
        return try {
            val result = inquiryService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateInquiry(
        @PathVariable id: Long,
        @RequestBody request: InquiryUpdateRequest
    ): ResponseEntity<Inquiry> {
        val updatedRequest = request.copy(id = id)
        val result = inquiryService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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