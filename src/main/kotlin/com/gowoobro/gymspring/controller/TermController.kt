package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Term
import com.gowoobro.gymspring.entity.TermCreateRequest
import com.gowoobro.gymspring.entity.TermUpdateRequest
import com.gowoobro.gymspring.service.TermService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/term")
class TermController(private val termService: TermService) {
    
    @GetMapping
    fun getTerms(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Term>> {
        val result = termService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/{id}")
    fun getTerm(@PathVariable id: Long): ResponseEntity<Term> {
        val result = termService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @GetMapping("/search/name")
    fun getTermsByMobileSearchName(@RequestParam name: String): ResponseEntity<List<Term>> {
        val result = termService.findByNameContaining(name)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = termService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }
    
    @PostMapping
    fun createTerm(@RequestBody request: TermCreateRequest): ResponseEntity<Term> {
        return try {
            val result = termService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }
    
    @PostMapping("/batch")
    fun createTerms(@RequestBody requests: List<TermCreateRequest>): ResponseEntity<List<Term>> {
        return try {
            val result = termService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }
    
    @PutMapping("/{id}")
    fun updateTerm(
        @PathVariable id: Long,
        @RequestBody request: TermUpdateRequest
    ): ResponseEntity<Term> {
        val updatedRequest = request.copy(id = id)
        val result = termService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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