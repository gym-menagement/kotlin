package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Usehealth
import com.gowoobro.gymspring.entity.UsehealthCreateRequest
import com.gowoobro.gymspring.entity.UsehealthUpdateRequest
import com.gowoobro.gymspring.service.UsehealthService
import com.gowoobro.gymspring.entity.UsehealthResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/usehealth")
class UsehealthController(private val usehealthService: UsehealthService) {

    @GetMapping
    fun getUsehealths(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<UsehealthResponse>> {
        val result = usehealthService.findAll(page, pageSize)
        val responsePage = result.map { UsehealthResponse.from(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getUsehealth(@PathVariable id: Long): ResponseEntity<UsehealthResponse> {
        val result = usehealthService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(UsehealthResponse.from(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/order")
    fun getUsehealthByOrder(@RequestParam order: Long): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByOrder(order)
        return ResponseEntity.ok(result.map { UsehealthResponse.from(it) } )
    }

    @GetMapping("/search/health")
    fun getUsehealthByHealth(@RequestParam health: Long): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByHealth(health)
        return ResponseEntity.ok(result.map { UsehealthResponse.from(it) } )
    }

    @GetMapping("/search/user")
    fun getUsehealthByUser(@RequestParam user: Long): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByUser(user)
        return ResponseEntity.ok(result.map { UsehealthResponse.from(it) } )
    }

    @GetMapping("/search/rocker")
    fun getUsehealthByRocker(@RequestParam rocker: Long): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByRocker(rocker)
        return ResponseEntity.ok(result.map { UsehealthResponse.from(it) } )
    }

    @GetMapping("/search/term")
    fun getUsehealthByTerm(@RequestParam term: Long): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByTerm(term)
        return ResponseEntity.ok(result.map { UsehealthResponse.from(it) } )
    }

    @GetMapping("/search/discount")
    fun getUsehealthByDiscount(@RequestParam discount: Long): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByDiscount(discount)
        return ResponseEntity.ok(result.map { UsehealthResponse.from(it) } )
    }

    @GetMapping("/search/startday")
    fun getUsehealthByStartday(@RequestParam startday: LocalDateTime): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByStartday(startday)
        return ResponseEntity.ok(result.map { UsehealthResponse.from(it) } )
    }

    @GetMapping("/search/endday")
    fun getUsehealthByEndday(@RequestParam endday: LocalDateTime): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByEndday(endday)
        return ResponseEntity.ok(result.map { UsehealthResponse.from(it) } )
    }

    @GetMapping("/search/date")
    fun getUsehealthByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByDate(date)
        return ResponseEntity.ok(result.map { UsehealthResponse.from(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = usehealthService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createUsehealth(@RequestBody request: UsehealthCreateRequest): ResponseEntity<UsehealthResponse> {
        return try {
            val result = usehealthService.create(request)
            ResponseEntity.ok(UsehealthResponse.from(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createUsehealths(@RequestBody requests: List<UsehealthCreateRequest>): ResponseEntity<List<UsehealthResponse>> {
        return try {
            val result = usehealthService.createBatch(requests)
            return ResponseEntity.ok(result.map { UsehealthResponse.from(it) } )
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
        val result = usehealthService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(UsehealthResponse.from(result))
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