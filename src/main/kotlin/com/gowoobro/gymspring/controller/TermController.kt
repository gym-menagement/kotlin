package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Term
import com.gowoobro.gymspring.entity.TermCreateRequest
import com.gowoobro.gymspring.entity.TermUpdateRequest
import com.gowoobro.gymspring.service.TermService
import com.gowoobro.gymspring.entity.TermResponse
import com.gowoobro.gymspring.entity.GymResponse
import com.gowoobro.gymspring.service.GymService
import com.gowoobro.gymspring.entity.DaytypeResponse
import com.gowoobro.gymspring.service.DaytypeService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/term")
class TermController(
    private val termService: TermService, private val gymService: GymService, private val daytypeService: DaytypeService) {

    private fun toResponse(term: Term):
    TermResponse {
        
        val gym = gymService.findById(term.gym)
        val gymResponse = gym?.let{ GymResponse.from(it) }
        
        val daytype = daytypeService.findById(term.daytype)
        val daytypeResponse = daytype?.let{ DaytypeResponse.from(it) }
        
        return TermResponse.from(term, gymResponse, daytypeResponse)
    }

    @GetMapping
    fun getTerms(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<TermResponse>> {
        val result = termService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getTerm(@PathVariable id: Long): ResponseEntity<TermResponse> {
        val result = termService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getTermByGym(@RequestParam gym: Long): ResponseEntity<List<TermResponse>> {
        val result = termService.findByGym(gym)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/daytype")
    fun getTermByDaytype(@RequestParam daytype: Long): ResponseEntity<List<TermResponse>> {
        val result = termService.findByDaytype(daytype)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getTermByName(@RequestParam name: String): ResponseEntity<List<TermResponse>> {
        val result = termService.findByName(name)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/term")
    fun getTermByTerm(@RequestParam term: Int): ResponseEntity<List<TermResponse>> {
        val result = termService.findByTerm(term)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getTermByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<TermResponse>> {
        val result = termService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = termService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createTerm(@RequestBody request: TermCreateRequest): ResponseEntity<TermResponse> {
        return try {
            val result = termService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createTerms(@RequestBody requests: List<TermCreateRequest>): ResponseEntity<List<TermResponse>> {
        return try {
            val result = termService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
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
        val result = termService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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