package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Paymenttype
import com.gowoobro.gymspring.entity.PaymenttypeCreateRequest
import com.gowoobro.gymspring.entity.PaymenttypeUpdateRequest
import com.gowoobro.gymspring.service.PaymenttypeService
import com.gowoobro.gymspring.entity.PaymenttypeResponse
import com.gowoobro.gymspring.entity.GymResponse
import com.gowoobro.gymspring.service.GymService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/paymenttype")
class PaymenttypeController(
    private val paymenttypeService: PaymenttypeService, private val gymService: GymService) {

    private fun toResponse(paymenttype: Paymenttype):
    PaymenttypeResponse {
        
        val gym = gymService.findById(paymenttype.gym)
        val gymResponse = gym?.let{ GymResponse.from(it) }
        
        return PaymenttypeResponse.from(paymenttype, gymResponse)
    }

    @GetMapping
    fun getPaymenttypes(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<PaymenttypeResponse>> {
        val result = paymenttypeService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getPaymenttype(@PathVariable id: Long): ResponseEntity<PaymenttypeResponse> {
        val result = paymenttypeService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getPaymenttypeByGym(@RequestParam gym: Long): ResponseEntity<List<PaymenttypeResponse>> {
        val result = paymenttypeService.findByGym(gym)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getPaymenttypeByName(@RequestParam name: String): ResponseEntity<List<PaymenttypeResponse>> {
        val result = paymenttypeService.findByName(name)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getPaymenttypeByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<PaymenttypeResponse>> {
        val result = paymenttypeService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = paymenttypeService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPaymenttype(@RequestBody request: PaymenttypeCreateRequest): ResponseEntity<PaymenttypeResponse> {
        return try {
            val result = paymenttypeService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPaymenttypes(@RequestBody requests: List<PaymenttypeCreateRequest>): ResponseEntity<List<PaymenttypeResponse>> {
        return try {
            val result = paymenttypeService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updatePaymenttype(
        @PathVariable id: Long,
        @RequestBody request: PaymenttypeUpdateRequest
    ): ResponseEntity<PaymenttypeResponse> {
        val updatedRequest = request.copy(id = id)
        val result = paymenttypeService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletePaymenttype(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = paymenttypeService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deletePaymenttypes(@RequestBody entities: List<Paymenttype>): ResponseEntity<Map<String, Boolean>> {
        val success = paymenttypeService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}