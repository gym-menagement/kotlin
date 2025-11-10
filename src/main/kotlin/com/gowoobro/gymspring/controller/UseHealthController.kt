package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Usehealth
import com.gowoobro.gymspring.entity.UsehealthCreateRequest
import com.gowoobro.gymspring.entity.UsehealthUpdateRequest
import com.gowoobro.gymspring.service.UsehealthService
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
    ): ResponseEntity<Page<Usehealth>> {
        val result = usehealthService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getUsehealth(@PathVariable id: Long): ResponseEntity<Usehealth> {
        val result = usehealthService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/order")
    fun getUsehealthByOrder(@RequestParam order: Long): ResponseEntity<List<Usehealth>> {
        val result = usehealthService.findByOrder(order)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/health")
    fun getUsehealthByHealth(@RequestParam health: Long): ResponseEntity<List<Usehealth>> {
        val result = usehealthService.findByHealth(health)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/user")
    fun getUsehealthByUser(@RequestParam user: Long): ResponseEntity<List<Usehealth>> {
        val result = usehealthService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/rocker")
    fun getUsehealthByRocker(@RequestParam rocker: Long): ResponseEntity<List<Usehealth>> {
        val result = usehealthService.findByRocker(rocker)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/term")
    fun getUsehealthByTerm(@RequestParam term: Long): ResponseEntity<List<Usehealth>> {
        val result = usehealthService.findByTerm(term)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/discount")
    fun getUsehealthByDiscount(@RequestParam discount: Long): ResponseEntity<List<Usehealth>> {
        val result = usehealthService.findByDiscount(discount)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/startday")
    fun getUsehealthByStartday(@RequestParam startday: LocalDateTime): ResponseEntity<List<Usehealth>> {
        val result = usehealthService.findByStartday(startday)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/endday")
    fun getUsehealthByEndday(@RequestParam endday: LocalDateTime): ResponseEntity<List<Usehealth>> {
        val result = usehealthService.findByEndday(endday)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getUsehealthByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Usehealth>> {
        val result = usehealthService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = usehealthService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createUsehealth(@RequestBody request: UsehealthCreateRequest): ResponseEntity<Usehealth> {
        return try {
            val result = usehealthService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createUsehealths(@RequestBody requests: List<UsehealthCreateRequest>): ResponseEntity<List<Usehealth>> {
        return try {
            val result = usehealthService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateUsehealth(
        @PathVariable id: Long,
        @RequestBody request: UsehealthUpdateRequest
    ): ResponseEntity<Usehealth> {
        val updatedRequest = request.copy(id = id)
        val result = usehealthService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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